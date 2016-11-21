import java.util.ArrayList;

// Document class that contain node list and file name

public class Document implements MDElement{

	private ArrayList<Node> node;
	public String filename;

	public Document(String filename){
		
		this.filename = filename;
		node = new ArrayList<Node>();
	}

	//after call accept function this object contain node list
	public void accept(MDElementVisitor visitor){
		visitor.visitDocument(this);
	}

	public void insertNode(Node newNode){
		this.node.add(newNode);
	}

	public ArrayList<Node> getNodeList(){
		return this.node;
	}
}		
