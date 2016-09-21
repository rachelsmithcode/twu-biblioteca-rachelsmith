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

        for (int i = 0; i < bookItemList.size(); i++) {
            BookItem book = bookItemList.get(i);
            if (book.returnName() == name) {
                book.checkOutBook();
            }
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
