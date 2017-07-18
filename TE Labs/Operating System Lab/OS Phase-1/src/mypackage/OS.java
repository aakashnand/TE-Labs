package mypackage;
import java.io.*;
public class OS {
	static BufferedReader br;//Static to use everywhere in program with its current Value
	static FileOutputStream fos;//Static to use everywhere in program with its current Value
	public static void main(String[] args) throws Exception 
	{
		long time=System.currentTimeMillis();//Storing initial Time
		Memory mem = new Memory();
		int no=1;//Counter for Job Completed
		CPU cp = new CPU();
		int memory_index =0;
		File f = new File("code.txt");//creating File Connections
		File fout_obj=new File("output.txt");//Creating Output File Connections
		FileInputStream fis = new FileInputStream(f);//InputStream To read the File
		fos=new FileOutputStream(fout_obj,true);//Output File's Stream opened in Append Mode
		InputStreamReader isr = new InputStreamReader(fis);
		OS.br = new BufferedReader(isr);
		String line;
		while((line = br.readLine())!= null)//Loop to read the File Line by line
		{
			if(line.startsWith("$AMJ"))
			{
				continue;//continues if the Line Starts with $AMJ
			}
			else if (line.startsWith("$DTA")) 
			{
				cp.start_Execution();//As line Started with $DTA executing user Program
				cp.terminate=false;//Making terminate flag false so as to read next Job refer loop of CPU class
				System.out.println("Memory Cleared....");
				if(no==1)//logic to Display Job Count
				{
					System.out.println(" 1st Job completed");
					no++;
				}
				else if(no==2)
				{
					System.out.println(" 2nd Job completed ");
					no++;
				}
				else if(no==3)
				{
					System.out.println(no+"rd Job Completed");
					no++;
				}
				else
				{
					System.out.println(no+"th Job Completed");
					no++;
				}
				Thread.sleep(380);
				//System.out.println(CPU.c);
				continue;   		
			}
			else if (line.startsWith("$END"))
			{
				memory_index=0;
				continue;
			}
			else
			{
				System.out.println("Loading....");//Loading to memory and loading function calls
				Thread.sleep(380);
				mem.store_card_to_memory_location(memory_index, line);
				memory_index+=10;	
				continue;
			}
			
		}
		long time2=System.currentTimeMillis();//Storing final Time
		System.out.println("Exiting...the Program");
		System.out.println("Time Required for Execution is:"+(time2-time)/1000+" seconds");//Displaying  Time Required
	}

}
