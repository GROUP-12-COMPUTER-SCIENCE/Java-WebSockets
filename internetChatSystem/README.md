# Java Web Sockets Programming

## Table of contents


## Overview

This repository contains a simple implementation of a server and multiple clients using Java DatagramSockets for communication. The server listens for messages from clients, displays them, and keeps track of the number of messages received from each client. Clients can send messages to the server and terminate the connection by sending "bye".

## Features

- **Server-Client Communication**: The server listens for messages from multiple clients.
- **Message Tracking**: The server keeps track of the number of messages received from each client.
- **Termination Command**: Clients can terminate the connection by sending "bye".

## Installation

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Java Runtime Environment (JRE) 11 or higher

### Cloning the Repository

To clone the repository, use the following command:

```sh
git clone https://github.com/yourusername/Java-WebSockets.git
cd Java-WebSockets
```

## Compilation

To compile the Java files, open a terminal and navigate to the directory containing the files, then run:

```sh
javac Server.java Client.java Client2.java
```

## Running

### Running the Server

Start the server by running:

```sh
java Server
```

This will start the server, which listens on port 1234 for incoming messages.

### Running the Clients

Start the clients by running the following in separate terminal windows:

```sh
java Client
```

```sh
java Client2
```

Both clients will prompt you to enter messages, which will be sent to the server.

### Stopping the Server and Clients

To stop the server or a client, type `bye` and press Enter. This will terminate the connection and stop the server or client.

## Contribute

Contributions are welcome! If you have any improvements or suggestions, feel free to open an issue or create a pull request.

### Steps to Contribute

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Open a pull request.

## Support

If you have any issues or questions, please open an issue in this repository.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Code Explanation

### Server.java

The server-side implementation performs the following steps:

1. **Initialize DatagramSocket**: The server listens on port 1234.
   ```java
   DatagramSocket ds = new DatagramSocket(1234);
   ```

2. **Receive DatagramPacket**: The server waits to receive packets from clients.
   ```java
   DatagramPacket DpReceive = new DatagramPacket(receive, receive.length);
   ds.receive(DpReceive);
   ```

3. **Extract and Track Client Information**: The server extracts the client's address and port, tracks the client, and counts the number of messages received from each client.
   ```java
   InetAddress clientAddress = DpReceive.getAddress();
   int clientPort = DpReceive.getPort();
   String clientKey = clientAddress.toString() + ":" + clientPort;
   ```

4. **Display Client Messages**: The server prints the client's message and the message count.
   ```java
   System.out.println("Client (" + clientId + "): " + data(receive));
   ```

5. **Terminate on "bye"**: The server stops if a client sends "bye".
   ```java
   if (data(receive).toString().equals("bye")) {
       break;
   }
   ```

### Client.java and Client2.java

Both client-side implementations perform the following steps:

1. **Initialize DatagramSocket**: The client creates a socket for communication.
   ```java
   DatagramSocket ds = new DatagramSocket();
   ```

2. **Read User Input**: The client reads messages from the user.
   ```java
   Scanner sc = new Scanner(System.in);
   String inp = sc.nextLine();
   ```

3. **Send DatagramPacket**: The client sends the message to the server.
   ```java
   DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 1234);
   ds.send(DpSend);
   ```

4. **Terminate on "bye"**: The client stops if the user types "bye".
   ```java
   if (inp.equals("bye")) {
       break;
   }
   ```

## Conclusion

This simple implementation demonstrates basic UDP communication between a server and multiple clients using Java. The server can handle multiple clients and keeps track of the messages received from each client. Clients can send messages to the server and terminate the connection by sending "bye".


## Authors

- **@waltertaya**
- **@Clint171**
- **@lemwas**
- **@CHEGGEBB**
- **@Baruk**
