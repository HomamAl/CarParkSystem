import CarPark_managment.FloorManagement;

// This class works with the floor space data from the thread class to handle the thread locking  and floor spaces 
public class CarPark_state {
	
	// true a thread has a lock, false otherwise
	private boolean accessing = false; 
	// number of waiting writers
	@SuppressWarnings("unused")
	private int threadsWaiting = 0; 
	
	// Variable holds the floor space data to be accessed
	private FloorManagement floorSpace;
	
	// Constructor
	CarPark_state(FloorManagement floorSpaceData) {
		floorSpace = floorSpaceData;
	}
	
	
	// Acquire a lock
		public synchronized void acquireLock() throws InterruptedException{
			Thread clientID = Thread.currentThread(); // Get the thread clientID
			System.out.println();
		    System.out.println(clientID.getName()+" is attempting to acquire a lock.");	
		    ++threadsWaiting;
			  
		    while (accessing) {  // While someone else is accessing or threadsWaiting > 0
		    System.out.println(clientID.getName()+" waiting to get a lock as someone else is accessing...");
			// Wait for the lock to be released - see releaseLock() below
			  wait();
			}
		      
			// Nobody has got a lock so get one
			--threadsWaiting;
			accessing = true;
			System.out.println(clientID.getName()+" got a lock."); 
		}
		
		// Release the lock
		public synchronized void releaseLock(){
			// Release the lock and tell everyone
		    accessing = false;
		    notifyAll();
		    Thread clientID = Thread.currentThread(); // Get the thread clientID
		    System.out.println(clientID.getName()+" released a lock.");
		}
	
	// Method reports the current status of the spaces
	public void CarParkSpaceState() {
		floorSpace.spaceReport();
	}
	
	// Process the input sent to the server
	public synchronized String processInput(String input){
		String output = null;
		String parking_clientID = Thread.currentThread().getName();
		
		if(input.equalsIgnoreCase("E")){
			
			// The switch statement will help me to add more cases if I want to add more entrances and exits for testing
			switch(parking_clientID){
			
			// Entrance_1
			case "Entrance_1":
				output = floorSpace.reserveSpace();
				break;
				
			// Entrance_2	
			case "Entrance_2":
				output = floorSpace.reserveSpace();
				break;
			
			// Exit_1	
			case "Exit_1":
				output = floorSpace.leaveSpace(parking_clientID);
				break;
			
			// Exit_2
			case "Exit_2":
				output = floorSpace.leaveSpace(parking_clientID);
				break;
			}
			
			// Report the changes to the spaces
			floorSpace.spaceReport();
		}
			
		return output;
	}

}
