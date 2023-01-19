package facades;

import dtos.BoatDto;
import dtos.HarbourDto;
import dtos.OwnerDto;
import entities.Boat;
import entities.Harbour;
import entities.Owner;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class BoatFacade {

    private static BoatFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private BoatFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BoatFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BoatFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }



    public List<OwnerDto> getAllOwners() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Owner> query = em.createQuery("select o from Owner o", Owner.class);
            List<Owner> owners = query.getResultList();
            return OwnerDto.getDTOs(owners);

        } finally {
            em.close();
        }
    }

    public List<HarbourDto> getAllHarbours() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Harbour> query = em.createQuery("select h from Harbour h", Harbour.class);
            List<Harbour> harbours = query.getResultList();
            return HarbourDto.getDTOs(harbours);
        }
        finally {
            em.close();
        }

    }
    public List<BoatDto> getAllBoats() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Boat> query = em.createQuery("select b from Boat b", Boat.class);
            List<Boat> boats = query.getResultList();
            return BoatDto.getDTOs(boats);
        }
        finally {
            em.close();
        }

    }


//    public Owner getOwnerById(String id) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            Owner owner = em.find(Owner.class, Integer.valueOf(id));
//            if (owner == null) throw new WebApplicationException("Owner could not be found", 404);
//            return owner;
//        } finally {
//            em.close();
//        }
//    }
//    public Harbour getHarbourById(String id) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            Harbour harbour = em.find(Harbour.class, Integer.valueOf(id));
//            if (harbour == null) throw new WebApplicationException("Harbour could not be found", 404);
//            return harbour;
//        } finally {
//            em.close();
//        }
//    }
//    public BoatDto createBoat(Boat boat, Owner owner) {
//        EntityManager em = getEntityManager();
//        try {
//            em.getTransaction().begin();
//            owner.addBoat(boat);
//            boat.addOwner(owner);
//            em.merge(boat.getHarbour());
//            em.persist(boat);
//            em.getTransaction().commit();
//            return new BoatDto(boat);
//        } finally {
//            em.close();
//        }
//
//    }
}
