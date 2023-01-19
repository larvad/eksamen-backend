package facades;

import dtos.DinnerEventDto;
import dtos.HarbourDto;
import entities.DinnerEvent;
import entities.Harbour;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

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
}
