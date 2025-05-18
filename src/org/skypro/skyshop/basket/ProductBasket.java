package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        productsMap.forEach((name, products) ->
                products.forEach(product ->
                        System.out.println(product.getName() + ": " + product.getPrice())
                )
        );
        System.out.println("Итого: " + getTotalPrice());
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