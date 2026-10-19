## Step 1 - Creating a new project in IntelliJ

Solution:

1. Open IntelliJ and select "New Project".
2. Name: `reinforcement-lab-1-fall-2026`. Build system: Maven. JDK: 17. Tick `Create Git repository`. Advanced Settings: GroupId `com.pluralsight`.
3. Click "Create".
4. Main menu: `VCS` -> `Share Project on GitHub`. Keep the repository name `reinforcement-lab-1-fall-2026`, click `Share`.
5. After every step from now on: commit and push, message = the step heading.

## Step 2 - Adding a new class

Solution:

1. In the project view, right-click on the `com.pluralsight` package.
2. Select "New" -> "Java Class"
3. Enter "MyApplication" as the class name and press Enter.
4. Add the `main` method.

## Step 3 - Creating variables

Solution:

1. In the `MyApplication` class, add the following code:

```java
Scanner scanner = new Scanner(System.in);

System.out.println("Enter your name:");
String name = scanner.nextLine();

System.out.println("Enter your age:");
int age = scanner.nextInt();

System.out.println("Name: " + name);
System.out.println("Age: " + age);
```

## Step 4 - Using the scanner with if statements

Solution:

1. Modify the existing code in `MyApplication` to include the following if statement:

```java
if (age >= 18) {
    System.out.println("You are old enough to vote.");
} else {
    System.out.println("You are not old enough to vote.");
}
```

## Step 5 - Declaring methods and calling them

Solution:

1. Add the following method to `MyApplication`:

```java
public static void printMessage(String name, int age) {
    System.out.println("Hello, " + name + "! You are " + age + " years old.");
}
```

2. Call the `printMessage` method from the main method:

```java
printMessage(name, age);
```

## Step 6 - Dividing the code into chunks of methods

Solution:

1. Identify parts of the code that perform distinct tasks, such as getting user input or printing out messages.
2. Refactor the code by moving these tasks into separate methods.

For example, you could create methods like `getName`, `getAge`, and `printNameAndAge`, and call them from the main method.

```java
package com.pluralsight;

import java.util.Scanner;

public class MyApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = getName(scanner);
        int age = getAge(scanner);

        System.out.println("=============================================");
        printNameAndAge(name, age);
        System.out.println("=============================================");
        printVotingEligibility(age);
        System.out.println("=============================================");
        printMessage(name, age);
    }

    public static String getName(Scanner scanner) {
        System.out.println("Enter your name:");
        return scanner.nextLine();
    }

    public static int getAge(Scanner scanner) {
        System.out.println("Enter your age:");
        return scanner.nextInt();
    }

    public static void printNameAndAge(String name, int age) {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    public static void printVotingEligibility(int age) {
        if (age >= 18) {
            System.out.println("You are old enough to vote.");
        } else {
            System.out.println("You are not old enough to vote.");
        }
    }

    public static void printMessage(String name, int age) {
        System.out.println("Hello, " + name + "! You are " + age + " years old.");
    }
}
```

## Step 7 - Comparing Strings

Solution:

1. Add the following method to your `MyApplication` class:

```java
public static void printWelcomeMessage(String name) {
    if (name.equalsIgnoreCase("Alice")) {
        System.out.println("Welcome, Alice!");
    } else {
        System.out.println("Hello, stranger!");
    }
}
```

2. Call this method from the `main` method:

```java
package com.pluralsight;

import java.util.Scanner;

public class MyApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = getName(scanner);
        int age = getAge(scanner);

        System.out.println("=============================================");
        printNameAndAge(name, age);
        System.out.println("=============================================");
        printVotingEligibility(age);
        System.out.println("=============================================");
        printMessage(name, age);
        System.out.println("=============================================");
        printWelcomeMessage(name);
    }

// The rest of the methods are below
}
```

## Step 8 - Using if statement with logical operators

Solution:

1. Add the following method to your `MyApplication` class:

```java
public static void printDrinkingEligibility(String name, int age) {
    if (name.equalsIgnoreCase("Bob") && age >= 21) {
        System.out.println("You are old enough to drink.");
    } else {
        System.out.println("You are not old enough to drink.");
    }
}
```

2. Call this method from the `main` method:

```java
package com.pluralsight;

import java.util.Scanner;

public class MyApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = getName(scanner);
        int age = getAge(scanner);

        System.out.println("=============================================");
        printNameAndAge(name, age);
        System.out.println("=============================================");
        printVotingEligibility(age);
        System.out.println("=============================================");
        printMessage(name, age);
        System.out.println("=============================================");
        printWelcomeMessage(name);
        System.out.println("=============================================");
        printDrinkingEligibility(name, age);
        System.out.println("=============================================");
    }

// The rest of the methods are below
}
```

## Step 9 - Using the Math library

Solution:

1. Add the following methods to `MyApplication`:

```java
public static double getNumber(Scanner scanner) {
    System.out.println("Enter a number:");
    return scanner.nextDouble();
}

public static double calculateSquareRoot(double number) {
    return Math.sqrt(number);
}
```

2. Call this method from the `main` method and print out the result with `printf`:

```java
package com.pluralsight;

import java.util.Scanner;

public class MyApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = getName(scanner);
        int age = getAge(scanner);

        System.out.println("=============================================");
        printNameAndAge(name, age);
        System.out.println("=============================================");
        printVotingEligibility(age);
        System.out.println("=============================================");
        printMessage(name, age);
        System.out.println("=============================================");
        printWelcomeMessage(name);
        System.out.println("=============================================");
        printDrinkingEligibility(name, age);
        System.out.println("=============================================");

        double number = getNumber(scanner);
        double squareRoot = calculateSquareRoot(number);
        System.out.printf("The square root of %.2f is %.2f%n", number, squareRoot);
        System.out.println("=============================================");
    }

// The rest of the methods are below

}
```

## Step 10 - Using a switch statement

Solution:

1. To add the switch statement based on the user's name, you can modify the `printWelcomeMessage` method like this:

```java
public static void printWelcomeMessage(String name) {
    switch (name) {
        case "Alice" -> System.out.println("Welcome, Alice!");
        case "Bob" -> System.out.println("Hey Bob, do you want to grab a drink?");
        default -> System.out.println("Hello, stranger!");
    }
}
```

Note: `switch` compares the exact text, so "alice" goes to `default`. Next week we learn how to ignore the case.

## Step 11 - Using boolean variables

Solution:

1. Add the following methods to `MyApplication`:

```java
public static int getFirstNumber(Scanner scanner) {
    System.out.println("Enter the first number:");
    return scanner.nextInt();
}

public static int getSecondNumber(Scanner scanner) {
    System.out.println("Enter the second number:");
    return scanner.nextInt();
}

public static boolean isGreaterThan(int firstNumber, int secondNumber) {
    return firstNumber > secondNumber;
}
```

2. Call these methods from the `main` method and print out the result:

```java
package com.pluralsight;

import java.util.Scanner;

public class MyApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = getName(scanner);
        int age = getAge(scanner);

        System.out.println("=============================================");
        printNameAndAge(name, age);
        System.out.println("=============================================");
        printVotingEligibility(age);
        System.out.println("=============================================");
        printMessage(name, age);
        System.out.println("=============================================");
        printWelcomeMessage(name);
        System.out.println("=============================================");
        printDrinkingEligibility(name, age);
        System.out.println("=============================================");

        double number = getNumber(scanner);
        double squareRoot = calculateSquareRoot(number);
        System.out.printf("The square root of %.2f is %.2f%n", number, squareRoot);
        System.out.println("=============================================");

        int firstNumber = getFirstNumber(scanner);
        int secondNumber = getSecondNumber(scanner);
        boolean firstIsBigger = isGreaterThan(firstNumber, secondNumber);
        System.out.println(firstNumber + " is greater than " + secondNumber + ": " + firstIsBigger);
    }
// The rest of the methods are below

}
```

## Step 12 - Using the ternary operator

Solution:

1. Add the ternary operator in the main method:

```java
package com.pluralsight;

import java.util.Scanner;

public class MyApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = getName(scanner);
        int age = getAge(scanner);

        System.out.println("=============================================");
        printNameAndAge(name, age);
        System.out.println("=============================================");
        printVotingEligibility(age);
        System.out.println("=============================================");
        printMessage(name, age);
        System.out.println("=============================================");
        printWelcomeMessage(name);
        System.out.println("=============================================");
        printDrinkingEligibility(name, age);
        System.out.println("=============================================");

        double number = getNumber(scanner);
        double squareRoot = calculateSquareRoot(number);
        System.out.printf("The square root of %.2f is %.2f%n", number, squareRoot);
        System.out.println("=============================================");

        int firstNumber = getFirstNumber(scanner);
        int secondNumber = getSecondNumber(scanner);
        boolean firstIsBigger = isGreaterThan(firstNumber, secondNumber);
        System.out.println(firstNumber + " is greater than " + secondNumber + ": " + firstIsBigger);
        System.out.println("=============================================");

        String welcomeMessage = name.equalsIgnoreCase("Lara") ? "You are not welcome, " + name + "!" : "Welcome, " + name + "!";
        System.out.println(welcomeMessage);
        System.out.println("=============================================");
    }
// The rest of the methods are below

}
```

## Step 13 - Using the Math library (part 2)

Solution:

1. Add the following methods to `MyApplication`:

```java
public static int getMax(int firstNumber, int secondNumber) {
    return Math.max(firstNumber, secondNumber);
}

public static int getMin(int firstNumber, int secondNumber) {
    return Math.min(firstNumber, secondNumber);
}
```

2. Call both methods from the `main` method with the two numbers from Step 11, and print out the results:

```java
int maxNumber = getMax(firstNumber, secondNumber);
int minNumber = getMin(firstNumber, secondNumber);
System.out.println("The larger number is: " + maxNumber);
System.out.println("The smaller number is: " + minNumber);

scanner.close();
```

## Step 14 - Checking the history on GitHub

Solution:

1. Open the repository on GitHub, click the commits count (clock icon) on the main page.
2. Expect one commit per step, newest on top. A missing step means a commit was skipped: commit and push it now.

## Final file

```java
package com.pluralsight;

import java.util.Scanner;

public class MyApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = getName(scanner);
        int age = getAge(scanner);

        System.out.println("=============================================");
        printNameAndAge(name, age);
        System.out.println("=============================================");
        printVotingEligibility(age);
        System.out.println("=============================================");
        printMessage(name, age);
        System.out.println("=============================================");
        printWelcomeMessage(name);
        System.out.println("=============================================");
        printDrinkingEligibility(name, age);
        System.out.println("=============================================");

        double number = getNumber(scanner);
        double squareRoot = calculateSquareRoot(number);
        System.out.printf("The square root of %.2f is %.2f%n", number, squareRoot);
        System.out.println("=============================================");

        int firstNumber = getFirstNumber(scanner);
        int secondNumber = getSecondNumber(scanner);
        boolean firstIsBigger = isGreaterThan(firstNumber, secondNumber);
        System.out.println(firstNumber + " is greater than " + secondNumber + ": " + firstIsBigger);
        System.out.println("=============================================");

        String welcomeMessage = name.equalsIgnoreCase("Lara") ? "You are not welcome, " + name + "!" : "Welcome, " + name + "!";
        System.out.println(welcomeMessage);
        System.out.println("=============================================");

        int maxNumber = getMax(firstNumber, secondNumber);
        int minNumber = getMin(firstNumber, secondNumber);
        System.out.println("The larger number is: " + maxNumber);
        System.out.println("The smaller number is: " + minNumber);

        scanner.close();
    }

    public static String getName(Scanner scanner) {
        System.out.println("Enter your name:");
        return scanner.nextLine();
    }

    public static int getAge(Scanner scanner) {
        System.out.println("Enter your age:");
        return scanner.nextInt();
    }

    public static void printNameAndAge(String name, int age) {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    public static void printVotingEligibility(int age) {
        if (age >= 18) {
            System.out.println("You are old enough to vote.");
        } else {
            System.out.println("You are not old enough to vote.");
        }
    }

    public static void printMessage(String name, int age) {
        System.out.println("Hello, " + name + "! You are " + age + " years old.");
    }

    public static void printWelcomeMessage(String name) {
        switch (name) {
            case "Alice" -> System.out.println("Welcome, Alice!");
            case "Bob" -> System.out.println("Hey Bob, do you want to grab a drink?");
            default -> System.out.println("Hello, stranger!");
        }
    }

    public static void printDrinkingEligibility(String name, int age) {
        if (name.equalsIgnoreCase("Bob") && age >= 21) {
            System.out.println("You are old enough to drink.");
        } else {
            System.out.println("You are not old enough to drink.");
        }
    }

    public static double getNumber(Scanner scanner) {
        System.out.println("Enter a number:");
        return scanner.nextDouble();
    }

    public static double calculateSquareRoot(double number) {
        return Math.sqrt(number);
    }

    public static int getFirstNumber(Scanner scanner) {
        System.out.println("Enter the first number:");
        return scanner.nextInt();
    }

    public static int getSecondNumber(Scanner scanner) {
        System.out.println("Enter the second number:");
        return scanner.nextInt();
    }

    public static boolean isGreaterThan(int firstNumber, int secondNumber) {
        return firstNumber > secondNumber;
    }

    public static int getMax(int firstNumber, int secondNumber) {
        return Math.max(firstNumber, secondNumber);
    }

    public static int getMin(int firstNumber, int secondNumber) {
        return Math.min(firstNumber, secondNumber);
    }
}
```
