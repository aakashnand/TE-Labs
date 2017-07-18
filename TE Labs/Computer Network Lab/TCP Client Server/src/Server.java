import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public  static void main(String[] args) throws Exception
	{
		ServerSocket ss =new ServerSocket(4001);
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Socket socket = ss.accept();
		PrintWriter out =new PrintWriter(socket.getOutputStream(),true);
		System.out.println("Connected");
		System.out.println("Enter the String To be Send");
			out.println(br.readLine());
			System.out.println(br.readLine());
		   
		
		
	}
	
}
