// Java program to illustrate Server side 
// Implementation using DatagramSocket 
import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.net.SocketException; 
import java.util.HashMap; 
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Server
{ 
    public static void main(String[] args) throws IOException 
    { 
        List<String> clients = new ArrayList<>();
        // Step 1 : Create a socket to listen at port 1234 
        DatagramSocket ds = new DatagramSocket(1234); 
        System.out.println("Server is listening on port 1234..."); 
        byte[] receive = new byte[65535]; 

        DatagramPacket DpReceive = null; 
        Map<String, Integer> clientCount = new HashMap<>();
        
        while (true) 
        { 
            // Step 2 : create a DatagramPacket to receive the data. 
            DpReceive = new DatagramPacket(receive, receive.length); 

            // Step 3 : receive the data in byte buffer. 
            ds.receive(DpReceive); 

            // Extract client information
            InetAddress clientAddress = DpReceive.getAddress();
            int clientPort = DpReceive.getPort();
            String clientKey = clientAddress.toString() + ":" + clientPort;
            if(!clients.contains(clientKey))
            {
                clients.add(clientKey);
            }
            int clientId = -1;
            for(int i=0 ; i < clients.size(); i++)
            {
                if(clients.get(i).equals(clientKey))
                {
                    clientId = i+1;
                    break;
                }
            }

            // Update or add the client to the map
            clientCount.put(clientKey, clientCount.getOrDefault(clientKey, 0) + 1);

            // Display client message and info
            if(!(clientId == -1)){
                System.out.println("Client (" + clientId + "): " + data(receive)); 
                System.out.println("Client (" + clientId + ") message count: " + clientCount.get(clientKey));
            }

            // Exit the server if the client sends "bye"
            if (data(receive).toString().equals("bye")) 
            { 
                System.out.println("Client (" + clientKey + ") sent bye.....EXITING"); 
                break; 
            } 

            // Clear the buffer after every message. 
            receive = new byte[65535]; 
        } 
    } 

    // A utility method to convert the byte array 
    // data into a string representation. 
    public static StringBuilder data(byte[] a) 
    { 
        if (a == null) 
            return null; 
        StringBuilder ret = new StringBuilder(); 
        int i = 0; 
        while (a[i] != 0) 
        { 
            ret.append((char) a[i]); 
            i++; 
        } 
        return ret; 
    } 
}
