package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "boat")
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "image", nullable = false)
    private String image;

    @ManyToMany
    @JoinTable(name = "link_owner_boat",
            joinColumns = @JoinColumn(name = "boat_id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id"))
    private Set<Owner> owners = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "habour_id", nullable = false)
    private Harbour harbour;

    public Integer getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getMake() {
        return make;
    }

    public String getImage() {
        return image;
    }


    public Harbour getHarbour() {
        return harbour;
    }

    public Set<Owner> getOwners() {
        return owners;
    }
}