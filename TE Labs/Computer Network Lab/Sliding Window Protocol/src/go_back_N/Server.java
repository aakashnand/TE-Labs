package go_back_N;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws IOException, Exception {
		int choice,size;
		ServerSocket ss = new ServerSocket(5000);
		System.out.println("Waiting for Client......");
		Socket socket = ss.accept();
		System.out.println("Connected.....");
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		BufferedInputStream br = new BufferedInputStream(socket.getInputStream());
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the No of Packets");
		size=scan.nextInt();
		out.write(size);
		out.flush();
		boolean array[] =new boolean[size];
		System.out.print("Enter your choice\n1.Error\n2.no error");
		choice=scan.nextInt();
		out.write(choice);
		out.flush();
		int neg;
		if(choice==2)
		{
			for(int i=0;i<size;i++)
			{
				System.out.println("Sending frame " +i+".....");
				Thread.sleep(4000);
				out.write(i);
				out.flush();
				System.out.println("Received Acknowledge of " +i+"....." );
				Thread.sleep(4000);
			}
		}
		else if(choice==1)
		{
			for(int i=0;i<size;i++)
			{
				System.out.println("Sending frame " +i+".....");
				Thread.sleep(4000);
				if(i==size/2)
				{
					array[i]=false;
					out.write(555);
					
					int temp =br.read();
					System.out.println("received negitive Acknowledge of ....."+ temp );
				 	Thread.sleep(4000);
				//Continue from Here Error code.....
				}
				else
				{
				 array[i]=true;
				 out.write(i);
				
			     System.out.println("received Acknowledge of " +i+"....." );
			 	 Thread.sleep(4000);
				}
				 out.flush();
			}
		}
	}

}
