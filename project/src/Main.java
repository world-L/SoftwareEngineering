import java.util.ArrayList;

// Main Class that Read MD file and convert to html 

public class Main{

	public static void main(String[] args){

		CommandCheck check = new CommandCheck();			
		CommandFormat format = new CommandFormat();
		MDElementVisitor visitor;
		MDElement element;
		ArrayList<String> html = new ArrayList<String>();

		format = check.receive(args);//check command input and return format				

		if(format.getnumOfFile() == 0) return;

		for(int i = 0;i<format.getnumOfFile();i++){ 

			if(format.getStyleIndex(i) == 1)	
				visitor = new PlainVisitor();
			else if(format.getStyleIndex(i) == 2)
				visitor = new FancyVisitor();	
			else 
				visitor = new SlideVisitor();	//assign visitor

			element = new Document(format.getInputIndex(i));	

			((Document)element).accept(visitor);	//read input file and parsing document to node
			
			for(Node inNode : ((Document)element).getNodeList())	//parsing raw data to token and generate html
				inNode.accept(visitor);					
						
	
			for(Node inNode : ((Document)element).getNodeList())	//add html code from node
				html.add(inNode.getHtml());
			

			Transform transform = new Transform();	//write file to html
			transform.createFile(format,i,html);
		}
			
		return;
	}
		

}