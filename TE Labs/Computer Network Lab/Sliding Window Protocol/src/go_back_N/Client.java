package go_back_N;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

 public static void main(String[] args) throws Exception, IOException {
	
	Socket s = new Socket("127.0.0.1", 5000);
	PrintWriter out = new PrintWriter(s.getOutputStream(),true);
	BufferedInputStream br = new BufferedInputStream(s.getInputStream());
	int size=br.read();
	System.out.println("The No of Frames that will be received are"+size );
	int choice = br.read();
	int frame;
	String value;
	if(choice==2)
	{
	  for(int j=0;j<size;j++)
	    {
		  frame=br.read();
		  System.out.println("The received Frames is" +frame+"....");
		  System.out.println("Sending Acknowledge for " +j+"....");
		  out.write(j);
		  out.flush();
		  Thread.sleep(4000);
	    }
	}
	else if(choice == 1)
	{
		for(int j=0;j<size;j++)
	    {
		  frame=br.read();
		  if(frame==555)
		  {
			  System.out.println("The frame is with error....Sending Negative Acknowledment....");
			  out.write(j);
			 
		  }
		  else
		  {
	       System.out.println("The received Frames is" +frame+"....");
		   System.out.println("Sending Acknowledge for " +j+"....");
		   out.write(j);
		   
		   //Thread.sleep(4000);	  
		   }
		}
		out.flush();
	}
  }
}
