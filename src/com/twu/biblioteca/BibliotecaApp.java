package com.twu.biblioteca;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;

public class BibliotecaApp {

    private Console console;
    private Welcome welcome;
    private MenuDisplay menuDisplay;
    private BookList bookList;
    private GetInput getInput;

    ArrayList<MenuItem> menuItems;

    public void main(String[] args) {
        new BibliotecaApp(new Console(), new Welcome(), new BookList(), new GetInput(), new MenuDisplay(console)).launch();
    }

    public BibliotecaApp(Console con, Welcome wel, BookList bkList, GetInput input, MenuDisplay menu) {
        console = con;
        welcome = wel;
        menuDisplay = menu;
        bookList = bkList;
        getInput = input;
    }


    private void launch() {
        welcome.printWelcome(console);

        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            menuDisplay.printItems(item);
            menuDisplay.selectItem(getInput, bookList);
        }



    }

}