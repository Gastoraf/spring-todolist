package com.example.demo.services.productcomments;

import com.example.demo.model.entity.ProductComments;
import com.example.demo.repositories.ProductCommentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCommentsServiceImpl implements ProductCommentsService {
    private final ProductCommentsRepository productCommentsRepository;

    @Override
    public List<ProductComments> findByIdListsFilling(Long idProduct) {
        return productCommentsRepository.findByIdListsFilling(idProduct);
    }

    @Override
    public void saveProductComment(ProductComments productComments) {
        log.info("Saving Product Comment {}", productComments);
        productCommentsRepository.save(productComments);
    }

    @Override
    public void deleteProductCommentById(Long idProductComment){
        log.info("Delete Product Comment by id: {}", idProductComment);
        productCommentsRepository.deleteById(idProductComment);
    }
}
