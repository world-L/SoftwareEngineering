import java.io.*;
import java.util.ArrayList;
import org.w3c.tidy.*;


//Transform class that write Html file with Jtidy

public class Transform{

	public Transform(){

		
	}
		
	public void createFile(CommandFormat format, int index, ArrayList<String> html){
         
        String fileName = format.getOutputIndex(index);

        if(fileName.lastIndexOf("/")!= -1){   //designate the position of the output file
            fileName = fileName.substring(fileName.lastIndexOf("/")+1,fileName.length());
            fileName = "../dist/" + fileName;        
        }
         
         int i=1;
         
        try{
             
            File file = new File(fileName) ;

            while(true){ // check the exist file and add .html + i-th number to fileName
            	if (file.exists()){
                    
      				fileName = fileName.substring(0,format.getOutputIndex(index).length()-5) + "-" + i + ".html";
      				file = new File(fileName);
     				
     				i++;
     				
     			}
     			else
     				break;
             }
   
            FileWriter fw = new FileWriter(file, false) ;
            
            fw.write("<html>\n<title></title>\n<body>");    //beginning of the html format
            fw.flush();

            for(String line : html){    //write html code line by line
                
                fw.write(line + "\n");
                fw.flush();
            }

            fw.write("</body>\n</html>");   //end of the html format
            fw.flush();
            

            fw.close();

            // start make well formed html using Jtidy
            FileInputStream is;
            is = new FileInputStream(fileName);

            Tidy tidy1 = new Tidy();

            ByteArrayOutputStream out1 = new ByteArrayOutputStream(1024); 
            tidy1.parse(is, out1); 
         
            FileOutputStream fos1 = new FileOutputStream(new File(fileName));
            out1.writeTo(fos1);

          
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }


	}
	
}



          
