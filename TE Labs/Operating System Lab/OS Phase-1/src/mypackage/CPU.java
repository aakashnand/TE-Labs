package mypackage;
import java.io.*;
public class CPU {
	char [] IR = new char [4];//Instruction register
	char [] R = new char [4];//General Register
	char  IC[] = new char[2];//Instruction Counter
	static boolean c=false;
	int ICcounter=0,mem_loc =0;;
	boolean terminate=false,check=false;//Boolean Initialization
	Memory m = new Memory();
	
	public void load_to_IR(int loc1) throws Exception//loads instruction from memory to IR
	{
		System.out.println("Fetching....");
		Thread.sleep(380);
		IR = m.getMemory(loc1);
	}

	public void setIC(int counter)//increment IC after fetching instruction
	{
	
		if(counter>=0 && counter<10)
	  	{
	  		IC[0]='0';
	  		IC[1]=(char)('0'+counter);
	  	}
		else
		{
			IC[0]=(char)('0'+counter/10);
			IC[1]=(char)('0'+counter%10);
		}
	}
	public void getIC()//displays IC's status
	{
		System.out.print(""+IC[0]+IC[1]);
	}
	public void masterMode(int interrupt) throws IOException//master mode and Service Interrupts 
, Exception
	{
		Memory memObj=new Memory();
	   	String card="";
		switch(interrupt)
	   	{
	   	case 1://GD(Get Data Interrupt)
	   		Memory.data=OS.br.readLine();
	   		
	   		mem_loc=mem_loc-(mem_loc%10);
	   		memObj.store_card_to_memory_location(mem_loc, Memory.data);
	   		System.out.println("Executing....");
	   		Thread.sleep(380);
	   		
	   		break;
	   	case 2://PD(Print Data Interrupt)
	   	    card=memObj.getCard(mem_loc);
	   	    card=card+"\n";
	   	    OS.fos.write(card.getBytes());
	   	    OS.fos.flush();
	   	    System.out.println("Executing....");
	   	    Thread.sleep(380);
	   		break;
	   	case 3:
	   		terminate=true;
	   	}
	}
	public void start_Execution() throws Exception  
	{
		
		char [] operator = new char[2];
		char [] operand = new char[2];
		char [] temp =new char[4];
		String interupt = null;
		String char_to_string , m1,endline;
		int loc =0;
		int ServiceInterupt;
		
		while(!terminate)//Loop until the Instructions Are Not over
		{
			load_to_IR(loc);//load Instruction from Memory Location loc(fetch Operation)
			setIC(ICcounter);
			for(int j =0; j<2 ; j++)
			{
				operator[j] = IR[j];//breaks word into operator
				
			}
			
			for(int j =2, i =0 ; j<4 && i<2 ; j++, i++)
			{
				operand[i] = IR[j];//breaks word into operand
			}
			
			m1 = String.valueOf(operand);
			mem_loc = Integer.parseInt(m1);	
			interupt = String.valueOf(operator);
			if(operator[0]=='H')
			{
				interupt=String.valueOf('H');//Storing Halt instruction W
			}
			switch (interupt) 
			{
			case "GD":
				ServiceInterupt=1;
				System.out.println("Decoding....");
				Thread.sleep(380);
				masterMode(ServiceInterupt);
				break;
				
			case "PD":
				ServiceInterupt=2;
				System.out.println("Decoding....");
				Thread.sleep(380);
				masterMode(ServiceInterupt);
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
					loc=mem_loc-1;
					ICcounter=loc;
				}
				System.out.println("Decoding....");
				Thread.sleep(380);
				System.out.println("Executing....");
				Thread.sleep(380);
				break;
				
			case "H":
				ServiceInterupt=3;
				masterMode(ServiceInterupt);
				System.out.println("Terminating....");
				Thread.sleep(380);
				endline="\n-------------------------------------------------------\n";//Separting Output by ---line
				OS.fos.write(endline.getBytes());

				break;
				
			default:
				System.out.println("Enter the correct command.");
				break;
			}
			loc++;
		    ICcounter++;		
		}
		
	}

}
