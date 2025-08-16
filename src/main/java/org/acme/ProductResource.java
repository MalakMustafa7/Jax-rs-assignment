package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.service.IProductService;
import org.example.model.Product;
import org.example.service.ProductService;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    private IProductService productService = new ProductService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Product addProduct(Product product){
        productService.addProduct(product);
        return product;
    }

    @GET
    public List<Product> getAllProducts(){
       return productService.getProducts();
    }

    @GET()
    @Path("/{id}")
    public Product getProduct(@PathParam("id")int id){
        return productService.getProductById(id);
    }

    @PUT()
    @Consumes(MediaType.APPLICATION_JSON)
    public Product updateProduct(Product product){
        productService.updateProduct(product);
        return product;
    }

    @DELETE
    @Path("/{id}")
    public String deleteProduct(@PathParam("id") int id) {
       boolean removed= productService.deleteProduct(id);
       if(removed){
           return "Product deleted successfully";
       }else {
           throw new NotFoundException("Product not found");
       }
    }
}
