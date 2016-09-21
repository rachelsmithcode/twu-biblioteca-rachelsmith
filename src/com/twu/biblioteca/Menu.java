package com.twu.biblioteca;


import java.util.ArrayList;

public class Menu extends ConsoleObject {

    ArrayList<MenuOption> items;
    GetInput getInput;

    public Menu(ArrayList<MenuOption> list) {
        items = list;
    }

    public void printList() {

        for (int i = 0; i < items.size(); i++) {
            MenuOption item = items.get(i);
            item.printName();
        }
    }


    public void selectItem(GetInput getInput) {


        saveGetInput(getInput);
        String input = requestInput();
        Boolean validEntry = false;

        for (int i = 0; i < items.size(); i++) {
            MenuOption item = items.get(i);
            String itemName = item.returnName();
            if (input == itemName) {
                item.select();
                validEntry = true;
                break;
            }
        }
        if (!validEntry) {
            printToConsole("Please select a valid item!");
        }
    }

    public void checkOutItem(GetInput getInput) {


        saveGetInput(getInput);
        String input = requestInput();

        for (int i = 0; i < items.size(); i++) {
            MenuOption bookList = items.get(i);
            String itemName = bookList.returnName();
            if ("Book List" == itemName) {
                bookList.checkOutBook(input);
                break;
            }
        }
    }

    public void returnItem(GetInput getInput) {


        saveGetInput(getInput);
        String input = requestInput();

        for (int i = 0; i < items.size(); i++) {
            MenuOption bookList = items.get(i);
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
