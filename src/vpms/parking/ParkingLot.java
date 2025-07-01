package vpms.parking;

import vpms.model.Vehicle;
import java.util.*;

public class ParkingLot {
    private TreeSet<ParkingSpot> availableSpots = new TreeSet<>();
    private LinkedList<Vehicle> parkedVehicles = new LinkedList<>();
    private Map<String, Ticket> activeTickets = new HashMap<>();
    private SimpleFeeCalculator feeCalc = new SimpleFeeCalculator();
    private Map<String, ParkingSpot> vehicleSpotMap = new HashMap<>();

    public ParkingLot(int totalSpots) {
        for (int i = 1; i <= totalSpots; i++) {
            availableSpots.add(new ParkingSpot(i));
        }
    }

    public void parkVehicle(Vehicle v) {
        if (activeTickets.containsKey(v.getRegNumber())) {
            System.out.println("Vehicle with registration number " + v.getRegNumber() + " is already parked.");
            return;
        }

        if (availableSpots.isEmpty()) {
            System.out.println("No available parking spots.");
            return;
        }

        ParkingSpot spot = availableSpots.pollFirst();
        parkedVehicles.add(v);
        activeTickets.put(v.getRegNumber(), new Ticket(v));
        vehicleSpotMap.put(v.getRegNumber(), spot);
        System.out.println("Parked " + v.getType() + " at Spot #" + spot.getSpotNumber());
    }

    public void releaseVehicle(String regNumber) {
        Ticket ticket = activeTickets.get(regNumber);
        if (ticket == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        Vehicle v = ticket.getVehicle();
        long duration = (System.currentTimeMillis() - ticket.getEntryTime()) / (60 * 1000);
        double fee = feeCalc.calculateFee(duration);

        parkedVehicles.remove(v);
        activeTickets.remove(regNumber);

        ParkingSpot spot = vehicleSpotMap.remove(regNumber);
        availableSpots.add(spot);

        System.out.printf("Vehicle %s exited. Duration: %d min. Fee: ₹%.2f%n", regNumber, duration, fee);
    }
    // ✅ show parked vehicle

    public void showParkedVehicles() {
        if (parkedVehicles.isEmpty()) {
            System.out.println("No vehicles parked.");
        } else {
            for (Vehicle v : parkedVehicles) {
                ParkingSpot spot = vehicleSpotMap.get(v.getRegNumber());
                System.out.println("Vehicle: " + v + " | Parked at Spot #" + (spot != null ? spot.getSpotNumber() : "Unknown"));
            }
        }
    }

    // ✅  Search Vehicle by Registration Number
    public void searchVehicle(String regNo) {
        Ticket ticket = activeTickets.get(regNo);
        if (ticket != null) {
            Vehicle v = ticket.getVehicle();
            ParkingSpot spot = vehicleSpotMap.get(regNo);
            System.out.println("Vehicle Found: " + v + " | Parked at Spot #" + (spot != null ? spot.getSpotNumber() : "Unknown"));
        } else {
            System.out.println("Vehicle with registration number " + regNo + " not found.");
        }
    }

    // ✅  Show Available Parking Spots
    public void showAvailableSpots() {
        System.out.println("Available parking spots: " + availableSpots.size());
    }

    // ✅  Show Total Vehicles Parked
    public void showTotalVehicles() {
        System.out.println("Total vehicles currently parked: " + parkedVehicles.size());
    }
}
