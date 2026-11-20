package com.pluralsight;

/*
 * Violinist is a child class of Musician, like Cellist. Because a
 * Violinist is-a Musician, it fits in a Duet<Musician>.
 */
public class Violinist extends Musician {

    public Violinist(String name, String instrument) {
        super(name, instrument);
    }

    // The Violinist's own version of perform(). This one runs when a Duet
    // asks a Violinist to perform.
    @Override
    public void perform() {
        System.out.println("I am a Violinist and I am performing!");
    }
}
