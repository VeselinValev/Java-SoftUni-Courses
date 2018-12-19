package entities;

import javax.persistence.Column;
import javax.persistence.Id;

public class Position {

    @Id
    @Column(length = 2, nullable = false)
    private String id;

    @Column(name = "position_description")
    private String positionDescription;
}
