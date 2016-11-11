import java.io.*;

public class Transform{

	public void Transform(){


		
	}
		
	public void createFile(Format format, int index){
		String txt = "Test file" ;
         
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
             
            fw.write(txt);
            fw.flush();
 
            fw.close(); 
             
             
        }catch(Exception e){
            e.printStackTrace();
        }


	}
	
}