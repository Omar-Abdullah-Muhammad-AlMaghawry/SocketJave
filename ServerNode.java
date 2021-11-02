package ds;
import java.net.*;
import java.io.*;

public class ServerNode {

	public static void main(String args[]) {
		try {
			//create socket for the server node with port = 8000
			ServerSocket sn = new ServerSocket(8000);
			
			
			while(true) {
				//wait and listen
				Socket client  = sn.accept(); 
				
				//create the I/O streams
				DataInputStream inS = new DataInputStream(client.getInputStream());
				DataOutputStream outS = new DataOutputStream(client.getOutputStream());
				
				while(true) {
				// iv) implement the ALP 
				//a) how can i help you?
				outS.writeUTF("you're connected, how can i help you ?");
				outS.flush();
				
				//b) recieve a request "to get best route"
				String request = inS.readUTF();
				if(request.equals("I want the recommended route")) {
				// c) ask for data collected from sensors
					outS.writeUTF("Give me the collected data");
					outS.flush();
				
					// d) recieving the collected data
					String collectedData = inS.readUTF();
					
					//apply process on the collected data
					//................................
					//.............................
					// getting the best route
				
					// send the best route and asking for ending the program
					outS.writeUTF(collectedData+" depend on it"+"The best route is ..., "+"Do you want to get another route [Y/N] ?");
					outS.flush();
					
					//getting the the decide
					String userChoice = inS.readUTF();
					
					if(userChoice.equals("N")) {
						outS.writeUTF("Bye Bye");
						outS.flush();
						break;
						}
					}
				}
				
				inS.close();
				outS.close();
				client.close();
			
			}
		}
			
	
		catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
	
	}
}
