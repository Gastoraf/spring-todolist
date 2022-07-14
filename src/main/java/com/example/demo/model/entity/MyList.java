package com.example.demo.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

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
    @ToString.Exclude
    private List<ListsFilling> listsFillings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MyList myList = (MyList) o;
        return id != null && Objects.equals(id, myList.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
