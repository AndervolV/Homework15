package org.skypro.skyshop.search;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
    private final List<Searchable> searchables = new ArrayList<>();

    public void add(Searchable searchable) {
        if (searchable != null) {
            searchables.add(searchable);
        }
    }

    public Map<String, Searchable> search(String query) {
        if (query == null || query.isBlank()) {
            return Collections.emptyMap();
        }

        String queryLower = query.toLowerCase();

        return searchables.stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getSearchTerm().toLowerCase().contains(queryLower))
                .sorted(Comparator.comparing(Searchable::getName))
                .collect(Collectors.toMap(
                        Searchable::getName,
                        item -> item,
                        (existing, replacement) -> existing,
                        TreeMap::new
                ));
    }

    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.isBlank()) {
            throw new BestResultNotFound(query);
        }

        Searchable bestMatch = null;
        int maxCount = 0;
        String queryLower = query.toLowerCase();

        for (Searchable item : searchables) {
            if (item != null) {
                String searchTerm = item.getSearchTerm().toLowerCase();
                int count = countSubstringOccurrences(searchTerm, queryLower);

                if (count > maxCount) {
                    maxCount = count;
                    bestMatch = item;
                }
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound(query);
        }
        return bestMatch;
    }

    private int countSubstringOccurrences(String text, String sub) {
        if (text == null || sub == null || sub.isEmpty()) {
            return 0;
        }

        int count = 0;
        int index = 0;
        int subLength = sub.length();

        while ((index = text.indexOf(sub, index)) != -1) {
            count++;
            index += subLength;
        }
        return count;
    }
}