import java.util.ArrayList;

// Node class that contain other node object, tokens object, raw data and html code\
// In this class, html code would not be made. Each child class make html code


public class Node implements MDElement{

	protected ArrayList<Node> otherNode;
	protected ArrayList<Tokens> token;
	private String data;
	protected String html;

	public Node(){
		otherNode = new ArrayList<Node>();
		token = new ArrayList<Tokens>();
	}
	
	// after call accept function this object conatain token list and html code
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

	public String getData(){	//after call this function this object cannot have raw data
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