# Temperature Sensor MQTT Publisher

## Description

The **TemperatureSensorProject** is a Java-based IoT simulation application that emulates the behavior of a temperature sensor. It generates temperature data and publishes it to an MQTT broker using the lightweight and efficient MQTT protocol. This project is designed for developers exploring machine-to-machine (M2M) communication, smart monitoring systems, or lightweight IoT architectures.

Built with **Apache Maven** and using the **Eclipse Paho MQTT client**, the project supports seamless communication with popular MQTT brokers such as Mosquitto and HiveMQ. Execution is supported through NetBeans action definitions (`nbactions.xml`) and an optional Ant-based build script (`build.xml`), making it easy to deploy and run in different environments.

A complementary **Temperature Subscriber** project is suggested by the build configuration, enabling full end-to-end publish-subscribe communication.

## Features

* ✅ MQTT-based communication using Eclipse Paho
* 🌡️ Simulated or test-generated temperature readings
* ⚡ Lightweight and portable for embedded or edge systems
* 🔁 Executable via command-line, Maven, or NetBeans actions
* 🔧 Modular and extensible Java application architecture
* 🌍 Compatible with common MQTT brokers (e.g., Mosquitto, HiveMQ)
* 📡 Easily extendable to other IoT sensor types or cloud platforms

## Technologies Used

* Java SE 8
* Apache Maven
* Eclipse Paho MQTT Client
* Apache Ant (build support)
* NetBeans Project Configuration

## Use Cases

* Educational tool for learning IoT and MQTT
* Simulated input source for MQTT subscribers
* Real-time environmental data stream testing
* Prototype for smart monitoring systems

## Getting Started

1. Clone the repository
2. Ensure Maven is installed and configured
3. Run the project using:

   ```bash
   mvn compile exec: java
   ```

   or via NetBeans run actions.

> Note: Ensure a valid MQTT broker is running and accessible before starting the application.
