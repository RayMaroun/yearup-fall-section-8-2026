# Trip Log

A console app that keeps a list of trips in a csv file and prints simple reports about them.

## Description of the Project

Trip Log is a Java console application for someone who drives for work and needs to keep track of their trips. Each trip has a date, a start city, an end city and a distance in miles. The app reads the trips from a file when it starts, lets the user add new trips, and prints the total distance for a month. It replaces a notebook that kept getting lost.

## Features

- Shows every trip, newest first
- Adds a trip (date, from, to, miles) and saves it to the file at once
- Prints the total miles for a month the user types in
- Searches trips by city (bonus)

## User Stories

- As a driver, I want to add a trip in a few seconds, so that I record it before I forget.
- As a driver, I want the total miles for a month, so that I can fill in my expense form.
- As a driver, I want to search by city, so that I can find a trip I made last year.

## Setup

### Prerequisites

- Java 17 (Amazon Corretto), the version used in class.
- IntelliJ IDEA Community Edition: <https://www.jetbrains.com/idea/download/>

### Running the application

1. Clone this repository, or download it as a zip and unzip it.
2. In IntelliJ: File > Open, pick the project folder (the one that holds `pom.xml`), press Open.
3. Wait for IntelliJ to finish indexing (progress bar at the bottom).
4. Open `src/main/java/com/pluralsight/TripLog.java`.
5. Right-click inside the file and choose Run 'TripLog.main()'.

### Data and configuration

- `trips.csv` sits at the project root, next to `pom.xml`. The program reads it at start and appends to it when a trip is added. The first line is a header: `date|from|to|miles`.

## Technologies Used

- Java 17
- IntelliJ IDEA

## Demo

![Main menu](images/menu.png)

![Monthly total](images/month-total.png)

## A piece of code I am proud of

```java
// Reads the file once and turns every line into a Trip object
while ((line = reader.readLine()) != null) {
    String[] parts = line.split("\\|");
    LocalDate date = LocalDate.parse(parts[0]);
    int miles = Integer.parseInt(parts[3]);
    trips.add(new Trip(date, parts[1], parts[2], miles));
}
```

This loop is where the file becomes objects. I like that the header is skipped once before the loop instead of inside it with an if, and that the split uses `\\|` because a bare `|` means "or" in a pattern, which took me an hour to find out.

## Future Work

- Sort the search results by date
- Validate the date the user types instead of letting the program crash on a typo

## Resources

- Java YearUp Fall 2026 hub: <https://raymaroun.github.io/yearup-java-fall2026/>
- Workbook 3a, Section 1-2 Using BufferedReader and Section 2-1 Writing to Files

## Author

- **Alex Rivera**

## Thanks

- Thank you to Raymond for the file reading demo that this app is built on.
