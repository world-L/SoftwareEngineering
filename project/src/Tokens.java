public class Tokens implements MDElement{

	protected String text;

	public Tokens(){


		
	}
		
	public void accept(MDElementVisitor visitor){}

	public String getItem(){
		return this.text;
	}

}
