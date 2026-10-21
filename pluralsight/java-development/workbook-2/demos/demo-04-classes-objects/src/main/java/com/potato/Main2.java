package com.potato;

/*
 * Main2 - using Person from a different package.
 *
 * Run it on its own (it has its own main method). It shows that a public class
 * can be used from another package with an import, while its private fields stay
 * hidden. The commented-out line marked "error" contains a mistake on purpose:
 * remove the // in front of it, run again, and read the error message.
 */

// Person lives in com.pluralsight, not in com.potato.
// The import tells Java where to find it, so we can write Person instead of com.pluralsight.Person.
import com.pluralsight.Person;

// A class in a different package. It can use Person because Person is public,
// but it cannot see Person's private fields.
public class Main2 {
    public static void main(String[] args) {
        Person person1 = new Person("Ray", 55);   // the public constructor works from any package
        // The public getters are the way in from outside the class.
        System.out.println("Name: " + person1.getName() + " Age: " + person1.getAge());

        // error: name and age are private. Even with no modifier at all (the default, package-private),
        // they would still be hidden here, because com.potato is a different package from com.pluralsight.
        //System.out.println("Name: " + person1.name + " Age: " + person1.age);
    }
}
