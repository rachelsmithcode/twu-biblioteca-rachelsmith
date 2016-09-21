package com.twu.biblioteca;

/**
 * Created by RachelSmith on 20/09/2016.
 */
public class Quit extends MenuItem {


    private String itemName = "Quit";

    @Override
    public void printName() {
        printMessage(itemName);
    }

    @Override
    public String returnName() {
        return itemName;
    }

    @Override
    public void select() {
        System.exit(1);
    }

    @Override
    public void checkOut(String name) {
        printMessage("Invalid Selection");
    }
}
