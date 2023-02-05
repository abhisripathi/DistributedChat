package chat;


import java.io.*;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;


public class NodeChat extends Thread {
    private BufferedReader buffered_reader;

    public NodeChat(Socket socket) throws IOException {
    	
    	InputStream input_stream = socket.getInputStream();
        Reader reader = new InputStreamReader(input_stream);
        this.buffered_reader = new BufferedReader(reader);
        
    }

    @Override
    public void run() {
        boolean entryValue = true;
        while (entryValue) {
            try {
                String message = buffered_reader.readLine();
                System.out.println(message);
            } catch (IOException e) {
            	entryValue = false;
                Thread.currentThread().interrupt();
            }
        }
    }
}





