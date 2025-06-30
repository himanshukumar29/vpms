package vpms.parking;

import vpms.model.Vehicle;

public class Ticket {
    private Vehicle vehicle;
    private long entryTime;
    private ParkingSpot spot;

    public Ticket(Vehicle vehicle, ParkingSpot spot) {
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = System.currentTimeMillis();
    }

    public Vehicle getVehicle() { return vehicle; }
    public long getEntryTime() { return entryTime; }
    public ParkingSpot getSpot() { return spot; }
}