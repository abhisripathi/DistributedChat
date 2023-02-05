/**
 * 
 */
package chat;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author ps747
 *
 */
public class ActiveParticipant extends Thread{

	/**
	 * to handl the request
	 */
	
	int portNumbers;
	NodeInfo  myNodeInfo = new NodeInfo();
	ServerSocket serverSocket = null;
	
	ArrayList<ActiveNodeWorker> thread = new ArrayList<ActiveNodeWorker>();
	public ActiveParticipant(int portNumbers) throws IOException {
		// TODO Auto-generated constructor stub
		this.portNumbers = portNumbers;
		this.serverSocket = new ServerSocket(portNumbers);
	}
	
	//get active participant port number
	public int getActiveParticipant() {
		return portNumbers;
	}
	
	//Running the thread class
    public void run() {
    	try {
    		while(true) {
    			ActiveNodeWorker active_NodeWorker = new ActiveNodeWorker(serverSocket.accept(),this);
    			thread.add(active_NodeWorker);
    			active_NodeWorker.start();	
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
  
    
    //sending messages
    public void sendMessage(String msg)
    {
    	try {
    		for (Thread th : thread) {
    			
    			 Socket sSocket1 = null;
    			 OutputStream os = sSocket1.getOutputStream();
    		     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
    		     writer.write(msg);
    		     writer.newLine();
    		     writer.close();
    		        
    		}
    	}
    	catch(IOException e){
    		//handle the exception
    	}
    }
    	
    public ArrayList<ActiveNodeWorker> getthreads(){
		return thread;
    	
    }
    
    boolean Join(String name, int portNumber){
    	return true;
    }

	public void setThreads(List<ActiveNodeWorker> threads) {
		// TODO Auto-generated method stub
		this.thread = thread;
	}

}
