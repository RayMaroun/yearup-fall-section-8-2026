package com.pluralsight;

/*
 * CellPhone is the blueprint (the class) for every cell phone object we create.
 * It describes WHAT a phone knows (its five properties) and gives safe ways
 * to read and change that data (getters and setters).
 *
 * Each "new CellPhone()" in CellPhoneApplication builds one object from this blueprint,
 * and each object keeps its own copy of the five properties.
 */
public class CellPhone {
    // Data members (properties). They are private so that code outside this class
    // cannot touch them directly; it must go through the getters and setters below.
    // This idea is called encapsulation.
    private int serialNumber;   // a whole number, ex: 1000000 - 9999999
    private String model;       // ex: iPhone X
    private String carrier;     // ex: AT&T
    private String phoneNumber; // a String, not a number: it contains dashes, ex: 800-555-5555
    private String owner;       // ex: Dana Wyatt

    // Parameterless constructor: runs when we write "new CellPhone()".
    // It gives every property a starting (default) value so nothing is left undefined.
    public CellPhone() {
        serialNumber = 0;
        model = "";
        carrier = "";
        phoneNumber = "";
        owner = "";
    }

    // Getters and setters: one pair per property.
    // A getter returns the current value; a setter replaces it.
    // "this.serialNumber" means the field of this object; "serialNumber" alone is the parameter.

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
