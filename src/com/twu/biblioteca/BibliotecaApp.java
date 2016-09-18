package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {



    public void main() {
        welcomeMessage();
        printBookList();
    }


    public void welcomeMessage() {
        System.out.println("Welcome to the Biblioteca Experience");
    }

    public void printBookList() {
        System.out.println(bookList());
    }

    private ArrayList<String> bookList() {
        ArrayList<String> books = new ArrayList<String>();
        String bookOne = "Book One";
        String bookTwo = "Book Two";
        books.add(bookOne);
        books.add(bookTwo);
        return books;
    }
}
