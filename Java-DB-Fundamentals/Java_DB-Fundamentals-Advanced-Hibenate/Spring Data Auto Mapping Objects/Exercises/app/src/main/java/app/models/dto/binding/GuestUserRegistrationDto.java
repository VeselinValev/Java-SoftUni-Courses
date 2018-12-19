package app.models.dto.binding;

import java.io.Serializable;

public class GuestUserRegistrationDto implements Serializable {

    private String email;

    private String password;

    private String fullName;

    private Boolean isAdministrator;

    public GuestUserRegistrationDto() {
        this.isAdministrator = false;
    }

    public GuestUserRegistrationDto(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.isAdministrator = false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(Boolean administrator) {
        isAdministrator = administrator;
    }
}
