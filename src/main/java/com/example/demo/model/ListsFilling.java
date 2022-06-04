package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;

@Entity
@Table(name = "lists_filling")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListsFilling {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_product")
    private String name_product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit")
    private String unit;

    @Column(name = "price")
    private Double price;

    @Column(name = "actual_price")
    private Double actual_price;

    @Column(name = "currency")
    private String currency;

    @Column(name = "category")
    private String category;

    @Null
    @Column(name = "description")
    private String description;

    @Column(name = "completed")
    private Boolean completed;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "list_filling")
    private List<ProductComments> productComments;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_list")
    private MyList lists;

    @Null
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_buyer")
    private User user;


    public MyList getLists() {
        return lists;
    }

    public void setLists(MyList lists) {
        this.lists = lists;
    }
}
