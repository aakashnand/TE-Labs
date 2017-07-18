// Execute user program after the interrupts are sets
package mypackage;

public class CPU 
{
	static char [] IR = new char [4];   //Instruction register
	char [] R = new char [4];    //General Register
	static int  IC[] = new int[2];    //Instruction Counter
	static boolean c=false;
	int [] meml = new int [2];
	static int ICcounter=0,mem_loc =0,page_table_number,new_index,count,pageno,loc,no;
	static String char_to_string ,endline,m1,m2,interupt;
	PCB pcb = new PCB();
	CPU cp = new CPU();
	Memory mem = new Memory();
	boolean terminate=false,check=false;   //Boolean Initialization
	static int ServiceInterupt=0,program_interupt=0,time_Interupt=0;
	
	public void load_to_IR(int loc1) throws Exception   //loads instruction from memory to IR
	{
		System.out.println("Fetching....");
		Thread.sleep(380);
		IR = mem.getMemory(loc1);
	}

	public void setIC(int counter)      //increment IC after fetching instruction
	{
	
		if(counter>=0 && counter<10)
	  	{
	  		IC[0] = 0;
	  		IC[1] = 0 + counter;
	  	}
		else
		{
			IC[0] = 0 + counter/10;
			IC[1] = 0 + counter%10;
		}
	}
	public void getIC()      //displays IC's status
	{
		System.out.print(""+IC[0]+IC[1]);
	}
	public void masterMode(int SI,int PI,int TI) throws Exception        //master mode and Service Interrupts 
	{
		if(TI==0 || TI==1)
		{
			if(SI==1 )
			{
				// Enqueue PCB's object from head of Ready Queue to IO Queue (Read operation)
				read();
			}
			if(SI==2)
			{
				// Enqueue PCB's object from head of Ready Queue to IO Queue (Write operation)
				write();
			}
			if(SI==3)
			{
				// Enqueue PCB's object from head of Ready Queue to Terminate Queue (Termination(0))
				ServiceInterupt=0;
				terminate(0);
			}
			if(PI==1)
			{   
				// Enqueue PCB's object from head of Ready Queue to Terminate Queue (Termination(4))
				program_interupt=0;
				terminate(4);
			}
			if(PI==2)
			{
				// Enqueue PCB's object from head of Ready Queue to Terminate Queue (Termination(5))
				program_interupt=0;
				terminate(5);
			}
			if(PI==3)
			{
				if(interupt.equals("GD") || interupt.equals("SR"))
				{
					pageno = mem.allocatepage();
					String no = String.valueOf(IR[2]);
			    	update_new_page_table((pcb.PTR*10+Integer.parseInt(no)));  // Updating the New Entry
			    	program_interupt=0;
			    	start_Execution();
				}
				else
				{
					// Enqueue PCB's object from head of Ready Queue to Terminate Queue (Termination(6))
					terminate(6);
					//Invalid Page Fault
				}
			}
		}
		else
		{
			if(SI==1)
			{
				// Enqueue PCB's object from head of Ready Queue to Terminate Queue (Termination(3))
 				terminate(3);
		
			}
			else if(SI==2 )
			{
				// Enqueue PCB's object from head of Ready Queue to IO Queue (Write operation)
				write();
				// Enqueue PCB's object from head of Ready Queue to Terminate Queue (Termination(3))
				terminate(3);
		
			}
			else if(SI==3)
			{
				ServiceInterupt=0;
				terminate(0);
				// Enqueue PCB's object from head of Ready Queue to Terminate Queue (Termination(0))
			}
			else if(PI==1)
			{
				// Enqueue PCB's object from head of Ready Queue to Terminate Queue (Termination(7))
				terminate(7); // 7=4+3 refer 3 and 4 error messages
			}
			else if(PI==2)
			{
				// Enqueue PCB's object from head of Ready Queue to Terminate Queue (Termination(8))
				terminate(8); // 8=5+3 refer 3 and 5 error messages
			}
			else if(PI == 3)
			{
				// Enqueue PCB's object from head of Ready Queue to Terminate Queue (Termination(3))
			}
		}
	}
	

	public void start_Execution() throws Exception  
	{
		char [] operator = new char[2];
		char [] operand = new char[2];
		char [] temp =new char[4];
		
		
	  
		 int loc =0;
		// *******************Four Lines from Simulation Doing Stuff **********
		
		 if(OS.readyQ.peek()==null)
		 {
			 return;
		 }
		 else
		 {
		   
			 if(OS.readyQ.peek().TTC ==OS.readyQ.peek().TTL)
			 {
				CPU.time_Interupt=2;
			 }
			OS.readyQ.peek().TSC++; // Incrementing Time Slice Counter
			if(OS.readyQ.peek().TSC==Time_Slice)
			{
		        CPU.time_Interupt=1;		
			}
			// ******************Four Lines ending here**************
		 }
		while(!terminate)          //Loop until the Instructions Are Not over
		{
			setIC(ICcounter);
			loc = virtual_to_real_conversion(IC);  //Conversion for Fetching the Instruction
			load_to_IR(loc);         //load Instruction from Memory Location loc(fetch Operation)
			 
			//PCB.TTC++;             
			/*if(PCB.TTC>=PCB.TTL)  //This code is done below 
			{
				time_Interupt=2; 
			}*/
			
			for(int j =0; j<2 ; j++)
			{
				operator[j] = IR[j];            //breaks word into operator
				
			}
			
			for(int j =2, i =0 ; j<4 && i<2 ; j++, i++)
			{
				operand[i] = IR[j];       //breaks word into operand
			}
			
			m1 = String.valueOf(operand[0]);
			meml[0]=Integer.parseInt(m1); 
			m2 = String.valueOf(operand[1]);
			String bt_no=m1+m2;
			meml[1]=Integer.parseInt(m2);
			check_operand_interupt();   // Checking for the Operand Error
			int bt_no1=Integer.parseInt(bt_no);
			interupt = String.valueOf(operator);
			
			if(operator[0]=='H')
			{
				interupt=String.valueOf('H');        //Storing Halt instruction W
			}
			
			mem_loc = virtual_to_real_conversion(meml); //Conversion for: Ex. "20" of GD20
			if(program_interupt==3)
			{
				 masterMode(ServiceInterupt, program_interupt, time_Interupt);
				 continue;
			}
			switch (interupt) 
			{
			case "GD":
				ServiceInterupt=1;
				System.out.println("Decoding....");
				Thread.sleep(380);
				break;
				
			case "PD":
				ServiceInterupt=2;
				System.out.println("Decoding....");
				Thread.sleep(380);
				break;
		
			case "LR":
			
				R = m.getMemory(mem_loc);
				System.out.println("Decoding....");
				Thread.sleep(380);
				System.out.println("Executing....");
				Thread.sleep(380);
				break;
				
			case "SR":
				
				char_to_string = String.valueOf(R);
				m.store_to_word(mem_loc, char_to_string);
				System.out.println("Decoding....");
				Thread.sleep(380);
				System.out.println("Executing....");
				Thread.sleep(380);
				break;
				
			case "CR":
				
				temp=m.getMemory(mem_loc);
				for(int i=0;i<=3;i++)
                 	{
                 		if(R[i]==temp[i])
                 		{
                 			c=true;
                 		}
                 		else
        				{
        					c=false;
        				}
                 	}
				System.out.println("Decoding....");
				Thread.sleep(380);
				System.out.println("Executing....");
				Thread.sleep(380);
				break;
				
			case "BT":
				
				if (c==true)
				{
		
					ICcounter=bt_no1-1;
				}
				System.out.println("Decoding....");
				Thread.sleep(380);
				System.out.println("Executing....");
				Thread.sleep(380);
				break;
				
			case "H":
				ServiceInterupt=3;
				
				System.out.println("Terminating....");
				Thread.sleep(380);
				//ServiceInterupt=0;
				//terminate(0);
				break;
				
			default:
				System.out.println("Enter the correct command.");
				program_interupt=1;
				break;
				
			}
			masterMode(ServiceInterupt,program_interupt,time_Interupt);
			ServiceInterupt=0;
		    ICcounter++;
		      
		}
		
	}
	
	public int virtual_to_real_conversion(int ic[]) throws Exception // Virtual To Real Conversion
	{
		int memloc;
		char [] temp = new char [2];
		char [] page = new char [4];
		String str;
		
		count = pcb.PTR * 10 + ic[0];
		page = mem.getMemory(count);
		if(page[1]=='1')
		{
			for(int j =2, i =0 ; j<4 && i<2 ; j++, i++)
			{
				temp[i] = page[j];       //breaks word to page no.
			}
			str = String.valueOf(temp);
			loc = Integer.parseInt(str);
				
		}
		else
		{
		     program_interupt=3;
	
		}
		
		memloc = loc * 10 + ic[1];
	
		return memloc;
	}


 void check_operand_interupt()   // checking for operand error
 {
	int operand[]=new int[2];
	operand[0]=Integer.parseInt(m1);
	operand[1]=Integer.parseInt(m2);
	if(!((operand[0]>=0 && operand[0]<=9) && (operand[1]>=0 && operand[1]<=9)))
	{
	
		program_interupt=2;
	} 
 }
 void read() throws Exception  //GD
  {
	    if(!(mem_loc%10==0))
	    {
	    	mem_loc=mem_loc-mem_loc%10;
	    }
	    Memory.data=OS.br.readLine();
	    
	    if(Memory.data.contains("$END"))
	    {
	    	terminate(1);
	    }
		
		Memory.counter = count;    // Storing Page_Table_Starting_Index in Memory.counter for Next Entry
    	mem.update_pagetable(pageno); //Updating Page Table
		mem.store_card_to_memory_location(mem_loc, Memory.data);
		//memObj.update_pagetable(pageno);
		System.out.println("Executing....");
		Thread.sleep(380);
		
		
  }
 void write() throws Exception  //PD
  {
	    String card="";
	    card=mem.getCard(mem_loc);
	    card=card+"\n";
	    OS.fos.write(card.getBytes());
	   // OS.fos.flush();
	    System.out.println("Executing....");
	    Thread.sleep(380);
	    pcb.TLC++;
	    if(pcb.TLC>=pcb.TLL)
	    {
	    	terminate(2);
	    } 
  }
 private void terminate(int error_no) throws Exception {
		
		
		if(error_no==0)
		{
			OS.fos.write("Normal Termination\n ".getBytes());
			terminate=true;
		}
		else if(error_no==1)
		{
			OS.fos.write("Out of Data\n".getBytes());
		}
		else if(error_no==2)
		{
			OS.fos.write("Line Limit Exceeded\n".getBytes());
		}
		else if(error_no==3)
		{
			OS.fos.write("Time Limit Exceeded\n".getBytes());
		}
		else if(error_no==4)
		{
			OS.fos.write("Operation Code Error\n".getBytes());
			terminate=true;
		}
		else if(error_no==5)
		{
			OS.fos.write("Operand Error\n".getBytes());
			terminate=true;
		}
		else if(error_no==6)
		{
			OS.fos.write("Invalid Page Fault\n".getBytes());
			terminate=true;
		}
		else if(error_no==7)
		{
			OS.fos.write("Time Limit Exceeded and Operation Code Error\n".getBytes());
		}
		else if(error_no==8)
		{
			OS.fos.write("Time Limit Exceeded and Operand Error\n".getBytes());
		}
		
		ServiceInterupt=0;

	}
 public void update_new_page_table(int index)   // Update the Newly Allocated page after page fault
	{
	   	char page_no_char[]=new char[2];
	    String new_page_no;
	    new_page_no=String.valueOf(pageno);
	    page_no_char=new_page_no.toCharArray();
	    if(pageno>=0 && pageno<=9)           //if page no is less than 10 append '0'
	    {
	    	Memory.memory[index][1]='1';
	    	Memory.memory[index][2]='0';
	    	Memory.memory[index][3]=page_no_char[0];		
	    }
	    else
	    {
	    Memory.memory[index][1]='1';
		Memory.memory[index][2]=page_no_char[0];
		Memory.memory[index][3]=page_no_char[1];
	    }
		
	}
 public void IR_1()
  {
      if(OS.channel_1_empty_buffer.type==0)
      {
    	  read next card in given channel_1_empty_buffer;
    	  change type bit to input full buffer;
      }
      if(not end of File and empty buffer queue is not empty)
       {
    	  OS.channel_1_empty_buffer=OS.eb.poll();//Removing head of Empty Buffer Queue and Storing in Channel_1_Empty Buffer Queue
    	  start_channel(1); // Starting channel 1  
       }
      covert character buffer of current buffer object to string as line 
      if(line.startsWith("$AMJ"))
		{
			
    	    pcb.getCounters(line);
			pcb.TLC=0;    //Initialize Counters
		    pcb.TTC=0;
			mem.initialise_freespace();
			page_table_number=mem.allocatepage();
			pcb.PTR = page_table_number;
			new_index=page_table_number*10;
			Memory.freespace[page_table_number]=false;
			//store data in PCB's variables
			for(int l=0;l<10;l++)
			{
				Memory.memory[new_index][1]='0'; // Initialize 2nd Column of Page Table
				new_index++;
			}
			Memory.counter = pcb.PTR * 10;
			
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
			
			String ptr_info="The Value Of PTR = "+ pcb.PTR + "\n";
			OS.fos.write(ptr_info.getBytes());
			OS.fos.write("\n-------------------------------------------------------\n".getBytes());  //Seperating Output by ---line
			CPU.ICcounter=0;
	        CPU.ServiceInterupt=CPU.time_Interupt=CPU.program_interupt=0;
			continue;
		}
		else
		{
		   /*********************Continue From HERE PLZZZZZZZZZZZZZZZZZZZ*/	
			System.out.println("Loading....");//Loading to memory and loading function calls
			pageno = mem.allocatepage();//allocate page
			mem.store_card_to_memory_location(pageno*10, line);	//store program card in that page
			mem.update_pagetable(pageno);//update page table
		    continue;
		}
		
	}

  }

