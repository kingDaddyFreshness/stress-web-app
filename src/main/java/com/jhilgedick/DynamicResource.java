package com.jhilgedick;

import java.security.InvalidParameterException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.jhilgedick.data.MyGlobalVars;
import com.jhilgedick.utils.CpuBurnThread;
import com.jhilgedick.utils.MyResponse;

@Path("dynamicResource")
public class DynamicResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response doIt() {

        MyGlobalVars mgv = MyGlobalVars.getInstance();

        Long responseTimeMS = mgv.getResponseTimeMS();
        if (responseTimeMS > 0) {
            try {
                Thread.sleep(responseTimeMS);
            } 
            catch(InvalidParameterException e) {
                e.printStackTrace();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        Long cpuBurnTimeMS = mgv.getCpuBurnTimeMS();
        if (cpuBurnTimeMS > 0) {
            CpuBurnThread.burn(8, .99, cpuBurnTimeMS);
        }

        String responseBody = mgv.getResponseBody();
        if ((responseBody != null) && (responseBody.length() > 0)) {
            // we'll use the response body that was set via the parameters endpoint
        } else {
            MyResponse myResponse = new MyResponse();
            InetAddress ip;
            try {
                ip = InetAddress.getLocalHost();
                myResponse.setMessage("Your current IP address : " + ip);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            try {
                responseBody = mapper.writeValueAsString(myResponse);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return Response.status(mgv.getResponseCode()).entity(responseBody).build();
    }
}