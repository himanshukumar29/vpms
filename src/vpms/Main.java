package vpms;

import vpms.model.*;
import vpms.parking.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLot lot = new ParkingLot(5); // 5 spots

        System.out.println("=== Vehicle Parking Management System ===");

        int choice;
        do {
            System.out.println("\n1. Park Car\n2. Park Bike\n3. Show Parked\n4. Exit Vehicle\n0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Reg No: ");
                    String reg = sc.nextLine();
                    System.out.print("Owner: ");
                    String own = sc.nextLine();
                    lot.parkVehicle(new Car(reg, own));
                }
                case 2 -> {
                    System.out.print("Reg No: ");
                    String reg = sc.nextLine();
                    System.out.print("Owner: ");
                    String own = sc.nextLine();
                    lot.parkVehicle(new Bike(reg, own));
                }
                case 3 -> lot.showParkedVehicles();
                case 4 -> {
                    System.out.print("Enter Reg No to Exit: ");
                    String reg = sc.nextLine();
                    lot.releaseVehicle(reg);
                }
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
