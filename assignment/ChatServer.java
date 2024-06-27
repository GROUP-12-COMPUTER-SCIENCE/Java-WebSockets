import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<PrintWriter> clientOutputs = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Chat server started...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter output;
        private BufferedReader input;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);

                synchronized (clientOutputs) {
                    clientOutputs.add(output);
                }

                String nickname = input.readLine();
                String message;
                while ((message = input.readLine()) != null) {
                    broadcastMessage(nickname + ": " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (clientOutputs) {
                    clientOutputs.remove(output);
                }
            }
        }

        private void broadcastMessage(String message) {
            synchronized (clientOutputs) {
                for (PrintWriter writer : clientOutputs) {
                    writer.println(message);
                }
            }
        }
    }
}
