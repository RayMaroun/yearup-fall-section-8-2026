package com.pluralsight;

/*
 * Car: the class under test. Nothing in here knows about JUnit.
 * The tests in src/test/java call accelerate(), brake() and isMoving()
 * and check that speed ends up where it should.
 */
public class Car {
    // Fields
    private String make;
    private String model;
    private int speed;

    // Constructors
    public Car() {
    }

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
        this.speed = 0;                                             // a new car stands still
    }

    // Getters and setters
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Boolean getter: is..., derived from speed (no "moving" field)
    public boolean isMoving() {
        return speed > 0;
    }

    // Methods under test
    public void accelerate(int changeInSpeed) {
        this.speed += changeInSpeed;
    }

    public void brake(int changeInSpeed) {
        if (changeInSpeed > this.speed) {                           // a car cannot go below 0
            this.speed = 0;
        } else {
            this.speed -= changeInSpeed;
        }
    }
}
