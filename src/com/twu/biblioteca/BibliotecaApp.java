package com.twu.biblioteca;


import java.util.ArrayList;

public class BibliotecaApp {

    private Welcome welcome;
    private GetInput getInput;

    ArrayList<MenuItem> menuItems = new ArrayList();
    ArrayList<BookItem> bookItems = new ArrayList();

    public void main(String[] args) {
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
        MenuList menulist = new MenuList(menuItems);
        menulist.printList();
        menulist.selectItem(getInput);

    }

    private void createMenuItemList() {
        menuItems.add(new Quit());
        menuItems.add(new BookList(bookItems));
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