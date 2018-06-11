package chain.utils;


import java.util.Collection;

public class Utils {

    public static int distance(String source, String target) {
        if (source.length() != target.length()) {
            return Integer.MAX_VALUE;
        }

        int result = 0;

        for (int index = 0; index < source.length(); index++) {
            if (source.charAt(index) != target.charAt(index)) {
                result++;
            }
        }

        return result;
    }

    public static boolean isInDictionary(Collection<String> dictionary, String word) {
        return dictionary.contains(word);
    }

}
