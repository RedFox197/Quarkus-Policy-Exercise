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

    @GET
    public List<Configuration> findAll() {
        return Configuration.listAll();
    }

    @GET
    @Path("/{id}")
    public Configuration findById(@PathParam("id") String id) {
        return Configuration.findById(new ObjectId(id));
    }

    @POST
    public Response create(Configuration configuration) {
        configuration.persist();

        configuration.extra.entrySet().stream()
                .map(entry -> entry.getKey() + entry.getValue())
                .forEach(System.out::println);

        return Response.created(URI.create("/api/v1/configurations/" + configuration.id)).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        Configuration configuration = Configuration.findById(new ObjectId(id));
        if (configuration == null) throw new NotFoundException();
        configuration.delete();
    }

    @PUT
    @Path("/{id}")
    public Configuration update(@PathParam("id") String id, Configuration configuration) {
        //configuration.id = new ObjectId(id);
        configuration.update();
        return configuration;
    }
}
