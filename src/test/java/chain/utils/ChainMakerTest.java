package chain.utils;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ChainMakerTest {

    private static final String NON_EMPTY_DICTIONARY_PATH = "src/test/resources/dictionary.txt";
    private static final String EMPTY_DICTIONARY_PATH = "src/test/resources/empty.txt";

    private ChainMaker chainMaker;
    private FileReader reader;

    @Before
    public void init(){
        chainMaker = new ChainMaker();
        reader = new FileReader();
    }

    @Test
    public void if_dictionary_is_empty_chain_must_be_empty() {
        Set<String> words = reader.read(EMPTY_DICTIONARY_PATH);
        String source = "source";
        String target = "target";

        assertTrue(words.isEmpty());
        assertTrue(chainMaker.getShortestChain(source, target, words).isEmpty());
    }

    @Test
    public void chain_must_be_empty_when_target_is_not_in_dictionary() {
        Set<String> words = reader.read(NON_EMPTY_DICTIONARY_PATH);
        String source = "source";
        String target = "khinkali"; // Georgian traditional dish

        assertFalse(words.contains(target));
        assertTrue(chainMaker.getShortestChain(source, target, words).isEmpty());
    }

    @Test
    public void chain_must_be_empty_when_source_is_not_in_dictionary() {
        Set<String> words = reader.read(NON_EMPTY_DICTIONARY_PATH);
        String source = "churchkhela"; // Georgian traditional sweet
        String target = "gold";

        assertFalse(words.contains(source));
        assertTrue(chainMaker.getShortestChain(source, target, words).isEmpty());
    }

    @Test
    public void chain_must_not_be_empty_when_target_is_in_dictionary() {
        Set<String> words = reader.read(NON_EMPTY_DICTIONARY_PATH);
        String source = "lead";
        String target = "gold";

        assertTrue(words.contains(target));
        assertFalse(chainMaker.getShortestChain(source, target, words).isEmpty());
    }

    @Test
    public void chain_size_must_same_when_in_both_way_chaining() {
        Set<String> words = reader.read(NON_EMPTY_DICTIONARY_PATH);
        String source = "lead";
        String target = "gold";

        assertTrue(words.contains(target));

        List<String> leadToGoldChain = chainMaker.getShortestChain(source, target, words);
        List<String> goldToLeadChain = chainMaker.getShortestChain(target, source, words);

        assertEquals(leadToGoldChain.size(), goldToLeadChain.size());
    }

    @Test
    public void chains_must_contain_exact_words_when_there_are_more_more_than_one_chain() {
        Set<String> words = reader.read(NON_EMPTY_DICTIONARY_PATH);
        String source = "travel";
        String target = "carpet";

        List<List<String>> allChainsFromTravelToCarpet = chainMaker.getAllChains(source, target, words);
        List<List<String>> allChainsFromCarpetToTravel = chainMaker.getAllChains(target, source, words);

        assertThat(allChainsFromCarpetToTravel.size(), Matchers.greaterThan(1));
        assertThat(allChainsFromTravelToCarpet.size(), Matchers.greaterThan(1));

        assertEquals(allChainsFromCarpetToTravel.size(), allChainsFromTravelToCarpet.size());
    }

    @Test
    public void difference_between_each_word_in_chain_must_be_one_letter() {
        Set<String> words = reader.read(NON_EMPTY_DICTIONARY_PATH);
        String source = "travel";
        String target = "carpet";

        List<String> fromTravelToCarpet = chainMaker.getShortestChain(source, target, words);
        List<String> fromCarpetToTravel = chainMaker.getShortestChain(target, source, words);

        assertEquals(fromCarpetToTravel.size(), fromTravelToCarpet.size());

        for (int i = 1; i < fromCarpetToTravel.size(); i++) {
            int distanceCarpetToTravel = Utils.distance(fromCarpetToTravel.get(i - 1), fromCarpetToTravel.get(i));
            int distanceTravelToCarpet = Utils.distance(fromTravelToCarpet.get(i - 1), fromTravelToCarpet.get(i));
            assertEquals(1, distanceCarpetToTravel);
            assertEquals(1, distanceTravelToCarpet);
        }
    }

}
