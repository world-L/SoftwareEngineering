import java.io.*;

public class DocumentParser{

	public void DocumentParser(){

		

		
	}
		

	public void readFile(Format format, int index){
		try {
      
      	BufferedReader in = new BufferedReader(new FileReader(format.getInputIndex(index)));
      	String s;

      	while ((s = in.readLine()) != null) {
        	//System.out.println(s);
      	}
    	  in.close();
       	}catch (IOException e) {
        	System.err.println(e); 
        	System.exit(1);
    	}



	}

}