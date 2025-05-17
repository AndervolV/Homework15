package org.skypro.skyshop.search;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Objects;

public class SearchEngine {
    private final Set<Searchable> searchables = new HashSet<>();

    private static final Comparator<Searchable> SEARCHABLE_COMPARATOR =
            Comparator.<Searchable, Integer>comparing(
                    s -> s.getName().length(),
                    Comparator.reverseOrder()
            ).thenComparing(Searchable::getName);

    public boolean addSearchable(Searchable searchable) {
        if (searchable == null) {
            throw new IllegalArgumentException("Searchable cannot be null");
        }
        return searchables.add(searchable);
    }

    public Set<Searchable> getAllSearchables() {
        return Collections.unmodifiableSet(searchables);
    }

    public Set<Searchable> search(String query) {
        if (query == null) {
            return Collections.emptySet();
        }

        String queryLower = query.toLowerCase();

        return searchables.stream()
                .filter(Objects::nonNull)
                .filter(s -> s.getSearchTerm() != null)
                .filter(s -> query.isEmpty() || s.getSearchTerm().toLowerCase().contains(queryLower))
                .collect(Collectors.toCollection(() -> new TreeSet<>(SEARCHABLE_COMPARATOR)));
    }

    private int countSubstringOccurrences(String text, String sub) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }
        return count;
    }
}