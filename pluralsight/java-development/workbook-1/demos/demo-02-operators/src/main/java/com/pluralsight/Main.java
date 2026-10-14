package com.pluralsight;

/*
 * Demo 02 - Operators and expressions
 *
 * This program walks through the operators of Section 3-2: joining Strings
 * with +, the arithmetic operators, ++ and --, how a literal gets its type,
 * what happens when a value moves to a wider or a narrower type, a few
 * methods of the Math class, and the shorthand assignment operators.
 *
 * Each block prints a title line, then its values one per line.
 * Run it, read the output next to the code, and change the values.
 *
 * Some lines are commented out on purpose. Remove the // in front of one,
 * run again, and read what happens: some give a different result, some
 * refuse to compile. Both are part of the lesson.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- String concatenation ----------
        // With a String on either side, + joins text instead of adding numbers.
        // Java works from left to right, so "Sum: " + 1 + 2 joins "Sum: " and 1 first,
        // then joins 2. Parentheses make the addition happen before the join.
        System.out.println("========== String concatenation ==========");
        String firstWord = "Hello";
        String secondWord = "Class";
        String greeting = firstWord + " " + secondWord;   // the " " puts a space between the words
        System.out.println(greeting);
        System.out.println("Sum: " + 1 + 2);         // "Sum: 12", left to right
        System.out.println("Sum: " + (1 + 2));       // "Sum: 3", the parentheses add first
        System.out.println();

        // ---------- Mathematical operators ----------
        // + - * / and % (the remainder of a division).
        // The result type follows the operands: int with int gives an int, and an int
        // division drops the decimals. With doubles, 10 / 3 keeps its decimals.
        // Try it: comment out the three double lines, uncomment the three int lines, run again.
        System.out.println("========== Mathematical operators ==========");
        /*
        int firstNumber = 10;
        int secondNumber = 3;
        int result;
        */
        double firstNumber = 10;
        double secondNumber = 3;
        double result;
        result = firstNumber + secondNumber;
        System.out.println(result);                  // 13.0
        result = firstNumber - secondNumber;
        System.out.println(result);                  // 7.0
        result = firstNumber * secondNumber;
        System.out.println(result);                  // 30.0
        result = firstNumber / secondNumber;
        System.out.println(result);                  // 3.3333333333333335 (with ints: 3)
        result = firstNumber % secondNumber;
        System.out.println(result);                  // 1.0, the remainder of 10 / 3
        System.out.println();

        // ---------- Pre and post increment ----------
        // x = x + 1, x++ and ++x all add 1 to x. The difference between x++ and ++x
        // only shows when the value is used in the same statement, like y = x++.
        System.out.println("========== Pre and post increment ==========");
        int x = 5;
        x = x + 1;                                   // 6
        x++;                                         // 7
        ++x;                                         // 8
        System.out.println(x);

        // Post-increment: use the value first, then add 1
        int a = 5;
        int b = a++;
        // 1- Take the value of a, which = 5
        // 2- Give that value to b, so b = 5
        // 3- Add +1 to the value of a, so now a = 6
        System.out.println(a);                       // 6
        System.out.println(b);                       // 5

        // Pre-increment: add 1 first, then use the value
        int c = 5;
        int d = ++c;
        // 1- Take the value of c, which = 5
        // 2- Add +1 to the value of c, so now c = 6
        // 3- Give that value to d, so d = 6
        System.out.println(c);                       // 6
        System.out.println(d);                       // 6
        System.out.println();

        // ---------- Literals are typed ----------
        // A literal (a value written in the code) has a type of its own.
        // A whole number is an int unless it ends with L; a decimal is a double unless it ends with f.
        // Underscores between digits are allowed and ignored; they only help you read the number.
        System.out.println("========== Literals are typed ==========");
        long worldPopulation = 8_000_000_000L;       // long needs L; underscores for readability
        float price = 3.45f;                         // float needs f
        System.out.println(worldPopulation);
        System.out.println(price);
        /*
        int tooBig = 3000000000;                  // error: the literal is an int, and 3 billion does not fit
        float wrong = 3.45;                       // error: 3.45 is a double, it needs the f to be a float
        */
        System.out.println();

        // ---------- Widening ----------
        // Moving a value into a wider type is automatic, because nothing can be lost:
        // every int fits in a long, every float fits in a double.
        // 3.8f prints as 3.799999952316284 in the double: a float only holds about 7 digits,
        // so 3.8 was never stored exactly, and the double shows what was really there.
        System.out.println("========== Widening ==========");
        int myInt = 9;
        long myLong = myInt;                         // int fits in long, automatic
        float myFloat = 3.8f;
        double myDouble = myFloat;                   // float fits in double, automatic
        System.out.println(myLong);
        System.out.println(myDouble);
        System.out.println();

        // ---------- Narrowing and casting ----------
        // Moving a value into a narrower type is refused by the compiler, because part of
        // the value may be lost. A cast, (float) or (int), tells the compiler "I accept the loss".
        // Floating point loses precision (the digits get shorter). A whole number that does not
        // fit loses its top bits, and what is left is a different number, even the sign changes.
        System.out.println("========== Narrowing and casting ==========");
        double precise = 123.4567890123;
        System.out.println(precise);
        // float rounded = precise;                  // error: possible lossy conversion from double to float
        float rounded = (float) precise;             // cast: you take responsibility for what is lost
        System.out.println(rounded);                 // 123.45679
        long bigNumber = 3000000000L;
        System.out.println(bigNumber);
        // int smallNumber = bigNumber;              // error: possible lossy conversion from long to int
        int smallNumber = (int) bigNumber;           // cast: too big for an int, the value is damaged
        System.out.println(smallNumber);             // -1294967296
        System.out.println();

        // ---------- Math class ----------
        // Math is a class full of ready-made methods. You call them with Math.name(...)
        // and they return a value. See the Java documentation for the full list.
        System.out.println("========== Math class ==========");
        float value = 1234.567f;
        int wholeNumber = Math.round(value);         // nearest whole number: 1235
        System.out.println(wholeNumber);
        double expressionResult = 1 - 2 * Math.pow(2, 2);   // Math.pow(2, 2) is 2 to the power 2 = 4.0, so 1 - 8 = -7.0
        System.out.println(expressionResult);
        int firstScore = 5;
        int secondScore = 10;
        int minimumScore = Math.min(firstScore, secondScore);   // the smaller of the two
        int maximumScore = Math.max(firstScore, secondScore);   // the larger of the two
        System.out.println(minimumScore);
        System.out.println(maximumScore);
        System.out.println();

        // ---------- Assignment operators ----------
        // answer = answer + 10 reads answer, adds 10, and stores the result back in answer.
        // The shorthand in each comment does exactly the same in fewer characters.
        System.out.println("========== Assignment operators ==========");
        int answer = 0;
        answer = answer + 10;                        // answer += 10;
        System.out.println(answer);                  // 10
        answer = answer - 5;                         // answer -= 5;
        System.out.println(answer);                  // 5
        answer = answer * 10;                        // answer *= 10;
        System.out.println(answer);                  // 50
        answer = answer / 2;                         // answer /= 2;
        System.out.println(answer);                  // 25
        answer = answer % 10;                        // answer %= 10;
        System.out.println(answer);                  // 5
        answer = answer + 1;                         // answer += 1;  or  answer++;
        System.out.println(answer);                  // 6
        System.out.println();
    }
}
