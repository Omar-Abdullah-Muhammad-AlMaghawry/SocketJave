package ds;
import java.net.*;
import java.io.*;
import java.util.*;

public class DriverNode {

	public static void main(String args[]) {
		
		Scanner sc =new Scanner(System.in);
		try {
			//create socket and connect to the computer node
			//with ip :
			//and with port number = 8250
			Socket driver = new Socket("localhost",8250);
			
			//create I/O stream
			DataInputStream inDr= new DataInputStream(driver.getInputStream());
			DataOutputStream outDr = new DataOutputStream(driver.getOutputStream());
			
			//perfom the ALP for iv 
			while (true) {
				String comMsg= inDr.readUTF();
				if(comMsg.equals("Bye Bye")) {
					System.out.println("Session ended");
					break;
				}
				//output to user the arrived message
				System.out.println(comMsg);
				
				//get from the user the answer
				String usrMsg = "";
				do {
				 usrMsg =sc.nextLine();
				}while(usrMsg.isEmpty());
				//send to computer node the answer
				outDr.writeUTF(usrMsg);
				outDr.flush();
				
			}
			outDr.close();
			inDr.close();
			driver.close();
			
		}catch (Exception ee) {
			// TODO: handle exception
			System.out.println(ee.getMessage());

		}
		
	}
	
	
	
}
