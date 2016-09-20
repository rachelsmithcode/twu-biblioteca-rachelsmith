package com.twu.biblioteca;

/**
 * Created by RachelSmith on 19/09/2016.
 */
public class MenuItem {

    Console console;
    String itemDescription;

    public MenuItem(Console con, String description) {
        console = con;
        itemDescription = description;
    }

    public void printDescription() {
        console.message(itemDescription);
    }
}
