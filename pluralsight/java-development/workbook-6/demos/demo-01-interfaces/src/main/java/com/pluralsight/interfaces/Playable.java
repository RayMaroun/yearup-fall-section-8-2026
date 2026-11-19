package com.pluralsight.interfaces;

/*
 * Playable is an interface: a list of methods that a class promises to have.
 * It describes what a device that plays music can do, play a song and stop,
 * without saying how. Each class that implements Playable (Speaker, Radio,
 * Phone) writes the how in its own way.
 */
public interface Playable {
    // Abstract methods: only the first line, ended by a semicolon, with no
    // { } and no body. In an interface a method is public and abstract
    // without writing either word.
    // Every class that implements Playable MUST write these two methods.
    void play(String song);                                         // no body: the class writes it

    void stop();

    // Default method: the word default lets an interface method come with
    // a body. A class that implements Playable gets print() ready to use
    // and does not have to write it. It may still override it.
    default void print() {
        System.out.println("I can play music!");
    }
}
