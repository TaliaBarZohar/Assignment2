//name: Talia Yarin BarZohar
//Id: 318257060
//name: Stav Sagi
//Id: 316584622
package Q1;

import java.util.ArrayList;
import java.util.Random;

public class WehicleWasher {
    //Vehicles waiting to be washed
    private double AverageVehicleArrivalTime;
    private double AverageCarWashTime;
    private ArrayList<Wehicle> queueList = new ArrayList<Wehicle>();

    //Sorting washed vehicles by type
    private ArrayList<Car> washedCar = new ArrayList<Car>();
    private ArrayList<Truck> washedTruck = new ArrayList<Truck>();
    private ArrayList<MiniBus> washedMiniBuse = new ArrayList<MiniBus>();
    private ArrayList<SUV> washedSUV = new ArrayList<SUV>();

    //array of Washing stations
    private Wehicle[] washingStations;

    //Parameter constructor
    public WehicleWasher(double AverageVehicleArrivalTime, double AverageCarWashTime, int numWashingStations) {
        washingStations = new Wehicle[numWashingStations];
        this.AverageCarWashTime = AverageCarWashTime;
        this.AverageVehicleArrivalTime = AverageVehicleArrivalTime;
    }
    
    //Poisson function
    public static double poisson(double avgTime) {
        Random randomGenerator = new Random();
        double nextTime = -((Math.log(randomGenerator.nextDouble())) / avgTime);
        return nextTime * 1000;
    }

    //addToQueue function - A function that adds the cars to the waiting list for washing
    public void addToQueue(Wehicle wehicle) throws InterruptedException {
        try {
            Thread.sleep((long) poisson(AverageVehicleArrivalTime));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //At a time only one thread can enter the waiting position
        synchronized (this) {
        	queueList.add(wehicle);
        }

        //Wait until the washing station becomes available
        washingStation();
    }

    //washingStation function
    public void washingStation() {
        while (true) {
            for (int i = 0; i < washingStations.length; i++) {
                //If a wash position is null, the next vehicle enter
                if (washingStations[i] == null) {
                	washNextWehicle(i);
                    try {
                        Thread.sleep((long) poisson(AverageCarWashTime));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return;
                }
            }
            try {
                synchronized (this) {
                    //If there is no free washing station, wait 
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    //Remove from the queue
    public synchronized Wehicle removeFromQueueList() {
        return queueList.remove(0);
    }

    //WashNext function - if the queue is empty, return
    public void washNextWehicle(int station) {
        if (queueList.size() == 0) {
            return;
        }
        //Remove the next vehicle from the queue and wash it
        Wehicle wehicle = removeFromQueueList();
        //Add the vehicle to the washing station
        washingStations[station] = wehicle;
        //Free the washing station
        washingStations[station] = null;
        wehicle.wash();
        //Add the vehicle to the correct list
        if (wehicle instanceof Truck) {
            washedTruck.add((Truck) wehicle);
        } else if (wehicle instanceof MiniBus) {
            washedMiniBuse.add((MiniBus) wehicle);
        } else if (wehicle instanceof SUV) {
            washedSUV.add((SUV) wehicle);
        } else if (wehicle instanceof Car) {
            washedCar.add((Car) wehicle);
        }

        synchronized (this) {
            // Notify all threads that a washing station is free
            notifyAll();
        }
    }
    //printWash function
    public void printWash (){
        System.out.println(washedCar+"\n"+washedSUV+"\n"+washedTruck+"\n"+washedMiniBuse);
    }
}

    


