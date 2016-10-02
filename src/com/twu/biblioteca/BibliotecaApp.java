package com.twu.biblioteca;


import java.util.ArrayList;

public class BibliotecaApp {

    private WelcomeScreen welcomeScreen;

    private static final ArrayList<String> menuOptions = new ArrayList();
    private static final ArrayList<BookItem> bookItems = new ArrayList();
    private static final ArrayList<MovieItem> movieItems = new ArrayList();
    private static final ArrayList<User> userList = new ArrayList();

    public static void main (String[] args) {new BibliotecaApp().launch();}

    public BibliotecaApp() {
        createUserList();
        Input input = new Input();
        welcomeScreen = new WelcomeScreen(input, userList);
    }


    private void launch() {
        welcomeScreen.requestLogin();
        User sessionUser = welcomeScreen.returnSessionUser();
        if (sessionUser != null) {
            mainMenu(sessionUser);
        } else {
            welcomeScreen.requestLogin();
        }
    }

    private void mainMenu(User sessionUser) {
            createBookItemList();
            createMovieItemList();
            createMenuOptionsList();
            Menu menu = new Menu(new Options(menuOptions, bookItems, movieItems, sessionUser), new Input());
            menu.printMainOptions();
            while (true) {
                menu.menuAction();
            }
    }

    private static void createMenuOptionsList() {
        menuOptions.add("User Details");
        menuOptions.add("Book List");
        menuOptions.add("Movie List");
        menuOptions.add("Checkout");
        menuOptions.add("Return");
        menuOptions.add("Quit");
    }

    private static void createBookItemList() {bookItems.add(createTestBookOne()); bookItems.add(createTestBookTwo());}

    private static BookItem createTestBookOne() {return new BookItem("Dune", "Frank Herbert", "1965");}

    private static BookItem createTestBookTwo() {return new BookItem("Gormengast", "Mervyn Peake", "1950");}

    private static void createMovieItemList() {movieItems.add(createTestMovieOne()); movieItems.add(createTestMovieTwo());}

    private static MovieItem createTestMovieOne() {return new MovieItem("Die Hard", "Frank Herbert", "1965", "5");}

    private static MovieItem createTestMovieTwo() {return new MovieItem("Dark Crystal", "Mervyn Peake", "1950", "5");}

    private static void createUserList() {userList.add(createTestUserOne()); userList.add(createTestUserTwo());}

    private static User createTestUserOne() {return new User("123-1234", "IAmAPassWord", "Rachel Smith", "rachel@email.com", "020-0-000-0000");}

    private static User createTestUserTwo() {return new User("123-1235", "IAmAnotherPassWord", "Cat Smith", "cat@email.com", "020-0-000-0000");}

}