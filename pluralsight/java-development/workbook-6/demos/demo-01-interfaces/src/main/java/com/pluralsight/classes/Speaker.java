package com.pluralsight.classes;

import com.pluralsight.interfaces.Playable;

/*
 * Speaker is the simplest class of this demo: it implements one interface.
 * "implements Playable" is a promise: a Speaker has every method listed
 * in Playable, so it can be used wherever a Playable is expected.
 */
public class Speaker implements Playable {                          // implements: Speaker promises play() and stop()
    // An interface has no fields like these. Each class keeps its own data.
    private String name;
    private String currentSong;

    public Speaker(String name) {
        this.name = name;
        this.currentSong = "";
    }

    public String getName() {
        return name;
    }

    public String getCurrentSong() {
        return currentSong;
    }

    // The first method promised to Playable. It is required. If you delete
    // it, the compiler says:
    // Speaker is not abstract and does not override abstract method play(String) in Playable
    // The method must be public, because every interface method is public.
    @Override                                                       // checks that Playable really has a play(String) method
    public void play(String song) {
        this.currentSong = song;
        System.out.println("The speaker is playing " + song + ".");
    }

    // The second method promised to Playable, required in the same way.
    @Override
    public void stop() {
        this.currentSong = "";
        System.out.println("The speaker stopped.");
    }

    // There is no print() in this class, and a Speaker can still call it:
    // print() is a default method of Playable, so Speaker inherits it with
    // the body written in the interface.
}
