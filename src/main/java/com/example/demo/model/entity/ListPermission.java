package com.example.demo.model.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "list_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_list")
    private MyList lists;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permission")
    private MyPermission myPermission;


}
