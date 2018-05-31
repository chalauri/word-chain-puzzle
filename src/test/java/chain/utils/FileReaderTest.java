package chain.utils;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileReaderTest {

    private static final String CORRECT_PATH = "src/test/resources/words.txt";
    private static final String INCORRECT_PATH = "src/test/resources/incorrect.txt";
    private static final String EMPTY_FILE_PATH = "src/test/resources/empty.txt";

    @Test
    public void read_should_return_empty_when_file_path_is_incorrect() {
        FileReader.read(INCORRECT_PATH);
    }

    @Test
    public void read_should_return_empty_collection_when_file_is_empty() {
        assertTrue(FileReader.read(EMPTY_FILE_PATH).isEmpty());
    }

    @Test
    public void read_should_not_return_empty_collection_when_file_is_not_empty() {
        assertFalse(FileReader.read(CORRECT_PATH).isEmpty());
    }
}
