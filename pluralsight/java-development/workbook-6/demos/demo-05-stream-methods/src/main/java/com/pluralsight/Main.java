package com.pluralsight;

import java.util.Arrays;
import java.util.List;

/*
 * Demo 05 - Stream methods
 *
 * The previous demo built one pipeline with filter. This one goes through
 * the stream methods one by one. A pipeline always has the same shape:
 *
 *   list.stream()      the source
 *       .method(...)   zero or more steps that work on the elements
 *       .method(...)   one last step that ends the stream and gives the
 *                      result
 *
 * Steps in the middle (each one hands the elements on to the next step):
 *
 *   filter    keeps the elements for which the lambda answers true
 *   sorted    puts the elements in order
 *   map       turns each element into a new value
 *   distinct  removes the repeats
 *
 * Steps that end the stream:
 *
 *   toList    saves the elements in a new list
 *   count     gives how many elements are left
 *   forEach   runs a lambda for each element and gives nothing back
 *   reduce    combines all the elements into one value
 *
 * Each block below starts with a title line, then its results, then a
 * blank line. Each block creates its own list, so it can be read alone.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- filter ----------
        // filter calls the lambda once for each title and keeps the titles
        // for which it answers true. toLowerCase() makes the test ignore
        // upper and lower case. Three of the six titles pass.
        // filter: keeps only the elements that match the condition
        System.out.println("========== Filter Method ==========");

        List<String> titles = Arrays.asList(
                "Halloween", "Ghost", "Halloween 2",
                "Friday the 13th", "Twister", "Halloween 3");

        // The same work in two statements, switched off: first the new
        // list, then the printing. The line with a lambda and the line
        // with System.out::println do the same thing.
        /*List<String> matching = titles
                .stream().filter(title -> title.toLowerCase().contains("halloween"))
                .toList();

        //matching.forEach(matchingTitle -> System.out.println(matchingTitle));
        matching.forEach(System.out::println);*/

        // Everything chained in one statement: filter, save in a list,
        // print each element of that list.
        // System.out::println is a method reference. It is a shorter way
        // to write a lambda that only passes its parameter to one method.
        titles
                .stream().filter(title -> title.toLowerCase().contains("halloween"))
                .toList()
                .forEach(System.out::println);                      // method reference: short for title -> System.out.println(title)

        // A lambda with several statements, switched off. With braces, the
        // body is a block: each statement ends with a semicolon, and
        // because filter is waiting for an answer, the last statement
        // returns it. This version keeps a title only when it contains
        // the word AND is longer than 10 characters.
        // The same filter with a multi-line lambda: { } and return are needed
        /*titles
                .stream().filter(title -> {
                    String lower = title.toLowerCase();
                    boolean containsHalloween = lower.contains("halloween");
                    boolean isLongTitle = title.length() > 10;
                    return containsHalloween && isLongTitle;
                } )
                .toList()
                .forEach(System.out::println);*/
        System.out.println();

        // ---------- count ----------
        // count ends the stream with one number: how many elements reached
        // it. Here it counts what filter let through, so the answer is 3.
        // The number comes back as a long, not an int.
        // count: ends the stream with how many elements are left
        System.out.println("========== Count Method ==========");

        List<String> titles2 = Arrays.asList(
                "Halloween", "Ghost", "Halloween 2",
                "Friday the 13th", "Twister", "Halloween 3");

        long count = titles2
                .stream().filter(title -> title.toLowerCase().contains("halloween"))
                .count();                                           // count() returns a long

        System.out.println("The count is: " + count);
        System.out.println();

        // ---------- forEach ----------
        // forEach runs something once for each element and gives nothing
        // back. It is the usual way to print the result of a pipeline.
        // forEach: runs a lambda or a method reference for each element
        System.out.println("========== forEach Method ==========");

        List<String> titles3 = Arrays.asList(
                "Halloween", "Ghost", "Halloween 2",
                "Friday the 13th", "Twister", "Halloween 3");

        // Switched off: forEach straight on the list prints all six titles.
        // titles3.forEach(System.out::println);

        titles3
                .stream().filter(title -> title.toLowerCase().contains("halloween"))
                .toList()
                .forEach(System.out::println);
        System.out.println();

        // ---------- sorted ----------
        // sorted puts the elements in order. For Strings that is
        // alphabetical order. It can only sort a type that knows how to
        // compare itself (a type that is Comparable), which String and the
        // number types are.
        // "Halloween " ends with a space, so it sorts before "Halloween 2".
        // sorted: sorts the stream, the type must be Comparable
        System.out.println("========== Sorted Method ==========");

        List<String> titles4 = Arrays.asList(
                "Halloween 3", "Ghost", "Halloween ",
                "Friday the 13th", "Twister", "Halloween 2");

        titles4
                .stream().filter(title -> title.toLowerCase().contains("halloween"))
                .sorted() // in reverse order: .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
        System.out.println();

        // ---------- map ----------
        // map hands each element to the lambda and passes on what the
        // lambda returns. One element goes in, one comes out, changed.
        // Here each number becomes its square.
        // map: turns each element into a new result
        System.out.println("========== Map Method ==========");

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        // The loop version, switched off: an empty list, one pass per
        // number, add the square.
        /*List<Integer> squaresList = new ArrayList<>();

        for (Integer number : numbers) {
            squaresList.add(number * number);
        }*/

        // Two steps chained: map makes the squares 9, 4, 4, 9, 49, 9, 25,
        // then distinct lets each value through only once: 9, 4, 49, 25.
        List<Integer> squaresList =
                numbers
                        .stream().map(number -> number * number)
                        .distinct()                                 // removes the duplicates
                        .toList();


        System.out.println(numbers);
        System.out.println(squaresList);

        System.out.println("-------------------------------------------------------------");

        // map works on any type. Here each name becomes the same name in
        // upper case. The original list is not changed.
        List<String> names = Arrays.asList("alice", "bob", "charlie", "dana");

        List<String> upperCaseNames =
                names
                        .stream().map(name -> name.toUpperCase())
                        .toList();

        System.out.println("Original list: " + names);
        System.out.println("Uppercase list: " + upperCaseNames);
        System.out.println();

        // ---------- reduce ----------
        // reduce combines all the elements into one value. It takes two
        // things:
        //   a starting value   the first value of the running result
        //   a lambda with two parameters: the running result so far, and
        //   the next element
        // What the lambda returns becomes the running result for the next
        // element. When the elements run out, the running result is the
        // answer.
        // For the sum: 0 + 3 is 3, 3 + 2 is 5, 5 + 2 is 7, and so on up
        // to 25.
        // reduce: aggregates all the elements into a single value
        System.out.println("========== Reduce Method ==========");

        List<Integer> numbers2 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        int sum = numbers2
                .stream().reduce(0, (sumTotal, num) -> sumTotal + num);     // 0 is the starting value, sumTotal carries the running result

        // (double) turns the sum into a double first, so the division
        // keeps its decimals. Without it, int divided by int drops them.
        double average = (double) sum / numbers2.size();

        System.out.println("The sum is: " + sum);
        System.out.println("The average is: " + average);

        System.out.println("-------------------------------------------------------------");
        // A product has to start at 1. Starting at 0 would give 0 at every
        // step, because 0 times anything is 0.
        List<Integer> numbers3 = Arrays.asList(1, 2, 3, 4, 5);

        int product = numbers3
                .stream().reduce(1, (sumTotal, num) -> sumTotal * num);     // a product starts at 1, not 0

        System.out.println("The product is: " + product);

        System.out.println("-------------------------------------------------------------");

        // reduce works on text too. The starting value is "" (an empty
        // String) and each step adds the next word and a space. The result
        // ends with one space too many, so trim() removes it when printing.
        List<String> words = Arrays.asList("Potatoes", "are", "awesome");

        String sentence = words
                .stream().reduce("", (acc, word) -> acc + word + " ");

        System.out.println("The original words: " + words);
        System.out.println("Combined Sentence: " + sentence.trim());
        System.out.println();
    }
}
