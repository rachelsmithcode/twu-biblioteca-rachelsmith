package com.twu.biblioteca;


import java.util.ArrayList;

public class BibliotecaApp {

    private Welcome welcome;
    private GetInput getInput;

    ArrayList<MenuOption> menuOptions = new ArrayList();
    ArrayList<BookItem> bookItems = new ArrayList();

    public static void main (String[] args) {
        new BibliotecaApp(new Welcome(), new GetInput()).launch();
    }

    public BibliotecaApp(Welcome wel, GetInput input) {
        welcome = wel;
        getInput = input;
    }


    private void launch() {
        welcome.printWelcome();
        createBookItemList();
        createMenuItemList();
        Menu menulist = new Menu(menuOptions);
        menulist.printList();
        while (true) {
            menulist.menuAction(getInput);
        }
    }

    private void createMenuItemList() {
        menuOptions.add(new BookList(bookItems));
        menuOptions.add(new Quit());
    }

    private void createBookItemList() {
        bookItems.add(createTestBookOne());
        bookItems.add(createTestBookTwo());
    }

    private BookItem createTestBookOne() {
        return new BookItem("Dune", "Frank Herbert", "1965");
    }

    private BookItem createTestBookTwo() {
        return new BookItem("Gormengast", "Mervyn Peake", "1950");
    }




}