package com.example.demo.model.dto;

import com.example.demo.model.entity.ListsFilling;
import com.example.demo.model.entity.ProductComments;
import com.example.demo.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String name;

    private boolean active;

    private String email;

    private String activation_code;

    private List<ListsFilling> listsFillings;

    private List<ProductComments> productComments;

    private String password;

    private Set<Role> roles = new HashSet<>();

    private LocalDateTime dateOfCreated;
}
