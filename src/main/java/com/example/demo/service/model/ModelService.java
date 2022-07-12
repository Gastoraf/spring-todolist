package com.example.demo.service.model;

import com.example.demo.model.dto.listFilling.ListsFillingDto;
import com.example.demo.model.dto.productcomments.ProductCommentsDto;
import com.example.demo.model.entity.MyList;
import com.example.demo.service.myList.MyListService;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ModelService {
    void addAttributeInfoProduct(Model model, ListsFillingDto listsFillingDto, MyList myList);

    void addAttributeCommentsProduct(Model model, List<ProductCommentsDto> productCommentsDtos, ListsFillingDto listsFillingDto);

    void getMyLists(Model model, List<MyList> myLists);

    void getInfoMyList(Model model, Long id);
}
