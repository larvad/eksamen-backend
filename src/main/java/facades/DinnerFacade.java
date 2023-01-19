package facades;

import dtos.AssignmentDto;
import dtos.DinnerEventDto;
import dtos.HarbourDto;
import dtos.UserDto;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DinnerFacade {


    private static DinnerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private DinnerFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static DinnerFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DinnerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }



    public List<DinnerEventDto> getAllDinners() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<DinnerEvent> query = em.createQuery("select d from DinnerEvent d", DinnerEvent.class);
            List<DinnerEvent> dinnerEvents = query.getResultList();
            return DinnerEventDto.getDTOs(dinnerEvents);
        }
        finally {
            em.close();
        }

    }
    public List<AssignmentDto> getAllAssignments() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Assignment> query = em.createQuery("select a from Assignment a", Assignment.class);
            List<Assignment> assignments = query.getResultList();
            return AssignmentDto.getDTOs(assignments);
        }
        finally {
            em.close();
        }

    }

    public UserDto getUserById(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            User user = em.find(User.class, username);
            if (user == null) throw new WebApplicationException("Owner could not be found", 404);
            return new UserDto(user);
        } finally {
            em.close();
        }
    }

    public String addUserToFamily(String username, String id) {
        EntityManager em = emf.createEntityManager();
        String message;
        User user;
        try {
            user = em.find(User.class, username);
            Assignment assignment = em.find(Assignment.class, Integer.valueOf(id));
            Set<Assignment> userAssignments = user.getAssignments();
            if (userAssignments == null) {
                userAssignments = new LinkedHashSet<>();
            }
            if (userAssignments.contains(assignment)) {
                message = "You're already in this family";
            } else {
                em.getTransaction().begin();
                userAssignments.add(assignment);
                assignment.addUser(user);
                em.persist(assignment);
                em.getTransaction().commit();
                message = "Mission Complete";
            }
        } finally {
            em.close();
        }
        return message;
    }
}
