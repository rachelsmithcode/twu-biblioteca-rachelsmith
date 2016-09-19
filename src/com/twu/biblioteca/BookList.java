package com.twu.biblioteca;

import java.util.ArrayList;

public class BookList {

    ArrayList<Book> books = new ArrayList<Book>();


    public void printBooks() {

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            book.printInfo();
        }

    }

    public void addBookToBookList(Book book) {

        books.add(book);
    }



}
