package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.content.Article;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFound;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // 1. Инициализация данных
        ProductBasket basket = new ProductBasket();
        SearchEngine engine = new SearchEngine();

        // 2. Добавление тестовых данных
        Product laptop1 = new SimpleProduct("Ноутбук Lenovo", 50000);
        Product laptop2 = new SimpleProduct("Ноутбук Asus", 55000);
        Product mouse = new SimpleProduct("Мышь", 1500);
        Article article = new Article("Выбор ноутбука", "Как выбрать лучший ноутбук");

        basket.add(laptop1);
        basket.add(laptop2);
        basket.add(mouse);

        engine.add(laptop1);
        engine.add(laptop2);
        engine.add(mouse);
        engine.add(article);

        // 3. Демонстрация удаления продуктов
        System.out.println("=== Демонстрация удаления продуктов ===");
        List<Product> removed = basket.removeProductsByName("Ноутбук");
        System.out.println("Удалено продуктов: " + removed.size());
        removed.forEach(p -> System.out.println("- " + p.getName()));
        System.out.println("Осталось в корзине:");
        basket.printBasket();

        // 4. Демонстрация поиска (возвращает все результаты)
        System.out.println("\n=== Демонстрация поиска ===");
        List<Searchable> searchResults = engine.search("ноутбук");
        System.out.println("Найдено результатов: " + searchResults.size());
        searchResults.forEach(item ->
                System.out.println("- " + item.getStringRepresentation())
        );

        // 5. Демонстрация поиска несуществующего товара
        System.out.println("\nПоиск 'планшет':");
        List<Searchable> noResults = engine.search("планшет");
        System.out.println("Найдено: " + noResults.size());
    }
}