package com.pluralsight.classes;

import com.pluralsight.interfaces.Playable;

/*
 * Radio implements Playable, like Speaker, but it runs on a battery.
 * The interface says WHAT a Playable can do. Each class decides HOW:
 * the two classes have the same methods with different bodies.
 */
public class Radio implements Playable {
    private String name;
    private String currentSong;
    private int battery;

    // A new Radio starts with a full battery.
    public Radio(String name) {
        this.name = name;
        this.currentSong = "";
        this.battery = 100;
    }

    public String getName() {
        return name;
    }

    public String getCurrentSong() {
        return currentSong;
    }

    public int getBattery() {
        return battery;
    }

    // Same method as in Speaker, written the Radio's own way: it only plays
    // while there is enough battery, and each song uses 10 of it. With less
    // than 10 left, nothing happens.
    @Override
    public void play(String song) {
        if (battery >= 10) {
            this.currentSong = song;
            battery -= 10;
            System.out.println("The radio is playing " + song + ".");
        }
    }

    // The Radio forgets the song and prints nothing. The interface only
    // asks for a stop() method, the body is up to the class.
    @Override
    public void stop() {
        this.currentSong = "";
    }
}
