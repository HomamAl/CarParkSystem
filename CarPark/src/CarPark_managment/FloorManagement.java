package CarPark_managment;

// Management class which manage the spaces in the car park floors
public class FloorManagement {
	
	// Creating the floor object
	private class Floor {
		
		// There is 3 spaces in each  floor
		private int space = 3;
	}
	
	// Private objects for the ground and first floor
	private Floor groundFloor = new Floor();
	private Floor firstFloor = new Floor();
	
	// Queue for entrance is set to 0 at the start
	private int queue = 0;
	
	private int spaceChecker(){
		// Free space = 6 at the start
		int freeSpace = 6;
		
		
		if(groundFloor.space > 0){
			freeSpace = 0; 
		}else 
			{
			     
				if(firstFloor.space > 0){
					freeSpace = 1; 
				}
			}
		return freeSpace;
	}
	
	// Car Parking handler
	public String reserveSpace(){
		String action = "No spaces available at the moment, adding the car to the queue";
		
		// If there is space in the ground floor let one car enter
		// If there is any car in the queue decrement the queue 
		if(spaceChecker() == 0){
			if(queue > 0){
				--queue;	
			}
			--groundFloor.space;
			action = "Parking on ground floor";
		}
		
		else if(spaceChecker() == 1){
			if(queue > 0){
				--queue;	
			}
			--firstFloor.space;
			action = "Parking on first floor";
			
		// If there is no space in the car park add the car to the queue 
		}else {
			++queue;
		}
	
		return action;
	}
	
	/*
	 * Leave a space from the ground or first floor,
	 * exit used depends on which floor the car is parked
	 */
	
	// When the car exit, it will leave a space 
	public String leaveSpace(String clientID){
		String action = "Floor is empty";
		
		// If the car uses Exit_1 Client  
		if(clientID.equals("Exit_1")){
			
			// Exit from the ground floor exits
			if(groundFloor.space < 3){
				++groundFloor.space;
				action = "Leaving ground floor";
			}
			
		// else if the car uses Exit_2 Client  
		}else if(clientID.equals("Exit_2")){
			
			// Exit from the first floor exits
			if(firstFloor.space < 3){
				++firstFloor.space;
				action = "Leaving first floor";
			}
		}
		
		// When there is no more cars return action
		return action;
	}
	
	// Report on the spaces in the car park
	public void spaceReport(){
		
		System.out.println("*****************************");
		System.out.println("Welcome to Homzino car park, we have 2 floors in our car park that can fit 6 cars.");
		System.out.println("Hope you have fun :)");
		System.out.println("*****************************");
		System.out.println("Please use the first floor parking only for emergencies.");
		System.out.println("Cars only allowed to park for 2 hours maximum in the ground floor");
		System.out.println("*****************************");
		System.out.println("Current car park spaces: ");
		System.out.println("Spaces available in the Ground floor: " + groundFloor.space);
		System.out.println("Spaces available in the First floor: " + firstFloor.space);
		System.out.println("Current queue waiting queue: " + queue);
		System.out.println("Expected waiting time: " + queue * 120  + " Minutes, please be patient!! ");
		System.out.println("*****************************");

	}
	

}