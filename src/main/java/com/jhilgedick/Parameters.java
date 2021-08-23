package com.jhilgedick;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jhilgedick.data.MyGlobalVars;
import com.jhilgedick.utils.MyResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.util.Base64;

@Path("parameters")
public class Parameters {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response setParameters(
        @QueryParam("responseCode") Integer responseCode,
        @QueryParam("responseTimeMS") Long responseTimeMS,
        @QueryParam("cpuBurnTimeMS") Long cpuBurnTimeMS,
        @QueryParam("healthCheck") Integer healthCheck,
        @QueryParam("responseBodyB64") String responseBodyB64
    ) {
        
        MyResponse myResponse = new MyResponse();
        MyGlobalVars mgv = MyGlobalVars.getInstance();

        if (responseCode != null) {
            mgv.setResponseCode(responseCode);
        }

        if (responseTimeMS != null) {
            mgv.setResponseTimeMS(responseTimeMS);
        }

        if (cpuBurnTimeMS != null) {
            mgv.setCpuBurnTimeMS(cpuBurnTimeMS);
        }

        if (healthCheck != null) {
            mgv.setHealthCheck(healthCheck);
        }

        if ((responseBodyB64 != null) && (responseBodyB64.length() > 0)) {
            byte[] decodedBytes = Base64.getDecoder().decode(responseBodyB64);
            String decodedString = new String(decodedBytes);
            // test that this is valid json...
            try {
                new ObjectMapper().readValue(decodedString, Object.class);
                mgv.setResponseBody(decodedString);
                myResponse.setMessage(decodedString);
            } catch (IOException e) {
                myResponse.setMessage("invalid json");
                //e.printStackTrace();
            }
        } else {
            mgv.setResponseBody(null);
        }

        String json = "";
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            json = mapper.writeValueAsString(myResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(json).build();
    }

}
