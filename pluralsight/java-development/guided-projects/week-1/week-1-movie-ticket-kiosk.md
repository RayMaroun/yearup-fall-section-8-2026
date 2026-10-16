# Week 1 Guided Project: Movie Ticket Kiosk

This is a self-study project. You build one small program, step by step, and it uses everything from week 1: variables, types, arithmetic, the Math class, printf, Scanner, if / else, logical operators, String comparison, switch, the ternary operator, and methods.

The program is a kiosk at a cinema. It asks the customer a few questions, works out the price of the tickets, and prints a receipt.

Type every line yourself, run the program at the end of every step, and compare your output with the expected output. If it does not match, compare your code with the step line by line before you move on. Most problems are a missing semicolon, a missing brace, or a capital letter in the wrong place.

Every step uses the same sample customer: name Maya, age 34, 3 tickets, Tuesday, member yes.

Stuck on an error? The Common errors page at the end of this guide lists the usual ones with their fixes.

## Step 1 - Create the project

### Type this

Create a new project in IntelliJ with these settings. Open Advanced Settings to see the GroupId field, and uncheck "Add sample code" so the project starts empty.

```text
Name:                   movie-ticket-kiosk
Build system:           Maven
JDK:                    17
Create Git repository:  ticked
GroupId:                com.pluralsight
```

For Location, use a `guided-projects` folder inside `java-development`, next to your workbook folders: Desktop > pluralsight > java-development > guided-projects. Create the folder if it does not exist yet.

Click Create. Then share the project on GitHub right away: VCS menu, Share Project on GitHub. Keep the repository name `movie-ticket-kiosk`. When IntelliJ asks for the first commit message, use the one at the end of this step.

### What is happening

- Maven is a build tool. It gives every project the same folder layout, so any Java developer knows where to find the code.
- The GroupId `com.pluralsight` becomes the package name. Your class will live in `com.pluralsight`.
- `pom.xml` is the Maven settings file. You do not need to change it this week.
- Create Git repository makes the project a Git repository from the first second. Share Project on GitHub then puts a copy of it on GitHub.
- One project, one repository. This project gets its own repository on GitHub, and nothing else goes in it.

### Run it

Nothing runs yet. Check that the Project panel shows this:

```text
movie-ticket-kiosk  >  src  >  main  >  java
movie-ticket-kiosk  >  pom.xml
```

Commit and push: "Step 1 - Create the project"

From now on, commit and push after every step, with the message given at the end of the step. One step, one commit.

## Step 2 - The class and main

### Type this

Right-click the `java` folder under `src/main`, choose New, Java Class, and type `com.pluralsight.MovieTicketKiosk`. IntelliJ creates the package and the class. Make the file look exactly like this:

```java
package com.pluralsight;

public class MovieTicketKiosk {
    public static void main(String[] args) {
        System.out.println("Welcome to the Movie Ticket Kiosk");
    }
}
```

### What is happening

- `package com.pluralsight;` says which folder the class belongs to. It must be the first line.
- `public class MovieTicketKiosk` must match the file name `MovieTicketKiosk.java` exactly, capitals included.
- `main` is where the program starts. Java looks for this exact header: `public static void main(String[] args)`.
- `System.out.println` prints one line and moves to the next line.
- Every statement ends with a semicolon. Every `{` has a matching `}`.

### Run it

Click the green arrow next to `main` and choose Run.

```text
Welcome to the Movie Ticket Kiosk
```

Commit and push: "Step 2 - The class and main"

## Step 3 - Variables

### Type this

Some of these values are typed in by hand for now. Later steps replace them one at a time with input and calculations. That is normal, the program grows.

Add these lines inside `main`, under the welcome line:

```java
        // Customer details, typed in by hand for now
        String customerName = "Maya";
        int age = 34;
        int ticketCount = 3;
        String dayOfWeek = "Tuesday";
        boolean isMember = true;

        // Prices
        double basePrice = 12.00;
        double dayDiscount = 3.00;
        final double SERVICE_FEE = 1.50;

        // Check the values
        System.out.println("Customer: " + customerName);
        System.out.println("Tickets: " + ticketCount);
        System.out.println("Member: " + isMember);
        System.out.println("Base price: " + basePrice);
```

### What is happening

- A variable is a name for a value. The type in front of the name decides what kind of value it can hold.
- `String` holds text in double quotes. `int` holds a whole number. `double` holds a number with decimals. `boolean` holds `true` or `false`.
- The names say what the value is: `ticketCount`, not `n`. Variables use camelCase.
- `final` means the value is set once and can never change. A `final` constant is named in UPPER_SNAKE_CASE.
- With text on one side, `+` joins instead of adding: `"Age: " + age` becomes `Age: 34`.
- The values are typed in by hand for now. In Steps 7 and 8 the user types them in, and in Steps 9 to 11 the program works some of them out.
- `println` shows a `double` with its raw digits: `12.0`. Step 6 fixes that.

### Run it

```text
Welcome to the Movie Ticket Kiosk
Customer: Maya
Tickets: 3
Member: true
Base price: 12.0
```

Commit and push: "Step 3 - Variables"

## Step 4 - Arithmetic

### Type this

Replace the `// Check the values` comment and the four `println` lines under it with this:

```java
        // Price per ticket after the day discount
        double ticketPrice = basePrice - dayDiscount;
        double subtotal = ticketPrice * ticketCount;
        double memberDiscount = 0.00;                // no member discount yet
        double total = subtotal - memberDiscount + SERVICE_FEE;

        System.out.println("Ticket price: " + ticketPrice);
        System.out.println("Subtotal: " + subtotal);
        System.out.println("Total: " + total);
```

### What is happening

- `-`, `*` and `+` work as in maths. Java does `*` and `/` before `+` and `-`, left to right.
- `basePrice - dayDiscount` is 12.00 minus 3.00, so one ticket costs 9.00.
- `ticketPrice * ticketCount` mixes a `double` and an `int`. The result is a `double`, so no decimals are lost.
- The total takes off the member discount and adds the service fee once, for the whole order.
- `memberDiscount` is 0.00 for now. Step 10 gives it a real value.

### Run it

```text
Welcome to the Movie Ticket Kiosk
Ticket price: 9.0
Subtotal: 27.0
Total: 28.5
```

Commit and push: "Step 4 - Arithmetic"

## Step 5 - The Math class

### Type this

Replace everything from the line `final double SERVICE_FEE = 1.50;` down to the last `println` in `main` with this. The subtotal, member discount and total lines are the same as before.

```java
        final double SERVICE_FEE = 1.50;
        final double MINIMUM_TICKET_PRICE = 5.00;

        // Price per ticket after the day discount, never below the minimum
        double ticketPrice = Math.max(basePrice - dayDiscount, MINIMUM_TICKET_PRICE);
        double subtotal = ticketPrice * ticketCount;
        double memberDiscount = 0.00;                // no member discount yet
        double total = subtotal - memberDiscount + SERVICE_FEE;
        long rewardPoints = Math.round(total);       // one point per dollar, rounded

        System.out.println("Ticket price: " + ticketPrice);
        System.out.println("Subtotal: " + subtotal);
        System.out.println("Total: " + total);
        System.out.println("Reward points: " + rewardPoints);
```

### What is happening

- The Math class holds ready-made maths methods. You call them with `Math.` in front.
- `Math.max(a, b)` returns the larger of the two values. Here it means the ticket price can never drop below 5.00, whatever the discount is.
- For Maya, 9.00 is larger than 5.00, so the ticket price stays 9.00.
- `Math.round` returns the nearest whole number. 28.5 rounds up to 29.
- `Math.round` returns a `long`, a bigger kind of whole number, so `rewardPoints` is a `long`.

### Run it

```text
Welcome to the Movie Ticket Kiosk
Ticket price: 9.0
Subtotal: 27.0
Total: 28.5
Reward points: 29
```

Commit and push: "Step 5 - The Math class"

## Step 6 - Formatted output with printf

### Type this

Replace the four `println` lines at the end of `main` with this receipt:

```java
        // Receipt
        System.out.println();
        System.out.printf("Customer:        %s%n", customerName);
        System.out.printf("Tickets:         %d x $%.2f%n", ticketCount, ticketPrice);
        System.out.printf("Subtotal:        $%.2f%n", subtotal);
        System.out.printf("Member discount: $%.2f%n", memberDiscount);
        System.out.printf("Service fee:     $%.2f%n", SERVICE_FEE);
        System.out.printf("Total:           $%.2f%n", total);
        System.out.printf("Reward points:   %d%n", rewardPoints);
```

### What is happening

- `printf` prints a pattern. Each `%` in the pattern is a hole, filled by the values after the comma, in order.
- `%s` is filled with a String, `%d` with a whole number, `%.2f` with a decimal number shown with 2 digits after the point.
- `%n` ends the line. `printf` does not end the line by itself, unlike `println`.
- The `$` before `%.2f` is plain text. It prints as it is.
- An empty `System.out.println()` prints a blank line, so the receipt stands apart from the welcome line.

### Run it

```text
Welcome to the Movie Ticket Kiosk

Customer:        Maya
Tickets:         3 x $9.00
Subtotal:        $27.00
Member discount: $0.00
Service fee:     $1.50
Total:           $28.50
Reward points:   29
```

Commit and push: "Step 6 - Formatted output with printf"

## Step 7 - Reading the name with Scanner

### Type this

Replace the top of the file, from `package com.pluralsight;` down to the line `String customerName = "Maya";`, with this:

```java
package com.pluralsight;

import java.util.Scanner;

public class MovieTicketKiosk {
    public static void main(String[] args) {
        System.out.println("Welcome to the Movie Ticket Kiosk");

        // One Scanner reads everything the user types
        Scanner scanner = new Scanner(System.in);

        // Customer details
        System.out.print("What is your name? ");
        String customerName = scanner.nextLine();
```

Then add this line at the very end of `main`, after the `Reward points` line:

```java

        scanner.close();                             // done reading: release the keyboard
```

### What is happening

- `import java.util.Scanner;` tells Java where the Scanner class lives. Without it, `Scanner` is an unknown word.
- `Scanner scanner = new Scanner(System.in)` creates one Scanner that reads from the keyboard. The program has exactly one, for its whole life.
- `print` (not `println`) keeps the cursor on the same line, so the user types the answer next to the question.
- `nextLine()` reads a whole line of text. Whatever the user types becomes `customerName`.
- `scanner.close()` is the last line of `main`. It releases the keyboard when the program is done reading.

### Run it

Type `Maya` when the program asks. Your typed answer appears after the question.

```text
Welcome to the Movie Ticket Kiosk
What is your name? Maya

Customer:        Maya
Tickets:         3 x $9.00
Subtotal:        $27.00
Member discount: $0.00
Service fee:     $1.50
Total:           $28.50
Reward points:   29
```

Run it again with your own name. The `Customer` line shows it.

Commit and push: "Step 7 - Reading the name with Scanner"

## Step 8 - Reading numbers and the leftover Enter

### Type this

Replace the four lines from `int age = 34;` down to `boolean isMember = true;` with this:

```java
        System.out.print("How old are you? ");
        int age = scanner.nextInt();
        System.out.print("How many tickets? ");
        int ticketCount = scanner.nextInt();
        scanner.nextLine();                          // eats the Enter left after the last number
        System.out.print("What day is it? ");
        String dayOfWeek = scanner.nextLine();
        System.out.print("Are you a member (yes/no)? ");
        String memberAnswer = scanner.nextLine();
```

### What is happening

- `nextInt()` reads one whole number. It is used for the age and the number of tickets.
- The leftover Enter: when the user types `3` and presses Enter, `nextInt()` takes the 3 and leaves the Enter behind.
- The next `nextLine()` would find that Enter at once and return an empty String. The extra `scanner.nextLine();` after the last `nextInt()` eats the leftover Enter.
- The day and the member answer are text, so they use `nextLine()`.
- `isMember` is gone for now. The program reads the answer as text into `memberAnswer`, and Step 10 turns it into a `boolean`.

### Run it

Type the sample answers when the program asks. Your typed answers appear after each question.

```text
Welcome to the Movie Ticket Kiosk
What is your name? Maya
How old are you? 34
How many tickets? 3
What day is it? Tuesday
Are you a member (yes/no)? yes

Customer:        Maya
Tickets:         3 x $9.00
Subtotal:        $27.00
Member discount: $0.00
Service fee:     $1.50
Total:           $28.50
Reward points:   29
```

Try it: put `//` in front of the `scanner.nextLine();` line that eats the Enter, run again, and watch the day question get skipped. Then remove the `//`.

Commit and push: "Step 8 - Reading numbers and the leftover Enter"

## Step 9 - Choosing a price with if

### Type this

Replace the line `double basePrice = 12.00;` with this:

```java
        // Base price by age: the first true condition wins
        double basePrice;
        if (age < 13) {
            basePrice = 8.50;
        } else if (age < 65) {
            basePrice = 12.00;
        } else {
            basePrice = 9.00;
        }
```

### What is happening

- `double basePrice;` declares the variable without a value. Each branch below gives it one.
- Java tests the conditions from top to bottom. The first one that is true runs, and the rest are skipped.
- `age < 65` does not need `age >= 13` in front of it. Anyone under 13 was already caught by the first branch.
- `else` has no condition. It catches everyone the branches above did not: 65 and over.
- Exactly one branch runs, so `basePrice` always has a value after the `if`.

### Run it

Maya is 34, so the price is the same as before:

```text
Welcome to the Movie Ticket Kiosk
What is your name? Maya
How old are you? 34
How many tickets? 3
What day is it? Tuesday
Are you a member (yes/no)? yes

Customer:        Maya
Tickets:         3 x $9.00
Subtotal:        $27.00
Member discount: $0.00
Service fee:     $1.50
Total:           $28.50
Reward points:   29
```

Run it again with age 10 and then 70. The ticket price changes to $5.50 and then $6.00.

Commit and push: "Step 9 - Choosing a price with if"

## Step 10 - Logical operators and comparing Strings

### Type this

Replace the line `double memberDiscount = 0.00;                // no member discount yet` with this:

```java
        // Members get 10 percent off the tickets, from 3 tickets up
        boolean isMember = memberAnswer.equalsIgnoreCase("yes");
        double memberDiscount = 0.00;
        if (isMember && ticketCount >= 3) {
            memberDiscount = subtotal * 0.10;
        }
```

### What is happening

- `equalsIgnoreCase("yes")` compares the letters of two Strings and ignores capitals. `yes`, `Yes` and `YES` all give `true`.
- Never compare Strings with `==`. It does not compare the letters, so it can say `false` even when the text looks the same.
- The result of the comparison is a `boolean`, so it goes straight into `isMember`.
- `&&` means "and": the discount applies only when both sides are true. A member buying 2 tickets gets nothing, and so does a non-member buying 5.
- `memberDiscount` starts at 0.00. The `if` changes it only when the condition is true. The service fee is added after the discount, so it is not discounted.

### Run it

```text
Welcome to the Movie Ticket Kiosk
What is your name? Maya
How old are you? 34
How many tickets? 3
What day is it? Tuesday
Are you a member (yes/no)? yes

Customer:        Maya
Tickets:         3 x $9.00
Subtotal:        $27.00
Member discount: $2.70
Service fee:     $1.50
Total:           $25.80
Reward points:   26
```

Run it again and answer `YES`, then `no`. The first gives the same discount. The second gives $0.00.

Commit and push: "Step 10 - Logical operators and comparing Strings"

## Step 11 - The day discount with switch

### Type this

Replace the line `double dayDiscount = 3.00;` with this:

```java
        // Discount per ticket by day: the text must match exactly
        double dayDiscount;
        switch (dayOfWeek) {
            case "Tuesday" -> dayDiscount = 3.00;
            case "Wednesday" -> dayDiscount = 2.00;
            default -> dayDiscount = 0.00;
        }
```

### What is happening

- `switch` looks at one value, `dayOfWeek`, and runs the case that matches it.
- This is the arrow form: one case, one arrow, one action. No `break` is needed, and nothing falls into the next case.
- `default` runs when no case matches. Every other day gets no discount.
- A `switch` on a String compares the exact text. `tuesday` with a small t does not match `"Tuesday"`, so it goes to `default`.
- The day discount is taken off each ticket, before `Math.max` checks the minimum price.

### Run it

```text
Welcome to the Movie Ticket Kiosk
What is your name? Maya
How old are you? 34
How many tickets? 3
What day is it? Tuesday
Are you a member (yes/no)? yes

Customer:        Maya
Tickets:         3 x $9.00
Subtotal:        $27.00
Member discount: $2.70
Service fee:     $1.50
Total:           $25.80
Reward points:   26
```

Run it again with `Wednesday` (ticket price $10.00), then `Friday` (ticket price $12.00), then `tuesday` with a small t (also $12.00, because the text does not match).

Commit and push: "Step 11 - The day discount with switch"

## Step 12 - The closing line with a ternary

### Type this

Add these lines after the `Reward points` line, before `scanner.close();`:

```java

        // Groups of 4 or more get a different goodbye
        String closingLine = ticketCount >= 4 ? "Enjoy the group night!" : "Enjoy the movie!";
        System.out.println();
        System.out.println("Thank you, " + customerName + ". " + closingLine);
```

### What is happening

- The ternary operator is a one-line `if / else` that picks a value: `condition ? value if true : value if false`.
- Maya buys 3 tickets, so `ticketCount >= 4` is false and she gets `"Enjoy the movie!"`.
- Use the ternary only to choose between two values. When each side needs several lines of work, write an `if / else`.

### Run it

```text
Welcome to the Movie Ticket Kiosk
What is your name? Maya
How old are you? 34
How many tickets? 3
What day is it? Tuesday
Are you a member (yes/no)? yes

Customer:        Maya
Tickets:         3 x $9.00
Subtotal:        $27.00
Member discount: $2.70
Service fee:     $1.50
Total:           $25.80
Reward points:   26

Thank you, Maya. Enjoy the movie!
```

Run it again with 4 tickets. The last line changes to `Enjoy the group night!`.

Commit and push: "Step 12 - The closing line with a ternary"

## Step 13 - A method for the base price

### Type this

`main` now does everything. In this step and the next four, each decision moves into its own method, one at a time, and `main` calls it.

In `main`, delete the `// Base price by age` comment and the whole `if`, down to its last `}`. Put this one line in their place:

```java
        double basePrice = getBasePrice(age);
```

Then add this method below `main`: after the `}` that closes `main`, and before the last `}` of the file.

```java

    // Base price by age: the first true condition wins
    public static double getBasePrice(int age) {
        double basePrice;
        if (age < 13) {
            basePrice = 8.50;
        } else if (age < 65) {
            basePrice = 12.00;
        } else {
            basePrice = 9.00;
        }
        return basePrice;
    }
```

### What is happening

- A method is a named block of code that does one job. `main` now reads `getBasePrice(age)` instead of the whole `if`.
- The header says everything about a method: `public static double getBasePrice(int age)` takes an `int` and returns a `double`.
- The `if` is the same code as before. It moved into the method; it did not change.
- `return` hands the value back to `main`, where it lands in `basePrice`.
- The method sits inside the class but outside `main`. Methods are never written inside other methods.

### Run it

The output does not change. The code is reorganised, and the program does exactly what it did before.

```text
Welcome to the Movie Ticket Kiosk
What is your name? Maya
How old are you? 34
How many tickets? 3
What day is it? Tuesday
Are you a member (yes/no)? yes

Customer:        Maya
Tickets:         3 x $9.00
Subtotal:        $27.00
Member discount: $2.70
Service fee:     $1.50
Total:           $25.80
Reward points:   26

Thank you, Maya. Enjoy the movie!
```

Commit and push: "Step 13 - A method for the base price"

## Step 14 - A method for the day discount

### Type this

In `main`, delete the `// Discount per ticket by day` comment and the whole `switch`, down to its last `}`. Put this one line in their place:

```java
        double dayDiscount = getDayDiscount(dayOfWeek);
```

Then add this method at the bottom of the class, after `getBasePrice` and before the last `}` of the file:

```java

    // Discount per ticket by day: the text must match exactly
    public static double getDayDiscount(String dayOfWeek) {
        double dayDiscount;
        switch (dayOfWeek) {
            case "Tuesday" -> dayDiscount = 3.00;
            case "Wednesday" -> dayDiscount = 2.00;
            default -> dayDiscount = 0.00;
        }
        return dayDiscount;
    }
```

### What is happening

- `getDayDiscount` takes a `String`, the day, and returns a `double`, the discount.
- The `switch` is the same code as before. It moved into the method; it did not change.
- `main` passes `dayOfWeek` in the brackets. Inside the method, that value is the parameter `dayOfWeek`.
- The method ends with `return dayDiscount;`, so the value goes back to `main`.

### Run it

The output does not change.

```text
Welcome to the Movie Ticket Kiosk
What is your name? Maya
How old are you? 34
How many tickets? 3
What day is it? Tuesday
Are you a member (yes/no)? yes

Customer:        Maya
Tickets:         3 x $9.00
Subtotal:        $27.00
Member discount: $2.70
Service fee:     $1.50
Total:           $25.80
Reward points:   26

Thank you, Maya. Enjoy the movie!
```

Commit and push: "Step 14 - A method for the day discount"

## Step 15 - A method for the member discount

### Type this

In `main`, find the `// Members get 10 percent off` comment. Replace that comment, the `isMember` line, the `memberDiscount` line and the whole `if` under them with these two lines:

```java
        boolean isMember = memberAnswer.equalsIgnoreCase("yes");
        double memberDiscount = getMemberDiscount(subtotal, ticketCount, isMember);
```

Then add this method at the bottom of the class, after `getDayDiscount` and before the last `}` of the file:

```java

    // Members get 10 percent off the tickets, from 3 tickets up
    public static double getMemberDiscount(double subtotal, int ticketCount, boolean isMember) {
        double memberDiscount = 0.00;
        if (isMember && ticketCount >= 3) {
            memberDiscount = subtotal * 0.10;
        }
        return memberDiscount;
    }
```

### What is happening

- A method can take more than one parameter. They are separated by commas, and each one has its own type.
- `main` passes the values in the same order as the header: first `subtotal`, then `ticketCount`, then `isMember`.
- The `&&` check is the same code as before. It moved into the method; it did not change.
- `isMember` stays in `main` for now. Step 17 moves the member question into a method too.

### Run it

The output does not change.

```text
Welcome to the Movie Ticket Kiosk
What is your name? Maya
How old are you? 34
How many tickets? 3
What day is it? Tuesday
Are you a member (yes/no)? yes

Customer:        Maya
Tickets:         3 x $9.00
Subtotal:        $27.00
Member discount: $2.70
Service fee:     $1.50
Total:           $25.80
Reward points:   26

Thank you, Maya. Enjoy the movie!
```

Commit and push: "Step 15 - A method for the member discount"

## Step 16 - A method for the closing line

### Type this

In `main`, find the `// Groups of 4 or more` comment. Replace everything from that comment down to the `Thank you` line with these two lines:

```java
        System.out.println();
        System.out.println("Thank you, " + customerName + ". " + getClosingLine(ticketCount));
```

Then add this method at the bottom of the class, after `getMemberDiscount` and before the last `}` of the file:

```java

    // Groups of 4 or more get a different goodbye
    public static String getClosingLine(int ticketCount) {
        return ticketCount >= 4 ? "Enjoy the group night!" : "Enjoy the movie!";
    }
```

### What is happening

- `getClosingLine` returns a `String`, so its header says `String` before the name.
- The ternary is the same code as before. The method returns its result straight away, so it needs no variable.
- A method call can sit inside a longer line. Java runs `getClosingLine(ticketCount)` first, then joins its text to the rest.

### Run it

The output does not change.

```text
Welcome to the Movie Ticket Kiosk
What is your name? Maya
How old are you? 34
How many tickets? 3
What day is it? Tuesday
Are you a member (yes/no)? yes

Customer:        Maya
Tickets:         3 x $9.00
Subtotal:        $27.00
Member discount: $2.70
Service fee:     $1.50
Total:           $25.80
Reward points:   26

Thank you, Maya. Enjoy the movie!
```

Commit and push: "Step 16 - A method for the closing line"

## Step 17 - A method for the member question

### Type this

In `main`, replace the two lines that ask the member question (the `Are you a member` line and the `memberAnswer` line) with this one line:

```java
        boolean isMember = askIsMember(scanner);     // the same Scanner travels to the method
```

Further down, delete the line `boolean isMember = memberAnswer.equalsIgnoreCase("yes");`. The new line above already sets `isMember`.

Then add this method directly below `main`: after the `}` that closes `main`, and above `getBasePrice`.

```java

    // Asks the member question with the Scanner it receives
    public static boolean askIsMember(Scanner scanner) {
        System.out.print("Are you a member (yes/no)? ");
        String memberAnswer = scanner.nextLine();
        return memberAnswer.equalsIgnoreCase("yes");
    }
```

### What is happening

- `askIsMember` needs to read from the keyboard, so it needs a Scanner.
- `main` passes it the one Scanner as a parameter: `askIsMember(scanner)`. The method uses that same Scanner.
- Do not create a second Scanner inside a method. One program, one Scanner, created in `main`.
- The method returns a `boolean`, so the comparison goes straight after `return`.
- `main` now reads like a list of steps. Each decision has its own method with a name that says what it does.

### Run it

The output does not change.

```text
Welcome to the Movie Ticket Kiosk
What is your name? Maya
How old are you? 34
How many tickets? 3
What day is it? Tuesday
Are you a member (yes/no)? yes

Customer:        Maya
Tickets:         3 x $9.00
Subtotal:        $27.00
Member discount: $2.70
Service fee:     $1.50
Total:           $25.80
Reward points:   26

Thank you, Maya. Enjoy the movie!
```

Commit and push: "Step 17 - A method for the member question"

## Check your history

Open the repository on GitHub and look at its commit list. It should show 17 commits, one per step, with the messages from this guide, newest first. Open any commit to see exactly what that step changed.

## Final file

This is the whole program after Step 17. Compare yours with it line by line and fix any difference.

```java
package com.pluralsight;

import java.util.Scanner;

public class MovieTicketKiosk {
    public static void main(String[] args) {
        System.out.println("Welcome to the Movie Ticket Kiosk");

        // One Scanner reads everything the user types
        Scanner scanner = new Scanner(System.in);

        // Customer details
        System.out.print("What is your name? ");
        String customerName = scanner.nextLine();
        System.out.print("How old are you? ");
        int age = scanner.nextInt();
        System.out.print("How many tickets? ");
        int ticketCount = scanner.nextInt();
        scanner.nextLine();                          // eats the Enter left after the last number
        System.out.print("What day is it? ");
        String dayOfWeek = scanner.nextLine();
        boolean isMember = askIsMember(scanner);     // the same Scanner travels to the method

        // Prices
        double basePrice = getBasePrice(age);
        double dayDiscount = getDayDiscount(dayOfWeek);
        final double SERVICE_FEE = 1.50;
        final double MINIMUM_TICKET_PRICE = 5.00;

        // Price per ticket after the day discount, never below the minimum
        double ticketPrice = Math.max(basePrice - dayDiscount, MINIMUM_TICKET_PRICE);
        double subtotal = ticketPrice * ticketCount;
        double memberDiscount = getMemberDiscount(subtotal, ticketCount, isMember);
        double total = subtotal - memberDiscount + SERVICE_FEE;
        long rewardPoints = Math.round(total);       // one point per dollar, rounded

        // Receipt
        System.out.println();
        System.out.printf("Customer:        %s%n", customerName);
        System.out.printf("Tickets:         %d x $%.2f%n", ticketCount, ticketPrice);
        System.out.printf("Subtotal:        $%.2f%n", subtotal);
        System.out.printf("Member discount: $%.2f%n", memberDiscount);
        System.out.printf("Service fee:     $%.2f%n", SERVICE_FEE);
        System.out.printf("Total:           $%.2f%n", total);
        System.out.printf("Reward points:   %d%n", rewardPoints);

        System.out.println();
        System.out.println("Thank you, " + customerName + ". " + getClosingLine(ticketCount));

        scanner.close();                             // done reading: release the keyboard
    }

    // Asks the member question with the Scanner it receives
    public static boolean askIsMember(Scanner scanner) {
        System.out.print("Are you a member (yes/no)? ");
        String memberAnswer = scanner.nextLine();
        return memberAnswer.equalsIgnoreCase("yes");
    }

    // Base price by age: the first true condition wins
    public static double getBasePrice(int age) {
        double basePrice;
        if (age < 13) {
            basePrice = 8.50;
        } else if (age < 65) {
            basePrice = 12.00;
        } else {
            basePrice = 9.00;
        }
        return basePrice;
    }

    // Discount per ticket by day: the text must match exactly
    public static double getDayDiscount(String dayOfWeek) {
        double dayDiscount;
        switch (dayOfWeek) {
            case "Tuesday" -> dayDiscount = 3.00;
            case "Wednesday" -> dayDiscount = 2.00;
            default -> dayDiscount = 0.00;
        }
        return dayDiscount;
    }

    // Members get 10 percent off the tickets, from 3 tickets up
    public static double getMemberDiscount(double subtotal, int ticketCount, boolean isMember) {
        double memberDiscount = 0.00;
        if (isMember && ticketCount >= 3) {
            memberDiscount = subtotal * 0.10;
        }
        return memberDiscount;
    }

    // Groups of 4 or more get a different goodbye
    public static String getClosingLine(int ticketCount) {
        return ticketCount >= 4 ? "Enjoy the group night!" : "Enjoy the movie!";
    }
}
```

## Challenges

Optional. Do each one in a new project (copy the code in), so this repository stays as it is.

1. Change the Tuesday discount to 5.00 and run the program for a 10 year old. Check that `Math.max` keeps the ticket price at $5.00.
2. Make the day question accept `tuesday` and `TUESDAY` too. Hint: change the text before the `switch` looks at it, with a String method you meet in week 2.
3. Add a "Thursday" case that gives 1.00 off, and a matinee question (yes/no) that takes another 1.50 off each ticket.
4. Add a method `printReceipt` that prints the receipt lines, and call it from `main`.

## Pages that help

- [Variables](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/12-variables.html): what a variable is, declared and filled.
- [Data types](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/11-data-types.html): `int`, `double`, `boolean`, `String` and when to use each.
- [final constants](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/13-final-constants.html): values that can never change.
- [+ with text vs numbers](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/15-concat-vs-addition.html): when `+` joins and when it adds.
- [Integer division](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/16-integer-division.html): why `7 / 2` is 3 with two `int` values.
- [printf](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/19-printf-format.html): how `%s`, `%d`, `%.2f` and `%n` fill the pattern.
- [Scanner methods](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/20-scanner-methods.html): `nextLine`, `nextInt` and what each one reads.
- [The leftover Enter](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/21-scanner-buffer.html): why a question gets skipped after `nextInt`.
- [if / else](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/22-if-else-path.html): which path the program takes, and why.
- [&& and ||](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/23-logical-operators.html): combining two conditions.
- [Comparing Strings](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/24-string-comparison.html): `equals`, `equalsIgnoreCase`, and why not `==`.
- [switch](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/29-switch-flow.html): how a switch finds its case.
- [Method signature](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/25-method-signature.html): reading a method header part by part.
- [Splitting code into methods](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/27-method-decomposition.html): turning one long `main` into small methods.
- [One Scanner, three ways](https://raymaroun.github.io/yearup-java-fall2026/week-01/visuals/28-one-scanner.html): why the Scanner is created once and passed as a parameter.
- [Cheat sheet](https://raymaroun.github.io/yearup-java-fall2026/week-01/reference/cheat-sheet.html): the week's syntax on one page.
- [Common errors](https://raymaroun.github.io/yearup-java-fall2026/week-01/reference/common-errors.html): error messages you will meet and how to fix them.
- [Glossary](https://raymaroun.github.io/yearup-java-fall2026/week-01/reference/glossary.html): every new word from the week.
