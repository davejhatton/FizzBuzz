package org.fizz.presentation;

import java.io.PrintStream;

/**
 * Implementation of Facade displaying results via a PrintStream.
 */
public class PrintStreamPresentationFacade implements PresentationFacade{

    private final PrintStream printStream;

    public PrintStreamPresentationFacade(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void presentSingleResult(String result) {
        printStream.println(result);
    }
}
