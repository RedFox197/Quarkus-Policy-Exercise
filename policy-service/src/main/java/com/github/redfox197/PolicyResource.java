package com.github.redfox197;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/api/v1/policies")
@Produces(MediaType.APPLICATION_JSON)
public class PolicyResource {

    @POST
    @Transactional
    public Response create(Policy policy) {
        policy.persist();
        return Response.created(URI.create("/api/v1/policies/" + policy.id)).build();
    }

    @GET
    public List<Policy> findAll() {
        return Policy.listAll();
    }

    @GET
    @Path("/{id}")
    public Policy findById(@PathParam("id") Long id) {
        return Policy.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Policy update(@PathParam("id") Long id, Policy updatedPolicy) {
        Policy policy = Policy.findById(id);

        if (policy == null)
            throw new NotFoundException();

        policy.code = updatedPolicy.code;
        policy.creationDate = updatedPolicy.creationDate;
        policy.expirationDate = updatedPolicy.expirationDate;

        return policy;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Policy.deleteById(id);
    }

}
