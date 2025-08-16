package org.example.service;

import org.example.model.Product;

import java.util.List;

public interface IProductService {
    void addProduct(Product product);
    void updateProduct(Product product);
    List<Product> getProducts();
    Product getProductById(int productId);
    boolean deleteProduct(int productId);
}
