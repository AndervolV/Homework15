package org.skypro.skyshop;

import org.skypro.skyshop.content.Article;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class App {
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ ПРОВЕРОК В КОНСТРУКТОРАХ ===");
        demonstrateConstructorValidation();
        System.out.println("\n=== ДЕМОНСТРАЦИЯ ПОИСКА ЛУЧШЕГО РЕЗУЛЬТАТА ===");
        demonstrateBestMatchSearch();
    }

    private static void demonstrateConstructorValidation() {
        try {
            Product validProduct = new SimpleProduct("Валидный товар", 1000);
            System.out.println("[УСПЕХ] Создан товар: " + validProduct);
        } catch (IllegalArgumentException e) {
            System.out.println("[ОШИБКА] " + e.getMessage());
        }
        try {
            Product invalidProduct = new SimpleProduct("", -100); // Пустое название и отрицательная цена
            System.out.println("[УСПЕХ] Создан товар: " + invalidProduct);
        } catch (IllegalArgumentException e) {
            System.out.println("[ОШИБКА] " + e.getMessage());
        }
    }

    private static void demonstrateBestMatchSearch() {
        SearchEngine engine = new SearchEngine(5);
        try {
            engine.add(new SimpleProduct("Ноутбук Lenovo IdeaPad", 45000));
            engine.add(new DiscountedProduct("Смартфон Samsung Galaxy", 75000, 15));
            engine.add(new Article("Выбор ноутбука", "Руководство по выбору лучшего ноутбука"));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при добавлении: " + e.getMessage());
        }
        try {
            Searchable bestMatch = engine.findBestMatch("ноутбук");
            System.out.println("[УСПЕХ] Найден лучший результат: " +
                    bestMatch.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("[ОШИБКА] " + e.getMessage());
        }
        try {
            Searchable bestMatch = engine.findBestMatch("планшет");
            System.out.println("[УСПЕХ] Найден лучший результат: " +
                    bestMatch.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("[ОШИБКА] " + e.getMessage());
        }
    }
}