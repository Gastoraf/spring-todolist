package com.example.demo.model.dto.listpermission;

import com.example.demo.model.entity.MyList;
import com.example.demo.model.entity.MyPermission;
import com.example.demo.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListPermissionDto {

    private Long id;

    private MyList lists;

    private User user;

    private MyPermission myPermission;
}
