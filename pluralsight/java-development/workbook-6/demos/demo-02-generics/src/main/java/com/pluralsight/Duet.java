package com.pluralsight;

/*
 * Duet is a generic class with a bounded type: <T extends Musician>.
 * T is still a placeholder, but not for any type: only Musician or a
 * child class of Musician (Cellist, Violinist). In exchange, Java knows
 * that every T has the methods of Musician, so Duet can call perform().
 * A Duet<Integer> is refused, because Integer is not a Musician.
 */
public class Duet<T extends Musician> {                             // bounded type: T must be Musician or a child class of Musician
    private T left;
    private T right;

    public Duet(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public T getRight() {
        return right;
    }

    // The same two calls that Pair could not make. Here they compile:
    // whatever T is, it is a Musician, so it has perform(). Each musician
    // runs its own version, the Cellist's or the Violinist's.
    public void perform() {
        left.perform();                                             // allowed: every T is-a Musician
        right.perform();
    }
}
