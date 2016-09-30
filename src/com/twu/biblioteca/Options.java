package com.twu.biblioteca;

import java.util.ArrayList;

public class Options {

    public static ArrayList<String> optionsList;
    public static ArrayList<BookItem> bookList;

    public Options(ArrayList<String> options, ArrayList<BookItem> books) {
        setOptionsList(options);
        setBookList(books);

    }

    private void setOptionsList(ArrayList<String> options) {
        optionsList = options;
    }

    private void setBookList(ArrayList<BookItem> books) {
        bookList = books;
    }

    public void printOptions() {
        for (int i = 0; i < optionsList.size(); i++) {
            printToConsole(optionsList.get(i));
        }
    }

    public ArrayList<String> getOptions() {
        return optionsList;
    }

    private void printToConsole(String message) {
        System.out.println(message);
    }

    public void quit() {
        System.exit(1);
    }

    public void printBookList() {
        ArrayList<BookItem> booksInStock = returnBooksInStock();
        for (int i = 0; i < booksInStock.size(); i++) {
            BookItem book = booksInStock.get(i);
                    book.printDetails();
        }

    }

    public void checkoutBook(String input) {

        Boolean validBookChoice = false;
        ArrayList<BookItem> booksInStock = returnBooksInStock();

        for (int i = 0; i < booksInStock.size(); i++) {
            BookItem book = booksInStock.get(i);
            if (book.returnName().equals(input)) {
                book.beCheckedOut();
                printToConsole("Thank you! Enjoy the book");
                validBookChoice = true;
                break;
            }
        }
        if (!validBookChoice) {
            printToConsole("That book is not available.");
        }

    }

    public void returnBook(String input) {
        Boolean validBookChoice = false;
        ArrayList<BookItem> booksOutOfStock = returnBooksOutOfStock();

        for (int i = 0; i < booksOutOfStock.size(); i++) {
            BookItem book = booksOutOfStock.get(i);
            if (book.returnName().equals(input)) {
                book.beReturned();
                printToConsole("Thank you for returning the book.");
                validBookChoice = true;
                break;
            }
        }
        if (!validBookChoice) {
            printToConsole("That is not a valid book to return.");
        }

    }

    private static ArrayList<BookItem> returnBooksInStock() {
        ArrayList<BookItem> booksInStock = new ArrayList<BookItem>();

        for (int i = 0; i < bookList.size(); i++) {
            BookItem book = bookList.get(i);
            if (book.isInStock()) {
                booksInStock.add(book);
            }
        }
        return booksInStock;


    }

    private static ArrayList<BookItem> returnBooksOutOfStock() {
        ArrayList<BookItem> booksOutOfStock = new ArrayList<BookItem>();

        for (int i = 0; i < bookList.size(); i++) {
            BookItem book = bookList.get(i);
            if (!book.isInStock()) {
                booksOutOfStock.add(book);
            }
        }
        return booksOutOfStock;


    }
}
