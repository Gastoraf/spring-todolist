package com.example.demo;


import com.example.demo.controller.ListController;
import com.example.demo.controller.HomeController;
import com.example.demo.repositorie.ListFillingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class DemoApplicationTests {

    @Autowired private HomeController homeController;
    @Autowired private ListController listController;
    @Autowired private ListFillingRepository listFillingRepository;

//    @BeforeEach
//    public void clear(){
//        listFillingRepository.deleteAll();
//    }


//    @Test
//    public void CreateLord_LordDtoValid_CreatedSuccessfully(){
//        CreateListsFillingDto createListsFillingDto = new CreateListsFillingDto(anyString());
//
//        int size =  listFillingRepository.findAll().size();
//
//        HttpServletRequest httpServletRequest = new MockHttpServletRequest(anyString(), anyString());
//
//        //mainController.addListFilling(anyLong(), createListsFillingDto, httpServletRequest);
//
//        assertEquals(size+1, listFillingRepository.findAll().size());
//    }

//    @Test
//    public void shouldMapCarToDto() {
//        //given
////        Products products = new Products();
////        products.setPrice(3);
////        products.setName("table");
////
////        //when
////        ProductsDto productsDto = ProductsMapper.INSTANCE.toDto(products);
////
////        //then
////        assertThat(productsDto).isNotNull();
////        assertThat(productsDto.getName()).isEqualTo("table");
////        assertThat(productsDto.getPrice()).isEqualTo(3);
//
//    }


}
