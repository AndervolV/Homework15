package org.skypro.skyshop;

import org.skypro.skyshop.content.Article;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.SearchEngine;

import java.util.Set;

public class App {
    public static void main(String[] args) {
        SearchEngine engine = new SearchEngine();

        engine.addSearchable(new Article("Java Programming", "Content about Java"));
        engine.addSearchable(new Article("Spring", "Spring framework"));
        engine.addSearchable(new Article("Hibernate Long", "Hibernate article"));
        engine.addSearchable(new SimpleProduct("Laptop", 1000));
        engine.addSearchable(new Article("Java Basics", "Introduction to Java"));
        engine.addSearchable(new Article("A", "Shortest"));
        engine.addSearchable(new Article("ABCDE", "Medium"));
        engine.addSearchable(new Article("ABC", "Same length"));
        engine.addSearchable(new Article("XYZ", "Same length"));
        engine.addSearchable(new SimpleProduct("Long Product Name", 100));

        System.out.println("=== Все элементы (отсортированы по длине имени и алфавиту) ===");
        Set<Searchable> allResults = engine.search("");
        allResults.forEach(item ->
                System.out.println(item.getName() + " (length: " + item.getName().length() + ")")
        );

        System.out.println("\n=== Результаты поиска по 'Java' ===");
        Set<Searchable> javaResults = engine.search("Java");
        javaResults.forEach(item ->
                System.out.println(item.getName() + " (length: " + item.getName().length() + ")")
        );

        System.out.println("\n=== Попытка добавить дубликат ===");
        boolean added = engine.addSearchable(new Article("Java Programming", "New content"));
        System.out.println("Дубликат добавлен? " + added);
    }
}