package com.example.demo.model.entity;

import com.example.demo.model.entity.ListsFilling;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lists")
public class MyList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_purchase")
    private String date_of_purchase;

//    @OneToMany(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL, mappedBy = "lists")
//    @OrderColumn
//    private List<ListsFilling> listsFillings;

    @OneToMany(mappedBy = "myList")
    private List<ListsFilling> listsFillings;
}
