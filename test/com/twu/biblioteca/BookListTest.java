package com.twu.biblioteca;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;


public class BookListTest {

    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Test
    public void printsAListOfTwoBooks() throws Exception {

        final Book book = context.mock(Book.class);
        BookList firstBookList = new BookList();

        context.checking(new Expectations() {{
            exactly(2).of (book).printInfo();
        }});

        firstBookList.addBookToBookList(book);
        firstBookList.addBookToBookList(book);
        firstBookList.printBooks();

    }

}
