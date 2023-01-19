package dtos;

import entities.Assignment;
import entities.DinnerEvent;

import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link entities.DinnerEvent} entity
 */
public class DinnerEventDto implements Serializable {
    private final Integer id;
    private final String name;
    private final String time;
    private final String image;
    private final String location;
    private final String dish;
    private final Integer price;
    private final Set<AssignmentInnerDto> assignments = new HashSet<>();


    public DinnerEventDto(Integer id, String name, String time, String image, String location, String dish, Integer price) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.image = image;
        this.location = location;
        this.dish = dish;
        this.price = price;
    }



    public Integer getId() {
        return id;
    }

    public String getName() {return name;}

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

    public Set<AssignmentInnerDto> getAssignments() {
        return assignments;
    }


    public DinnerEventDto(DinnerEvent dinnerEvent) {
        this.id = dinnerEvent.getId();
        this.name = dinnerEvent.getName();
        this.time = dinnerEvent.getTime();
        this.image = dinnerEvent.getImage();
        this.location = dinnerEvent.getLocation();
        this.dish = dinnerEvent.getDish();
        this.price = dinnerEvent.getPrice();
        dinnerEvent.getAssignments().forEach( assignment -> {
            assignments.add(new DinnerEventDto.AssignmentInnerDto(assignment));
        });
    }

    public static List<DinnerEventDto> getDTOs(List<DinnerEvent> dinnerEvents) {
        List<DinnerEventDto> dinnerEventDtos = new ArrayList<>();
        dinnerEvents.forEach(dinnerEvent -> {
            dinnerEventDtos.add(new DinnerEventDto(dinnerEvent));
        });
        return dinnerEventDtos;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DinnerEventDto entity = (DinnerEventDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.time, entity.time) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.dish, entity.dish) &&
                Objects.equals(this.price, entity.price) &&
                Objects.equals(this.assignments, entity.assignments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, location, dish, price, assignments);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "time = " + time + ", " +
                "location = " + location + ", " +
                "dish = " + dish + ", " +
                "price = " + price + ", " +
                "assignments = " + assignments + ")";
    }

    /**
     * A DTO for the {@link entities.Assignment} entity
     */
    public static class AssignmentInnerDto implements Serializable {
        private final Integer id;
        private final String name;
        private final String contact;

        public AssignmentInnerDto(Integer id, String name, String contact) {
            this.id = id;
            this.name = name;
            this.contact = contact;
        }

        public AssignmentInnerDto (Assignment assignment) {
            this.id = assignment.getId();
            this.name = assignment.getName();
            this.contact = assignment.getContact();
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }



        public String getContact() {
            return contact;
        }


    }
}