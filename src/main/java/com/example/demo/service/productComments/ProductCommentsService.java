package com.example.demo.service.productComments;

import com.example.demo.model.entity.ProductComments;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface ProductCommentsService {

    List<ProductComments> findByIdListsFilling(Long idProduct);

    void saveProductComment(ProductComments productComments);

    void saveProductComment(HttpServletRequest request, Long idProduct, String comment);

    void deleteProductCommentById(Long idProductComment);


}
