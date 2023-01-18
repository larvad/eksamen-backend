package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BoatDto;
import dtos.HarbourDto;
import dtos.OwnerDto;
import facades.BoatFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.annotation.security.DeclareRoles;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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





}