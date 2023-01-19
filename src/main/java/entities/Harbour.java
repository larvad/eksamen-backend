package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "harbour")
public class Harbour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @OneToMany(mappedBy = "harbour")
    private Set<Boat> boats = new LinkedHashSet<>();



    public Harbour(Integer id, String name, String address, Integer capacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    public Harbour() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Set<Boat> getBoats() {
        return boats;
    }
}