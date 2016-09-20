package com.twu.biblioteca;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

/**
 * Created by RachelSmith on 19/09/2016.
 */
public class MenuItemTest {

    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Test
    public void printsAWelcomeMessage() throws Exception {

        final Console console = context.mock(Console.class);

        context.checking(new Expectations() {{
            oneOf (console).message("Book List");
        }});

        new MenuItem(console, "Book List").printDescription();
    }
}

