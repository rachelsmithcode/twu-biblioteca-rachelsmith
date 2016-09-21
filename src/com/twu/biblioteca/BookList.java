package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by RachelSmith on 20/09/2016.
 */
public class BookList extends MenuItem implements List {

    private String listName = "Book List";
    private ArrayList<BookItem> bookItemList;


    public BookList(ArrayList<BookItem> list) {
        setList(list);

    }

    private void setList(ArrayList<BookItem> list){
        bookItemList = list;
    };

    @Override
    public void printList() {

        for (int i = 0; i < bookItemList.size(); i++) {
            BookItem item = bookItemList.get(i);
            if (!item.checkedOut()) {
                item.printDetails();
            }
        }
    }

    public void checkOut(String name) {

        Boolean validBookChoice = false;

        for (int i = 0; i < bookItemList.size(); i++) {
            BookItem book = bookItemList.get(i);
            if (book.returnName() == name && !book.checkedOut()) {
                book.checkOutBook();
                printMessage("Thank you! Enjoy the book");
                validBookChoice = true;
                break;
            }
        }
            if (!validBookChoice) {
                printMessage("That book is not available.");
            }

    }

    public void returnBook(String name) {

        Boolean validBookChoice = false;

        for (int i = 0; i < bookItemList.size(); i++) {
            BookItem book = bookItemList.get(i);
            if (book.returnName() == name && book.checkedOut()) {
                book.beReturned();
                printMessage("Thank you for returning the book.");
                validBookChoice = true;
                break;
            }
        }
        if (!validBookChoice) {
            printMessage("That is not a valid book to return.");
        }

    }

    @Override
    public void printName() {

        printMessage(listName);

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
