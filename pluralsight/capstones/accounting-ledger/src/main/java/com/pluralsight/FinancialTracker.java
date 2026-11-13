package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/*
 * Capstone solution  –  Personal Financial Tracker
 * =================================================
 *   • Stores every deposit (positive amount) and payment (negative amount)
 *   • Persists data in a text file using pipe delimiters
 *     Format: yyyy-MM-dd|HH:mm:ss|description|vendor|amount
 *   • Provides ledger views, date-range reports, vendor search, and custom search
 */
public class FinancialTracker {

    /* ------------------------------------------------------------------
       Constants and shared data
       ------------------------------------------------------------------ */

    private static final ArrayList<Transaction> transactions = new ArrayList<>();

    private static final String FILE_NAME = "transactions.csv";

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final String DATETIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    /* ANSI color codes – work in IntelliJ's run console, macOS Terminal, modern PowerShell. */
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_GREEN = "\u001B[32m";
    private static final String COLOR_RED = "\u001B[31m";
    private static final String COLOR_YELLOW = "\u001B[33m";
    private static final String COLOR_CYAN = "\u001B[36m";
    private static final String COLOR_BOLD = "\u001B[1m";

    /* Menu box width – kept consistent across all menus. */
    private static final int MENU_WIDTH = 40;

    /* ------------------------------------------------------------------
       Main menu loop
       ------------------------------------------------------------------ */
    public static void main(String[] args) {

        clearScreen();
        printBanner();
        loadTransactionsWithAnimation();

        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            printMainMenu();
            System.out.print(COLOR_GREEN + "Your choice: " + COLOR_RESET);

            String menuChoice = scanner.nextLine().trim().toUpperCase();

            switch (menuChoice) {
                case "D" -> addDeposit(scanner);
                case "P" -> addPayment(scanner);
                case "L" -> ledgerMenu(scanner);
                case "X" -> keepRunning = false;
                default -> printWarning("Invalid option – please try again.");
            }
        }

        System.out.println();
        System.out.println(COLOR_CYAN + "Goodbye!" + COLOR_RESET);
        scanner.close();
    }

    /* ------------------------------------------------------------------
       Startup visuals
       ------------------------------------------------------------------ */

    /**
     * Clears the terminal screen using ANSI escape codes.
     * Works in IntelliJ's run console and most modern terminals.
     */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Prints the ASCII-art startup banner in cyan.
     */
    private static void printBanner() {
        System.out.println(COLOR_BOLD + COLOR_CYAN);
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                                                      ║");
        System.out.println("║     ██╗     ███████╗██████╗  ██████╗ ███████╗██████╗ ║");
        System.out.println("║     ██║     ██╔════╝██╔══██╗██╔════╝ ██╔════╝██╔══██╗║");
        System.out.println("║     ██║     █████╗  ██║  ██║██║  ███╗█████╗  ██████╔╝║");
        System.out.println("║     ██║     ██╔══╝  ██║  ██║██║   ██║██╔══╝  ██╔══██╗║");
        System.out.println("║     ███████╗███████╗██████╔╝╚██████╔╝███████╗██║  ██║║");
        System.out.println("║     ╚══════╝╚══════╝╚═════╝  ╚═════╝ ╚══════╝╚═╝  ╚═╝║");
        System.out.println("║                                                      ║");
        System.out.println("║              Personal Finance Tracker  v1.0          ║");
        System.out.println("║                                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println(COLOR_RESET);
    }

    /**
     * Loads the transactions file with a progress-bar effect at startup
     * so the launch feels substantial instead of just dumping output.
     */
    private static void loadTransactionsWithAnimation() {
        showProgressBar("Loading transactions", 800);
        loadTransactions(FILE_NAME);
    }

    /**
     * Shows an animated progress bar with the given label that fills up over
     * the given duration in milliseconds. Used purely for visual effect –
     * the underlying work is essentially instant.
     * <p>
     * Uses the carriage-return character ('\r') to move the cursor back to
     * the start of the line so each frame overwrites the previous one.
     */
    private static void showProgressBar(String label, int totalMillis) {
        final int width = 30;          // total cells in the bar
        final int frames = 20;         // how many redraws across the duration
        int delay = totalMillis / frames;

        try {
            for (int frame = 0; frame <= frames; frame++) {
                int filled = (int) ((double) frame / frames * width);
                int empty = width - filled;
                int percent = (int) ((double) frame / frames * 100);

                String bar = COLOR_GREEN + "█".repeat(filled) + COLOR_RESET
                        + "░".repeat(empty);
                System.out.print("\r" + COLOR_CYAN + label + COLOR_RESET
                        + " [" + bar + "] " + percent + "%");
                System.out.flush();
                Thread.sleep(delay);
            }
            System.out.println();      // move past the bar line when done
        } catch (InterruptedException ignored) {
        }
    }

    /* ------------------------------------------------------------------
       Menu rendering helpers
       ------------------------------------------------------------------ */

    /**
     * Prints a horizontal border for menu boxes.
     */
    private static void printMenuBorder(String left, String mid, String right) {
        System.out.println(left + mid.repeat(MENU_WIDTH - 2) + right);
    }

    /**
     * Prints a centered title row for a menu box.
     */
    private static void printMenuTitle(String title) {
        int padding = MENU_WIDTH - 2 - title.length();
        int leftPad = padding / 2;
        int rightPad = padding - leftPad;
        System.out.println("│" + " ".repeat(leftPad)
                + COLOR_BOLD + COLOR_CYAN + title + COLOR_RESET
                + " ".repeat(rightPad) + "│");
    }

    /**
     * Prints one menu option line. The key (e.g. "D") is shown in bold yellow.
     */
    private static void printMenuOption(String key, String label) {
        String text = "  " + COLOR_BOLD + COLOR_YELLOW + key + COLOR_RESET + ")  " + label;
        // pad based on visible length (not including ANSI escape codes)
        int visibleLength = ("  " + key + ")  " + label).length();
        int padding = MENU_WIDTH - 2 - visibleLength;
        if (padding < 0) padding = 0;
        System.out.println("│" + text + " ".repeat(padding) + "│");
    }

    private static void printMainMenu() {
        System.out.println();
        printMenuBorder("┌", "─", "┐");
        printMenuTitle("MAIN MENU");
        printMenuBorder("├", "─", "┤");
        printMenuOption("D", "Add Deposit");
        printMenuOption("P", "Make Payment (Debit)");
        printMenuOption("L", "Ledger");
        printMenuOption("X", "Exit");
        printMenuBorder("└", "─", "┘");
    }

    private static void printLedgerMenu() {
        System.out.println();
        printMenuBorder("┌", "─", "┐");
        printMenuTitle("LEDGER");
        printMenuBorder("├", "─", "┤");
        printMenuOption("A", "All Transactions");
        printMenuOption("D", "Deposits Only");
        printMenuOption("P", "Payments Only");
        printMenuOption("R", "Reports");
        printMenuOption("H", "Home");
        printMenuBorder("└", "─", "┘");
    }

    private static void printReportsMenu() {
        System.out.println();
        printMenuBorder("┌", "─", "┐");
        printMenuTitle("REPORTS");
        printMenuBorder("├", "─", "┤");
        printMenuOption("1", "Month To Date");
        printMenuOption("2", "Previous Month");
        printMenuOption("3", "Year To Date");
        printMenuOption("4", "Previous Year");
        printMenuOption("5", "Search by Vendor");
        printMenuOption("6", "Custom Search");
        printMenuOption("0", "Back");
        printMenuBorder("└", "─", "┘");
    }

    /* ------------------------------------------------------------------
       Status message helpers (with icons + colors)
       ------------------------------------------------------------------ */

    private static void printSuccess(String message) {
        System.out.println(COLOR_GREEN + "✅ " + message + COLOR_RESET);
    }

    private static void printWarning(String message) {
        System.out.println(COLOR_YELLOW + "⚠️  " + message + COLOR_RESET);
    }

    private static void printError(String message) {
        System.out.println(COLOR_RED + "❌ " + message + COLOR_RESET);
    }

    private static void printInfo(String message) {
        System.out.println(COLOR_CYAN + "ℹ️  " + message + COLOR_RESET);
    }

    /* ------------------------------------------------------------------
       Load data file (creates it if missing)
       ------------------------------------------------------------------ */

    /**
     * Reads the pipe-delimited data file and fills {@code transactions}.
     * If the file is not present, an empty file is created so later writes succeed.
     * Blank lines are skipped, and malformed lines are reported and skipped
     * so one bad row does not abort the entire load.
     */
    public static void loadTransactions(String fileName) {
        try {
            File dataFile = new File(fileName);
            if (dataFile.createNewFile()) {
                printInfo("Created new data file: " + fileName);
            }

            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    String[] fields = line.split("\\|");

                    if (fields.length != 5) {
                        printWarning("Skipping malformed line: " + line);
                        continue;
                    }

                    LocalDate date = LocalDate.parse(fields[0], DATE_FMT);
                    LocalTime time = LocalTime.parse(fields[1], TIME_FMT);
                    String description = fields[2];
                    String vendor = fields[3];
                    double amount = Double.parseDouble(fields[4]);

                    Transaction transaction =
                            new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);
                } catch (Exception parseError) {
                    printWarning("Skipping bad line: " + line);
                }
            }
            reader.close();

            printSuccess("Loaded " + transactions.size() + " transactions.");
        } catch (IOException ioException) {
            printError("Error reading data file: " + ioException.getMessage());
        }
    }

    /* ------------------------------------------------------------------
       Add new transactions
       ------------------------------------------------------------------ */

    private static void addDeposit(Scanner scanner) {
        Transaction deposit = promptForTransaction(scanner, false);
        saveTransaction(deposit);
        printSuccess("Deposit recorded.");
    }

    private static void addPayment(Scanner scanner) {
        Transaction payment = promptForTransaction(scanner, true);
        saveTransaction(payment);
        printSuccess("Payment recorded.");
    }

    /**
     * Shared helper that prompts for date, time, description, vendor, and amount,
     * then builds a Transaction. If {@code negateAmount} is true (payment),
     * the amount is stored as a negative number.
     */
    private static Transaction promptForTransaction(Scanner scanner, boolean negateAmount) {

        LocalDateTime dateTime = promptDateTime(scanner);
        String description = promptNonEmptyText(scanner, "Description");
        String vendor = promptNonEmptyText(scanner, "Vendor");
        double amount = promptPositiveAmount(scanner);

        return new Transaction(
                dateTime.toLocalDate(),
                dateTime.toLocalTime(),
                description,
                vendor,
                negateAmount ? -amount : amount);
    }

    /* ------------- input helpers (loop until valid) ------------- */

    /**
     * Prompt for a date-time line in the format "yyyy-MM-dd HH:mm:ss".
     * Keeps asking until the user types something valid.
     */
    private static LocalDateTime promptDateTime(Scanner scanner) {
        while (true) {
            System.out.print("Date & time (" + DATETIME_PATTERN + "): ");
            String userInput = scanner.nextLine().trim();
            try {
                return LocalDateTime.parse(userInput, DATETIME_FMT);
            } catch (Exception parseException) {
                printWarning("Invalid date/time format. Try again.");
            }
        }
    }

    /**
     * Prompt for a positive number. Loops until the user types a valid value.
     * Tells the user whether the problem was the format or the sign.
     */
    private static double promptPositiveAmount(Scanner scanner) {
        while (true) {
            System.out.print("Amount (positive): ");
            String userInput = scanner.nextLine().trim();
            try {
                double amount = Double.parseDouble(userInput);
                if (amount > 0) {
                    return amount;
                }
                printWarning("Amount must be greater than zero. Try again.");
            } catch (NumberFormatException badNumber) {
                printWarning("That's not a valid number. Try again.");
            }
        }
    }

    /**
     * Prompt for a piece of text. Rejects blank input and any input that
     * contains the pipe character (which would corrupt the CSV file).
     */
    private static String promptNonEmptyText(Scanner scanner, String fieldName) {
        while (true) {
            System.out.print(fieldName + ": ");
            String userInput = scanner.nextLine().trim();
            if (userInput.isEmpty()) {
                printWarning(fieldName + " cannot be blank. Try again.");
            } else if (userInput.contains("|")) {
                printWarning(fieldName + " cannot contain the | character. Try again.");
            } else {
                return userInput;
            }
        }
    }

    /* ------------------------------------------------------------------
       Persist a new Transaction (to list + file)
       ------------------------------------------------------------------ */
    private static void saveTransaction(Transaction transaction) {

        transactions.add(transaction);

        String recordLine = "%s|%s|%s|%s|%.2f".formatted(
                transaction.getDate().format(DATE_FMT),
                transaction.getTime().format(TIME_FMT),
                transaction.getDescription(),
                transaction.getVendor(),
                transaction.getAmount());

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
            writer.write(recordLine);
            writer.newLine();
            writer.close();
        } catch (IOException ioException) {
            printError("Failed to write to file: " + ioException.getMessage());
        }
    }

    /* ------------------------------------------------------------------
       Ledger submenu (lists & reports)
       ------------------------------------------------------------------ */
    private static void ledgerMenu(Scanner scanner) {

        // Show a quick progress bar so entering the ledger feels like a transition.
        showProgressBar("Opening ledger", 400);

        // Sort once on entry – data does not change while inside this menu.
        transactions.sort(Comparator
                .comparing(Transaction::getDate)
                .thenComparing(Transaction::getTime)
                .reversed());

        boolean inLedgerMenu = true;
        while (inLedgerMenu) {
            printLedgerMenu();
            System.out.print(COLOR_GREEN + "Your choice: " + COLOR_RESET);

            String ledgerChoice = scanner.nextLine().trim().toUpperCase();

            switch (ledgerChoice) {
                case "A" -> displayLedger();
                case "D" -> displayDeposits();
                case "P" -> displayPayments();
                case "R" -> reportsMenu(scanner);
                case "H" -> inLedgerMenu = false;
                default -> printWarning("Invalid option – please try again.");
            }
        }
    }

    /* ---------- Pretty printing helpers (boxed Unicode table + colors) ---------- */

    private static final int W_DATE = 12;
    private static final int W_TIME = 10;
    private static final int W_DESC = 24;
    private static final int W_VENDOR = 18;
    private static final int W_AMOUNT = 12;

    private static void printTopBorder() {
        System.out.println("┌" + "─".repeat(W_DATE + 2)
                + "┬" + "─".repeat(W_TIME + 2)
                + "┬" + "─".repeat(W_DESC + 2)
                + "┬" + "─".repeat(W_VENDOR + 2)
                + "┬" + "─".repeat(W_AMOUNT + 2) + "┐");
    }

    private static void printMiddleBorder() {
        System.out.println("├" + "─".repeat(W_DATE + 2)
                + "┼" + "─".repeat(W_TIME + 2)
                + "┼" + "─".repeat(W_DESC + 2)
                + "┼" + "─".repeat(W_VENDOR + 2)
                + "┼" + "─".repeat(W_AMOUNT + 2) + "┤");
    }

    private static void printBottomBorder() {
        System.out.println("└" + "─".repeat(W_DATE + 2)
                + "┴" + "─".repeat(W_TIME + 2)
                + "┴" + "─".repeat(W_DESC + 2)
                + "┴" + "─".repeat(W_VENDOR + 2)
                + "┴" + "─".repeat(W_AMOUNT + 2) + "┘");
    }

    private static void printTableHeader() {
        printTopBorder();
        System.out.printf("│ " + COLOR_BOLD + COLOR_CYAN + "%-" + W_DATE + "s" + COLOR_RESET
                        + " │ " + COLOR_BOLD + COLOR_CYAN + "%-" + W_TIME + "s" + COLOR_RESET
                        + " │ " + COLOR_BOLD + COLOR_CYAN + "%-" + W_DESC + "s" + COLOR_RESET
                        + " │ " + COLOR_BOLD + COLOR_CYAN + "%-" + W_VENDOR + "s" + COLOR_RESET
                        + " │ " + COLOR_BOLD + COLOR_CYAN + "%" + W_AMOUNT + "s" + COLOR_RESET
                        + " │%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        printMiddleBorder();
    }

    private static void printTransactionRow(Transaction transaction) {
        String description = truncate(transaction.getDescription(), W_DESC);
        String vendor = truncate(transaction.getVendor(), W_VENDOR);

        String amountColor = transaction.getAmount() >= 0 ? COLOR_GREEN : COLOR_RED;
        String amountText = String.format("%,.2f", transaction.getAmount());

        System.out.printf("│ %-" + W_DATE + "s"
                        + " │ %-" + W_TIME + "s"
                        + " │ %-" + W_DESC + "s"
                        + " │ %-" + W_VENDOR + "s"
                        + " │ " + amountColor + "%" + W_AMOUNT + "s" + COLOR_RESET
                        + " │%n",
                transaction.getDate().format(DATE_FMT),
                transaction.getTime().format(TIME_FMT),
                description,
                vendor,
                amountText);
    }

    /**
     * Cuts a string off at the given width, adding "…" if it was too long,
     * so the table stays aligned no matter how long descriptions or vendors are.
     */
    private static String truncate(String text, int width) {
        if (text.length() <= width) return text;
        return text.substring(0, width - 1) + "…";
    }

    private static void displayLedger() {
        printTableHeader();
        for (Transaction transaction : transactions) {
            printTransactionRow(transaction);
        }
        printBottomBorder();
    }

    private static void displayDeposits() {
        printTableHeader();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                printTransactionRow(transaction);
            }
        }
        printBottomBorder();
    }

    private static void displayPayments() {
        printTableHeader();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                printTransactionRow(transaction);
            }
        }
        printBottomBorder();
    }

    /* ------------------------------------------------------------------
       Reports submenu
       ------------------------------------------------------------------ */
    private static void reportsMenu(Scanner scanner) {

        // Show a quick progress bar so entering the reports menu feels like a transition.
        showProgressBar("Opening reports", 400);

        boolean inReportsMenu = true;
        while (inReportsMenu) {
            printReportsMenu();
            System.out.print(COLOR_GREEN + "Your choice: " + COLOR_RESET);

            String reportChoice = scanner.nextLine().trim();
            LocalDate today = LocalDate.now();

            switch (reportChoice) {
                case "1" -> {
                    // Month To Date: from the 1st of this month through today
                    LocalDate startOfMonth = today.withDayOfMonth(1);
                    filterTransactionsByDate(startOfMonth, today);
                }
                case "2" -> {
                    // Previous Month: from the 1st through the last day of last month
                    LocalDate startOfLastMonth = today.minusMonths(1).withDayOfMonth(1);
                    LocalDate endOfLastMonth = startOfLastMonth.withDayOfMonth(startOfLastMonth.lengthOfMonth());
                    filterTransactionsByDate(startOfLastMonth, endOfLastMonth);
                }
                case "3" -> {
                    // Year To Date: from January 1st through today
                    LocalDate startOfYear = today.withDayOfYear(1);
                    filterTransactionsByDate(startOfYear, today);
                }
                case "4" -> {
                    // Previous Year: from January 1st through December 31st of last year
                    LocalDate startOfLastYear = today.minusYears(1).withDayOfYear(1);
                    LocalDate endOfLastYear = startOfLastYear.withDayOfYear(startOfLastYear.lengthOfYear());
                    filterTransactionsByDate(startOfLastYear, endOfLastYear);
                }
                case "5" -> {
                    String vendorName = promptNonEmptyText(scanner, "Vendor name");
                    filterTransactionsByVendor(vendorName);
                }
                case "6" -> customSearch(scanner);
                case "0" -> inReportsMenu = false;
                default -> printWarning("Invalid option – please try again.");
            }
        }
    }

    /* -------------------------- Report helpers -------------------------- */

    /**
     * Prints every transaction whose date is within [start … end] inclusive.
     */
    private static void filterTransactionsByDate(LocalDate start, LocalDate end) {
        showProgressBar("Generating report", 500);
        printTableHeader();
        boolean anyFound = false;

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();
            if (!transactionDate.isBefore(start) && !transactionDate.isAfter(end)) {
                printTransactionRow(transaction);
                anyFound = true;
            }
        }
        printBottomBorder();
        if (!anyFound) printInfo("No transactions found for the selected dates.");
    }

    /**
     * Prints every transaction for the given vendor (case-insensitive).
     */
    private static void filterTransactionsByVendor(String vendor) {
        showProgressBar("Generating report", 500);
        printTableHeader();
        boolean anyFound = false;

        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                printTransactionRow(transaction);
                anyFound = true;
            }
        }
        printBottomBorder();
        if (!anyFound) printInfo("No transactions found for that vendor.");
    }

    /**
     * Prompts user for optional criteria (date range, description,
     * vendor, exact amount) and prints matching rows.
     * Blank input on any field means "do not filter on this field."
     * Invalid (non-blank) input is rejected and re-prompted.
     */
    private static void customSearch(Scanner scanner) {

        LocalDate startDate = promptOptionalDate(scanner, "Start date");
        LocalDate endDate = promptOptionalDate(scanner, "End date");

        System.out.print("Description (blank = any): ");
        String descriptionFilter = scanner.nextLine().trim();

        System.out.print("Vendor      (blank = any): ");
        String vendorFilter = scanner.nextLine().trim();

        Double amountFilter = promptOptionalDouble(scanner, "Amount");

        showProgressBar("Searching transactions", 500);
        printTableHeader();
        boolean anyFound = false;

        for (Transaction transaction : transactions) {
            boolean matches = true;

            if (startDate != null && transaction.getDate().isBefore(startDate)) matches = false;
            if (endDate != null && transaction.getDate().isAfter(endDate)) matches = false;
            if (!descriptionFilter.isEmpty()
                    && !transaction.getDescription().equalsIgnoreCase(descriptionFilter)) matches = false;
            if (!vendorFilter.isEmpty()
                    && !transaction.getVendor().equalsIgnoreCase(vendorFilter)) matches = false;
            if (amountFilter != null && transaction.getAmount() != amountFilter) matches = false;

            if (matches) {
                printTransactionRow(transaction);
                anyFound = true;
            }
        }
        printBottomBorder();
        if (!anyFound) printInfo("No transactions match the chosen criteria.");
    }

    /* -------------------------- Optional-input helpers -------------------------- */

    /**
     * Prompts for an optional date. Blank input returns null (no filter).
     * Any non-blank input that does not parse is rejected and the prompt repeats.
     */
    private static LocalDate promptOptionalDate(Scanner scanner, String fieldName) {
        while (true) {
            System.out.print(fieldName + " (" + DATE_PATTERN + ", blank = none): ");
            String userInput = scanner.nextLine().trim();
            if (userInput.isEmpty()) return null;
            try {
                return LocalDate.parse(userInput, DATE_FMT);
            } catch (Exception bad) {
                printWarning("Invalid date format. Try again or leave blank.");
            }
        }
    }

    /**
     * Prompts for an optional number. Blank input returns null (no filter).
     * Any non-blank input that does not parse is rejected and the prompt repeats.
     */
    private static Double promptOptionalDouble(Scanner scanner, String fieldName) {
        while (true) {
            System.out.print(fieldName + " (blank = any): ");
            String userInput = scanner.nextLine().trim();
            if (userInput.isEmpty()) return null;
            try {
                return Double.parseDouble(userInput);
            } catch (Exception bad) {
                printWarning("Invalid number. Try again or leave blank.");
            }
        }
    }
}