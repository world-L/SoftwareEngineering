import java.util.ArrayList;

public class CommandFormat{

	private ArrayList<String> input;
	private ArrayList<String> output; 
	private ArrayList<Integer> style;
	private int numOfFile;

	public CommandFormat(){
		input = new ArrayList<String>();
		output = new ArrayList<String>();
		style = new ArrayList<Integer>();
		numOfFile = 0;
	} 

	public void setInput(String input){
		this.input.add(numOfFile,input);
	}
	public void setOutput(String output){
		this.output.add(numOfFile,output);
	}
	public void setStyle(int style){
		this.style.add(numOfFile,style);
	}
	public void addNumOfFile(){
		this.numOfFile++;
	}

	
	public String getInput(){
		return this.input.get(numOfFile);
	}
	public String getOutput(){
		return this.output.get(numOfFile);
	}
	public int getStyle(){
		return this.style.get(numOfFile);
	}	
	public int getnumOfFile(){
		return this.numOfFile;
	}

	public String getInputIndex(int index){
		return this.input.get(index);
	}
	public String getOutputIndex(int index){
		return this.output.get(index);
	}
	public int getStyleIndex(int index){
		return this.style.get(index);
	}	

}