import java.io.*;
import java.net.*;

public class ChatClient {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private BufferedReader console;

    public ChatClient(String serverAddress, int serverPort) throws IOException {
        socket = new Socket(serverAddress, serverPort);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() throws IOException {
        System.out.print("Enter your nickname: ");
        String nickname = console.readLine();
        output.println(nickname);

        new Thread(new IncomingMessageHandler()).start();

        String message;
        while ((message = console.readLine()) != null) {
            output.println(message);
        }
    }

    private class IncomingMessageHandler implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = input.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        int serverPort = 12345;
        ChatClient client = new ChatClient(serverAddress, serverPort);
        client.start();
    }
}
