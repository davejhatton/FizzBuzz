package org.fizz.chain;


import org.fizz.strategy.FizzBuzzNumberPredicates;
import org.fizz.strategy.FizzBuzzNumberStringPredicates;
import org.fizz.strategy.FizzBuzzStringPredicates;

import static org.fizz.DisplayValueEnum.*;

public class ChainAssemblyStageTwo extends ChainAssemblyStageOne implements ChainAssembly {

    public ChainAssemblyStageTwo() {
        super();
    }

    @Override
    public NumberProcessor assembleChain() {

        NumberProcessor fizzBuzzLink
                = new NumberProcessorChainComponent(FizzBuzzNumberStringPredicates.matchesFizzBuzzNumberOrString, FIZZ_BUZZ.getDisplayValue());
        NumberProcessor fizzLink
                = new NumberProcessorChainComponent(FizzBuzzNumberPredicates.matchesFizz.or(FizzBuzzStringPredicates.matchesFizz), FIZZ.getDisplayValue());
        NumberProcessor buzzLink
                = new NumberProcessorChainComponent(FizzBuzzNumberPredicates.matchesBuzz.or(FizzBuzzStringPredicates.matchesBuzz), BUZZ.getDisplayValue());
        NumberProcessor noOpLink
                = new NumberProcessorChainComponent(null, null);
        buzzLink.setNextChain(noOpLink);
        fizzLink.setNextChain(buzzLink);
        fizzBuzzLink.setNextChain(fizzLink);
        return fizzBuzzLink;
    }
}
