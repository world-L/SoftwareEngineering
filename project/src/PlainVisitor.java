import java.awt.SecondaryLoop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//PlainVisitor class that actually read, parse file and generate html code as plain style

public class PlainVisitor implements MDElementVisitor {
	public PlainVisitor() {
	}

	public int tabCount(String string) {
		int tabcount = 0;
		for (int i = 0; i < string.length(); i++) {
			string = string.substring(0);

			if (string.startsWith("\t") || string.startsWith("    "))
				tabcount++;
			else
				break;
		}
		return tabcount;
	}

	public String cuttingF(String string) {
		for (int i = 0; i < string.length(); i++) {
			string = string.substring(i);

			if (string.startsWith(" ") || string.startsWith("\t"))
				continue;
			else
				break;
		}
		return string;
	}

	public int cuttingFfont(String string, String special) {
		int real = 0;
		for (int i = 0; i < string.length(); i++) {
			string = string.substring(i);

			if (string.startsWith(" "))
				;
			else if (string.startsWith("\t"))
				real++;
			else if (string.startsWith(special)) {
				real++;
				break;
			} else
				break;
		}
		return real;
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
		} else if (node instanceof Horizon) {
			Horizon horizon = (Horizon) node;

			horizon.setHead("<hr/>");
			horizon.setHtml();
		} else if (node instanceof ItemList) {
			ItemList itemlist = (ItemList) node;

			itemlist.setStarting("<ul>");
			itemlist.setHead("<li>");
			itemlist.setTail("</li>");
			itemlist.setEnding("</ul>");
			itemlist.setHtml();
		} else if (node instanceof ItemListOrdered) {
			ItemListOrdered itemlist = (ItemListOrdered) node;

			itemlist.setStarting("<ol>");
			itemlist.setHead("<li>");
			itemlist.setTail("</li>");
			itemlist.setEnding("</ol>");
			itemlist.setHtml();
		} else if (node instanceof Block) {
			Block block = (Block) node;

			block.setStarting("<blockquote>");
			block.setHead("<p>");
			block.setTail("</p>");
			block.setEnding("</blockquote>");
			block.setHtml();
		} else {
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
			String firstLine;
			String nextLine = in.readLine(); // Next line of 'first line'

			int unorderCount = 0;
			int orderCount = 0;
			int blockCount = 0;
			System.out.println("sssssssssss");

			while ((firstLine = in.readLine()) != null) {
				while (firstLine.equals("") || firstLine.equals(" ") || firstLine.equals("\t"))
					firstLine = in.readLine();
				/* line setting */
				String changeLine = new String(nextLine);
				nextLine = new String(firstLine);
				firstLine = new String(changeLine);

				while (true) { // iterate until string has no item
					Node newNode;

					if (firstLine.equals("") || firstLine.equals(" ") || firstLine.equals("\t")) {
						break;
					}
					/* two 'if' statement is about header */
					else if (firstLine.startsWith("#")) {
						int i;
						for (i = 1; i < firstLine.length(); i++)
							if (firstLine.charAt(i) != '#')
								break;

						newNode = new Header(i);

						firstLine = firstLine.substring(i, firstLine.length());
						newNode.setData(firstLine);
						document.insertNode(newNode);
						unorderCount = 0;
						break;
					} else if (nextLine.startsWith("===") || nextLine.startsWith("---")) {
						if (nextLine.startsWith("==="))
							newNode = new Header(1);
						else
							newNode = new Header(2);

						nextLine = in.readLine();

						newNode.setData(firstLine);
						document.insertNode(newNode);
						unorderCount = 0;
						break;

						/* blockquote statement */
					} else if (cuttingF(firstLine).startsWith(">")) {
						newNode = new Block();

						firstLine = cuttingF(firstLine).substring(1);
						newNode.setData(firstLine);
						document.insertNode(newNode);
						unorderCount = 0;
						break;

					} else if (firstLine.startsWith("***") || firstLine.startsWith("* * *")
							|| firstLine.startsWith("- - -")) {
						newNode = new Horizon();

						newNode.setData(firstLine);
						document.insertNode(newNode);
						unorderCount = 0;
						break;

					} else if ((cuttingFfont(firstLine, "* ") > 0) || (cuttingFfont(firstLine, "- ") > 0)
							|| (cuttingFfont(firstLine, "+ ") > 0)) {
						boolean showstarting = false, showEnding = false;

						// show <li>...</li>, compare with next line
						int arr[] = new int[6];

						arr[0] = cuttingFfont(firstLine, "* ");
						arr[1] = cuttingFfont(firstLine, "- ");
						arr[2] = cuttingFfont(firstLine, "+ ");

						if (nextLine.startsWith("---") || nextLine.startsWith("***") || nextLine.startsWith("* * *")
								|| nextLine.startsWith("- - -"))
							;
						else {
							arr[3] = cuttingFfont(nextLine, "* ");
							arr[4] = cuttingFfont(nextLine, "- ");
							arr[5] = cuttingFfont(nextLine, "+ ");
						}

						if (unorderCount < arr[0] + arr[1] + arr[2])
							showstarting = true;
						if (arr[0] + arr[1] + arr[2] > arr[3] + arr[4] + arr[5])
							showEnding = true;

						newNode = new ItemList(showstarting, showEnding);
						unorderCount = arr[0] + arr[1] + arr[2];
						firstLine = firstLine.substring(2, firstLine.length());

						newNode.setData(firstLine);
						document.insertNode(newNode);
						break;
					} else if ((firstLine.charAt(0) >= '0') && (firstLine.charAt(0) <= '9')
							&& (firstLine.charAt(1) == '.')) {
						boolean showstarting = true, showEnding = true;

						if (unorderCount != 0)
							showstarting = false;
						if ((tabCount(firstLine) <= tabCount(nextLine))
								|| ((nextLine.charAt(0) >= '0') && (nextLine.charAt(0) <= '9') && (nextLine.charAt(1) == '.'))) {
							System.out.println(firstLine);
							showEnding = false;
							unorderCount = 0;
						}

						newNode = new ItemListOrdered(showstarting, showEnding);

						firstLine = firstLine.substring(2, firstLine.length());

						newNode.setData(firstLine);
						document.insertNode(newNode);

						break;
					} else { // the string has nothing, set Block node
						newNode = new Paragraph();

						newNode.setData(firstLine);
						document.insertNode(newNode);

						break;
					}
				}
			}
			in.close();
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}

	}
}