/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Assignment;
import entities.DinnerEvent;
import entities.Role;
import entities.User;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author tha
 */
public class Populator {
    private static APIFacade instance;
    private static EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();

    private static final APIFacade FACADE = APIFacade.getInstance(emf);

    public static void populate() {

        EntityManager em = emf.createEntityManager();
        //Create test users
        User user = new User("jon", "1234", "Svaneke", "12345678", "member@test.dk", "1990", 20000);
        User admin = new User("thomas", "1234", "Gudhjem", "12345678", "admin@test.dk", "1990", 20000);
        User both = new User("jorg", "1234", "Allinge", "12345678", "member_admin@test.dk", "1990", 20000);


        //Create test events
        String imageUrl1 = "https://images.squarespace-cdn.com/content/v1/5cf423ac7eb1290001ede256/1611443427041-9YBGUEEB67TMD356NZ1G/Kitchen-Geranium-Menu-Review+%281+of+1%29.jpg?format=1000w";
        String imageUrl2 = "https://files.guidedanmark.org/files/382/201278_Kadeau_MarieLouiseMunkegaard.jpg";
        String imageUrl3 = "https://spiseguidenaarhus.dk/wp-content/uploads/2021/05/Restaurant-ATRIUM-3-1280x720.jpg";
        String description = "Copenhagen Runners startede oprindeligt som en løbeklub i det indre København. Klubben blev grundlagt af en gruppe entusiastiske løbere, der ønskede at dele deres passion for løb og motion";

        DinnerEvent dinnerEvent = new DinnerEvent("Geranium", "5 timer", imageUrl1, "København", "7 retters", 2500);
        DinnerEvent dinnerEvent2 = new DinnerEvent("Kadeau", "3 timer", imageUrl2, "Bornholm", "5 retters", 1749);
        DinnerEvent dinnerEvent3 = new DinnerEvent("Atrium", "5 timer", imageUrl3, "Aarhus", "7 retters", 2149);


        Assignment assignment = new Assignment("Copenhagen Runners", 2002, "dinner@cphrunners.dk", description);

        em.getTransaction().begin();
        Role userRole = new Role("member");
        Role adminRole = new Role("admin");
        user.addRole(userRole);
        admin.addRole(adminRole);
        both.addRole(userRole);
        both.addRole(adminRole);
        assignment.addDinner(dinnerEvent);
        assignment.addUser(user);
        em.persist(assignment);
        em.persist(dinnerEvent);
        em.persist(dinnerEvent2);
        em.persist(dinnerEvent3);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);
        em.persist(both);
//
////        Add owners
//        Owner o1 = new Owner("Skipper Bænt", "Persillehaven 40", "38383838");
//        Owner o2 = new Owner("Skipper Niels", "Persillehaven 42", "39393939");
//        Owner o3 = new Owner("Skipper Bente", "Persillehaven 38", "40404040");
//
//        Harbour h1 = new Harbour("Melsted Havn", "Melsted byvej", 8);
//        Harbour h2 = new Harbour("Nexø Havn", "Hovedvejen", 14);
//        Harbour h3 = new Harbour("Aakirkeby Havn", "Melsted byvej", 32);
//
//        Boat b1 = new Boat("Boatmaster", "speeder", "Martha", "https://img.fruugo.com/product/8/58/278398588_max.jpg");
//        Boat b2 = new Boat("Das Boot", "submarine", "Aase", "https://cdn.shopify.com/s/files/1/0626/0562/3537/products/31S6ddXfLmL.jpg?v=1659358008");
//        Boat b3 = new Boat("Hanger", "supersize", "King Lincoln", "https://upload.wikimedia.org/wikipedia/commons/2/2d/USS_Nimitz_%28CVN-68%29.jpg");
//
//        b1.addOwner(o1);
//        b2.addOwner(o1);
//        b2.addOwner(o2);
//        b3.addOwner(o1);
//        b3.addOwner(o3);
//
//        h1.addBoat(b1);
//        h3.addBoat(b2);
//        h3.addBoat(b3);
//
//        em.persist(o1);
//        em.persist(o2);
//        em.persist(o3);
//        em.persist(b1);
//        em.persist(b2);
//        em.persist(b3);
//        em.persist(h1);
//        em.persist(h2);
//        em.persist(h3);
//
        em.getTransaction().commit();
//        System.out.println("PW: " + user.getUserPass());
//        System.out.println("Testing user with OK password: " + user.verifyPassword("As123456"));
//        System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
//        System.out.println("Created TEST Users");
//
////        Create dummy -owners
////        FACADE.create(new Owner("Preben"));
////        FACADE.create(new Owner("Poul"));
        em.close();
    }

}
