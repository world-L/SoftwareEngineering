import java.io.*;


public class FancyVisitor implements MDElementVisitor{

	public FancyVisitor(){


		
	}
		
	public void visitNode(Node node){





  }

	public void visitDocument(Document document){
		try {
      
      		BufferedReader in = new BufferedReader(new FileReader(document.filename));
      		String s;

      		while ((s = in.readLine()) != null) {

            if(s.startsWith("#")){
                int i;
                for(i = 1;i<s.length();i++)
                  if(s.charAt(i) != '#')break;

                document.insertNode(new Header(i));
                continue;
            }
            if(s.startsWith(">")){
            	document.insertNode(new Block());
            }
            if(s.contentEquals("---")){
            	document.insertNode(new Horizon());
            }





        		System.out.println(s);
      		}
    	  	in.close();
       	}catch (IOException e) {
        	System.err.println(e); 
        	System.exit(1);
    	}	



	}
}