package dtos;

import entities.Assignment;
import entities.DinnerEvent;
import entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link Assignment} entity
 */
public class AssignmentDto implements Serializable {
    private final Integer id;
    private final String name;
    private final Integer createDate;
    private final String contact;
    private final String description;
    private final Set<UserInnerDto> users = new LinkedHashSet<>();


    public AssignmentDto (Assignment assignment) {
        this.id = assignment.getId();
        this.name = assignment.getName();
        this.createDate = assignment.getCreateDate();
        this.contact = assignment.getContact();
        this.description = assignment.getDescription();
        assignment.getUsers().forEach(user -> {
            users.add(new AssignmentDto.UserInnerDto(user));
        });
    }
    public static List<AssignmentDto> getDTOs(List<Assignment> assignments) {
        List<AssignmentDto> assignmentDtos = new ArrayList<>();
        assignments.forEach(assignment -> {
            assignmentDtos.add(new AssignmentDto(assignment));
        });
        return assignmentDtos;

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCreateDate() {
        return createDate;
    }

    public String getContact() {
        return contact;
    }

    public Set<UserInnerDto> getUsers() {
        return users;
    }



    /**
     * A DTO for the {@link User} entity
     */
    public static class UserInnerDto implements Serializable {
        @NotNull
        @Size(min = 1, max = 255)
        private final String userName;

        public UserInnerDto(String userName) {
            this.userName = userName;
        }

        public UserInnerDto(User user) {
            this.userName = user.getUserName();
        }

        public String getUserName() {
            return userName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserInnerDto entity = (UserInnerDto) o;
            return Objects.equals(this.userName, entity.userName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userName);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "userName = " + userName + ")";
        }
    }
}