import java.net.*;
import java.io.*;

/*
 * Thread initialises new client-server processes, a new thread
 * is created for each client when it connects to the server.
 * 
 */

//
public class CarPark_thread extends Thread {
	
	private Socket parking_clientSocket = null;
	private String parking_clientID;
	private CarPark_state floorSpaceData;

	// Setup the thread with a Constructor for launching new client-server processes
	public CarPark_thread(Socket parking_clientSocket, String parking_clientID, CarPark_state floorSpaceData){
		super(parking_clientID);
		this.parking_clientSocket = parking_clientSocket;
		this.floorSpaceData = floorSpaceData;
	}
	
	// Define what the thread does when running
	public void run(){
		try {
			
			// Get the current threads parking_clientID
			parking_clientID = Thread.currentThread().getName();
			System.out.println(parking_clientID + " client thread initialising");
			
			// Create I/O
			PrintWriter out = new PrintWriter(parking_clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(parking_clientSocket.getInputStream()));
			String inputLine; 
			String outputLine;
			
			
			while((inputLine = in.readLine()) != null){
				
				// 1. Get a lock first
				// 2. Process client input and send response to client
				// 3. Do action
				// 4. Print out to server window
				try{
					
				// 1.	
				floorSpaceData.acquireLock();
				// 2.
				outputLine = floorSpaceData.processInput(inputLine);
				// 3.
				out.println(outputLine);
				floorSpaceData.releaseLock();
				// 4.
				System.out.println(outputLine);
				
				// If the user input end, break the server
				if(inputLine.equalsIgnoreCase("end")){
					break;
				}
				
				// If an error occurs in the try statement 
				}catch(InterruptedException e){
					System.out.println("failed to get lock");
				}
			}
			out.close();
			in.close();
			parking_clientSocket.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
