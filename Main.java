public class Main{

	public static void main(String[] args){

		CommandCheck check = new CommandCheck();
		Format format;
		MDElement element;

		format = check.receive(args);

		if(format == null)System.out.println("Error");






		return;
	}
		

}