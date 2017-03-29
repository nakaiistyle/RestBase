package jp.co.allsmart.restbase.controller;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.allsmart.restbase.dto.request.SampleRegist;
import jp.co.allsmart.restbase.dto.response.SampleGetAll;
import jp.co.allsmart.restbase.service.SampleService;

/**
 * サンプルコントローラー。
 *
 * @author Nakai
 *
 */
@Component
@Path("/sample")
public class SampleController {

    private static final Logger log = LogManager.getLogger(SampleController.class);

    @Autowired
    SampleService sampleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        log.debug("sample getall start");

        SampleGetAll entity = sampleService.getAll();

        Response resp = Response.status(Status.OK).entity(entity).build();

        return resp;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id) {

        Response resp = Response.status(Status.OK).entity(sampleService.getById(id)).build();

        return resp;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response regist(@Valid SampleRegist req) {

        sampleService.regist(req);

        return Response.status(Status.OK).build();
    }


}
