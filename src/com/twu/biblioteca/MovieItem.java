package com.twu.biblioteca;



public class MovieItem {

    private String itemTitle;
    private String itemDirector;
    private String itemYear;
    private String itemRating;
    private Boolean inStock = true;

    public MovieItem(String title, String director, String year, String rating) {
        setTitle(title);
        setDirector(director);
        setYear(year);
        setRating(rating);
    }

    public void printDetails() {
        printAsColumn(getTitle(), getDirector(), getYear(), getRaiting());
    }

    private void setTitle(String title) {
        itemTitle = title;
    }

    private void setDirector(String author) {
        itemDirector = author;
    }

    private void setYear(String year) {
        itemYear = year;
    }

    private void setRating(String rating) {
        itemRating = rating;
    }

    public String getTitle() {
        return itemTitle;
    }

    public String getDirector() {
        return itemDirector;
    }

    public String getYear() {
        return itemYear;
    }

    public String getRaiting() {
        return itemRating;
    }

    public void beCheckedOut() {
        inStock = false;
    }

    public void beReturned() {
        inStock = true;
    }

    public Boolean isInStock() {
        return inStock;
    }

    private void printAsColumn(String title, String director, String year, String rating) {
        System.out.print(title + " | " + director + " | " + year + " | " + rating + "\n");
    }

}
