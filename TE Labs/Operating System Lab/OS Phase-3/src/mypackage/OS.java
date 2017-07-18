package mypackage;
import java.io.*;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class OS 
{
	static BufferedReader br;//Static to use everywhere in program with its current Value
	static FileOutputStream fos;//Static to use everywhere in program with its current Value
	static int page_table_number;
	static Queue<Buffer> ifb ,eb,ofb; 
	static Queue<PCB> loadQ ,readyQ,terminateQ,IOQ;
	static Buffer channel_1_empty_buffer,channel_2_empty_buffer,channel_3_empty_buffer=null;
	public static void main(String[] args) throws Exception 
	{
		/*long time=System.currentTimeMillis();//Storing initial Time
		Memory mem = new Memory();
		int no=1;//Counter for Job Completed
		CPU cp = new CPU();
		int new_index, pageno;
		File f = new File("code.txt");//creating File Connections
		File fout_obj=new File("output.txt");//Creating Output File Connections
		FileInputStream fis = new FileInputStream(f);//InputStream To read the File
		fos=new FileOutputStream(fout_obj,true);//Output File's Stream opened in Append Mode
		InputStreamReader isr = new InputStreamReader(fis);
		OS.br = new BufferedReader(isr);
		String line;*/
		
		/*while((line = br.readLine())!= null)//Loop for reading the File Line by line
		{
			
			
			if(line.startsWith("$AMJ"))
			{
				pcb.getCounters(line);
				PCB.TLC=0;    //Initialize Counters
			    PCB.TTC=0;
				mem.initialise_freespace();
				page_table_number=mem.allocatepage();
				PCB.PTR = page_table_number;
				new_index=page_table_number*10;
				Memory.freespace[page_table_number]=false;
				//store data in PCB's variables
				for(int l=0;l<10;l++)
				{
					Memory.memory[new_index][1]='0'; // Initialize 2nd Column of Page Table
					new_index++;
				}
				Memory.counter = PCB.PTR * 10;
				
				continue;
				
			}
			else if (line.startsWith("$DTA")) 
			{
				
				cp.start_Execution();		//As line Started with $DTA executing user Program
				cp.terminate=false;			//Making terminate flag false so as to read next Job refer loop of CPU class
				System.out.println("Memory Cleared....");
				if(no==1)					//logic to Display Job Count
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
				
				continue;   		
			}
			else if (line.startsWith("$END"))
			{
				
				String ptr_info="The Value Of PTR = "+ PCB.PTR + "\n";
				fos.write(ptr_info.getBytes());
				fos.write("\n-------------------------------------------------------\n".getBytes());  //Seperating Output by ---line
				CPU.ICcounter=0;
		        CPU.ServiceInterupt=CPU.time_Interupt=CPU.program_interupt=0;
				continue;
			}
			else
			{
				
				System.out.println("Loading....");//Loading to memory and loading function calls
				pageno = mem.allocatepage();//allocate page
				mem.store_card_to_memory_location(pageno*10, line);	//store program card in that page
				mem.update_pagetable(pageno);//update page table
			    continue;
			}
			
		}
		long time2=System.currentTimeMillis();//Storing final Time
		System.out.println("Exiting...the Program");
		System.out.println("Time Required for Execution is:"+(time2-time)/1000+" seconds");//Displaying  Time Required
	}*/

	public void initialisation()
	{
		Buffer bufferObj[] = new Buffer[10]; //Instantiating 10 objects of buffer 
	    Queue<Buffer> ifb = new ArrayBlockingQueue<>(10);
		Queue<Buffer> eb = new ArrayBlockingQueue<>(10);
		Queue<Buffer> ofb = new ArrayBlockingQueue<>(10);
		Queue<PCB> loadQ = new ArrayBlockingQueue<>(10);
		Queue<PCB> readyQ = new ArrayBlockingQueue<>(10);
		Queue<PCB> terminateQ = new ArrayBlockingQueue<>(10);
		ifb.addAll(null); // initialised to null
		ofb.addAll(null); // initialised to null
		//set IOI to 1
        while(size of queue)
        {
        	enque buffer objects in empty queue eb
        	set type(bit) of each object to 0;
        	//0=Empty buffer
        	//1=Input Full Buffer
        	//2=Output Full Buffer
        }
        //Creating temporary buffers to access same Buffer for Next time to do Remaining Job
       
	}
	public void simulation()
	{
	 
		   if(flag1==busy)
		   {
			   increment channel_1_timer 
		   }
		   if(flag2==busy)
		   {
			   increment channel_2_timer
		   }
		   if(flag3==busy)
		   {
			   increment channel_3_timer
		   }
		  
		   if(channel_1_timer==5)
		   {
			   IOI=IOI+1;
		   }
		   if(channel_2_timer==5)
		   {
			   IOI=IOI+2;
		   }
		   if(channel_3_timer==2)
		   {
			   IOI=IOI+4;
		   }
	   
	}
	
}
