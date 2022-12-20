//name: Talia Yarin BarZohar
//Id: 318257060
//name: Stav Sagi
//Id: 316584622
package Q1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Collect from the user the amount of washing stations
		System.out.print("Insert the number of wash stations: ");
		int numberVehicles = scanner.nextInt();
		// Collect the number of vehicles from the user
		System.out.print("Insert the number of vehicles: ");
		int numberWashingStations = scanner.nextInt();
		// Receive from the user the average time of a vehicle to arrive
		System.out.print("Insert the average time for a vehicle to arrive: ");
		double averageTimeAVehicleToArrive = scanner.nextDouble();
		// Collect the average time for washing vehicles from the user
		System.out.print("Insert the average time for washing a vehicle: ");
		double averageTimeWashingAVehicle = scanner.nextDouble();

		// Creating a car wash facility
		WehicleWasher washer = new WehicleWasher(averageTimeAVehicleToArrive, averageTimeWashingAVehicle,numberVehicles);

		// Create a thread array of number washing stations
		Thread[] threads = new Thread[numberWashingStations];

		// Create a random vehicle
		for (int i = 0; i < numberWashingStations; i++) {
			Wehicle wehicle = randomVehicle();

			// Set the washer and time to wash
			wehicle.setWasher(washer);

			// Create a thread and start it
			Thread thread = new Thread(wehicle);
			threads[i] = thread;
			thread.start();
		}

		// Wait for all threads to finish
		for (int i = 0; i < numberVehicles; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
			}
		}
		scanner.close();
	}

	// A function for random selection of the vehicle type
	public static Wehicle randomVehicle() {
		int random = (int) (Math.random() * 4);
		if (random == 0)
			return new SUV();
		else if (random == 1)
			return new Car();
		else if (random == 2)
			return new MiniBus();
		else
			return new Truck();
	}

}
