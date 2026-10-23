package com.pluralsight;

/*
 * Demo 07 - Arrays
 *
 * This program walks through arrays: one variable that holds many values of
 * the same type, each in a numbered slot. The program is split into three
 * classes, each with its own main method, so run them one at a time:
 *
 *   Main  - declare and create an array, read and write slots by index,
 *           loop over an array, default values, and passing arrays to and
 *           from methods.
 *   Main2 - the for loop with an index vs the for-each loop, and sorting
 *           with Arrays.sort (A to Z and Z to A).
 *   Main3 - copying part of an array with System.arraycopy and a whole
 *           array with Arrays.copyOf.
 *
 * Each block prints a title line, then its results.
 * Run it, read the output next to the code, and change the values.
 *
 * Some lines are commented out on purpose. The ones marked with an error or an
 * exception name contain a mistake: remove the // in front of one, run again,
 * and read the message Java gives you. That message is part of the lesson.
 * The other commented lines and blocks are alternatives to try: remove the
 * comment markers and compare the output.
 *
 * Two ideas run through all three classes: the size of an array is fixed when it
 * is created, and indexes start at 0, so the last index is always length - 1.
 */
public class Main {

    public static void main(String[] args) {
        // ---------- Declare and create ----------
        // Declare and create
        System.out.println("========== Declare and create ==========");
        // For comparison: a plain int variable can hold only one number at a time.
        //int firstNumber = 1;                                       // one variable holds one value
        // Two ways to create an array:
        // new int[5] when you know the size but not the values yet,
        // {...} when you already know the values.
        int[] newArrayOfNumbers = new int[5];                        // 5 empty slots, the size is fixed from now on
        int[] listOfNumbers = {63, 123, 234, 123, 545, 23, 64};      // literal: the size comes from the values
        System.out.println(listOfNumbers[1]);                        // index 1 is the second element, indexes start at 0
        // Prints 123. Try listOfNumbers[0] to get the first element, 63.
        System.out.println();

        // ---------- Loop over an array ----------
        // Loop over an array
        // A for loop from 0 to length - 1 visits every slot once.
        // length is a property of the array (no parentheses), not a method.
        System.out.println("========== Loop over an array ==========");
        for (int i = 0; i < listOfNumbers.length; i++) {             // last index is length - 1
            System.out.print(listOfNumbers[i] + " ");
        }
        // print (not println) keeps the values on one line; the println below ends that line.
        System.out.println();
        for (int i = 0; i < newArrayOfNumbers.length; i++) {
            System.out.print(newArrayOfNumbers[i] + " ");            // int slots start at 0
        }
        // Prints 0 0 0 0 0: nothing has been stored yet, so every slot holds the default.
        System.out.println();
        System.out.println();

        // ---------- Set by index ----------
        // Set by index
        // array[index] = value stores a value in one slot. The other slots do not change.
        System.out.println("========== Set by index ==========");
        newArrayOfNumbers[2] = 15;                                   // writes into the third slot
        // The array has 5 slots, numbered 0 to 4. Slot 5 does not exist. This compiles,
        // but the program stops when it runs this line.
        //newArrayOfNumbers[5] = 1;                                  // ArrayIndexOutOfBoundsException: indexes go 0 to 4
        for (int i = 0; i < newArrayOfNumbers.length; i++) {
            System.out.print(newArrayOfNumbers[i] + " ");
        }
        // Prints 0 0 15 0 0
        System.out.println();
        System.out.println();

        // ---------- Default values ----------
        // Default values
        // A new array is never really empty: every slot starts with the default value
        // for its type. 0 for numbers, false for boolean, null for objects like String.
        System.out.println("========== Default values ==========");
        String[] listOfNames = new String[5];
        for (int i = 0; i < listOfNames.length; i++) {
            System.out.print(listOfNames[i] + " ");                  // String slots start at null
        }
        // null means "no String here yet". Calling a method on it, such as
        // listOfNames[0].length(), would stop the program with a NullPointerException.
        System.out.println();
        boolean[] boolArray = new boolean[5];
        for (int i = 0; i < boolArray.length; i++) {
            System.out.print(boolArray[i] + " ");                    // boolean slots start at false
        }
        System.out.println();
        boolArray[1] = true;
        for (int i = 0; i < boolArray.length; i++) {
            System.out.print(boolArray[i] + " ");
        }
        // Prints false true false false false: only slot 1 changed.
        System.out.println();
        System.out.println();

        // ---------- A value from a method vs an array from a method ----------
        // A value from a method vs an array from a method
        // Methods can return and receive arrays the same way they return and receive
        // single values. Only the type changes: int becomes int[].
        System.out.println("========== A value from a method vs an array from a method ==========");
        int numberFromMethod = getNumber();                          // returns one int
        printSingleNumber(numberFromMethod);                         // passes one int
        int[] arrayFromMethod = getNumbers();                        // returns a whole array
        displayNumbers(arrayFromMethod);                             // passes a whole array
        // Two other ways to print the same array, to try instead of displayNumbers:
        // the first prints each index next to its value, one per line;
        // the second is a for-each loop (see Main2), which gives each value without an index.
        /*
        for (int i = 0; i < arrayFromMethod.length; i++) {
            System.out.println(i + ":" + arrayFromMethod[i]);
        }

        for (int num : arrayFromMethod) {
            System.out.print(num + " ");
        }
        */
        System.out.println();
    }

    // Returns a single int.
    public static int getNumber() {
        int randomNumber = 5;
        return randomNumber;
    }

    // the return type int[] means the method hands back an array
    // The caller does not need to know the size in advance: it gets whatever array
    // the method built, and can ask it for its length.
    public static int[] getNumbers() {
        int[] nums = {63, 65, 60, 53, 58, 37, 35, 31};
        return nums;
    }

    // Takes a single int.
    public static void printSingleNumber(int numberToPrint) {
        System.out.println("The number is: " + numberToPrint);
    }

    // the parameter int[] means the method takes an array
    // Written once, it can print any int array of any size, because it loops up to
    // the length of whatever array it receives.
    public static void displayNumbers(int[] listOfNumbers) {
        for (int i = 0; i < listOfNumbers.length; i++) {
            System.out.print(listOfNumbers[i] + " ");
        }
        System.out.println();
    }
}
