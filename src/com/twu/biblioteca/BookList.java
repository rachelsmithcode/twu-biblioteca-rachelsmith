package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by RachelSmith on 20/09/2016.
 */
public class BookList extends MenuItem implements List {

    private String listName = "Book List";
    private ArrayList<BookItem> menuItemList;


    public BookList(ArrayList<BookItem> list) {
        setList(list);

    }

    private void setList(ArrayList<BookItem> list){
        menuItemList = list;
    };

    @Override
    public void printList() {

        for (int i = 0; i < menuItemList.size(); i++) {
            BookItem item = menuItemList.get(i);
            item.printDetails();
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
