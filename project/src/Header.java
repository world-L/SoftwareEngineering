//Header class that represent <h#> tag

public class Header extends Node{

	private int level;
	private String head;
	private String tail;

	//header has level from 1 to 6
	public Header(int level){
		this.level = level;
	}

	public int getLevel(){
		return this.level;
	}

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

	public void setTail(String tail){
		this.tail = tail;
	}

	
}