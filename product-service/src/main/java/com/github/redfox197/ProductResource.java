package com.github.redfox197;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.net.URI;
import java.util.List;

@Path("/api/v1/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Channel("policy-create")
    public Emitter<Product> policyEmitter;

    @POST
    @Transactional
    public Response create(Product product) {
        product.persist();
        policyEmitter.send(product);
        return Response.created(URI.create("/api/v1/products/" + product.id)).build();
    }

    @GET
    public List<Product> findAll() {
        return Product.listAll();
    }

    @GET
    @Path("/{id}")
    public Product findById(@PathParam("id") Long id) {
        return Product.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Product update(@PathParam("id") Long id, Product updatedProduct) {
        Product product = Product.findById(id);

        if (product == null)
            throw new NotFoundException();

        product.code = updatedProduct.code;
        product.name = updatedProduct.name;
        product.price = updatedProduct.price;

        return product;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Product.deleteById(id);
    }

}
