package entities;

import javax.persistence.*;
import java.math.BigDecimal;

public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(length = 65000)
    private byte[] logo;

    @Column(length = 3)
    private String initials;

    @Column(name = "primary_kit")
    private Color primaryKit;

    @Column(name = "secondary_kit")
    private Color secondaryKit;

    @ManyToOne
    private Town town;

    @Column
    private BigDecimal budget;
}
