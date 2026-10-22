package com.pluralsight;

/*
 * CellPhone is the blueprint (the class) for every cell phone object we create.
 * It describes WHAT a phone knows (its five properties), gives safe ways to read
 * and change that data (getters and setters), and what a phone can DO (dial).
 *
 * New in this exercise: overloading. The class now has TWO constructors and
 * TWO dial() methods. They share a name but take different parameters, and Java
 * picks the right one by looking at the arguments you pass.
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

    // Constructor 1, parameterless: runs for "new CellPhone()".
    // It gives every property a starting (default) value; the setters fill them in later.
    public CellPhone() {
        serialNumber = 0;
        model = "";
        carrier = "";
        phoneNumber = "";
        owner = "";
    }

    // Constructor 2, the overloaded constructor: runs for "new CellPhone(serial, model, carrier, number, owner)".
    // It builds a fully filled phone in one line, so no setters are needed afterwards.
    // "this.model" is the field of the object; "model" alone is the parameter with the same name.
    public CellPhone(int serialNumber, String model, String carrier, String phoneNumber, String owner) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.carrier = carrier;
        this.phoneNumber = phoneNumber;
        this.owner = owner;
    }

    // Getters and setters: one pair per property.
    // A getter returns the current value; a setter replaces it.

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

    // dial() version 1: takes the phone number to call as a String.
    // "owner" is the owner of the phone we call dial() on.
    public void dial(String number) {
        System.out.println("\n" + owner + "'s phone is calling " + number);
    }

    // Overloaded dial method that accepts a CellPhone object as input parameter (the optional part).
    // The caller passes the whole phone, and this method asks it for its number itself.
    public void dial(CellPhone phone) {
        System.out.println(owner + "'s phone is calling " + phone.getPhoneNumber());
    }
}
