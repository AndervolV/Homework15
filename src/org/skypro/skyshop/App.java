package org.skypro.skyshop;

import org.skypro.skyshop.content.Article;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.SearchEngine;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngine(20);
        searchEngine.add(new SimpleProduct("Ноутбук Lenovo IdeaPad", 45000));
        searchEngine.add(new DiscountedProduct("Смартфон Samsung Galaxy", 75000, 15));
        searchEngine.add(new FixPriceProduct("Блокнот"));
        searchEngine.add(new SimpleProduct("Компьютерная мышь Logitech", 2500));
        searchEngine.add(new DiscountedProduct("Наушники Sony WH-1000XM4", 20000, 10));
        searchEngine.add(new Article("Как выбрать ноутбук",
                "При выборе ноутбука обратите внимание на процессор, оперативную память и тип диска."));
        searchEngine.add(new Article("Обзор смартфонов 2023",
                "Лучшие смартфоны этого года: Samsung Galaxy S23, iPhone 14 Pro и другие."));
        searchEngine.add(new Article("Руководство по аксессуарам",
                "Какие аксессуары действительно нужны для вашего компьютера."));
        System.out.println("=== Поиск по запросу 'ноутбук' ===");
        printSearchResults(searchEngine.search("ноутбук"));

        System.out.println("\n=== Поиск по запросу 'Samsung' ===");
        printSearchResults(searchEngine.search("Samsung"));

        System.out.println("\n=== Поиск по запросу 'аксессуар' ===");
        printSearchResults(searchEngine.search("аксессуар"));

        System.out.println("\n=== Поиск по запросу 'мышь' ===");
        printSearchResults(searchEngine.search("мышь"));

        System.out.println("\n=== Поиск по запросу '2023' ===");
        printSearchResults(searchEngine.search("2023"));
    }

    private static void printSearchResults(Searchable[] results) {
        System.out.println("Найдено результатов: " + Arrays.stream(results).filter(r -> r != null).count());
        for (Searchable item : results) {
            if (item != null) {
                System.out.println("- " + item.getStringRepresentation());
                System.out.println("  Поисковый термин: " + item.getSearchTerm());
            }
        }
    }
}