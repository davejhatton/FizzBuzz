package org.fizz.chain;

public interface NumberProcessor {

    String processNumber(int number);
    void setNextChain(NumberProcessor numberProcessor);
}
