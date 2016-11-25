import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//PlainVisitor class that actually read, parse file and generate html code as plain style

public class PlainVisitor implements MDElementVisitor{

	public PlainVisitor(){}
	
  //the function that parse raw data of the node to token and set html code
	public void visitNode(Node node){

    //parsing data to token
    while(true){
      node.insertToken(new PlainText(node.getData()));
      break;
    }

    // apply accept function to nested Node list
    for(Node nestedNode : node.getNodeList())
          nestedNode.accept(this);

    //check instance of the node and generate html code    
    if(node instanceof Header){
      Header header = (Header)node;

      header.setHead("<h"+header.getLevel()+">");
      header.setTail("</h"+header.getLevel()+">");
      header.setHtml();
    }else if(node instanceof Horizon){
    	Horizon horizon = (Horizon)node;
    	
    	horizon.setHead("<hr/>");	
    	horizon.setHtml();
	}else if(node instanceof ItemList){
		ItemList itemlist = (ItemList)node;
		
		itemlist.setHead("<li>");
		itemlist.setTail("</li>");
		itemlist.setHtml();
	}else if(node instanceof Block){
      Block block = (Block)node;

      block.setHead("<p>");
      block.setTail("</p>");

      block.setHtml();  
  }else{
     Block block = (Block)node;

      block.setHead("<p>");
      block.setTail("</p>");

      block.setHtml(); 
  }

       

  }

  //the function that read file and parse to the node from the data
	public void visitDocument(Document document){
      try {
      
          BufferedReader in = new BufferedReader(new FileReader(document.filename));
          String s;
          int nested = 0;
          int count = 0;

          while ((s = in.readLine()) != null) { //read file until detect the EOF

              nested = 0;

              while(true){  // iterate until string has no item
              
              Node newNode;

              if(s.startsWith("#")){  //check the string contain header item
                  int i;
                  for(i = 1;i<s.length();i++)
                    if(s.charAt(i) != '#')break;

                  newNode = new Header(i);
                  nested++;

                  s = s.substring(i,s.length());    
                  newNode.setData(s); 
                  document.insertNode(newNode);
                  break;       
              }/*
              else if(s.startsWith(">")){
                newNode = new Block();

                newNode.setData(s);
                document.insertNode(newNode);
              }
              else if(s.startsWith("---")){
                newNode = new Horizon();
                newNode.setData(s);
                document.insertNode(newNode);
              }
              else if(s.startsWith("* ")){
                newNode = new ItemList();
                newNode.setData(s);
                document.insertNode(newNode);
              }*/
              else{  //the string has nothing, set Block node

                  newNode = new Block();
                  
                  newNode.setData(s);
                  document.insertNode(newNode);

                  break;
              }
            }            
          }
          in.close();
        }catch (IOException e) {
          System.err.println(e); 
          System.exit(1);
      } 


	}
}