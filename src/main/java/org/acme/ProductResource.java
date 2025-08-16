package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import  jakarta.ws.rs.core.Response;
import org.example.Repository.IProductRepository;
import org.example.model.Product;
import org.example.Repository.ProductRepository;

import java.util.List;
import java.util.Map;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    private IProductRepository productRepository = new ProductRepository();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product){
        productRepository.addProduct(product);
        return Response.status(Response.Status.CREATED)
                .entity(product)
                .build();
    }

    @GET
    public Response getAllProducts(){

        List<Product> products= productRepository.getProducts();
        return Response.ok(products).build();
    }

    @GET()
    @Path("/{id}")
    public Response getProduct(@PathParam("id")int id){
        try {
            Product product = productRepository.getProductById(id);
           return Response.ok(product).build();
        }catch (NotFoundException ex){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", ex.getMessage()))
                    .build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id,Product product){
        try {
            product.setId(id);
            productRepository.updateProduct(product);
            return Response.ok(product).build();
        }catch (NotFoundException ex){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", ex.getMessage()))
                    .build();
        }

    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id) {
       boolean removed= productRepository.deleteProduct(id);
       if(removed){
           return Response.ok(Map.of("message", "Product deleted successfully")).build();
       }else {
           return Response.status(Response.Status.NOT_FOUND)
                   .entity(Map.of("error", "Product not found"))
                   .build();
       }
    }
}
