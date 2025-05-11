package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.content.Article;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.Searchable;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        SearchEngine engine = new SearchEngine();

        engine.add(new SimpleProduct("Ноутбук Asus", 50000));
        engine.add(new SimpleProduct("Ноутбук Lenovo", 55000));
        engine.add(new DiscountedProduct("Мышь Logitech", 2500, 10));
        engine.add(new Article("Выбор ноутбука", "Руководство по выбору ноутбука"));
        engine.add(new Article("Обзор техники", "Сравнение ноутбуков и планшетов"));

        System.out.println("=== Результаты поиска 'ноутбук' ===");
        Map<String, Searchable> results = engine.search("ноутбук");

        results.forEach((name, item) -> {
            System.out.println("Наименование: " + name);
            System.out.println("Тип: " + item.getContentType());
            System.out.println("Совпадение: " + item.getSearchTerm());
            System.out.println("-----------");
        });

        System.out.println("\n=== Поиск 'планшет' ===");
        Map<String, Searchable> emptyResults = engine.search("планшет");
        if (emptyResults.isEmpty()) {
            System.out.println("Ничего не найдено");
        }
    }
}