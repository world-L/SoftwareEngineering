//Block class that represent <p> tag

public class Paragraph extends Node{
	private String head;
	private String tail;

	public Paragraph(){}

	public void setHtml(){
		String html = new String();

		html = this.head;

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