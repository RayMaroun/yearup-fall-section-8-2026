package com.pluralsight;

import java.util.Scanner;

/**
 * HighScoreWins (Workbook 2, Exercise 3)
 *
 * The user types one line that holds both team names and the final score,
 * for example: Home:Visitor|21:9
 * The program breaks that line apart and prints the name of the team with
 * the higher score, or "Tie!" when both scores are the same.
 *
 * The line has two levels of separators:
 *   - the pipe ( | ) separates the team names from the scores
 *   - the colon ( : ) separates home from visitor, on each side of the pipe
 * So we cut on the pipe first, then cut each half on the colon.
 */
public class HighScoreWins {

    public static void main(String[] args) {
        // A Scanner reads what the user types in the console
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a game score
        // print (not println) keeps the cursor on the same line as the prompt
        System.out.print("Please enter the game score: ");
        String input = scanner.nextLine(); // e.g. Spurs:Knicks|106:107

        // Find the position of the pipe character (|)
        // indexOf returns -1 when the character is not in the text
        int pipeIndex = input.indexOf('|');

        // No pipe means the input is not in the expected format, so we stop here
        if (pipeIndex == -1) {
            System.out.println("Invalid input format.");
            scanner.close();
            return; // leaves main early, nothing below runs
        }

        // Split the input into home and visitor parts
        // substring(start, end) stops BEFORE end, so the pipe itself is left out
        String teamPart = input.substring(0, pipeIndex);    // Spurs:Knicks
        String scorePart = input.substring(pipeIndex + 1);  // 106:107 (+1 skips the pipe)

        // Find the position of the colon character (:) in the team names
        int colonIndex = teamPart.indexOf(':');

        if (colonIndex == -1) {
            System.out.println("Invalid input format.");
            scanner.close();
            return;
        }

        // Extract team names and scores
        // Same idea as the pipe: left of the colon is home, right of it is visitor
        String homeTeam = teamPart.substring(0, colonIndex);       // Spurs
        String visitorTeam = teamPart.substring(colonIndex + 1);   // Knicks

        // Find the position of the colon character (:) in the scores
        // We reuse the same variable; it now points into scorePart
        colonIndex = scorePart.indexOf(':');

        if (colonIndex == -1) {
            System.out.println("Invalid input format.");
            scanner.close();
            return;
        }

        // The scores are still text ("106"), so we convert them to int to compare them as numbers
        int homeScore = Integer.parseInt(scorePart.substring(0, colonIndex));       // 106
        int visitorScore = Integer.parseInt(scorePart.substring(colonIndex + 1));   // 107

        // Determine the winning team
        // Three possible outcomes: home wins, visitor wins, or the scores are equal
        String winner;
        if (homeScore > visitorScore) {
            winner = homeTeam;
        } else if (visitorScore > homeScore) {
            winner = visitorTeam;
        } else {
            winner = "Tie!"; // In case of a tie (both scores are equal)
        }

        // Display the winning team
        System.out.println("Winner: " + winner);

        // Close the Scanner when we are done reading input
        scanner.close();
    }

    /*
     * Alternative solution: the same exercise using split() instead of indexOf/substring.
     * split("\\|") cuts the line on the pipe. The pipe has a special meaning in split
     * (it means "or"), so it must be escaped with \\ to mean a real pipe character.
     * Each half is then split on ":" and we pick element [0] (home) or [1] (visitor).
     * Note: this version has no tie branch; on equal scores the ternary picks the
     * visitor. To match the workbook, add a check for homeScore == visitorScore and
     * print "Winner: Tie!".
     * To try it, comment out the main above and remove the comment markers below.
     */
/*    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the game score: ");
        String input = scanner.nextLine();

        // Split the input string into team names and scores
        String[] parts = input.split("\\|");
        String homeTeam = parts[0].split(":")[0];
        String visitorTeam = parts[0].split(":")[1];
        int homeScore = Integer.parseInt(parts[1].split(":")[0]);
        int visitorScore = Integer.parseInt(parts[1].split(":")[1]);

        // Determine the winning team and display its name
        String winner = homeScore > visitorScore ? homeTeam : visitorTeam;

        System.out.println("Winner: " + winner);
    }*/
}
