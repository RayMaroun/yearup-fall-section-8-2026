package com.pluralsight;

/*
 * Caveman is a child class of Human: a Caveman IS a Human.
 * It inherits the name and the age, adds a strength and skills,
 * replaces eat() with its own version and adds run().
 */
public class Caveman extends Human {
    // The two fields written here. name and age come from Human.
    private int strength;
    private String skills;

    // super(name, age) hands the name and the age up to the Human
    // constructor. The other two values belong to Caveman, so they are
    // stored here.
    public Caveman(String name, int age, int strength, String skills) {
        super(name, age);
        this.strength = strength;
        this.skills = skills;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    // Override: same name, same parameters and same return type as eat() in
    // Human. For a Caveman object, this version runs instead of the
    // inherited one.
    // @Override asks the compiler to check that Human really has an eat()
    // method. With a typo in the name (eta, for example) the compiler says:
    // method does not override or implement a method from a supertype
    @Override                                                       // optional, but always write it
    public void eat() {
        System.out.println("Me Hunt Lion and Eat!");
    }

    // run() does not exist in Human. Only a variable of type Caveman can
    // call it, even when the object behind a Human variable is a Caveman.
    public void run() {
        System.out.println("Me running away from dinosaur!");
    }
}
