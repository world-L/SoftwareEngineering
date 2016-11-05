public class Main{

	public static void main(String[] args){
		Main main = new Main();
		int argNum;
		int style = 1;

		
		try{
			argNum = main.inputCheck(args);
			
			if(args[0].equals("-help")){
			main.help();
			return;
			}

			main.fileCheck(args[0]);

			if(argNum == 1){
				style = 1;
			}else if(argNum == 2){
				style = main.styleCheck(args[1]);
			}			
			
		}
		catch(Exception e){
			System.err.println(e);
			return;
		}		

		System.out.println("File name is:" + args[0]);
		if(style == 1) 
			System.out.println("Style is plain");
		else if(style == 2)
			System.out.println("Style is fancy");
		else if(style == 3)
			System.out.println("Style is slide");
		
	}


	public void help(){
		System.out.println("This is help function.");

	}

	public void fileCheck(String name) throws Exception{
		
		String[] filename;

		filename = name.split("\\.");

		if(!filename[filename.length-1].equals("md")){
			throw new Exception("Wrong File Name Extension.");
		}
	}

	public int inputCheck(String[] input) throws Exception{				

		if(input.length == 0){
			throw new Exception("No arguments");
		}else if(input.length > 2){
			throw new Exception("More than 2 arguments");
		}else{
			return input.length;
		}
						
	}

	public int styleCheck(String style) throws Exception{

		if(style.equals("plain")){
			return 1;
		}else if(style.equals("fancy")){
			return 2;
		}else if(style.equals("slide")){
			return 3;
		}else{
			throw new Exception("Wrong style input");
		}

	}
		

}