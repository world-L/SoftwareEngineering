import java.util.ArrayList;

public class Document implements MDElement{

	private ArrayList<Node> node;
	public String filename;

	public Document(String filename){
		
		this.filename = filename;
		node = new ArrayList<Node>();
	}

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
