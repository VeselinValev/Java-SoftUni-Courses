package app.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 3, max = 100)
    @Pattern(regexp = "^[A-Z].+$")
    private String title;

    @Column
    @Length(min = 11, max = 11)
    private String trailer;

    @Column(name = "image_url")
    @Pattern(regexp = "^(http(s)?:\\/\\/).+$")
    private String image;

    @Column()
    @Min(value = 0)
    @Digits(integer = 5, fraction = 1)
    private BigDecimal size;

    @Column()
    @Min(value = 0)
    @Digits(integer = 6, fraction = 2)
    private BigDecimal price;

    @Basic
    @Length(min = 20)
    private  String description;

    @Column(name = "release_date")
    private Date releaseDate;

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
