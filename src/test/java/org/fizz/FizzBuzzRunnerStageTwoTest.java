package org.fizz;

import org.fizz.chain.ChainAssembly;
import org.fizz.chain.ChainAssemblyStageTwo;
import org.fizz.presentation.PresentationFacade;
import org.fizz.presentation.PrintStreamPresentationFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fizz.DisplayValueEnum.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FizzBuzzRunnerStageTwoTest {

    @Mock
    private PresentationFacade mockPresentationFacade;
    private ChainAssembly chainAssembly;
    private FizzBuzzRunner runner;

    @Before
    public void setup() {
        chainAssembly = new ChainAssemblyStageTwo();
        runner = new FizzBuzzRunner(mockPresentationFacade, chainAssembly);
    }

    @After
    public void teardown() {
        runner = null;
        chainAssembly = null;
    }

    @Test
    public void whenPlayed_thenDisplaysFizzInCorrectIndexes() {
        Set<Integer> multiples
                = getNumberMultiplesExcludingMultiplierTwoUpToLimit(MatchValueEnum.FIZZ.getMatchValue(),
                MatchValueEnum.BUZZ.getMatchValue(), 100);
        assertCorrectIndexesForDisplayValue(multiples, FIZZ.getDisplayValue());
    }

    @Test
    public void whenPlayed_thenDisplaysBuzzInCorrectIndexes() {
        Set<Integer> multiples
                = getNumberMultiplesExcludingMultiplierTwoUpToLimit(MatchValueEnum.BUZZ.getMatchValue(),
                MatchValueEnum.FIZZ.getMatchValue(), 100);
        assertCorrectIndexesForDisplayValue(multiples, BUZZ.getDisplayValue());
    }

    @Test
    public void whenPlayed_thenDisplaysFizzBuzzInCorrectIndexes() {
        Set<Integer> multiples = getIndexesForMultipleAndCharacterUpToLimit(3, 100);
        multiples.removeAll(
                getNumberMultiplesExcludingMultiplierTwoUpToLimit(MatchValueEnum.FIZZ.getMatchValue(),
                        MatchValueEnum.BUZZ.getMatchValue(), 100));
        assertCorrectIndexesForDisplayValue(multiples, FIZZ_BUZZ.getDisplayValue());
    }

    /* Test our test utility method. */
    @Test
    public void shouldGetMultiplesOfThreeBelowThirty() {
        Set<Integer> multiples = getIndexesForMultipleAndCharacterUpToLimit(3, 30);
        assertThat(multiples).containsExactly(3, 6, 9, 12, 13, 15, 18, 21, 23, 24, 27, 30);
    }

    @Test
    public void runPlayerForDisplay() {
        runner = new FizzBuzzRunner(new PrintStreamPresentationFacade(System.out), chainAssembly);
        runner.playFizzBuzz();
    }

    private void assertCorrectIndexesForDisplayValue(Set<Integer> indexes, String displayValue) {
        ArgumentCaptor<String> displayValueCaptor = ArgumentCaptor.forClass(String.class);

        runner.playFizzBuzz();

        verify(mockPresentationFacade, times(100)).presentSingleResult(displayValueCaptor.capture());
        List<String> displayValues = displayValueCaptor.getAllValues();
        indexes.forEach((number) -> assertThat(displayValues.get(number - 1)).isEqualTo(displayValue));
    }

    /* Returns multiples of multiplierOne, excluding those that are also multiples of multiplierTwo. */
    private Set<Integer> getNumberMultiplesExcludingMultiplierTwoUpToLimit(int multiplierOne, int multiplierTwo, int limit) {
        Set<Integer> multiplesOfOne = getIndexesForMultipleAndCharacterUpToLimit(multiplierOne, limit);
        Set<Integer> multiplesOfTwo = getIndexesForMultipleAndCharacterUpToLimit(multiplierTwo, limit);
        multiplesOfOne.removeAll(multiplesOfTwo);
        return multiplesOfOne;
    }

    private Set<Integer> getIndexesForMultipleAndCharacterUpToLimit(int multiplier, int limit) {
        Set<Integer> indexes = new LinkedHashSet<>();
        String multiplierCharacter = String.valueOf(multiplier);
        int counter = 1;

        while (counter <= limit) {
            if (counter % multiplier == 0) {
                indexes.add(counter);
            }

            if (String.valueOf(counter).contains(multiplierCharacter)) {
                indexes.add(counter);
            }

            counter++;
        }

        return indexes;
    }
}
