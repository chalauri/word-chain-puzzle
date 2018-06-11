package chain.utils;

import java.util.*;

public class ChainMaker {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public List<String> getShortestChain(String source, String destination, Set<String> dictionary) {

        if (dictionary.isEmpty()) {
            return Collections.emptyList();
        }

        if (!dictionary.contains(source) || !dictionary.contains(destination)) {
            return Collections.emptyList();
        }

        List<List<String>> allChains = getAllChains(source, destination, dictionary);

        Optional<List<String>> shortestChainOptional = allChains.stream().sorted(Comparator.comparing(List::size)).findFirst();

        return shortestChainOptional.orElse(Collections.emptyList());
    }

    public List<List<String>> getAllChains(String source, String destination, Set<String> dictionary) {
        List<List<String>> allChains = new ArrayList<>();
        HashMap<String, ArrayList<String>> neighbours = new HashMap<>();
        HashMap<String, Integer> distance = new HashMap<>();
        ArrayList<String> solution = new ArrayList<>();

        initializeNextLinksForWordAndDistanceFromSource(source, destination, dictionary, neighbours, distance);
        findAllChainsForWord(source, destination, neighbours, distance, solution, allChains);

        return allChains;
    }

    private void initializeNextLinksForWordAndDistanceFromSource(String source, String target, Set<String> dictionary,
                                                                 HashMap<String, ArrayList<String>> neighbours,
                                                                 HashMap<String, Integer> distance) {

        dictionary.forEach(word -> neighbours.put(word, new ArrayList<>()));

        Queue<String> queue = new LinkedList<>();
        queue.offer(source);
        distance.put(source, 0); // distance from source to source is 0
        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean foundDestination = false;

            for (int i = 0; i < count; i++) {
                String firstWordFromQueue = queue.poll();
                ArrayList<String> currentNeighbours = getNeighbours(firstWordFromQueue, dictionary);

                for (String neighbour : currentNeighbours) {
                    neighbours.get(firstWordFromQueue).add(neighbour);
                    if (!distance.containsKey(neighbour)) {
                        distance.put(neighbour, distance.get(firstWordFromQueue) + 1);
                        if (target.equals(neighbour)) {
                            foundDestination = true;
                        } else {
                            queue.offer(neighbour);
                        }
                    }
                }
            }
            if (foundDestination) break;
        }

    }

    private void findAllChainsForWord(String currentWord, String destination, HashMap<String, ArrayList<String>> neighbours,
                                      HashMap<String, Integer> distance, ArrayList<String> chain, List<List<String>> result) {
        chain.add(currentWord);
        if (currentWord.equals(destination)) {
            // Recursion stop condition
            result.add(new ArrayList<>(chain));
        } else {
            for (String next : neighbours.get(currentWord)) {
                if (distance.get(next) == distance.get(currentWord) + 1)
                    findAllChainsForWord(next, destination, neighbours, distance, chain, result);
            }
        }
        chain.remove(chain.size() - 1);
    }

    private ArrayList<String> getNeighbours(String word, Set<String> dictionary) {
        ArrayList<String> neighbours = new ArrayList<>();
        char chars[] = word.toCharArray();
        for (char letter : ALPHABET.toCharArray()) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == letter) continue; // if same letter - change should not perform
                char oldLetter = chars[i];
                chars[i] = letter;
                if (dictionary.contains(String.valueOf(chars)))
                    neighbours.add(String.valueOf(chars));
                chars[i] = oldLetter; // reset the char
            }
        }
        return neighbours;
    }

}
