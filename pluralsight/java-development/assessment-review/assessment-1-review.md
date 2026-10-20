# Java Basics — Study Guide & Assessment Reference

This guide is designed to help you **study before the assessment** and serve as a **reference during the assessment**. It explains the core concepts, shows canonical examples, and highlights common pitfalls. It does **not** give you direct answers — you'll still need to think through each question. The goal is to make sure that if you understand the material in this guide, you can reason through any question on the assessment.

---

## How to Approach Problems on the Assessment

Before diving into the content, here are some general strategies that will serve you well regardless of the specific question:

### 1. Read the entire code before answering

Don't just look at the first line or the last line. Many questions involve subtle interactions between multiple lines. Read top to bottom, then re-read.

### 2. Trace through code step by step

For loops, conditionals, and method calls, walk through what happens on each iteration or each step. Keep track of variable values as you go. If it helps, write them down on scratch paper.

**Example of tracing:**

```java
int x = 5;
x = x + 2;
x = x * 3;
```

Trace: `x = 5` → `x = 5 + 2 = 7` → `x = 7 * 3 = 21`. Final value: `21`.

### 3. Check array bounds carefully

Arrays in Java are **zero-indexed**. An array of size `n` has valid indices from `0` to `n - 1`. Accessing an index outside this range throws an error.

### 4. Watch for data type mismatches

Java is strict about types. Assigning a `double` to an `int` without a cast will fail. Returning the wrong type from a method will fail. Always check what type is expected.

### 5. Distinguish between declaration, initialization, and assignment

- **Declaration**: telling Java the variable exists and what type it is (`int level;`)
- **Initialization**: giving it a first value (`int level = 7;`)
- **Assignment**: giving it a new value later (`level = 15;`)

### 6. Pay attention to capitalization and syntax

Java is **case-sensitive**. `String` and `string` are not the same. `Class` and `class` are not the same. Missing semicolons, mismatched braces, and wrong capitalization all cause compiler errors.

### 7. When in doubt, check the method signature

If a question involves a method, look at:

- What does it return? (return type)
- What does it take? (parameters)
- Is it `void` or does it return something?

### 8. Don't overthink "trick" options

If an option looks almost right but has something clearly wrong (missing keyword, wrong capitalization, wrong syntax), it's probably the distractor.

---

## 1. Java Fundamentals

### Primitive Data Types

Java has **eight primitive data types**. These are the basic building blocks for storing simple values:

| Type      | Stores             | Example                |
| --------- | ------------------ | ---------------------- |
| `byte`    | Very small integer | `byte b = 10;`         |
| `short`   | Small integer      | `short s = 500;`       |
| `int`     | Standard integer   | `int i = 1000;`        |
| `long`    | Large integer      | `long l = 5000L;`      |
| `float`   | Small decimal      | `float f = 5.7f;`      |
| `double`  | Standard decimal   | `double d = 9.7;`      |
| `char`    | Single character   | `char c = 'A';`        |
| `boolean` | `true` or `false`  | `boolean flag = true;` |

### What is NOT a Primitive?

`String` is **not** a primitive type. It is a **class** (an object type). This is a common source of confusion.

```java
String greeting = "Hello, Java!";  // String is a class, not a primitive
```

### Variable Declaration and Initialization

The general pattern is:

```
<type> <name> = <value>;
```

Examples:

```java
int age = 25;
double temperature = 72.5;
boolean isActive = true;
char initial = 'R';
String name = "Alice";
```

You can also declare first, then assign later:

```java
int age;          // declaration
age = 25;         // assignment
```

### ⚠️ Watch Out For

- Declaring a variable without initializing it and trying to use it immediately in certain contexts will cause an error.
- Using the wrong type for the value: `int temperature = 72.5;` fails because `72.5` is a decimal.
- Capitalization matters: `Integer` (a class) is different from `int` (a primitive).
- Missing semicolons at the end of statements.

### Valid Variable Names

A variable name can contain letters, digits, `_` and `$`, but it **cannot start with a digit** and cannot be a Java keyword.

```java
int total;       // valid
int _total;      // valid
int $total;      // valid (unusual, but legal)
int total2;      // valid
int 2total;      // ❌ starts with a digit
int my-total;    // ❌ hyphen is not allowed
int class;       // ❌ keyword
```

By convention, variable names start with a lowercase letter and use camelCase: `studentCount`, `totalPrice`.

---

## 2. Type Casting

Java is strict about types, but sometimes you need to convert a value from one type to another.

### Implicit Casting (Widening)

Java automatically converts smaller types to larger types when there's no risk of losing information:

```java
int i = 10;
double d = i;     // int automatically becomes double: 10.0
```

This works because a `double` can hold any `int` value without loss.

### Explicit Casting (Narrowing)

Converting from a larger type to a smaller type can lose information, so Java requires you to **explicitly** cast:

```java
double d = 14.8;
int i = (int) d;    // Explicit cast: i becomes 14 (decimal truncated)
```

### What Happens Without a Cast?

```java
double d = 14.8;
int i = d;          // ❌ Compiler error! Cannot assign double to int without cast
```

### ⚠️ Watch Out For

- Assigning a `double` to an `int` directly (without cast) → **compiler error**.
- Casting a `double` to an `int` **truncates** the decimal part, it does not round. `(int) 14.8` is `14`, not `15`.
- Casting can lose precision — be intentional about it.

---

## 3. Operators and Precedence

### Arithmetic Operators

| Operator | Meaning             | Example                            |
| -------- | ------------------- | ---------------------------------- |
| `+`      | Addition            | `5 + 3` → `8`                      |
| `-`      | Subtraction         | `5 - 3` → `2`                      |
| `*`      | Multiplication      | `5 * 3` → `15`                     |
| `/`      | Division            | `10 / 3` → `3` (integer division!) |
| `%`      | Modulus (remainder) | `10 % 3` → `1`                     |

### Integer Division Warning

When both operands are integers, division gives an integer result (decimal is dropped):

```java
int result = 7 / 2;       // result is 3, not 3.5
double result2 = 7.0 / 2; // result2 is 3.5 (at least one operand is double)
```

### Operator Precedence (Order of Operations)

From highest to lowest precedence (for what you need here):

1. Parentheses `( )`
2. Unary operators `++`, `--`, `!`
3. Multiplication, Division, Modulus `*`, `/`, `%`
4. Addition, Subtraction `+`, `-`
5. Comparison `<`, `<=`, `>`, `>=`
6. Equality `==`, `!=`
7. Logical AND `&&`
8. Logical OR `||`
9. Assignment `=`, `+=`, `-=`, `*=`, `/=`

**Example:**

```java
int x = 5 + 2 * 3;      // x is 11 (multiplication first)
int y = (5 + 2) * 3;    // y is 21 (parentheses force addition first)
```

### Comparison Operators

| Operator | Meaning                  |
| -------- | ------------------------ |
| `==`     | Equal to                 |
| `!=`     | Not equal to             |
| `<`      | Less than                |
| `<=`     | Less than or equal to    |
| `>`      | Greater than             |
| `>=`     | Greater than or equal to |

Note: `=>` is **not** a valid Java operator.

### Assignment vs. Equality

- `=` is **assignment**: `x = 5` stores 5 in x.
- `==` is **equality check**: `x == 5` asks "is x equal to 5?" and returns `true` or `false`.

Mixing them up is a very common bug.

### ⚠️ Watch Out For

- Integer division: `5 / 2` is `2`, not `2.5`.
- Precedence mistakes: without parentheses, `*` and `/` always happen before `+` and `-`.
- Using `=` when you meant `==` (or vice versa).

---

## 4. Printing

### `print` vs `println`

- `System.out.print(...)` prints and **stays on the same line**.
- `System.out.println(...)` prints and **moves to the next line**.

```java
System.out.print("A");
System.out.print("B");
System.out.println("C");
System.out.println("D");
```

Output:

```
ABC
D
```

So a loop that uses `println` prints **each value on its own line**; a loop that uses `print` puts them all on **one line**.

### ⚠️ Watch Out For

- Inside a loop, `println` gives **one line per iteration**; `print` keeps everything on **one line**.
- `print` never adds a space or a newline by itself; if you want a separator, print it yourself.

---

## 5. Strings

Strings are objects, not primitives, but they're used so often that they behave in many ways like built-in types.

### String Declaration

```java
String name = "Alice";
String empty = "";
```

### Common String Methods

| Method                     | What it does                               | Example                                  |
| -------------------------- | ------------------------------------------ | ---------------------------------------- |
| `.length()`                | Returns number of characters               | `"Hello".length()` → `5`                 |
| `.charAt(i)`               | Returns character at index `i`             | `"Hello".charAt(0)` → `'H'`              |
| `.substring(start, end)`   | Returns part of the string (end exclusive) | `"Hello".substring(1, 4)` → `"ell"`      |
| `.equals(other)`           | Checks if values are equal                 | `"abc".equals("abc")` → `true`           |
| `.equalsIgnoreCase(other)` | Equals ignoring case                       | `"ABC".equalsIgnoreCase("abc")` → `true` |
| `.toUpperCase()`           | Converts to uppercase                      | `"hi".toUpperCase()` → `"HI"`            |
| `.toLowerCase()`           | Converts to lowercase                      | `"HI".toLowerCase()` → `"hi"`            |

### String Length — Note the Difference!

```java
String s = "Hello";
int len = s.length();      // Method call with parentheses — Strings use .length()

int[] arr = {1, 2, 3};
int arrLen = arr.length;   // Field access with NO parentheses — arrays use .length
```

This is a classic source of confusion:

- **Strings use `.length()`** (method, with parentheses)
- **Arrays use `.length`** (field, no parentheses)

### String Concatenation

Use `+` to join strings:

```java
String first = "Hello";
String second = "World";
String combined = first + " " + second;  // "Hello World"
```

### ⚠️ Watch Out For

- `.length()` on String (parentheses) vs `.length` on array (no parentheses).
- `substring(start, end)` — the `end` index is **exclusive**.
- Using `==` to compare string values (this compares references, not content — use `.equals()` instead).

---

## 6. Arrays

An **array** stores multiple values of the same type in a fixed-size sequence.

### Array Declaration and Initialization

**With initial values:**

```java
int[] temperatures = {68, 72, 75, 80, 77};
String[] colors = {"red", "blue", "green"};
```

**With a specified size (all elements default to 0, null, or false):**

```java
int[] data = new int[4];           // 4 zeros: {0, 0, 0, 0}
String[] labels = new String[3];   // 3 nulls
```

### Array Indexing

Arrays are **zero-indexed**:

- First element: index `0`
- Last element: index `length - 1`

```java
int[] items = {100, 200, 300, 400, 500};
// indices:   0    1    2    3    4
int first = items[0];    // 100
int third = items[2];    // 300
int last = items[4];     // 500
```

### Array Length

Use `.length` (no parentheses — it's a field, not a method):

```java
int[] items = {100, 200, 300};
int size = items.length;          // 3
int lastIndex = items.length - 1; // 2
```

### ⚠️ Watch Out For

- Indices start at **0**, not 1.
- An array of size `n` has indices from `0` to `n - 1`. Accessing index `n` throws an `ArrayIndexOutOfBoundsException`.
- `.length` for arrays has **no parentheses**.
- `int[] arr = new int[4];` creates an array with **4 elements** (indices 0–3), all initialized to `0`.

---

## 7. Loops

Loops repeat a block of code. Java has four main loop types.

### Standard `for` Loop

Best when you know how many times to repeat:

```java
for (int i = 0; i < 5; i++) {
    System.out.println(i);     // Prints 0, 1, 2, 3, 4
}
```

Structure: `for (initialization; condition; update)`

- **Initialization**: runs once at the start (`int i = 0`)
- **Condition**: checked before each iteration (`i < 5`); loop stops when false
- **Update**: runs after each iteration (`i++`)

### Enhanced `for` Loop (For-each)

Best when you want to process every element of an array or collection, and you don't need the index:

```java
int[] prices = {25, 40, 15};
for (int p : prices) {
    System.out.println(p);     // Prints 25, 40, 15
}
```

Reads as: "for each `p` in `prices`."

### `while` Loop

Best when you don't know how many iterations — repeat as long as a condition is true:

```java
int attempts = 0;
while (attempts < 3) {
    System.out.println("Trying...");
    attempts++;
}
// Prints "Trying..." 3 times
```

The condition is checked **before** each iteration. If it's false at the start, the loop body never runs.

### `do-while` Loop

Like `while`, but the condition is checked **after** the body, so it always runs **at least once**:

```java
int attempts = 10;
do {
    System.out.println("Trying...");   // Prints once even though attempts >= 3
    attempts++;
} while (attempts < 3);
```

### Summary: When to Use Which

| Loop Type      | Use When                                                                      |
| -------------- | ----------------------------------------------------------------------------- |
| `for`          | You know exact number of iterations or need an index                          |
| Enhanced `for` | You want to visit each element, don't need the index                          |
| `while`        | You want to loop until a condition changes, and the body might not run at all |
| `do-while`     | Same as `while`, but you want the body to run at least once                   |

### ⚠️ Watch Out For

- Forgetting to update the loop variable → **infinite loop**.
- Off-by-one errors: `i < length` vs `i <= length` (the second goes one past the end).
- `do-while` runs **at least once** even if the condition is false initially.
- Count how many times a loop actually runs — trace through the condition carefully.

---

## 8. Conditional Statements

### `if` / `else if` / `else`

```java
int temperature = 68;
if (temperature >= 80) {
    System.out.println("Hot");
} else if (temperature >= 60) {
    System.out.println("Warm");
} else {
    System.out.println("Cold");
}
```

Rules:

- You can have one `if`, zero or more `else if`, and at most one `else`.
- Only **one** branch executes (the first whose condition is true).
- `else` has no condition — it's the fallback.

### Logical Operators

Combine conditions with:

- `&&` — AND (both must be true)
- `||` — OR (at least one must be true)
- `!` — NOT (flips true to false and vice versa)

```java
int age = 20;
boolean hasLicense = true;
if (age >= 18 && hasLicense) {
    System.out.println("Can drive");
}
```

### ⚠️ Watch Out For

- Using `=` instead of `==` in a condition.
- Forgetting that `else if` only runs if the previous `if` was false.

---

## 9. Methods

A **method** is a reusable block of code that performs a task. It can take inputs (parameters) and return an output.

### Method Structure

```java
public <returnType> <methodName>(<parameters>) {
    // method body
    return <value>;  // if returnType is not void
}
```

### Examples

**Method that returns a value:**

```java
public int multiply(int x, int y) {
    return x * y;
}
```

- **Return type:** `int` — the method gives back an integer
- **Parameters:** `int x, int y` — two integer inputs
- **Body:** calculates the product and returns it

**Method that doesn't return anything (`void`):**

```java
public void displayMessage(String text) {
    System.out.println("Message: " + text);
}
```

- `void` means the method does **not** return a value.

### Calling a Method

```java
int product = multiply(3, 5);         // product is 15
displayMessage("Welcome");            // Prints "Message: Welcome"
```

### Return Types

The **return type** must match what the method returns:

- If the method returns an integer → return type is `int` (or `double`, `long`, etc., if the result fits)
- If the method returns text → return type is `String`
- If the method returns nothing → return type is `void`

**Compatible return types:** an `int` value can be returned from a method declared with return type `int`, `long`, `double`, or `float` (widening works automatically). But not every type works for every value — returning `"hello"` from a method declared to return `int` will fail.

### The `return` Keyword

- In a non-`void` method, `return` sends a value back and exits the method.
- In a `void` method, you can use `return;` alone to exit early (no value), but you **cannot** return a value.

```java
public void doSomething() {
    return 100;      // ❌ Compiler error! void methods cannot return a value
}
```

### ⚠️ Watch Out For

- Every non-`void` method must return a value on every possible path through the code.
- `void` methods **cannot** return a value. Writing `return 5;` in a void method is a compiler error.
- The type of the returned value must be compatible with the declared return type.
- Method names are case-sensitive: `multiply` and `Multiply` are different methods.

---

## 10. Classes and Objects

A **class** is a blueprint. An **object** is an instance created from that blueprint.

### Declaring a Class

```java
public class Laptop {
    // fields (attributes)
    String brand;
    int ram;

    // constructor
    public Laptop(String brand, int ram) {
        this.brand = brand;
        this.ram = ram;
    }

    // method
    public void powerOn() {
        System.out.println(brand + " is starting up!");
    }
}
```

Key parts:

- `public class Laptop { ... }` — declares a class named `Laptop`
- **Fields** (`String brand; int ram;`) — the data each object holds
- **Constructor** — initializes new objects (see next section)
- **Methods** — behaviors the object can perform

### Syntax Rules

- The `class` keyword is **lowercase**.
- The class name should start with an uppercase letter (convention).
- The class name must match the filename (for public classes): `Laptop` → `Laptop.java`.
- The body is enclosed in `{ }`.

### Creating an Object

Use the `new` keyword:

```java
Laptop myLaptop = new Laptop("Dell", 16);
```

This:

1. Allocates memory for a new `Laptop` object.
2. Calls the constructor to initialize it.
3. Assigns a reference to `myLaptop`.

### ⚠️ Watch Out For

- `class` is lowercase; `Class` is not valid as a keyword.
- You **must** use `new` to create an object: `Laptop myLaptop = Laptop("Dell", 16);` is wrong (missing `new`).
- The class name in `new Laptop(...)` must match an actual class.

---

## 11. Constructors

A **constructor** is a special method that runs when a new object is created. Its purpose is typically (but not always) to initialize the object's fields.

### Constructor Rules

1. The constructor's **name must match the class name exactly**.
2. Constructors have **no return type** — not even `void`.
3. A class can have multiple constructors (called "overloading"), each with different parameters.
4. If you don't write any constructor, Java provides a default empty one.

### Example

```java
public class Phone {
    String brand;
    int storage;

    // Constructor
    public Phone(String brand, int storage) {
        this.brand = brand;
        this.storage = storage;
    }
}
```

When you call `new Phone("Samsung", 128)`, the constructor runs and assigns the values to the new object's fields.

### Empty Constructors

A constructor doesn't have to assign values. It can be empty:

```java
public Phone() {
    // empty, but still a valid constructor
}
```

This is legal and sometimes used when you want to set fields later.

### Constructors with No Parameters vs. With Parameters

```java
public class Student {
    String fullName;

    public Student() {
        this.fullName = "Unknown";
    }

    public Student(String fullName) {
        this.fullName = fullName;
    }
}

// Usage:
Student s1 = new Student();              // Uses first constructor
Student s2 = new Student("Maria Lopez"); // Uses second constructor
```

### ⚠️ Watch Out For

- Constructor name **must** match the class name — capitalization and spelling.
- A constructor has **no return type**. Writing `public void Phone(...)` would make it a regular method, not a constructor.
- Calling `new Phone()` only works if a matching constructor exists.

---

## 12. The `this` Keyword

`this` is a reference to the **current object** — the object whose method or constructor is currently running.

### Common Use: Distinguishing Fields from Parameters

When a parameter has the same name as a field, `this.` makes it clear which one you mean:

```java
public class Movie {
    String director;
    int runtime;

    public Movie(String director, int runtime) {
        this.director = director;   // this.director is the field; director is the parameter
        this.runtime = runtime;
    }
}
```

Without `this.`, Java would think both sides refer to the parameter, and the field would never be set.

### ⚠️ Watch Out For

- `this` only makes sense inside a class (in constructors and instance methods).
- `this.field = field;` is a common pattern — the left side is the object's field, the right side is the parameter.

---

## 13. Accessing Object Members

Once you have an object, you can access its fields and methods using the **dot operator** (`.`).

### Accessing Fields

```java
Movie m = new Movie("Nolan", 148);
System.out.println(m.director);    // "Nolan" (if director is accessible)
System.out.println(m.runtime);     // 148
```

This only works if the fields are accessible from outside the class. If fields are declared `private` (recommended for real-world code), you access them through **getter methods** instead:

```java
System.out.println(m.getDirector());    // Preferred in well-designed classes
System.out.println(m.getRuntime());
```

For the purposes of this assessment, assume fields are accessible unless stated otherwise.

### Calling Methods

```java
Laptop myLaptop = new Laptop("Dell", 16);
myLaptop.powerOn();    // Calls the powerOn() method on myLaptop
```

### ⚠️ Watch Out For

- Use the dot `.` to access fields and methods on an object.
- If a field is `private`, you cannot access it directly from outside the class.
- Method calls require parentheses, even if there are no arguments: `myLaptop.powerOn()`, not `myLaptop.powerOn`.

---

## 14. Common Array Patterns

These are fundamental patterns you should recognize and be able to apply.

### Pattern 1: Sum All Elements

```java
int[] marks = {15, 20, 25, 10};
int sum = 0;
for (int m : marks) {
    sum += m;
}
// sum is 70
```

Key idea: initialize the accumulator to `0`, then add each element.

### Pattern 2: Find the Maximum (Largest) Value

**The right way:**

```java
int[] readings = {12, 45, 8, 33};
int largest = readings[0];         // Start with the first element
for (int r : readings) {
    if (r > largest) {
        largest = r;
    }
}
```

**Why `largest = readings[0]` instead of `largest = 0`?**
Starting `largest` at `0` only works if there's at least one value `≥ 0` in the array. If all values are negative (like `{-12, -45, -8, -33}`), `largest` would incorrectly stay at `0` because no element is greater than `0`.

Starting with the first element of the array guarantees correctness no matter what values the array contains.

### Pattern 3: Find the Minimum Value

Mirror image of finding max:

```java
int[] readings = {12, 45, 8, 33};
int smallest = readings[0];
for (int r : readings) {
    if (r < smallest) {
        smallest = r;
    }
}
```

### Pattern 4: Count Elements Matching a Condition

```java
int[] ages = {22, 17, 30, 15, 40};
int adults = 0;
for (int a : ages) {
    if (a >= 18) {
        adults++;
    }
}
// adults is 3 (the values 22, 30, and 40)
```

### Pattern 5: Iterate with Index (when you need the position)

```java
int[] prices = {25, 40, 15};
for (int i = 0; i < prices.length; i++) {
    System.out.println("Index " + i + ": " + prices[i]);
}
```

Use the standard `for` loop when you need the index. Use enhanced `for` when you only care about the values.

### ⚠️ Watch Out For

- **Initialization matters.** Starting `max` at `0` fails for all-negative arrays. Starting at the first element is safe.
- **Starting `sum` at `0`** is correct because `0` is the identity for addition — adding 0 doesn't change anything.
- **Off-by-one errors** in standard `for` loops: `i < arr.length` is correct; `i <= arr.length` goes one past the end.

---

## Quick Reference Summary

### Primitive Types

`byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean` — **`String` is NOT a primitive.**

### Declaration Pattern

`<type> <name> = <value>;`

### Array Indexing

- First: `0`
- Last: `length - 1`
- Size-`n` array has indices `0` to `n-1`

### Length

- Strings: `.length()` (method, parentheses)
- Arrays: `.length` (field, no parentheses)

### Loops

- `for` — count-controlled
- Enhanced `for` — iterate over each element
- `while` — may run 0 times
- `do-while` — runs at least once

### Methods

- Non-`void` → must return a value
- `void` → cannot return a value (but `return;` alone is legal to exit early)

### Classes

- Declared with `public class <Name> { ... }`
- Create objects with `new ClassName(...)`
- Access members with `.`

### Constructors

- Name matches class name exactly
- No return type
- Can be empty or initialize fields
- Use `this.field = field;` to avoid name collisions

### Casting

- Implicit (widening): automatic, e.g., `int` → `double`
- Explicit (narrowing): required, e.g., `(int) someDouble`

---

## Final Study Tips

1. **Practice tracing code by hand.** Don't just read; simulate execution on paper.
2. **Memorize the primitive types.** Especially remember that `String` is not one.
3. **Know the difference between array `.length` and String `.length()`.**
4. **Understand that arrays are zero-indexed.** Last index is always `length - 1`.
5. **Constructors: name matches class, no return type.**
6. **`this.x = x;` is how you assign a parameter to a field with the same name.**
7. **For loops: always count the iterations carefully.** Off-by-one errors are common.
8. **`void` methods don't return values. Non-`void` methods must.**
9. **When casting from `double` to `int`, decimals are truncated, not rounded.**
10. **`==` is equality, `=` is assignment.** Don't mix them up.

Good luck! Remember — this guide is a reference, not a substitute for understanding. The assessment tests whether you can **apply** these concepts, so read each question carefully, trace through the code, and think about what's actually happening.
