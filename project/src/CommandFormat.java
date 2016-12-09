import java.util.ArrayList;

//CommandFormat class that contain inputfile name, outputfile name and style format of user input

public class CommandFormat{

	// using arrayList for multiple file read

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

	// set variable
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

	//get variable
	public String getInput(){
		return this.input.get(numOfFile);
	}
	public int getnumOfFile(){
		return this.numOfFile;
	}

	//get variable as index
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