import java.util.ArrayList;


public class Main{

	public static void main(String[] args){

		CommandCheck check = new CommandCheck();
		CommandFormat format = new CommandFormat();
		MDElementVisitor visitor;
		MDElement element;
		ArrayList<String> html = new ArrayList<String>();

		format = check.receive(args);

		if(format.getnumOfFile() == 0) return;

		for(int i = 0;i<format.getnumOfFile();i++){

			if(format.getStyleIndex(i) == 1)	
				visitor = new PlainVisitor();
			else if(format.getStyleIndex(i) == 2)
				visitor = new FancyVisitor();	
			else 
				visitor = new SlideVisitor();

			element = new Document(format.getInputIndex(i));

			visitor.visitDocument((Document)element);
			
			for(Node inNode : ((Document)element).getNodeList())
				inNode.accept(visitor);
						
	
			for(Node inNode : ((Document)element).getNodeList())
				html.add(inNode.getHtml());
			

			Transform transfrom = new Transform();
			transfrom.createFile(format,i,html);
		}
			
		return;
	}
		

}