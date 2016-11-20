public class StyleText extends Tokens{

	private String text;

	public StyleText(String text){
		this.text = text;	
	}
		
	public void accept(MDElementVisitor visitor){}

	public String getItem(){
		return this.text;
	}

}
}