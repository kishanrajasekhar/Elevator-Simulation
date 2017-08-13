// Steven Nguyen 94591871
// Kishan Rajasekhar 57609613


/*
 * Keeps track of all the floors' values. Elevator uses this class to update the values.
 */
public class BuildingManager {
	
	// Private Variables
	private BuildingFloor[] floors;
	
	// Constructor
	public BuildingManager() {
		floors = new BuildingFloor[5];
		floors[0] = new BuildingFloor();
		floors[1] = new BuildingFloor();
		floors[2] = new BuildingFloor();
		floors[3] = new BuildingFloor();
		floors[4] = new BuildingFloor();
	}
	
	/*
	 * Searches the floors of the building and creates elevator events based on the encountered passengerArrival object values on a specific floor.
	 * Updates the necessary elevator values such as passenger, total loaded passengers, total unloaded passengers, and sets the approaching floor of a floor as well.
	 */
	public synchronized void makeEvent(Elevator e, int floor) {
		int approachingElevatorStatus = floors[floor].getApproachingElevator();
		boolean pickedUp = false;
		if(approachingElevatorStatus != -1) { return; }
		// Search for passengers starting with this floor and going up.
		for (int destination=floor+1;destination<floors.length;destination++) {
			int passengers = floors[floor].getPassengerRequests()[destination];
			if (passengers > 0) {
				e.getMoveQueue().add(new ElevatorEvent(destination, SimClock.getSimulatedTime() + (Math.abs(destination%5-floor))*5 + 10));
				e.getPassengerDestinations()[destination] = passengers;
				e.setNumPassengers(e.getNumPassengers() + passengers);
				e.setTotalLoadedPassengers(e.getTotalLoadedPassengers() + passengers);
				floors[floor].getPassengerRequests()[destination] = 0;
				floors[floor].setApproachingElevator(e.getElevatorID());
				pickedUp = true;
					System.out.print("Time "+SimClock.getSimulatedTime()+": E[" + e.getElevatorID() + "] has found " + passengers + " passengers going from floor " + floor + " to " + destination);
					System.out.println(". ETA is " + (SimClock.getSimulatedTime() + (Math.abs(destination%5-floor))*5 + 10) );
					System.out.println("Elevator[" + e.getElevatorID() + "]: Number passengers = " + e.getNumPassengers() + " Total Loaded = " + e.getTotalLoadedPassengers() + " Total Unloaded = " + e.getTotalUnloadedPassengers());
			}
		}
		// If we find passengers on the current floor or on the floors above it, we don't need to search down the floors.
		if (pickedUp) { return; }
		// Search for passengers starting with this floor and going down.
		for (int destination=floor-1; destination>=0; destination--) {
			int passengers = floors[floor].getPassengerRequests()[destination];
			if (passengers > 0) {
				e.getMoveQueue().add(new ElevatorEvent(destination, SimClock.getSimulatedTime() + (Math.abs(destination%5-floor))*5 + 10));
				e.getPassengerDestinations()[destination] = passengers;
				e.setNumPassengers(e.getNumPassengers() + passengers);
				e.setTotalLoadedPassengers(e.getTotalLoadedPassengers() + passengers);
				floors[floor].getPassengerRequests()[destination] = 0;
				floors[floor].setApproachingElevator(e.getElevatorID());
					System.out.print("Time "+SimClock.getSimulatedTime()+": E[" + e.getElevatorID() + "] has found " + passengers + " passengers going from floor " + floor + " to " + destination);
					System.out.println(". ETA is " + (SimClock.getSimulatedTime() + (Math.abs(destination%5-floor))*5 + 10) );
					System.out.println("Elevator[" + e.getElevatorID() + "]: Number passengers = " + e.getNumPassengers() + " Total Loaded = " + e.getTotalLoadedPassengers() + " Total Unloaded = " + e.getTotalUnloadedPassengers());
			}
		}
	}
	
	public BuildingFloor[] getFloors() {
		return floors;
	}
	public synchronized void updateFloor(int destination, int originalFloor, int numPassengers) {
		floors[originalFloor].getArrivedPassengers()[destination] += numPassengers;
		floors[originalFloor].getPassengerRequests()[destination] = 0;
	}
	public synchronized void updatePassengers(int floor, int numPassengers, int destination){
		getFloors()[floor].addPassengersRequest(numPassengers, destination);
	}
	public synchronized void setApproachingElevator(int id, int dest) {
		floors[dest].setApproachingElevator(id);
	}
	public synchronized void resetElevator(int currentFloor) {
		floors[currentFloor].setApproachingElevator(-1);
	}
	
//	public void updateTotalDestinationRequests(BuildingFloor floor, int[] totalDestinationRequests) { floor.setTotalDestinationRequests(totalDestinationRequests); }
//	public void updateArrivedPassengers(BuildingFloor floor, int[] arrivedPassengers) { floor.setArrivedPassengers(arrivedPassengers); }
//	public void updatePassengerRequests(BuildingFloor floor, int[] passengerRequests) { floor.setPassengerRequests(passengerRequests); }
//	public void updateApproachingElevator(BuildingFloor floor, int approachingElevator) {
//		if (approachingElevator != -1) { 
//			floor.setApproachingElevator(approachingElevator);
//		} else { 
//			floor.setApproachingElevator(-1); 
//		}
//		/*
//		 * Elevator ID that is currently heading to the floor for passenger pickup.
//		 * Value could be -1 when there is currently no elevator heading in this direction.
//		 */
//	}
	
}
