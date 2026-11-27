# Week 6 Guided Project: Delivery Desk

<!--
Verification markers (read by the checker, invisible on GitHub):
  apply: file / after / before / end / replace / delete  = how the next code block changes a file
  run            = run Main, no typed input
  final          = the built file must equal the next block
-->

This is a self-study project. You build one small program, step by step, and it uses everything from week 6: a parent class and its child classes, `extends`, inherited getters and setters, private and protected members, constructors that call `super(...)`, overriding with `@Override`, a base class reference, one list that holds every kind of object, `instanceof` with a downcast, and an abstract class with an abstract method. It also reuses earlier weeks: BigDecimal for money, ArrayList, for-each, and printf.

The program is the counter of a delivery company. People hand over letters, parcels and freight. Every one of them is a shipment: it has a tracking number, a recipient and a destination. A `Letter` can be registered, a `Parcel` has a weight, `Freight` comes on pallets, and each of them works out its own cost. `Main` builds a few shipments, puts them in one list, prints what each one costs, adds up the total and prints the notes for the driver.

Type every line yourself, run the program at the end of every step, and compare your output with the expected output. If it does not match, compare your code with the step line by line before you move on. Most problems are a missing semicolon, a missing brace, or a capital letter in the wrong place.

Every step uses the same sample data:

- Letter L-100 for Nadia in Austin, registered.
- Parcel P-200 for Omar in Denver, 6 kg.
- Freight F-300 for Victor in Seattle, 2 pallets.
- A plain shipment S-001 for Leo in Boston, until Step 22 shows why it should not exist.

A word on commit messages. Every commit message has the shape `type: what changed`, where the type is one of `feat` (new behavior), `fix`, `docs`, `refactor` (same behavior, cleaner code), `chore` (setup, no code change) or `test`. The message at the end of every step already has that shape. Copy it exactly.

## Step 1 - Create the project

### Type this

Create a new project in IntelliJ with these settings. Open Advanced Settings to see the GroupId field, and uncheck "Add sample code" so the project starts empty.

```text
Name:                   delivery-desk
Build system:           Maven
JDK:                    17
Create Git repository:  ticked
GroupId:                com.pluralsight
```

For Location, use the `guided-projects` folder inside `java-development`, next to your workbook folders: Desktop > pluralsight > java-development > guided-projects. Create the folder if it does not exist yet.

Click Create. Then share the project on GitHub right away: VCS menu, Share Project on GitHub. Keep the repository name `delivery-desk`. When IntelliJ asks for the first commit message, use the one at the end of this step.

### What is happening

- Maven gives every project the same folder layout: your code goes in `src/main/java`.
- The GroupId `com.pluralsight` becomes the package name. Your classes will live in `com.pluralsight`.
- One project, one repository. This project gets its own repository on GitHub, and nothing else goes in it.
- `chore` is the right type for this commit: the project exists, but no program code was written.

### Run it

<!-- no-run -->
Nothing runs yet. Check that the Project panel shows this:

```text
delivery-desk  >  src  >  main  >  java
delivery-desk  >  pom.xml
```

Commit and push: "chore: create the delivery-desk project"

From now on, commit and push after every step, with the message given at the end of the step. One step, one commit.

## Step 2 - The class and main

### Type this

Right-click the `java` folder under `src/main`, choose New, Java Class, and type `com.pluralsight.Main`. IntelliJ creates the package and the class. Make the file look exactly like this:

<!-- apply: file Main.java -->
```java
package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Delivery Desk");
    }
}
```

### What is happening

- `package com.pluralsight;` must be the first line. It says which package the class belongs to.
- `public class Main` must match the file name `Main.java` exactly, capitals included.
- `main` is where the program starts. Every other class in this project is used from here.

### Run it

Click the green arrow next to `main` and choose Run.

<!-- run -->
```text
Welcome to the Delivery Desk
```

Commit and push: "feat: add Main with a welcome line"

## Step 3 - The Letter class

### Type this

Right-click the `com.pluralsight` package, choose New, Java Class, and type `Letter`. Make the file look exactly like this:

<!-- apply: file Letter.java -->
```java
package com.pluralsight;

public class Letter {
    private String trackingNumber;
    private String recipient;

    public String getTrackingNumber() {
        return this.trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
```

Add the getter and the setter for the second field after the `}` that closes `setTrackingNumber`:

<!-- apply: after Letter.java | this.trackingNumber = trackingNumber; >> } -->
```java

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
```

Back in `Main`, add these lines under the welcome line:

<!-- apply: after Main.java | System.out.println("Welcome to the Delivery Desk"); -->
```java

        Letter letter = new Letter();
        letter.setTrackingNumber("L-100");
        letter.setRecipient("Nadia");
        System.out.println(letter.getTrackingNumber() + " for " + letter.getRecipient());
```

### What is happening

- This is a week 5 class: two private fields, a getter and a setter for each. Nothing new yet.
- There is no constructor in the file. When a class has none, Java supplies an empty one, which is why `new Letter()` works.
- The fields start as `null`. The two setters fill them in after the letter is created.
- Constructors with parameters come back in Step 11. For now the setters keep the classes short.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
L-100 for Nadia
```

Commit and push: "feat: add the Letter class"

## Step 4 - The Parcel class, and the same lines again

### Type this

Right-click the `com.pluralsight` package, choose New, Java Class, and type `Parcel`. Make the file look exactly like this. Yes, most of it is `Letter` all over again. Type it anyway.

<!-- apply: file Parcel.java -->
```java
package com.pluralsight;

public class Parcel {
    private String trackingNumber;
    private String recipient;
    private int weightKg;

    public String getTrackingNumber() {
        return this.trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
```

Add the getter and the setter for `recipient` after the `}` that closes `setTrackingNumber`:

<!-- apply: after Parcel.java | this.trackingNumber = trackingNumber; >> } -->
```java

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
```

Then the getter and the setter for `weightKg` after the `}` that closes `setRecipient`:

<!-- apply: after Parcel.java | this.recipient = recipient; >> } -->
```java

    public int getWeightKg() {
        return this.weightKg;
    }

    public void setWeightKg(int weightKg) {
        this.weightKg = weightKg;
    }
```

In `Main`, add these lines under the line that prints the letter:

<!-- apply: after Main.java | letter.getRecipient()); -->
```java

        Parcel parcel = new Parcel();
        parcel.setTrackingNumber("P-200");
        parcel.setRecipient("Omar");
        parcel.setWeightKg(6);
        System.out.println(parcel.getTrackingNumber() + " for " + parcel.getRecipient() + ", " + parcel.getWeightKg() + " kg");
```

### What is happening

- Two fields and four methods of `Parcel` are word for word the ones in `Letter`. Only `weightKg` is new.
- That is duplication. If a tracking number ever needs a check, you must remember to add it in both classes, and one day you will forget one.
- The desk will soon take freight too. A third class would repeat the same lines a third time.
- A letter is a shipment. A parcel is a shipment. What they share belongs in one place, written once. That is the job of inheritance, and the next three steps do it.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
L-100 for Nadia
P-200 for Omar, 6 kg
```

Commit and push: "feat: add the Parcel class"

## Step 5 - The parent class Shipment

### Type this

Right-click the `com.pluralsight` package, choose New, Java Class, and type `Shipment`. This class holds what every shipment has in common. Make the file look exactly like this:

<!-- apply: file Shipment.java -->
```java
package com.pluralsight;

public class Shipment {
    private String trackingNumber;
    private String recipient;

    public String getTrackingNumber() {
        return this.trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
```

Add the getter and the setter for `recipient` after the `}` that closes `setTrackingNumber`:

<!-- apply: after Shipment.java | this.trackingNumber = trackingNumber; >> } -->
```java

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
```

In `Main`, add these lines under the line that prints the parcel:

<!-- apply: after Main.java | parcel.getWeightKg() + " kg"); -->
```java

        Shipment plain = new Shipment();
        plain.setTrackingNumber("S-001");
        plain.setRecipient("Leo");
        System.out.println(plain.getTrackingNumber() + " for " + plain.getRecipient());
```

### What is happening

- `Shipment` is going to be the parent class. It holds the members that every kind of shipment shares: the tracking number, the recipient, and their getters and setters.
- This is the last time you type these lines. From here on they live in `Shipment` only.
- So far `Shipment` is an ordinary class. `Letter` and `Parcel` do not know it exists yet; nothing connects them.
- `plain` is a shipment that is neither a letter nor a parcel. Keep an eye on it: Step 22 comes back to whether that makes sense.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
L-100 for Nadia
P-200 for Omar, 6 kg
S-001 for Leo
```

Commit and push: "feat: add the Shipment class"

## Step 6 - Letter extends Shipment

### Type this

In `Letter.java`, add `extends Shipment` after the class name. Then delete everything between the two braces of the class: the two fields and the four methods. The file ends up exactly like this:

<!-- apply: file Letter.java -->
```java
package com.pluralsight;

public class Letter extends Shipment {
}
```

Do not touch `Main`.

### What is happening

- `extends Shipment` makes `Letter` a child class of `Shipment`. `Shipment` is the parent class.
- A child class inherits the fields and the methods of its parent. `Letter` has an empty body and still has a tracking number, a recipient, two getters and two setters.
- `Main` did not change by one character. `letter.setTrackingNumber("L-100")` still compiles, because the method is now found in the parent.
- Inheritance is an is-a relationship: a Letter is-a Shipment. If that sentence sounds wrong for two classes, `extends` is the wrong tool for them.
- The output is the same as before. That is what `refactor` means in the commit message: cleaner code, same behavior.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
L-100 for Nadia
P-200 for Omar, 6 kg
S-001 for Leo
```

Commit and push: "refactor: make Letter a child class of Shipment"

## Step 7 - Parcel extends Shipment

### Type this

In `Parcel.java`, add `extends Shipment` after the class name. Delete the fields `trackingNumber` and `recipient` and their four methods. Keep `weightKg` with its getter and its setter. The file ends up exactly like this:

<!-- apply: file Parcel.java -->
```java
package com.pluralsight;

public class Parcel extends Shipment {
    private int weightKg;

    public int getWeightKg() {
        return this.weightKg;
    }

    public void setWeightKg(int weightKg) {
        this.weightKg = weightKg;
    }
}
```

### What is happening

- A child class has everything it inherits plus whatever it adds. `Parcel` adds `weightKg`; `Letter` adds nothing so far.
- What is left in `Parcel` is only what makes a parcel different from any other shipment. The class now reads like a list of differences.
- `parcel.getRecipient()` runs code written in `Shipment`. `parcel.getWeightKg()` runs code written in `Parcel`. From `Main` you cannot tell the difference, and you do not need to.
- It works in one direction only. `plain.getWeightKg()` does not compile: a parent knows nothing about what its children add.
- One parent, two children, and the duplicated lines are gone.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
L-100 for Nadia
P-200 for Omar, 6 kg
S-001 for Leo
```

Commit and push: "refactor: make Parcel a child class of Shipment"

## Step 8 - Private: inherited, but out of reach

### Type this

In `Parcel.java`, add this method at the bottom of the class, under `setWeightKg`:

<!-- apply: end Parcel.java -->
```java

    // Only parcels have this method. The fields are private in Shipment, so the child asks the getters.
    public String getDriverNote() {
        //System.out.println(this.trackingNumber);      // uncomment: trackingNumber has private access in Shipment
        return "Hand " + getTrackingNumber() + " to " + getRecipient();
    }
```

In `Main`, add this line under the line that prints the parcel:

<!-- apply: after Main.java | parcel.getWeightKg() + " kg"); -->
```java
        System.out.println(parcel.getDriverNote());
```

### What is happening

- `trackingNumber` is `private` in `Shipment`. A parcel has that field, the value is stored inside every parcel, but code written in `Parcel` cannot touch it directly.
- Private means "this class only", and a child class is another class. Remove the `//` in front of the `System.out.println` line to see the error, then put the `//` back.
- The getters are `public` and inherited, so the child uses them: `getTrackingNumber()` and `getRecipient()`, with no object in front because the parcel is asking itself.
- `getDriverNote` exists only in `Parcel`. A letter does not have it and neither does a plain shipment.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
L-100 for Nadia
P-200 for Omar, 6 kg
Hand P-200 to Omar
S-001 for Leo
```

Commit and push: "feat: add a driver note to Parcel"

## Step 9 - Protected: open to the child classes

### Type this

In `Shipment.java`, add this field under `private String recipient;`:

<!-- apply: after Shipment.java | private String recipient; -->
```java
    protected String destination;                // protected: child classes can use it directly
```

In `Parcel.java`, replace the `return` line of `getDriverNote` with this:

<!-- apply: replace Parcel.java | return "Hand " + getTrackingNumber() + " to " + getRecipient(); -->
```java
        return "Hand " + getTrackingNumber() + " to " + getRecipient() + " in " + this.destination;
```

In `Main`, add this line under `parcel.setWeightKg(6);`:

<!-- apply: after Main.java | parcel.setWeightKg(6); -->
```java
        parcel.destination = "Denver";
```

### What is happening

- `protected` sits between `private` and `public`: the class itself and its child classes can use the member directly. `Parcel` reads `this.destination` with no getter in between.
- `this.destination` inside `Parcel` looks like a field of `Parcel`. It is not written there; it is inherited from `Shipment`.
- `parcel.destination = "Denver";` compiles in `Main` as well. Protected also lets in every class of the same package, and `Main` is in `com.pluralsight`.
- That is the price of `protected`: `Shipment` can no longer control who changes `destination` or what goes into it. Private fields with getters stay the normal choice. Use `protected` on purpose, not by habit.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
L-100 for Nadia
P-200 for Omar, 6 kg
Hand P-200 to Omar in Denver
S-001 for Leo
```

Commit and push: "feat: add a protected destination to Shipment"

## Step 10 - The parent constructor runs first

### Type this

In `Shipment.java`, add this constructor under the `destination` field:

<!-- apply: after Shipment.java | protected String destination; -->
```java

    public Shipment() {
        System.out.println("Shipment constructor");
    }
```

In `Letter.java`, add this constructor between the two braces of the class:

<!-- apply: after Letter.java | public class Letter extends Shipment { -->
```java
    public Letter() {
        System.out.println("Letter constructor");
    }
```

### What is happening

- Constructors are the one thing a child class does not inherit. Every class writes its own.
- `new Letter()` printed two lines, and the parent's line came first. Before a child constructor does anything, the parent constructor runs: the shipment part of the object is built before the letter part.
- You did not write that call. Java puts an invisible `super();` at the top of every child constructor that does not have one.
- `Parcel` has no constructor in its file, and `new Parcel()` still printed `Shipment constructor`. The empty constructor Java supplies for `Parcel` calls `super();` too.
- The plain shipment prints the line once: it has no child part to build.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
Shipment constructor
Letter constructor
L-100 for Nadia
Shipment constructor
P-200 for Omar, 6 kg
Hand P-200 to Omar in Denver
Shipment constructor
S-001 for Leo
```

Commit and push: "feat: show the order of the constructors"

## Step 11 - super with arguments

### Type this

In `Shipment.java`, add a second constructor after the `}` that closes the first one:

<!-- apply: after Shipment.java | System.out.println("Shipment constructor"); >> } -->
```java

    public Shipment(String trackingNumber, String recipient, String destination) {
        this.trackingNumber = trackingNumber;
        this.recipient = recipient;
        this.destination = destination;
    }
```

In `Letter.java`, replace the whole `public Letter() { ... }` constructor with these lines:

<!-- apply: replace Letter.java | public Letter() { >> } -->
```java
    private boolean registered;

    public Letter(String trackingNumber, String recipient, String destination, boolean registered) {
        super(trackingNumber, recipient, destination);   // calls the Shipment constructor with 3 parameters
        this.registered = registered;
    }

    public boolean isRegistered() {
        return this.registered;
    }
```

In `Main`, replace the three lines from `Letter letter = new Letter();` to `letter.setRecipient("Nadia");` with this one:

<!-- apply: replace Main.java | Letter letter = new Letter(); >> letter.setRecipient("Nadia"); -->
```java
        Letter letter = new Letter("L-100", "Nadia", "Austin", true);
```

And add this line under the line that prints the letter:

<!-- apply: after Main.java | letter.getRecipient()); -->
```java
        System.out.println("Registered: " + letter.isRegistered());
```

### What is happening

- The `Letter` constructor takes four values. Three of them belong to the shipment part, so it passes them up with `super(trackingNumber, recipient, destination)`. The fourth is the letter's own, and it stores that one itself.
- `super(...)` calls a constructor of the parent class. Java picks the one whose parameters match the arguments, exactly as it does for overloaded constructors.
- `super(...)` must be the first statement of the constructor. Move `this.registered = registered;` above it and the code does not compile.
- `Letter` cannot write `this.trackingNumber = trackingNumber;` on its own: the field is private in `Shipment`. Handing the value to the parent constructor is the way in.
- The lines `Shipment constructor` and `Letter constructor` are gone for the letter. `super(...)` with three arguments picked the other parent constructor, the one that prints nothing.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
L-100 for Nadia
Registered: true
Shipment constructor
P-200 for Omar, 6 kg
Hand P-200 to Omar in Denver
Shipment constructor
S-001 for Leo
```

Commit and push: "feat: give Letter a constructor that calls super"

## Step 12 - Parcel calls super, and the empty constructor goes

### Type this

In `Parcel.java`, add this constructor under `private int weightKg;`:

<!-- apply: after Parcel.java | private int weightKg; -->
```java

    public Parcel(String trackingNumber, String recipient, String destination, int weightKg) {
        super(trackingNumber, recipient, destination);
        this.weightKg = weightKg;
    }
```

In `Main`, replace the five lines from `Parcel parcel = new Parcel();` to `parcel.destination = "Denver";` with this one:

<!-- apply: replace Main.java | Parcel parcel = new Parcel(); >> parcel.destination = "Denver"; -->
```java
        Parcel parcel = new Parcel("P-200", "Omar", "Denver", 6);
```

Replace the three lines from `Shipment plain = new Shipment();` to `plain.setRecipient("Leo");` with this one:

<!-- apply: replace Main.java | Shipment plain = new Shipment(); >> plain.setRecipient("Leo"); -->
```java
        Shipment plain = new Shipment("S-001", "Leo", "Boston");
```

Nothing calls the empty `Shipment` constructor any more. In `Shipment.java`, delete it: the three lines from `public Shipment() {` to its closing `}`.

<!-- apply: delete Shipment.java | public Shipment() { >> } -->

### What is happening

- Same pattern as `Letter`: pass the shared values up with `super(...)` first, then store your own.
- With the empty constructor gone, every shipment must be created with a tracking number, a recipient and a destination. A shipment with `null` in those fields can no longer exist.
- It also means every child constructor must call `super(...)` with three arguments. Leave that line out and Java tries its invisible `super();`, finds no constructor without parameters in `Shipment`, and reports "There is no default constructor available in 'com.pluralsight.Shipment'".
- The setters are still there, inherited by every child, for the day a recipient changes.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
L-100 for Nadia
Registered: true
P-200 for Omar, 6 kg
Hand P-200 to Omar in Denver
S-001 for Leo
```

Commit and push: "feat: give Parcel a constructor and remove the empty Shipment constructor"

## Step 13 - A cost in the parent class

### Type this

In `Shipment.java`, add this line under `package com.pluralsight;`:

<!-- apply: after Shipment.java | package com.pluralsight; -->
```java

import java.math.BigDecimal;
```

Add this method at the bottom of the class, under `setRecipient`:

<!-- apply: end Shipment.java -->
```java

    // What it costs to send. For now every shipment pays the same handling fee.
    public BigDecimal getCost() {
        return new BigDecimal("5.00");
    }
```

In `Main`, replace everything inside `main` from `Letter letter = new Letter("L-100", "Nadia", "Austin", true);` down to the line that prints the plain shipment with these lines. The welcome line stays.

<!-- apply: replace Main.java | Letter letter = new Letter("L-100", >> plain.getRecipient()); -->
```java
        Shipment plain = new Shipment("S-001", "Leo", "Boston");
        Letter letter = new Letter("L-100", "Nadia", "Austin", true);
        Parcel parcel = new Parcel("P-200", "Omar", "Denver", 6);

        System.out.printf("Plain shipment: %.2f%n", plain.getCost());
        System.out.printf("Letter: %.2f%n", letter.getCost());
        System.out.printf("Parcel: %.2f%n", parcel.getCost());
```

### What is happening

- Money is a `BigDecimal`, never a `double`. `new BigDecimal("5.00")` takes a String on purpose, so the amount is exact.
- `getCost` is written once, in the parent. `Letter` and `Parcel` inherit it, so all three calls run the same code and print the same amount.
- `printf` with `%.2f` prints a BigDecimal with two decimal places. `%n` ends the line.
- A letter for 5.00 and a parcel for 5.00 is not how a delivery desk works. Each child needs its own way to compute the cost, and that is the next step.
- `Main` shrinks on purpose. The experiments from Steps 3 to 12 did their job, and the commits keep the old lines.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
Plain shipment: 5.00
Letter: 5.00
Parcel: 5.00
```

Commit and push: "feat: add getCost to Shipment"

## Step 14 - Override getCost in Letter

### Type this

In `Letter.java`, add this line under `package com.pluralsight;`:

<!-- apply: after Letter.java | package com.pluralsight; -->
```java

import java.math.BigDecimal;
```

Add this method at the bottom of the class, under `isRegistered`:

<!-- apply: end Letter.java -->
```java

    // Override: same name, same parameters, same return type as getCost() in Shipment
    @Override
    public BigDecimal getCost() {
        BigDecimal cost = new BigDecimal("1.50");
        if (this.registered) {
            cost = cost.add(new BigDecimal("3.00"));     // add returns a new BigDecimal
        }
        return cost;
    }
```

Do not touch `Main`.

### What is happening

- A child class can override a method it inherits: it writes a method with the same name, the same parameters and the same return type, and its version replaces the parent's for objects of that class.
- `letter.getCost()` now runs the `Letter` version: 1.50, plus 3.00 because L-100 is registered. The parent's 5.00 is not involved at all.
- `Parcel` did not override anything, so a parcel still gets the inherited version and still costs 5.00.
- `@Override` asks the compiler to check that the parent really has this method. Type `getcost` with a small c and, thanks to the annotation, you get an error at once. Without it you would silently have two different methods and the wrong price.
- `cost.add(...)` does not change `cost`. It hands back a new BigDecimal, which is why the result is stored back into `cost`.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
Plain shipment: 5.00
Letter: 4.50
Parcel: 5.00
```

Commit and push: "feat: override getCost in Letter"

## Step 15 - Override getCost in Parcel

### Type this

In `Parcel.java`, add this line under `package com.pluralsight;`:

<!-- apply: after Parcel.java | package com.pluralsight; -->
```java

import java.math.BigDecimal;
```

Add this method at the bottom of the class, under `getDriverNote`:

<!-- apply: end Parcel.java -->
```java

    // 4.00 to take the parcel, plus 1.25 for every kg
    @Override
    public BigDecimal getCost() {
        BigDecimal weightCost = new BigDecimal("1.25").multiply(BigDecimal.valueOf(this.weightKg));
        return new BigDecimal("4.00").add(weightCost);
    }
```

### What is happening

- Same method name, a third body. A plain shipment costs 5.00, a letter 1.50 or 4.50, a parcel depends on its weight.
- `multiply` needs a BigDecimal on both sides. `BigDecimal.valueOf(this.weightKg)` turns the `int` into one; an `int` is exact, so that is safe.
- 1.25 x 6 = 7.50, plus 4.00 is 11.50.
- `Main` calls `getCost()` three times in the same way and gets three different computations. The caller does not choose which version runs. The object does.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
Plain shipment: 5.00
Letter: 4.50
Parcel: 11.50
```

Commit and push: "feat: override getCost in Parcel"

## Step 16 - Override toString

### Type this

In `Shipment.java`, add this method at the bottom of the class, under `getCost`:

<!-- apply: end Shipment.java -->
```java

    // toString comes from Object, the parent of every class: this is an override too
    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + this.trackingNumber + " for " + this.recipient + ", " + this.destination;
    }
```

In `Main`, add these lines under `Parcel parcel = new Parcel("P-200", "Omar", "Denver", 6);`:

<!-- apply: after Main.java | Parcel parcel = new Parcel("P-200", "Omar", "Denver", 6); -->
```java

        System.out.println(plain);
        System.out.println(letter);
        System.out.println(parcel);
```

### What is happening

- Every class has a parent. When you write no `extends`, the parent is `Object`, so `Shipment` is a child of `Object` and inherits its `toString`.
- `System.out.println(letter)` calls `toString` for you. Without this step it would print the inherited version, something like `com.pluralsight.Letter@4e25154f`.
- `Shipment` overrides `toString` once, and `Letter` and `Parcel` inherit the new version. One method, three classes fixed.
- `getClass().getSimpleName()` gives the name of the real class of the object. The code is written in `Shipment`, and for a letter it still answers `Letter`.
- Inside `Shipment` the private fields are used directly. This is the class they belong to.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
Shipment S-001 for Leo, Boston
Letter L-100 for Nadia, Austin
Parcel P-200 for Omar, Denver
Plain shipment: 5.00
Letter: 4.50
Parcel: 11.50
```

Commit and push: "feat: override toString in Shipment"

## Step 17 - A third child class: Freight

### Type this

Right-click the `com.pluralsight` package, choose New, Java Class, and type `Freight`. Everything in it is something you have already done once. Make the file look exactly like this:

<!-- apply: file Freight.java -->
```java
package com.pluralsight;

import java.math.BigDecimal;

public class Freight extends Shipment {
    private int pallets;

    public Freight(String trackingNumber, String recipient, String destination, int pallets) {
        super(trackingNumber, recipient, destination);
        this.pallets = pallets;
    }

    public int getPallets() {
        return this.pallets;
    }
}
```

Add this method at the bottom of the class, under `getPallets`:

<!-- apply: end Freight.java -->
```java

    // 60.00 for every pallet
    @Override
    public BigDecimal getCost() {
        return new BigDecimal("60.00").multiply(BigDecimal.valueOf(this.pallets));
    }
```

In `Main`, add this line under `Parcel parcel = new Parcel("P-200", "Omar", "Denver", 6);`:

<!-- apply: after Main.java | Parcel parcel = new Parcel("P-200", "Omar", "Denver", 6); -->
```java
        Freight freight = new Freight("F-300", "Victor", "Seattle", 2);
```

Add this line under `System.out.println(parcel);`:

<!-- apply: after Main.java | System.out.println(parcel); -->
```java
        System.out.println(freight);
```

And this line under the `Parcel:` print line:

<!-- apply: after Main.java | System.out.printf("Parcel: -->
```java
        System.out.printf("Freight: %.2f%n", freight.getCost());
```

### What is happening

- A whole new kind of shipment in about twenty lines: `extends`, one field of its own, a constructor that calls `super(...)`, one getter, one override.
- You did not open `Shipment`, `Letter` or `Parcel`. Adding a child class changes nothing in the parent or in the other children.
- `Freight` got the tracking number, the recipient, the destination, the getters, the setters and `toString` for free. The printed line starts with `Freight` because `getClass()` reports the real class.
- This is what the duplication in Step 4 would have cost a third time. With a parent class it costs nothing.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
Shipment S-001 for Leo, Boston
Letter L-100 for Nadia, Austin
Parcel P-200 for Omar, Denver
Freight F-300 for Victor, Seattle
Plain shipment: 5.00
Letter: 4.50
Parcel: 11.50
Freight: 120.00
```

Commit and push: "feat: add the Freight class"

## Step 18 - A base class reference

### Type this

In `Main`, add these lines under the `Freight:` print line, just above the `}` that closes `main`:

<!-- apply: after Main.java | System.out.printf("Freight: -->
```java

        // A base class reference: the variable is a Shipment, the object is a Letter
        Shipment next = new Letter("L-101", "Maya", "Tucson", false);
        System.out.printf("Next: %.2f%n", next.getCost());
        //System.out.println(next.isRegistered());     // uncomment: cannot find symbol

        next = parcel;                                   // the same variable, now a Parcel
        System.out.printf("Next: %.2f%n", next.getCost());
```

### What is happening

- A variable of the parent type can hold an object of any child type. A Letter is-a Shipment, so a `Letter` fits in a `Shipment` variable. No cast, no conversion.
- The variable decides what you are allowed to call. `next` is a `Shipment`, and `Shipment` has no `isRegistered`, so that line does not compile even though the object really is a letter.
- The object decides which version runs. `next.getCost()` printed 1.50, the `Letter` price for a letter that is not registered, not the 5.00 written in `Shipment`.
- Then the same variable points at the parcel, and the same line of code prints 11.50. One call, many forms: that is polymorphism.
- The other direction does not work: `Letter wrong = plain;` does not compile. A shipment is not always a letter.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
Shipment S-001 for Leo, Boston
Letter L-100 for Nadia, Austin
Parcel P-200 for Omar, Denver
Freight F-300 for Victor, Seattle
Plain shipment: 5.00
Letter: 4.50
Parcel: 11.50
Freight: 120.00
Next: 1.50
Next: 11.50
```

Commit and push: "feat: hold a child object in a Shipment variable"

## Step 19 - One list for every kind of shipment

### Type this

In `Main`, add these two lines under `package com.pluralsight;`:

<!-- apply: after Main.java | package com.pluralsight; -->
```java

import java.util.ArrayList;
import java.util.List;
```

Then replace everything inside `main` from `System.out.println(plain);` down to the last `Next:` print line with these lines. The welcome line and the four lines that create the shipments stay.

<!-- apply: replace Main.java | System.out.println(plain); >> next = parcel; -->
```java
        // One list, different types inside
        List<Shipment> shipments = new ArrayList<>();
        shipments.add(plain);
        shipments.add(letter);
        shipments.add(parcel);
        shipments.add(freight);

        for (Shipment shipment : shipments) {
            System.out.printf("%s: %.2f%n", shipment, shipment.getCost());   // the object decides which getCost() runs
        }
```

<!-- apply: delete Main.java | System.out.printf("Next: %.2f%n", next.getCost()); -->

### What is happening

- `List<Shipment>` is a list of base class references. Each slot can hold any child of `Shipment`, so a shipment, a letter, a parcel and freight sit in the same list.
- Without inheritance you would need one list per class and one loop per list.
- The loop variable is a `Shipment`. The loop does not know and does not ask what kind each one is. It calls `getCost()`, and every object answers with its own version.
- `%s` prints the shipment as text, which means its `toString`. `%.2f` prints the cost.
- Add a fifth kind of shipment one day and this loop does not change by one character.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
Shipment S-001 for Leo, Boston: 5.00
Letter L-100 for Nadia, Austin: 4.50
Parcel P-200 for Omar, Denver: 11.50
Freight F-300 for Victor, Seattle: 120.00
```

Commit and push: "feat: put every shipment in one list"

## Step 20 - The total

### Type this

In `Main`, add this line just above `import java.util.ArrayList;`:

<!-- apply: before Main.java | import java.util.ArrayList; -->
```java
import java.math.BigDecimal;
```

Add this line just above the `for` line:

<!-- apply: before Main.java | for (Shipment shipment : shipments) { -->
```java
        BigDecimal total = new BigDecimal("0.00");
```

Add this line inside the loop, under the `printf` line:

<!-- apply: after Main.java | System.out.printf("%s: %.2f%n", shipment, shipment.getCost()); -->
```java
            total = total.add(shipment.getCost());
```

And this line after the `}` that closes the loop:

<!-- apply: after Main.java | total = total.add(shipment.getCost()); >> } -->
```java
        System.out.printf("Total: %.2f%n", total);
```

### What is happening

- `total` starts at `0.00` and grows by one cost on every turn of the loop.
- You cannot write `total += ...` with a BigDecimal. You call `add`, and `add` returns a new BigDecimal, so the result goes back into `total`. Write `total.add(...)` alone on a line and the total stays at zero.
- Four different `getCost` methods feed one sum: 5.00 + 4.50 + 11.50 + 120.00.
- The line that adds does not know there are letters or pallets in the world. It only knows that every `Shipment` can say what it costs.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
Shipment S-001 for Leo, Boston: 5.00
Letter L-100 for Nadia, Austin: 4.50
Parcel P-200 for Omar, Denver: 11.50
Freight F-300 for Victor, Seattle: 120.00
Total: 141.00
```

Commit and push: "feat: add up the total cost"

## Step 21 - instanceof and a downcast

### Type this

In `Main`, add these lines under the `Total:` print line, just above the `}` that closes `main`:

<!-- apply: after Main.java | System.out.printf("Total: -->
```java

        System.out.println("Notes for the driver:");
        for (Shipment shipment : shipments) {
            if (shipment instanceof Parcel) {
                Parcel found = (Parcel) shipment;                // downcast: safe, instanceof just confirmed the type
                System.out.println(found.getDriverNote());
            } else if (shipment instanceof Freight) {
                System.out.println("Book the forklift: " + ((Freight) shipment).getPallets() + " pallets");
            } else {
                System.out.println("Nothing special for " + shipment.getTrackingNumber());
            }
        }
```

### What is happening

- `getDriverNote` exists only in `Parcel` and `getPallets` only in `Freight`. The loop variable is a `Shipment`, so neither can be called on it directly.
- `shipment instanceof Parcel` asks the object a question: are you a Parcel? It answers true or false.
- `(Parcel) shipment` is a downcast: it gives you the same object through a `Parcel` variable, and now the child-only method is reachable. Nothing is copied or converted.
- The `Freight` branch does the same downcast in one line, with the extra pair of parentheses around the cast.
- Always ask with `instanceof` before you cast. `(Parcel) plain` compiles, then crashes at run time with a `ClassCastException`, because that object is not a parcel.
- Use this only for what a child has and the parent does not. For the cost you did not need it: overriding already picked the right method.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
Shipment S-001 for Leo, Boston: 5.00
Letter L-100 for Nadia, Austin: 4.50
Parcel P-200 for Omar, Denver: 11.50
Freight F-300 for Victor, Seattle: 120.00
Total: 141.00
Notes for the driver:
Nothing special for S-001
Nothing special for L-100
Hand P-200 to Omar in Denver
Book the forklift: 2 pallets
```

Commit and push: "feat: print the driver notes with instanceof"

## Step 22 - An abstract class

### Type this

Look at the first line of the list in the output: a shipment that is not a letter, not a parcel and not freight. Nobody walks up to the desk with "a shipment". In `Shipment.java`, replace the line `public class Shipment {` with this:

<!-- apply: replace Shipment.java | public class Shipment { -->
```java
public abstract class Shipment {                 // abstract: always a parent class, never an object of its own
```

`Main` no longer compiles. Replace the line `Shipment plain = new Shipment("S-001", "Leo", "Boston");` with this commented line:

<!-- apply: replace Main.java | Shipment plain = new Shipment("S-001", "Leo", "Boston"); -->
```java
        //Shipment plain = new Shipment("S-001", "Leo", "Boston");   // uncomment: Shipment is abstract; cannot be instantiated
```

And delete the line `shipments.add(plain);`.

<!-- apply: delete Main.java | shipments.add(plain); -->

### What is happening

- An abstract class is a class that exists only to be a parent. `new Shipment(...)` is now a compile error. Remove the `//` to read it, then put the `//` back.
- Everything else still works. `Shipment` keeps its fields, its constructor, its getters and its `toString`, and the children still call `super(...)`. An abstract class has a constructor even though you cannot call it with `new`.
- `Shipment` is still a type. `List<Shipment>` and `for (Shipment shipment : shipments)` did not change: a variable of an abstract type holds objects of its child classes.
- The line for S-001 is gone from the output, and the total dropped by its 5.00.
- A class that can be created with `new` is called a concrete class. `Letter`, `Parcel` and `Freight` are concrete.

### Run it

<!-- run -->
```text
Welcome to the Delivery Desk
Letter L-100 for Nadia, Austin: 4.50
Parcel P-200 for Omar, Denver: 11.50
Freight F-300 for Victor, Seattle: 120.00
Total: 136.00
Notes for the driver:
Nothing special for L-100
Hand P-200 to Omar in Denver
Book the forklift: 2 pallets
```

Commit and push: "refactor: make Shipment an abstract class"

## Step 23 - An abstract method

### Type this

No object uses the 5.00 written in `Shipment` any more: every child class overrides `getCost`. In `Shipment.java`, replace the whole `getCost` method, from its comment line to its closing `}`, with these two lines:

<!-- apply: replace Shipment.java | // What it costs to send. >> } -->
```java
    // Abstract method: no body. Every child class MUST override it.
    public abstract BigDecimal getCost();
```

Do not touch anything else.

### What is happening

- An abstract method has a signature and no body: no braces, a semicolon instead. The parent says "every shipment has a cost" and leaves the "how" to each child.
- It is a rule for the children. Delete `getCost` from `Freight` and the project stops compiling: "Class 'Freight' must either be declared abstract or implement abstract method 'getCost()' in 'Shipment'". Before this step the same mistake would have compiled and quietly charged 5.00 for two pallets.
- Only an abstract class can have abstract methods. That is why Step 22 came first.
- The loop still calls `shipment.getCost()` on a `Shipment` variable. The compiler allows it because it now knows for certain that every concrete child has one.
- An abstract class can mix both kinds: `getCost` is abstract, while `getTrackingNumber`, `setRecipient` and `toString` are finished methods that the children inherit as they are.

### Run it

The output is the same as in Step 22.

<!-- run -->
```text
Welcome to the Delivery Desk
Letter L-100 for Nadia, Austin: 4.50
Parcel P-200 for Omar, Denver: 11.50
Freight F-300 for Victor, Seattle: 120.00
Total: 136.00
Notes for the driver:
Nothing special for L-100
Hand P-200 to Omar in Denver
Book the forklift: 2 pallets
```

Commit and push: "refactor: make getCost an abstract method"

## Check your history

Open the repository on GitHub and look at its commit list. It should show 23 commits, one per step, with the messages from this guide, newest first. Every message starts with a type and a colon; `feat` for the steps that added behavior, `refactor` for the four steps that reshaped the code without adding any, `chore` for the empty project. Open any commit to see exactly what that step changed.

## Final files

These are the five files after Step 23. Compare yours with them line by line and fix any difference.

`src/main/java/com/pluralsight/Main.java`

<!-- final Main.java -->
```java
package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Delivery Desk");

        //Shipment plain = new Shipment("S-001", "Leo", "Boston");   // uncomment: Shipment is abstract; cannot be instantiated
        Letter letter = new Letter("L-100", "Nadia", "Austin", true);
        Parcel parcel = new Parcel("P-200", "Omar", "Denver", 6);
        Freight freight = new Freight("F-300", "Victor", "Seattle", 2);

        // One list, different types inside
        List<Shipment> shipments = new ArrayList<>();
        shipments.add(letter);
        shipments.add(parcel);
        shipments.add(freight);

        BigDecimal total = new BigDecimal("0.00");
        for (Shipment shipment : shipments) {
            System.out.printf("%s: %.2f%n", shipment, shipment.getCost());   // the object decides which getCost() runs
            total = total.add(shipment.getCost());
        }
        System.out.printf("Total: %.2f%n", total);

        System.out.println("Notes for the driver:");
        for (Shipment shipment : shipments) {
            if (shipment instanceof Parcel) {
                Parcel found = (Parcel) shipment;                // downcast: safe, instanceof just confirmed the type
                System.out.println(found.getDriverNote());
            } else if (shipment instanceof Freight) {
                System.out.println("Book the forklift: " + ((Freight) shipment).getPallets() + " pallets");
            } else {
                System.out.println("Nothing special for " + shipment.getTrackingNumber());
            }
        }
    }
}
```

`src/main/java/com/pluralsight/Shipment.java`

<!-- final Shipment.java -->
```java
package com.pluralsight;

import java.math.BigDecimal;

public abstract class Shipment {                 // abstract: always a parent class, never an object of its own
    private String trackingNumber;
    private String recipient;
    protected String destination;                // protected: child classes can use it directly

    public Shipment(String trackingNumber, String recipient, String destination) {
        this.trackingNumber = trackingNumber;
        this.recipient = recipient;
        this.destination = destination;
    }

    public String getTrackingNumber() {
        return this.trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    // Abstract method: no body. Every child class MUST override it.
    public abstract BigDecimal getCost();

    // toString comes from Object, the parent of every class: this is an override too
    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + this.trackingNumber + " for " + this.recipient + ", " + this.destination;
    }
}
```

`src/main/java/com/pluralsight/Letter.java`

<!-- final Letter.java -->
```java
package com.pluralsight;

import java.math.BigDecimal;

public class Letter extends Shipment {
    private boolean registered;

    public Letter(String trackingNumber, String recipient, String destination, boolean registered) {
        super(trackingNumber, recipient, destination);   // calls the Shipment constructor with 3 parameters
        this.registered = registered;
    }

    public boolean isRegistered() {
        return this.registered;
    }

    // Override: same name, same parameters, same return type as getCost() in Shipment
    @Override
    public BigDecimal getCost() {
        BigDecimal cost = new BigDecimal("1.50");
        if (this.registered) {
            cost = cost.add(new BigDecimal("3.00"));     // add returns a new BigDecimal
        }
        return cost;
    }
}
```

`src/main/java/com/pluralsight/Parcel.java`

<!-- final Parcel.java -->
```java
package com.pluralsight;

import java.math.BigDecimal;

public class Parcel extends Shipment {
    private int weightKg;

    public Parcel(String trackingNumber, String recipient, String destination, int weightKg) {
        super(trackingNumber, recipient, destination);
        this.weightKg = weightKg;
    }

    public int getWeightKg() {
        return this.weightKg;
    }

    public void setWeightKg(int weightKg) {
        this.weightKg = weightKg;
    }

    // Only parcels have this method. The fields are private in Shipment, so the child asks the getters.
    public String getDriverNote() {
        //System.out.println(this.trackingNumber);      // uncomment: trackingNumber has private access in Shipment
        return "Hand " + getTrackingNumber() + " to " + getRecipient() + " in " + this.destination;
    }

    // 4.00 to take the parcel, plus 1.25 for every kg
    @Override
    public BigDecimal getCost() {
        BigDecimal weightCost = new BigDecimal("1.25").multiply(BigDecimal.valueOf(this.weightKg));
        return new BigDecimal("4.00").add(weightCost);
    }
}
```

`src/main/java/com/pluralsight/Freight.java`

<!-- final Freight.java -->
```java
package com.pluralsight;

import java.math.BigDecimal;

public class Freight extends Shipment {
    private int pallets;

    public Freight(String trackingNumber, String recipient, String destination, int pallets) {
        super(trackingNumber, recipient, destination);
        this.pallets = pallets;
    }

    public int getPallets() {
        return this.pallets;
    }

    // 60.00 for every pallet
    @Override
    public BigDecimal getCost() {
        return new BigDecimal("60.00").multiply(BigDecimal.valueOf(this.pallets));
    }
}
```

## Challenges

Optional. Do each one in a new project (copy the code in), so this repository stays as it is.

1. Add a fourth child class, `Tube`, for rolled posters. It has a `lengthCm` of its own and costs 3.00 plus 0.05 for every cm. Create one in `Main` and add it to the list. How many lines of the two loops did you have to change?
2. Make `destination` private in `Shipment` and give it a getter. Which lines stop compiling, and in which classes? Fix them without making the field protected again.
3. Add a finished (not abstract) method `isExpensive()` to `Shipment` that returns true when `getCost()` is more than 50.00. Use `compareTo`. Then print `(expensive)` next to the shipments that are. Notice that a method written in the abstract parent calls a method that only the children have a body for.

## Pages that help

- [The Duplication Problem](https://raymaroun.github.io/yearup-java-fall2026/week-06/visuals/01-the-duplication-problem.html): what Steps 3 and 4 made you feel, and what the parent class removes.
- [Is-A](https://raymaroun.github.io/yearup-java-fall2026/week-06/visuals/02-is-a.html): the sentence to say before you write `extends`.
- [What a Child Inherits](https://raymaroun.github.io/yearup-java-fall2026/week-06/visuals/04-what-a-child-inherits.html): fields and methods come down, constructors do not.
- [Private, Protected, Public](https://raymaroun.github.io/yearup-java-fall2026/week-06/visuals/05-private-protected-public.html): who can reach a member, class by class.
- [The Constructor Chain](https://raymaroun.github.io/yearup-java-fall2026/week-06/visuals/06-constructor-chain.html): why the parent constructor runs first.
- [super With Arguments](https://raymaroun.github.io/yearup-java-fall2026/week-06/visuals/07-super-with-arguments.html): passing values up to the parent constructor.
- [Method Overriding](https://raymaroun.github.io/yearup-java-fall2026/week-06/visuals/08-method-overriding.html): the child's version replaces the inherited one.
- [The @Override Annotation](https://raymaroun.github.io/yearup-java-fall2026/week-06/visuals/09-the-override-annotation.html): how a typo becomes a compile error.
- [Base Class References](https://raymaroun.github.io/yearup-java-fall2026/week-06/visuals/11-base-class-references.html): the variable decides what you can call, the object decides what runs.
- [One List, Many Types](https://raymaroun.github.io/yearup-java-fall2026/week-06/visuals/13-heterogeneous-collections.html): a `List<Shipment>` with every kind of shipment inside.
- [instanceof and Downcasting](https://raymaroun.github.io/yearup-java-fall2026/week-06/visuals/14-instanceof-and-downcasting.html): ask first, then cast.
- [Abstract Classes](https://raymaroun.github.io/yearup-java-fall2026/week-06/visuals/15-abstract-classes.html): a parent that can never be an object of its own.
- [Abstract Methods](https://raymaroun.github.io/yearup-java-fall2026/week-06/visuals/16-abstract-methods.html): a method every child must write.
