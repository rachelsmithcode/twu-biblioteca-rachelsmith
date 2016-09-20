package com.twu.biblioteca;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class WelcomeTest {

    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Test
    public void printsAWelcomeMessage() throws Exception {

        final Console console = context.mock(Console.class);

        context.checking(new Expectations() {{
            oneOf (console).message("Welcome to the Biblioteca Experience");
        }});

        new Welcome().printWelcome(console);
    }
}
