package app.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String name;

    @Basic
    private String country;

    @OneToMany(targetEntity = User.class, mappedBy = "birthTown", cascade = CascadeType.ALL)
    private Set<User> usersBornHere;

    @OneToMany(targetEntity = User.class, mappedBy = "currentTown", cascade = CascadeType.ALL)
    private Set<User> usersCurrentlyHere;

    public Town() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<User> getUsersBornHere() {
        return usersBornHere;
    }

    public void setUsersBornHere(Set<User> usersBornHere) {
        this.usersBornHere = usersBornHere;
    }

    public Set<User> getUsersCurrentlyHere() {
        return usersCurrentlyHere;
    }

    public void setUsersCurrentlyHere(Set<User> usersCurrentlyHere) {
        this.usersCurrentlyHere = usersCurrentlyHere;
    }
}
