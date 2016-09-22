package com.twu.biblioteca;

import java.util.ArrayList;

public class BookList extends MenuOption {

    private String listName = "Book List";
    private ArrayList<BookItem> bookItemList;


    public BookList(ArrayList<BookItem> list) {
        setList(list);

    }

    private void setList(ArrayList<BookItem> list){
        bookItemList = list;
    };

    private void printList() {

        for (int i = 0; i < bookItemList.size(); i++) {
            BookItem item = bookItemList.get(i);
            if (item.isInStock()) {
                item.printDetails();
            }
        }
    }

    public void checkOutBook(String name) {

        Boolean validBookChoice = false;

        for (int i = 0; i < bookItemList.size(); i++) {
            BookItem book = bookItemList.get(i);
            if (book.returnName().equals(name) && book.isInStock()) {
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

    public void returnBook(String name) {

        Boolean validBookChoice = false;

        for (int i = 0; i < bookItemList.size(); i++) {
            BookItem book = bookItemList.get(i);
            if (book.returnName().equals(name) && !book.isInStock()) {
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

    @Override
    public void printName() {

        printToConsole(listName);

    }

    @Override
    public String returnName() {

        return listName;
    }

    @Override
    public void select() {

        printList();

    }


}
