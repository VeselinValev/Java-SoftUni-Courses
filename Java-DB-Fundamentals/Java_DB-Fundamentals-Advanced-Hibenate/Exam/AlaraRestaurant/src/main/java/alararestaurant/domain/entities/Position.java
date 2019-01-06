package alararestaurant.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "positions")
public class Position extends BaseEntity {

    @Column(name = "name", nullable = false)
    @Length(min = 3, max = 30)
    private String name;

    @OneToMany(mappedBy = "position", targetEntity = Employee.class)
    private List<Employee> employees;

    public Position() {
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
