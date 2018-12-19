package entities;

import javax.persistence.*;

public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToOne
    private Country country;
}
