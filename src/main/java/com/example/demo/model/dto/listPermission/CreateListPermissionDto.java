package com.example.demo.model.dto.listPermission;

import com.example.demo.model.entity.MyList;
import com.example.demo.model.entity.MyPermission;
import com.example.demo.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateListPermissionDto {

    @NotNull
    @NotEmpty
    private MyList lists;

    @NotNull
    @NotEmpty
    private User user;

    @NotNull
    @NotEmpty
    private MyPermission myPermission;
}
