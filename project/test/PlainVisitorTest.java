import org.junit.Test ;
import static org.junit.Assert.* ;

public class PlainVisitorTest
{

	@Test
	public void testCuttingFront() {
		PlainVisitor p = new PlainVisitor() ;
		String s = "\ttest";
		assertTrue(p.cuttingFront(s).equals("test"));
	}

		
	@Test
	public void testVisitNodeHeader() {
		PlainVisitor p = new PlainVisitor() ;
		Node node = new Header(1);
		node.setData("data");
		p.visitNode(node);
		assertTrue(node.getHtml().equals("<h1>data</h1>"));
	}
	@Test
	public void testVisitNodeHorizon() {
		PlainVisitor p = new PlainVisitor() ;
		Node node = new Horizon();
		node.setData("data");
		p.visitNode(node);
		assertTrue(node.getHtml().equals("<hr/>"));
	}
	@Test
	public void testVisitNodeItemList() {
		PlainVisitor p = new PlainVisitor() ;
		Node node = new ItemList(true,true);
		node.setData("data");
		p.visitNode(node);
		assertTrue(node.getHtml().equals("<ul><li>data</li></ul>"));
	}	
	@Test
	public void testVisitNodeItemListOrdered() {
		PlainVisitor p = new PlainVisitor() ;
		Node node = new ItemListOrdered(true,true);
		node.setData("data");
		p.visitNode(node);
		assertTrue(node.getHtml().equals("<ol><li>data</li></ol>"));
	}	
	@Test
	public void testVisitNodeBlock() {
		PlainVisitor p = new PlainVisitor() ;
		Node node = new Block(true,true);
		node.setData("data");
		p.visitNode(node);
		assertTrue(node.getHtml().equals("<blockquote><p>data</p></blockquote>"));
	}
	@Test
	public void testVisitNodeParagraph() {
		PlainVisitor p = new PlainVisitor() ;
		Node node = new Paragraph();
		node.setData("data");
		p.visitNode(node);
		assertTrue(node.getHtml().equals("<p>data</p>"));
	}





	@Test
	public void testVisitDocument() {
		PlainVisitor p = new PlainVisitor() ;
		Document document = new Document("./document/test.md");
		p.visitDocument(document);
		//assertTrue(node.getHtml().equals("<blockquote><p>null</p></blockquote>"));
	}

}

