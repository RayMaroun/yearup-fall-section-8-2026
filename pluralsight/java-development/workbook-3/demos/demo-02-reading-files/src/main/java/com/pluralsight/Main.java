package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;

/*
 * Demo 02 - Reading files, part 1
 *
 * This program opens a text file and prints it one line at a time.
 *
 * Two classes work together:
 *   - FileReader opens the file and reads characters from it.
 *   - BufferedReader wraps the FileReader and adds readLine(), which gives
 *     you one whole line at a time. That is the method you want.
 *
 * poem.txt sits at the project root, next to pom.xml. Java looks for the
 * file there when you run from IntelliJ.
 *
 * Opening a file can fail (wrong name, wrong folder), so the whole thing
 * sits inside try / catch. Swap in the missing.txt line to see the catch run.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- Read a file line by line ----------
        System.out.println("========== Read a file line by line ==========");
        try {
            // Alternative: both steps in one line. Same reader, same result.
            // BufferedReader bufferedReader = new BufferedReader(new FileReader("poem.txt")); // one step: same reader, one line
            // Try it: a file that does not exist throws FileNotFoundException, and the catch runs.
            // FileReader fileReader = new FileReader("missing.txt");    // no such file: the catch runs
            FileReader fileReader = new FileReader("poem.txt");          // poem.txt sits at the project root
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            // readLine() gives the next line, or null when there are no more lines.
            // The loop reads a line and checks it in the same expression:
            // assign first (inside the parentheses), then compare with null.
            while ((line = bufferedReader.readLine()) != null) {          // readLine gives null at the end of the file
                System.out.println(line);
            }

            // Close what you opened, so the file is released for other programs
            fileReader.close();
            bufferedReader.close();                                       // release the file when done

        } catch (Exception ex) {
            System.out.println("Something went wrong!");
        }
        System.out.println();
    }
}
