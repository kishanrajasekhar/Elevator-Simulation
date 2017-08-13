// Steven Nguyen 94591871
// Kishan Rajasekhar 57609613

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ElevatorSimulation {
	
	// Private Variables
	// File IO
	private final File filename = new File("ElevatorConfig.txt");
	private Scanner scanner;
	// Elevators and Building Manager
	private Thread t0;
	private Thread t1;
	private Thread t2;
	private Thread t3;
	private Thread t4;
	private Elevator[] elevatorList;
	private BuildingManager manager;
	// Time
	private int totalSimulationTime;
	private int simulatedSecondRate;
	// ArrayList of passengerArrivals objects for each floor.
	private ArrayList<ArrayList<PassengerArrival>> passengerArrival;
	
	// Builds the threads, starts them, and ends them when the time is up.
	public void start() {
		try {
			manager = new BuildingManager();
			definePassengerArrival();
			createElevatorThreads();
			runSimulation();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			endElevatorThreads();
		}
	}
	
	public void runSimulation() throws InterruptedException{
		while (SimClock.getSimulatedTime() <= totalSimulationTime) {
			for (int i=0; i<passengerArrival.size();i++) {
				for (int j=0;j<passengerArrival.get(i).size();j++) {
					PassengerArrival pa = passengerArrival.get(i).get(j);
					if (SimClock.getSimulatedTime() % pa.getExpectedTimeOfArrival() == 0 && SimClock.getSimulatedTime() != 0){
						manager.updatePassengers(i, pa.getNumPassengers(), pa.getDestinationFloor());
					}
				} 
//				if (SimClock.getSimulatedTime() % 40 == 0 ) {
//					System.out.println("Time " + SimClock.getSimulatedTime());
//					manager.getFloors()[i].printData(i);
//				}
			}
//			if (SimClock.getSimulatedTime() % 100 == 0){
//				System.out.println("Time: " + SimClock.getSimulatedTime());
//				printBuildingState();
//				System.out.println();
//			}
			Thread.sleep(simulatedSecondRate);
			SimClock.tick();
		}
	}
	
	/*
	 * For each floor in building keep track of:
	 * 1. total number of passengers requesting elevator access on the floor, defined by ElevatorConfig.txt file behavior
	 * 2. total number of passengers that exited an elevator to the floor
	 * 3. current number of passengers waiting for an elevator on the floor
	 * 4. elevator currently heading towards the floor for passenger pickup
	 */
	/*
	 * For each elevator:
	 * 1. total number of passengers that entered the elevator throughout simulation
	 * 2. total number of passengers that exited this elevator on a specific floor
	 * 3. current number of passengers heading to any floor
	 */
	public void printBuildingState() {
		System.out.println("Elevator 0: \n" +
		"Total Loaded Passengers: " + elevatorList[0].getTotalLoadedPassengers() +
		" Total Unloaded Passengers: " + elevatorList[0].getTotalUnloadedPassengers() +
		" Current Passengers: " + elevatorList[0].getNumPassengers());
		System.out.println("Elevator 1: \n" +
		"Total Loaded Passengers: " + elevatorList[1].getTotalLoadedPassengers() +
		" Total Unloaded Passengers: " + elevatorList[1].getTotalUnloadedPassengers() +
		" Current Passengers: " + elevatorList[1].getNumPassengers());
		System.out.println("Elevator 2: \n" +
		"Total Loaded Passengers: " + elevatorList[2].getTotalLoadedPassengers() +
		" Total Unloaded Passengers: " + elevatorList[2].getTotalUnloadedPassengers() +
		" Current Passengers: " + elevatorList[2].getNumPassengers());
		System.out.println("Elevator 3: \n" +
		"Total Loaded Passengers: " + elevatorList[3].getTotalLoadedPassengers() +
		" Total Unloaded Passengers: " + elevatorList[3].getTotalUnloadedPassengers() +
		" Current Passengers: " + elevatorList[3].getNumPassengers());
		System.out.println("Elevator 4: \n" +
		"Total Loaded Passengers: " + elevatorList[4].getTotalLoadedPassengers() +
		" Total Unloaded Passengers: " + elevatorList[4].getTotalUnloadedPassengers() +
		" Current Passengers: " + elevatorList[4].getNumPassengers());
	}
	
	// Read from ElevatorConfig.txt and create passenger arrival objects from the information.
	public void definePassengerArrival() {
		passengerArrival = new ArrayList<ArrayList<PassengerArrival>>();
		String[] floorLine;
		String[] passengerArrivalLine;
		ArrayList<PassengerArrival> tempArray;
		try {
			scanner = new Scanner(filename);
			totalSimulationTime = Integer.parseInt(scanner.nextLine());
			simulatedSecondRate = Integer.parseInt(scanner.nextLine());
			while(scanner.hasNextLine()) {
				floorLine = scanner.nextLine().split(";");
				tempArray = new ArrayList<PassengerArrival>();
				for (int i=0; i<floorLine.length; i++) {
					passengerArrivalLine = floorLine[i].split(" ");
					int numPeople = Integer.parseInt(passengerArrivalLine[0]);
					int floorDestination = Integer.parseInt(passengerArrivalLine[1]);
					int requestRate = Integer.parseInt(passengerArrivalLine[2]);
					tempArray.add(new PassengerArrival(numPeople, floorDestination, requestRate));
				}
				passengerArrival.add(tempArray);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	// Create and start threads for five elevators.
	public void createElevatorThreads() {
		elevatorList = new Elevator[5];
		for(int i=0; i<5; i++){
			elevatorList[i] = new Elevator(i, manager);
		}
		t0 = new Thread(elevatorList[0]);
		t1 = new Thread(elevatorList[1]);
		t2 = new Thread(elevatorList[2]);
		t3 = new Thread(elevatorList[3]);
		t4 = new Thread(elevatorList[4]);
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	
	// End elevator threads.
	public void endElevatorThreads() {
		t0.interrupt();
		t1.interrupt();
		t2.interrupt();
		t3.interrupt();
		t4.interrupt();
	}
}
