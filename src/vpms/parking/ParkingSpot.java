package vpms.parking;

public class ParkingSpot implements Comparable<ParkingSpot> {
    private int spotNumber;

    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public int getSpotNumber() { return spotNumber; }

    public int compareTo(ParkingSpot other) {
        return Integer.compare(this.spotNumber, other.spotNumber);
    }
}
