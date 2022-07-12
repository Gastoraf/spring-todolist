package com.example.demo.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Entity
@Table(name = "lists_filling")
@Data
@Getter
@Setter
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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "list_filling")
    private List<ProductComments> productComments;

//    @NotNull
//    @ToString.Exclude
//    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
//    @JoinColumn(name = "id_list")
//    private MyList lists;

    @ManyToOne
    @JoinColumn(name = "id_list")
    private MyList myList;

    @Null
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_buyer")
    private User user;


    public MyList getLists() {
        return myList;
    }

    public void setLists(MyList myList) {
        this.myList = myList;
    }
}
