// Bike.java
package vpms.model;

public class Bike extends Vehicle {
    public Bike(String regNumber, String owner) {
        super(regNumber, owner);
    }

    public String getType() { return "Bike"; }
}
