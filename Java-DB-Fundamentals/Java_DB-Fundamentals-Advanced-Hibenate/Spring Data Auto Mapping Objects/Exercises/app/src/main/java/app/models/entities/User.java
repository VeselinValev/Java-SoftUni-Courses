package app.models.entities;

import app.annotations.email.Email;
import app.annotations.password.Password;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false)
    private String email;

    @Password(containsDigit = true, containsUpperCase = true, containsLowerCase = true)
    @Column(nullable = false)
    private String password;

    @Column(name = "fill_name", nullable = false)
    private String fullName;

    @OneToMany(targetEntity = Game.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Game> games;

    @Column(name = "is_administrator")
    private boolean isAdministrator;

    public User() {
        this.games = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }
}
