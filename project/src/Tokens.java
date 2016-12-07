// Tokens class that contain text information

public class Tokens implements MDElement{

	protected String text;

	//public StyleText style;

	public Tokens(){
			
	}
	
	//this function dose nothing yet
	public void accept(MDElementVisitor visitor){

	}

	public String getItem(){
		return this.text;
	}
	public void setItem(String text){
		this.text=text;
	}

}
