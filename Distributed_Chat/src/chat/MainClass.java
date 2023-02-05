package chat;

//import statements
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {

	private static final String JOIN = null;
	private static final String NOTE = null;
	private static final String LEAVE = null;

	public MainClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Scanner userInput = new Scanner(System.in);
		
		String message = "";
		String inputLine=null;
		
		ActiveParticipant activParticipant;
		
		//object creation for classes JOIN,LEAVE,NODEINFO..
		Join userJoin = new Join();
		Leave userLeave = new Leave();
		NodeInfo node = new NodeInfo();
		MessageNote msgNote = new MessageNote();
		
		//take user input
		System.out.println("Enter the Request:");
		//inputValue = scan.next().charAt(0);
		
		
		//checking the requests
		
		while(true) {
			//read user input request
			inputLine = userInput.nextLine();
			
			//check start for keywords in messageTypes
			/*if JOIN check if already connected to chat if not, get 
			  connectivity info from user and send a join request to server */
			
			if(inputLine.startsWith("JOIN")){
				
				/*
				 * if(userJoin.has_Joined == 0) {
				 * System.err.println("You have already joined the chat"); }
				 */
					
				BufferedReader bufferedRead = new BufferedReader(new InputStreamReader(System.in));
				node.logialName = bufferedRead.readLine();
				node.port_number = Integer.parseInt(bufferedRead.readLine());
				
				activParticipant = new ActiveParticipant(node.port_number);
				activParticipant.start();
				
				node.have_Predecessor = 0;
				node.have_Successor = 0;
				
				// name & port
				String inputVal = bufferedRead.readLine();
				
				//Talk To Peers method call
				userJoin.talk_to_peers(activParticipant, node, inputLine, inputVal, bufferedRead);
				
				if(userJoin.has_Joined == 1)
				{
					System.out.println("Chat Joined Successfully!");
					inputVal = userInput.next();
					if(inputVal == NOTE)
					{
						//Transfer Messages Method Call
						msgNote.transferMessages(inputLine, inputVal, bufferedRead, activParticipant, node);						
					}
					else if(inputVal == LEAVE) {
						// User Leave Method Call
						userLeave.leave(activParticipant, node.port_number);
					}
				}
				else if(inputLine.startsWith("NOTE"))
					System.out.println("Join the chat first!");
				else if(inputLine.startsWith("LEAVE"))
					System.out.println("Join the chat to LEAVE!");
				else {
					System.out.println("INVALID!");
					System.exit(0);
				}
			}
			userInput.close();
		}
	}

	public String myMessage(BufferedReader bufferedRead) throws IOException {
		// TODO Auto-generated method stub
		String message = bufferedRead.readLine();
		return message;
	}

}
