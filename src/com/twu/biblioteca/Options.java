package com.twu.biblioteca;

import java.util.ArrayList;

public class Options {

    public static ArrayList<String> optionsList;
    public static ArrayList<BookItem> bookList;
    public static ArrayList<MovieItem> movieList;

    public Options(ArrayList<String> options, ArrayList<BookItem> books, ArrayList<MovieItem> movies) {
        setOptionsList(options);
        setBookList(books);
        setMovieList(movies);

    }

    private void setOptionsList(ArrayList<String> options) {
        optionsList = options;
    }

    private void setBookList(ArrayList<BookItem> books) {
        bookList = books;
    }

    private void setMovieList(ArrayList<MovieItem> movies) {movieList = movies;}

    public void printOptions() {

        for (int i = 0; i < optionsList.size(); i++) {
            printToConsole(optionsList.get(i));
        }
    }

    public ArrayList<String> getOptions() {
        return optionsList;
    }

    private void printToConsole(String message) {
        System.out.println(message);
    }

    public void quit() {System.exit(1);}

    public void printBookList() {
        printColumnHeaders();
        ArrayList<BookItem> booksInStock = returnBooksInStock();
        for (int i = 0; i < booksInStock.size(); i++) {
            BookItem book = booksInStock.get(i);
                    book.printDetails();
        }

    }

    public void printMovieList() {
        printMovieColumnHeaders();
        ArrayList<MovieItem> moviesInStock = returnMoviesInStock();
        for (int i = 0; i < moviesInStock.size(); i++) {
            MovieItem movie = moviesInStock.get(i);
            movie.printDetails();
        }

    }

    public void checkoutBook(String input) {

        Boolean validBookChoice = false;
        ArrayList<BookItem> booksInStock = returnBooksInStock();

        for (int i = 0; i < booksInStock.size(); i++) {
            BookItem book = booksInStock.get(i);
            if (book.getTitle().equals(input)) {
                book.beCheckedOut();
                printToConsole("Thank you! Enjoy the book");
                validBookChoice = true;
                break;
            }
        }
        if (!validBookChoice) {
            printToConsole("That book is not available.");
        }

    }

    public void checkoutMovie(String input) {

        Boolean validMovieChoice = false;
        ArrayList<MovieItem> moviesInStock = returnMoviesInStock();

        for (int i = 0; i < moviesInStock.size(); i++) {
            MovieItem movie = moviesInStock.get(i);
            if (movie.getTitle().equals(input)) {
                movie.beCheckedOut();
                printToConsole("Thank you! Enjoy the movie");
                validMovieChoice = true;
                break;
            }
        }
        if (!validMovieChoice) {
            printToConsole("That movie is not available.");
        }

    }

    public void returnBook(String input) {
        Boolean validBookChoice = false;
        ArrayList<BookItem> booksOutOfStock = returnBooksOutOfStock();

        for (int i = 0; i < booksOutOfStock.size(); i++) {
            BookItem book = booksOutOfStock.get(i);
            if (book.getTitle().equals(input)) {
                book.beReturned();
                printToConsole("Thank you for returning the book.");
                validBookChoice = true;
                break;
            }
        }
        if (!validBookChoice) {
            printToConsole("That is not a valid book to return.");
        }

    }

    public void returnMovie(String input) {
        Boolean validMovieChoice = false;
        ArrayList<MovieItem> moviesOutOfStock = returnMoviesOutOfStock();

        for (int i = 0; i < moviesOutOfStock.size(); i++) {
            MovieItem movie = moviesOutOfStock.get(i);
            if (movie.getTitle().equals(input)) {
                movie.beReturned();
                printToConsole("Thank you for returning the movie.");
                validMovieChoice = true;
                break;
            }
        }
        if (!validMovieChoice) {
            printToConsole("That is not a valid movie to return.");
        }

    }

    private static ArrayList<BookItem> returnBooksInStock() {
        ArrayList<BookItem> booksInStock = new ArrayList<BookItem>();

        for (int i = 0; i < bookList.size(); i++) {
            BookItem book = bookList.get(i);
            if (book.isInStock()) {
                booksInStock.add(book);
            }
        }
        return booksInStock;


    }

    private static ArrayList<MovieItem> returnMoviesInStock() {
        ArrayList<MovieItem> moviesInStock = new ArrayList<MovieItem>();

        for (int i = 0; i < movieList.size(); i++) {
            MovieItem movie = movieList.get(i);
            if (movie.isInStock()) {
                moviesInStock.add(movie);
            }
        }
        return moviesInStock;


    }

    private static ArrayList<BookItem> returnBooksOutOfStock() {
        ArrayList<BookItem> booksOutOfStock = new ArrayList<BookItem>();

        for (int i = 0; i < bookList.size(); i++) {
            BookItem book = bookList.get(i);
            if (!book.isInStock()) {
                booksOutOfStock.add(book);
            }
        }
        return booksOutOfStock;

    }

    private static ArrayList<MovieItem> returnMoviesOutOfStock() {
        ArrayList<MovieItem> moviesOutOfStock = new ArrayList<MovieItem>();

        for (int i = 0; i < movieList.size(); i++) {
            MovieItem movie = movieList.get(i);
            if (!movie.isInStock()) {
                moviesOutOfStock.add(movie);
            }
        }
        return moviesOutOfStock;

    }

    private void printAsColumn(String title, String author, String year) {
        System.out.print(title + " | " + author + " | " + year + "\n");
    }

    private void printColumnHeaders() {

        printAsColumn("Title", "Author", "Year");
    }

    private void printAsMovieColumn(String title, String author, String year, String rating) {
        System.out.print(title + " | " + author + " | " + year + " | " + rating + "\n");
    }

    private void printMovieColumnHeaders() {

        printAsMovieColumn("Title", "Director", "Year", "Rating");
    }
}
