package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;

public class ProductBasket {
    private final Map<String, List<Product>> productsMap = new HashMap<>();

    public void add(Product product) {
        productsMap.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    public List<Product> removeProductsByName(String name) {
        return productsMap.containsKey(name) ? productsMap.remove(name) : Collections.emptyList();
    }

    public int getTotalPrice() {
        return productsMap.values().stream()
                .flatMap(List::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    public boolean containsProduct(String productName) {
        return productsMap.containsKey(productName);
    }

    public void printBasket() {
        if (productsMap.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        productsMap.values().stream()
                .flatMap(List::stream)
                .forEach(product ->
                        System.out.println(product.getName() + ": " + product.getPrice())
                );

        System.out.println("Итого: " + getTotalPrice());

        int specialCount = getSpecialCount();
        if (specialCount > 0) {
            System.out.println("Специальных товаров в корзине: " + specialCount);
        }
    }

    private int getSpecialCount() {
        return (int) productsMap.values().stream()
                .flatMap(List::stream)
                .filter(Product::isSpecial)
                .count();
    }

    public void clearBasket() {
        productsMap.clear();
    }

    public int getUniqueItemCount() {
        return productsMap.size();
    }

    public int getTotalItemCount() {
        return productsMap.values().stream()
                .mapToInt(List::size)
                .sum();
    }
}