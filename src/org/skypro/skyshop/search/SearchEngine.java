package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] searchables;
    private int currentIndex;

    public SearchEngine(int capacity) {
        this.searchables = new Searchable[capacity];
        this.currentIndex = 0;
    }

    public void add(Searchable searchable) {
        if (currentIndex < searchables.length) {
            searchables[currentIndex++] = searchable;
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int foundCount = 0;

        for (Searchable item : searchables) {
            if (item == null) continue;

            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results[foundCount++] = item;
                if (foundCount == 5) break;
            }
        }

        return results;
    }

    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.isEmpty()) {
            throw new BestResultNotFound(query);
        }

        Searchable bestMatch = null;
        int maxCount = 0;
        String queryLower = query.toLowerCase();

        for (Searchable item : searchables) {
            if (item == null) continue;

            String searchTerm = item.getSearchTerm().toLowerCase();
            int count = countSubstringOccurrences(searchTerm, queryLower);

            if (count > maxCount) {
                maxCount = count;
                bestMatch = item;
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound(query);
        }

        return bestMatch;
    }

    private int countSubstringOccurrences(String str, String sub) {
        int count = 0;
        int index = 0;
        int subLength = sub.length();

        if (subLength == 0) return 0;

        while ((index = str.indexOf(sub, index)) != -1) {
            count++;
            index += subLength;
        }

        return count;
    }
}