package com.twu.biblioteca;

public class Quit extends MenuOption {


    private String itemName = "Quit";

    @Override
    public void printName() {
        printToConsole(itemName);
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
    public void checkOutBook(String name) {
        printToConsole("Invalid Selection");
    }

    @Override
    public void returnBook(String name) {
        printToConsole("Invalid Selection");
    }
}
