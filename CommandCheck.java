public class CommandCheck{

	Format format;

	public Format receive(String[] args){

		int argNum;

		format = new Format();
		
		try{
			argNum = this.inputCheck(args);
			
			if(args[0].equals("-help")){

				this.help();
				return null;

			}   

			format.setInput(this.fileCheck(args[0]));


			if(argNum == 1){

				format.setStyle(1);
				format.setOutput(format.getInput());

			}else if(argNum == 3){
				
				if(args[1].equals("-filename")){

					format.setOutput(this.outNameCheck(args[2]));
					format.setStyle(1);

				}else if(args[1].equals("-style")){

					format.setStyle(this.styleCheck(args[2]));
					format.setOutput(format.getInput());

				}else{

					throw new Exception("Wrong format: option command is -filename or -style");
				}	
		
			}else if(argNum == 5){

				if(args[1].equals("-filename")){

					format.setOutput(this.outNameCheck(args[2]));

					if(!args[3].equals("-style"))
						throw new Exception("Wrong format");
						
					format.setStyle(this.styleCheck(args[4]));

				}else if(args[1].equals("-style")){

					format.setStyle(this.styleCheck(args[2]));

					if(!args[3].equals("-filename"))
						throw new Exception("Wrong format: option command is -filename or -style");

					format.setOutput(this.outNameCheck(args[4]));

				}else{

					throw new Exception("Wrong format: option command is -filename or -style");
				}	

			}						
			
		}
		catch(Exception e){
			System.err.println(e);
			return null;
		}		

		System.out.println("Input File name is:" + format.getInput());
		
		System.out.println("Output File name is:" + format.getOutput());

		if(format.getStyle() == 1)
			System.out.println("Style is: plain");
		else if(format.getStyle() == 2)
			System.out.println("Style is: fancy");
		else
			System.out.println("Style is: slide");

		return format;
		
	}


	public void help(){
		System.out.println("Instruction: java Main [MDfile] [-option] [arg]");
		System.out.println("  Or java Main MDfile [-option1] [arg] [-option2] [arg]\n");
		
		System.out.println("in here, options are like this.");
		System.out.println("  -filename: [arg]	designate filename of converting html file");
		System.out.println("                	[arg] is filename of converting html file");
		System.out.println("                	default filename is [MDfile]'s filename");
		System.out.println("			Cannot use those characters:\\ / : * ? \" < > |\n");
		
		System.out.println("  -style: [arg] 	designate style of converting html file");
		System.out.println("                	[arg] is style of converting html file");
		System.out.println("                	There are three styles of support : plain, fancy, slide");
		System.out.println("                	default style is plain style\n");

		System.out.println("  -help			print this help message");
		System.out.println("  			this option cannot use with [MDfile] command and another option\n");

		System.out.println("For more information, see README.md document.");
	
	}
	public String fileCheck(String name) throws Exception{
		
		String[] filename;

		filename = name.split("\\.");

		if(!filename[filename.length-1].equals("md")){
			throw new Exception("Wrong File Name Extension. Only accept .md format");
		}

		return name;
	}

	public int inputCheck(String[] input) throws Exception{				

		if(input.length == 0){
			throw new Exception("No arguments");
		}else if(input.length == 2 | input.length == 4){
			throw new Exception("Wrong format: missing arguments");
		}else if(input.length > 5){
			throw new Exception("Too many arguments");
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
			throw new Exception("Wrong style input only accept plain, fancy or slide.");
		}

	}

	public String outNameCheck(String output) throws Exception{

		boolean check = false;
		
		check = (check || output.contains("\\"));
		check = (check || output.contains("/"));
		check = (check || output.contains(":"));
		check = (check || output.contains("*"));
		check = (check || output.contains("?"));
		check = (check || output.contains("\""));
		check = (check || output.contains("<"));
		check = (check || output.contains(">"));
		check = (check || output.contains("|"));
		
		if(check)
			throw new Exception("Cannot use those characters in file name:\\ / : * ? \" < > |");
		if(output.length()>255)
			throw new Exception("Cannot name more than 255 characters");
	
		return output;
	}
		

}



