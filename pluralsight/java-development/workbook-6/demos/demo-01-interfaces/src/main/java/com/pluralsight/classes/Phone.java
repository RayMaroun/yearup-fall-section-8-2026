package com.pluralsight.classes;

import com.pluralsight.interfaces.Chargeable;
import com.pluralsight.interfaces.Playable;

/*
 * Phone implements two interfaces: it plays music AND it can be charged.
 * A class can extend only one parent class, but it can implement as many
 * interfaces as it needs, separated by commas. In exchange it must write
 * the methods of all of them: play() and stop() for Playable, charge()
 * for Chargeable.
 */
public class Phone implements Playable, Chargeable {                // two promises: a Phone is a Playable and a Chargeable

    private String name;
    private String currentSong;
    private int battery;

    // A new Phone starts with half a battery.
    public Phone(String name) {
        this.name = name;
        this.currentSong = "";
        this.battery = 50;
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

    // Promised to Chargeable.
    @Override
    public void charge() {
        this.battery = 100;
        System.out.println("The phone is charging.");
    }

    // Promised to Playable. Each song uses 10 of the battery, and with
    // less than 10 left the Phone says so.
    @Override
    public void play(String song) {
        if (battery >= 10) {
            this.currentSong = song;
            battery -= 10;
            System.out.println("The phone is playing " + song + ".");
        } else {
            System.out.println("Not enough battery to play.");
        }
    }

    // Promised to Playable.
    @Override
    public void stop() {
        this.currentSong = "";
        System.out.println("The phone stopped the music.");
    }

    // Playable has a default print() and Chargeable has one too. Phone
    // would inherit two different bodies for the same method, and Java
    // refuses to pick one. So this override is required. If you delete it,
    // the compiler says:
    // types Playable and Chargeable are incompatible;
    // class Phone inherits unrelated defaults for print() from types Playable and Chargeable
    //
    // Inside the override, Phone is free to do what it wants. Here it
    // chooses one of the two inherited versions.
    @Override
    public void print() {
        Playable.super.print();                                     // InterfaceName.super.method(): run the default from that interface
    }
}
