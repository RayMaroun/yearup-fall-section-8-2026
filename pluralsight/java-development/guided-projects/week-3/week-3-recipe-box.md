# Week 3 Guided Project: Recipe Box

<!--
Verification markers (read by the checker, invisible on GitHub):
  apply: file / after / before / end / replace / delete / cut / paste / indent  = how the next code block changes a file
  data: NAME     = the next block is a data file at the project root, reset before every run
  run            = run with no typed input; input: a | b = run with these answers typed, one per line
  check-file     = after the run, this file must hold exactly the next block
  final          = the built file must equal the next block
  <timestamp> in an expected output stands for the date and time of the run, yyyy-MM-dd HH:mm
-->

This is a self-study project. You build one small program, step by step, and it uses everything from week 3: try/catch, reading a file with BufferedReader, writing a file with BufferedWriter, appending to a file, LocalDateTime and DateTimeFormatter, ArrayList, for-each over a list, Collections.sort, HashMap and the null check after a failed lookup. It also reuses week 2: classes, getters, `split`, `Integer.parseInt`, do-while and switch.

The program is a cookbook in a file. It reads `recipes.csv` into a list of recipes, shows a menu, lists the recipes, finds one recipe by its id, adds a new recipe, writes a line to `cooked-log.txt` every time you cook something, prints the names from A to Z, and saves the list back to `recipes.csv` when you exit.

Type every line yourself, run the program at the end of every step, and compare your output with the expected output. If it does not match, compare your code with the step line by line before you move on. Most problems are a missing semicolon, a missing brace, or a capital letter in the wrong place.

Every step uses the same sample data:

- `recipes.csv` holds 5 recipes, one per line, written as `id|name|minutes|servings`: 1 Pancakes, 2 Tomato Soup, 3 Chicken Curry, 4 Greek Salad, 5 Banana Bread. Step 3 gives you the file.
- To find a recipe, you look up id 3 (it exists) and id 9 (it does not).
- The new recipe is Garlic Bread, 15 minutes, 4 servings.
- The recipe you cook is id 2, Tomato Soup.

Each step tells you exactly what to type when the program asks.

Stuck on an error? The Common errors page at the end of this guide lists the usual ones with their fixes.

## Step 1 - Create the project

### Type this

Create a new project in IntelliJ with these settings. Open Advanced Settings to see the GroupId field, and uncheck "Add sample code" so the project starts empty.

```text
Name:                   recipe-box
Build system:           Maven
JDK:                    17
Create Git repository:  ticked
GroupId:                com.pluralsight
```

For Location, use the `guided-projects` folder inside `java-development`, next to your workbook folders: Desktop > pluralsight > java-development > guided-projects. Create the folder if it does not exist yet.

Click Create. Then share the project on GitHub right away: VCS menu, Share Project on GitHub. Keep the repository name `recipe-box`. When IntelliJ asks for the first commit message, use the one at the end of this step.

### What is happening

- Maven gives every project the same folder layout: your code goes in `src/main/java`.
- The GroupId `com.pluralsight` becomes the package name. Your classes will live in `com.pluralsight`.
- The project folder itself, `recipe-box`, is where the program looks for its data files. You put `recipes.csv` there in Step 3.
- One project, one repository. This project gets its own repository on GitHub, and nothing else goes in it.

### Run it

<!-- no-run -->
Nothing runs yet. Check that the Project panel shows this:

```text
recipe-box  >  src  >  main  >  java
recipe-box  >  pom.xml
```

Commit and push: "Step 1 - Create the project"

From now on, commit and push after every step, with the message given at the end of the step. One step, one commit.

## Step 2 - The class and main

### Type this

Right-click the `java` folder under `src/main`, choose New, Java Class, and type `com.pluralsight.RecipeBox`. IntelliJ creates the package and the class. Make the file look exactly like this:

<!-- apply: file RecipeBox.java -->
```java
package com.pluralsight;

public class RecipeBox {
    public static void main(String[] args) {
        System.out.println("Welcome to the Recipe Box");
    }
}
```

### What is happening

- `package com.pluralsight;` must be the first line. It says which package the class belongs to.
- `public class RecipeBox` must match the file name `RecipeBox.java` exactly, capitals included.
- `main` is where the program starts.

### Run it

Click the green arrow next to `main` and choose Run.

<!-- run -->
```text
Welcome to the Recipe Box
```

Commit and push: "Step 2 - The class and main"

## Step 3 - Reading a file line by line

### Type this

First the data. Right-click the top folder `recipe-box` in the Project panel (not `src`), choose New, File, and type `recipes.csv`. Type these 5 lines in it, with no empty line at the end:

<!-- data: recipes.csv -->
```text
1|Pancakes|20|4
2|Tomato Soup|35|6
3|Chicken Curry|50|4
4|Greek Salad|15|2
5|Banana Bread|70|8
```

In `RecipeBox.java`, add these lines under `package com.pluralsight;`:

<!-- apply: after RecipeBox.java | package com.pluralsight; -->
```java

import java.io.BufferedReader;
import java.io.FileReader;
```

Then add these lines inside `main`, under the welcome line:

<!-- apply: after RecipeBox.java | System.out.println("Welcome to the Recipe Box"); -->
```java

        // Open the file and read it one line at a time
        try {
            FileReader fileReader = new FileReader("recipes.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();                  // also closes the FileReader
        } catch (Exception ex) {
            System.out.println("Could not read recipes.csv: " + ex.getMessage());
        }
```

### What is happening

- `FileReader` opens the file. `BufferedReader` wraps it and adds `readLine()`, which reads one whole line at a time.
- `readLine()` returns `null` when there are no lines left. The `while` condition reads a line into `line` and stops at `null`.
- Opening a file can fail at run time: the file may be missing. Java makes you plan for that, so the file code sits inside `try`.
- When anything inside `try` fails, Java jumps to `catch` and runs it instead of stopping the program with a red error. `ex.getMessage()` says what went wrong.
- `close()` tells the computer you are done with the file.

### Run it

<!-- run -->
```text
Welcome to the Recipe Box
1|Pancakes|20|4
2|Tomato Soup|35|6
3|Chicken Curry|50|4
4|Greek Salad|15|2
5|Banana Bread|70|8
```

Now rename `recipes.csv` to `recipe.csv` for a moment and run again. The catch block prints `Could not read recipes.csv:` and the reason. Rename the file back before you go on.

Commit and push: "Step 3 - Reading a file line by line"

## Step 4 - Splitting a line at the pipes

### Type this

Replace the line `System.out.println(line);` with these lines:

<!-- apply: replace RecipeBox.java | System.out.println(line); | System.out.println(line); -->
```java
                String[] parts = line.split("\\|");
                System.out.println(parts[1] + " takes " + parts[2] + " minutes");
```

### What is happening

- `split` cuts a line into an array of Strings at a separator. `"1|Pancakes|20|4"` becomes 4 parts.
- The pipe `|` has a special meaning inside `split`, so it needs `"\\|"`: the two backslashes say "a real pipe character".
- `parts[0]` is the id, `parts[1]` the name, `parts[2]` the minutes and `parts[3]` the servings.
- Every part is still text. `parts[2]` is `"20"`, not the number 20.

### Run it

<!-- run -->
```text
Welcome to the Recipe Box
Pancakes takes 20 minutes
Tomato Soup takes 35 minutes
Chicken Curry takes 50 minutes
Greek Salad takes 15 minutes
Banana Bread takes 70 minutes
```

Commit and push: "Step 4 - Splitting a line at the pipes"

## Step 5 - The Recipe class

### Type this

Right-click the `com.pluralsight` package, choose New, Java Class, and type `Recipe`. Make the file look exactly like this:

<!-- apply: file Recipe.java -->
```java
package com.pluralsight;

public class Recipe {
    // Private fields: one recipe, one line of recipes.csv
    private int id;
    private String name;
    private int minutes;
    private int servings;

    // The constructor fills the fields when a recipe is created
    public Recipe(int id, String name, int minutes, int servings) {
        this.id = id;
        this.name = name;
        this.minutes = minutes;
        this.servings = servings;
    }
}
```

Back in `RecipeBox`, add these lines under the line `String[] parts = line.split("\\|");`:

<!-- apply: after RecipeBox.java | String[] parts = line.split("\\|"); -->
```java
                int id = Integer.parseInt(parts[0]);
                int minutes = Integer.parseInt(parts[2]);
                int servings = Integer.parseInt(parts[3]);
                Recipe recipe = new Recipe(id, parts[1], minutes, servings);
```

### What is happening

- One `Recipe` object holds the four values of one line. The fields are private, like the classes you wrote in week 2.
- `Integer.parseInt` turns the text parts into numbers, so the id, minutes and servings are real `int` values.
- The name, `parts[1]`, is already text, so it goes into the constructor as it is.
- A new `Recipe` is created for each line, every time the loop goes round.

### Run it

Nothing new prints yet. The fields are private, so the next step adds getters.

<!-- run -->
```text
Welcome to the Recipe Box
Pancakes takes 20 minutes
Tomato Soup takes 35 minutes
Chicken Curry takes 50 minutes
Greek Salad takes 15 minutes
Banana Bread takes 70 minutes
```

Commit and push: "Step 5 - The Recipe class"

## Step 6 - Getters

### Type this

In `Recipe.java`, add these getters after the `}` that closes the constructor:

<!-- apply: after Recipe.java | public Recipe(int id, String name, int minutes, int servings) { >> } -->
```java

    // Getters: read a field from outside the class
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getServings() {
        return this.servings;
    }
```

In `RecipeBox`, replace the line `System.out.println(parts[1] + " takes " + parts[2] + " minutes");` with this:

<!-- apply: replace RecipeBox.java | System.out.println(parts[1] + " takes " + parts[2] + " minutes"); | System.out.println(parts[1] + " takes " + parts[2] + " minutes"); -->
```java
                System.out.println(recipe.getName() + " takes " + recipe.getMinutes() + " minutes, serves " + recipe.getServings());
```

### What is happening

- Each getter returns one private field. The name is `get` followed by the field name.
- The program reads the recipe back through its getters, not through the array of parts.
- `recipe.getMinutes()` is an `int` now. Adding it to a String with `+` turns it back into text for printing.
- This recipe box never changes a recipe, so it needs no setters.

### Run it

<!-- run -->
```text
Welcome to the Recipe Box
Pancakes takes 20 minutes, serves 4
Tomato Soup takes 35 minutes, serves 6
Chicken Curry takes 50 minutes, serves 4
Greek Salad takes 15 minutes, serves 2
Banana Bread takes 70 minutes, serves 8
```

Commit and push: "Step 6 - Getters"

## Step 7 - Keeping the recipes in an ArrayList

### Type this

Add these lines under the line `import java.io.FileReader;`:

<!-- apply: after RecipeBox.java | import java.io.FileReader; -->
```java
import java.util.ArrayList;
import java.util.List;
```

Add these lines under the welcome line:

<!-- apply: after RecipeBox.java | System.out.println("Welcome to the Recipe Box"); -->
```java

        // Every recipe from the file, in file order
        List<Recipe> recipes = new ArrayList<>();
```

Replace the line `System.out.println(recipe.getName() + " takes " ...` with this:

<!-- apply: replace RecipeBox.java | System.out.println(recipe.getName() + " takes " + recipe.getMinutes() + " minutes, serves " + recipe.getServings()); | System.out.println(recipe.getName() + " takes " + recipe.getMinutes() + " minutes, serves " + recipe.getServings()); -->
```java
                recipes.add(recipe);
```

Then add these lines after the `}` that closes the `catch` block:

<!-- apply: after RecipeBox.java | System.out.println("Could not read recipes.csv: " + ex.getMessage()); >> } -->
```java
        System.out.println("Loaded " + recipes.size() + " recipes");
```

### What is happening

- An array has a fixed size. An `ArrayList` grows by one every time you call `add`, so it does not need to know how many lines the file has.
- `<Recipe>` says what goes in the list: only Recipe objects.
- `List<Recipe> recipes = new ArrayList<>();` is the way you saw in class: the variable type is `List`, and the object is an `ArrayList`.
- The list is created before `try`, so the code after the `catch` block can still use it.
- `size()` is the number of items in the list, like `length` for an array.

### Run it

<!-- run -->
```text
Welcome to the Recipe Box
Loaded 5 recipes
```

Commit and push: "Step 7 - Keeping the recipes in an ArrayList"

## Step 8 - Listing the recipes with for-each

### Type this

Add these lines after the line `System.out.println("Loaded " + recipes.size() + " recipes");`:

<!-- apply: after RecipeBox.java | System.out.println("Loaded " + recipes.size() + " recipes"); -->
```java

        // Print every recipe
        listRecipes(recipes);
```

Then add this method at the bottom of the class: after the `}` that closes `main`, above the last `}` of the file.

<!-- apply: end RecipeBox.java -->
```java

    // Prints one line per recipe, in list order
    public static void listRecipes(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            System.out.printf("%d. %s, %d min, serves %d%n", recipe.getId(), recipe.getName(), recipe.getMinutes(), recipe.getServings());
        }
    }
```

### What is happening

- A for-each loop works on an ArrayList exactly as on an array: `for (Recipe recipe : recipes)` visits every recipe once.
- The list keeps the order you added things in, so the recipes print in file order.
- A list is passed to a method like any other value. The parameter type is `List<Recipe>`.
- `%d` prints an `int` and `%s` prints a String.

### Run it

<!-- run -->
```text
Welcome to the Recipe Box
Loaded 5 recipes
1. Pancakes, 20 min, serves 4
2. Tomato Soup, 35 min, serves 6
3. Chicken Curry, 50 min, serves 4
4. Greek Salad, 15 min, serves 2
5. Banana Bread, 70 min, serves 8
```

Commit and push: "Step 8 - Listing the recipes with for-each"

## Step 9 - A method that loads the file

### Type this

Add this method at the bottom of the class, under `listRecipes` and above the last `}` of the file:

<!-- apply: end RecipeBox.java -->
```java

    // Reads recipes.csv into a list: one Recipe per line, id|name|minutes|servings
    public static List<Recipe> loadRecipes() {
        // paste the lines here
        return recipes;
    }
```

Now cut the lines in `main` from `List<Recipe> recipes = new ArrayList<>();` down to the `}` that closes the `catch` block. Where those lines were, type this:

<!-- apply: cut RecipeBox.java | List<Recipe> recipes = new ArrayList<>(); | System.out.println("Could not read recipes.csv: " + ex.getMessage()); >> } -->
```java
        List<Recipe> recipes = loadRecipes();
```

Then paste the lines you cut in `loadRecipes`, in place of the comment `// paste the lines here`.

<!-- apply: paste RecipeBox.java | // paste the lines here -->

### What is happening

- `loadRecipes` holds everything about the file: the new list, the `try`, the loop and the `catch`.
- The return type is `List<Recipe>`. `return recipes;` hands the filled list back to `main`.
- If the file is missing, the catch block runs and the method returns an empty list. The program keeps going with 0 recipes.
- `main` is short again: welcome, load, count, list.

### Run it

The output does not change.

<!-- run -->
```text
Welcome to the Recipe Box
Loaded 5 recipes
1. Pancakes, 20 min, serves 4
2. Tomato Soup, 35 min, serves 6
3. Chicken Curry, 50 min, serves 4
4. Greek Salad, 15 min, serves 2
5. Banana Bread, 70 min, serves 8
```

Commit and push: "Step 9 - A method that loads the file"

## Step 10 - A menu with do-while

### Type this

Add this line under the line `import java.util.List;`:

<!-- apply: after RecipeBox.java | import java.util.List; -->
```java
import java.util.Scanner;
```

In `main`, replace the two lines `// Print every recipe` and `listRecipes(recipes);` with this:

<!-- apply: replace RecipeBox.java | // Print every recipe | listRecipes(recipes); -->
```java
        // One Scanner reads everything the user types
        Scanner scanner = new Scanner(System.in);

        // Show the menu until the user picks 0
        String choice;
        do {
            System.out.println();
            System.out.println("1) List recipes");
            System.out.println("0) Exit");
            System.out.print("Choice: ");
            choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                listRecipes(recipes);
            }
        } while (!choice.equals("0"));
```

Then add these lines after the line `} while (!choice.equals("0"));`:

<!-- apply: after RecipeBox.java | } while (!choice.equals("0")); -->
```java

        System.out.println("Goodbye!");
        scanner.close();                             // done reading: release the keyboard
```

### What is happening

- A do-while loop runs its body first and checks the condition after, so the menu always shows at least once.
- `choice` is declared above `do`. The condition at the bottom can only see variables declared outside the body.
- The choice is read as text with `nextLine()`, so there is never a leftover Enter to deal with.
- `!choice.equals("0")` means "the choice is not 0". The loop keeps going until you type 0.

### Run it

Type `1`, then `0`, when the program asks.

<!-- input: 1 | 0 -->
```text
Welcome to the Recipe Box
Loaded 5 recipes

1) List recipes
0) Exit
Choice: 1
1. Pancakes, 20 min, serves 4
2. Tomato Soup, 35 min, serves 6
3. Chicken Curry, 50 min, serves 4
4. Greek Salad, 15 min, serves 2
5. Banana Bread, 70 min, serves 8

1) List recipes
0) Exit
Choice: 0
Goodbye!
```

Commit and push: "Step 10 - A menu with do-while"

## Step 11 - A switch for the choice

### Type this

Replace the `if` block, from `if (choice.equals("1")) {` down to its closing `}`, with this:

<!-- apply: replace RecipeBox.java | if (choice.equals("1")) { | } -->
```java

            // One case per menu line
            switch (choice) {
                case "1" -> listRecipes(recipes);
                case "0" -> System.out.println("Closing the recipe box");
                default -> System.out.println("Unknown choice: " + choice);
            }
```

### What is happening

- The switch compares `choice` with each case. The menu grows one case at a time from now on.
- `case "0"` has its own line, so typing 0 does not count as an unknown choice.
- `default` catches anything that is not on the menu, like `7` or `hello`.
- The switch picks what to do; the do-while decides whether to show the menu again.

### Run it

Type `7`, then `0`.

<!-- input: 7 | 0 -->
```text
Welcome to the Recipe Box
Loaded 5 recipes

1) List recipes
0) Exit
Choice: 7
Unknown choice: 7

1) List recipes
0) Exit
Choice: 0
Closing the recipe box
Goodbye!
```

Commit and push: "Step 11 - A switch for the choice"

## Step 12 - A HashMap of recipes by id

### Type this

Add this line under the line `import java.util.ArrayList;`:

<!-- apply: after RecipeBox.java | import java.util.ArrayList; -->
```java
import java.util.HashMap;
```

In `main`, add these lines after the line `System.out.println("Loaded " + recipes.size() + " recipes");`:

<!-- apply: after RecipeBox.java | System.out.println("Loaded " + recipes.size() + " recipes"); -->
```java

        // The same recipes again, this time found by their id
        HashMap<Integer, Recipe> recipesById = buildIndex(recipes);
        System.out.println("Ids: " + recipesById.keySet());
```

Then add this method at the bottom of the class, under `loadRecipes`:

<!-- apply: end RecipeBox.java -->
```java

    // Puts every recipe in a HashMap, with its id as the key
    public static HashMap<Integer, Recipe> buildIndex(List<Recipe> recipes) {
        HashMap<Integer, Recipe> recipesById = new HashMap<>();
        for (Recipe recipe : recipes) {
            recipesById.put(recipe.getId(), recipe);
        }
        return recipesById;
    }
```

### What is happening

- A HashMap stores pairs: a key and a value. Here the key is the id and the value is the whole recipe.
- `HashMap<Integer, Recipe>` uses `Integer`, not `int`. The types between `< >` must be class names.
- `put(key, value)` adds one pair. The for-each loop puts every recipe in, so the map and the list hold the same 5 recipes.
- `keySet()` gives all the keys. The `Ids:` line is only a check; the next step removes it.

### Run it

Type `0`.

<!-- input: 0 -->
```text
Welcome to the Recipe Box
Loaded 5 recipes
Ids: [1, 2, 3, 4, 5]

1) List recipes
0) Exit
Choice: 0
Closing the recipe box
Goodbye!
```

Commit and push: "Step 12 - A HashMap of recipes by id"

## Step 13 - Finding a recipe, and the null check

### Type this

Delete the check line `System.out.println("Ids: " + recipesById.keySet());`.

<!-- apply: delete RecipeBox.java | System.out.println("Ids: " + recipesById.keySet()); | System.out.println("Ids: " + recipesById.keySet()); -->

Add a menu line under `System.out.println("1) List recipes");`:

<!-- apply: after RecipeBox.java | System.out.println("1) List recipes"); -->
```java
            System.out.println("2) Find a recipe by id");
```

Add a case under `case "1" -> listRecipes(recipes);`:

<!-- apply: after RecipeBox.java | case "1" -> listRecipes(recipes); -->
```java
                case "2" -> findRecipe(scanner, recipesById);
```

Add this method at the bottom of the class, under `buildIndex`:

<!-- apply: end RecipeBox.java -->
```java

    // Asks for an id and returns that recipe, or null when no recipe has that id
    public static Recipe askRecipe(Scanner scanner, HashMap<Integer, Recipe> recipesById) {
        System.out.print("Recipe id: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Recipe recipe = recipesById.get(id);         // get returns null for a missing key
        if (recipe == null) {
            System.out.println("No recipe with id " + id);
        }
        return recipe;
    }
```

And this one under it:

<!-- apply: end RecipeBox.java -->
```java

    // Finds one recipe by its id and prints it in full
    public static void findRecipe(Scanner scanner, HashMap<Integer, Recipe> recipesById) {
        Recipe recipe = askRecipe(scanner, recipesById);
        if (recipe != null) {
            System.out.println(recipe.getName() + ": " + recipe.getMinutes() + " minutes, serves " + recipe.getServings());
        }
    }
```

### What is happening

- `get(id)` jumps straight to the recipe with that key. No loop over the list is needed.
- When no recipe has that key, `get` returns `null`: no object at all. Calling `getName()` on `null` would crash the program.
- That is why `askRecipe` checks `recipe == null` and says so, and why `findRecipe` prints only when `recipe != null`.
- `askRecipe` is its own method because Step 17 needs the same question again.

### Run it

Type `2`, `3`, then `2`, `9`, then `0`.

<!-- input: 2 | 3 | 2 | 9 | 0 -->
```text
Welcome to the Recipe Box
Loaded 5 recipes

1) List recipes
2) Find a recipe by id
0) Exit
Choice: 2
Recipe id: 3
Chicken Curry: 50 minutes, serves 4

1) List recipes
2) Find a recipe by id
0) Exit
Choice: 2
Recipe id: 9
No recipe with id 9

1) List recipes
2) Find a recipe by id
0) Exit
Choice: 0
Closing the recipe box
Goodbye!
```

Commit and push: "Step 13 - Finding a recipe, and the null check"

## Step 14 - Catching an id that is not a number

### Type this

In `askRecipe`, replace the line `int id = Integer.parseInt(scanner.nextLine().trim());` with these lines:

<!-- apply: replace RecipeBox.java | int id = Integer.parseInt(scanner.nextLine().trim()); | int id = Integer.parseInt(scanner.nextLine().trim()); -->
```java
        String idText = scanner.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            System.out.println("Not a number: " + idText);
            return null;                             // no id, so no recipe
        }
```

### What is happening

- Before this step, typing `abc` as the id stopped the whole program with a red `NumberFormatException`.
- `NumberFormatException` is the exact exception `parseInt` throws for bad text. The catch block names it, so it handles only that problem.
- `return null;` ends the method early. The callers already know what `null` means: no recipe.
- `id` is declared above `try`. A variable declared inside the `try` block would not exist below it.

### Run it

Type `2`, `abc`, then `0`.

<!-- input: 2 | abc | 0 -->
```text
Welcome to the Recipe Box
Loaded 5 recipes

1) List recipes
2) Find a recipe by id
0) Exit
Choice: 2
Recipe id: abc
Not a number: abc

1) List recipes
2) Find a recipe by id
0) Exit
Choice: 0
Closing the recipe box
Goodbye!
```

Commit and push: "Step 14 - Catching an id that is not a number"

## Step 15 - Adding a recipe

### Type this

Add a menu line under `System.out.println("2) Find a recipe by id");`:

<!-- apply: after RecipeBox.java | System.out.println("2) Find a recipe by id"); -->
```java
            System.out.println("3) Add a recipe");
```

Add a case under `case "2" -> findRecipe(scanner, recipesById);`:

<!-- apply: after RecipeBox.java | case "2" -> findRecipe(scanner, recipesById); -->
```java
                case "3" -> addRecipe(scanner, recipes, recipesById);
```

Add this method at the bottom of the class, under `findRecipe`:

<!-- apply: end RecipeBox.java -->
```java

    // Asks for a new recipe and adds it to the list and to the map
    public static void addRecipe(Scanner scanner, List<Recipe> recipes, HashMap<Integer, Recipe> recipesById) {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Minutes: ");
        int minutes = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Servings: ");
        int servings = Integer.parseInt(scanner.nextLine().trim());

        int id = recipes.size() + 1;                 // ids run 1 to 5, so the next one is 6
        Recipe recipe = new Recipe(id, name, minutes, servings);
        recipes.add(recipe);
        recipesById.put(id, recipe);
        System.out.println("Added recipe " + id + ": " + name);
    }
```

### What is happening

- The new recipe goes in two places: `recipes.add` for the list, and `recipesById.put` for the map. Forget one and that part of the menu cannot see it.
- Both hold the same object. There is still only one Garlic Bread; the list and the map each point at it.
- The id is the list size plus one. That works because the ids run 1, 2, 3 with no gaps.
- The run finds recipe 6 straight after adding it, which proves the map has it.

### Run it

Type `3`, `Garlic Bread`, `15`, `4`, then `2`, `6`, then `0`.

<!-- input: 3 | Garlic Bread | 15 | 4 | 2 | 6 | 0 -->
```text
Welcome to the Recipe Box
Loaded 5 recipes

1) List recipes
2) Find a recipe by id
3) Add a recipe
0) Exit
Choice: 3
Name: Garlic Bread
Minutes: 15
Servings: 4
Added recipe 6: Garlic Bread

1) List recipes
2) Find a recipe by id
3) Add a recipe
0) Exit
Choice: 2
Recipe id: 6
Garlic Bread: 15 minutes, serves 4

1) List recipes
2) Find a recipe by id
3) Add a recipe
0) Exit
Choice: 0
Closing the recipe box
Goodbye!
```

Choose 1 before 0 and Garlic Bread is the sixth line of the list too. It is not in `recipes.csv` yet: Step 20 saves it.

Commit and push: "Step 15 - Adding a recipe"

## Step 16 - Catching bad numbers in a new recipe

### Type this

In `addRecipe`, add this line above the line `System.out.print("Minutes: ");`:

<!-- apply: before RecipeBox.java | System.out.print("Minutes: "); -->
```java
        try {
```

Indent every line from `System.out.print("Minutes: ");` down to `System.out.println("Added recipe " + id + ": " + name);` by four more spaces, so they sit inside the `try` block.

<!-- apply: indent RecipeBox.java | System.out.print("Minutes: "); | System.out.println("Added recipe " + id + ": " + name); -->

Then add these lines after the `Added recipe` line:

<!-- apply: after RecipeBox.java | System.out.println("Added recipe " + id + ": " + name); -->
```java
        } catch (NumberFormatException ex) {
            System.out.println("Minutes and servings must be whole numbers. Nothing was added.");
        }
```

### What is happening

- When `parseInt` fails, Java jumps straight to `catch`. Every line between the failing line and the `catch` is skipped.
- The `add` and `put` lines are inside the `try`, so a recipe with bad numbers never reaches the list or the map.
- Typing `fifteen` for the minutes fails at once, so the program never even asks for the servings.
- The menu shows again after the message, because the exception was handled inside `addRecipe`.

### Run it

Type `3`, `Garlic Bread`, `fifteen`, then `1`, then `0`.

<!-- input: 3 | Garlic Bread | fifteen | 1 | 0 -->
```text
Welcome to the Recipe Box
Loaded 5 recipes

1) List recipes
2) Find a recipe by id
3) Add a recipe
0) Exit
Choice: 3
Name: Garlic Bread
Minutes: fifteen
Minutes and servings must be whole numbers. Nothing was added.

1) List recipes
2) Find a recipe by id
3) Add a recipe
0) Exit
Choice: 1
1. Pancakes, 20 min, serves 4
2. Tomato Soup, 35 min, serves 6
3. Chicken Curry, 50 min, serves 4
4. Greek Salad, 15 min, serves 2
5. Banana Bread, 70 min, serves 8

1) List recipes
2) Find a recipe by id
3) Add a recipe
0) Exit
Choice: 0
Closing the recipe box
Goodbye!
```

Commit and push: "Step 16 - Catching bad numbers in a new recipe"

## Step 17 - The date and time a recipe was cooked

### Type this

Add these lines under the line `import java.io.FileReader;`:

<!-- apply: after RecipeBox.java | import java.io.FileReader; -->
```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
```

Add a menu line under `System.out.println("3) Add a recipe");`:

<!-- apply: after RecipeBox.java | System.out.println("3) Add a recipe"); -->
```java
            System.out.println("4) Mark a recipe as cooked");
```

Add a case under `case "3" -> addRecipe(scanner, recipes, recipesById);`:

<!-- apply: after RecipeBox.java | case "3" -> addRecipe(scanner, recipes, recipesById); -->
```java
                case "4" -> markCooked(scanner, recipesById);
```

Add this method at the bottom of the class, under `addRecipe`:

<!-- apply: end RecipeBox.java -->
```java

    // Marks a recipe as cooked, with the date and time it happened
    public static void markCooked(Scanner scanner, HashMap<Integer, Recipe> recipesById) {
        Recipe recipe = askRecipe(scanner, recipesById);
        if (recipe == null) {
            return;                                  // askRecipe already said what went wrong
        }

        // Right now, written like 2026-10-24 18:30
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String cookedAt = LocalDateTime.now().format(formatter);
        System.out.println("Cooked " + recipe.getName() + " at " + cookedAt);
    }
```

### What is happening

- `LocalDateTime.now()` is the date and the time at the moment the line runs.
- `DateTimeFormatter.ofPattern` describes how to write it: `yyyy` year, `MM` month, `dd` day, `HH` hour from 0 to 23, `mm` minutes.
- Capitals matter: `MM` is the month and `mm` is the minutes.
- `format` turns the date and time into a String, ready to print or to write to a file.
- `askRecipe` from Step 13 does the asking, the number check and the null check again, with no new code.

### Run it

Type `4`, `2`, then `0`. Your own date and time appear where this guide shows `<timestamp>`.

<!-- input: 4 | 2 | 0 -->
```text
Welcome to the Recipe Box
Loaded 5 recipes

1) List recipes
2) Find a recipe by id
3) Add a recipe
4) Mark a recipe as cooked
0) Exit
Choice: 4
Recipe id: 2
Cooked Tomato Soup at <timestamp>

1) List recipes
2) Find a recipe by id
3) Add a recipe
4) Mark a recipe as cooked
0) Exit
Choice: 0
Closing the recipe box
Goodbye!
```

Commit and push: "Step 17 - The date and time a recipe was cooked"

## Step 18 - Appending to the cooked log

### Type this

Add this line under the line `import java.io.BufferedReader;`:

<!-- apply: after RecipeBox.java | import java.io.BufferedReader; -->
```java
import java.io.BufferedWriter;
```

And this one under the line `import java.io.FileReader;`:

<!-- apply: after RecipeBox.java | import java.io.FileReader; -->
```java
import java.io.FileWriter;
```

In `markCooked`, add these lines after the line `System.out.println("Cooked " + recipe.getName() + " at " + cookedAt);`:

<!-- apply: after RecipeBox.java | System.out.println("Cooked " + recipe.getName() + " at " + cookedAt); -->
```java

        // Add one line at the end of the log: true means append, not overwrite
        String logLine = cookedAt + "|" + recipe.getName();
        try {
            FileWriter fileWriter = new FileWriter("cooked-log.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(logLine + "\n");
            bufferedWriter.close();
            System.out.println("Written to cooked-log.txt");
        } catch (Exception ex) {
            System.out.println("Could not write cooked-log.txt: " + ex.getMessage());
        }
```

### What is happening

- `FileWriter` creates the file the first time. The second value, `true`, means append: new lines go at the end and the old lines stay.
- Without `true`, every run would wipe the log and start it again with one line.
- `BufferedWriter` collects the text and writes it to the disk in one go. `close()` makes sure the text really lands in the file.
- `write` does not add a new line by itself, so the line ends with `"\n"`.
- Writing can fail too, for example on a full disk, so it sits in its own try/catch.

### Run it

Type `4`, `2`, then `0`.

<!-- input: 4 | 2 | 0 -->
```text
Welcome to the Recipe Box
Loaded 5 recipes

1) List recipes
2) Find a recipe by id
3) Add a recipe
4) Mark a recipe as cooked
0) Exit
Choice: 4
Recipe id: 2
Cooked Tomato Soup at <timestamp>
Written to cooked-log.txt

1) List recipes
2) Find a recipe by id
3) Add a recipe
4) Mark a recipe as cooked
0) Exit
Choice: 0
Closing the recipe box
Goodbye!
```

A new file `cooked-log.txt` appears in the `recipe-box` folder. Open it:

<!-- check-file: cooked-log.txt -->
```text
<timestamp>|Tomato Soup
```

Run the program again and cook another recipe. The log grows by one line each time and keeps the old lines.

Commit and push: "Step 18 - Appending to the cooked log"

## Step 19 - Names from A to Z with Collections.sort

### Type this

Add this line under the line `import java.util.ArrayList;`:

<!-- apply: after RecipeBox.java | import java.util.ArrayList; -->
```java
import java.util.Collections;
```

Add a menu line under `System.out.println("4) Mark a recipe as cooked");`:

<!-- apply: after RecipeBox.java | System.out.println("4) Mark a recipe as cooked"); -->
```java
            System.out.println("5) Names A to Z");
```

Add a case under `case "4" -> markCooked(scanner, recipesById);`:

<!-- apply: after RecipeBox.java | case "4" -> markCooked(scanner, recipesById); -->
```java
                case "5" -> printSortedNames(recipes);
```

Add this method at the bottom of the class, under `markCooked`:

<!-- apply: end RecipeBox.java -->
```java

    // Prints the recipe names from A to Z; the recipe list keeps its own order
    public static void printSortedNames(List<Recipe> recipes) {
        List<String> names = new ArrayList<>();
        for (Recipe recipe : recipes) {
            names.add(recipe.getName());
        }

        Collections.sort(names);
        for (String name : names) {
            System.out.println(name);
        }
    }
```

### What is happening

- `names` is a second list, of Strings this time. The loop copies each recipe name into it.
- `Collections.sort(names)` puts a list of Strings in A to Z order. It changes the list you give it.
- Sorting the copy, not `recipes`, keeps the menu list in file order.
- Choose 1 after 5 and you see the recipes still listed 1 to 5.

### Run it

Type `5`, then `0`.

<!-- input: 5 | 0 -->
```text
Welcome to the Recipe Box
Loaded 5 recipes

1) List recipes
2) Find a recipe by id
3) Add a recipe
4) Mark a recipe as cooked
5) Names A to Z
0) Exit
Choice: 5
Banana Bread
Chicken Curry
Greek Salad
Pancakes
Tomato Soup

1) List recipes
2) Find a recipe by id
3) Add a recipe
4) Mark a recipe as cooked
5) Names A to Z
0) Exit
Choice: 0
Closing the recipe box
Goodbye!
```

Commit and push: "Step 19 - Names from A to Z with Collections.sort"

## Step 20 - Saving the recipes on exit

### Type this

Replace the menu line `System.out.println("0) Exit");` with this:

<!-- apply: replace RecipeBox.java | System.out.println("0) Exit"); | System.out.println("0) Exit"); -->
```java
            System.out.println("0) Save and exit");
```

Replace the line `case "0" -> System.out.println("Closing the recipe box");` with this:

<!-- apply: replace RecipeBox.java | case "0" -> System.out.println("Closing the recipe box"); | case "0" -> System.out.println("Closing the recipe box"); -->
```java
                case "0" -> saveRecipes(recipes);
```

Add this method at the bottom of the class, under `printSortedNames`:

<!-- apply: end RecipeBox.java -->
```java

    // Writes every recipe back to recipes.csv, replacing what was there
    public static void saveRecipes(List<Recipe> recipes) {
        try {
            FileWriter fileWriter = new FileWriter("recipes.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Recipe recipe : recipes) {
                bufferedWriter.write(String.format("%d|%s|%d|%d\n", recipe.getId(), recipe.getName(), recipe.getMinutes(), recipe.getServings()));
            }
            bufferedWriter.close();
            System.out.println("Saved " + recipes.size() + " recipes to recipes.csv");
        } catch (Exception ex) {
            System.out.println("Could not save recipes.csv: " + ex.getMessage());
        }
    }
```

### What is happening

- `new FileWriter("recipes.csv")` has no `true`, so it overwrites: the file is written again from scratch with the whole list.
- `String.format` builds each line in the same `id|name|minutes|servings` shape that `loadRecipes` reads. What you save, you can load.
- The program saves once, when you choose 0, so any recipe added during the run is in the file for next time.
- The cooked log appends and the recipe file overwrites. Pick by asking: do I add to the history, or replace the whole list?

### Run it

Type `3`, `Garlic Bread`, `15`, `4`, then `0`.

<!-- input: 3 | Garlic Bread | 15 | 4 | 0 -->
```text
Welcome to the Recipe Box
Loaded 5 recipes

1) List recipes
2) Find a recipe by id
3) Add a recipe
4) Mark a recipe as cooked
5) Names A to Z
0) Save and exit
Choice: 3
Name: Garlic Bread
Minutes: 15
Servings: 4
Added recipe 6: Garlic Bread

1) List recipes
2) Find a recipe by id
3) Add a recipe
4) Mark a recipe as cooked
5) Names A to Z
0) Save and exit
Choice: 0
Saved 6 recipes to recipes.csv
Goodbye!
```

Open `recipes.csv`. It now holds 6 lines:

<!-- check-file: recipes.csv -->
```text
1|Pancakes|20|4
2|Tomato Soup|35|6
3|Chicken Curry|50|4
4|Greek Salad|15|2
5|Banana Bread|70|8
6|Garlic Bread|15|4
```

Run the program once more: it says `Loaded 6 recipes`. To start over, put the 5 lines from Step 3 back in `recipes.csv`.

Commit and push: "Step 20 - Saving the recipes on exit"

## Check your history

Open the repository on GitHub and look at its commit list. It should show 20 commits, one per step, with the messages from this guide, newest first. Open any commit to see exactly what that step changed.

## Final file

This is the whole program after Step 20. Compare yours with it line by line and fix any difference.

`RecipeBox.java`:

<!-- final: RecipeBox.java -->
```java
package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class RecipeBox {
    public static void main(String[] args) {
        System.out.println("Welcome to the Recipe Box");

        // Every recipe from the file, in file order
        List<Recipe> recipes = loadRecipes();
        System.out.println("Loaded " + recipes.size() + " recipes");

        // The same recipes again, this time found by their id
        HashMap<Integer, Recipe> recipesById = buildIndex(recipes);

        // One Scanner reads everything the user types
        Scanner scanner = new Scanner(System.in);

        // Show the menu until the user picks 0
        String choice;
        do {
            System.out.println();
            System.out.println("1) List recipes");
            System.out.println("2) Find a recipe by id");
            System.out.println("3) Add a recipe");
            System.out.println("4) Mark a recipe as cooked");
            System.out.println("5) Names A to Z");
            System.out.println("0) Save and exit");
            System.out.print("Choice: ");
            choice = scanner.nextLine().trim();

            // One case per menu line
            switch (choice) {
                case "1" -> listRecipes(recipes);
                case "2" -> findRecipe(scanner, recipesById);
                case "3" -> addRecipe(scanner, recipes, recipesById);
                case "4" -> markCooked(scanner, recipesById);
                case "5" -> printSortedNames(recipes);
                case "0" -> saveRecipes(recipes);
                default -> System.out.println("Unknown choice: " + choice);
            }
        } while (!choice.equals("0"));

        System.out.println("Goodbye!");
        scanner.close();                             // done reading: release the keyboard
    }

    // Prints one line per recipe, in list order
    public static void listRecipes(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            System.out.printf("%d. %s, %d min, serves %d%n", recipe.getId(), recipe.getName(), recipe.getMinutes(), recipe.getServings());
        }
    }

    // Reads recipes.csv into a list: one Recipe per line, id|name|minutes|servings
    public static List<Recipe> loadRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        // Open the file and read it one line at a time
        try {
            FileReader fileReader = new FileReader("recipes.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                int id = Integer.parseInt(parts[0]);
                int minutes = Integer.parseInt(parts[2]);
                int servings = Integer.parseInt(parts[3]);
                Recipe recipe = new Recipe(id, parts[1], minutes, servings);
                recipes.add(recipe);
            }

            bufferedReader.close();                  // also closes the FileReader
        } catch (Exception ex) {
            System.out.println("Could not read recipes.csv: " + ex.getMessage());
        }
        return recipes;
    }

    // Puts every recipe in a HashMap, with its id as the key
    public static HashMap<Integer, Recipe> buildIndex(List<Recipe> recipes) {
        HashMap<Integer, Recipe> recipesById = new HashMap<>();
        for (Recipe recipe : recipes) {
            recipesById.put(recipe.getId(), recipe);
        }
        return recipesById;
    }

    // Asks for an id and returns that recipe, or null when no recipe has that id
    public static Recipe askRecipe(Scanner scanner, HashMap<Integer, Recipe> recipesById) {
        System.out.print("Recipe id: ");
        String idText = scanner.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            System.out.println("Not a number: " + idText);
            return null;                             // no id, so no recipe
        }

        Recipe recipe = recipesById.get(id);         // get returns null for a missing key
        if (recipe == null) {
            System.out.println("No recipe with id " + id);
        }
        return recipe;
    }

    // Finds one recipe by its id and prints it in full
    public static void findRecipe(Scanner scanner, HashMap<Integer, Recipe> recipesById) {
        Recipe recipe = askRecipe(scanner, recipesById);
        if (recipe != null) {
            System.out.println(recipe.getName() + ": " + recipe.getMinutes() + " minutes, serves " + recipe.getServings());
        }
    }

    // Asks for a new recipe and adds it to the list and to the map
    public static void addRecipe(Scanner scanner, List<Recipe> recipes, HashMap<Integer, Recipe> recipesById) {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        try {
            System.out.print("Minutes: ");
            int minutes = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Servings: ");
            int servings = Integer.parseInt(scanner.nextLine().trim());

            int id = recipes.size() + 1;                 // ids run 1 to 5, so the next one is 6
            Recipe recipe = new Recipe(id, name, minutes, servings);
            recipes.add(recipe);
            recipesById.put(id, recipe);
            System.out.println("Added recipe " + id + ": " + name);
        } catch (NumberFormatException ex) {
            System.out.println("Minutes and servings must be whole numbers. Nothing was added.");
        }
    }

    // Marks a recipe as cooked, with the date and time it happened
    public static void markCooked(Scanner scanner, HashMap<Integer, Recipe> recipesById) {
        Recipe recipe = askRecipe(scanner, recipesById);
        if (recipe == null) {
            return;                                  // askRecipe already said what went wrong
        }

        // Right now, written like 2026-10-24 18:30
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String cookedAt = LocalDateTime.now().format(formatter);
        System.out.println("Cooked " + recipe.getName() + " at " + cookedAt);

        // Add one line at the end of the log: true means append, not overwrite
        String logLine = cookedAt + "|" + recipe.getName();
        try {
            FileWriter fileWriter = new FileWriter("cooked-log.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(logLine + "\n");
            bufferedWriter.close();
            System.out.println("Written to cooked-log.txt");
        } catch (Exception ex) {
            System.out.println("Could not write cooked-log.txt: " + ex.getMessage());
        }
    }

    // Prints the recipe names from A to Z; the recipe list keeps its own order
    public static void printSortedNames(List<Recipe> recipes) {
        List<String> names = new ArrayList<>();
        for (Recipe recipe : recipes) {
            names.add(recipe.getName());
        }

        Collections.sort(names);
        for (String name : names) {
            System.out.println(name);
        }
    }

    // Writes every recipe back to recipes.csv, replacing what was there
    public static void saveRecipes(List<Recipe> recipes) {
        try {
            FileWriter fileWriter = new FileWriter("recipes.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Recipe recipe : recipes) {
                bufferedWriter.write(String.format("%d|%s|%d|%d\n", recipe.getId(), recipe.getName(), recipe.getMinutes(), recipe.getServings()));
            }
            bufferedWriter.close();
            System.out.println("Saved " + recipes.size() + " recipes to recipes.csv");
        } catch (Exception ex) {
            System.out.println("Could not save recipes.csv: " + ex.getMessage());
        }
    }
}
```

`Recipe.java`:

<!-- final: Recipe.java -->
```java
package com.pluralsight;

public class Recipe {
    // Private fields: one recipe, one line of recipes.csv
    private int id;
    private String name;
    private int minutes;
    private int servings;

    // The constructor fills the fields when a recipe is created
    public Recipe(int id, String name, int minutes, int servings) {
        this.id = id;
        this.name = name;
        this.minutes = minutes;
        this.servings = servings;
    }

    // Getters: read a field from outside the class
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getServings() {
        return this.servings;
    }
}
```

## Challenges

Optional. Do each one in a new project (copy the code in), so this repository stays as it is.

1. Add a menu option that removes a recipe. Use `askRecipe` to find it, then `recipes.remove(recipe)` and `recipesById.remove(recipe.getId())`. Then look at `addRecipe`: why can `recipes.size() + 1` now give an id that is already taken? Fix it with a loop that finds the highest id.
2. Add a menu option that asks for a position from 1 to the number of recipes and prints `recipes.get(position - 1)`. Catch the exception you get when the position is too big.
3. Add a menu option that lists only the quick recipes, the ones that take 30 minutes or less.
4. Add a menu option that shows the cooked log: read `cooked-log.txt` with the same `while ((line = ...) != null)` loop as `loadRecipes`.
5. When marking a recipe as cooked, ask for the date instead of using today, typed like `10/24/2026`. Turn it into a `LocalDate` with `DateTimeFormatter.ofPattern("MM/dd/yyyy")` and `LocalDate.parse`, and catch the exception when the date is not valid.

## Pages that help

- [Three kinds of errors](https://raymaroun.github.io/yearup-java-fall2026/week-03/visuals/01-three-kinds-of-errors.html): compile errors, logic errors and runtime errors.
- [try/catch](https://raymaroun.github.io/yearup-java-fall2026/week-03/visuals/02-try-catch-flow.html): where the program jumps when something fails.
- [Reading a file](https://raymaroun.github.io/yearup-java-fall2026/week-03/visuals/03-reading-a-file.html): `FileReader`, `BufferedReader` and the `readLine` loop.
- [Writing a file](https://raymaroun.github.io/yearup-java-fall2026/week-03/visuals/04-writing-a-file.html): `FileWriter`, `BufferedWriter`, overwrite or append.
- [Date and time objects](https://raymaroun.github.io/yearup-java-fall2026/week-03/visuals/05-date-and-time-objects.html): `LocalDate`, `LocalTime` and `LocalDateTime`.
- [Date patterns](https://raymaroun.github.io/yearup-java-fall2026/week-03/visuals/06-date-patterns.html): `yyyy`, `MM`, `dd`, `HH`, `mm` and the rest.
- [Array or ArrayList](https://raymaroun.github.io/yearup-java-fall2026/week-03/visuals/07-array-vs-arraylist.html): a fixed size or a list that grows.
- [ArrayList operations](https://raymaroun.github.io/yearup-java-fall2026/week-03/visuals/08-arraylist-operations.html): `add`, `get`, `set`, `remove` and `size`.
- [HashMap lookup](https://raymaroun.github.io/yearup-java-fall2026/week-03/visuals/09-hashmap-lookup.html): finding a value by its key, and the `null` you get when the key is missing.
- [HashMap operations](https://raymaroun.github.io/yearup-java-fall2026/week-03/visuals/10-hashmap-operations.html): `put`, `get`, `remove`, `keySet` and `values`.
- [Cheat sheet](https://raymaroun.github.io/yearup-java-fall2026/week-03/reference/cheat-sheet.html): the week's syntax on one page.
- [Common errors](https://raymaroun.github.io/yearup-java-fall2026/week-03/reference/common-errors.html): error messages you will meet and how to fix them.
