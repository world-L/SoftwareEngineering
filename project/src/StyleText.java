// StyleText class that contain style text
// the text in this class should be handling escape character
// this class contains style format like <em> <strong> ...


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
