// Steven Nguyen 94591871
// Kishan Rajasekhar 57609613

/*
 * Class that repsents the passenger arrival behavior extracted from ElevatorConfig.txt. Called in ElevatorSimulation and assigned variables from there.
 */
public class PassengerArrival {
	
	// Private Variables
	private int numPassengers;
	private int destinationFloor;
	private int timePeriod;
	private int expectedTimeOfArrival;
	
	// Constructor
	public PassengerArrival(int numPassengers, int destinationFloor, int timePeriod) {
		this.numPassengers = numPassengers;
		this.destinationFloor = destinationFloor;
		this.timePeriod = timePeriod;
		this.expectedTimeOfArrival = timePeriod;
	}
	
	// GETTERS and SETTERS
	public int getNumPassengers() {
		return numPassengers;
	}
	public void setNumPassengers(int numPassengers) {
		this.numPassengers = numPassengers;
	}
	public int getDestinationFloor() {
		return destinationFloor;
	}
	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}
	public int getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(int timePeriod) {
		this.timePeriod = timePeriod;
	}
	public int getExpectedTimeOfArrival() {
		return expectedTimeOfArrival;
	}
	public void setExpectedTimeOfArrival(int expectedTimeOfArrival) {
		this.expectedTimeOfArrival = expectedTimeOfArrival;
	}
	
	// Prints passenger arrival data.
	public String toString() {
		return numPassengers + " " + destinationFloor + " " + timePeriod;
	}

}
