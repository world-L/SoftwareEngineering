public class Main{

	public static void main(String[] args){

		CommandCheck check = new CommandCheck();
		Format format = new Format();
		
		format = check.receive(args);

		if(format.getnumOfFile() == 0) return;

		for(int i = 0;i<format.getnumOfFile();i++){
			DocumentParser dParser = new DocumentParser();
			dParser.readLine(format,i);
			NodeParser nParser = new NodeParser();
			Transform transfrom = new Transform();
			transfrom.createFile(format,i);
		}
			
		return;
	}
		

}