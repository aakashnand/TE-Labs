import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {

	public static void main(String[] args) throws Exception {
		
		Socket s=new Socket("127.0.0.1",4001);
        PrintWriter out=new PrintWriter(s.getOutputStream(),true);
        BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println("Enter the String ");
        	//out.println(br.readLine());
        	System.out.println(br.readLine());	
       
        
	}

}
