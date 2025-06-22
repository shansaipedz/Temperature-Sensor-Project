/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample.temperaturesensorproject;

import java.sql.Timestamp;
// The four libraries below are needed to communicate with a MQTT broker
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Required from the assignment instruction 
 * Publishes randomly-generated temperature every 5 seconds
 * to a MQTT broker
 *
 * @author Jerry codes adopted from https://www.eclipse.org/paho/clients/java/
 */
public class TemperatureSensor {

  // topics to published to in the MQTT broker
  static final String TOPIC_TEMP_COLD = "pittsburgh/temperature/coldTemps";
  static final String TOPIC_TEMP_NICE = "pittsburgh/temperature/niceTemps";
  static final String TOPIC_TEMP_HOT = "pittsburgh/temperature/hotTemps";

  // Quality of Service
  static int qos = 2; // guarantees that message is delivered exactly once
  // url of the mqtt broker
  // port 1883 used to connect via TCP
  static String broker = "tcp://localhost:1883"; 
  // client ID for identification
  static String clientId = "TemperatureSensor";
  static MemoryPersistence persistence = new MemoryPersistence();
  
  // MQTT Client object, initially none (null)
  static MqttClient mqttClient = null;

  /**
   * main method
   * invoked with the project runs
   * @param args
   * @throws InterruptedException 
   */
  public static void main(String[] args) throws InterruptedException {
    
    // try to connect to the MQTT broker
    if (connectToMQTT()) {
      // if connected
      // loop on generating a random temperature and publishing to appropriate
      // topic with timestamp to the MQTT broker
      while (true) {
        // generate a random temp between 0 to 100
        int randomTemp = (int) (Math.random() * 100);
        
        // publish to appropriate topics
        if (randomTemp <= 45) { // 0 to 45 degrees, cold
          publish(TOPIC_TEMP_COLD, randomTemp + " degrees - " + timeStamp());
        } else if (randomTemp <= 80) { // 46 to 80, Nice
          publish(TOPIC_TEMP_NICE, randomTemp + " degrees - " + timeStamp());
        } else { // 81 to 100, hot
          publish(TOPIC_TEMP_HOT, randomTemp + " degrees - " + timeStamp());
        }
        Thread.sleep(5000); // wait for 5 seconds
      }
    } else {  // cannot connect to broker, display message
      System.out.println("Cannot connect to MQTT broker");
    }
  }

  /**
   * returns the current time stamp in yyyy-mm-dd hh:mm:ss.ms format
   */
  static String timeStamp() {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    return timestamp.toString();
  }

  /**
   * Establish connection with the MQTT broker
   * if successful, the mqttClient object is instantiated and returns true
   * otherwise false is returned and mqttClient remain null
   * @return 
   */
  static boolean connectToMQTT() {
    try {
      // instantiate mqttClient object
      mqttClient = new MqttClient(broker, clientId, persistence);
      // instantiate a MQTT connection options
      MqttConnectOptions connOpts = new MqttConnectOptions();
      connOpts.setCleanSession(true);
      System.out.println("Connecting to broker: " + broker);
      mqttClient.connect(connOpts);
      System.out.println("Connected");
      return true;
    } catch (MqttException me) {
      me.printStackTrace();
      return false;
    }
  }

  /**
   * sends a message (i.e. publish a message to the mqtt broker)
   * @param topic
   * @param message 
   */
  static void publish(String topic, String message) {
    try {
      System.out.println(topic + "  " + message); // for debugging only
      // create an MqttMessage from the message parameter
      MqttMessage mqttMsg = new MqttMessage(message.getBytes());
      //set the quality of service (qos)
      mqttMsg.setQos(qos);
      // publish the message to the mqtt broker with appropriate topic
      mqttClient.publish(topic, mqttMsg);
      // mqttClient.disconnect();
    } catch (MqttException ex) {
      //Logger.getLogger(TemperatureSensor.class.getName()).log(Level.SEVERE, null, ex);
      ex.printStackTrace();
    }
  }
}
