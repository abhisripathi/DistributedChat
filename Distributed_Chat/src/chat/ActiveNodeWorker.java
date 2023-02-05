package chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ActiveNodeWorker extends Thread{
	
	public ActiveParticipant activParticipant;
	public Socket chatConnection;
	public ObjectOutputStream out;
	//public PrintWriter prinntWriter;
	
	public ActiveNodeWorker() {
		//constructor 
	}
	public ActiveNodeWorker(Socket chatConnection, ActiveParticipant activrParticipant) {
		this.activParticipant = activrParticipant;
		this.chatConnection = chatConnection;
	}
	
	//get the input & output stream
	public ObjectOutputStream getOutputStream() {
	    return this.out;
	}
	
	public void run(){
		
		//init variables
		ObjectInputStream readFromNet = null;
		//ObjectOutputStream writeToNet;
		MessageNote messageNote = null;
		/*
		 * try {
		 * 
		 * readFromNet = new ObjectInputStream(chatConnection.getInputStream());
		 * writeToNet = new ObjectOutputStream(chatConnection.getOutputStream());
		 * 
		 * message = (Message) readFromNet.readObject();
		 * 
		 * } catch (Exception ex) {
		 * 
		 * }
		 */
		
		try {
		    BufferedReader buffered_read = new BufferedReader(new InputStreamReader(this.chatConnection.getInputStream()));
		    ObjectOutputStream writeToNet =new ObjectOutputStream(chatConnection.getOutputStream());
		    while (true) {
		    	activParticipant.sendMessage(buffered_read.readLine());
		    	writeToNet.writeObject(activParticipant);
		    	writeToNet.flush();
		    }
		} catch (IOException e) {
		    // Handle the exception
			List<ActiveNodeWorker> threads = activParticipant.getthreads();
			threads.remove(this);
			activParticipant.setThreads(threads);
			activParticipant.getthreads().remove(this);
		}
	}
}
