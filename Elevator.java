// Steven Nguyen 94591871
// Kishan Rajasekhar 57609613

import java.util.ArrayList;

/*
 * Class representing en Elevator and its behavior. Implements Runnable interface to be ran in its own thread.
 */
public class Elevator implements Runnable{
	
	// Private Variables
	private int elevatorID;
	private int currentFloor;
	private int numPassengers;
	private int totalLoadedPassengers;
	private int totalUnloadedPassengers;
	private ArrayList<ElevatorEvent> moveQueue;
	private int[] passengerDestinations;
	private BuildingManager manager;
	
	// Constructor
	public Elevator(int elevatorID, BuildingManager manager){
		this.setElevatorID(elevatorID);
		currentFloor = 0;
		numPassengers = 0;
		totalLoadedPassengers = 0;
		totalUnloadedPassengers = 0;
		moveQueue = new ArrayList<ElevatorEvent>();
		passengerDestinations = new int[5];
		this.setManager(manager);
	}
	
	// Runs an elevator in a separate thread. The elevator is responsible for looping through and handling elevator events in the movequeue.
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			if(moveQueue.size() == 0){
				createElevatorEvent();
			}
			else if (moveQueue.size() > 0) {
				ElevatorEvent event = moveQueue.get(0);
				manager.setApproachingElevator(elevatorID, event.getDestination());
				if(SimClock.getSimulatedTime() == event.getExpectedArrival()) {
					manager.updateFloor(event.getDestination(), currentFloor, passengerDestinations[event.getDestination()]);
					numPassengers -= passengerDestinations[event.getDestination()];
					totalUnloadedPassengers += passengerDestinations[event.getDestination()];
					passengerDestinations[event.getDestination()] = 0;

					if(moveQueue.size() == 1){
						currentFloor = event.getDestination(); 
					}
					moveQueue.remove(0);
					System.out.println("\tTime " + SimClock.getSimulatedTime()+ ": E[" + elevatorID + "] has arrived at floor " + event.getDestination() + " compared to the ETA: " + event.getExpectedArrival());
					System.out.println("\tElevator[" + elevatorID + "]: Number Passengers = " + numPassengers + " Total Loaded = " + totalLoadedPassengers + " Total Unloaded = " + totalUnloadedPassengers);
				}
			}
			manager.resetElevator(currentFloor);

		}
		
	}

	// Loop through the floors and make elevator events if there are passengers waiting.
	public void createElevatorEvent() {
		int startingFloor = currentFloor;
		for (int floor=startingFloor; floor<5; floor++) {
			manager.makeEvent(this, floor);
			if (moveQueue.size() > 0) {
				currentFloor = floor;
				return;
			}
		}	
		for (int floor=startingFloor; floor>=0; floor--) {
			manager.makeEvent(this, floor);
			if (moveQueue.size() > 0) {
				currentFloor = floor;
				return;
			}
		}
	}
	
	// GETTERS and SETTERS
	public int getElevatorID() { return elevatorID; }
	public void setElevatorID(int elevatorID) { this.elevatorID = elevatorID; }
	public int getCurrentFloor() { return currentFloor; }
	public void setCurrentFloor(int currentFloor) { this.currentFloor = currentFloor; }
	public int getNumPassengers() { return numPassengers; }
	public void setNumPassengers(int numPassengers) { this.numPassengers = numPassengers; }
	public int getTotalLoadedPassengers() { return totalLoadedPassengers; }
	public void setTotalLoadedPassengers(int totalLoadedPassengers) { this.totalLoadedPassengers = totalLoadedPassengers; }
	public int getTotalUnloadedPassengers() { return totalUnloadedPassengers; }
	public void setTotalUnloadedPassengers(int totalUnloadedPassengers) { this.totalUnloadedPassengers = totalUnloadedPassengers; }
	public ArrayList<ElevatorEvent> getMoveQueue() { return moveQueue; }
	public void setMoveQueue(ArrayList<ElevatorEvent> moveQueue) { this.moveQueue = moveQueue; }
	public int[] getPassengerDestinations() { return passengerDestinations; }
	public void setPassengerDestinations(int[] passengerDestinations) { this.passengerDestinations = passengerDestinations; }
	public BuildingManager getManager() { return manager; }
	public void setManager(BuildingManager manager) { this.manager = manager; }

}
