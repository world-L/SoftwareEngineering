import java.util.ArrayList;

public class Node implements MDElement{

	protected ArrayList<Node> otherNode;
	protected ArrayList<Tokens> token;
	private String data;
	protected String html;

	public Node(){
		otherNode = new ArrayList<Node>();
		token = new ArrayList<Tokens>();
	}
		
	public void accept(MDElementVisitor visitor){
		visitor.visitNode(this);
	}

	public String getHtml(){
		return this.html;
	}

	public void insertToken(Tokens token){
		this.token.add(token);
	}
 	
 	public void setData(String data){
 		this.data = data;
 	}
	public String getData(){
		String returnData = this.data;
		this.data = "";
		return returnData;
	}
	public ArrayList<Node> getNodeList(){
		return this.otherNode;
	}
	public void insertNode(Node node){
		this.otherNode.add(node);
	}

}