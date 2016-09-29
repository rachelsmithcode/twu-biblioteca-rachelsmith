package com.twu.biblioteca;


import java.util.ArrayList;

public class Menu extends ConsoleObject {

    ArrayList<MenuOption> items;
    GetInput getInput;
    Boolean validEntry = false;

    public static String checkingOut = "Check Out A Book";
    public static String returningBooks = "Return A Book";

    public Menu(ArrayList<MenuOption> list) {
        items = list;
    }

    public void printList() {

        for (int i = 0; i < items.size(); i++) {
            MenuOption item = items.get(i);
            item.printName();
        }
        printToConsole(checkingOut);
        printToConsole(returningBooks);
    }


    public void menuAction(GetInput getInput) {


        saveGetInput(getInput);
        String input = requestInput();
        validEntry = false;

        selectMenuOption(input);
        checkIfReturnItem(input);
        checkIfCheckOutItem(input);
        checkIfValidEntry();
    }

    private void selectMenuOption(String input) {
        for (int i = 0; i < items.size(); i++) {
            MenuOption item = items.get(i);
            String itemName = item.returnName();
            if (input.equalsIgnoreCase(itemName)) {
                item.select();
                validEntry = true;
                break;
            }
        }
    }

    private void checkIfReturnItem(String input) {
        if (input.equalsIgnoreCase(returningBooks)) {
            validEntry = true;
            printToConsole("What is the title of the book you wish to return?");
            returnItem(getInput);
        }
    }

    private void checkIfCheckOutItem(String input) {
        if (input.equalsIgnoreCase(checkingOut)) {
            validEntry = true;
            printToConsole("What is the title of the book you wish to check out?");
            checkOutItem(getInput);
        }
    }

    private void checkIfValidEntry() {
        if (!validEntry) {
            printToConsole("Please select a valid item!\n");
            printList();
        }
    }

    public void checkOutItem(GetInput getInput) {


        saveGetInput(getInput);
        String input = requestInput();

        for (int i = 0; i < items.size(); i++) {
            MenuOption bookList = items.get(i);
            String itemName = bookList.returnName();
            if ("Book List".equalsIgnoreCase(itemName)) {
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
            if ("Book List".equalsIgnoreCase(itemName)) {
                bookList.returnBook(input);
                break;
            }
        }
    }

    private String requestInput() {
        return getInput.returnString(System.in);
    }

    private void saveGetInput(GetInput getIn) {
        getInput = getIn;
    }

}
