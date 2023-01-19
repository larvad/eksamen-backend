package dtos;

import entities.Assignment;
import entities.DinnerEvent;
import entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link User} entity
 */
public class UserDto implements Serializable {
    @NotNull
    @Size(min = 1, max = 255)
    private final String userName;
    private final String address;
    private final String phone;
    private final String email;
    private final String birthYear;

    private final Integer account;
    private final Set<AssignmentInnerDto> assignments = new LinkedHashSet<>();

    public UserDto(User user) {
        this.userName = user.getUserName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.birthYear = user.getBirthYear();
        this.account = user.getAccount();
        user.getAssignments().forEach(assignment -> {
            assignments.add(new AssignmentInnerDto(assignment));
        });
    }

    public String getUserName() {
        return userName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public Integer getAccount() {
        return account;
    }

    public Set<AssignmentInnerDto> getAssignments() {
        return assignments;
    }

    public static List<AssignmentDto> getDTOs(List<Assignment> assignments) {
        List<AssignmentDto> assignmentDtos = new ArrayList<>();
        assignments.forEach(assignment -> {
            assignmentDtos.add(new AssignmentDto(assignment));
        });
        return assignmentDtos;

    }




    /**
     * A DTO for the {@link Assignment} entity
     */
    public static class AssignmentInnerDto implements Serializable {
        private final Integer id;
        private final String name;
        private final Integer createDate;
        private final String contact;
        private final String description;
        private final Set<DinnerEventInnerDto> dinnerEvents = new LinkedHashSet<>();
        private final Set<UserFamilyInnerDto> users = new LinkedHashSet<>();

        public AssignmentInnerDto(Assignment assignment) {
            this.id = assignment.getId();
            this.name = assignment.getName();
            this.createDate = assignment.getCreateDate();
            this.contact = assignment.getContact();
            this.description = assignment.getDescription();
            assignment.getDinnerEvents().forEach(dinnerEvent -> {
                dinnerEvents.add(new DinnerEventInnerDto(dinnerEvent));
            });
            assignment.getUsers().forEach(user -> {
                users.add(new UserFamilyInnerDto(user));
            });
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

        public String getDescription() {
            return description;
        }

        public Set<DinnerEventInnerDto> getDinnerEvents() {
            return dinnerEvents;
        }

        public Set<UserFamilyInnerDto> getUsers() {
            return users;
        }



        /**
         * A DTO for the {@link DinnerEvent} entity
         */
        public static class DinnerEventInnerDto implements Serializable {
            private final Integer id;

            public DinnerEventInnerDto(DinnerEvent dinnerEvent) {
                this.id = dinnerEvent.getId();
            }

            public Integer getId() {
                return id;
            }


        }

        /**
         * A DTO for the {@link User} entity
         */
        public static class UserFamilyInnerDto implements Serializable {
            @NotNull
            @Size(min = 1, max = 255)
            private final String userName;

            public UserFamilyInnerDto(String userName) {
                this.userName = userName;
            }
            public UserFamilyInnerDto(User user) {
                this.userName = user.getUserName();
            }


            public String getUserName() {
                return userName;
            }


        }
    }
}