import java.awt.SecondaryLoop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

//PlainVisitor class that actually read, parse file and generate html code as plain style

public class SlideVisitor implements MDElementVisitor {
  public SlideVisitor() {
  }

  public int tabCount(String string) {
    int tabcount = 0;
    for (int i = 0; i < string.length(); i++) {
      string = string.substring(i);

      if (string.startsWith("\t") || string.startsWith("    "))
        tabcount++;
      else
        break;
    }
    return tabcount;
  }

  public String cuttingFront(String string) {
    for (int i = 0; i < string.length(); i++) {
      string = string.substring(i);

      if (string.startsWith(" ") || string.startsWith("\t"))
        continue;
      else
        break;
    }
    return string;
  }

  // the function that parse raw data of the node to token and set html code
  public void visitNode(Node node) {

    // parsing data to token
    while (true) {
      node.insertToken(new PlainText(node.getData()));
      break;
    }

    // apply accept function to nested Node list
    for (Node nestedNode : node.getNodeList())
      nestedNode.accept(this);

    // check instance of the node and generate html code
    if (node instanceof Header) {
      Header header = (Header) node;

      header.setHead("<h" + header.getLevel() + ">");
      header.setTail("</h" + header.getLevel() + ">");
      header.setHtml();
    }if (node instanceof Horizon) {
      Horizon horizon = (Horizon) node;

      horizon.setHead("<hr/>");
      horizon.setHtml();
    }if (node instanceof ItemList) {
      ItemList itemlist = (ItemList) node;

      itemlist.setStarting("<ul>");
      itemlist.setHead("<li>");
      itemlist.setTail("</li>");
      itemlist.setEnding("</ul>");
      itemlist.setHtml();
    } if (node instanceof ItemListOrdered) {
      ItemListOrdered itemlist = (ItemListOrdered) node;

      itemlist.setStarting("<ol>");
      itemlist.setHead("<li>");
      itemlist.setTail("</li>");
      itemlist.setEnding("</ol>");
      itemlist.setHtml();
    } if (node instanceof Block) {
      Block block = (Block) node;

      block.setStarting("<blockquote>");
      block.setHead("<p>");
      block.setTail("</p>");
      block.setEnding("</blockquote>");
      block.setHtml();
    } if(node instanceof Paragraph){
      Paragraph paragraph = (Paragraph) node;

      paragraph.setHead("<p>");
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

      do {
        firstLine = in.readLine();
        if (firstLine != null){
          while (firstLine.equals("") || firstLine.equals("\t") || firstLine.equals(" "))
            firstLine = in.readLine();
        }
        else if(firstLine == null)
          firstLine = new String(" ");
        
        

        /* line setting */
        String changeLine = new String(nextLine);
        nextLine = new String(firstLine);
        firstLine = new String(changeLine);

        System.out.println(firstLine);
        while (true) { // iterate until string has no item
          Node newNode;

          if (firstLine.equals("") || firstLine.equals(" ") || firstLine.equals("\t")) {
            break;
          }
          /* two 'if' statement is about header */
          else if (cuttingFront(firstLine).startsWith("#")) {
            int i;
            for (i = 1; i < firstLine.length(); i++)
              if (firstLine.charAt(i) != '#')
                break;

            newNode = new Header(i);

            firstLine = firstLine.substring(i, firstLine.length());
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

            if (blockCount < temp1)
              showstarting = true;
            if (temp1 > temp2)
              showEnding = true;

            newNode = new Block(showstarting, showEnding);

            firstLine = cuttingFront(firstLine).substring(1);
            newNode.setData(firstLine);
            document.insertNode(newNode);

            if (tabCount(firstLine) < tabCount)
              unorderCount = 0;

            blockCount = temp1;
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

            if (unorderCount < temp1)
              showstarting = true;
            if (temp1 > temp2)
              showEnding = true;

            newNode = new ItemList(showstarting, showEnding);
            firstLine = firstLine.substring(2, firstLine.length());
            newNode.setData(firstLine);
            document.insertNode(newNode);

            unorderCount = temp1;
            tabCount = tabCount(firstLine);
            break;
          } else if ((firstLine.charAt(0) >= '0') && (firstLine.charAt(0) <= '9')
              && (firstLine.charAt(1) == '.')) {
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
            if (orderCount < temp1)
              showstarting = true;
            if (temp1 > temp2)
              showEnding = true;

            newNode = new ItemListOrdered(showstarting, showEnding);
            firstLine = firstLine.substring(2, firstLine.length());
            newNode.setData(firstLine);
            document.insertNode(newNode);

            orderCount = temp1;
            tabCount = tabCount(firstLine);
            break;
          } else { // the string has nothing, set Block node
            newNode = new Paragraph();

            newNode.setData(firstLine);
            document.insertNode(newNode);

            break;
          }
        }
      } while (!nextLine.equals(" "));
      in.close();
    } catch (IOException e) {
      System.err.println(e);
      System.exit(1);
    }

  }
}