package chain.utils;

import java.util.*;
import java.util.stream.Collectors;

import static chain.utils.Utils.distance;
import static chain.utils.Utils.isInDictionary;

public class ChainMaker {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /*
    This algorithm works in case-sensitive manner.
     */
    public static List<String> create(String source, String target, Set<String> dictionary) {

        if (!isInDictionary(dictionary, source) || !isInDictionary(dictionary, target)) {
            return Collections.emptyList();
        }

        List<String> candidates = new ArrayList<>();
        candidates.add(source);

        List<String> visited = new ArrayList<>();
        visited.add(source);

        List<String> chain = new ArrayList<>();

        Iterator<String> candidateIterator = candidates.iterator();
        while (candidateIterator.hasNext()) {
            String lastCandidate = candidates.remove(candidates.size() - 1);
            chain.add(lastCandidate);
            candidateIterator = candidates.iterator();
            if (lastCandidate.equals(target)) {
                return chain;
            }

            List<String> viableCandidates = getViableCandidatesForNextNode(
                    getAllCandidatesForNextNode(lastCandidate, target, dictionary),
                    target, distance(lastCandidate, target),
                    candidates, visited);

            if (viableCandidates.isEmpty()) {
                chain.remove(chain.size() - 1);
            } else {
                candidates.addAll(viableCandidates);
                visited.addAll(viableCandidates);
            }
        }

        return new ArrayList<>();
    }

    private static List<String> getViableCandidatesForNextNode(Collection<String> candidates, String target,
                                                               int distanceToTarget,
                                                               Collection<String> chain,
                                                               Collection<String> visited) {

        return candidates.stream()
                .filter(candidate -> !visited.contains(candidate))
                .filter(candidate -> !chain.contains(candidate))
                .filter(candidate -> {
                    int distance = distance(candidate, target);
                    return distance <= distanceToTarget;
                }).sorted((candidate1, candidate2) -> {
                    int candidateFirstDistance = distance(candidate1, target);
                    int candidateSecondDistance = distance(candidate2, target);
                    return Integer.compare(candidateSecondDistance, candidateFirstDistance);
                }).collect(Collectors.toList());
    }

    private static Set<String> getAllCandidatesForNextNode(String source, String target, Set<String> dictionary) {
        Set<String> candidates = new HashSet<>();
        for (int index = 0; index < source.length(); index++) {
            if (source.charAt(index) != target.charAt(index)) {
                for (int letter = 0; letter < ALPHABET.length(); letter++) {
                    StringBuilder builder = new StringBuilder(source);
                    builder.setCharAt(index, ALPHABET.charAt(letter));
                    if (isInDictionary(dictionary, builder.toString())) {
                        candidates.add(builder.toString());
                    }
                }
            }
        }

        return candidates;
    }
}
