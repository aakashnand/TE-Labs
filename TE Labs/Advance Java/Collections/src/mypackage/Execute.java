package mypackage;
import java.util.*;
public class Execute {

	public static void main(String[] args) {
		int no;
		String name;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the No and String\n");
        no=Integer.parseInt(scan.nextLine());
        name=scan.nextLine();
        Generics<Integer,String> gobj =new Generics<>();
        gobj.show();
        gobj.<Integer,String>show(no, name);
        Generics<Integer,String> gobj1 =new Generics<>(no,name);
        gobj1.show();
        gobj1.show(10.5, "Hello");
	}

}
class Generics<T1,T2>
{
    
	T1 var;
	T2 str;
	public Generics()
	{
		var=(T1) new Integer(10);
		str=(T2) new  String("VIT");
	}
	public Generics(T1 no,T2 name)
	{
		var=no;
		str=name;
	}
	void show()
	{
		System.out.println(""+var +""+ str);
	}
	<T1,T2> void show(T1 var1,T2 var2)
	{
		System.out.println(""+ var1 + ""+var2);
	}
}
