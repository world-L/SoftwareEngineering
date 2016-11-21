// PlainText class that contain pure text
// the text in this class should be handling escape character

public class PlainText extends Tokens{

	

	public PlainText(String text){
		super.text = text;	
	}
		
	public void accept(MDElementVisitor visitor){}

}