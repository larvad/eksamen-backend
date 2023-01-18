package dtos;

import entities.Boat;
import entities.Harbour;
import entities.Owner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link Boat} entity
 */
public class BoatDto implements Serializable {
    private final Integer id;
    private final String brand;
    private final String image;
    private final List<OwnerInnerDto> owners = new ArrayList<>();
    private final HarbourInnerDto harbour;

    public BoatDto(Integer id, String brand, String image, Set<OwnerInnerDto> owners, HarbourInnerDto harbour) {
        this.id = id;
        this.brand = brand;
        this.image = image;
        this.harbour = harbour;
    }

    public BoatDto(Boat boat) {

        this.id = boat.getId();
        this.brand = boat.getBrand();
        this.image = boat.getBrand();
        this.harbour = new HarbourInnerDto(boat.getHarbour());
        boat.getOwners().forEach( owner -> {
            owners.add(new OwnerInnerDto(owner));
        });

    }

    public static List<BoatDto> getDTOs(List<Boat> boats) {
        List<BoatDto> boatDtoList = new ArrayList<>();
        boats.forEach(boat -> {
            boatDtoList.add(new BoatDto(boat));

        });
        return boatDtoList;
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

    public HarbourInnerDto getHarbour() {
        return harbour;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "brand = " + brand + ", " +
                "image = " + image + ", " +
                "owners = " + owners + ", " +
                "harbour = " + harbour + ")";
    }

    /**
     * A DTO for the {@link Owner} entity
     */
    public static class OwnerInnerDto implements Serializable {
        private final Integer id;
        private final String name;

        public OwnerInnerDto(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
        public OwnerInnerDto(Owner owner) {
            this.id = owner.getId();
            this.name = owner.getName();
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ")";
        }
    }

    /**
     * A DTO for the {@link Harbour} entity
     */
    public static class HarbourInnerDto implements Serializable {
        private final Integer id;
        private final String name;

        public HarbourInnerDto(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public HarbourInnerDto(Harbour harbour) {
            this.id = harbour.getId();
            this.name = harbour.getName();

        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ")";
        }
    }
}