import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

// Entrance client one class 
public class Enterance1Client {
	
	// Running the client
	@SuppressWarnings("resource")
	public static void main(String [] args) throws IOException{
		
		// Create the first client socket
		Socket parking_clientSocket = null;
		// I/O
		PrintWriter out = null;
		BufferedReader in = null;
		//Entrance 1 client
		String parking_clientName = "Entrance 1";
		int parking_clientPort = 7070;
		String carPark_serverName = "localhost";
		
		
		
		// Start up the client on the parking_clientPort
		try{
			parking_clientSocket = new Socket(carPark_serverName, parking_clientPort);
			out = new PrintWriter(parking_clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(parking_clientSocket.getInputStream()));
		}
		
		// If an error occurs in the try statement 
		catch(UnknownHostException e){
			System.err.println("Don't know anything about host: localhost ");
			System.exit(1);
		}
		
		// If an error occurs in the try statement 
		catch(IOException e){
			System.err.println("Couldn't get I/O for the connection to " + parking_clientPort);
			System.exit(1);
		}
		
		// Take the input from the Eclipse console 
		BufferedReader user_input = new BufferedReader(new InputStreamReader(System.in));
		String fromServer;
		String fromUser;
		
		System.out.println("Initialised " + parking_clientName + " client and I/O connections.");
		
		
		// Step 1: The client communicates with the server on key press
		// Step 2: Key press means that a car arrive at the entrance and request to park in one of the six parking spaces
		// Step 3: This request will be sent to the server to check if the car park is empty or full 
		// Step 4: The client will wait for a response from the server.
		
		
		// While the enterance1client is live and running listen for an event  
		// Let the client speak first 
		while(true){
			
			fromUser = user_input.readLine();
			if(fromUser != null){
				// Send client input to server
				out.println(fromUser);
			}
			
			// Read and print server response to client window
			fromServer = in.readLine();
			System.out.println(carPark_serverName+":" + fromServer);
		}
	}

}