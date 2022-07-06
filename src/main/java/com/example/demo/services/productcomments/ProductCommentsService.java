package com.example.demo.services.productcomments;

import com.example.demo.model.ProductComments;

import java.util.List;


public interface ProductCommentsService {

    public List<ProductComments> findByIdListsFilling(Long idProduct);

    public void saveProductComment(ProductComments productComments);

    public void deleteProductCommentById(Long idProductComment);
}
