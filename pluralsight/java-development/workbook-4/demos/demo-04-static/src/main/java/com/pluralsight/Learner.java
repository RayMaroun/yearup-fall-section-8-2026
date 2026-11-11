package com.pluralsight;

/*
 * Learner: one class with both kinds of member side by side.
 *
 *   static  = belongs to the class. ONE copy, shared by every Learner,
 *             there before the first object is built, reached through
 *             the class name: Learner.changeSchool(...)
 *   instance = belongs to each object. Every Learner has its OWN name
 *             and grade, reached through the object: alice.printDetails()
 */
public class Learner {
    // Static member: one copy for the whole class, shared by every Learner
    private static String schoolName = "Greenwood High";

    // Instance members: each Learner has its own name and grade
    private String name;
    private int grade;

    public Learner(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    // Instance method: can read both instance fields and the static field
    public void printDetails() {
        System.out.println(name + " | grade " + grade + " | " + schoolName);
    }

    // Static method: called on the class, Learner.changeSchool(...), no object needed.
    // It can touch the static field only: there is no "this", no name, no grade here
    public static void changeSchool(String newName) {
        schoolName = newName;                                       // no this: there is no object here
    }
}
