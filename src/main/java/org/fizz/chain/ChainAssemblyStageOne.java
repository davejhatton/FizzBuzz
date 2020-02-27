package org.fizz.chain;

import org.fizz.DisplayValueEnum;
import org.fizz.strategy.FizzBuzzNumberPredicates;

public class ChainAssemblyStageOne implements ChainAssembly {

    private final NumberProcessor numberProcessorChain;

    public ChainAssemblyStageOne() {
        this.numberProcessorChain = assembleChain();
    }

    public String callChain(int number) {
        return numberProcessorChain.processNumber(number);
    }

    public NumberProcessor assembleChain() {
        NumberProcessor fizzBuzzLink
                = new NumberProcessorChainComponent(FizzBuzzNumberPredicates.matchesFizzBuzz, DisplayValueEnum.FIZZ_BUZZ.getDisplayValue());
        NumberProcessor fizzLink
                = new NumberProcessorChainComponent(FizzBuzzNumberPredicates.matchesFizz, DisplayValueEnum.FIZZ.getDisplayValue());
        NumberProcessor buzzLink
                = new NumberProcessorChainComponent(FizzBuzzNumberPredicates.matchesBuzz, DisplayValueEnum.BUZZ.getDisplayValue());
        NumberProcessor noOpLink
                = new NumberProcessorChainComponent(null, null);
        buzzLink.setNextChain(noOpLink);
        fizzLink.setNextChain(buzzLink);
        fizzBuzzLink.setNextChain(fizzLink);
        return fizzBuzzLink;
    }
}
