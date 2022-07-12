package com.example.demo.model.dto.myList;

import com.example.demo.model.entity.ListsFilling;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyListDto {

    private Long id;

    private String name;

    private String date_of_purchase;

    private List<ListsFilling> listsFillings;
}
