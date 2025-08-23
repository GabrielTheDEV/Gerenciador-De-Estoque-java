package com.application;

import com.application.model.Category;
import com.application.model.Product;

import static com.application.service.StockService.addProduct;
import static com.application.service.StockService.getInventory;
import static com.application.service.StockService.entryStock;
import static com.application.service.StockService.removeStock;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");

        Category Eletronico = new Category(1, "eletronico");

//        Product notebook = new Product("Notebook lenovo ideapad-1", 1.750, 10, Eletronico);
//        addProduct(notebook);
//        entryStock(2 , 10);
//        removeStock(3, 5);

        getInventory();
    }
}
