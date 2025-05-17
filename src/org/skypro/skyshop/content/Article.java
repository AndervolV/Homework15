package org.skypro.skyshop.content;

import org.skypro.skyshop.search.Searchable;
import java.util.Objects;

public class Article implements Searchable {
    private final String title;
    private final String text;

    public Article(String title, String text) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Заголовок статьи не может быть пустым");
        }
        this.title = title;
        this.text = text != null ? text : "";
    }

    @Override
    public String getSearchTerm() {
        return title + " " + text;
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return title.equalsIgnoreCase(article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title.toLowerCase());
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }
}