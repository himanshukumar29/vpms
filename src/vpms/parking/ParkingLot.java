package vpms.parking;

import vpms.model.Vehicle;
import java.util.*;

public class ParkingLot {
    private TreeSet<ParkingSpot> availableSpots = new TreeSet<>();
    private LinkedList<Vehicle> parkedVehicles = new LinkedList<>();
    private Map<String, Ticket> activeTickets = new HashMap<>();
    private SimpleFeeCalculator feeCalc = new SimpleFeeCalculator();

    public ParkingLot(int totalSpots) {
        for (int i = 1; i <= totalSpots; i++) {
            availableSpots.add(new ParkingSpot(i));
        }
    }

    public void parkVehicle(Vehicle v) {
        String regNum = v.getRegNumber();

        if (activeTickets.containsKey(regNum)) {
            System.out.println("Vehicle with registration number " + regNum + " is already parked.");
            return;
        }

        if (availableSpots.isEmpty()) {
            System.out.println("No available parking spots.");
            return;
        }

        ParkingSpot spot = availableSpots.pollFirst();
        parkedVehicles.add(v);
        activeTickets.put(regNum, new Ticket(v, spot));
        System.out.println("Parked " + v.getType() + " at Spot #" + spot.getSpotNumber());
    }

    public void releaseVehicle(String regNumber) {
        Ticket ticket = activeTickets.get(regNumber);
        if (ticket == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        Vehicle v = ticket.getVehicle();
        ParkingSpot spot = ticket.getSpot(); // recover original spot
        long duration = (System.currentTimeMillis() - ticket.getEntryTime()) / (60 * 1000);
        double fee = feeCalc.calculateFee(duration);

        parkedVehicles.remove(v);
        activeTickets.remove(regNumber);
        availableSpots.add(spot);

        System.out.printf("Vehicle %s exited. Duration: %d min. Fee: ₹%.2f%n", regNumber, duration, fee);
    }

    public void showParkedVehicles() {
        if (parkedVehicles.isEmpty()) {
            System.out.println("No vehicles parked.");
        } else {
            parkedVehicles.forEach(System.out::println);
        }
    }
}