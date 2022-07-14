package com.example.demo.model.dto.listFilling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateListsFillingDto {


    private Double id_list;
    private String name_product;
    private Boolean completed;
    private Double id_buyer;

}
