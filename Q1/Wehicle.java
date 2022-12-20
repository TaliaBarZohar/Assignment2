//name: Talia Yarin BarZohar
//Id: 318257060
//name: Stav Sagi
//Id: 316584622
package Q1;

public abstract class Wehicle implements Runnable {
    private WehicleWasher washer;

    public void run() {
        //If the washer is null, throw an exception
        if (washer == null) {
            throw new RuntimeException("Washer is null");
        }
        log("Add to queue");

        //Add the vehicle to the queue
        try {
            washer.addToQueue(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    //Wash function
    public void wash() {
        log("Washing");
        log("Washed");
    }

    //setWasher function 
    public void setWasher(WehicleWasher washer) {
        this.washer = washer;
    }

    //WehicleWasher function
    public WehicleWasher getWasher() {
        return washer;
    }

    private void log(String message) {
        // Log the message with the vehicle type
        System.out.println(message + "Vehicle type: " + getClass().getSimpleName());
    }
}
    


