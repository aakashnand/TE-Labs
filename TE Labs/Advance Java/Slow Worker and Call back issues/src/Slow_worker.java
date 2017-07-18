import java.io.*;
import java.util.Arrays;
public class Slow_worker {
public String sort(File fobj) throws IOException
  {
	int myarray[]=new int[10];
	int index=0,temp;
	String line;
	FileInputStream fis =new FileInputStream(fobj);
	//FileOutputStream fos =new FileOutputStream(fobj);
	InputStreamReader in =new InputStreamReader(fis);
	BufferedReader br=new BufferedReader(in);
	while((line=br.readLine())!=null)
			{
		       myarray[index]=Integer.parseInt(line);
		       index++;
			}
	 for(int i=0 ;i<myarray.length;i++)
	 {
		 for(int j=0;j<myarray.length;j++)
		 {
			 if(myarray[j]>myarray[i])
			 {
				 temp=myarray[j];
				 myarray[j]=myarray[i];
				 myarray[i]=temp;
			 }
		 }
		
	 }
	 
	String nand=Arrays.toString(myarray);
	System.out.println(nand);
	return null;
	
  }
  public static void main(String args[]) throws IOException
   {
	  Slow_worker swobj =new Slow_worker();
	  File file =new File("Input.txt");
	  long time=System.currentTimeMillis();
	  System.out.println(time);
	  swobj.sort(file);
	  long time1 = System.currentTimeMillis();
	  //System.out.println(time1);
	  //System.out.println(time1-time);
   } 
}
