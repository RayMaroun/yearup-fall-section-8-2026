package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;

/*
 * Demo 03 - Writing files, part 1
 *
 * This program creates a text file and writes three lines into it.
 *
 * FileWriter is the mirror of FileReader: you give it a file name and it
 * opens that file for writing. If the file does not exist, it is created.
 * If it exists, it is EMPTIED first and written over. Run the program twice
 * and potato.txt still has three lines, not six.
 *
 * potato.txt lands at the project root, next to pom.xml. Open it in IntelliJ
 * after the run to see what was written.
 *
 * Writing can fail (folder does not exist, no permission, disk full), so the
 * whole thing sits inside try / catch, exactly like reading did.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- FileWriter ----------
        System.out.println("========== FileWriter ==========");
        try {
            // Open (or create) the file. From this line on, potato.txt is empty.
            FileWriter fileWriter = new FileWriter("potato.txt");      // creates the file, or overwrites it if it exists

            // write() puts the text in the file as it is. Unlike println, it adds
            // NO line break, so each line ends with \n by hand. The last line has
            // none: the file ends right after the text.
            fileWriter.write("Hello Everyone\n");                       // write adds no new line: \n does
            fileWriter.write("I am your Potato instructor!\n");
            fileWriter.write("Welcome to the Java Bootcamp!");

            // close() writes everything out and releases the file. Forget it and
            // the file may stay empty: the text can sit in memory until close.
            fileWriter.close();                                         // nothing is guaranteed on disk before close
        } catch (Exception e) {
            System.err.println("An error has occurred!");
        }
        System.out.println();
    }
}
