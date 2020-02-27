package org.fizz.strategy;

import java.util.function.IntPredicate;

import static org.fizz.MatchValueEnum.BUZZ;
import static org.fizz.MatchValueEnum.FIZZ;

public interface FizzBuzzStringPredicates {

    IntPredicate matchesFizz = (number) -> String.valueOf(number).contains(FIZZ.getMatchValueCharacter());
    IntPredicate matchesBuzz = (number) -> String.valueOf(number).contains(BUZZ.getMatchValueCharacter());
}
