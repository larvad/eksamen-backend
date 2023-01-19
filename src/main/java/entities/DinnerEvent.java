package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dinnerEvent")
public class DinnerEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "dish", nullable = false)
    private String dish;

    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToMany(mappedBy = "dinnerEvents")
    private Set<Assignment> assignments = new LinkedHashSet<>();


    public DinnerEvent(String name, String time, String image, String location, String dish, Integer price) {
        this.name = name;
        this.time = time;
        this.image = image;
        this.location = location;
        this.dish = dish;
        this.price = price;
    }

    public DinnerEvent() {
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

    public String getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }

    public String getLocation() {
        return location;
    }

    public String getDish() {
        return dish;
    }

    public Integer getPrice() {
        return price;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

//    public void addAssignment(Assignment assignment) {assignments.add(assignment);}



}