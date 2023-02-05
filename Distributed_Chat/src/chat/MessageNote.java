package chat;

import java.io.Serializable;
import java.io.BufferedReader;
import java.io.*;

public class MessageNote implements Serializable{
	
	public void transferMessages(String inputLine, String inputVal, BufferedReader bufferedRead, ActiveParticipant activParticipant, NodeInfo node) {
		
		MainClass main = new MainClass();
		Leave userLeave = new Leave();
		System.out.println("enter the message to send!");
		try {
			boolean entryVal = true;
			
			while(entryVal)
			{
				String message = main.myMessage(bufferedRead);
				if(message.equals("LEAVE")) {
					entryVal = false;
					userLeave.leave(activParticipant, node.port_number);
					break;
				}
				else
				{
						  String message_To_Send = "username: " + inputVal + ", message: " + inputLine;
						  activParticipant.sendMessage(message_To_Send);

				}
			}
		}
		catch(Exception ex)
		{
			System.out.println("Invalid!");
		}
		
	}

}
