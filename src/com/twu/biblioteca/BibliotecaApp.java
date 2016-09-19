package com.twu.biblioteca;


public class BibliotecaApp {



    public void main() {
        welcomeMessage();
        printBookList(createBookList());
    }


    public void welcomeMessage() {
        System.out.println("Welcome to the Biblioteca Experience");
    }

    public void printBookList(BookList booklist) {
        booklist.printBooks();
    }

    private BookList createBookList() {
        BookList bookListOne = new BookList();
        return bookListOne;
    }

}
