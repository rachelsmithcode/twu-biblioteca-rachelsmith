package com.twu.biblioteca;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

public class MenuDisplayTest {

    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};


    @Test
    public void printsMenuOptions() throws Exception {

        final MenuItem menuItem = context.mock(MenuItem.class);
        final Console console = context.mock(Console.class);
        final BookList bookList = context.mock(BookList.class);
        final GetInput getInput = context.mock(GetInput.class);


        context.checking(new Expectations() {{
            oneOf(menuItem).printDescription();
        }});

        new MenuDisplay(console).printItems(menuItem);
    }

    @Test
    public void acceptsBookListAsARequest() throws Exception {

        final GetInput getInput = context.mock(GetInput.class);
        final Console console = context.mock(Console.class);
        final BookList bookList = context.mock(BookList.class);

        context.checking(new Expectations() {{

            oneOf(getInput).returnString(); will(returnValue("Book List"));
            oneOf(bookList).printBooks();

        }});

        new MenuDisplay(console).selectItem(getInput, bookList);
    }

    @Test
    public void returnsErrorMessageToAnyOtherStringRequest() throws Exception {

        final GetInput getInput = context.mock(GetInput.class);
        final Console console = context.mock(Console.class);
        final BookList bookList = context.mock(BookList.class);

        context.checking(new Expectations() {{

            oneOf(getInput).returnString(); will(returnValue("I am a cat"));
            oneOf(console).message("Please select a valid item!");
            oneOf(getInput).returnString(); will(returnValue("Book List"));
            oneOf(bookList).printBooks();

        }});

        new MenuDisplay(console).selectItem(getInput, bookList);
    }

}
