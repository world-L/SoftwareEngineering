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

					throw new Exception("Wrong format");
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
						throw new Exception("Wrong format");

					format.setOutput(this.outNameCheck(args[4]));

				}else{

					throw new Exception("Wrong format");
				}	

			}						
			
		}
		catch(Exception e){
			System.err.println(e);
			return null;
		}		

		System.out.println("File name is:" + format.getOutput());

		return format;
		
	}


	public void help(){
		System.out.println("This is help function.");
		System.out.println("Cannot use those characters:\\ / : * ? \" < > |");

	}

	public String fileCheck(String name) throws Exception{
		
		String[] filename;

		filename = name.split("\\.");

		if(!filename[filename.length-1].equals("md")){
			throw new Exception("Wrong File Name Extension.");
		}

		return name;
	}

	public int inputCheck(String[] input) throws Exception{				

		if(input.length == 0){
			throw new Exception("No arguments");
		}else if(input.length > 5){
			throw new Exception("More than 5 arguments");
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
			throw new Exception("Cannot use those characters:\\ / : * ? \" < > |");
		if(output.length()>255)
			throw new Exception("Cannot name more than 255 characters");

		return output;
	}
		

}



