package org.fizz.strategy;

import java.util.function.IntPredicate;

public interface FizzBuzzNumberStringPredicates {

    IntPredicate matchesFizzNumberOrString = FizzBuzzNumberPredicates.matchesFizz.or(FizzBuzzStringPredicates.matchesFizz);
    IntPredicate matchesBuzzNumberOrString = FizzBuzzNumberPredicates.matchesBuzz.or(FizzBuzzStringPredicates.matchesBuzz);
    IntPredicate matchesFizzBuzzNumberOrString = matchesFizzNumberOrString.and(matchesBuzzNumberOrString);
}
