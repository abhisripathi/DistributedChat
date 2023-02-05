package chat;

public class NodeInfo {

	public NodeInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public NodeInfo(String logicalName, int portNumber) {
		// TODO Auto-generated constructor stub
		this.logialName = logicalName;
		this.port_number = portNumber;
	}

	int port_number;
	int have_Successor;
	int have_Predecessor;
	String logialName;
	
}
