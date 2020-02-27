package org.fizz.chain;

import java.util.Objects;
import java.util.function.IntPredicate;

public class NumberProcessorChainComponent implements NumberProcessor {

    private final IntPredicate matchPredicate;
    private final String responseValue;
    private NumberProcessor nextProcessor = null;


    public NumberProcessorChainComponent(IntPredicate matchPredicate, String responseValue) {
        this.matchPredicate = matchPredicate;
        this.responseValue = responseValue;
    }

    @Override
    public void setNextChain(NumberProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public String processNumber(int number) {

        if (Objects.nonNull(matchPredicate) && matchPredicate.test(number)) {
            return responseValue;
        } else if (Objects.nonNull(nextProcessor)) {
            return nextProcessor.processNumber(number);
        } else {
            return String.valueOf(number);
        }
    }
}
