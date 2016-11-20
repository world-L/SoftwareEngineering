import java.io.*;
import java.util.ArrayList;

public class Transform{

	public Transform(){

		
	}
		
	public void createFile(CommandFormat format, int index, ArrayList<String> html){
         
        String fileName = format.getOutputIndex(index);
         
         int i=1;
         
        try{
             
            File file = new File(fileName) ;

            while(true){
            	if (file.exists()){
     
      				fileName = fileName.substring(0,format.getOutputIndex(index).length()-5) + "-" + i + ".html";
      				file = new File(fileName);
     				
     				i++;
     				
     			}
     			else
     				break;
             }
   
            FileWriter fw = new FileWriter(file, false) ;
            
            fw.write("<html>\n<title></title>\n<body>");
            fw.flush();

            for(String line : html){
                
                fw.write(line + "\n");
                fw.flush();
            }

            fw.write("</body>\n</html>");
            fw.flush();
            

            fw.close();              
             
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }


	}
	
}