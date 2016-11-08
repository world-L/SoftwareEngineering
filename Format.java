public class Format{

	private String input;
	private String output; 
	private int style;

	public Format(){

	} 

	public void setInput(String input){
		this.input = input;
	}
	public void setOutput(String output){
		this.output = output;
	}
	public void setStyle(int style){
		this.style = style;
	}
	
	public String getInput(){
		return this.input;
	}
	public String getOutput(){
		return this.output;
	}
	public int getStyle(){
		return this.style;
	}	

}