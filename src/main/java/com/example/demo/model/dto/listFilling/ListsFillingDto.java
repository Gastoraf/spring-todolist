package com.example.demo.model.dto.listFilling;

import com.example.demo.model.entity.MyList;
import com.example.demo.model.entity.ProductComments;
import com.example.demo.model.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListsFillingDto {

    private Long id;

    private String name_product;

    private Integer quantity;

    private String unit;

    private Double price;

    private Double actual_price;

    private String currency;

    private String category;

    private String description;

    private Boolean completed;

    private List<ProductComments> productComments;

    private MyList lists;

    private User user;

}
