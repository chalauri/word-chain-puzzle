package chain;

import chain.utils.ChainMaker;
import chain.utils.FileReader;
import chain.utils.Utils;

public class Main {

    private static final String DICTIONARY_PATH = "src/main/resources/wordlist.txt";

    private static final String SOURCE = "gold";
    private static final String DESTINATION = "lead";

    public static void main(String[] args) {

        System.out.println(ChainMaker.create(DESTINATION, SOURCE, FileReader.read(DICTIONARY_PATH)));
    }
}
