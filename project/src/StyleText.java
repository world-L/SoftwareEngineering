// StyleText class that contain style text
// the text in this class should be handling escape character
// this class contains style format like <em> <strong> ...


public class StyleText extends Tokens{

	
	

	public StyleText(String text){
		super.text = text;
		//handle(text);
	}
		
	public void accept(MDElementVisitor visitor){}
	/*
	public void handle(String text){
		if(text.contains("file")){
			String newText=text.replace("file","elif");
			setItem(newText);
		}
		if(text.contains("&")){
			String newText=text.replace("&","Goooooooood");
			setItem(newText);
		}
	}*/

}
