# Week 5 Guided Project: Bike Rental Desk

<!--
Verification markers (read by the checker, invisible on GitHub):
  apply: file / after / before / end / replace  = how the next code block changes a file
  run            = run Main, no typed input
  test           = run the test class in IntelliJ; the block shows the console part of the run panel
  final          = the built file must equal the next block
-->

This is a self-study project. You build one small program, step by step, and it uses everything from week 5: classes with private fields, constructors, getters and setters, a boolean getter, a derived getter, methods that change the object, an overloaded constructor and an overloaded method, JUnit 5 tests written as Arrange, Act, Assert, BigDecimal for money, a static counter, and has-a relationships between classes. It also reuses earlier weeks: ArrayList, for-each, if, and the null check after a lookup.

The program is the front desk of a bike rental shop. A `Bike` knows its model and wheel size and whether it is on the rack. A `Rental` has a bike, a renter, a number of hours and an hourly rate, and works out the cost in cents that never drift. A `RentalShop` has a list of bikes, rents out the first one on the rack, and takes it back. `Main` opens the shop and runs a morning. Two test classes prove that `rentOut` and `getCost` do what they say.

Type every line yourself, run the program at the end of every step, and compare your output with the expected output. If it does not match, compare your code with the step line by line before you move on. Most problems are a missing semicolon, a missing brace, or a capital letter in the wrong place.

Every step uses the same sample data:

- Three bikes: 1 Trek Marlin with 29 inch wheels, 2 Giant Pre with 20 inch wheels, 3 Schwinn Cruiser with 26 inch wheels.
- The hourly rate is 4.50.
- The renters are Maya (3 hours), Omar (2 hours), Lina (1 hour) and Sam, who arrives when every bike is out.

Stuck on an error? The Common errors page at the end of this guide lists the usual ones with their fixes.

A word on commit messages. From Capstone 1 on, every commit message has the shape `type: what changed`, where the type is one of `feat` (new behavior), `fix`, `docs`, `refactor` (same behavior, cleaner code), `chore` (setup, no code change) or `test`. The message at the end of every step already has that shape. Copy it exactly.

## Step 1 - Create the project

### Type this

Create a new project in IntelliJ with these settings. Open Advanced Settings to see the GroupId field, and uncheck "Add sample code" so the project starts empty.

```text
Name:                   bike-rental
Build system:           Maven
JDK:                    17
Create Git repository:  ticked
GroupId:                com.pluralsight
```

For Location, use the `guided-projects` folder inside `java-development`, next to your workbook folders: Desktop > pluralsight > java-development > guided-projects. Create the folder if it does not exist yet.

Click Create. Then share the project on GitHub right away: VCS menu, Share Project on GitHub. Keep the repository name `bike-rental`. When IntelliJ asks for the first commit message, use the one at the end of this step.

### What is happening

- Maven gives every project the same folder layout: your code goes in `src/main/java`, and from Step 9 on your tests go in `src/test/java`.
- The GroupId `com.pluralsight` becomes the package name. Your classes will live in `com.pluralsight`.
- One project, one repository. This project gets its own repository on GitHub, and nothing else goes in it.
- `chore` is the right type for this commit: the project exists, but no program code was written.

### Run it

<!-- no-run -->
Nothing runs yet. Check that the Project panel shows this:

```text
bike-rental  >  src  >  main  >  java
bike-rental  >  pom.xml
```

Commit and push: "chore: create the bike-rental project"

From now on, commit and push after every step, with the message given at the end of the step. One step, one commit.

## Step 2 - The class and main

### Type this

Right-click the `java` folder under `src/main`, choose New, Java Class, and type `com.pluralsight.Main`. IntelliJ creates the package and the class. Make the file look exactly like this:

<!-- apply: file Main.java -->
```java
package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Bike Rental Desk");
    }
}
```

### What is happening

- `package com.pluralsight;` must be the first line. It says which package the class belongs to.
- `public class Main` must match the file name `Main.java` exactly, capitals included.
- `main` is where the program starts. It is `static`, which is a word this week explains in Step 16.

### Run it

Click the green arrow next to `main` and choose Run.

<!-- run -->
```text
Welcome to the Bike Rental Desk
```

Commit and push: "feat: add Main with a welcome line"

## Step 3 - The Bike class

### Type this

Right-click the `com.pluralsight` package, choose New, Java Class, and type `Bike`. Make the file look exactly like this:

<!-- apply: file Bike.java -->
```java
package com.pluralsight;

public class Bike {
    // Private fields: one bike on the rack
    private int id;
    private String model;
    private int wheelInches;

    // The constructor fills the fields when a bike is created
    public Bike(int id, String model, int wheelInches) {
        this.id = id;
        this.model = model;
        this.wheelInches = wheelInches;
    }
}
```

Back in `Main`, add these lines under the welcome line:

<!-- apply: after Main.java | System.out.println("Welcome to the Bike Rental Desk"); -->
```java

        Bike bike = new Bike(1, "Trek Marlin", 29);
        System.out.println("One bike created");
```

### What is happening

- A class is a plan for one kind of thing. `Bike` says what every bike knows: an id, a model and a wheel size.
- The fields are `private`. Only code inside `Bike` can read or change them. `Main` cannot write `bike.model`.
- The constructor has the same name as the class and no return type. It runs once, when `new Bike(...)` is called, and copies the three values into the fields.
- `this.id = id;` means "the field `id` of this bike gets the parameter `id`". Without `this.`, the two names would be the same variable.
- `new Bike(1, "Trek Marlin", 29)` builds one object from the plan. The variable `bike` points at it.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
One bike created
```

Commit and push: "feat: add the Bike class with private fields and a constructor"

## Step 4 - Getters

### Type this

In `Bike.java`, add these getters after the `}` that closes the constructor:

<!-- apply: after Bike.java | this.wheelInches = wheelInches; >> } -->
```java

    // Getters: read a field from outside the class
    public int getId() {
        return this.id;
    }

    public String getModel() {
        return this.model;
    }

    public int getWheelInches() {
        return this.wheelInches;
    }
```

In `Main`, replace the line `System.out.println("One bike created");` with this:

<!-- apply: replace Main.java | System.out.println("One bike created"); -->
```java
        System.out.println("Bike " + bike.getId() + ": " + bike.getModel() + ", " + bike.getWheelInches() + " inch wheels");
```

### What is happening

- Each getter returns one private field. The name is `get` followed by the field name with a capital letter: `wheelInches` becomes `getWheelInches`.
- A getter only reads. It never changes a field and never prints.
- `Main` reads the bike through its getters. That is the only door into the object.
- `bike.getWheelInches()` is an `int`. Adding it to a String with `+` turns it into text for printing.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
Bike 1: Trek Marlin, 29 inch wheels
```

Commit and push: "feat: add getters to Bike"

## Step 5 - A boolean field, its getter and its setter

### Type this

In `Bike.java`, add this field under `private int wheelInches;`:

<!-- apply: after Bike.java | private int wheelInches; -->
```java
    private boolean available;
```

Add this line inside the constructor, under `this.wheelInches = wheelInches;`:

<!-- apply: after Bike.java | this.wheelInches = wheelInches; -->
```java
        this.available = true;                   // a new bike starts on the rack
```

Add these two methods after the `}` that closes `getWheelInches`:

<!-- apply: after Bike.java | return this.wheelInches; >> } -->
```java

    // A boolean getter starts with "is", not "get"
    public boolean isAvailable() {
        return this.available;
    }

    // A setter changes one field from outside the class
    public void setAvailable(boolean available) {
        this.available = available;
    }
```

In `Main`, add these lines under the `Bike 1:` print line:

<!-- apply: after Main.java | " inch wheels"); -->
```java
        System.out.println("Available: " + bike.isAvailable());
        bike.setAvailable(false);
        System.out.println("Available: " + bike.isAvailable());
```

### What is happening

- `available` is a `boolean`: true or false. The constructor sets it to true, so every new bike starts on the rack. Nobody has to pass it in.
- A boolean getter is named `isAvailable`, not `getAvailable`. It reads like a question: is it available?
- A setter is named `set` plus the field name, takes one parameter, returns nothing, and does exactly one thing: store the value.
- The field stays private. `Main` changes it only through `setAvailable`, so `Bike` always knows when its state changes.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
Bike 1: Trek Marlin, 29 inch wheels
Available: true
Available: false
```

Commit and push: "feat: add the available field with its getter and setter"

## Step 6 - A derived getter

### Type this

In `Bike.java`, add this method after the `}` that closes `setAvailable`:

<!-- apply: after Bike.java | this.available = available; >> } -->
```java

    // A derived getter: no field behind it, the answer is worked out from wheelInches
    public boolean isKidsBike() {
        return this.wheelInches < 24;
    }
```

In `Main`, add these lines under the second `Available:` print line:

<!-- apply: after Main.java | System.out.println("Available: " + bike.isAvailable()); >> last -->
```java

        Bike smallBike = new Bike(2, "Giant Pre", 20);
        System.out.println(bike.getModel() + " kids bike: " + bike.isKidsBike());
        System.out.println(smallBike.getModel() + " kids bike: " + smallBike.isKidsBike());
```

### What is happening

- `isKidsBike` looks like a getter but there is no `kidsBike` field. The answer is computed from `wheelInches` every time it is called.
- That is a derived getter. Storing a `kidsBike` field as well would be a second copy of the same fact, and the two could disagree.
- `this.wheelInches < 24` is already a boolean, so it is returned as it is. No `if` needed.
- Two bikes, two objects. `bike` and `smallBike` each have their own `wheelInches`, so the same method gives two different answers.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
Bike 1: Trek Marlin, 29 inch wheels
Available: true
Available: false
Trek Marlin kids bike: false
Giant Pre kids bike: true
```

Commit and push: "feat: add the derived getter isKidsBike"

## Step 7 - Methods that change the bike

### Type this

In `Bike.java`, add these two methods at the bottom of the class, under `isKidsBike`:

<!-- apply: end Bike.java -->
```java

    // Methods: the bike changes its own state and says what happened
    public void rentOut() {
        if (!this.available) {
            System.out.println(this.model + " is already out");
            return;
        }
        this.available = false;
        System.out.println(this.model + " rented out");
    }

    public void bringBack() {
        this.available = true;
        System.out.println(this.model + " is back on the rack");
    }
```

In `Main`, replace the line `bike.setAvailable(false);` with these three lines:

<!-- apply: replace Main.java | bike.setAvailable(false); -->
```java
        bike.rentOut();
        bike.rentOut();
        bike.bringBack();
```

### What is happening

- A setter stores a value and asks no questions. A method can check first: `rentOut` refuses when the bike is already out.
- `return;` leaves the method early. The two lines under it do not run for a bike that is out.
- `!this.available` reads "not available". The `!` flips true to false and false to true.
- The bike changes its own field. `Main` never touches `available` now; it just asks the bike to rent itself out.
- The second `rentOut()` call is refused, which is the whole point of the check.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
Bike 1: Trek Marlin, 29 inch wheels
Available: true
Trek Marlin rented out
Trek Marlin is already out
Trek Marlin is back on the rack
Available: true
Trek Marlin kids bike: false
Giant Pre kids bike: true
```

Commit and push: "feat: add rentOut and bringBack to Bike"

## Step 8 - An overloaded constructor

### Type this

In `Bike.java`, add this second constructor after the `}` that closes the first constructor:

<!-- apply: after Bike.java | this.available = true; >> } -->
```java

    // Overloaded constructor: same name, fewer parameters. Most bikes have 26 inch wheels.
    public Bike(int id, String model) {
        this(id, model, 26);
    }
```

In `Main`, add these lines under the `Giant Pre` print line:

<!-- apply: after Main.java | smallBike.isKidsBike()); -->
```java

        Bike cruiser = new Bike(3, "Schwinn Cruiser");
        System.out.println(cruiser.getModel() + " has " + cruiser.getWheelInches() + " inch wheels");
```

### What is happening

- A class can have more than one constructor as long as the parameter lists differ. Java picks the one that matches the arguments you pass: two values, or three.
- `this(id, model, 26);` calls the other constructor with 26 filled in. The field assignments live in one place, not two.
- That call must be the first line of the constructor. Java insists.
- `new Bike(3, "Schwinn Cruiser")` and `new Bike(1, "Trek Marlin", 29)` build the same kind of object; one just took a shortcut.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
Bike 1: Trek Marlin, 29 inch wheels
Available: true
Trek Marlin rented out
Trek Marlin is already out
Trek Marlin is back on the rack
Available: true
Trek Marlin kids bike: false
Giant Pre kids bike: true
Schwinn Cruiser has 26 inch wheels
```

Commit and push: "feat: add an overloaded Bike constructor with a default wheel size"

## Step 9 - The first JUnit test

### Type this

Open `Bike.java` and click once on the word `Bike` in `public class Bike`. Press Alt+Enter (Option+Enter on a Mac) and choose Create Test. In the dialog:

```text
Testing library:   JUnit5
Class name:        BikeTest
```

If the dialog says "JUnit5 library not found in the module", click Fix. IntelliJ adds the JUnit dependency to `pom.xml`; it looks like this, and the version number may differ from the one on your screen, keep whatever IntelliJ gave you:

```xml
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.2</version>
            <scope>test</scope>
        </dependency>
```

Click OK. IntelliJ creates `src/test/java/com/pluralsight/BikeTest.java`. If a small Maven icon with a refresh arrow appears at the top right of the editor, click it (Load Maven Changes) so the dependency is downloaded. Make the new file look exactly like this:

<!-- apply: file test:BikeTest.java -->
```java
package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BikeTest {

}
```

Then put this method inside the class, between the two braces:

<!-- apply: replace test:BikeTest.java | class BikeTest {\n\n} -->
```java
class BikeTest {

    @Test
    public void rentOut_BikeOnRack_BikeIsNotAvailable() {
        // Arrange
        Bike bike = new Bike(1, "Trek Marlin", 29);

        // Act
        bike.rentOut();

        // Assert
        boolean actual = bike.isAvailable();
        assertFalse(actual);
    }
}
```

### What is happening

- A unit test is a method that checks one thing about one method. `@Test` marks it so the test runner finds it.
- The name has three parts: `rentOut_BikeOnRack_BikeIsNotAvailable`. The method, the state it starts in, the expected result. Someone reading a red test name knows what broke without opening the code.
- Arrange sets up the object. Act calls the one method under test. Assert checks the result. Three parts, in that order, every time.
- `assertFalse(actual)` passes when `actual` is false and fails otherwise. The `import static` line is what lets you write `assertFalse` without a class name in front.
- Tests live in `src/test/java`, in the same package as the class they test. They are not part of the program; `Main` never runs them.

### Run it

Click the green arrow next to `class BikeTest` and choose Run. The run panel opens with a green check next to `BikeTest` and next to `rentOut_BikeOnRack_BikeIsNotAvailable`, and the line `Tests passed: 1 of 1 test` at the top. The console part shows:

<!-- test -->
```text
Trek Marlin rented out
```

Now make it fail on purpose: change `assertFalse(actual)` to `assertTrue(actual)` and run again. The check turns red and the panel says `expected: <true> but was: <false>`. A test that cannot fail proves nothing. Change it back to `assertFalse` before you go on.

Commit and push: "test: add BikeTest for rentOut"

## Step 10 - The Rental class has a Bike

### Type this

Right-click the `com.pluralsight` package, choose New, Java Class, and type `Rental`. Make the file look exactly like this:

<!-- apply: file Rental.java -->
```java
package com.pluralsight;

public class Rental {
    // A rental has a bike: the field holds a whole Bike object, not a copy of its fields
    private Bike bike;
    private String renterName;
    private int hours;

    public Rental(Bike bike, String renterName, int hours) {
        this.bike = bike;
        this.renterName = renterName;
        this.hours = hours;
    }
}
```

In `Main`, replace everything inside `main` from `Bike bike = new Bike(1, "Trek Marlin", 29);` down to the `Schwinn Cruiser` print line with these lines. The welcome line stays.

<!-- apply: replace Main.java | Bike bike = new Bike(1, "Trek Marlin", 29); >> cruiser.getWheelInches() + " inch wheels"); -->
```java
        Bike bike = new Bike(1, "Trek Marlin", 29);
        Rental rental = new Rental(bike, "Maya", 3);
        System.out.println("One rental created");
```

### What is happening

- A field can hold an object, not only an `int` or a `String`. `private Bike bike;` means a rental has a bike. That is a has-a relationship.
- The constructor takes a `Bike` that already exists and keeps a reference to it. There is still only one Trek Marlin; `bike` in `Main` and `bike` inside the rental point at the same object.
- `Rental` does not copy the model or the wheel size. If it needs them, it asks the bike.
- `Main` shrinks on purpose. The bike experiments from Steps 3 to 8 did their job; the program now tells a different story, and the commits keep the old lines.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
One rental created
```

Commit and push: "feat: add the Rental class that has a Bike"

## Step 11 - Getters on Rental, and reaching through

### Type this

In `Rental.java`, add these getters after the `}` that closes the constructor:

<!-- apply: after Rental.java | this.hours = hours; >> } -->
```java

    public Bike getBike() {
        return this.bike;
    }

    public String getRenterName() {
        return this.renterName;
    }

    public int getHours() {
        return this.hours;
    }
```

In `Main`, replace the line `System.out.println("One rental created");` with this:

<!-- apply: replace Main.java | System.out.println("One rental created"); -->
```java
        System.out.println(rental.getRenterName() + " rents " + rental.getBike().getModel() + " for " + rental.getHours() + " hours");
```

### What is happening

- `getBike()` returns the `Bike` object itself, so the caller can ask it questions.
- `rental.getBike().getModel()` is two calls in a row: first get the bike, then get its model. Read it left to right.
- `Rental` has no `getModel()` of its own. The model belongs to the bike, and the bike is the one that answers.
- Three getters, three fields, no logic. Getters stay boring.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
Maya rents Trek Marlin for 3 hours
```

Commit and push: "feat: add getters to Rental"

## Step 12 - The hourly rate as a BigDecimal

### Type this

In `Rental.java`, add this line under `package com.pluralsight;`:

<!-- apply: after Rental.java | package com.pluralsight; -->
```java

import java.math.BigDecimal;
```

Add this field under `private int hours;`:

<!-- apply: after Rental.java | private int hours; -->
```java
    private BigDecimal hourlyRate;               // money is never a double
```

Replace the constructor line `public Rental(Bike bike, String renterName, int hours) {` with this:

<!-- apply: replace Rental.java | public Rental(Bike bike, String renterName, int hours) { -->
```java
    public Rental(Bike bike, String renterName, int hours, BigDecimal hourlyRate) {
```

Add this line inside the constructor, under `this.hours = hours;`:

<!-- apply: after Rental.java | this.hours = hours; -->
```java
        this.hourlyRate = hourlyRate;
```

Add this getter after the `}` that closes `getHours`:

<!-- apply: after Rental.java | return this.hours; >> } -->
```java

    public BigDecimal getHourlyRate() {
        return this.hourlyRate;
    }
```

In `Main`, add the same import under `package com.pluralsight;`, then replace the line `Rental rental = new Rental(bike, "Maya", 3);` with this:

<!-- apply: after Main.java | package com.pluralsight; -->
```java

import java.math.BigDecimal;
```

<!-- apply: replace Main.java | Rental rental = new Rental(bike, "Maya", 3); -->
```java
        Rental rental = new Rental(bike, "Maya", 3, new BigDecimal("4.50"));
```

And add this line under the `rents` print line:

<!-- apply: after Main.java | " hours"); -->
```java
        System.out.println("Rate per hour: " + rental.getHourlyRate());
```

### What is happening

- A `double` cannot hold most money amounts exactly. `0.1 + 0.2` is `0.30000000000000004` in a double. After a thousand additions the cents are wrong.
- `BigDecimal` stores the digits you give it, exactly. It lives in `java.math`, so both files import it.
- `new BigDecimal("4.50")` takes a String on purpose. `new BigDecimal(4.50)` would take a double, and the double is already slightly wrong before BigDecimal sees it.
- It prints `4.50`, not `4.5`. A BigDecimal remembers how many decimal places it was given. That is called its scale.
- The constructor grew by one parameter, so every `new Rental(...)` had to grow too. IntelliJ underlines the old call in red until you fix it.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
Maya rents Trek Marlin for 3 hours
Rate per hour: 4.50
```

Commit and push: "feat: add the hourly rate as a BigDecimal"

## Step 13 - The cost: multiply and setScale

### Type this

In `Rental.java`, add this line under `import java.math.BigDecimal;`:

<!-- apply: after Rental.java | import java.math.BigDecimal; -->
```java
import java.math.RoundingMode;
```

Add this method at the bottom of the class, under `getHourlyRate`:

<!-- apply: end Rental.java -->
```java

    // Cost = rate x hours. multiply returns a new BigDecimal; hourlyRate itself never changes.
    public BigDecimal getCost() {
        BigDecimal cost = this.hourlyRate.multiply(new BigDecimal(this.hours));
        return cost.setScale(2, RoundingMode.HALF_UP);
    }
```

In `Main`, add this line under the `Rate per hour:` print line:

<!-- apply: after Main.java | System.out.println("Rate per hour: " + rental.getHourlyRate()); -->
```java
        System.out.println("Cost: " + rental.getCost());
```

### What is happening

- You cannot write `hourlyRate * hours` with a BigDecimal. You call `multiply`, and both sides must be BigDecimals, so the `int` is wrapped with `new BigDecimal(this.hours)`. An `int` is exact, so that constructor is safe; only a double is not.
- `multiply` does not change `hourlyRate`. It hands back a brand new BigDecimal, which is why the result is stored in `cost`. Call `multiply` and throw the result away, and nothing happens.
- `setScale(2, RoundingMode.HALF_UP)` fixes the answer at two decimal places, rounding half cents up, the way a cashier does. Here `13.50` already has two places, so nothing is rounded; Step 14 shows a case where it matters.
- `getCost` is a derived getter, like `isKidsBike`: no `cost` field, the value is worked out from the rate and the hours each time.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
Maya rents Trek Marlin for 3 hours
Rate per hour: 4.50
Cost: 13.50
```

Commit and push: "feat: compute the rental cost"

## Step 14 - A discount: subtract, and why the rounding mode matters

### Type this

In `Rental.java`, add this method at the bottom of the class, under `getCost`:

<!-- apply: end Rental.java -->
```java

    // fractionOff is 0.15 for 15 percent. The result is rounded to cents, half up.
    public BigDecimal getCostWithDiscount(BigDecimal fractionOff) {
        BigDecimal discount = getCost().multiply(fractionOff);
        return getCost().subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }
```

In `Main`, add this line under the `Cost:` print line:

<!-- apply: after Main.java | System.out.println("Cost: " + rental.getCost()); -->
```java
        System.out.println("Cost with 15% off: " + rental.getCostWithDiscount(new BigDecimal("0.15")));
```

### What is happening

- The discount is `13.50 x 0.15 = 2.0250`. Multiplying adds the decimal places together: two from the cost, two from the fraction, four in all.
- `subtract` gives `11.4750`. Without `setScale` that is exactly what would print, and no till shows four decimals.
- `setScale(2, RoundingMode.HALF_UP)` turns `11.4750` into `11.48`. With `HALF_DOWN` it would be `11.47`. The rounding mode is not a detail: it decides who keeps the half cent, so Java makes you name it.
- `getCost()` is called twice instead of being copied into a field. It is cheap, and there is one place that knows how a cost is computed.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
Maya rents Trek Marlin for 3 hours
Rate per hour: 4.50
Cost: 13.50
Cost with 15% off: 11.48
```

Commit and push: "feat: add a discount rounded to cents"

## Step 15 - Comparing money with compareTo

### Type this

In `Rental.java`, add this method at the bottom of the class, under `getCostWithDiscount`:

<!-- apply: end Rental.java -->
```java

    // compareTo gives a negative number, zero or a positive number. Never use == on BigDecimal.
    public boolean fitsBudget(BigDecimal budget) {
        return getCost().compareTo(budget) <= 0;
    }
```

In `Main`, add these lines under the `Cost with 15% off:` print line:

<!-- apply: after Main.java | rental.getCostWithDiscount(new BigDecimal("0.15"))); -->
```java
        System.out.println("Fits 15.00: " + rental.fitsBudget(new BigDecimal("15.00")));
        System.out.println("Fits 10.00: " + rental.fitsBudget(new BigDecimal("10.00")));
```

### What is happening

- `<` and `>` do not work on BigDecimal, and `==` compares whether two variables point at the same object, which is almost never what you mean.
- `a.compareTo(b)` returns a negative number when `a` is smaller, `0` when they are equal in value, and a positive number when `a` is bigger. `<= 0` therefore means "a is not more than b".
- `equals` is a trap too: `new BigDecimal("13.5").equals(new BigDecimal("13.50"))` is false, because the scales differ. `compareTo` says they are equal. For money, use `compareTo`.
- The method returns the comparison directly. No `if (...) return true; else return false;`.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
Maya rents Trek Marlin for 3 hours
Rate per hour: 4.50
Cost: 13.50
Cost with 15% off: 11.48
Fits 15.00: true
Fits 10.00: false
```

Commit and push: "feat: add fitsBudget using compareTo"

## Step 16 - A static counter

### Type this

In `Rental.java`, add these lines right under `public class Rental {`, above the `bike` field and its comment:

<!-- apply: after Rental.java | public class Rental { -->
```java
    // Static: one copy for the whole class, shared by every rental
    private static int rentalCount = 0;

    private int rentalNumber;
```

Add these two lines inside the constructor, under `this.hourlyRate = hourlyRate;`:

<!-- apply: after Rental.java | this.hourlyRate = hourlyRate; -->
```java
        rentalCount++;                           // one more rental for the class
        this.rentalNumber = rentalCount;         // this rental keeps its own number
```

Add these two methods after the `}` that closes `getHourlyRate`:

<!-- apply: after Rental.java | return this.hourlyRate; >> } -->
```java

    public int getRentalNumber() {
        return this.rentalNumber;
    }

    // A static getter: called on the class, Rental.getRentalCount(), not on one rental
    public static int getRentalCount() {
        return rentalCount;
    }
```

In `Main`, add these lines under the `Fits 10.00:` print line:

<!-- apply: after Main.java | rental.fitsBudget(new BigDecimal("10.00"))); -->
```java

        Bike smallBike = new Bike(2, "Giant Pre", 20);
        Rental second = new Rental(smallBike, "Omar", 2, new BigDecimal("4.50"));
        System.out.println("Rental numbers: " + rental.getRentalNumber() + " and " + second.getRentalNumber());
        System.out.println("Rentals so far: " + Rental.getRentalCount());
```

### What is happening

- Every rental has its own `rentalNumber`, `bike` and `hours`: those are instance fields, one copy per object.
- `rentalCount` is `static`: one copy for the whole class, created when the class is loaded, before any rental exists. Every constructor call adds one to the same counter.
- There is no `this.` in front of `rentalCount`, because it does not belong to this rental. It belongs to `Rental`.
- A static method is called on the class: `Rental.getRentalCount()`. You do not need a rental in hand to ask how many there are. `main` is static for the same reason: it runs before any object exists.
- A static method cannot read instance fields. Inside `getRentalCount`, `this.hours` would not compile: which rental's hours?

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
Maya rents Trek Marlin for 3 hours
Rate per hour: 4.50
Cost: 13.50
Cost with 15% off: 11.48
Fits 15.00: true
Fits 10.00: false
Rental numbers: 1 and 2
Rentals so far: 2
```

Commit and push: "feat: add a static rental counter"

## Step 17 - A test for the money

### Type this

Open `Rental.java`, click once on the word `Rental` in `public class Rental`, press Alt+Enter (Option+Enter on a Mac), choose Create Test, keep JUnit5 and the name `RentalTest`, and click OK. Make the new file look exactly like this:

<!-- apply: file test:RentalTest.java -->
```java
package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalTest {

}
```

Add this line under `import org.junit.jupiter.api.Test;`:

<!-- apply: after test:RentalTest.java | import org.junit.jupiter.api.Test; -->
```java

import java.math.BigDecimal;
```

Then put this method inside the class, between the two braces:

<!-- apply: replace test:RentalTest.java | class RentalTest {\n\n} -->
```java
class RentalTest {

    @Test
    public void getCost_ThreeHoursAtHourlyRate_ReturnsRateTimesHours() {
        // Arrange
        Bike bike = new Bike(1, "Trek Marlin", 29);
        Rental rental = new Rental(bike, "Maya", 3, new BigDecimal("4.50"));
        BigDecimal expected = new BigDecimal("13.50");

        // Act
        BigDecimal actual = rental.getCost();

        // Assert
        assertEquals(expected, actual);
    }
}
```

### What is happening

- Arrange builds the rental and writes down what the right answer is, as a BigDecimal from a String. Act calls `getCost` once. Assert compares.
- `assertEquals(expected, actual)` takes the expected value first. Get them backwards and a failing test reports them the wrong way round.
- `assertEquals` uses `equals`, and for BigDecimal that means the scale counts: `13.5` would not equal `13.50`. The `setScale(2, ...)` in `getCost` is what keeps this test green. Remove it and `4.50 x 3` still prints `13.50`, but a rate of `4.5` would give `13.5` and the test would catch it.
- The test needs a `Bike` to build a `Rental`, but it tests only `getCost`. The bike is part of Arrange, not what is under test.

### Run it

Click the green arrow next to `class RentalTest` and choose Run. The run panel shows a green check next to `getCost_ThreeHoursAtHourlyRate_ReturnsRateTimesHours` and `Tests passed: 1 of 1 test`. The console part is empty: `getCost` prints nothing.

To run every test in the project at once, right-click the `java` folder under `src/test` and choose Run 'All Tests'. The panel shows `Tests passed: 2 of 2 tests`.

<!-- test -->
```text
Trek Marlin rented out
```

Commit and push: "test: add RentalTest for getCost"

## Step 18 - The RentalShop class has a list of bikes

### Type this

Right-click the `com.pluralsight` package, choose New, Java Class, and type `RentalShop`. Make the file look exactly like this:

<!-- apply: file RentalShop.java -->
```java
package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;

public class RentalShop {
    private BigDecimal hourlyRate;
    private ArrayList<Bike> bikes;               // the shop has a list of bikes

    public RentalShop(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
        this.bikes = new ArrayList<>();
    }

    public void addBike(Bike bike) {
        this.bikes.add(bike);
    }
}
```

In `Main`, replace everything inside `main` from `Bike bike = new Bike(1, "Trek Marlin", 29);` down to the `Rentals so far:` print line with these lines. The welcome line and the import stay.

<!-- apply: replace Main.java | Bike bike = new Bike(1, "Trek Marlin", 29); >> System.out.println("Rentals so far: " + Rental.getRentalCount()); -->
```java
        RentalShop shop = new RentalShop(new BigDecimal("4.50"));
        shop.addBike(new Bike(1, "Trek Marlin", 29));
        shop.addBike(new Bike(2, "Giant Pre", 20));
        System.out.println("The shop is open");
```

### What is happening

- A rental has one bike; a shop has many. The field is an `ArrayList<Bike>`, the list from week 3 with `Bike` as the type inside the angle brackets.
- The constructor creates the empty list. A field that is declared but never given `new ArrayList<>()` is `null`, and the first `add` would crash.
- `addBike` is the only way in. Nobody outside the shop can reach the list, so the shop always knows what it holds.
- The hourly rate moves into the shop. Every rental made by this shop will use it, so a rental no longer needs to be told the rate from `Main`.
- `Main` shrinks again. The money lines from Steps 12 to 16 are in the commits and in `RentalTest`.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
The shop is open
```

Commit and push: "feat: add the RentalShop class with a list of bikes"

## Step 19 - Listing the bikes with their status

### Type this

In `RentalShop.java`, add this method at the bottom of the class, under `addBike`:

<!-- apply: end RentalShop.java -->
```java

    // Prints every bike with its status
    public void listBikes() {
        for (Bike bike : this.bikes) {
            String status = "on the rack";
            if (!bike.isAvailable()) {
                status = "out";
            }
            System.out.println(bike.getId() + ") " + bike.getModel() + " - " + status);
        }
    }
```

In `Main`, add this line under `System.out.println("The shop is open");`:

<!-- apply: after Main.java | System.out.println("The shop is open"); -->
```java
        shop.listBikes();
```

### What is happening

- `for (Bike bike : this.bikes)` visits every bike in the list, in the order they were added.
- The shop does not know a bike's status; it asks with `isAvailable()`. The boolean getter from Step 5 earns its keep.
- `status` starts as `"on the rack"` and is changed only when the bike is out. One variable, one print line, no duplicated `println`.
- The shop prints, the bike answers questions. Each class does its own job.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
The shop is open
1) Trek Marlin - on the rack
2) Giant Pre - on the rack
```

Commit and push: "feat: list the bikes with their status"

## Step 20 - An overloaded method

### Type this

In `RentalShop.java`, add this method after the `}` that closes the first `addBike`:

<!-- apply: after RentalShop.java | this.bikes.add(bike); >> } -->
```java

    // Overloaded method: same name, different parameters. It builds the Bike, then calls the other addBike.
    public void addBike(int id, String model, int wheelInches) {
        addBike(new Bike(id, model, wheelInches));
    }
```

In `Main`, add this line under `shop.addBike(new Bike(2, "Giant Pre", 20));`:

<!-- apply: after Main.java | shop.addBike(new Bike(2, "Giant Pre", 20)); -->
```java
        shop.addBike(3, "Schwinn Cruiser", 26);
```

### What is happening

- Two methods named `addBike`, one taking a `Bike` and one taking an `int`, a `String` and an `int`. Java picks by looking at what you pass, exactly as it did for the two constructors in Step 8.
- The new one does not touch the list. It builds the `Bike` and hands it to the first `addBike`. One method owns the list; the other is a convenience.
- The caller gets to choose: build the bike yourself, or let the shop do it.
- Overloading is about the parameter list, never the return type. Two methods with the same name and the same parameters do not compile.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
The shop is open
1) Trek Marlin - on the rack
2) Giant Pre - on the rack
3) Schwinn Cruiser - on the rack
```

Commit and push: "feat: overload addBike to build the bike from its parts"

## Step 21 - Renting the first bike on the rack

### Type this

In `RentalShop.java`, add this method at the bottom of the class, under `listBikes`:

<!-- apply: end RentalShop.java -->
```java

    // Rents the first bike on the rack. Returns null when every bike is out.
    public Rental rentBike(String renterName, int hours) {
        for (Bike bike : this.bikes) {
            if (bike.isAvailable()) {
                bike.rentOut();
                return new Rental(bike, renterName, hours, this.hourlyRate);
            }
        }
        System.out.println("No bike on the rack for " + renterName);
        return null;
    }
```

In `Main`, add these lines under `shop.listBikes();`:

<!-- apply: after Main.java | shop.listBikes(); -->
```java

        Rental maya = shop.rentBike("Maya", 3);
        Rental omar = shop.rentBike("Omar", 2);
        Rental lina = shop.rentBike("Lina", 1);
        Rental sam = shop.rentBike("Sam", 1);
        if (sam == null) {
            System.out.println("Sam goes home");
        }
        System.out.println(maya.getRenterName() + " owes " + maya.getCost());
        System.out.println("Rentals so far: " + Rental.getRentalCount());
        shop.listBikes();
```

### What is happening

- The loop stops at the first available bike. `return` inside the loop leaves the method at once, with a new `Rental` in hand; the rest of the list is never visited.
- The three classes work together: the shop finds the bike, the bike marks itself out, the rental records who has it and for how long, and the shop's rate goes into the rental.
- When the loop finishes without returning, every bike was out. The method says so and returns `null`, which means "no rental".
- Whoever calls `rentBike` must check for `null` before using the result, exactly like a HashMap lookup in week 3. `sam.getCost()` would crash with a `NullPointerException`.
- The counter counts three rentals, not four. Sam's attempt never created a `Rental`.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
The shop is open
1) Trek Marlin - on the rack
2) Giant Pre - on the rack
3) Schwinn Cruiser - on the rack
Trek Marlin rented out
Giant Pre rented out
Schwinn Cruiser rented out
No bike on the rack for Sam
Sam goes home
Maya owes 13.50
Rentals so far: 3
1) Trek Marlin - out
2) Giant Pre - out
3) Schwinn Cruiser - out
```

Commit and push: "feat: rent the first bike on the rack"

## Step 22 - Returning a bike

### Type this

In `RentalShop.java`, add this method at the bottom of the class, under `rentBike`:

<!-- apply: end RentalShop.java -->
```java

    // The rental is over: the bike goes back on the rack and the renter pays
    public void returnBike(Rental rental) {
        rental.getBike().bringBack();
        System.out.println(rental.getRenterName() + " pays " + rental.getCost());
    }
```

In `Main`, add these lines under the last `shop.listBikes();`, just above the `}` that closes `main`:

<!-- apply: after Main.java | shop.listBikes(); >> last -->
```java

        shop.returnBike(maya);
        shop.returnBike(lina);
        shop.listBikes();
```

### What is happening

- The shop takes a `Rental`, not a `Bike`. The rental knows which bike it has, so the shop reaches through: `rental.getBike().bringBack()`.
- `bringBack` from Step 7 flips `available` back to true. The last `listBikes` proves it: Trek Marlin and Schwinn Cruiser are on the rack, Giant Pre is still out with Omar.
- The cost is worked out by the rental, printed by the shop. Nobody stored `13.50` anywhere; `getCost` computed it from the rate and the hours when asked.
- Four classes, each with one job: `Bike` knows itself, `Rental` pairs a bike with a renter and the money, `RentalShop` holds the bikes and runs the desk, `Main` tells the story.

### Run it

<!-- run -->
```text
Welcome to the Bike Rental Desk
The shop is open
1) Trek Marlin - on the rack
2) Giant Pre - on the rack
3) Schwinn Cruiser - on the rack
Trek Marlin rented out
Giant Pre rented out
Schwinn Cruiser rented out
No bike on the rack for Sam
Sam goes home
Maya owes 13.50
Rentals so far: 3
1) Trek Marlin - out
2) Giant Pre - out
3) Schwinn Cruiser - out
Trek Marlin is back on the rack
Maya pays 13.50
Schwinn Cruiser is back on the rack
Lina pays 4.50
1) Trek Marlin - on the rack
2) Giant Pre - out
3) Schwinn Cruiser - on the rack
```

Run all the tests once more (right-click the `java` folder under `src/test`, Run 'All Tests'): `Tests passed: 2 of 2 tests`. The shop changed a lot since Step 9 and the tests still hold.

Commit and push: "feat: return a bike and charge the renter"

## Check your history

Open the repository on GitHub and look at its commit list. It should show 22 commits, one per step, with the messages from this guide, newest first. Every message starts with a type and a colon; `feat` for the steps that added behavior, `test` for the two test classes, `chore` for the empty project. Open any commit to see exactly what that step changed.

## Final files

These are the six files after Step 22. Compare yours with them line by line and fix any difference.

`src/main/java/com/pluralsight/Main.java`

<!-- final Main.java -->
```java
package com.pluralsight;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Bike Rental Desk");

        RentalShop shop = new RentalShop(new BigDecimal("4.50"));
        shop.addBike(new Bike(1, "Trek Marlin", 29));
        shop.addBike(new Bike(2, "Giant Pre", 20));
        shop.addBike(3, "Schwinn Cruiser", 26);
        System.out.println("The shop is open");
        shop.listBikes();

        Rental maya = shop.rentBike("Maya", 3);
        Rental omar = shop.rentBike("Omar", 2);
        Rental lina = shop.rentBike("Lina", 1);
        Rental sam = shop.rentBike("Sam", 1);
        if (sam == null) {
            System.out.println("Sam goes home");
        }
        System.out.println(maya.getRenterName() + " owes " + maya.getCost());
        System.out.println("Rentals so far: " + Rental.getRentalCount());
        shop.listBikes();

        shop.returnBike(maya);
        shop.returnBike(lina);
        shop.listBikes();
    }
}
```

`src/main/java/com/pluralsight/Bike.java`

<!-- final Bike.java -->
```java
package com.pluralsight;

public class Bike {
    // Private fields: one bike on the rack
    private int id;
    private String model;
    private int wheelInches;
    private boolean available;

    // The constructor fills the fields when a bike is created
    public Bike(int id, String model, int wheelInches) {
        this.id = id;
        this.model = model;
        this.wheelInches = wheelInches;
        this.available = true;                   // a new bike starts on the rack
    }

    // Overloaded constructor: same name, fewer parameters. Most bikes have 26 inch wheels.
    public Bike(int id, String model) {
        this(id, model, 26);
    }

    // Getters: read a field from outside the class
    public int getId() {
        return this.id;
    }

    public String getModel() {
        return this.model;
    }

    public int getWheelInches() {
        return this.wheelInches;
    }

    // A boolean getter starts with "is", not "get"
    public boolean isAvailable() {
        return this.available;
    }

    // A setter changes one field from outside the class
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // A derived getter: no field behind it, the answer is worked out from wheelInches
    public boolean isKidsBike() {
        return this.wheelInches < 24;
    }

    // Methods: the bike changes its own state and says what happened
    public void rentOut() {
        if (!this.available) {
            System.out.println(this.model + " is already out");
            return;
        }
        this.available = false;
        System.out.println(this.model + " rented out");
    }

    public void bringBack() {
        this.available = true;
        System.out.println(this.model + " is back on the rack");
    }
}
```

`src/main/java/com/pluralsight/Rental.java`

<!-- final Rental.java -->
```java
package com.pluralsight;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rental {
    // Static: one copy for the whole class, shared by every rental
    private static int rentalCount = 0;

    private int rentalNumber;
    // A rental has a bike: the field holds a whole Bike object, not a copy of its fields
    private Bike bike;
    private String renterName;
    private int hours;
    private BigDecimal hourlyRate;               // money is never a double

    public Rental(Bike bike, String renterName, int hours, BigDecimal hourlyRate) {
        this.bike = bike;
        this.renterName = renterName;
        this.hours = hours;
        this.hourlyRate = hourlyRate;
        rentalCount++;                           // one more rental for the class
        this.rentalNumber = rentalCount;         // this rental keeps its own number
    }

    public Bike getBike() {
        return this.bike;
    }

    public String getRenterName() {
        return this.renterName;
    }

    public int getHours() {
        return this.hours;
    }

    public BigDecimal getHourlyRate() {
        return this.hourlyRate;
    }

    public int getRentalNumber() {
        return this.rentalNumber;
    }

    // A static getter: called on the class, Rental.getRentalCount(), not on one rental
    public static int getRentalCount() {
        return rentalCount;
    }

    // Cost = rate x hours. multiply returns a new BigDecimal; hourlyRate itself never changes.
    public BigDecimal getCost() {
        BigDecimal cost = this.hourlyRate.multiply(new BigDecimal(this.hours));
        return cost.setScale(2, RoundingMode.HALF_UP);
    }

    // fractionOff is 0.15 for 15 percent. The result is rounded to cents, half up.
    public BigDecimal getCostWithDiscount(BigDecimal fractionOff) {
        BigDecimal discount = getCost().multiply(fractionOff);
        return getCost().subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }

    // compareTo gives a negative number, zero or a positive number. Never use == on BigDecimal.
    public boolean fitsBudget(BigDecimal budget) {
        return getCost().compareTo(budget) <= 0;
    }
}
```

`src/main/java/com/pluralsight/RentalShop.java`

<!-- final RentalShop.java -->
```java
package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;

public class RentalShop {
    private BigDecimal hourlyRate;
    private ArrayList<Bike> bikes;               // the shop has a list of bikes

    public RentalShop(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
        this.bikes = new ArrayList<>();
    }

    public void addBike(Bike bike) {
        this.bikes.add(bike);
    }

    // Overloaded method: same name, different parameters. It builds the Bike, then calls the other addBike.
    public void addBike(int id, String model, int wheelInches) {
        addBike(new Bike(id, model, wheelInches));
    }

    // Prints every bike with its status
    public void listBikes() {
        for (Bike bike : this.bikes) {
            String status = "on the rack";
            if (!bike.isAvailable()) {
                status = "out";
            }
            System.out.println(bike.getId() + ") " + bike.getModel() + " - " + status);
        }
    }

    // Rents the first bike on the rack. Returns null when every bike is out.
    public Rental rentBike(String renterName, int hours) {
        for (Bike bike : this.bikes) {
            if (bike.isAvailable()) {
                bike.rentOut();
                return new Rental(bike, renterName, hours, this.hourlyRate);
            }
        }
        System.out.println("No bike on the rack for " + renterName);
        return null;
    }

    // The rental is over: the bike goes back on the rack and the renter pays
    public void returnBike(Rental rental) {
        rental.getBike().bringBack();
        System.out.println(rental.getRenterName() + " pays " + rental.getCost());
    }
}
```

`src/test/java/com/pluralsight/BikeTest.java`

<!-- final test:BikeTest.java -->
```java
package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BikeTest {

    @Test
    public void rentOut_BikeOnRack_BikeIsNotAvailable() {
        // Arrange
        Bike bike = new Bike(1, "Trek Marlin", 29);

        // Act
        bike.rentOut();

        // Assert
        boolean actual = bike.isAvailable();
        assertFalse(actual);
    }
}
```

`src/test/java/com/pluralsight/RentalTest.java`

<!-- final test:RentalTest.java -->
```java
package com.pluralsight;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RentalTest {

    @Test
    public void getCost_ThreeHoursAtHourlyRate_ReturnsRateTimesHours() {
        // Arrange
        Bike bike = new Bike(1, "Trek Marlin", 29);
        Rental rental = new Rental(bike, "Maya", 3, new BigDecimal("4.50"));
        BigDecimal expected = new BigDecimal("13.50");

        // Act
        BigDecimal actual = rental.getCost();

        // Assert
        assertEquals(expected, actual);
    }
}
```

## Challenges

Optional. Do each one in a new project (copy the code in), so this repository stays as it is.

1. Add two tests to `BikeTest`: `isKidsBike_TwentyInchWheels_ReturnsTrue` and `isKidsBike_TwentyNineInchWheels_ReturnsFalse`. One test, one scenario. Then a third: `rentOut_BikeAlreadyOut_BikeStaysOut`.
2. Add a test to `RentalTest` for `getCostWithDiscount` with 15 percent off: the expected value is `11.48`. Then change `HALF_UP` to `HALF_DOWN` in the method and watch the test go red. Change it back.
3. Add a `getTotalDue()` to `Rental` that adds a flat helmet fee of `2.00` to the cost. Use `add`, and keep the two decimal places. Write the test first, then the method.
4. Give `RentalShop` a `rentBike(String renterName, int hours, boolean kidsBike)` overload that rents the first available bike for which `isKidsBike()` matches. Rent a bike for a child when only the Giant Pre is on the rack.
5. Add a static `getBikeCount()` to `Bike` that counts how many bikes were ever created, the way `Rental.getRentalCount()` counts rentals. Where does the `++` go so that the overloaded constructor from Step 8 is counted too, but only once?

## Pages that help

- [Encapsulation](https://raymaroun.github.io/yearup-java-fall2026/week-05/visuals/03-encapsulation.html): why the fields are private and how a getter is the only door in.
- [Derived getters](https://raymaroun.github.io/yearup-java-fall2026/week-05/visuals/05-derived-getters.html): `isAvailable` with a field behind it, `isKidsBike` without one.
- [Boolean getters](https://raymaroun.github.io/yearup-java-fall2026/week-05/visuals/06-boolean-getters.html): isAvailable answers yes or no from a rule.
- [Constructors and this](https://raymaroun.github.io/yearup-java-fall2026/week-05/visuals/04-the-constructor.html): what runs when you write `new`, and what `this.` points at.
- [Overloading](https://raymaroun.github.io/yearup-java-fall2026/week-05/visuals/09-method-overloading.html): same name, different parameter lists, and how Java picks.
- [Arrange, Act, Assert](https://raymaroun.github.io/yearup-java-fall2026/week-05/visuals/12-arrange-act-assert.html): the three parts of a unit test and the `MethodName_StateUnderTest_ExpectedBehavior` name.
- [BigDecimal for money](https://raymaroun.github.io/yearup-java-fall2026/week-05/visuals/14-bigdecimal-operations.html): why a double drifts, the String constructor, `add`, `subtract`, `multiply`, `setScale` and `compareTo`.
- [Static or instance](https://raymaroun.github.io/yearup-java-fall2026/week-05/visuals/15-instance-vs-static.html): one copy for the class or one copy per object.
- [Has-a](https://raymaroun.github.io/yearup-java-fall2026/week-05/visuals/17-has-a-relationship.html): a field that holds an object, and reaching through it.
- [Cheat sheet](https://raymaroun.github.io/yearup-java-fall2026/week-05/reference/cheat-sheet.html): the week's syntax on one page.
- [Common errors](https://raymaroun.github.io/yearup-java-fall2026/week-05/reference/common-errors.html): error messages you will meet and how to fix them.
