package com.github.redfox197;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.net.URI;
import java.util.List;

@Path("/api/v1/configurations")
@Produces(MediaType.APPLICATION_JSON)
public class ConfigurationResource {

    @POST
    public Response create(Configuration configuration) {
        configuration.persist();
        return Response.created(URI.create("/api/v1/configurations/" + configuration.id)).build();
    }

    @GET
    public List<Configuration> findAll() {
        return Configuration.listAll();
    }

    @GET
    @Path("/{id}")
    public Configuration findById(@PathParam("id") String id) {
        return Configuration.findById(new ObjectId(id));
    }

    @PUT
    @Path("/{id}")
    public Configuration update(@PathParam("id") String id, Configuration configuration) {
        ObjectId objectId = new ObjectId(id);

        if (Configuration.findById(objectId) == null) {
            throw new NotFoundException();
        }

        configuration.id = objectId;
        configuration.update();
        return configuration;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        Configuration.deleteById(new ObjectId(id));
    }
}
