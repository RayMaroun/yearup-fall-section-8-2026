# Week 2 Guided Project: Pizza Order Builder

This is a self-study project. You build one small program, step by step, and it uses everything from week 2: String methods, turning text into numbers, splitting text into an array, classes with private fields, constructors and `this`, getters and setters, an overloaded constructor, arrays, for loops, for-each loops, StringBuilder, while loops and do-while loops.

The program takes pizza orders. It asks the customer for a name and the number of pizzas, asks for the size and toppings of each pizza, prints a receipt with the total, and builds a short ticket for the kitchen.

Type every line yourself, run the program at the end of every step, and compare your output with the expected output. If it does not match, compare your code with the step line by line before you move on. Most problems are a missing semicolon, a missing brace, or a capital letter in the wrong place.

Every step uses the same sample customer: name Nadia, 2 pizzas, a large pizza with mushroom and olives, a medium pizza with no toppings, and no second order.

Stuck on an error? The Common errors page at the end of this guide lists the usual ones with their fixes.

## Step 1 - Create the project

### Type this

Create a new project in IntelliJ with these settings. Open Advanced Settings to see the GroupId field, and uncheck "Add sample code" so the project starts empty.

```text
Name:                   pizza-order-builder
Build system:           Maven
JDK:                    17
Create Git repository:  ticked
GroupId:                com.pluralsight
```

For Location, use a `guided-projects` folder inside `java-development`, next to your workbook folders: Desktop > pluralsight > java-development > guided-projects. Create the folder if it does not exist yet.

Click Create. Then share the project on GitHub right away: VCS menu, Share Project on GitHub. Keep the repository name `pizza-order-builder`. When IntelliJ asks for the first commit message, use the one at the end of this step.

### What is happening

- Maven is a build tool. It gives every project the same folder layout, so any Java developer knows where to find the code.
- The GroupId `com.pluralsight` becomes the package name. Your classes will live in `com.pluralsight`.
- `pom.xml` is the Maven settings file. You do not need to change it this week.
- Create Git repository makes the project a Git repository from the first second. Share Project on GitHub then puts a copy of it on GitHub.
- One project, one repository. This project gets its own repository on GitHub, and nothing else goes in it.

### Run it

<!-- no-run -->
Nothing runs yet. Check that the Project panel shows this:

```text
pizza-order-builder  >  src  >  main  >  java
pizza-order-builder  >  pom.xml
```

Commit and push: "Step 1 - Create the project"

From now on, commit and push after every step, with the message given at the end of the step. One step, one commit.

## Step 2 - The class and main

### Type this

Right-click the `java` folder under `src/main`, choose New, Java Class, and type `com.pluralsight.PizzaOrderBuilder`. IntelliJ creates the package and the class. Make the file look exactly like this:

<!-- apply: file PizzaOrderBuilder.java -->
```java
package com.pluralsight;

public class PizzaOrderBuilder {
    public static void main(String[] args) {
        System.out.println("Welcome to the Pizza Order Builder");
    }
}
```

### What is happening

- `package com.pluralsight;` says which folder the class belongs to. It must be the first line.
- `public class PizzaOrderBuilder` must match the file name `PizzaOrderBuilder.java` exactly, capitals included.
- `main` is where the program starts. Java looks for this exact header: `public static void main(String[] args)`.
- `System.out.println` prints one line and moves to the next line.

### Run it

Click the green arrow next to `main` and choose Run.

```text
Welcome to the Pizza Order Builder
```

Commit and push: "Step 2 - The class and main"

## Step 3 - Cleaning up the name with String methods

### Type this

Add this line under `package com.pluralsight;`:

<!-- apply: after PizzaOrderBuilder.java | package com.pluralsight; -->
```java

import java.util.Scanner;
```

Then add these lines inside `main`, under the welcome line:

<!-- apply: after PizzaOrderBuilder.java | System.out.println("Welcome to the Pizza Order Builder"); -->
```java

        // One Scanner reads everything the user types
        Scanner scanner = new Scanner(System.in);

        // The customer name, cleaned up
        System.out.print("What is your name? ");
        String nameInput = scanner.nextLine();
        String customerName = nameInput.trim().toUpperCase();
        System.out.println("Hello, " + customerName + "!");

        scanner.close();                             // done reading: release the keyboard
```

### What is happening

- A String has methods of its own. You call them with a dot after the variable: `nameInput.trim()`.
- `trim()` returns a copy of the text without the spaces at the start and at the end. Spaces in the middle stay.
- `toUpperCase()` returns a copy with every letter in capitals. `toLowerCase()` does the opposite.
- `nameInput.trim().toUpperCase()` chains two calls: `trim()` runs first, and `toUpperCase()` runs on its result.
- These methods never change `nameInput` itself. A String cannot be changed, so each method gives back a new String.

### Run it

Type `Nadia` when the program asks. Your typed answer appears after the question.

<!-- input: Nadia -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
```

Run it again and type a few spaces before `nadia`, all in small letters. The greeting is still `Hello, NADIA!`.

Commit and push: "Step 3 - Cleaning up the name with String methods"

## Step 4 - Reading a number as text

### Type this

Add these lines after the line `System.out.println("Hello, " + customerName + "!");`:

<!-- apply: after PizzaOrderBuilder.java | System.out.println("Hello, " + customerName + "!"); -->
```java

        // Read the count as text, then turn the text into a number
        System.out.print("How many pizzas? ");
        String countText = scanner.nextLine();
        int pizzaCount = Integer.parseInt(countText.trim());
        System.out.println("Pizzas: " + pizzaCount);
```

### What is happening

- `nextLine()` always reads text. When the user types `2`, `countText` holds the String `"2"`, not the number 2.
- `Integer.parseInt` turns text into an `int`. `Double.parseDouble` does the same for a `double`.
- `trim()` comes first because `parseInt` stops the program with an error if the text has a space in it.
- Reading every answer with `nextLine()` means there is never a leftover Enter to eat. Every answer, number or not, is one full line.
- Text and numbers behave differently: `"2" + 1` is `"21"`, but `2 + 1` is 3. That is why the text is turned into a number first.

### Run it

Type the sample answers when the program asks.

<!-- input: Nadia | 2 -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizzas: 2
```

Commit and push: "Step 4 - Reading a number as text"

## Step 5 - Splitting the toppings

### Type this

Add these lines after the line `System.out.println("Pizzas: " + pizzaCount);`:

<!-- apply: after PizzaOrderBuilder.java | System.out.println("Pizzas: " + pizzaCount); -->
```java

        // The toppings, split at each comma
        System.out.print("Toppings (separated by commas)? ");
        String toppingsLine = scanner.nextLine().trim();
        String[] toppings = toppingsLine.split(",");

        // Check the values
        System.out.println("Toppings: " + toppings.length);
        for (int i = 0; i < toppings.length; i++) {
            System.out.println("  " + (i + 1) + ". " + toppings[i].trim());
        }
```

### What is happening

- The program asks about one pizza for now. Step 11 asks about each pizza in turn.
- `split(",")` cuts the text at every comma and returns the pieces as an array of Strings: `String[]`.
- `toppings.length` is the number of pieces in the array. `"mushroom, olives"` gives 2.
- `toppings[0]` is the first piece and `toppings[1]` the second. An index always starts at 0.
- The `for` loop counts `i` from 0 up to `length - 1`, so it visits every piece once. `i + 1` prints the count from 1, which reads better.
- The second piece is `" olives"`, with the space that followed the comma. `trim()` removes it before printing.

### Run it

<!-- input: Nadia | 2 | mushroom, olives -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizzas: 2
Toppings (separated by commas)? mushroom, olives
Toppings: 2
  1. mushroom
  2. olives
```

Run it again with three toppings, for example `ham, pineapple, peppers`. The count and the list follow.

Commit and push: "Step 5 - Splitting the toppings"

## Step 6 - The price by size with switch

### Type this

Add these lines after the line `System.out.println("Pizzas: " + pizzaCount);`, above the toppings:

<!-- apply: after PizzaOrderBuilder.java | System.out.println("Pizzas: " + pizzaCount); -->
```java

        // The size, in lower case so the switch can match it
        System.out.print("Size (small/medium/large)? ");
        String size = scanner.nextLine().trim().toLowerCase();
        double price = getSizePrice(size);
```

Add this line at the end of the check, after the `}` that closes the `for` loop:

<!-- apply: after PizzaOrderBuilder.java | System.out.println("  " + (i + 1) + ". " + toppings[i].trim()); >> } -->
```java
        System.out.printf("Price: $%.2f%n", price);
```

Then add this method at the bottom of the class: after the `}` that closes `main`, above the last `}` of the file.

<!-- apply: end PizzaOrderBuilder.java -->
```java

    // Returns the price of one pizza of the given size
    public static double getSizePrice(String size) {
        double price;
        switch (size) {
            case "small" -> price = 9.00;
            case "medium" -> price = 11.50;
            case "large" -> price = 14.00;
            default -> price = 0.00;
        }
        return price;
    }
```

### What is happening

- `switch` on a String compares the exact text, so `"Large"` would not match `"large"`.
- `toLowerCase()` turns whatever the user types into small letters before the `switch` sees it. `Large`, `LARGE` and `large` all become `large`.
- The method receives the size and returns the price. `main` keeps only the call: `getSizePrice(size)`.
- `default` catches any size the menu does not have, and the price is 0.00.
- `%.2f` shows the price with two digits after the point: `14.0` prints as `14.00`.

### Run it

<!-- input: Nadia | 2 | large | mushroom, olives -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizzas: 2
Size (small/medium/large)? large
Toppings (separated by commas)? mushroom, olives
Toppings: 2
  1. mushroom
  2. olives
Price: $14.00
```

Run it again and type `LARGE`. The price is still $14.00.

Commit and push: "Step 6 - The price by size with switch"

## Step 7 - The Pizza class

### Type this

Right-click the `com.pluralsight` package, choose New, Java Class, and type `Pizza`. Make the file look exactly like this:

<!-- apply: file Pizza.java -->
```java
package com.pluralsight;

public class Pizza {
    // Private fields: only code inside Pizza can use them
    private String size;
    private String toppings;
    private double price;

    // The constructor fills the fields when a pizza is created
    public Pizza(String size, String toppings, double price) {
        this.size = size;
        this.toppings = toppings;
        this.price = price;
    }
}
```

Back in `PizzaOrderBuilder`, add this line under the line `String toppingsLine = scanner.nextLine().trim();`:

<!-- apply: after PizzaOrderBuilder.java | String toppingsLine = scanner.nextLine().trim(); -->
```java
        Pizza pizza = new Pizza(size, toppingsLine, price);
```

### What is happening

- A class describes a new type. `Pizza` groups the three values that belong to one pizza: its size, its toppings and its price.
- The fields are `private`. Code outside the `Pizza` class cannot read or change them directly.
- The constructor has the same name as the class and no return type. It runs once, when `new Pizza(...)` creates an object.
- Inside the constructor, `size` alone means the parameter. `this.size` means the field of this object. `this.size = size;` copies the parameter into the field.
- `new Pizza(size, toppingsLine, price)` creates one Pizza object and stores it in the variable `pizza`.

### Run it

Nothing new prints. The fields are private, so `main` cannot read them yet. Step 8 fixes that.

<!-- input: Nadia | 2 | large | mushroom, olives -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizzas: 2
Size (small/medium/large)? large
Toppings (separated by commas)? mushroom, olives
Toppings: 2
  1. mushroom
  2. olives
Price: $14.00
```

Commit and push: "Step 7 - The Pizza class"

## Step 8 - Getters and setters

### Type this

In `Pizza.java`, add these getters after the `}` that closes the constructor:

<!-- apply: after Pizza.java | public Pizza(String size, String toppings, double price) { >> } -->
```java

    // Getters: read a field from outside the class
    public String getSize() {
        return this.size;
    }

    public String getToppings() {
        return this.toppings;
    }

    public double getPrice() {
        return this.price;
    }
```

Then add these setters after the `}` that closes `getPrice`:

<!-- apply: after Pizza.java | return this.price; >> } -->
```java

    // Setters: change a field from outside the class
    public void setSize(String size) {
        this.size = size;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public void setPrice(double price) {
        this.price = price;
    }
```

In `PizzaOrderBuilder`, add this line under the line `String[] toppings = toppingsLine.split(",");`:

<!-- apply: after PizzaOrderBuilder.java | String[] toppings = toppingsLine.split(","); -->
```java
        pizza.setPrice(pizza.getPrice() + toppings.length * 1.25);   // 1.25 per topping
```

Then replace everything from the line `// Check the values` down to the line `System.out.printf("Price: $%.2f%n", price);` with this:

<!-- apply: replace PizzaOrderBuilder.java | // Check the values | System.out.printf("Price: $%.2f%n", price); -->
```java
        // Read the pizza back through its getters
        System.out.printf("Pizza: %s, %s, $%.2f%n", pizza.getSize(), pizza.getToppings(), pizza.getPrice());
```

### What is happening

- A getter returns the value of one private field. Its name is `get` followed by the field name: `getPrice()`.
- A setter changes the value of one private field. Its name is `set` followed by the field name, and it returns nothing: `void`.
- The fields stay private. Other classes go through the getters and setters, so the `Pizza` class decides what can be read and changed.
- Each topping adds 1.25. `setPrice` replaces the price with the size price plus the topping charge: 14.00 + 2 x 1.25 = 16.50.
- The check lines from Step 5 are gone. The `printf` line reads the finished pizza back through its getters.

### Run it

<!-- input: Nadia | 2 | large | mushroom, olives -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizzas: 2
Size (small/medium/large)? large
Toppings (separated by commas)? mushroom, olives
Pizza: large, mushroom, olives, $16.50
```

Commit and push: "Step 8 - Getters and setters"

## Step 9 - A method that asks for one pizza

### Type this

Add this method at the bottom of the class, under `getSizePrice` and above the last `}` of the file:

<!-- apply: end PizzaOrderBuilder.java -->
```java

    // Asks for the size and toppings of one pizza, and returns it
    public static Pizza askPizza(Scanner scanner) {
        // paste the lines here
        return pizza;
    }
```

Now cut the lines in `main` from `// The size, in lower case so the switch can match it` down to the `pizza.setPrice(...)` line. Where those lines were, type this:

<!-- apply: cut PizzaOrderBuilder.java | // The size, in lower case so the switch can match it | pizza.setPrice(pizza.getPrice() + toppings.length * 1.25);   // 1.25 per topping -->
```java
        // One pizza, asked for by its own method
        Pizza pizza = askPizza(scanner);
```

Then paste the lines you cut in `askPizza`, in place of the comment `// paste the lines here`.

<!-- apply: paste PizzaOrderBuilder.java | // paste the lines here -->

### What is happening

- `askPizza` holds every line about one pizza: the size question, the toppings question, the price and the new `Pizza`.
- The return type is `Pizza`, a class you wrote. A method can return an object just like it returns a `double`.
- `return pizza;` hands the finished object back to `main`, where it lands in the variable `pizza`.
- `main` passes its one Scanner as a parameter. The method uses that same Scanner, and never creates a second one.
- The lines keep their indentation: inside `main` and inside `askPizza` they are both one level deep in a method.

### Run it

The output does not change.

<!-- input: Nadia | 2 | large | mushroom, olives -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizzas: 2
Size (small/medium/large)? large
Toppings (separated by commas)? mushroom, olives
Pizza: large, mushroom, olives, $16.50
```

Commit and push: "Step 9 - A method that asks for one pizza"

## Step 10 - An overloaded constructor

### Type this

In `Pizza.java`, add this second constructor after the `}` that closes the first one:

<!-- apply: after Pizza.java | public Pizza(String size, String toppings, double price) { >> } -->
```java

    // A second constructor for a pizza with no toppings: size and price only
    public Pizza(String size, double price) {
        this.size = size;
        this.toppings = "no toppings";
        this.price = price;
    }
```

In `askPizza`, replace the line `System.out.print("Toppings (separated by commas)? ");` with this:

<!-- apply: replace PizzaOrderBuilder.java | System.out.print("Toppings (separated by commas)? "); | System.out.print("Toppings (separated by commas)? "); -->
```java
        System.out.print("Toppings (separated by commas, or none)? ");
```

Then add these lines under the line `String toppingsLine = scanner.nextLine().trim();`:

<!-- apply: after PizzaOrderBuilder.java | String toppingsLine = scanner.nextLine().trim(); -->
```java
        if (toppingsLine.equalsIgnoreCase("none")) {
            return new Pizza(size, price);               // the size-only constructor
        }
```

### What is happening

- `Pizza` now has two constructors with the same name. They differ in their parameters: three values, or two. This is overloading.
- Java picks the constructor that matches the values you pass. `new Pizza(size, price)` has two values, so it runs the new one.
- The new constructor sets the toppings itself, to `"no toppings"`. The caller does not have to.
- `equalsIgnoreCase("none")` accepts `none`, `None` and `NONE`. Never compare Strings with `==`.
- `return` ends the method at once. A pizza with no toppings skips the topping charge below, so its price is the size price.

### Run it

The sample pizza has toppings, so the output does not change.

<!-- input: Nadia | 2 | large | mushroom, olives -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizzas: 2
Size (small/medium/large)? large
Toppings (separated by commas, or none)? mushroom, olives
Pizza: large, mushroom, olives, $16.50
```

Run it again with `medium` and `none`. The last line is `Pizza: medium, no toppings, $11.50`.

Commit and push: "Step 10 - An overloaded constructor"

## Step 11 - An array of pizzas

### Type this

In `main`, replace everything from the line `System.out.println("Pizzas: " + pizzaCount);` down to the `printf` line that starts with `System.out.printf("Pizza: ` with this:

<!-- apply: replace PizzaOrderBuilder.java | System.out.println("Pizzas: " + pizzaCount); | System.out.printf("Pizza: %s, %s, $%.2f%n", pizza.getSize(), pizza.getToppings(), pizza.getPrice()); -->
```java

        // One slot per pizza, filled one at a time
        Pizza[] pizzas = new Pizza[pizzaCount];
        for (int i = 0; i < pizzas.length; i++) {
            System.out.println("Pizza " + (i + 1) + ":");
            pizzas[i] = askPizza(scanner);
        }

        // The receipt: one line per pizza, read back through the getters
        System.out.println();
        System.out.println("Receipt for " + customerName);
        for (int i = 0; i < pizzas.length; i++) {
            Pizza pizza = pizzas[i];
            System.out.printf("Pizza %d: %s, %s, $%.2f%n", i + 1, pizza.getSize(), pizza.getToppings(), pizza.getPrice());
        }
```

### What is happening

- `new Pizza[pizzaCount]` creates an array with one slot per pizza. Its size is fixed when it is created: 2 slots for Nadia.
- The slots start empty. The first loop fills them one at a time: `pizzas[i] = askPizza(scanner);`.
- `pizzas.length` is the number of slots. Both loops stop at `length`, so the index never goes past the last slot.
- The second loop reads each pizza back out of the array and prints its line through the getters.
- The `Pizzas: 2` check line is gone. The receipt shows every pizza now.

### Run it

<!-- input: Nadia | 2 | large | mushroom, olives | medium | none -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizza 1:
Size (small/medium/large)? large
Toppings (separated by commas, or none)? mushroom, olives
Pizza 2:
Size (small/medium/large)? medium
Toppings (separated by commas, or none)? none

Receipt for NADIA
Pizza 1: large, mushroom, olives, $16.50
Pizza 2: medium, no toppings, $11.50
```

Commit and push: "Step 11 - An array of pizzas"

## Step 12 - The total with a for-each loop

### Type this

Add these lines after the `}` that closes the receipt loop:

<!-- apply: after PizzaOrderBuilder.java | System.out.printf("Pizza %d: %s, %s, $%.2f%n", i + 1, pizza.getSize(), pizza.getToppings(), pizza.getPrice()); >> } -->
```java

        // Add up the prices: for-each visits every pizza in the array
        double total = 0.00;
        for (Pizza pizza : pizzas) {
            total += pizza.getPrice();
        }
        System.out.printf("Total: $%.2f%n", total);
```

### What is happening

- `for (Pizza pizza : pizzas)` reads as "for each Pizza in pizzas". The variable `pizza` holds one pizza per round.
- A for-each loop has no index. Use it when you need every element and do not care about its position.
- `total` starts at 0.00, before the loop. Each round adds one price: `total += pizza.getPrice();` is short for `total = total + pizza.getPrice();`.
- The total is printed once, after the loop, when every price has been added: 16.50 + 11.50 = 28.00.

### Run it

<!-- input: Nadia | 2 | large | mushroom, olives | medium | none -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizza 1:
Size (small/medium/large)? large
Toppings (separated by commas, or none)? mushroom, olives
Pizza 2:
Size (small/medium/large)? medium
Toppings (separated by commas, or none)? none

Receipt for NADIA
Pizza 1: large, mushroom, olives, $16.50
Pizza 2: medium, no toppings, $11.50
Total: $28.00
```

Commit and push: "Step 12 - The total with a for-each loop"

## Step 13 - A kitchen ticket with StringBuilder

### Type this

Add these lines after the line `System.out.printf("Total: $%.2f%n", total);`:

<!-- apply: after PizzaOrderBuilder.java | System.out.printf("Total: $%.2f%n", total); -->
```java

        // A short kitchen ticket: the name, then the first letter of each size
        StringBuilder ticket = new StringBuilder();
        for (Pizza pizza : pizzas) {
            ticket.append("-");
            ticket.append(pizza.getSize().charAt(0));
        }
        ticket.insert(0, customerName);
        System.out.println("Kitchen ticket: " + ticket.toString().toUpperCase());
```

### What is happening

- A StringBuilder holds text that grows. `append` adds to the end of it, without creating a new String each time.
- `charAt(0)` returns the first character of the size: `l` for `large`, `m` for `medium`.
- After the loop the ticket holds `-l-m`. `insert(0, customerName)` puts the name at position 0, the very start: `NADIA-l-m`.
- `toString()` turns the StringBuilder into a normal String, and `toUpperCase()` gives `NADIA-L-M`.

### Run it

<!-- input: Nadia | 2 | large | mushroom, olives | medium | none -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizza 1:
Size (small/medium/large)? large
Toppings (separated by commas, or none)? mushroom, olives
Pizza 2:
Size (small/medium/large)? medium
Toppings (separated by commas, or none)? none

Receipt for NADIA
Pizza 1: large, mushroom, olives, $16.50
Pizza 2: medium, no toppings, $11.50
Total: $28.00
Kitchen ticket: NADIA-L-M
```

Commit and push: "Step 13 - A kitchen ticket with StringBuilder"

## Step 14 - A method for the kitchen ticket

### Type this

Add this method at the bottom of the class, under `askPizza` and above the last `}` of the file:

<!-- apply: end PizzaOrderBuilder.java -->
```java

    // Builds the kitchen ticket, for example NADIA-L-M
    public static String buildTicket(String customerName, Pizza[] pizzas) {
        // paste the lines here
        return ticket.toString().toUpperCase();
    }
```

Now cut the lines in `main` from `StringBuilder ticket = new StringBuilder();` down to `ticket.insert(0, customerName);`.

<!-- apply: cut PizzaOrderBuilder.java | StringBuilder ticket = new StringBuilder(); | ticket.insert(0, customerName); -->

Paste them in `buildTicket`, in place of the comment `// paste the lines here`.

<!-- apply: paste PizzaOrderBuilder.java | // paste the lines here -->

Back in `main`, replace the line `System.out.println("Kitchen ticket: " + ticket.toString().toUpperCase());` with this:

<!-- apply: replace PizzaOrderBuilder.java | System.out.println("Kitchen ticket: " + ticket.toString().toUpperCase()); | System.out.println("Kitchen ticket: " + ticket.toString().toUpperCase()); -->
```java
        System.out.println("Kitchen ticket: " + buildTicket(customerName, pizzas));
```

### What is happening

- `buildTicket` receives two values: the name and the whole array. An array is passed like any other value.
- The method builds the text and returns it as a String. It prints nothing; `main` decides what to do with the result.
- `ticket` now lives inside `buildTicket`. `main` cannot see it any more, which is why the `println` line calls the method instead.
- The comment above the `println` line stays in `main`, where it still describes what the line prints.

### Run it

The output does not change.

<!-- input: Nadia | 2 | large | mushroom, olives | medium | none -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizza 1:
Size (small/medium/large)? large
Toppings (separated by commas, or none)? mushroom, olives
Pizza 2:
Size (small/medium/large)? medium
Toppings (separated by commas, or none)? none

Receipt for NADIA
Pizza 1: large, mushroom, olives, $16.50
Pizza 2: medium, no toppings, $11.50
Total: $28.00
Kitchen ticket: NADIA-L-M
```

Commit and push: "Step 14 - A method for the kitchen ticket"

## Step 15 - A method for the receipt

### Type this

Add this method at the bottom of the class, under `buildTicket` and above the last `}` of the file:

<!-- apply: end PizzaOrderBuilder.java -->
```java

    // Prints one line per pizza, then the total
    public static void printReceipt(String customerName, Pizza[] pizzas) {
        // paste the lines here
    }
```

Now cut the lines in `main` from `// The receipt: one line per pizza, read back through the getters` down to `System.out.printf("Total: $%.2f%n", total);`. Where those lines were, type this:

<!-- apply: cut PizzaOrderBuilder.java | // The receipt: one line per pizza, read back through the getters | System.out.printf("Total: $%.2f%n", total); -->
```java
        // The receipt and the total
        printReceipt(customerName, pizzas);
```

Then paste the lines you cut in `printReceipt`, in place of the comment `// paste the lines here`.

<!-- apply: paste PizzaOrderBuilder.java | // paste the lines here -->

### What is happening

- `printReceipt` prints and returns nothing, so its return type is `void`. `buildTicket` returns its text instead. Both are fine; the name says which one it is.
- The receipt loop and the total loop move together. They both need only the name and the array.
- `total` is a variable inside `printReceipt` now. Nothing in `main` used it, so nothing else changes.
- `main` is short again: questions, the array, the receipt, the ticket.

### Run it

The output does not change.

<!-- input: Nadia | 2 | large | mushroom, olives | medium | none -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizza 1:
Size (small/medium/large)? large
Toppings (separated by commas, or none)? mushroom, olives
Pizza 2:
Size (small/medium/large)? medium
Toppings (separated by commas, or none)? none

Receipt for NADIA
Pizza 1: large, mushroom, olives, $16.50
Pizza 2: medium, no toppings, $11.50
Total: $28.00
Kitchen ticket: NADIA-L-M
```

Commit and push: "Step 15 - A method for the receipt"

## Step 16 - A while loop for an empty name

### Type this

In `main`, add these lines under the line `String customerName = nameInput.trim().toUpperCase();`:

<!-- apply: after PizzaOrderBuilder.java | String customerName = nameInput.trim().toUpperCase(); -->
```java
        while (customerName.length() == 0) {
            System.out.print("Please type a name: ");
            customerName = scanner.nextLine().trim().toUpperCase();
        }
```

### What is happening

- `length()` returns the number of characters in a String. After `trim()`, a name of only spaces has length 0.
- A `while` loop checks its condition first. When the condition is false at the start, the body never runs.
- Nadia's name has 5 characters, so the loop is skipped and the output does not change.
- When the name is empty, the loop asks again, and keeps asking until the name has at least one character.
- The question is inside the loop. Without a new answer inside the body, the condition would stay true forever.

### Run it

<!-- input: Nadia | 2 | large | mushroom, olives | medium | none -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizza 1:
Size (small/medium/large)? large
Toppings (separated by commas, or none)? mushroom, olives
Pizza 2:
Size (small/medium/large)? medium
Toppings (separated by commas, or none)? none

Receipt for NADIA
Pizza 1: large, mushroom, olives, $16.50
Pizza 2: medium, no toppings, $11.50
Total: $28.00
Kitchen ticket: NADIA-L-M
```

Run it again and press Enter without typing a name. The program asks `Please type a name:` until you type one.

Commit and push: "Step 16 - A while loop for an empty name"

## Step 17 - Another order with do-while

### Type this

In `main`, add these lines above the line `// The customer name, cleaned up`:

<!-- apply: before PizzaOrderBuilder.java | // The customer name, cleaned up -->
```java
        // Take orders until the answer is not yes
        String answer;
        do {
```

Indent every line from `// The customer name, cleaned up` down to the `Kitchen ticket` line by four more spaces, so they sit inside the `do` block.

<!-- apply: indent PizzaOrderBuilder.java | // The customer name, cleaned up | System.out.println("Kitchen ticket: " + buildTicket(customerName, pizzas)); -->

Then add these lines after the `Kitchen ticket` line:

<!-- apply: after PizzaOrderBuilder.java | System.out.println("Kitchen ticket: " + buildTicket(customerName, pizzas)); -->
```java

            // Ask before starting again
            System.out.print("Another order (yes/no)? ");
            answer = scanner.nextLine();
        } while (answer.trim().equalsIgnoreCase("yes"));

        System.out.println("Goodbye!");
```

### What is happening

- A `do-while` loop runs its body first and checks the condition after. The body always runs at least once, which is right for the first order.
- `answer` is declared above the `do`, outside the loop. The condition at the bottom can only see variables declared outside the body.
- The condition ends with a semicolon: `} while (...);`. A `while` loop at the top has none.
- Each new order gets a new name, a new array and a new receipt, because all of them are created inside the body.
- `Goodbye!` is printed once, after the loop, when the answer is anything other than yes.

### Run it

<!-- input: Nadia | 2 | large | mushroom, olives | medium | none | no -->
```text
Welcome to the Pizza Order Builder
What is your name? Nadia
Hello, NADIA!
How many pizzas? 2
Pizza 1:
Size (small/medium/large)? large
Toppings (separated by commas, or none)? mushroom, olives
Pizza 2:
Size (small/medium/large)? medium
Toppings (separated by commas, or none)? none

Receipt for NADIA
Pizza 1: large, mushroom, olives, $16.50
Pizza 2: medium, no toppings, $11.50
Total: $28.00
Kitchen ticket: NADIA-L-M
Another order (yes/no)? no
Goodbye!
```

Run it again and answer `yes`. A second order starts with the name question.

Commit and push: "Step 17 - Another order with do-while"

## Check your history

Open the repository on GitHub and look at its commit list. It should show 17 commits, one per step, with the messages from this guide, newest first. Open any commit to see exactly what that step changed.

## Final file

This is the whole program after Step 17. Compare yours with it line by line and fix any difference.

`PizzaOrderBuilder.java`:

<!-- final: PizzaOrderBuilder.java -->
```java
package com.pluralsight;

import java.util.Scanner;

public class PizzaOrderBuilder {
    public static void main(String[] args) {
        System.out.println("Welcome to the Pizza Order Builder");

        // One Scanner reads everything the user types
        Scanner scanner = new Scanner(System.in);

        // Take orders until the answer is not yes
        String answer;
        do {
            // The customer name, cleaned up
            System.out.print("What is your name? ");
            String nameInput = scanner.nextLine();
            String customerName = nameInput.trim().toUpperCase();
            while (customerName.length() == 0) {
                System.out.print("Please type a name: ");
                customerName = scanner.nextLine().trim().toUpperCase();
            }
            System.out.println("Hello, " + customerName + "!");

            // Read the count as text, then turn the text into a number
            System.out.print("How many pizzas? ");
            String countText = scanner.nextLine();
            int pizzaCount = Integer.parseInt(countText.trim());

            // One slot per pizza, filled one at a time
            Pizza[] pizzas = new Pizza[pizzaCount];
            for (int i = 0; i < pizzas.length; i++) {
                System.out.println("Pizza " + (i + 1) + ":");
                pizzas[i] = askPizza(scanner);
            }

            // The receipt and the total
            printReceipt(customerName, pizzas);

            // A short kitchen ticket: the name, then the first letter of each size
            System.out.println("Kitchen ticket: " + buildTicket(customerName, pizzas));

            // Ask before starting again
            System.out.print("Another order (yes/no)? ");
            answer = scanner.nextLine();
        } while (answer.trim().equalsIgnoreCase("yes"));

        System.out.println("Goodbye!");

        scanner.close();                             // done reading: release the keyboard
    }

    // Returns the price of one pizza of the given size
    public static double getSizePrice(String size) {
        double price;
        switch (size) {
            case "small" -> price = 9.00;
            case "medium" -> price = 11.50;
            case "large" -> price = 14.00;
            default -> price = 0.00;
        }
        return price;
    }

    // Asks for the size and toppings of one pizza, and returns it
    public static Pizza askPizza(Scanner scanner) {
        // The size, in lower case so the switch can match it
        System.out.print("Size (small/medium/large)? ");
        String size = scanner.nextLine().trim().toLowerCase();
        double price = getSizePrice(size);

        // The toppings, split at each comma
        System.out.print("Toppings (separated by commas, or none)? ");
        String toppingsLine = scanner.nextLine().trim();
        if (toppingsLine.equalsIgnoreCase("none")) {
            return new Pizza(size, price);               // the size-only constructor
        }
        Pizza pizza = new Pizza(size, toppingsLine, price);
        String[] toppings = toppingsLine.split(",");
        pizza.setPrice(pizza.getPrice() + toppings.length * 1.25);   // 1.25 per topping
        return pizza;
    }

    // Builds the kitchen ticket, for example NADIA-L-M
    public static String buildTicket(String customerName, Pizza[] pizzas) {
        StringBuilder ticket = new StringBuilder();
        for (Pizza pizza : pizzas) {
            ticket.append("-");
            ticket.append(pizza.getSize().charAt(0));
        }
        ticket.insert(0, customerName);
        return ticket.toString().toUpperCase();
    }

    // Prints one line per pizza, then the total
    public static void printReceipt(String customerName, Pizza[] pizzas) {
        // The receipt: one line per pizza, read back through the getters
        System.out.println();
        System.out.println("Receipt for " + customerName);
        for (int i = 0; i < pizzas.length; i++) {
            Pizza pizza = pizzas[i];
            System.out.printf("Pizza %d: %s, %s, $%.2f%n", i + 1, pizza.getSize(), pizza.getToppings(), pizza.getPrice());
        }

        // Add up the prices: for-each visits every pizza in the array
        double total = 0.00;
        for (Pizza pizza : pizzas) {
            total += pizza.getPrice();
        }
        System.out.printf("Total: $%.2f%n", total);
    }
}
```

`Pizza.java`:

<!-- final: Pizza.java -->
```java
package com.pluralsight;

public class Pizza {
    // Private fields: only code inside Pizza can use them
    private String size;
    private String toppings;
    private double price;

    // The constructor fills the fields when a pizza is created
    public Pizza(String size, String toppings, double price) {
        this.size = size;
        this.toppings = toppings;
        this.price = price;
    }

    // A second constructor for a pizza with no toppings: size and price only
    public Pizza(String size, double price) {
        this.size = size;
        this.toppings = "no toppings";
        this.price = price;
    }

    // Getters: read a field from outside the class
    public String getSize() {
        return this.size;
    }

    public String getToppings() {
        return this.toppings;
    }

    public double getPrice() {
        return this.price;
    }

    // Setters: change a field from outside the class
    public void setSize(String size) {
        this.size = size;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
```

## Challenges

Optional. Do each one in a new project (copy the code in), so this repository stays as it is.

1. Add an `"xl"` size at 16.00 to `getSizePrice`, and change the size question to show it.
2. Ask for a tip after the receipt, read it with `nextLine()`, turn it into a `double` with `Double.parseDouble`, and print the total with the tip.
3. Give a 2.00 discount on any pizza whose toppings contain `"pineapple"`. Hint: the `contains` method of String, and the `setPrice` setter.
4. In the toppings loop of Step 5, stop with `break` after the third topping and print `Only 3 toppings per pizza`.

## Pages that help

- [String methods](https://raymaroun.github.io/yearup-java-fall2026/week-02/visuals/02-string-methods.html): `trim`, `toUpperCase`, `length`, `charAt` and the rest.
- [Text to numbers](https://raymaroun.github.io/yearup-java-fall2026/week-02/visuals/03-string-to-number.html): `Integer.parseInt` and `Double.parseDouble`.
- [StringBuilder](https://raymaroun.github.io/yearup-java-fall2026/week-02/visuals/06-stringbuilder.html): `append`, `insert` and `toString`.
- [Classes and objects](https://raymaroun.github.io/yearup-java-fall2026/week-02/visuals/07-class-vs-object.html): one class, many objects.
- [Constructors and this](https://raymaroun.github.io/yearup-java-fall2026/week-02/visuals/09-what-new-does.html): what `this.size = size;` does.
- [Getters and setters](https://raymaroun.github.io/yearup-java-fall2026/week-02/visuals/08-encapsulation.html): reading and changing private fields.
- [Overloading](https://raymaroun.github.io/yearup-java-fall2026/week-02/visuals/11-constructor-overloading.html): two constructors or methods with one name.
- [Loops](https://raymaroun.github.io/yearup-java-fall2026/week-02/visuals/12-loop-types.html): `while`, `do-while`, `for` and when to use each.
- [Arrays](https://raymaroun.github.io/yearup-java-fall2026/week-02/visuals/14-arrays-indexing.html): slots, indexes and `length`.
- [for-each](https://raymaroun.github.io/yearup-java-fall2026/week-02/visuals/15-for-each-loop.html): visiting every element without an index.
- [Cheat sheet](https://raymaroun.github.io/yearup-java-fall2026/week-02/reference/cheat-sheet.html): the week's syntax on one page.
- [Common errors](https://raymaroun.github.io/yearup-java-fall2026/week-02/reference/common-errors.html): error messages you will meet and how to fix them.
- [Glossary](https://raymaroun.github.io/yearup-java-fall2026/week-02/reference/glossary.html): every new word from the week.
