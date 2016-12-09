import org.junit.Test ;
import static org.junit.Assert.* ;

public class EtcTest
{
	@Test
	public void testMain() {
		Main m = new Main();
		String s[] = new String[3];

		s[0] = "./document/test.md";
		s[1] = "-filename";
		s[2] = "output";

		m.main(s);

	}
	@Test
	public void testMain2() {
		Main m = new Main();
		String s[] = new String[1];

		s[0] = "-help";

		m.main(s);

	}
	@Test
	public void testMain3() {
		Main m = new Main();
		String s[] = new String[3];

		s[0] = "./document/test.md";
		s[1] = "-style";
		s[2] = "fancy";

		m.main(s);

	}
	@Test
	public void testMain4() {
		Main m = new Main();
		String s[] = new String[3];

		s[0] = "./document/test.md";
		s[1] = "-style";
		s[2] = "slide";

		m.main(s);

	}


	@Test
	public void testTokenEtc(){
		Tokens t = new Tokens();
		PlainVisitor p = new PlainVisitor();
		t.accept(p);
	}



}