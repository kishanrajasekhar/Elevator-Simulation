// Steven Nguyen 94591871
// Kishan Rajasekhar 57609613

/*
 * Represents an event that the elevator is scheduled to perform.
 * Events in this case contain information including the expected time that the event will occur.
 * An event is only the elevator's destination floor and expected simulated arrival time.
 */
public class ElevatorEvent {
	
	// Private Variables
	private int destination;  // the destination floor
	private int expectedArrival; // time it will reach destination floor
	
	// Constructor
	ElevatorEvent(int destination, int expectedArrival) {
		this.destination = destination;
		this.expectedArrival = expectedArrival;
	}
	
	// GETTERS and SETTERS
	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	public int getExpectedArrival() {
		return expectedArrival;
	}
	public void setExpectedArrival(int expectedArrival) {
		this.expectedArrival = expectedArrival;
	}
	
	

}
