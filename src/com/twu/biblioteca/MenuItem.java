package com.twu.biblioteca;

abstract public class MenuItem extends ConsoleObject {

    abstract public void printName();

    abstract public String returnName();

    abstract public void select();

    abstract public void checkOut(String name);


}
