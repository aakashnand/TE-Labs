package mypackage;

public class Linker {

	public static void main(String[] args) throws Exception {
		
		Symbol_Table st1 = new Symbol_Table("code1.c");
		Symbol_Table st2 = new Symbol_Table("code2.c");
		st1.process();
		st2.process();

   }
}
