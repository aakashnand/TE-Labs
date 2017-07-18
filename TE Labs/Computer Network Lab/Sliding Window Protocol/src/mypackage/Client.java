package mypackage;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws Exception, IOException {
		int j;
		Socket s = new Socket("127.0.0.1", 5000);
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);
        BufferedInputStream br = new BufferedInputStream(s.getInputStream());
        //out.flush();
        int no= br.read();
        System.out.println("The no frames from Server are:" + no);
        System.out.println("The Frames received Are as follows:");
        int arr[] =new int[no];
        for(int i =0;i<no;i++)
        {
        	arr[i]=br.read();
        	System.out.println(arr[i]);
        }
        arr[2]=9999;
        for (j=0;j<arr.length;j++)
        {
        	if(arr[j]==9999)
        	{
        		System.out.println("The frame no "+(j+1)+ "is errorful please send it again");
        		System.out.println("Requesting Server to resend the frame....");
        		out.write(j);
        		out.flush();
        		break;
        	}
        }
        
        arr[j]=br.read(); 
        System.out.println("Retransmitted frame is "+ arr[j]);
        System.out.println("Quitting........");
	}

}
