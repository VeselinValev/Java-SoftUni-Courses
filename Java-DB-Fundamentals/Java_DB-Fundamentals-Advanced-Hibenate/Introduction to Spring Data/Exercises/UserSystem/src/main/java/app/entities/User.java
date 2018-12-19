package app.entities;

import app.annotations.email.Email;
import app.annotations.password.Password;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
@Where(clause = "is_deleted <> 1") //Soft Delete Method - all other queries will not include users where isDeleted = true
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 4, max = 30)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false)
    @Password(containsLowerCase = true, containsUpperCase = true,
    containsDigit = true, containsSpecialSymbol = true)
    private String password;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(name = "profile_picture", columnDefinition = "LONGBLOB")
    @Size(max = 1000000)
    private byte[] profilePicture;

    @Column(name = "registration_time")
    private LocalDate registrationTime;

    @Column(name = "last_login_time")
    private LocalDate lastLoginTime;


    @Range(min = 1, max = 120)
    private Integer age;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @ManyToOne(targetEntity = Town.class, cascade = CascadeType.ALL)
    private Town birthTown;

    @ManyToOne(targetEntity = Town.class, cascade = CascadeType.ALL)
    private Town currentTown;

    @ManyToMany
    @JoinTable(name = "users_friends", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> friends;

    @OneToMany(targetEntity = Album.class, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Album> albums;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public LocalDate getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDate registrationTime) {
        this.registrationTime = registrationTime;
    }

    public LocalDate getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDate lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Town getBirthTown() {
        return birthTown;
    }

    public void setBirthTown(Town birthTown) {
        this.birthTown = birthTown;
    }

    public Town getCurrentTown() {
        return currentTown;
    }

    public void setCurrentTown(Town currentTown) {
        this.currentTown = currentTown;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
