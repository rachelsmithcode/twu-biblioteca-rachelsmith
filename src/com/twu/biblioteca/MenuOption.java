package com.twu.biblioteca;

abstract public class MenuOption extends ConsoleObject {

    abstract public void printName();

    abstract public String returnName();

    abstract public void select();

    abstract public void checkOutBook(String name);

    abstract public void returnBook(String name);

}
