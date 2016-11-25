
public class Horizon extends Node {
	private String head;
	private String tail;
	
	public Horizon(){}
	
	public void setHtml(){
		String html = new String();

		html = this.head;
		for(Node inNode : this.otherNode)
        	html = html + inNode.getHtml();    
      	
      	for(Tokens token : this.token)
        	html = html + token.getItem();    
      	
		html = html + this.tail;

		super.html = html;

	}
	public void setHead(String head){
		this.head = head;
	}
}
