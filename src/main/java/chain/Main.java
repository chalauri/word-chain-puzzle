package chain;

import chain.utils.ChainMaker;
import chain.utils.FileReader;
import chain.utils.Utils;

public class Main {

    private static final String DICTIONARY_PATH = "src/main/resources/wordlist.txt";

    private static final String SOURCE = "gold";
    private static final String DESTINATION = "lead";

    public static void main(String[] args) {
        ChainMaker chainMaker = new ChainMaker();
        FileReader reader = new FileReader();

        System.out.println(chainMaker.getShortestChain(DESTINATION, SOURCE, reader.read(DICTIONARY_PATH)));
    }
}
