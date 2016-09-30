package com.twu.biblioteca;


import java.util.ArrayList;

public class Menu {

    private static Options options;
    private static Input inputStream;
    private Boolean validSelection;


    public static String checkingOut = "Check Out A Book";
    public static String returningBooks = "Return A Book";

    public Menu(Options menuOptions, Input getInput) {
        options = menuOptions;
        inputStream = getInput;
    }

    public void printMainOptions() {

        options.printOptions();

    }

    private void printToConsole(String message) {
        System.out.println(message);
    }

    public void menuAction() {

        String input = requestInput();
        selectMenuOption(input);

    }
//
//    private void selectMenuOption(String input) {
//        Boolean validSelection = false;
//        ArrayList<String> optionsList = options.getOptions();
//        for (int i = 0; i < optionsList.size(); i++) {
//            String option = optionsList.get(i);
//            if (input.contains((i + 1).toString()) {
//                runOption(input);
//                validSelection = true;
//            }
//        }
//            if (!validSelection) {
//                printToConsole("Please select a valid item!\n");
//            }
//    }

    private void selectMenuOption(String input) {
        validSelection = false;
        checkIfQuit(input);
        checkIfPrintBookList(input);
        checkIfCheckOutItem(input);
        checkIfReturnItem(input);
        checkIfValidSelection();
    }

    private void checkIfPrintBookList(String input) {
        if (input.contains("List") || input.contains("list") ) {
            validSelection = true;
            options.printBookList();
        }
    }

    private void checkIfCheckOutItem(String input) {
        if (input.contains("Checkout") || input.contains("checkout")) {
            printToConsole("What is the title of the book you wish to check out?");
            String titleInput = requestInput();
            validSelection = true;
            options.checkoutBook(titleInput);
        }
    }

    private void checkIfReturnItem(String input) {
        if (input.contains("Return") || input.contains("return")) {
            printToConsole("What is the title of the book you wish to return?");
            String titleInput = requestInput();
            validSelection = true;
            options.returnBook(titleInput);
        }
    }

    private void checkIfQuit(String input) {
        if (input.contains("Quit") || input.contains("quit")) {
            validSelection = true;
            options.quit();
        }
    }

    private void checkIfValidSelection() {
        if (!validSelection) {
            printToConsole("Please select a valid item!\n");
        }
    }

    private String requestInput() {
        return inputStream.returnString(System.in);
    }


}
