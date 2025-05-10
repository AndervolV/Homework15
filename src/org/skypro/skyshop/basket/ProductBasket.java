package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductBasket {
    private final List<Product> products;

    public ProductBasket() {
        this.products = new ArrayList<>();
    }

    /**
     * Добавляет продукт в корзину
     * @param product продукт для добавления
     */
    public void add(Product product) {
        products.add(product);
    }

    /**
     * Удаляет все продукты с указанным именем
     * @param name имя продукта для удаления
     * @return список удаленных продуктов
     */
    public List<Product> removeProductsByName(String name) {
        List<Product> removedProducts = new ArrayList<>();
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equalsIgnoreCase(name)) {
                removedProducts.add(product);
                iterator.remove();
            }
        }
        return removedProducts;
    }

    /**
     * Вычисляет общую стоимость товаров в корзине
     * @return общая стоимость
     */
    public int getTotalPrice() {
        return products.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }

    /**
     * Проверяет наличие продукта в корзине по имени
     * @param productName имя продукта
     * @return true если продукт есть в корзине
     */
    public boolean containsProduct(String productName) {
        return products.stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(productName));
    }

    /**
     * Выводит содержимое корзины в консоль
     */
    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        products.forEach(product ->
                System.out.println(product.getName() + ": " + product.getPrice())
        );
        System.out.println("Итого: " + getTotalPrice());
    }

    /**
     * Очищает корзину
     */
    public void clearBasket() {
        products.clear();
    }

    /**
     * Возвращает количество товаров в корзине
     * @return количество товаров
     */
    public int getItemCount() {
        return products.size();
    }
}