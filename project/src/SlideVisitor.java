import java.io.*;

//PlainVisitor class that actually read, parse file and generate html code as slide style

public class SlideVisitor implements MDElementVisitor{

	public SlideVisitor(){


		
	}
		
	public void visitNode(Node node){}

	public void visitDocument(Document document){
		try {
      
      		BufferedReader in = new BufferedReader(new FileReader(document.filename));
      		String s;

      		while ((s = in.readLine()) != null) {
        		System.out.println(s);
      		}
    	  	in.close();
       	}catch (IOException e) {
        	System.err.println(e); 
        	System.exit(1);
    	}	



	}
}	