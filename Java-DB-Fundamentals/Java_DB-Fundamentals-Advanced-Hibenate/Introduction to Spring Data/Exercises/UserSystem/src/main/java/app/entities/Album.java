package app.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String name;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "is_public")
    private Boolean isPublic;

    @ManyToMany
        @JoinTable(name = "albums_pictures", joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id"))
    private Set<Picture> pictures;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User user;

    public Album() {
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

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
