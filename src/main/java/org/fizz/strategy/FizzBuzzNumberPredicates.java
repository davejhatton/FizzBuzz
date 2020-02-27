package org.fizz.strategy;

import java.util.function.IntPredicate;

import static org.fizz.MatchValueEnum.BUZZ;
import static org.fizz.MatchValueEnum.FIZZ;

public interface FizzBuzzNumberPredicates {

    IntPredicate matchesFizz = (number) -> number % FIZZ.getMatchValue() == 0;
    IntPredicate matchesBuzz = (number) -> number % BUZZ.getMatchValue() == 0;
    IntPredicate matchesFizzBuzz = (number) -> matchesFizz.and(matchesBuzz).test(number);
}
