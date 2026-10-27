package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

/*
 * Demo 03 - Writing files, part 2: BufferedWriter and append mode
 *
 * Two new ideas on top of Main:
 *
 *   1. BufferedWriter wraps the FileWriter, the same way BufferedReader wrapped
 *      FileReader. It collects the text in memory and writes it to disk in
 *      bigger chunks, which is faster when you write many lines.
 *
 *   2. Append mode. new FileWriter("tomato.txt", true) opens the file and
 *      KEEPS what is already there: new text goes at the end. Without the
 *      true (as in Main) the file is emptied first. Run this program three
 *      times and tomato.txt has 30 lines.
 *
 * Switched-off blocks: the two-step way to build the writer, and a version
 * that asks for a name and writes a greeting. Uncomment them to try.
 */
public class Main2 {
    public static void main(String[] args) {
        // ---------- BufferedWriter in append mode ----------
        System.out.println("========== BufferedWriter in append mode ==========");
        try {
            // Alternative: build the writer in two steps. Same writer as the one
            // line below, but this one OVERWRITES (no true).
            /*
            FileWriter fileWriter = new FileWriter("tomato.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            */

            // Needed only by the "enter a name" block below
            // Scanner scanner = new Scanner(System.in);

            // One line: the FileWriter opens the file in append mode (true), the
            // BufferedWriter sits on top of it
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("tomato.txt", true)); // true: append, keep what is there

            // Alternative: write something the user typed instead of the counter
            /*
            System.out.println("Please enter the name that you want to write: ");
            String name = scanner.nextLine();

            //String nameToWrite = "Hello " + name;

            bufferedWriter.write("Hello " + name);
            */

            // Write ten lines: Counting 1 ... Counting 10.
            // String.format builds the text like printf, but gives it back as a
            // String instead of printing it, so it can be handed to write().
            String numText;
            for (int i = 1; i <= 10; i++) {
                numText = String.format("Counting %d\n", i);
                //numText = "Counting " + i + "\n";                     // same text with + instead of format
                bufferedWriter.write(numText);
            }

            // close() empties the buffer into the file and releases it.
            // Without it the ten lines may never reach the disk.
            bufferedWriter.close();                                     // flushes the buffer to the file

        } catch (Exception ex) {
            System.err.println("An error has occurred!");
        }
        System.out.println();
    }
}
