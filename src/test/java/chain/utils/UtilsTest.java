package chain.utils;


import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static chain.utils.Utils.distance;
import static chain.utils.Utils.isInDictionary;
import static java.lang.Integer.MAX_VALUE;
import static org.junit.Assert.*;

public class UtilsTest {

    private static final Set<String> DICTIONARY = new HashSet<>();

    @Before
    public void init() {
        DICTIONARY.add("book");
        DICTIONARY.add("cook");
        DICTIONARY.add("cool");
        DICTIONARY.add("bool");
        DICTIONARY.add("boot");
        DICTIONARY.add("root");
    }

    @Test
    public void when_source_and_target_has_different_size_result_is_MAX_INT() {
        String source = "Source";
        String target = "DifferentThenSource";

        assertEquals(MAX_VALUE, distance(source, target));
    }

    @Test
    public void when_source_and_target_are_same_result_is_zero() {
        int expectedResult = 0;
        String source = "Source";
        String target = source;

        assertEquals(expectedResult, distance(source, target));
    }

    @Test
    public void when_source_and_target_are_different_but_have_same_size_result_is_number_of_different_chars_by_index() {
        int expectedResult = 6;
        String source = "source";
        String target = "target";

        assertEquals(expectedResult, distance(source, target));
    }

    @Test
    public void isInDictionary_should_return_true_when_dictionary_contains_word() {
        assertTrue(isInDictionary(DICTIONARY, "root"));
    }

    @Test
    public void isInDictionary_should_return_false_when_dictionary_does_not_contain_word() {
        assertFalse(isInDictionary(DICTIONARY, "notebook"));
    }
}
