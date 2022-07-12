package com.example.demo.service.listPermission;

import com.example.demo.model.entity.ListPermission;
import com.example.demo.model.entity.MyList;
import com.example.demo.model.entity.User;
import com.example.demo.repositorie.ListPermissionRepository;
import com.example.demo.repositorie.MyListRepository;
import com.example.demo.repositorie.UserRepository;
import com.example.demo.service.myList.MyListService;
import com.example.demo.service.myPermission.MyPermissionService;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListPermissionServiceImpl implements ListPermissionService {
    private final ListPermissionRepository listPermissionRepository;
    private final UserRepository userRepository;
    private final MyListRepository myListRepository;

    private final UserService userService;
    private final MyListService myListService;
    private final MyPermissionService myPermissionService;

    @Override
    public void addListPermission(ListPermission listPermission) {
        log.info("Saving new ListPermission {}", listPermission);
        listPermissionRepository.save(listPermission);
    }

    @Override
    public void addListPermission(MyList myList, HttpServletRequest request) {
        ListPermission listPermission = new ListPermission();
        listPermission.setLists(myList);
        listPermission.setUser(userService.getUserByName(request.getRemoteUser()));
        listPermission.setMyPermission(myPermissionService.getMyPermissionById(1L));
        listPermissionRepository.save(listPermission);
    }

    @Override
    public void addListPermission(String nameUser, Long idList) {
        ListPermission listPermission = new ListPermission();

        listPermission.setUser(userService.getUserByName(nameUser));
        listPermission.setLists(myListService.getMyListById(idList));
        listPermission.setMyPermission(myPermissionService.getMyPermissionById(2L));
        listPermissionRepository.save(listPermission);
    }

    @Override
    public void deleteListPermissionById(Long id){
        log.info("Delete ListPermission by id: {}", id);
        listPermissionRepository.deleteById(id);
    }

    @Override
    public void deleteById_userAndId_list(Long id_user, Long id_list){
        log.info("Delete ListPermission by id: {}", id_user);
        ListPermission listPermission = listPermissionRepository.findByUserAndLists(userRepository.getById(id_user), myListRepository.getById(id_list));
        listPermissionRepository.deleteById(listPermission.getId());
    }

    @Override
    public ListPermission getListPermissionById(Long id){
        log.info("Get ListPermission by id: {}", id);
        //TODO: Здесь можно исключение провернуть
        return listPermissionRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getUsersListPermissionById(Long idList){
        log.info("Get getUsersListPermissionById: {}", idList);
        //TODO: Здесь можно исключение провернуть
        return userRepository.findUsersByIdList(idList);
    }
}
