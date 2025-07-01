package vpms.parking;

import vpms.model.Vehicle;

public class Ticket {
    private Vehicle vehicle;
    private long entryTime;

    public Ticket(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.entryTime = System.currentTimeMillis();
    }

    public long getEntryTime() { return entryTime; }
    public Vehicle getVehicle() { return vehicle; }
}
