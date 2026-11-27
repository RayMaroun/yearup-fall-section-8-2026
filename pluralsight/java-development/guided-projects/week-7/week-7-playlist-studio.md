# Week 7 Guided Project: Playlist Studio

<!--
Verification markers (read by the checker, invisible on GitHub):
  apply: file / after / before / end / replace / delete  = how the next code block changes a file
  run            = run Main, no typed input
  final          = the built file must equal the next block
-->

This is a self-study project. You build one small program, step by step, and it uses everything from week 7: an interface and the classes that implement it, an interface as a variable type, one list that holds unrelated classes, `instanceof` with a downcast, a default method, a class that implements two interfaces, `Comparable` for sorting, a generic class of your own, a bounded type, lambdas, and the stream methods `filter`, `forEach`, `count`, `map`, `sorted` and `reduce`. It also reuses earlier weeks: classes with private fields, constructors, `toString`, ArrayList and for-each.

The program is the back room of a small music app. A `Song` and a `Podcast` have almost nothing in common: one has an artist, the other has a show and an episode number. But both can be played and both have a length, and that is all the player needs to know. `Main` builds a few songs and podcasts, puts them in one list, sorts the songs, fills two playlists and then answers a few questions about the tracks, first with loops and then with streams.

Type every line yourself, run the program at the end of every step, and compare your output with the expected output. If it does not match, compare your code with the step line by line before you move on. Most problems are a missing semicolon, a missing brace, or a capital letter in the wrong place.

Every step uses the same sample data. All lengths are in seconds.

- Song "Paper Lanterns" by Tin Lighthouse, 215 seconds.
- Song "Slow Tide" by Velvet Otters, 332 seconds.
- Song "Borrowed Umbrella" by Tin Lighthouse, 187 seconds.
- Song "Night Bus Home" by The Quiet Kettles, 215 seconds.
- Podcast "Code and Coffee", episode 12, 1840 seconds.
- Podcast "Small Talk Science", episode 3, 754 seconds.

A word on commit messages. Every commit message has the shape `type: what changed`, where the type is one of `feat` (new behavior), `fix`, `docs`, `refactor` (same behavior, cleaner code), `chore` (setup, no code change) or `test`. The message at the end of every step already has that shape. Copy it exactly.

## Step 1 - Create the project

### Type this

Create a new project in IntelliJ with these settings. Open Advanced Settings to see the GroupId field, and uncheck "Add sample code" so the project starts empty.

```text
Name:                   playlist-studio
Build system:           Maven
JDK:                    17
Create Git repository:  ticked
GroupId:                com.pluralsight
```

For Location, use the `guided-projects` folder inside `java-development`, next to your workbook folders: Desktop > pluralsight > java-development > guided-projects. Create the folder if it does not exist yet.

Click Create. Then share the project on GitHub right away: VCS menu, Share Project on GitHub. Keep the repository name `playlist-studio`. When IntelliJ asks for the first commit message, use the one at the end of this step.

### What is happening

- Maven gives every project the same folder layout: your code goes in `src/main/java`.
- The GroupId `com.pluralsight` becomes the package name. Your classes and interfaces will live in `com.pluralsight`.
- One project, one repository. This project gets its own repository on GitHub, and nothing else goes in it.
- `chore` is the right type for this commit: the project exists, but no program code was written.

### Run it

<!-- no-run -->
Nothing runs yet. Check that the Project panel shows this:

```text
playlist-studio  >  src  >  main  >  java
playlist-studio  >  pom.xml
```

Commit and push: "chore: create the playlist-studio project"

From now on, commit and push after every step, with the message given at the end of the step. One step, one commit.

## Step 2 - The class and main

### Type this

Right-click the `java` folder under `src/main`, choose New, Java Class, and type `com.pluralsight.Main`. IntelliJ creates the package and the class. Make the file look exactly like this:

<!-- apply: file Main.java -->
```java
package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Playlist Studio");
    }
}
```

### What is happening

- `package com.pluralsight;` must be the first line. It says which package the class belongs to.
- `public class Main` must match the file name `Main.java` exactly, capitals included.
- `main` is where the program starts. Everything else in this project is used from here.

### Run it

Click the green arrow next to `main` and choose Run.

<!-- run -->
```text
Welcome to the Playlist Studio
```

Commit and push: "feat: add Main with a welcome line"

## Step 3 - The Playable interface

### Type this

Right-click the `com.pluralsight` package, choose New, Java Class, and type `Playable`. Before you press Enter, pick Interface in the list under the name. Make the file look exactly like this:

<!-- apply: file Playable.java -->
```java
package com.pluralsight;

public interface Playable {
    void play();

    int getLengthInSeconds();
}
```

Do not touch `Main`.

### What is happening

- An interface is a contract. It lists methods that a class promises to have, and says nothing about how they work.
- The two methods have no body: no braces, a semicolon instead. They are abstract methods, like the abstract method from week 6, but here you do not write the word `abstract`. In an interface that is assumed.
- You do not write `public` either. Every method in an interface is public.
- There are no fields and no constructor. An interface describes what something can do, not what it is made of.
- Nothing uses the interface yet, so the output is the same as in Step 2.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
```

Commit and push: "feat: add the Playable interface"

## Step 4 - Song implements Playable

### Type this

Right-click the `com.pluralsight` package, choose New, Java Class, and type `Song`. Make the file look exactly like this:

<!-- apply: file Song.java -->
```java
package com.pluralsight;

public class Song implements Playable {
    private String title;
    private String artist;
    private int lengthInSeconds;

    public Song(String title, String artist, int lengthInSeconds) {
        this.title = title;
        this.artist = artist;
        this.lengthInSeconds = lengthInSeconds;
    }
}
```

IntelliJ underlines the first line of the class in red. That is correct for now: the class promised to be `Playable` and has not kept the promise yet. Keep typing.

Add the two getters at the bottom of the class, under the constructor:

<!-- apply: end Song.java -->
```java

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }
```

Then the two methods the interface asks for, at the bottom of the class:

<!-- apply: end Song.java -->
```java

    // The two methods Playable asks for
    @Override
    public void play() {
        System.out.println("Playing the song " + this.title + " by " + this.artist);
    }

    @Override
    public int getLengthInSeconds() {
        return this.lengthInSeconds;
    }
```

The red line is gone. In `Main`, add these lines under the welcome line:

<!-- apply: after Main.java | System.out.println("Welcome to the Playlist Studio"); -->
```java

        Song lanterns = new Song("Paper Lanterns", "Tin Lighthouse", 215);
        lanterns.play();
        System.out.println(lanterns.getLengthInSeconds() + " seconds");
```

### What is happening

- `implements Playable` is the promise: this class has every method the interface lists.
- A class implements an interface by writing a body for each of its methods. Same name, same parameters, same return type, and `public`.
- `@Override` works here as it did in week 6. It asks the compiler to check that the method really comes from the interface. Type `Play` with a capital P and you get an error at once.
- `getTitle` and `getArtist` are the song's own methods. The interface knows nothing about them, and it does not need to.
- `extends` is for a parent class and says is-a. `implements` is for an interface and says can-do: a Song can be played.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Playing the song Paper Lanterns by Tin Lighthouse
215 seconds
```

Commit and push: "feat: add the Song class"

## Step 5 - Podcast implements Playable

### Type this

Right-click the `com.pluralsight` package, choose New, Java Class, and type `Podcast`. Make the file look exactly like this:

<!-- apply: file Podcast.java -->
```java
package com.pluralsight;

public class Podcast implements Playable {
    private String showName;
    private int episodeNumber;
    private int lengthInSeconds;

    public Podcast(String showName, int episodeNumber, int lengthInSeconds) {
        this.showName = showName;
        this.episodeNumber = episodeNumber;
        this.lengthInSeconds = lengthInSeconds;
    }
}
```

Add the two getters at the bottom of the class, under the constructor:

<!-- apply: end Podcast.java -->
```java

    public String getShowName() {
        return this.showName;
    }

    public int getEpisodeNumber() {
        return this.episodeNumber;
    }
```

Then the two methods the interface asks for, at the bottom of the class:

<!-- apply: end Podcast.java -->
```java

    // The two methods Playable asks for
    @Override
    public void play() {
        System.out.println("Playing episode " + this.episodeNumber + " of the podcast " + this.showName);
    }

    @Override
    public int getLengthInSeconds() {
        return this.lengthInSeconds;
    }
```

In `Main`, add these lines under the line that prints the seconds of the song:

<!-- apply: after Main.java | lanterns.getLengthInSeconds() + " seconds"); -->
```java

        Podcast coffee = new Podcast("Code and Coffee", 12, 1840);
        coffee.play();
        System.out.println(coffee.getLengthInSeconds() + " seconds");
```

### What is happening

- `Song` and `Podcast` are unrelated classes. Neither is the parent of the other, and they share no parent class that you wrote.
- A podcast is not a song and a song is not a podcast, so there is no is-a sentence that connects them. Inheritance would be the wrong tool.
- What they share is an ability: both can be played and both have a length. An interface is the tool for that.
- Each class keeps the promise in its own way. `play()` in `Podcast` prints a different sentence, built from different fields.
- Any class can sign the same contract, wherever it sits. That is the difference with a parent class: a class picks its parent once, but it can implement an interface no matter what it extends.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Playing the song Paper Lanterns by Tin Lighthouse
215 seconds
Playing episode 12 of the podcast Code and Coffee
1840 seconds
```

Commit and push: "feat: add the Podcast class"

## Step 6 - A promise that is not kept

### Type this

What happens when a class says `implements Playable` and leaves a method out? In `Playable.java`, add this commented line at the very bottom of the file, under the `}` that closes the interface:

<!-- apply: after Playable.java | int getLengthInSeconds(); >> } -->
```java

//class Jingle implements Playable { }           // uncomment: Jingle is not abstract and does not override abstract method getLengthInSeconds() in Playable
```

Remove the `//` at the start of the line, read the error, then put the `//` back.

### What is happening

- `Jingle` promises to be `Playable` and has an empty body. The compiler refuses the class, and the message names a method that is missing.
- That is the whole point of an interface. A class cannot say it is `Playable` and forget a method. The mistake is found when you compile, not when a user presses play.
- The message offers a way out, "is not abstract": an abstract class may leave the methods to its children. `Jingle` is meant to be a normal class, so the fix is to write the methods.
- IntelliJ shows the same problem with a red line under the class name, as it did for a moment in Step 4, and offers "Implement methods" to write the empty methods for you.
- The line stays in the file as a comment, so the program runs as before. `docs` is the right commit type: only a comment was added.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Playing the song Paper Lanterns by Tin Lighthouse
215 seconds
Playing episode 12 of the podcast Code and Coffee
1840 seconds
```

Commit and push: "docs: show what a missing interface method does"

## Step 7 - An interface as a variable type

### Type this

In `Main`, add these lines under the line that prints the seconds of the podcast, just above the `}` that closes `main`:

<!-- apply: after Main.java | coffee.getLengthInSeconds() + " seconds"); -->
```java

        // An interface as a variable type: the variable is a Playable, the object is a Song
        Playable current = lanterns;
        current.play();
        //System.out.println(current.getArtist());       // uncomment: cannot find symbol

        current = coffee;                                // the same variable, now a Podcast
        current.play();
```

### What is happening

- An interface is a type. A `Playable` variable can hold any object whose class implements `Playable`. No cast, no conversion.
- The variable decides what you are allowed to call. `current` is a `Playable`, and `Playable` has no `getArtist`, so that line does not compile even though the object really is a song. Remove the `//` to see it, then put it back.
- The object decides which version runs. `current.play()` printed the song sentence the first time and the podcast sentence the second time. One call, many forms: polymorphism, this time through an interface.
- `new Playable()` does not compile. An interface has no constructor and can never be an object of its own, like an abstract class.
- You have used this since week 4: `List<String> names = new ArrayList<>();`. `List` is an interface, `ArrayList` is a class that implements it.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Playing the song Paper Lanterns by Tin Lighthouse
215 seconds
Playing episode 12 of the podcast Code and Coffee
1840 seconds
Playing the song Paper Lanterns by Tin Lighthouse
Playing episode 12 of the podcast Code and Coffee
```

Commit and push: "feat: hold a song and a podcast in a Playable variable"

## Step 8 - toString in both classes

### Type this

In `Song.java`, add this method at the bottom of the class, under `getLengthInSeconds`:

<!-- apply: end Song.java -->
```java

    @Override
    public String toString() {
        return this.title + " by " + this.artist;
    }
```

In `Podcast.java`, add this method at the bottom of the class, under `getLengthInSeconds`:

<!-- apply: end Podcast.java -->
```java

    @Override
    public String toString() {
        return this.showName + ", episode " + this.episodeNumber;
    }
```

In `Main`, add these lines at the bottom of `main`, under the second `current.play();`:

<!-- apply: after Main.java | current = coffee; >> current.play(); -->
```java

        System.out.println(lanterns);
        System.out.println(current);
```

### What is happening

- This is week 6 again: `toString` comes from `Object`, the parent of every class, and each class overrides it.
- `System.out.println(lanterns)` calls `toString` for you.
- `System.out.println(current)` works too, although `current` is a `Playable` and the interface does not list `toString`. Every object has the methods of `Object`, whatever the type of the variable.
- `current` still points at the podcast, so the podcast version ran.
- The two classes now have three methods with the same name: `play`, `getLengthInSeconds` and `toString`. Only the first two are part of the contract.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Playing the song Paper Lanterns by Tin Lighthouse
215 seconds
Playing episode 12 of the podcast Code and Coffee
1840 seconds
Playing the song Paper Lanterns by Tin Lighthouse
Playing episode 12 of the podcast Code and Coffee
Paper Lanterns by Tin Lighthouse
Code and Coffee, episode 12
```

Commit and push: "feat: override toString in Song and Podcast"

## Step 9 - One list for songs and podcasts

### Type this

In `Main`, add these two lines under `package com.pluralsight;`:

<!-- apply: after Main.java | package com.pluralsight; -->
```java

import java.util.ArrayList;
import java.util.List;
```

Then replace everything inside `main` from `lanterns.play();` down to `System.out.println(current);` with these lines. The welcome line and the line that creates `lanterns` stay.

<!-- apply: replace Main.java | lanterns.play(); >> System.out.println(current); -->
```java
        Song tide = new Song("Slow Tide", "Velvet Otters", 332);
        Song umbrella = new Song("Borrowed Umbrella", "Tin Lighthouse", 187);
        Song nightBus = new Song("Night Bus Home", "The Quiet Kettles", 215);
        Podcast coffee = new Podcast("Code and Coffee", 12, 1840);
        Podcast science = new Podcast("Small Talk Science", 3, 754);
```

Add these lines under the line that creates `science`:

<!-- apply: after Main.java | Podcast science = new Podcast( -->
```java

        // One list, two unrelated classes inside
        List<Playable> tracks = new ArrayList<>();
        tracks.add(lanterns);
        tracks.add(tide);
        tracks.add(coffee);
        tracks.add(umbrella);
        tracks.add(nightBus);
        tracks.add(science);

        for (Playable track : tracks) {
            System.out.println(track + ", " + track.getLengthInSeconds() + " seconds");
        }
```

### What is happening

- `List<Playable>` is a list of interface references. Each slot can hold an object of any class that implements `Playable`, so songs and podcasts sit in the same list.
- Without the interface you would need a `List<Song>`, a `List<Podcast>` and one loop for each.
- The loop variable is a `Playable`. The loop does not know and does not ask what kind each track is. It calls `getLengthInSeconds()`, and every object answers with its own version.
- Write a third class that implements `Playable` one day and this loop does not change by one character.
- `Main` shrinks on purpose. The experiments from Steps 4 to 8 did their job, and the commits keep the old lines.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 215 seconds
Slow Tide by Velvet Otters, 332 seconds
Code and Coffee, episode 12, 1840 seconds
Borrowed Umbrella by Tin Lighthouse, 187 seconds
Night Bus Home by The Quiet Kettles, 215 seconds
Small Talk Science, episode 3, 754 seconds
```

Commit and push: "feat: put every track in one list"

## Step 10 - instanceof and a downcast

### Type this

In `Main`, add these lines after the `}` that closes the `for` loop, just above the `}` that closes `main`:

<!-- apply: after Main.java | track.getLengthInSeconds() + " seconds"); >> } -->
```java

        System.out.println("Details:");
        for (Playable track : tracks) {
            if (track instanceof Song) {
                Song found = (Song) track;               // downcast: safe, instanceof just confirmed the type
                System.out.println("Song, the artist is " + found.getArtist());
            } else if (track instanceof Podcast) {
                System.out.println("Podcast, episode " + ((Podcast) track).getEpisodeNumber());
            }
        }
```

### What is happening

- `getArtist` exists only in `Song` and `getEpisodeNumber` only in `Podcast`. The loop variable is a `Playable`, so neither can be called on it directly.
- `track instanceof Song` asks the object a question: are you a Song? It answers true or false.
- `(Song) track` is a downcast: it gives you the same object through a `Song` variable, and now the song's own method is reachable. Nothing is copied or converted.
- The `Podcast` branch does the same downcast in one line, with the extra pair of parentheses around the cast.
- Always ask with `instanceof` before you cast. `(Song) coffee` does not even compile, and `(Song) track` on a podcast compiles and then crashes at run time with a `ClassCastException`.
- Use this only for what a class has outside the interface. For the length you did not need it: the interface method already picked the right version.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 215 seconds
Slow Tide by Velvet Otters, 332 seconds
Code and Coffee, episode 12, 1840 seconds
Borrowed Umbrella by Tin Lighthouse, 187 seconds
Night Bus Home by The Quiet Kettles, 215 seconds
Small Talk Science, episode 3, 754 seconds
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
```

Commit and push: "feat: print track details with instanceof"

## Step 11 - A default method

### Type this

215 seconds is hard to read. A music app shows 3:35. In `Playable.java`, add this method inside the interface, under `int getLengthInSeconds();`:

<!-- apply: after Playable.java | int getLengthInSeconds(); -->
```java

    // default method: it has a body, and every class that implements Playable gets it
    default String getLengthLabel() {
        int minutes = getLengthInSeconds() / 60;
        int seconds = getLengthInSeconds() % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
```

In `Main`, replace the `System.out.println` line inside the first `for` loop with this:

<!-- apply: replace Main.java | track.getLengthInSeconds() + " seconds"); -->
```java
            System.out.println(track + ", " + track.getLengthLabel());
```

Do not touch `Song` or `Podcast`.

### What is happening

- A default method is a method in an interface that has a body. The word `default` in front is what allows it.
- `Song` and `Podcast` did not change, and both have `getLengthLabel()` now. A class that implements the interface gets the default method without writing anything.
- That is why default methods exist: an interface can grow a new method without breaking the classes that already implement it. Add a normal method without a body instead, and `Song` and `Podcast` stop compiling until both write it.
- The default method calls `getLengthInSeconds()`, a method the interface only promises. When the code runs, the object is a real song or podcast, so a real version is there.
- `/` on two ints drops the remainder and `%` gives the remainder: 215 / 60 is 3 and 215 % 60 is 35. `%02d` prints a number with two digits, so 5 seconds shows as `05`.
- A class may still override a default method when it wants its own version. Challenge 2 does that.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Small Talk Science, episode 3, 12:34
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
```

Commit and push: "feat: add a default method for the length label"

## Step 12 - One class, two interfaces

### Type this

Songs can be shared with a friend. Podcasts, in this app, cannot. Right-click the `com.pluralsight` package, choose New, Java Class, type `Shareable` and pick Interface. Make the file look exactly like this:

<!-- apply: file Shareable.java -->
```java
package com.pluralsight;

public interface Shareable {
    String getShareText();
}
```

In `Song.java`, replace the line `public class Song implements Playable {` with this:

<!-- apply: replace Song.java | public class Song implements Playable { -->
```java
public class Song implements Playable, Shareable {       // one class, two interfaces
```

Add this method at the bottom of the class, under `toString`:

<!-- apply: end Song.java -->
```java

    // The method Shareable asks for
    @Override
    public String getShareText() {
        return "Listen to " + this.title + " by " + this.artist + "!";
    }
```

In `Main`, add these lines after the `}` that closes the `Details` loop, just above the `}` that closes `main`:

<!-- apply: after Main.java | ((Podcast) track).getEpisodeNumber()); >> } >> } -->
```java

        System.out.println("Ready to share:");
        for (Playable track : tracks) {
            if (track instanceof Shareable) {            // does the class of this object implement Shareable?
                System.out.println(((Shareable) track).getShareText());
            }
        }
```

### What is happening

- A class can extend only one parent class, but it can implement as many interfaces as it needs. The names go after `implements`, separated by commas.
- `Song` now keeps two promises: the two methods of `Playable` and the one method of `Shareable`.
- `Podcast` implements only `Playable`. Nothing forces it to be shareable, and nothing in `Podcast.java` changed.
- `instanceof` works with an interface too. The question is no longer "are you a Song?" but "can you be shared?". The loop does not care which class says yes.
- The cast goes to the interface type: `(Shareable) track`. Through a `Shareable` variable you can call `getShareText()` and nothing else.
- Small interfaces are a good habit. One ability, one interface, and each class picks the abilities it really has.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Small Talk Science, episode 3, 12:34
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
Ready to share:
Listen to Paper Lanterns by Tin Lighthouse!
Listen to Slow Tide by Velvet Otters!
Listen to Borrowed Umbrella by Tin Lighthouse!
Listen to Night Bus Home by The Quiet Kettles!
```

Commit and push: "feat: add the Shareable interface to Song"

## Step 13 - Comparable: a list that sorts itself

### Type this

Java already has interfaces of its own. `Comparable` is one of them, and it has a single method, `compareTo`. In `Song.java`, replace the first line of the class with this:

<!-- apply: replace Song.java | public class Song implements Playable, Shareable { -->
```java
public class Song implements Playable, Shareable, Comparable<Song> {
```

Add this method at the bottom of the class, under `getShareText`:

<!-- apply: end Song.java -->
```java

    // The method Comparable asks for: negative = this song first, positive = the other first, 0 = equal
    @Override
    public int compareTo(Song other) {
        if (this.lengthInSeconds != other.lengthInSeconds) {
            return this.lengthInSeconds - other.lengthInSeconds;    // shortest first
        }
        return this.title.compareTo(other.title);                   // same length: by title
    }
```

In `Main`, add this line under `import java.util.ArrayList;`:

<!-- apply: after Main.java | import java.util.ArrayList; -->
```java
import java.util.Collections;
```

Then add these lines at the bottom of `main`, after the `}` that closes the `Ready to share` loop:

<!-- apply: after Main.java | ((Shareable) track).getShareText()); >> } >> } -->
```java

        // Song implements Comparable, so Java knows how to put songs in order
        List<Song> songs = new ArrayList<>();
        songs.add(lanterns);
        songs.add(tide);
        songs.add(umbrella);
        songs.add(nightBus);
        Collections.sort(songs);

        System.out.println("Songs, shortest first:");
        for (Song song : songs) {
            System.out.println(song + ", " + song.getLengthLabel());
        }
```

### What is happening

- `Collections.sort` can sort Strings and numbers because Java knows their order. It has no idea how to order songs: by title, by artist, by length? Before this step, `Collections.sort(songs)` does not compile.
- `Comparable<Song>` is the contract that answers the question. The class writes `compareTo`, and from then on Java can compare one song with another.
- `compareTo` returns an int. A negative number means this song comes first, a positive number means the other one comes first, zero means the order does not matter. 187 minus 215 is negative, so the shorter song goes first.
- Two songs are 215 seconds long. The tiebreak decides: when the lengths are equal, compare the titles. `String` implements `Comparable` too, so the title has a `compareTo` of its own, and "Night Bus Home" comes before "Paper Lanterns".
- `Collections.sort(songs)` rearranges the list itself. Remember that for Step 22.
- You never call `compareTo` yourself here. `Collections.sort` calls it, many times, with two songs at a time.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Small Talk Science, episode 3, 12:34
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
Ready to share:
Listen to Paper Lanterns by Tin Lighthouse!
Listen to Slow Tide by Velvet Otters!
Listen to Borrowed Umbrella by Tin Lighthouse!
Listen to Night Bus Home by The Quiet Kettles!
Songs, shortest first:
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
```

Commit and push: "feat: sort the songs with Comparable"

## Step 14 - A generic class

### Type this

The studio has named slots: one for the mood of the evening, one for the opening song, one for the volume. A slot has a name and holds one thing, and the type of that thing is different every time. Right-click the `com.pluralsight` package, choose New, Java Class, and type `Slot`. Make the file look exactly like this:

<!-- apply: file Slot.java -->
```java
package com.pluralsight;

public class Slot<T> {                           // T is the type parameter: chosen when the object is created
    private String name;
    private T content;

    public Slot(String name, T content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return this.name;
    }
}
```

Add the getter and the setter for `content` at the bottom of the class, under `getName`:

<!-- apply: end Slot.java -->
```java

    public T getContent() {
        return this.content;
    }

    public void setContent(T content) {
        this.content = content;
    }
```

In `Main`, add these lines at the bottom of `main`, after the `}` that closes the last loop:

<!-- apply: after Main.java | System.out.println(song + ", " + song.getLengthLabel()); >> } -->
```java

        // A generic class: in this Slot, T is String
        Slot<String> mood = new Slot<>("Mood", "Rainy evening");
        System.out.println(mood.getName() + ": " + mood.getContent());
        mood.setContent("Sunday morning");
        System.out.println(mood.getName() + ": " + mood.getContent().toUpperCase());
        //mood.setContent(42);                           // uncomment: incompatible types: int cannot be converted to String
```

### What is happening

- `Slot<T>` is a generic class. `T` is a type parameter: a placeholder for a type that is not known yet. Inside the class you use `T` wherever the real type will go: the field, the constructor parameter, the return type of the getter.
- `Slot<String>` fills in the placeholder. For the variable `mood`, read every `T` in the class as `String`.
- `mood.getContent()` gives back a `String`, not an `Object`, so `.toUpperCase()` works on it with no cast.
- The compiler guards the type. `mood.setContent(42)` does not compile, because this slot was created for Strings. Remove the `//` to see it, then put it back.
- You have used generic classes since week 4. `ArrayList<String>` and `HashMap<String, Integer>` are generic classes written by someone else. This is the first one you wrote.
- `new Slot<>(...)` with empty angle brackets lets Java take the type from the left side, as in `new ArrayList<>()`.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Small Talk Science, episode 3, 12:34
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
Ready to share:
Listen to Paper Lanterns by Tin Lighthouse!
Listen to Slow Tide by Velvet Otters!
Listen to Borrowed Umbrella by Tin Lighthouse!
Listen to Night Bus Home by The Quiet Kettles!
Songs, shortest first:
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Mood: Rainy evening
Mood: SUNDAY MORNING
```

Commit and push: "feat: add the generic Slot class"

## Step 15 - The same class with other types

### Type this

In `Main`, add these lines at the bottom of `main`, under the commented `mood.setContent(42);` line:

<!-- apply: after Main.java | //mood.setContent(42); -->
```java

        // The same class, and T is Song this time
        Slot<Song> opener = new Slot<>("Opener", lanterns);
        System.out.println(opener.getName() + ": " + opener.getContent().getArtist());
        //opener.setContent(coffee);                     // uncomment: incompatible types: Podcast cannot be converted to Song

        Slot<Integer> volume = new Slot<>("Volume", 7);  // a wrapper type: Integer, not int
        System.out.println(volume.getName() + ": " + (volume.getContent() + 1));
```

Do not touch `Slot.java`.

### What is happening

- One class, three types. You did not write a `StringSlot`, a `SongSlot` and a `NumberSlot`. `Slot.java` did not change at all.
- `opener.getContent()` is a `Song`, so `.getArtist()` is right there. `volume.getContent()` is an `Integer`, so you can add 1 to it.
- Each slot is locked to its type. A podcast does not go in a `Slot<Song>`: remove the `//` to read the error, then put it back.
- The type between the angle brackets must be a class, never a primitive. That is why it says `Slot<Integer>` and not `Slot<int>`, exactly as with `ArrayList<Integer>`.
- Before generics the only way to write one class for every type was a field of type `Object`. It works, but every `getContent()` then needs a cast, and a wrong cast crashes at run time. With generics the mistake is found when you compile.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Small Talk Science, episode 3, 12:34
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
Ready to share:
Listen to Paper Lanterns by Tin Lighthouse!
Listen to Slow Tide by Velvet Otters!
Listen to Borrowed Umbrella by Tin Lighthouse!
Listen to Night Bus Home by The Quiet Kettles!
Songs, shortest first:
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Mood: Rainy evening
Mood: SUNDAY MORNING
Opener: Tin Lighthouse
Volume: 8
```

Commit and push: "feat: use Slot with a Song and an Integer"

## Step 16 - A bounded type: Playlist

### Type this

`T` can be anything, and that has a price. In `Slot.java`, add these two lines at the bottom of the class, under `setContent`:

<!-- apply: end Slot.java -->
```java

    // T can be anything, so nothing says the content has a play() method
    //public void play() { this.content.play(); }      // uncomment: cannot find symbol
```

A playlist needs to call methods on its items, so it must limit what `T` can be. Right-click the `com.pluralsight` package, choose New, Java Class, and type `Playlist`. Make the file look exactly like this:

<!-- apply: file Playlist.java -->
```java
package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Playlist<T extends Playable> {      // bounded type: T must be Playable or a class that implements it
    private String name;
    private List<T> items = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }
}
```

Add these two methods at the bottom of the class, under the constructor:

<!-- apply: end Playlist.java -->
```java

    public String getName() {
        return this.name;
    }

    public void add(T item) {
        this.items.add(item);
    }
```

And the method that needs the limit, under `add`:

<!-- apply: end Playlist.java -->
```java

    public int getTotalSeconds() {
        int total = 0;
        for (T item : this.items) {
            total += item.getLengthInSeconds();          // allowed: every T is a Playable
        }
        return total;
    }
```

In `Main`, add these lines at the bottom of `main`, under the line that prints the volume:

<!-- apply: after Main.java | (volume.getContent() + 1)); -->
```java

        // A bounded generic class: it only accepts types that are Playable
        Playlist<Song> roadTrip = new Playlist<>("Road Trip");
        roadTrip.add(lanterns);
        roadTrip.add(tide);
        System.out.println(roadTrip.getName() + ": " + roadTrip.getTotalSeconds() + " seconds");

        Playlist<Playable> commute = new Playlist<>("Commute");     // T can be the interface itself
        commute.add(nightBus);
        commute.add(coffee);
        System.out.println(commute.getName() + ": " + commute.getTotalSeconds() + " seconds");
        //Playlist<String> words = new Playlist<>("Words");         // uncomment: type argument String is not within bounds of type-variable T
```

### What is happening

- In `Slot<T>`, `T` could be a String or an Integer, so the compiler cannot let you call `play()` on the content. Remove the `//` in `Slot.java` to see the error, then put it back.
- `<T extends Playable>` limits the placeholder: only `Playable` itself or a class that implements it is accepted. This is called a bounded type. The word is `extends` even though `Playable` is an interface. In this spot `extends` covers both.
- Because of the limit, `item.getLengthInSeconds()` compiles inside `Playlist`. Whatever `T` turns out to be, it is certain to have that method.
- `Playlist<Song>` takes songs only. `Playlist<Playable>` takes any mix of songs and podcasts. `Playlist<String>` is refused before the program ever runs: a String cannot be played.
- 215 + 332 is 547, and 215 + 1840 is 2055.
- So there are two kinds of generic class. Unbounded, like `Slot<T>`: stores anything, calls nothing on it. Bounded, like `Playlist<T extends Playable>`: stores less, and can use what the interface promises.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Small Talk Science, episode 3, 12:34
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
Ready to share:
Listen to Paper Lanterns by Tin Lighthouse!
Listen to Slow Tide by Velvet Otters!
Listen to Borrowed Umbrella by Tin Lighthouse!
Listen to Night Bus Home by The Quiet Kettles!
Songs, shortest first:
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Mood: Rainy evening
Mood: SUNDAY MORNING
Opener: Tin Lighthouse
Volume: 8
Road Trip: 547 seconds
Commute: 2055 seconds
```

Commit and push: "feat: add the bounded Playlist class"

## Step 17 - Four questions, four loops

### Type this

Time to ask the list of tracks some questions. First the way you already know. In `Main`, add these lines at the bottom of `main`, under the commented `Playlist<String>` line:

<!-- apply: after Main.java | //Playlist<String> words = new Playlist<>("Words"); -->
```java

        // Question 1: which tracks are longer than 5 minutes?
        List<Playable> longTracks = new ArrayList<>();
        for (Playable track : tracks) {
            if (track.getLengthInSeconds() > 300) {
                longTracks.add(track);
            }
        }
        System.out.println("Longer than 5 minutes:");
        for (Playable track : longTracks) {
            System.out.println(track + ", " + track.getLengthLabel());
        }
```

Under them, the second question:

<!-- apply: after Main.java | for (Playable track : longTracks) { >> } -->
```java

        // Question 2: how many tracks are shorter than 4 minutes?
        int shortCount = 0;
        for (Playable track : tracks) {
            if (track.getLengthInSeconds() < 240) {
                shortCount++;
            }
        }
        System.out.println("Shorter than 4 minutes: " + shortCount);
```

The third:

<!-- apply: after Main.java | System.out.println("Shorter than 4 minutes: " + shortCount); -->
```java

        // Question 3: what are the song titles, in capital letters?
        List<String> titles = new ArrayList<>();
        for (Song song : songs) {
            titles.add(song.getTitle().toUpperCase());
        }
        System.out.println("Titles: " + titles);
```

And the fourth:

<!-- apply: after Main.java | System.out.println("Titles: " + titles); -->
```java

        // Question 4: how long is everything together?
        int totalSeconds = 0;
        for (Playable track : tracks) {
            totalSeconds += track.getLengthInSeconds();
        }
        System.out.println("Total: " + totalSeconds + " seconds");
```

### What is happening

- Nothing here is new. Four questions, five loops, and four different jobs: keep some elements, count some elements, turn each element into something else, and add everything up.
- Every loop has the same skeleton: make an empty result, walk the list, test or change each element, update the result. Only one line in the middle of each loop is really about the question.
- That one line is what matters. The rest is machinery you have typed a hundred times.
- The titles come out shortest song first, because `songs` was sorted in Step 13.
- The next six steps replace these loops, one at a time, with streams. Keep the output of this step in mind: it is the answer the streams have to give too.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Small Talk Science, episode 3, 12:34
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
Ready to share:
Listen to Paper Lanterns by Tin Lighthouse!
Listen to Slow Tide by Velvet Otters!
Listen to Borrowed Umbrella by Tin Lighthouse!
Listen to Night Bus Home by The Quiet Kettles!
Songs, shortest first:
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Mood: Rainy evening
Mood: SUNDAY MORNING
Opener: Tin Lighthouse
Volume: 8
Road Trip: 547 seconds
Commute: 2055 seconds
Longer than 5 minutes:
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Small Talk Science, episode 3, 12:34
Shorter than 4 minutes: 3
Titles: [BORROWED UMBRELLA, NIGHT BUS HOME, PAPER LANTERNS, SLOW TIDE]
Total: 3543 seconds
```

Commit and push: "feat: answer four questions with loops"

## Step 18 - filter: keep what matches

### Type this

In `Main`, add this line under `import java.util.List;`:

<!-- apply: after Main.java | import java.util.List; -->
```java
import java.util.stream.Collectors;
```

In Question 1, replace the first loop, from `List<Playable> longTracks = new ArrayList<>();` down to the `}` that closes that `for`, with these three lines. The loop that prints stays.

<!-- apply: replace Main.java | List<Playable> longTracks = new ArrayList<>(); >> } >> } -->
```java
        List<Playable> longTracks = tracks.stream()
                .filter(track -> track.getLengthInSeconds() > 300)
                .collect(Collectors.toList());
```

### What is happening

- `tracks.stream()` turns the list into a stream: the elements flow past one at a time, and you say what should happen to them on the way. The list itself is not changed.
- `filter` keeps the elements that pass a test and drops the others.
- `track -> track.getLengthInSeconds() > 300` is a lambda expression: a small method without a name. On the left of the arrow is the parameter, on the right is what it returns. Read it as "given a track, is it longer than 300 seconds?".
- You do not write the type of `track`. It is a stream of `Playable`, so Java knows. The name is your choice; `t` would work, `track` reads better.
- `.collect(Collectors.toList())` ends the stream and puts what is left into a new list.
- Seven lines became three, and the `if` from the loop is the only part that survived, as the lambda. The output is the same, which is why this commit is a `refactor`.

### Run it

The output is the same as in Step 17.

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Small Talk Science, episode 3, 12:34
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
Ready to share:
Listen to Paper Lanterns by Tin Lighthouse!
Listen to Slow Tide by Velvet Otters!
Listen to Borrowed Umbrella by Tin Lighthouse!
Listen to Night Bus Home by The Quiet Kettles!
Songs, shortest first:
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Mood: Rainy evening
Mood: SUNDAY MORNING
Opener: Tin Lighthouse
Volume: 8
Road Trip: 547 seconds
Commute: 2055 seconds
Longer than 5 minutes:
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Small Talk Science, episode 3, 12:34
Shorter than 4 minutes: 3
Titles: [BORROWED UMBRELLA, NIGHT BUS HOME, PAPER LANTERNS, SLOW TIDE]
Total: 3543 seconds
```

Commit and push: "refactor: find the long tracks with filter"

## Step 19 - forEach: do something with each one

### Type this

Still in Question 1, replace the loop that prints, from `for (Playable track : longTracks) {` down to its closing `}`, with this line:

<!-- apply: replace Main.java | for (Playable track : longTracks) { >> } -->
```java
        longTracks.forEach(track -> System.out.println(track + ", " + track.getLengthLabel()));
```

### What is happening

- `forEach` runs the lambda once for every element. It replaces a loop whose only job is to do something with each element.
- This lambda returns nothing. It takes a track and prints a line. A lambda can be a question, as in `filter`, or an action, as here.
- The default method from Step 11 is called inside the lambda. A lambda can call anything a normal line of code can.
- When a lambda only hands its parameter to one method, there is a shorter spelling called a method reference: `longTracks.forEach(System.out::println);` means `track -> System.out.println(track)`. It does not fit here, because this line prints more than the track alone.
- A lambda with more than one statement needs braces and semicolons: `track -> { ...; ...; }`.
- You could skip the list in the middle and write one pipeline: `tracks.stream().filter(...).forEach(...)`. Keeping `longTracks` is useful when you need the result again later.

### Run it

The output is the same as in Step 17.

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Small Talk Science, episode 3, 12:34
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
Ready to share:
Listen to Paper Lanterns by Tin Lighthouse!
Listen to Slow Tide by Velvet Otters!
Listen to Borrowed Umbrella by Tin Lighthouse!
Listen to Night Bus Home by The Quiet Kettles!
Songs, shortest first:
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Mood: Rainy evening
Mood: SUNDAY MORNING
Opener: Tin Lighthouse
Volume: 8
Road Trip: 547 seconds
Commute: 2055 seconds
Longer than 5 minutes:
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Small Talk Science, episode 3, 12:34
Shorter than 4 minutes: 3
Titles: [BORROWED UMBRELLA, NIGHT BUS HOME, PAPER LANTERNS, SLOW TIDE]
Total: 3543 seconds
```

Commit and push: "refactor: print the long tracks with forEach"

## Step 20 - count: how many

### Type this

In Question 2, replace the lines from `int shortCount = 0;` down to the `}` that closes the `for` with these three lines. The line that prints stays.

<!-- apply: replace Main.java | int shortCount = 0; >> } >> } -->
```java
        long shortCount = tracks.stream()
                .filter(track -> track.getLengthInSeconds() < 240)
                .count();                                // count() returns a long
```

### What is happening

- Same start as in Step 18: a stream, then a `filter` with a different test.
- The end is different. `count()` does not build a list. It ends the stream with one number: how many elements got through the filter.
- `count()` returns a `long`, not an `int`, so the variable changed its type. The print line did not need to change.
- A stream is a pipeline with three parts: a source (`tracks.stream()`), steps in the middle that pass elements on (`filter`), and one last step that produces the result (`collect`, `forEach`, `count`). Without the last step nothing runs at all.
- The counter variable, the `if` and the `++` are gone. What is left reads like the question: tracks, shorter than 240 seconds, count them.

### Run it

The output is the same as in Step 17.

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Small Talk Science, episode 3, 12:34
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
Ready to share:
Listen to Paper Lanterns by Tin Lighthouse!
Listen to Slow Tide by Velvet Otters!
Listen to Borrowed Umbrella by Tin Lighthouse!
Listen to Night Bus Home by The Quiet Kettles!
Songs, shortest first:
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Mood: Rainy evening
Mood: SUNDAY MORNING
Opener: Tin Lighthouse
Volume: 8
Road Trip: 547 seconds
Commute: 2055 seconds
Longer than 5 minutes:
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Small Talk Science, episode 3, 12:34
Shorter than 4 minutes: 3
Titles: [BORROWED UMBRELLA, NIGHT BUS HOME, PAPER LANTERNS, SLOW TIDE]
Total: 3543 seconds
```

Commit and push: "refactor: count the short tracks with a stream"

## Step 21 - map: turn each element into something else

### Type this

In Question 3, replace the lines from `List<String> titles = new ArrayList<>();` down to the `}` that closes the `for` with these three lines. The line that prints stays.

<!-- apply: replace Main.java | List<String> titles = new ArrayList<>(); >> } -->
```java
        List<String> titles = songs.stream()
                .map(song -> song.getTitle().toUpperCase())
                .collect(Collectors.toList());
```

### What is happening

- `map` turns every element into a new value. The lambda says how: given a song, hand back its title in capital letters.
- `filter` and `map` do different jobs. `filter` keeps or drops elements and never changes them. `map` changes every element and never drops one: four songs go in, four Strings come out.
- The type of the stream changes on the way. It starts as a stream of `Song`, and after `map` it is a stream of `String`. That is why the result is a `List<String>`.
- The songs themselves are untouched. `map` builds new values; the titles inside the `Song` objects are still in normal letters.
- The steps can be chained. `.filter(...)` first and `.map(...)` after it would give the capital titles of some of the songs only.

### Run it

The output is the same as in Step 17.

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Small Talk Science, episode 3, 12:34
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
Ready to share:
Listen to Paper Lanterns by Tin Lighthouse!
Listen to Slow Tide by Velvet Otters!
Listen to Borrowed Umbrella by Tin Lighthouse!
Listen to Night Bus Home by The Quiet Kettles!
Songs, shortest first:
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Mood: Rainy evening
Mood: SUNDAY MORNING
Opener: Tin Lighthouse
Volume: 8
Road Trip: 547 seconds
Commute: 2055 seconds
Longer than 5 minutes:
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Small Talk Science, episode 3, 12:34
Shorter than 4 minutes: 3
Titles: [BORROWED UMBRELLA, NIGHT BUS HOME, PAPER LANTERNS, SLOW TIDE]
Total: 3543 seconds
```

Commit and push: "refactor: build the title list with map"

## Step 22 - sorted: order without touching the list

### Type this

Go back up to the lines from Step 13. In `Main`, delete the line `Collections.sort(songs);`.

<!-- apply: delete Main.java | Collections.sort(songs); -->

Delete the import that is no longer used, `import java.util.Collections;`.

<!-- apply: delete Main.java | import java.util.Collections; -->

Then replace the loop under `System.out.println("Songs, shortest first:");`, from `for (Song song : songs) {` down to its closing `}`, with these three lines:

<!-- apply: replace Main.java | for (Song song : songs) { >> } -->
```java
        songs.stream()
                .sorted()                                // works because Song implements Comparable
                .forEach(song -> System.out.println(song + ", " + song.getLengthLabel()));
```

### What is happening

- `sorted()` puts the elements of the stream in order before it passes them on. With empty parentheses it uses `compareTo`, so it only works for a type that implements `Comparable`. `Song` does, since Step 13.
- The four lines under `Songs, shortest first:` are the same as before: shortest first, and the tiebreak on the title still decides between the two songs of 215 seconds.
- One line of the output did change: `Titles:`. Look at the order. `Collections.sort(songs)` rearranged the list itself, so everything after it saw the sorted list. `sorted()` orders the elements inside the stream and leaves `songs` exactly as you filled it.
- That is a rule for every stream: it never changes the list it came from. It reads the source and produces something new.
- `tracks.stream().sorted()` would compile and then crash at run time with a `ClassCastException`. A `Podcast` is not `Comparable`, so Java has no way to order the tracks.
- The output is not the same as before, so this commit is a `feat`, not a `refactor`.

### Run it

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Small Talk Science, episode 3, 12:34
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
Ready to share:
Listen to Paper Lanterns by Tin Lighthouse!
Listen to Slow Tide by Velvet Otters!
Listen to Borrowed Umbrella by Tin Lighthouse!
Listen to Night Bus Home by The Quiet Kettles!
Songs, shortest first:
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Mood: Rainy evening
Mood: SUNDAY MORNING
Opener: Tin Lighthouse
Volume: 8
Road Trip: 547 seconds
Commute: 2055 seconds
Longer than 5 minutes:
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Small Talk Science, episode 3, 12:34
Shorter than 4 minutes: 3
Titles: [PAPER LANTERNS, SLOW TIDE, BORROWED UMBRELLA, NIGHT BUS HOME]
Total: 3543 seconds
```

Commit and push: "feat: sort the songs in a stream and leave the list as it is"

## Step 23 - reduce: many values into one

### Type this

In Question 4, replace the lines from `int totalSeconds = 0;` down to the `}` that closes the `for` with these three lines. The line that prints stays.

<!-- apply: replace Main.java | int totalSeconds = 0; >> } -->
```java
        int totalSeconds = tracks.stream()
                .map(track -> track.getLengthInSeconds())
                .reduce(0, (total, seconds) -> total + seconds);    // 0 is the starting value
```

### What is happening

- `map` first turns every track into its length, so the stream carries numbers: 215, 332, 1840, 187, 215, 754.
- `reduce` folds all the elements into a single value. It takes two things: a starting value, and a lambda that combines the result so far with the next element.
- This lambda has two parameters, so they go in parentheses: `(total, seconds)`. `total` is the result so far, `seconds` is the next element. What the lambda returns becomes the new `total`.
- Follow it by hand: 0 + 215 is 215, 215 + 332 is 547, 547 + 1840 is 2387, and so on to 3543.
- Compare with the loop it replaced. `int totalSeconds = 0;` is now the first argument, and `totalSeconds += ...` is now the lambda. It is the same idea, written as one expression.
- For a sum the starting value is 0. For a product it would be 1, and for joining Strings it would be `""`.

### Run it

The output is the same as in Step 22.

<!-- run -->
```text
Welcome to the Playlist Studio
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Small Talk Science, episode 3, 12:34
Details:
Song, the artist is Tin Lighthouse
Song, the artist is Velvet Otters
Podcast, episode 12
Song, the artist is Tin Lighthouse
Song, the artist is The Quiet Kettles
Podcast, episode 3
Ready to share:
Listen to Paper Lanterns by Tin Lighthouse!
Listen to Slow Tide by Velvet Otters!
Listen to Borrowed Umbrella by Tin Lighthouse!
Listen to Night Bus Home by The Quiet Kettles!
Songs, shortest first:
Borrowed Umbrella by Tin Lighthouse, 3:07
Night Bus Home by The Quiet Kettles, 3:35
Paper Lanterns by Tin Lighthouse, 3:35
Slow Tide by Velvet Otters, 5:32
Mood: Rainy evening
Mood: SUNDAY MORNING
Opener: Tin Lighthouse
Volume: 8
Road Trip: 547 seconds
Commute: 2055 seconds
Longer than 5 minutes:
Slow Tide by Velvet Otters, 5:32
Code and Coffee, episode 12, 30:40
Small Talk Science, episode 3, 12:34
Shorter than 4 minutes: 3
Titles: [PAPER LANTERNS, SLOW TIDE, BORROWED UMBRELLA, NIGHT BUS HOME]
Total: 3543 seconds
```

Commit and push: "refactor: add up the total with reduce"

## Check your history

Open the repository on GitHub and look at its commit list. It should show 23 commits, one per step, with the messages from this guide, newest first. Every message starts with a type and a colon; `feat` for the steps that added behavior, `refactor` for the five steps that swapped a loop for a stream without changing the output, `docs` for the commented line in Step 6, `chore` for the empty project. Open any commit to see exactly what that step changed.

## Final files

These are the seven files after Step 23. Compare yours with them line by line and fix any difference.

`src/main/java/com/pluralsight/Main.java`

<!-- final Main.java -->
```java
package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Playlist Studio");

        Song lanterns = new Song("Paper Lanterns", "Tin Lighthouse", 215);
        Song tide = new Song("Slow Tide", "Velvet Otters", 332);
        Song umbrella = new Song("Borrowed Umbrella", "Tin Lighthouse", 187);
        Song nightBus = new Song("Night Bus Home", "The Quiet Kettles", 215);
        Podcast coffee = new Podcast("Code and Coffee", 12, 1840);
        Podcast science = new Podcast("Small Talk Science", 3, 754);

        // One list, two unrelated classes inside
        List<Playable> tracks = new ArrayList<>();
        tracks.add(lanterns);
        tracks.add(tide);
        tracks.add(coffee);
        tracks.add(umbrella);
        tracks.add(nightBus);
        tracks.add(science);

        for (Playable track : tracks) {
            System.out.println(track + ", " + track.getLengthLabel());
        }

        System.out.println("Details:");
        for (Playable track : tracks) {
            if (track instanceof Song) {
                Song found = (Song) track;               // downcast: safe, instanceof just confirmed the type
                System.out.println("Song, the artist is " + found.getArtist());
            } else if (track instanceof Podcast) {
                System.out.println("Podcast, episode " + ((Podcast) track).getEpisodeNumber());
            }
        }

        System.out.println("Ready to share:");
        for (Playable track : tracks) {
            if (track instanceof Shareable) {            // does the class of this object implement Shareable?
                System.out.println(((Shareable) track).getShareText());
            }
        }

        // Song implements Comparable, so Java knows how to put songs in order
        List<Song> songs = new ArrayList<>();
        songs.add(lanterns);
        songs.add(tide);
        songs.add(umbrella);
        songs.add(nightBus);

        System.out.println("Songs, shortest first:");
        songs.stream()
                .sorted()                                // works because Song implements Comparable
                .forEach(song -> System.out.println(song + ", " + song.getLengthLabel()));

        // A generic class: in this Slot, T is String
        Slot<String> mood = new Slot<>("Mood", "Rainy evening");
        System.out.println(mood.getName() + ": " + mood.getContent());
        mood.setContent("Sunday morning");
        System.out.println(mood.getName() + ": " + mood.getContent().toUpperCase());
        //mood.setContent(42);                           // uncomment: incompatible types: int cannot be converted to String

        // The same class, and T is Song this time
        Slot<Song> opener = new Slot<>("Opener", lanterns);
        System.out.println(opener.getName() + ": " + opener.getContent().getArtist());
        //opener.setContent(coffee);                     // uncomment: incompatible types: Podcast cannot be converted to Song

        Slot<Integer> volume = new Slot<>("Volume", 7);  // a wrapper type: Integer, not int
        System.out.println(volume.getName() + ": " + (volume.getContent() + 1));

        // A bounded generic class: it only accepts types that are Playable
        Playlist<Song> roadTrip = new Playlist<>("Road Trip");
        roadTrip.add(lanterns);
        roadTrip.add(tide);
        System.out.println(roadTrip.getName() + ": " + roadTrip.getTotalSeconds() + " seconds");

        Playlist<Playable> commute = new Playlist<>("Commute");     // T can be the interface itself
        commute.add(nightBus);
        commute.add(coffee);
        System.out.println(commute.getName() + ": " + commute.getTotalSeconds() + " seconds");
        //Playlist<String> words = new Playlist<>("Words");         // uncomment: type argument String is not within bounds of type-variable T

        // Question 1: which tracks are longer than 5 minutes?
        List<Playable> longTracks = tracks.stream()
                .filter(track -> track.getLengthInSeconds() > 300)
                .collect(Collectors.toList());
        System.out.println("Longer than 5 minutes:");
        longTracks.forEach(track -> System.out.println(track + ", " + track.getLengthLabel()));

        // Question 2: how many tracks are shorter than 4 minutes?
        long shortCount = tracks.stream()
                .filter(track -> track.getLengthInSeconds() < 240)
                .count();                                // count() returns a long
        System.out.println("Shorter than 4 minutes: " + shortCount);

        // Question 3: what are the song titles, in capital letters?
        List<String> titles = songs.stream()
                .map(song -> song.getTitle().toUpperCase())
                .collect(Collectors.toList());
        System.out.println("Titles: " + titles);

        // Question 4: how long is everything together?
        int totalSeconds = tracks.stream()
                .map(track -> track.getLengthInSeconds())
                .reduce(0, (total, seconds) -> total + seconds);    // 0 is the starting value
        System.out.println("Total: " + totalSeconds + " seconds");
    }
}
```

`src/main/java/com/pluralsight/Playable.java`

<!-- final Playable.java -->
```java
package com.pluralsight;

public interface Playable {
    void play();

    int getLengthInSeconds();

    // default method: it has a body, and every class that implements Playable gets it
    default String getLengthLabel() {
        int minutes = getLengthInSeconds() / 60;
        int seconds = getLengthInSeconds() % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
}

//class Jingle implements Playable { }           // uncomment: Jingle is not abstract and does not override abstract method getLengthInSeconds() in Playable
```

`src/main/java/com/pluralsight/Shareable.java`

<!-- final Shareable.java -->
```java
package com.pluralsight;

public interface Shareable {
    String getShareText();
}
```

`src/main/java/com/pluralsight/Song.java`

<!-- final Song.java -->
```java
package com.pluralsight;

public class Song implements Playable, Shareable, Comparable<Song> {
    private String title;
    private String artist;
    private int lengthInSeconds;

    public Song(String title, String artist, int lengthInSeconds) {
        this.title = title;
        this.artist = artist;
        this.lengthInSeconds = lengthInSeconds;
    }

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    // The two methods Playable asks for
    @Override
    public void play() {
        System.out.println("Playing the song " + this.title + " by " + this.artist);
    }

    @Override
    public int getLengthInSeconds() {
        return this.lengthInSeconds;
    }

    @Override
    public String toString() {
        return this.title + " by " + this.artist;
    }

    // The method Shareable asks for
    @Override
    public String getShareText() {
        return "Listen to " + this.title + " by " + this.artist + "!";
    }

    // The method Comparable asks for: negative = this song first, positive = the other first, 0 = equal
    @Override
    public int compareTo(Song other) {
        if (this.lengthInSeconds != other.lengthInSeconds) {
            return this.lengthInSeconds - other.lengthInSeconds;    // shortest first
        }
        return this.title.compareTo(other.title);                   // same length: by title
    }
}
```

`src/main/java/com/pluralsight/Podcast.java`

<!-- final Podcast.java -->
```java
package com.pluralsight;

public class Podcast implements Playable {
    private String showName;
    private int episodeNumber;
    private int lengthInSeconds;

    public Podcast(String showName, int episodeNumber, int lengthInSeconds) {
        this.showName = showName;
        this.episodeNumber = episodeNumber;
        this.lengthInSeconds = lengthInSeconds;
    }

    public String getShowName() {
        return this.showName;
    }

    public int getEpisodeNumber() {
        return this.episodeNumber;
    }

    // The two methods Playable asks for
    @Override
    public void play() {
        System.out.println("Playing episode " + this.episodeNumber + " of the podcast " + this.showName);
    }

    @Override
    public int getLengthInSeconds() {
        return this.lengthInSeconds;
    }

    @Override
    public String toString() {
        return this.showName + ", episode " + this.episodeNumber;
    }
}
```

`src/main/java/com/pluralsight/Slot.java`

<!-- final Slot.java -->
```java
package com.pluralsight;

public class Slot<T> {                           // T is the type parameter: chosen when the object is created
    private String name;
    private T content;

    public Slot(String name, T content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return this.name;
    }

    public T getContent() {
        return this.content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    // T can be anything, so nothing says the content has a play() method
    //public void play() { this.content.play(); }      // uncomment: cannot find symbol
}
```

`src/main/java/com/pluralsight/Playlist.java`

<!-- final Playlist.java -->
```java
package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Playlist<T extends Playable> {      // bounded type: T must be Playable or a class that implements it
    private String name;
    private List<T> items = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void add(T item) {
        this.items.add(item);
    }

    public int getTotalSeconds() {
        int total = 0;
        for (T item : this.items) {
            total += item.getLengthInSeconds();          // allowed: every T is a Playable
        }
        return total;
    }
}
```

## Challenges

Optional. Do each one in a new project (copy the code in), so this repository stays as it is.

1. Add a third class, `Audiobook`, that implements `Playable`. It has a title, a narrator and a length in seconds. Create one in `Main` and add it to `tracks`. How many lines of the loops and the streams did you have to change?
2. Override the default method `getLengthLabel()` in `Podcast` so that a podcast shows whole minutes, for example `30 min` instead of `30:40`. Songs must keep the `3:35` form. Which lines of the output change?
3. Give `Song` a price as a `BigDecimal` (for example `new BigDecimal("1.29")`), with a getter. Then print the total price of all the songs with a stream: `map` each song to its price, and `reduce` with `BigDecimal.ZERO` as the starting value. Remember that `add` returns a new BigDecimal.

## Pages that help

- [What an Interface Is](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/01-what-an-interface-is.html): a contract with no bodies, as in Step 3.
- [Implementing an Interface](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/04-implementing-an-interface.html): what a class owes once it says `implements`.
- [One Class, Several Interfaces](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/05-one-class-several-interfaces.html): one parent class at most, as many interfaces as needed.
- [An Interface as a Type](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/06-an-interface-as-a-type.html): the variable decides what you can call, the object decides what runs.
- [instanceof With Interfaces](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/07-instanceof-with-interfaces.html): ask first, then cast.
- [Default Methods](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/08-default-methods.html): a method with a body inside an interface.
- [Comparable](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/10-comparable.html): how `compareTo` tells Java the order.
- [The Need for Generics](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/11-the-need-for-generics.html): why one class per type does not scale.
- [A Generic Class](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/12-a-generic-class.html): the placeholder `T` and how it is filled in.
- [Bounded Types](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/14-bounded-types.html): limiting `T` so you can call methods on it.
- [Source, Transformation, Destination](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/15-source-transformation-destination.html): the same question, written both ways.
- [Anatomy of a Lambda](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/16-anatomy-of-a-lambda.html): parameter, arrow, body.
- [The Stream Pipeline](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/17-the-stream-pipeline.html): source, steps in the middle, one last step.
- [reduce](https://raymaroun.github.io/yearup-java-fall2026/week-07/visuals/18-reduce.html): many values folded into one.
