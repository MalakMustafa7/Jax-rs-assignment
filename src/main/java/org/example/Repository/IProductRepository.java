package org.example.Repository;

import org.example.model.Product;

import java.util.List;

public interface IProductRepository {
    void addProduct(Product product);
    void updateProduct(Product product);
    List<Product> getProducts();
    Product getProductById(int productId);
    boolean deleteProduct(int productId);
}
