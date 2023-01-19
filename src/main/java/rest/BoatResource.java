package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.BoatDto;
import dtos.HarbourDto;
import dtos.OwnerDto;
import entities.Boat;
import entities.Owner;
import facades.BoatFacade;
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

@Path("boat")
@DeclareRoles({"user", "admin"})
public class BoatResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;
    @Context
    SecurityContext securityContext;
    private static final BoatFacade facade = BoatFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("owner")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllOwners(){
        List<OwnerDto> ownerDtoList = facade.getAllOwners();
        return GSON.toJson(ownerDtoList);
    }


    @Path("harbour")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllHarbours(){
        List<HarbourDto> harbourDtoList = facade.getAllHarbours();
        return GSON.toJson(harbourDtoList);
    }

    @Path("boats")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllBoats(){
        List<BoatDto> boatDtoList = facade.getAllBoats();
        return GSON.toJson(boatDtoList);
    }
//    @POST
//    @Path("createBoat")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public String createBeer(String jdata) {
//        JsonObject json = JsonParser.parseString(jdata).getAsJsonObject();
//        String imageUrl = json.get("imageurl").getAsString();
//        String brand = json.get("brand").getAsString();
//        String harbourId = json.get("harbourid").getAsString();
//        String ownerId = json.get("ownerid").getAsString();
//        String make = "empty";
//        Owner owner = facade.getOwnerById(ownerId);
//
//        Boat boat = new Boat(
//                brand, make, imageUrl, facade.getHarbourById(harbourId)
//        );
//
//        return GSON.toJson(facade.createBoat(boat, owner));
//
//    }






}