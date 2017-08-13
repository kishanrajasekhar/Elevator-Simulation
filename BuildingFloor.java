// Steven Nguyen 94591871
// Kishan Rajasekhar 57609613

/*
 * Keeps track of all the passengers and requests that come to and from this floor.
 */
public class BuildingFloor {

	// Private variables.
	private int[] totalDestinationRequests; // Total amount of people who left this floor and arrived at their destination floor.
	private int[] arrivedPassengers; // Total amount of people who came to this floor from other floors.
	private int[] passengerRequests; // Current number of people who want to get to a destination floor (but have not yet arrived).
	private int approachingElevator; // ID of elevator currently heading to this floor.
	
	// Constructor
	public BuildingFloor() {
		totalDestinationRequests = new int[5];
		arrivedPassengers = new int[5];
		passengerRequests = new int[5];
		approachingElevator = -1;
	}
	
	// Prints local floor data.
	public void printData(int floorNum) {
		System.out.println("Floor: " + floorNum);
		System.out.print("Total destination requests: ");
		for (int i=0; i<5; i++) {
			System.out.print(totalDestinationRequests[i] + " ");
		}
		System.out.println();
		System.out.print("Arrived Passengers: ");
		for (int i=0; i<5; i++) {
			System.out.print(arrivedPassengers[i] + " ");
		}
		System.out.println();
		System.out.print("Passenger Requests: ");
		for (int i=0; i<5; i++) {
			System.out.print(passengerRequests[i] + " ");
		}
		System.out.println();
		System.out.println("Approaching Elevator: " + approachingElevator);
		System.out.println();
	}
	
	// Given the number of passengers and a destination, update the values on this floor.
	public void addPassengersRequest(int numPassengers, int destination){
		passengerRequests[destination] += numPassengers;
		totalDestinationRequests[destination] += numPassengers;
	}
	
	//GETTERS and SETTERS
	public int[] getTotalDestinationRequests() {
		return totalDestinationRequests;
	}
	public void setTotalDestinationRequests(int[] totalDestinationRequests) {
		this.totalDestinationRequests = totalDestinationRequests;
	}
	public int[] getArrivedPassengers() {
		return arrivedPassengers;
	}
	public void setArrivedPassengers(int[] arrivedPassengers) {
		this.arrivedPassengers = arrivedPassengers;
	}
	public int[] getPassengerRequests() {
		return passengerRequests;
	}
	public void setPassengerRequests(int[] passengerRequests) {
		this.passengerRequests = passengerRequests;
	}
	public int getApproachingElevator() {
		return approachingElevator;
	}
	public void setApproachingElevator(int approachingElevator) {
		this.approachingElevator = approachingElevator;
	}
	
}
