package alararestaurant.domain.entities;

import alararestaurant.enums.OrderType;

import javax.persistence.*;
import java.util.*;

@Entity(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "customer", nullable = false)
    private String customer;

    @Column(name = "date_time", nullable = false)
    private Date dateTime;

    @Column(name = "order_type", columnDefinition = "varchar(32) default 'ForHere'", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OrderType orderType;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "order", targetEntity = OrderItem.class)
    private List<OrderItem> orderItems;

    public Order() {
        this.orderItems = new ArrayList<>();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
