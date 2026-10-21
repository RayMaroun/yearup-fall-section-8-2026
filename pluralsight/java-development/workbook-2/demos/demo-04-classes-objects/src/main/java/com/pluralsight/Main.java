package com.pluralsight;

/*
 * Demo 04 - Classes and objects
 *
 * This program walks through building your own type: why separate variables
 * stop working once you have many people to track, how a class (Person.java)
 * keeps related data together, how new creates an object from that class,
 * how private fields are read and changed through getters and setters, how
 * methods change an object's own state, and how two objects of the same class
 * stay independent. Dog.java is a second class built the same way, and
 * com/potato/Main2.java uses Person from a different package.
 *
 * Each block prints a title line, then its results.
 * Run it, type a name and an age when asked, read the output next to the
 * code, and change the values.
 *
 * Some lines are commented out on purpose. The ones marked "error" contain a
 * mistake: remove the // in front of one (or the block comment around it),
 * run again, and read the error message. That message is part of the lesson.
 * The other commented lines are alternatives: swap them with the live line
 * next to them and run again to see that they do the same thing.
 *
 * One idea runs through the whole file: a class is the blueprint, an object
 * is one thing built from it. You write the class once and build as many
 * objects from it as you need, each with its own values.
 */

// Scanner lives in the java.util package, so it has to be imported.
// Person and Dog need no import: they are in the same package as Main (com.pluralsight).
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ---------- Why classes ----------
        System.out.println("========== Why classes ==========");
        // One pair of variables per student: this does not scale to 30 students.
        // A Person class keeps name and age together in one object.
        // Nothing in the code ties firstStudentName to firstStudentAge except the
        // variable names. With a class, one variable holds one whole person.
        // The block below prints nothing; it is here to read and compare.
        /*
        String firstStudentName = "Raymond";
        int firstStudentAge = 55;

        String secondStudentName = "Ray";
        int secondStudentAge = 33;

        String thirdStudentName = "PotatoSensei";
        int thirdStudentAge = 23;
        */
        System.out.println();

        // ---------- Declare and initialize ----------
        // A variable goes through two steps: declare it (give it a type and a name),
        // then initialize it (give it its first value). They can be on separate lines.
        System.out.println("========== Declare and initialize ==========");
        String potato;              // declaration: a variable, no value yet
        potato = "testingPotato";   // initialization: give it a value
        System.out.println(potato);

        // Read the person's details from the keyboard, so the object below is built
        // from values you type rather than values fixed in the code.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the instructor:");
        String name = scanner.nextLine();   // reads the whole line you type

        System.out.println("Please enter the age of the instructor:");
        int age = scanner.nextInt();        // reads a whole number; typing letters here crashes the program

        // Same two steps for an object
        // The commented block is the two-line version. The live line below does both
        // at once. Swap them to see that the result is the same.
        // name and age here are the values you typed; they go into the constructor in Person.java.
        /*
        Person instructor;                  // declare: a Person variable, no object yet
        instructor = new Person(name, age); // instantiate: new builds the object and runs the constructor
        */
        Person instructor = new Person(name, age);  // both steps in one line
        System.out.println();

        // ---------- Getters ----------
        // The fields in Person are private, so Main cannot read them directly.
        // A getter is a public method that hands back a field's value.
        System.out.println("========== Getters ==========");
        System.out.println(instructor.getName());
        System.out.println(instructor.getAge());
        // Remove the /* and */ around these two lines to see the compiler refuse.
        /*
        System.out.println(instructor.name);    // error: name has private access in Person
        System.out.println(instructor.age);     // error: age has private access in Person
        */
        System.out.println();

        // ---------- Setters ----------
        // A setter is a public method that replaces a field's value.
        // The object stays the same object; only the value inside it changes.
        System.out.println("========== Setters ==========");
        instructor.setName("Ray");              // change a private field through its setter
        System.out.println(instructor.getName());   // Ray: the name you typed earlier is replaced
        System.out.println();

        // ---------- Methods ----------
        // Methods are actions the object performs on its own data.
        // Main never does the energy math; it asks the object to work or eat,
        // and the rules in Person.java decide how much energy changes.
        System.out.println("========== Methods ==========");
        System.out.println("Energy level before work: " + instructor.getEnergy());   // 100, set by the constructor
        instructor.work(5);                     // 5 hours: -50
        System.out.println("Energy level after work: " + instructor.getEnergy());    // 50
        instructor.eat();                       // a meal: +20
        System.out.println("Energy level after eating: " + instructor.getEnergy());  // 70
        instructor.greet();                     // uses the name set by the setter above
        System.out.println();

        // ---------- A second object ----------
        // Each object has its own copy of every field. Changing one object
        // leaves the other untouched.
        System.out.println("========== A second object ==========");
        Person student = new Person("Brandon", 35);   // same class, a separate object
        student.work(2);                              // only the student's energy changes
        System.out.println(instructor.getName() + " energy: " + instructor.getEnergy());   // still 70
        System.out.println(student.getName() + " energy: " + student.getEnergy());         // 100 - 20 = 80
        System.out.println();

        // ---------- Dog ----------
        // Dog.java is a second class built the same way as Person.
        System.out.println("========== Dog ==========");
        // Any thing can be a class. Uncomment to create a Dog.
        // Inside the block, the line marked "error" asks for a Dog with no values.
        // Dog has no constructor that takes nothing, so it fails. Comment the first
        // line, uncomment the second, and read the error; then see Dog.java for the fix.
        /*
        Dog cliford = new Dog("Pitbull", 23, "Brown");
        //Dog cliford = new Dog();     // error: Dog has no empty constructor
        */
        System.out.println();

        // Close the Scanner when you are done reading input.
        scanner.close();
    }
}
