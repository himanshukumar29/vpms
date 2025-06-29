// Car.java
package vpms.model;

public class Car extends Vehicle {
    public Car(String regNumber, String owner) {
        super(regNumber, owner);
    }

    public String getType() { return "Car"; }
}
