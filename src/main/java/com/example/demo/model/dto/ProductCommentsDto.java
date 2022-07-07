package com.example.demo.model.dto;

import com.example.demo.model.entity.ListsFilling;
import com.example.demo.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCommentsDto {

    private Long id;

    private String comment;

    private User user;

    private ListsFilling list_filling;
}
