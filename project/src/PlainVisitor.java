import java.io.*;

public class PlainVisitor implements MDElementVisitor{

	public PlainVisitor(){


		
	}
		
	public void visitNode(Node node){

    //parsing data to token
    while(true){
      node.insertToken(new PlainText(node.getData()));
      break;
    }

    for(Node nestedNode : node.getNodeList())
          nestedNode.accept(this);

    if(node instanceof Header){
      Header header = (Header)node;

      header.setHead("<h"+header.getLevel()+">");
      header.setTail("</h"+header.getLevel()+">");

      header.setHtml();

    }else if(node instanceof Block){
      Block block = (Block)node;

      block.setHead("<p>");
      block.setTail("</p>");

      block.setHtml();
    }else{
System.out.println("n");
    }    

       

  }

	public void visitDocument(Document document){
      try {
      
          BufferedReader in = new BufferedReader(new FileReader(document.filename));
          String s;
          int nested = 0;
          int count = 0;

          while ((s = in.readLine()) != null) {

              nested = 0;

              while(true){
              
              Node newNode;

              if(s.startsWith("#")){
                  int i;
                  for(i = 1;i<s.length();i++)
                    if(s.charAt(i) != '#')break;

                  newNode = new Header(i);
                  nested++;

                  s = s.substring(i,s.length());    
                  newNode.setData(s); 
                  document.insertNode(newNode);
                  break;       
              }else{

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