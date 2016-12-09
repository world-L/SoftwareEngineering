import org.junit.Test ;
import static org.junit.Assert.* ;

public class NodeTest
{
	@Test
	public void testHeader() {
		Header p = new Header(1) ;
		Tokens t = new Tokens();
		String s = "test\t#";

		t.setItem(s);
		p.insertToken(t);
		
		p.setHead("<h1>");
		p.setTail("</h1>");
		p.setHtml();

		
		assertTrue(p.getHtml().equals("<h1>test\t</h1>")) ;
	}
	@Test
	public void testHeader2() {
		Header p = new Header(1) ;
		Tokens t = new Tokens();
		String s = "test ####";

		t.setItem(s);
		p.insertToken(t);
		
		p.setHead("<h1>");
		p.setTail("</h1>");
		p.setHtml();

		assertTrue(p.getHtml().equals("<h1>test ###</h1>")) ;
	}
	@Test
	public void testHeader3() {
		Header p = new Header(1) ;
		Tokens t = new Tokens();
		String s = "";

		t.setItem(s);
		p.insertToken(t);
		
		p.setHead("<h1>");
		p.setTail("</h1>");
		p.setHtml();


		assertTrue(p.getHtml().equals("<h1></h1>")) ;
	}
	@Test
	public void testHeader4() {
		Header p = new Header(1) ;
		Tokens t = new Tokens();
		String s = "test##";

		t.setItem(s);
		p.insertToken(t);
		
		p.setHead("<h1>");
		p.setTail("</h1>");
		p.setHtml();

		//System.out.println(p.getHtml());
		assertTrue(p.getHtml().equals("<h1>test#</h1>")) ;
	}


}