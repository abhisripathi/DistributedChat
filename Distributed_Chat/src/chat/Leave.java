package chat;

public class Leave {
	
	void leave(ActiveParticipant activParticipant, int portNumber)
	{
		boolean entryValue = true;
		
		/*
		 * while(entryValue) {
		 * 
		 * for(ActiveNodeWorker accNode : activeparticipant.getthreads()); {
		 * if(accNode.activParticipant.portNumbers == portNumber) {
		 * System.out.println("node having port number:"+accNode.activParticipant.
		 * portNumbers+"is left"); entryValue = false; break; } } }
		 */
		
		
		while (entryValue) {
			  for (ActiveNodeWorker accNode : activParticipant.getthreads()) {
			    if (accNode.activParticipant.portNumbers == portNumber) {
			      System.out.println("Node with port number " + accNode.activParticipant.portNumbers + " is removed");
			      entryValue = false;
			      break;
			    }
			  }
			}
			
	}
}
