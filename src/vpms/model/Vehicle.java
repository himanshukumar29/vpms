package vpms.model;

import vpms.interface_.Parkable;

public abstract class Vehicle implements Parkable {
    private String regNumber;
    private String owner;

    public Vehicle(String regNumber, String owner) {
        this.regNumber = regNumber;
        this.owner = owner;
    }

    public String getRegNumber() { return regNumber; }
    public String getOwner() { return owner; }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + " | Reg: " + regNumber + " | Owner: " + owner;
    }
}
