package chain.utils;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ChainMakerTest {

    private static final String NON_EMPTY_DICTIONARY_PATH = "src/test/resources/dictionary.txt";
    private static final String EMPTY_DICTIONARY_PATH = "src/test/resources/empty.txt";

    @Test
    public void if_dictionary_is_empty_chain_must_be_empty() {
        Set<String> words = FileReader.read(EMPTY_DICTIONARY_PATH);
        String source = "source";
        String target = "target";

        assertTrue(words.isEmpty());
        assertTrue(ChainMaker.create(source, target, words).isEmpty());
    }

    @Test
    public void chain_must_be_empty_when_target_is_not_in_dictionary() {
        Set<String> words = FileReader.read(NON_EMPTY_DICTIONARY_PATH);
        String source = "source";
        String target = "khinkali"; // Georgian traditional dish

        assertFalse(words.contains(target));
        assertTrue(ChainMaker.create(source, target, words).isEmpty());
    }

    @Test
    public void chain_must_be_empty_when_source_is_not_in_dictionary() {
        Set<String> words = FileReader.read(NON_EMPTY_DICTIONARY_PATH);
        String source = "churchkhela"; // Georgian traditional sweet
        String target = "gold";

        assertFalse(words.contains(source));
        assertTrue(ChainMaker.create(source, target, words).isEmpty());
    }

    @Test
    public void chain_must_not_be_empty_when_target_is_in_dictionary() {
        Set<String> words = FileReader.read(NON_EMPTY_DICTIONARY_PATH);
        String source = "lead";
        String target = "gold";

        assertTrue(words.contains(target));
        assertFalse(ChainMaker.create(source, target, words).isEmpty());
    }

    @Test
    public void chain_size_must_same_when_in_both_way_chaining() {
        Set<String> words = FileReader.read(NON_EMPTY_DICTIONARY_PATH);
        String source = "lead";
        String target = "gold";

        assertTrue(words.contains(target));

        List<String> leadToGoldChain = ChainMaker.create(source, target, words);
        List<String> goldToLeadChain = ChainMaker.create(target, source, words);

        assertEquals(leadToGoldChain.size(), goldToLeadChain.size());
    }

}
