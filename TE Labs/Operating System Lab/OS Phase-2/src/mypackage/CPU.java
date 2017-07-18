// Execute user program after the interrupts are sets
package mypackage;

public class CPU 
{
	static char [] IR = new char [4];   //Instruction register
	char [] R = new char [4];    //General Register
	static int  IC[] = new int[2];    //Instruction Counter
	static boolean c=false;
	int [] meml = new int [2];
	static int ICcounter=0,mem_loc =0;
	static int count;
	static  int pageno,loc;
	static String char_to_string ,endline,m1,m2,interupt;
	Memory memObj=new Memory();
	
	boolean terminate=false,check=false;   //Boolean Initialization
	Memory m = new Memory();
	static int ServiceInterupt=0,program_interupt=0,time_Interupt=0;
	
	public void load_to_IR(int loc1) throws Exception   //loads instruction from memory to IR
	{
		System.out.println("Fetching....");
		Thread.sleep(380);
		IR = m.getMemory(loc1);
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
		if(TI==0)
		{
			if(SI==1 )
			{
				read();
			}
			if(SI==2)
			{
				write();
			}
			if(SI==3)
			{
				ServiceInterupt=0;
				terminate(0);
			}
			if(PI==1)
			{   
				program_interupt=0;
				terminate(4);
			}
			if(PI==2)
			{
				program_interupt=0;
				terminate(5);
			}
			if(PI==3)
			{
				if(interupt.equals("GD") || interupt.equals("SR"))
				{
					pageno = m.allocatepage();
					String no = String.valueOf(IR[2]);
			    	update_new_page_table((PCB.PTR*10+Integer.parseInt(no)));  // Updating the New Entry
			    	program_interupt=0;
			    	start_Execution();
				}
				else
				{
					terminate(6);
					//Invalid Page Fault
				}
			}
		}
		else
		{
			if(SI==2)
			{
				write();
				terminate(3);
			}
			else if(SI==1 && PI==3)
			{
				terminate(3);
			}
			else if(SI==3)
			{
				ServiceInterupt=0;
				terminate(0);
			}
			else if(PI==1)
			{
				terminate(7); // 7=4+3 refer 3 and 4 error messages
			}
			else if(PI==2)
			{
				terminate(8); // 8=5+3 refer 3 and 5 error messages
			}
		}
	}
	

	public void start_Execution() throws Exception  
	{
		char [] operator = new char[2];
		char [] operand = new char[2];
		char [] temp =new char[4];
		
		
	  
		 int loc =0;
		
		while(!terminate)          //Loop until the Instructions Are Not over
		{
			setIC(ICcounter);
			loc = virtual_to_real_conversion(IC);  //Conversion for Fetching the Instruction
			load_to_IR(loc);         //load Instruction from Memory Location loc(fetch Operation)
			 
			PCB.TTC++;             // Incrementing Total time Counter
			if(PCB.TTC>=PCB.TTL)   // Checking for Time Inetrupt
			{
				time_Interupt=2; 
			}
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
		
		count = PCB.PTR * 10 + ic[0];
		page = m.getMemory(count);
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

/*	public int pageFaultInterrupt() 
	{
	   char[] temp = new char[2];
	    temp[0]=IR[0];
	    temp[1] = IR[1];
	    String str;
	    int pageno=0;
	    
	    str = String.valueOf(temp);
	    
	    switch(str)
	    {
	    case "GD":
	    	// update the value of totalTimeCounter by 2 call startExcution
	    	return pageno;
	    
	    case "PD":
	    	terminate = false;
	    	break;
	    	
	    
	    case "LR":
	    	terminate = false;
	    	break;
	    	
	    case "SR":
	    	
	    	return pageno;
	    	
	    }
	    return pageno;
		
	}*/
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
    	m.update_pagetable(pageno); //Updating Page Table
		memObj.store_card_to_memory_location(mem_loc, Memory.data);
		//memObj.update_pagetable(pageno);
		System.out.println("Executing....");
		Thread.sleep(380);
		
		
  }
 void write() throws Exception  //PD
  {
	    String card="";
	    card=memObj.getCard(mem_loc);
	    card=card+"\n";
	    OS.fos.write(card.getBytes());
	   // OS.fos.flush();
	    System.out.println("Executing....");
	    Thread.sleep(380);
	    PCB.TLC++;
	    if(PCB.TLC>=PCB.TLL)
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
}
