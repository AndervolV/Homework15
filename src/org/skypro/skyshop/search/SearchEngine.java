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
}