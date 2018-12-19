package entities;

import javax.persistence.*;

public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 3)
    private String name;

    @ManyToOne
    private Continent continent;
}
