import org.junit.Test ;
import static org.junit.Assert.* ;

public class TokenTest
{
	@Test
	public void testGroupHtml() {
		PlainText p = new PlainText("<table>");

		assertTrue(p.getItem().equals("<table>"));
	}
	@Test
	public void testGroupHtml2() {
		PlainText p = new PlainText("</table>");

		assertTrue(p.getItem().equals("</table>"));
	}
	@Test
	public void testGroupHtml3() {
		PlainText p = new PlainText("<tr>");

		assertTrue(p.getItem().equals("<tr>"));
	}
	@Test
	public void testGroupHtml4() {
		PlainText p = new PlainText("<td>");

		assertTrue(p.getItem().equals("<td>"));
	}
	@Test
	public void testGroupHtml5() {
		PlainText p = new PlainText("</td>");

		assertTrue(p.getItem().equals("</td>"));
	}
	@Test
	public void testGroupHtml6() {
		PlainText p = new PlainText("<a>");

		assertTrue(p.getItem().equals("<a>"));
	}
	@Test
	public void testGroupHtml7() {
		PlainText p = new PlainText("<img>");

		assertTrue(p.getItem().equals("<img>"));
	}
	@Test
	public void testGroupHtml8() {
		PlainText p = new PlainText("</tr>");

		assertTrue(p.getItem().equals("</tr>"));
	}
	@Test
	public void testGroupHtml9() {
		PlainText p = new PlainText("<br>");

		assertTrue(p.getItem().equals("<br>"));
	}



	@Test
	public void testGroupEscape() {
		PlainText p = new PlainText(">");

		assertTrue(p.getItem().equals("&gt;"));
	}
	@Test
	public void testGroupEscape2() {
		PlainText p = new PlainText("<");

		assertTrue(p.getItem().equals("&lt;"));
	}
	@Test
	public void testGroupEscape3() {
		PlainText p = new PlainText("&");

		assertTrue(p.getItem().equals("&amp;"));
	}	


	@Test
	public void testGroupConvert() {
		PlainText p = new PlainText("\\\\");

		assertTrue(p.getItem().equals("\\"));
	}
	@Test
	public void testGroupConvert2() {
		PlainText p = new PlainText("\\#");

		assertTrue(p.getItem().equals("#"));
	}
	@Test
	public void testGroupConvert3() {
		PlainText p = new PlainText("\\'");

		assertTrue(p.getItem().equals("'"));
	}
	@Test
	public void testGroupConvert4() {
		PlainText p = new PlainText("\\{");

		assertTrue(p.getItem().equals("{"));
	}
	@Test
	public void testGroupConvert5() {
		PlainText p = new PlainText("\\}");

		assertTrue(p.getItem().equals("}"));
	}
	@Test
	public void testGroupConvert6() {
		PlainText p = new PlainText("\\[");

		assertTrue(p.getItem().equals("["));
	}
	@Test
	public void testGroupConvert7() {
		PlainText p = new PlainText("\\]");

		assertTrue(p.getItem().equals("]"));
	}
	@Test
	public void testGroupConvert8() {
		PlainText p = new PlainText("\\(");

		assertTrue(p.getItem().equals("("));
	}
	@Test
	public void testGroupConvert9() {
		PlainText p = new PlainText("\\)");

		assertTrue(p.getItem().equals(")"));
	}
	@Test
	public void testGroupConvert10() {
		PlainText p = new PlainText("\\.");

		assertTrue(p.getItem().equals("."));
	}
	@Test
	public void testGroupConvert11() {
		PlainText p = new PlainText("\\!");

		assertTrue(p.getItem().equals("!"));
	}
	@Test
	public void testGroupConvert12() {
		PlainText p = new PlainText("\\*");

		assertTrue(p.getItem().equals("*"));
	}
	@Test
	public void testGroupConvert13() {
		PlainText p = new PlainText("\\_");

		assertTrue(p.getItem().equals("_"));
	}


	@Test
	public void testGroupStyle3() {
		PlainText p = new PlainText("_text_");

		assertTrue(p.getItem().equals("<em>text</em>"));
	}
	@Test
	public void testGroupStyle4() {
		PlainText p = new PlainText("__text__");

		assertTrue(p.getItem().equals("<strong>text</strong>"));
	}



	@Test
	public void testGroupUrl() {
		PlainText p = new PlainText("<http://url>");

		assertTrue(p.getItem().equals("<a href=\"http://url\">http://url</a>"));
	}
	@Test
	public void testGroupUrl2() {
		PlainText p = new PlainText("<http://url");
		
		assertTrue(p.getItem().equals("<http://url"));
	}
	@Test
	public void testGroupUrl3() {
		PlainText p = new PlainText("<https://url");

		assertTrue(p.getItem().equals("<https://url"));
	}
	@Test
	public void testGroupUrl4() {
		PlainText p = new PlainText("<https://url>");

		assertTrue(p.getItem().equals("<a href=\"https://url\">https://url</a>"));
	}


}