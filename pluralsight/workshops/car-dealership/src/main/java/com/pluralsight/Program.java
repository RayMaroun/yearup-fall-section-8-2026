package com.pluralsight;

/**
 * Entry point (the "Program" class of the workshop diagram).
 * <p>
 * main does two things only: create the user interface and start it.
 * It does not load the file and does not create the Dealership; the
 * UserInterface does that in its init() method, so this class stays the
 * same even when the application later supports more than one dealership.
 */
public class Program {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.display();
    }
}
