package com.twu.biblioteca;


import java.util.ArrayList;
import java.util.Scanner;
import java.io.InputStream;

public class MenuList extends ConsoleObject implements List {

    ArrayList<MenuItem> items;
    GetInput getInput;

    public MenuList(ArrayList<MenuItem> list) {
        items = list;
    }

    @Override
    public void printList() {

        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            item.printName();
        }
    }


    public void selectItem(GetInput getInput) {


        saveGetInput(getInput);
        String input = requestInput();
        Boolean validEntry = false;

        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            String itemName = item.returnName();
            if (input == itemName) {
                item.select();
                validEntry = true;
                break;
            }
        }
        if (!validEntry) {
            printMessage("Please select a valid item!");
        }
    }

    public void checkOutItem(GetInput getInput) {


        saveGetInput(getInput);
        String input = requestInput();

        for (int i = 0; i < items.size(); i++) {
            MenuItem bookList = items.get(i);
            String itemName = bookList.returnName();
            if ("Book List" == itemName) {
                bookList.checkOut(input);
                break;
            }
        }
    }

    public void returnItem(GetInput getInput) {


        saveGetInput(getInput);
        String input = requestInput();

        for (int i = 0; i < items.size(); i++) {
            MenuItem bookList = items.get(i);
            String itemName = bookList.returnName();
            if ("Book List" == itemName) {
                bookList.returnBook(input);
                break;
            }
        }
    }

    private String requestInput() {
        return getInput.returnString();
    }

    private void saveGetInput(GetInput getIn) {
        getInput = getIn;
    }

}
