package org.fizz;

import org.fizz.chain.ChainAssembly;
import org.fizz.chain.ChainAssemblyStageOne;
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
public class FizzBuzzRunnerStageOneTest {

    @Mock
    private PresentationFacade mockPresentationFacade;
    private ChainAssembly chainAssembly;
    private FizzBuzzRunner runner;

    @Before
    public void setup() {
        chainAssembly = new ChainAssemblyStageOne();
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
        Set<Integer> multiples = getNumberMultiplesUpToLimit(3, 100);
        multiples.removeAll(
                getNumberMultiplesExcludingMultiplierTwoUpToLimit(MatchValueEnum.FIZZ.getMatchValue(),
                        MatchValueEnum.BUZZ.getMatchValue(), 100));
        assertCorrectIndexesForDisplayValue(multiples, FIZZ_BUZZ.getDisplayValue());
    }

    /* Test our test utility method. */
    @Test
    public void shouldGetMultiplesOfThreeBelowThirty() {
        Set<Integer> multiples = getNumberMultiplesUpToLimit(3, 30);
        assertThat(multiples).containsExactly(3, 6, 9, 12, 15, 18, 21, 24, 27, 30);
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
        Set<Integer> multiplesOfOne = getNumberMultiplesUpToLimit(multiplierOne, limit);
        Set<Integer> multiplesOfTwo = getNumberMultiplesUpToLimit(multiplierTwo, limit);
        multiplesOfOne.removeAll(multiplesOfTwo);
        return multiplesOfOne;
    }

    private Set<Integer> getNumberMultiplesUpToLimit(int multiplier, int limit) {
        Set<Integer> multiples = new LinkedHashSet<>();
        int counter = 2;
        int multiple = multiplier;

        while (multiple <= limit) {
            multiples.add(multiple);
            multiple = counter * multiplier;
            counter++;
        }
        return multiples;
    }
}