package com.pluralsight;

/*
 * Pair is a generic class: it holds two things of the same type.
 * The <T> after the class name is a type parameter, a placeholder for a
 * type. Pair.java never says which type: the code that creates a Pair
 * fills it in, as Pair<Integer> or Pair<String>. One class, any type.
 */
public class Pair<T> {                                              // T is filled in when a Pair object is created
    private T leftThing;
    private T rightThing;

    // Both things must be the same type T: a Pair<Integer> takes two
    // Integer values.
    public Pair(T leftThing, T rightThing) {
        this.leftThing = leftThing;
        this.rightThing = rightThing;
    }

    // Returns a T: an Integer for a Pair<Integer>, a String for a
    // Pair<String>. No cast needed.
    public T getLeftThing() {
        return leftThing;
    }

    public T getRightThing() {
        return rightThing;
    }

    // Exchanges the two things with the help of a temporary variable.
    // swap() works for any T because it only moves the things around.
    // It never calls a method on them, so it does not need to know what
    // they are.
    public void swap() {
        T temp = leftThing;                                         // a variable of type T, whatever T is
        leftThing = rightThing;
        rightThing = temp;
    }

    // The limit of a plain T: it can be any type at all, an Integer, a
    // String, a Cellist. Java cannot promise that every possible T has a
    // perform() method, so it refuses the call. Remove the comment marks
    // and the compiler says, for both lines:
    // cannot find symbol - method perform(), variable leftThing of type T
    // Duet.java shows the fix: limit T to musicians.
    /*public void perform(){
        leftThing.perform();
        rightThing.perform();
    }*/
}
