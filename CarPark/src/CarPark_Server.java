// Importing some functionalities from the CarPark_managment package
import CarPark_managment.FloorManagement; 
import java.io.IOException;
import java.net.ServerSocket;


// The server class will be used to manage the parking system and listen to the clients
public class CarPark_Server {
	
	public static void main(String [] args) throws IOException {
		
		
		// Making the server socket
		ServerSocket CarPark_serverSocket = null;
		String serverName = "Car Park Server";
		int serverPort = 7070;	
		// If the server is listening
		boolean listening = true;
				
		// Creating a floor space object
		FloorManagement floorSpace = new FloorManagement();
		// Creating a the floor space data object in global scope
		CarPark_state floorSpaceData = new CarPark_state(floorSpace);
		
		
		// Start up the server on serverPort
		try{
			CarPark_serverSocket = new ServerSocket(serverPort);
		}
		// If an error occurs try...
		catch(IOException e){
			System.err.println("Could not start " +serverName+ " on port: " + serverPort);
			System.exit(-1);
		}
		// If there is no errors print...
		System.out.println(serverName+ " is running:");
		
		// Print the spaces available to the server window
		floorSpaceData.CarParkSpaceState();
		
	    //Got to do this in the correct order with only four clients!  Can automate this...
		while(listening){
			// Run the entrance 1 & 2 client threads
			new CarPark_thread(CarPark_serverSocket.accept(), "Entrance_1", floorSpaceData).start();
			new CarPark_thread(CarPark_serverSocket.accept(), "Entrance_2", floorSpaceData).start();
			
			// Run the exit 1 & 2 client threads
			new CarPark_thread(CarPark_serverSocket.accept(), "Exit_1", floorSpaceData).start();
			new CarPark_thread(CarPark_serverSocket.accept(), "Exit_2", floorSpaceData).start();

			
		}
		
		// Close connection
		CarPark_serverSocket.close();
	}
}
