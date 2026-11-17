package com.pluralsight;

/*
 * ModernPerson is the second child class of Human: a ModernPerson IS a Human.
 * It inherits the name and the age, adds two fields of its own,
 * replaces eat() with its own version and adds drive().
 */
public class ModernPerson extends Human {
    // The two fields written here. name and age come from Human.
    private int tiktokUsagePerHour;
    private int stressLevel;

    // super(name, age) hands the name and the age up to the Human
    // constructor. The other two values are stored here.
    public ModernPerson(String name, int age, int tiktokUsagePerHour, int stressLevel) {
        super(name, age);
        this.tiktokUsagePerHour = tiktokUsagePerHour;
        this.stressLevel = stressLevel;
    }

    public int getTiktokUsagePerHour() {
        return tiktokUsagePerHour;
    }

    public void setTiktokUsagePerHour(int tiktokUsagePerHour) {
        this.tiktokUsagePerHour = tiktokUsagePerHour;
    }

    public int getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(int stressLevel) {
        this.stressLevel = stressLevel;
    }

    // Override: a third version of eat(). Human, Caveman and ModernPerson
    // now each answer the same call, eat(), in their own way.
    @Override
    public void eat() {
        System.out.println("I am going to McDonalds to eat!");
    }

    // drive() does not exist in Human. Only a variable of type ModernPerson
    // can call it.
    public void drive() {
        System.out.println("I am driving my Car!");
    }
}
