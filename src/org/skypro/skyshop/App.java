package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;

public class App {
    public static void main(String[] args) {
        SimpleProduct laptop = new SimpleProduct("Ноутбук", 50000);
        DiscountedProduct smartphone = new DiscountedProduct("Смартфон", 30000, 10);  // Скидка 10%
        FixPriceProduct notebook = new FixPriceProduct("Блокнот");
        DiscountedProduct headphones = new DiscountedProduct("Наушники", 8000, 15);   // Скидка 15%
        FixPriceProduct pen = new FixPriceProduct("Ручка");

        ProductBasket basket = new ProductBasket();
        basket.addProduct(laptop);
        basket.addProduct(smartphone);
        basket.addProduct(notebook);
        basket.addProduct(headphones);
        basket.addProduct(pen);

        System.out.println("=== Содержимое корзины ===");
        basket.printBasket();

        System.out.println("\n=== Дополнительные проверки ===");
        System.out.println("Общая стоимость: " + basket.getTotalPrice());
        System.out.println("Есть ли 'Наушники' в корзине: " + basket.containsProduct("Наушники"));
        System.out.println("Есть ли 'Карандаш' в корзине: " + basket.containsProduct("Карандаш"));
    }
}