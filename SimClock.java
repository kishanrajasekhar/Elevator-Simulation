// Steven Nguyen 94591871
// Kishan Rajasekhar 57609613

/*
 * Class used to represent the simulated time.
 */
public class SimClock {
	
	// Private Variables
	private static int simulatedTime;
	
	// Constructor: Initializes simulated time to 0.
	public SimClock(){
		simulatedTime = 0;
	}
	
	// Increments the simulated time by 1.
	public static void tick(){
		simulatedTime++;
	}

	// Returns the value of the simulated time.
	public synchronized static int getSimulatedTime() {
		return simulatedTime;
	}
	
}
