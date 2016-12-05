//CommandCheck class that read files and set formats


public class CommandCheck{

	CommandFormat format;

	public CommandFormat receive(String[] args){	// argument can contain inputfile name, outputfile name, style,
													// and help command.
		int argNum;
		int checkNum;

		format = new CommandFormat();
		
		try{
			argNum = this.inputCheck(args);
			

			for(int i=0;i<argNum;){	// check argument and set format each file
				
				if(args[0].equals("-help")){	// print help message and return

					this.help();
					return null;

				}

				checkNum = 1;	   

				format.setInput(this.fileCheck(args[i]));
				

				/* check the number of argument and set format
					default format of outputfile name is inputfile name
					default format of style is plain style	
				*/					
				if(argNum - i <= 1){	// set default formats

					format.setStyle(1);
					format.setOutput(format.getInput().substring(0,format.getInput().length()-3)+".html");
					i += checkNum;

				}else if(argNum - i <= 3){

					if(args[i+1].equals("-style")){
				
						if(i+2 < argNum)
							format.setStyle(this.styleCheck(args[i+2]));
						else
							throw new Exception("Wrong format");

						checkNum +=2;		

					}else
						format.setStyle(1);
					

					if(args[i+1].equals("-filename")){

						if(i+2 < argNum)
							format.setOutput(this.outNameCheck(args[i+2])+".html");
						else
							throw new Exception("Wrong format");

						checkNum +=2;			

					}else
						format.setOutput(format.getInput().substring(0,format.getInput().length()-3)+".html");
					
					i += checkNum;
				}else{

					if(args[i+1].equals("-style")){
						
						format.setStyle(this.styleCheck(args[i+2]));
						checkNum +=2;		

						if(argNum - i <= 5){
							if(args[i+3].equals("-filename")){
								format.setOutput(this.outNameCheck(args[i+4])+".html");
								checkNum +=2;
							}else
								format.setOutput(format.getInput().substring(0,format.getInput().length()-3)+".html");
						}else
							format.setOutput(format.getInput().substring(0,format.getInput().length()-3)+".html");

						i += checkNum;

					}else if(args[i+1].equals("-filename")){
						
						format.setOutput(this.outNameCheck(args[i+2])+".html");
						checkNum +=2;		

						if(argNum - i <= 5){
							if(args[i+3].equals("-style")){
								format.setStyle(this.styleCheck(args[i+4]));
								checkNum +=2;
							}else
								format.setStyle(1);
						}else
							format.setStyle(1);

						i += checkNum;

					}else{
						format.setStyle(1);
						format.setOutput(format.getInput().substring(0,format.getInput().length()-3)+".html");	
						i += checkNum;
					}
					
				}						
			
			
			format.addNumOfFile(); // increase number of input format


			}
		}catch(Exception e){
				System.err.println(e);
				return null;
		}		
			
	
		return format;
		
	}


	public void help(){	//print help messasge function
		System.out.println("Instruction: java Main -help  ");
		System.out.println("  Or java Main [ MDfile [-option arg] [-option arg] ]+\n");
		
		System.out.println("in here, options are like this.");
		System.out.println("  [-filename arg]	designate filename of converting html file");
		System.out.println("                	[arg] is filename of converting html file");
		System.out.println("                	default filename is [MDfile]'s filename");
		System.out.println("			Cannot use those characters:\\ / : * ? \" < > |\n");
		
		System.out.println("  [-style  arg] 	designate style of converting html file");
		System.out.println("                	[arg] is style of converting html file");
		System.out.println("                	There are three styles of support : plain, fancy, slide");
		System.out.println("                	default style is plain style\n");

		System.out.println("About help Commnad");
		System.out.println("  -help			print this help message");
		System.out.println("  			this option cannot use with [MDfile] command and another option\n");

		System.out.println("For more information, see README.md document.");
	
	}
	public String fileCheck(String name) throws Exception{	// check that the input filename is .md format
		
		String[] filename;

		filename = name.split("\\.");

		if(!filename[filename.length-1].equals("md")){
			throw new Exception("Wrong File Name Extension. Only accept .md format");
		}

		return name;
	}

	public int inputCheck(String[] input) throws Exception{	// check the number of input		

		if(input.length == 0){
			throw new Exception("No arguments");
		}else{
			return input.length;
		}
						
	}

	public int styleCheck(String style) throws Exception{	// check the style
															// style contain plain, fancy and slide
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

	public String outNameCheck(String output) throws Exception{	// check the outputfile name contain illegal character

		boolean check = false;
		
		check = output.contains("\\");
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



