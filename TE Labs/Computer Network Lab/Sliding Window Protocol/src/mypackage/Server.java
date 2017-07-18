package mypackage;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(5000);
		System.out.println("Waiting for Client..........");
        Socket socket = ss.accept();
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        BufferedInputStream br =new BufferedInputStream(socket.getInputStream());
        System.out.println("Connected");
        int arr[]={1,2,3,4,5};
        System.out.println("The No Packets to be send is "+ arr.length);
        out.write(arr.length);
        //out.flush();
        for(int i =0 ;i<arr.length;i++)
        {
        	out.write(arr[i]);
        	out.flush();
        }
        int error_index=br.read();
        System.out.println("Resending the Packet no" +error_index+".......");
        out.write(arr[error_index]);
        out.flush();
        System.out.println("Quitting.......");
	}

}
