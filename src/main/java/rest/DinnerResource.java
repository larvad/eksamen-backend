package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.*;
import entities.Boat;
import entities.Owner;
import entities.User;
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

    @Path("assignment")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllAssigment() {
        List<AssignmentDto> assignmentDtos = facade.getAllAssignments();
        return GSON.toJson(assignmentDtos);
    }

    @Path("user")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserById(String jdata) {
        JsonObject json = JsonParser.parseString(jdata).getAsJsonObject();
        String username = json.get("user").getAsString();
        UserDto userDto = facade.getUserById(username);
        return GSON.toJson(userDto);
    }
    @Path("addUserToFamily")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addUserToFamily(String jdata) {
        JsonObject json = JsonParser.parseString(jdata).getAsJsonObject();
        String username = json.get("user").getAsString();
        String id = json.get("id").getAsString();
        String message = facade.addUserToFamily(username, id);
        return GSON.toJson(message);
    }



}