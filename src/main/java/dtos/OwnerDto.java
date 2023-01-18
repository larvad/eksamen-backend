package dtos;

import entities.Boat;
import entities.Owner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link Owner} entity
 */
public class OwnerDto implements Serializable {
    private final Integer id;
    private final String name;
    private final List<BoatInnerDto> boats = new ArrayList<>();

    public OwnerDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public OwnerDto(Owner owner) {
        this.id = owner.getId();
        this.name = owner.getName();
        owner.getBoats().forEach( boat -> {
            boats.add(new BoatInnerDto(boat));
        });
    }

    public static List<OwnerDto> getDTOs(List<Owner> owners) {
        List<OwnerDto> ownerDtoList = new ArrayList<>();
        owners.forEach( owner -> {
            ownerDtoList.add(new OwnerDto(owner));
        });
        return ownerDtoList;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<BoatInnerDto> getBoats() {
        return boats;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "boats = " + boats + ")";
    }

    /**
     * A DTO for the {@link Boat} entity
     */
    public static class BoatInnerDto implements Serializable {
        private final Integer id;
        private final String brand;
        private final String image;

        public BoatInnerDto(Integer id, String brand, String image) {
            this.id = id;
            this.brand = brand;
            this.image = image;
        }

        public BoatInnerDto(Boat boat) {

            this.id = boat.getId();
            this.brand = boat.getBrand();
            this.image = boat.getImage();
        }

        public Integer getId() {
            return id;
        }

        public String getBrand() {
            return brand;
        }

        public String getImage() {
            return image;
        }



    }
}