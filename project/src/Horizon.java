
public class Horizon extends Node {
	private String head;
	
	public Horizon(){}
	
	public void setHtml(){
		String html = new String();

		html = this.head;

		super.html = html;

	}
	public void setHead(String head){
		this.head = head;
	}
}
