package org.tapbeatbox.server.resources;

/**
 * This resource will be used when sending a Student object to the front end.
 * @author Sajith Dilshan
 */
public class StudentResource {
    private int id;
    private String indexNumber;
    private String firstName;
    private String lastName;
    private String avatarURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    @Override
    public String toString() {
        return "StudentResource{" +
                "id=" + id +
                ", indexNumber='" + indexNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                '}';
    }
}
