package ds;
import java.net.*;
import java.io.*;

public class ComputerNode {

	public static void main(String args[]) {
		try {
			
			//create socket for the server node with port = 8250
			ServerSocket cn = new ServerSocket(8250);
			
			String bestRoute = "the";
			
			while(true) {
			
				// wait for request from the driver
				Socket cl = cn.accept();
				
				//create I/O Stream
				DataInputStream inCom = new DataInputStream(cl.getInputStream());
				DataOutputStream outCom = new DataOutputStream(cl.getOutputStream()); 
				
				while(true) {
					// u are connected
					outCom.writeUTF("you're connected, how can i help you ?");
					outCom.flush();
					
					//waiting for the request
					String recomRoute = "";
					do {
					recomRoute = inCom.readUTF();
					}while(recomRoute.isEmpty());
					if(recomRoute.equals("I want the recommended route")) {

					//ask for location
					outCom.writeUTF("Enter your location ..");
					
					//get the location
					String location = "";
					do {
					location = inCom.readUTF();
					}while(location.isEmpty());
					//ask for destenation
					outCom.writeUTF("Enter your destenation ..");
					
					//get the destenation
					String destenation = "";
					do {
						destenation = inCom.readUTF();
					}while(destenation.isEmpty());
					
					// collect the needed data 
					//.........................
					//......................
					
					try {
					Socket cc = new Socket("localhost",8000);
					//create I/O Stream
					DataInputStream inSC = new DataInputStream(cc.getInputStream());
					DataOutputStream outSC = new DataOutputStream(cc.getOutputStream()); 
					
					//recieve you are connected
					String req = inSC.readUTF();
					
					//send ask for recomended route 
					outSC.writeUTF("I want the recommended route");
					outSC.flush();
					
					//recieve give collecting data from the server node
					String rec = inSC.readUTF();
					
					//send the collected data to the server node
					outSC.writeUTF("From: "+location+" To: "+destenation+" and the collected data from sensors");
					outSC.flush();
					
					//recieving the best route and question to cont. from the server node
					bestRoute = inSC.readUTF();
					
					//send N for no from to server node
					outSC.writeUTF("N");
					outSC.flush();
					
					inSC.readUTF();
					
					}catch(IOException er) {
						System.out.println(er.getMessage());
					}
					
					//send the best route and question to cont. to the driver
					outCom.writeUTF(bestRoute);
					outCom.flush();
					
					String userChoice = inCom.readUTF();
					if(userChoice.equals("N")) {
						outCom.writeUTF("Bye Bye");
						outCom.flush();
						break;
					}
				}
							
				}
				inCom.close();
				outCom.close();
				cl.close();
				
			}
			
			
		}	
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			
		}
		
	}
	
	
	
}
