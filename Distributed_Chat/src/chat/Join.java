package chat;

import java.io.*;
import java.net.*;

public class Join {
	int has_Joined;
	
	public void talk_to_peers(ActiveParticipant activeParticipant, NodeInfo myNodeInfo, String name, String input, BufferedReader bufferedReader) throws IOException {
		
		
		
		String[] input_msgs = input.split(" ");
		int length = input_msgs.length;
		if(!input_msgs.equals("s"))
		{
			
			for(int index=0; index<length; index++)
			{
				
				Socket socket_Predecessor = null;
				Socket socket_Sucesssor = null;
				
				String[] string_adress = input_msgs[index].split(":");
				System.out.println("adress is: "+string_adress[1]);
				
				try {
					
						NodeInfo nodeInfo_predecessor = null;
						NodeInfo nodeInfo_sucessor = null;
						NodeInfo node = new NodeInfo();
						int portNumber = Integer.parseInt(string_adress[1]);
						String logicalName = string_adress[0];
						
						if (node.have_Predecessor == 0) {
							nodeInfo_predecessor = new NodeInfo(logicalName, portNumber);
							socket_Predecessor = new Socket(logicalName, portNumber);
							has_Joined = 1;
							NodeChat node_chat_Object = new NodeChat(socket_Predecessor);
							node_chat_Object.start();
					    }
						else if(node.have_Successor == 0)
						{
							nodeInfo_sucessor = new NodeInfo(logicalName, portNumber);
							socket_Sucesssor = new Socket(logicalName, portNumber);
							has_Joined = 1;
							NodeChat node_chat_Object = new NodeChat(socket_Sucesssor);
							node_chat_Object.start();
						}
						else
							System.out.println("both sucessor & predecessor is present!");
		
				}
				catch(Exception e) {
					// handle exc
					if(socket_Predecessor != null) 
						socket_Predecessor.close();
					/*
					 * else System.out.println("Invalid!");
					 */
					else if(socket_Sucesssor != null) 
						socket_Sucesssor.close();
		        	else 
		        		System.out.println("Invalid!");
				}
			}
		}
	}
}
