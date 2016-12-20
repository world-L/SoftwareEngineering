import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//PlainVisitor class that actually read, parse file and generate html code as plain style

public class FancyVisitor implements MDElementVisitor {
  public FancyVisitor() {
  }

  public int tabCount(String string) {
    string.replace("    ", "\t");
    
    int tabcount = 0;
    for (int i = 0; i < string.length(); i++) {
      string = string.substring(i);

      if (string.startsWith("\t"))
        tabcount++;
      else
        break;
    }
    return tabcount;
  }

  public String cuttingFront(String string) {
    for (int i = 0; i < string.length(); i++) {
      string = string.substring(i);

      if (string.startsWith(" "))
        continue;
      else if(string.startsWith("\t"))
        continue;
      else
        break;
    }
    return string;
  }
  public boolean showTagLater(String tag, int tCount){
    return true;
  }

  // the function that parse raw data of the node to token and set html code
  public void visitNode(Node node) {

    // parsing data to token
    while (true) {
      node.insertToken(new PlainText(node.getData()));
      break;
    }

    // check instance of the node and generate html code
    if (node instanceof Header) {
      Header header = (Header) node;

      header.setHead("<h" + header.getLevel() + " style = \"color: pink; font-family:'Raleway',sans-serif;font-weight: 800; line-height: 72px;  margin: 0 0 24px; text-align: center;text-transform: uppercase;\">");
      header.setTail("</h" + header.getLevel() + ">");
      header.setHtml();
    }if (node instanceof Horizon) {
      Horizon horizon = (Horizon) node;

      horizon.setHead("<hr/>");
      horizon.setHtml();
    }if (node instanceof ItemList) {
      ItemList itemlist = (ItemList) node;

      itemlist.setStarting("<ul style = \"color:#ff6600; margin-top:30px;\">");
      itemlist.setHead("<li style = \"margin-top:20px;\">");
      itemlist.setTail("</li>");
      itemlist.setEnding("</ul>");
      itemlist.setHtml();
    } if (node instanceof ItemListOrdered) {
      ItemListOrdered itemlist = (ItemListOrdered) node;

      itemlist.setStarting("<ol style = \"color:#00a3cc;\">");
      itemlist.setHead("<li style = \"margin-top:20px;\">");
      itemlist.setTail("</li>");
      itemlist.setEnding("</ol>");
      itemlist.setHtml();
    } if (node instanceof Block) {
      Block block = (Block) node;

      block.setStarting("<blockquote style = \" display:block;background: #ffff99;padding: 15px 20px 15px 45px; margin: 2em 10% 2em 10%;position: relative;font-family: Georgia, serif;font-size: 16px;line-height: 1.2;color: #666;text-align: justify; border-left: 15px solid #c76c0c; border-right: 2px solid #c76c0c; -moz-box-shadow: 2px 2px 15px #ccc;-webkit-box-shadow: 2px 2px 15px #ccc; box-shadow: 2px 2px 15px #ccc;\">");
      block.setHead("<p style = \"color:#BA55D3; margin-left:2em;\">");
      block.setTail("</p>");
      block.setEnding("</blockquote>");
      block.setHtml();
    } if(node instanceof EndingSet){
      EndingSet endingset = (EndingSet) node;
      endingset.setTag("</ul>");
      endingset.setHtml();
    } if(node instanceof Paragraph){
      Paragraph paragraph = (Paragraph) node;

      paragraph.setHead("<p style = \"color:#BA55D3; margin-left:2em;\">");
      paragraph.setTail("</p>");
      paragraph.setHtml();
    }

  }

  // the function that read file and parse to the node from the data
  public void visitDocument(Document document) {
    try {

      BufferedReader in = new BufferedReader(new FileReader(document.filename));
      String nextLine = in.readLine(); // Next line of 'first line'
      String firstLine;

      int unorderCount = 0;
      int orderCount = 0;
      int blockCount = 0;
      int tabCount = 0;
      int unTab = 0;

      do {
        firstLine = in.readLine();
        if (firstLine != null){
          while (cuttingFront(firstLine).equals(""))
            firstLine = in.readLine();
        }
        else if(firstLine == null)
          firstLine = new String(" ");

        /* line setting */
        String changeLine = new String(nextLine);
        nextLine = new String(firstLine);
        firstLine = new String(changeLine);

        while (true) { // iterate until string has no item
          Node newNode;

          if (firstLine.equals("") || firstLine.equals(" ") || firstLine.equals("\t")) {
            break;
          }
          
          
          if((tabCount(firstLine)<unTab) && (!cuttingFront(firstLine).startsWith("-") && !cuttingFront(firstLine).startsWith("+") && !cuttingFront(firstLine).startsWith("*") )){
            newNode = new EndingSet();
            newNode.setData(firstLine);
            document.insertNode(newNode);
            unTab = 0;
          }
            
          /* two 'if' statement is about header */
          if (cuttingFront(firstLine).startsWith("#")) {
            int i;
            for (i = 1; i < firstLine.length(); i++){
              if(i == 5)
                break;
              if (firstLine.charAt(i) != '#')
                break;
            }

            newNode = new Header(i);

            firstLine = cuttingFront(firstLine).substring(i);
            newNode.setData(firstLine);
            document.insertNode(newNode);
            if (tabCount(firstLine) < tabCount) {
              unorderCount = 0;
              blockCount = 0;
            }
            break;
          } if (nextLine.startsWith("===") || nextLine.startsWith("---")) {
            if (nextLine.startsWith("==="))
              newNode = new Header(1);
            else
              newNode = new Header(2);

            nextLine = in.readLine();

            newNode.setData(firstLine);
            document.insertNode(newNode);
            if (tabCount(firstLine) < tabCount) {
              unorderCount = 0;
              blockCount = 0;
            }
            break;

            /* blockquote statement */
          } else if (cuttingFront(firstLine).startsWith(">")) {
            boolean showstarting = false, showEnding = false;
            int temp1, temp2 = 0;

            temp1 = tabCount(firstLine) + 1;
            if (cuttingFront(nextLine).startsWith(">"))
              temp2 = tabCount(nextLine) + 1;
            else
              temp2 = tabCount(nextLine);
            if (blockCount < temp1){
              showstarting = true;
              blockCount = temp1;
            }
            if (temp1 > temp2){
              showEnding = true;
              blockCount = 0;
            }

            newNode = new Block(showstarting, showEnding);

            firstLine = cuttingFront(firstLine).substring(1);
            newNode.setData(firstLine);
            document.insertNode(newNode);

            tabCount = tabCount(firstLine);
            break;

          } else if (firstLine.startsWith("***") || firstLine.startsWith("* * *")
              || firstLine.startsWith("- - -") || firstLine.startsWith("---")) {
            newNode = new Horizon();

            newNode.setData(firstLine);
            document.insertNode(newNode);
            if (tabCount(firstLine) < tabCount) {
              unorderCount = 0;
              blockCount = 0;
            }
            break;

          } else if (cuttingFront(firstLine).startsWith("*") || cuttingFront(firstLine).startsWith("-")
              || cuttingFront(firstLine).startsWith("+")) {
            boolean showstarting = false, showEnding = false;
            int temp1, temp2 = 0;
            // show <li>...</li>, compare with next line

            temp1 = tabCount(firstLine) + 1;

            if (nextLine.startsWith("---") || nextLine.startsWith("***") || nextLine.startsWith("* * *")
                || nextLine.startsWith("- - -"))
              temp2 = 0;
            else if (cuttingFront(nextLine).startsWith("*") || cuttingFront(nextLine).startsWith("-")
                || cuttingFront(nextLine).startsWith("+"))
              temp2 = tabCount(nextLine) + 1;
            else
              temp2 = tabCount(nextLine);

            if (unorderCount < temp1){
              showstarting = true;
              unorderCount = temp1;
            }
            if (temp1 > temp2){
              showEnding = true;
              unorderCount = 0;
            }
            if(temp1 == temp2 && tabCount(firstLine)<tabCount(nextLine)){
              EndingSet endingset = new EndingSet();
              endingset.setTag("</ul>");
              unTab = tabCount(nextLine);
            }

            newNode = new ItemList(showstarting, showEnding);
            firstLine = cuttingFront(firstLine).substring(1);
            newNode.setData(firstLine);
            document.insertNode(newNode);

            if (tabCount(firstLine) < tabCount) {
              blockCount = 0;
            }
            
            tabCount = tabCount(firstLine);
            break;
          } else if ((cuttingFront(firstLine).charAt(0) >= '0') && (cuttingFront(firstLine).charAt(0) <= '9')
              && (cuttingFront(firstLine).charAt(1) == '.')) {
            boolean showstarting = false, showEnding = false;
            int temp1, temp2 = 0;

            temp1 = tabCount(firstLine) + 1;
            if (nextLine.length() >= 2) {
              if ((cuttingFront(nextLine).charAt(0) >= '0') && (cuttingFront(nextLine).charAt(0) <= '9')
                  && cuttingFront(nextLine).charAt(1) == '.')
                temp2 = tabCount(nextLine) + 1;
              else
                temp2 = tabCount(nextLine);
            }
            if (orderCount < temp1){
              showstarting = true;
              orderCount = temp1;
            }
            if (temp1 > temp2){
              showEnding = true;
              orderCount = 0;
            }
            newNode = new ItemListOrdered(showstarting, showEnding);
            firstLine = cuttingFront(firstLine).substring(2);
            newNode.setData(firstLine);
            document.insertNode(newNode);

            tabCount = tabCount(firstLine);
            
            if (tabCount(firstLine) < tabCount) {
              unorderCount = 0;
              blockCount = 0;
            }
            break;
          } else { // the string has nothing, set Block node
            newNode = new Paragraph();

            newNode.setData(cuttingFront(firstLine));
            document.insertNode(newNode);

            break;
          }
        }
      } while (!nextLine.equals(" "));
      if(nextLine.equals(" ")){
        Node newNode = new EndingSet();
        newNode.setData(firstLine);
        document.insertNode(newNode);
        unTab = 0;
      }
      in.close();
    } catch (IOException e) {
      System.err.println(e);
      return;
    }

  }
}