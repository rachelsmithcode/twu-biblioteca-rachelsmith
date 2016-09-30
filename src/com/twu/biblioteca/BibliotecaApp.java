package com.twu.biblioteca;


import java.util.ArrayList;

public class BibliotecaApp {

    private Welcome welcome;

    private static final ArrayList<String> menuOptions = new ArrayList();
    private static final ArrayList<BookItem> bookItems = new ArrayList();

    public static void main (String[] args) {new BibliotecaApp().launch();}

    public BibliotecaApp() {welcome = new Welcome();}


    private void launch() {
        welcome.printWelcome();
        createBookItemList();
        createMenuOptionsList();
        Menu menu = new Menu(new Options(menuOptions, bookItems), new Input());
        menu.printMainOptions();
        while (true) {
            menu.menuAction();
        }
    }

    private static void createMenuOptionsList() {
        menuOptions.add("Book List");
        menuOptions.add("Checkout");
        menuOptions.add("Return");
        menuOptions.add("Quit");
    }

    private static void createBookItemList() {bookItems.add(createTestBookOne()); bookItems.add(createTestBookTwo());}

    private static BookItem createTestBookOne() {return new BookItem("Dune", "Frank Herbert", "1965");}

    private static BookItem createTestBookTwo() {return new BookItem("Gormengast", "Mervyn Peake", "1950");}




}