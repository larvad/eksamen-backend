package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "createDate", nullable = false)
    private Integer createDate;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(name = "link_dinnerEvent_assignment",
            joinColumns = @JoinColumn(name = "assignment_id"),
            inverseJoinColumns = @JoinColumn(name = "dinnerEvent_id"))
    private Set<DinnerEvent> dinnerEvents = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "link_user_assignment",
            joinColumns = @JoinColumn(name = "assignment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_name"))
    private Set<User> users = new LinkedHashSet<>();


    public Assignment() {
    }


    public Assignment(String name, Integer createDate, String contact, String description) {
        this.name = name;
        this.createDate = createDate;
        this.contact = contact;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<DinnerEvent> getDinnerEvents() {
        return dinnerEvents;
    }

    public Integer getCreateDate() {
        return createDate;
    }


    public String getContact() {
        return contact;
    }


    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {users.add(user);}

    public void addDinner(DinnerEvent dinnerEvent) {dinnerEvents.add(dinnerEvent);}
}
