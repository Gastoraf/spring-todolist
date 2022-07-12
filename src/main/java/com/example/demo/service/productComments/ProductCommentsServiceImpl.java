package com.example.demo.service.productComments;

import com.example.demo.model.entity.ListsFilling;
import com.example.demo.model.entity.ProductComments;
import com.example.demo.model.entity.User;
import com.example.demo.repositorie.ProductCommentsRepository;
import com.example.demo.service.listFilling.ListFillingService;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCommentsServiceImpl implements ProductCommentsService {
    private final ProductCommentsRepository productCommentsRepository;

    private final ListFillingService listFillingService;
    private final UserService userService;

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
    public void saveProductComment(HttpServletRequest request, Long idProduct, String comment) {
        User user = userService.getUserByName(request.getRemoteUser());
        ListsFilling listsFilling = listFillingService.getListFillingById(idProduct);
        ProductComments productComments = new ProductComments();
        productComments.setComment(comment);
        productComments.setUser(user);
        productComments.setList_filling(listsFilling);
        productCommentsRepository.save(productComments);
    }

    @Override
    public void deleteProductCommentById(Long idProductComment){
        log.info("Delete Product Comment by id: {}", idProductComment);
        productCommentsRepository.deleteById(idProductComment);
    }


}
