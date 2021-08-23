package com.jhilgedick;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jhilgedick.utils.MyResponse;

@Path("version")
public class Version {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response doIt() {

        MyResponse myResponse = new MyResponse();
        myResponse.setMessage("version: v1.0.3");
        String json = "";
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            json = mapper.writeValueAsString(myResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return Response.status(200).entity(json).build();
    }
}