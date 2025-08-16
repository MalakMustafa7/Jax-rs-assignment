package org.example.Repository;

import jakarta.ws.rs.NotFoundException;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
   private static List<Product> products =  new ArrayList<>();
    private static int nextId = 1;

    @Override
    public void addProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
    }

    @Override
    public void updateProduct(Product product) {
         Product existing = getProductById(product.getId());
         existing.setName(product.getName());
         existing.setPrice(product.getPrice());

    }

    @Override
    public List<Product> getProducts() {
        return products;

    }

    @Override
    public Product getProductById(int productId) {
     return   products.stream()
                 .filter(p->p.getId() == productId)
                 .findFirst()
                 .orElseThrow(() -> new NotFoundException("Product not found"));

    }

    @Override
    public boolean deleteProduct(int productId) {
        return products.removeIf(p -> p.getId() == productId);
    }
}
