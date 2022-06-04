package com.example.demo.services;

import com.example.demo.model.ListsFilling;
import com.example.demo.model.MyList;
import com.example.demo.model.ProductComments;
import com.example.demo.repositories.MyPermissionRepository;
import com.example.demo.repositories.ProductCommentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCommentsService {
    private final ProductCommentsRepository productCommentsRepository;

    public List<ProductComments> findByIdListsFilling(Long idProduct) {
        return productCommentsRepository.findByIdListsFilling(idProduct);
    }

    public void saveProductComment(ProductComments productComments) {
        log.info("Saving Product Comment {}", productComments);
        productCommentsRepository.save(productComments);
    }

    public void deleteProductCommentById(Long idProductComment){
        log.info("Delete Product Comment by id: {}", idProductComment);
        productCommentsRepository.deleteById(idProductComment);
    }
}
