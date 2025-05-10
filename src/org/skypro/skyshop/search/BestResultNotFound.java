package org.skypro.skyshop.search;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String query) {
        super("Не найдено подходящих результатов для запроса: '" + query + "'");
    }
}