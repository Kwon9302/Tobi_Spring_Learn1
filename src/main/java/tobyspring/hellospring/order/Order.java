package tobyspring.hellospring.order;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String no;


    private BigDecimal total;

    public Order(String no, BigDecimal total) {
        this.no = no;
        this.total = total;
    }

    public Order() {

    }
}
