package com.twu.biblioteca;

public class Menu {

    private static Options options;
    private static Input inputStream;
    private Boolean validSelection;

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

    private void selectMenuOption(String input) {
        validSelection = false;
        checkIfPrintBookList(input);
        checkIfCheckOutItem(input);
        checkIfReturnItem(input);
        checkIfQuit(input);
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
