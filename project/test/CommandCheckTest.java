import org.junit.Test ;
import static org.junit.Assert.* ;

public class CommandCheckTest
{
	@Test
	public void testHelp() {
		CommandCheck c = new CommandCheck() ;
		String s[] = new String[1];
		s[0] = "-help";
		assertTrue(c.receive(s) == null) ;
	}
	@Test
	public void testCommand() {
		CommandCheck c = new CommandCheck() ;
		String s[] = new String[1];

		s[0] = "README.md";

		assertTrue(c.receive(s).getInputIndex(0).equals(s[0])&&c.receive(s).getOutputIndex(0).equals("README.html")&&c.receive(s).getStyleIndex(0)==1);
	}
	@Test
	public void testCommandOutput() {
		CommandCheck c = new CommandCheck() ;
		String s[] = new String[3];

		s[0] = "README.md";
		s[1] = "-filename";
		s[2] = "output";

		assertTrue(c.receive(s).getInputIndex(0).equals(s[0])&&c.receive(s).getOutputIndex(0).equals(s[2]+".html")&&c.receive(s).getStyleIndex(0)==1);
	}
	@Test
	public void testCommandStyle() {
		CommandCheck c = new CommandCheck() ;
		String s[] = new String[3];

		s[0] = "README.md";
		s[1] = "-style";
		s[2] = "fancy";

		assertTrue(c.receive(s).getInputIndex(0).equals(s[0])&&c.receive(s).getOutputIndex(0).equals("README.html")&&c.receive(s).getStyleIndex(0)==2);
	}
	@Test
	public void testCommandOutputStyle() {
		CommandCheck c = new CommandCheck() ;
		String s[] = new String[5];

		s[0] = "README.md";
		s[1] = "-filename";
		s[2] = "output";
		s[3] = "-style";
		s[4] = "slide";

		assertTrue(c.receive(s).getInputIndex(0).equals(s[0])&&c.receive(s).getOutputIndex(0).equals(s[2]+".html")&&(c.receive(s).getStyleIndex(0)==3)) ;
	}
	@Test
	public void testCommandOutput2() {
		CommandCheck c = new CommandCheck() ;
		String s[] = new String[4];

		s[0] = "README.md";
		s[1] = "-filename";
		s[2] = "output";
		s[3] = "README.md";

		assertTrue(c.receive(s).getInputIndex(0).equals(s[0])&&c.receive(s).getOutputIndex(0).equals(s[2]+".html")&&(c.receive(s).getStyleIndex(0)==1)&&c.receive(s).getInputIndex(1).equals(s[3])&&c.receive(s).getOutputIndex(1).equals("README.html")&&c.receive(s).getStyleIndex(1)==1) ;
	}
	@Test
	public void testCommandStyle2() {
		CommandCheck c = new CommandCheck() ;
		String s[] = new String[4];

		s[0] = "README.md";
		s[1] = "-style";
		s[2] = "plain";
		s[3] = "README.md";

		assertTrue(c.receive(s).getInputIndex(0).equals(s[0])&&c.receive(s).getOutputIndex(0).equals("README.html")&&(c.receive(s).getStyleIndex(0)==1)&&c.receive(s).getInputIndex(1).equals(s[3])&&c.receive(s).getOutputIndex(1).equals("README.html")&&c.receive(s).getStyleIndex(1)==1) ;
	}
	@Test
	public void testCommandStyleOutput() {
		CommandCheck c = new CommandCheck() ;
		String s[] = new String[5];

		s[0] = "README.md";
		s[1] = "-style";
		s[2] = "slide";
		s[3] = "-filename";
		s[4] = "output";		

		assertTrue(c.receive(s).getInputIndex(0).equals(s[0])&&c.receive(s).getOutputIndex(0).equals(s[4]+".html")&&(c.receive(s).getStyleIndex(0)==3)) ;
	}
	@Test
	public void testCommand2() {
		CommandCheck c = new CommandCheck() ;
		String s[] = new String[12];

		s[0] = "README.md";
		s[1] = "README.md";
		s[2] = "-style";
		s[3] = "slide";
		s[4] = "README.md";
		s[5] = "-filename";
		s[6] = "output";
		s[7] = "README.md";
		s[8] = "-style";
		s[9] = "slide";
		s[10] = "-filename";
		s[11] = "output";		

		assertTrue(c.receive(s).getInputIndex(0).equals(s[0])&&c.receive(s).getOutputIndex(0).equals("README.html")&&(c.receive(s).getStyleIndex(0)==1)&&c.receive(s).getInputIndex(1).equals(s[1])&&c.receive(s).getOutputIndex(1).equals("README.html")&&(c.receive(s).getStyleIndex(1)==3)&&c.receive(s).getInputIndex(2).equals(s[4])&&c.receive(s).getOutputIndex(2).equals(s[6]+".html")&&(c.receive(s).getStyleIndex(2)==1)&&c.receive(s).getInputIndex(3).equals(s[7])&&c.receive(s).getOutputIndex(3).equals(s[11]+".html")&&(c.receive(s).getStyleIndex(3)==3)) ;
	}



	@Test
	public void testCommandInputError(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[1];
		 	 s[0] = "README";
		 	 c.receive(s);
	}
	@Test
	public void testCommandEmptyError(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[0];
		 	 
		 	 c.receive(s);
	}
	@Test
	public void testCommandStyleError(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[3];
		 	 s[0] = "README.md";
			 s[1] = "-style";
			 s[2] = "invalidstyle";
		 	 c.receive(s);
	}
	@Test
	public void testCommandEmptyOutputError(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[2];
		 	 s[0] = "README.md";
			 s[1] = "-filename";
		 	 c.receive(s);
	}

	@Test
	public void testCommandOutputError(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[3];
		 	 s[0] = "README.md";
			 s[1] = "-filename";
			 s[2] = "\\/:*?\"<>|";
		 	 c.receive(s);
	}
	@Test
	public void testCommandEmptyStyleError(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[2];
		 	 s[0] = "README.md";
			 s[1] = "-style";
			 c.receive(s);
	}

	@Test
	public void testCommandOutputError2(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[3];
		 	 s[0] = "README.md";
			 s[1] = "-filename";
			 s[2] = "\\";
		 	 c.receive(s);
	}
	@Test
	public void testCommandOutputError3(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[3];
		 	 s[0] = "README.md";
			 s[1] = "-filename";
			 s[2] = "/";
		 	 c.receive(s);
	}
	@Test
	public void testCommandOutputError4(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[3];
		 	 s[0] = "README.md";
			 s[1] = "-filename";
			 s[2] = ":";
		 	 c.receive(s);
	}
	@Test
	public void testCommandOutputError5(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[3];
		 	 s[0] = "README.md";
			 s[1] = "-filename";
			 s[2] = "*";
		 	 c.receive(s);
	}
	@Test
	public void testCommandOutputError6(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[3];
		 	 s[0] = "README.md";
			 s[1] = "-filename";
			 s[2] = "?";;
		 	 c.receive(s);
	}
	@Test
	public void testCommandOutputError7(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[3];
		 	 s[0] = "README.md";
			 s[1] = "-filename";
			 s[2] = "\"";;
		 	 c.receive(s);
	}
	@Test
	public void testCommandOutputError8(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[3];
		 	 s[0] = "README.md";
			 s[1] = "-filename";
			 s[2] = "<";;
		 	 c.receive(s);
	}
	@Test
	public void testCommandOutputError9(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[3];
		 	 s[0] = "README.md";
			 s[1] = "-filename";
			 s[2] = ">";;
		 	 c.receive(s);
	}

	@Test
	public void testCommandOutputError10(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[3];
		 	 s[0] = "README.md";
			 s[1] = "-filename";
			 s[2] = "|";;
		 	 c.receive(s);
	}
	@Test
	public void testCommandOutputLongError(){

             CommandCheck c = new CommandCheck() ;
			 String s[] = new String[3];
		 	 s[0] = "README.md";
			 s[1] = "-filename";
			 s[2] = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";;
		 	 c.receive(s);
	}

	public void doTest(){
		this.testHelp() ;
		this.testCommand();
		this.testCommandStyle();
		this.testCommandOutput();
		this.testCommandOutputStyle();
		this.testCommandStyleOutput();
		this.testCommandOutput2();
		this.testCommandStyle2();
		this.testCommand2();


		this.testCommandInputError();
		this.testCommandEmptyError();
		this.testCommandStyleError();
		this.testCommandEmptyStyleError();
	 	this.testCommandEmptyOutputError();

		this.testCommandOutputError();
		this.testCommandOutputError2();
		this.testCommandOutputError3();
		this.testCommandOutputError4();
		this.testCommandOutputError5();
		this.testCommandOutputError6();
		this.testCommandOutputError7();
		this.testCommandOutputError8();
		this.testCommandOutputError9();
		this.testCommandOutputError10();
		this.testCommandOutputLongError();
	}


	
 
	public static void main(String [] args) {
		CommandCheckTest s = new CommandCheckTest();
		
		s.doTest();
		

	}
}
