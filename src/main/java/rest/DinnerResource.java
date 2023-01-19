package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.BoatDto;
import dtos.DinnerEventDto;
import dtos.HarbourDto;
import dtos.OwnerDto;
import entities.Boat;
import entities.Owner;
import facades.BoatFacade;
import facades.DinnerFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.annotation.security.DeclareRoles;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("dinner")
@DeclareRoles({"member", "admin"})
public class DinnerResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;
    @Context
    SecurityContext securityContext;
    private static final DinnerFacade facade = DinnerFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @Path("event")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllDinners() {
        List<DinnerEventDto> dinnerEventDtos = facade.getAllDinners();
        return GSON.toJson(dinnerEventDtos);
    }

//    @Path("assignment")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getAllAssigment() {
//        List<DinnerEventDto> dinnerEventDtos = facade.getAllDinners();
//        return GSON.toJson(dinnerEventDtos);
//    }

}