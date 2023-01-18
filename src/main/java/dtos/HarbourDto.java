package dtos;

import entities.Boat;
import entities.Harbour;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link Harbour} entity
 */
public class HarbourDto implements Serializable {
    private final Integer id;
    private final String name;
    private final String address;
    private final Integer capacity;
    private final List<BoatInnerDto> boats = new ArrayList<>();

    public HarbourDto(Integer id, String name, String address, Integer capacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;

    }

    public HarbourDto(Harbour harbour) {
        this.id = harbour.getId();
        this.name = harbour.getName();
        this.address = harbour.getAddress();
        this.capacity = harbour.getCapacity();
        harbour.getBoats().forEach(boat -> {
            boats.add(new BoatInnerDto(boat));
        });
    }

    public static List<HarbourDto> getDTOs(List<Harbour> harbours) {
        List<HarbourDto> harbourDtoList= new ArrayList<>();
        harbours.forEach(harbour -> {
            harbourDtoList.add(new HarbourDto(harbour));
        });
        return harbourDtoList;

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


    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "address = " + address + ", " +
                "capacity = " + capacity + ", " +
                "boats = " + boats + ")";
    }

    /**
     * A DTO for the {@link Boat} entity
     */
    public static class BoatInnerDto implements Serializable {
        private final Integer id;
        private final String brand;
        private final String make;
        private final String image;

        public BoatInnerDto(Integer id, String brand, String make, String image) {
            this.id = id;
            this.brand = brand;
            this.make = make;
            this.image = image;
        }

        public BoatInnerDto(Boat boat) {
            this.id = boat.getId();
            this.brand = boat.getBrand();
            this.make = boat.getBrand();
            this.image = boat.getImage();
        }

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

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "brand = " + brand + ", " +
                    "make = " + make + ", " +
                    "image = " + image + ")";
        }
    }
}