package com.twu.biblioteca;

public class Quit extends MenuOption {


    public static String ITEM_NAME = "Quit";
    public static String INVALID_SELECTION = "Invalid Selection";

    @Override
    public void printName() {
        printToConsole(ITEM_NAME);
    }

    @Override
    public String returnName() {
        return ITEM_NAME;
    }

    @Override
    public void select() {
        System.exit(1);
    }

    @Override
    public void checkOutBook(String name) {
        printToConsole(INVALID_SELECTION);
    }

    @Override
    public void returnBook(String name) {
        printToConsole(INVALID_SELECTION);
    }
}
