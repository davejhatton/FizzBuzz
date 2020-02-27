package org.fizz;

import org.fizz.chain.ChainAssembly;
import org.fizz.presentation.PresentationFacade;

public class FizzBuzzRunner {

    private static final int GAME_SIZE = 100;
    private final PresentationFacade presentationFacade;
    private final ChainAssembly chainAssembly;

    public FizzBuzzRunner(PresentationFacade presentationFacade, ChainAssembly chainAssembly) {
        this.presentationFacade = presentationFacade;
        this.chainAssembly = chainAssembly;
    }

    public void playFizzBuzz() {

        for (int number = 1; number <= GAME_SIZE; number++) {
            presentationFacade.presentSingleResult(chainAssembly.callChain(number));
        }
    }

}
