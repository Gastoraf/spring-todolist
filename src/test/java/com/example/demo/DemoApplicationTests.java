package com.example.demo;


import com.example.demo.controller.ListController;
import com.example.demo.controller.MainController;
import com.example.demo.model.dto.products.ProductsDto;
import com.example.demo.model.dto.listfilling.CreateListsFillingDto;
import com.example.demo.model.entity.Products;
import com.example.demo.model.mapping.ProductsMapper;
import com.example.demo.repositories.ListFillingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class DemoApplicationTests {

    @Autowired private MainController mainController;
    @Autowired private ListController listController;
    @Autowired private ListFillingRepository listFillingRepository;

    @BeforeEach
    public void clear(){
        listFillingRepository.deleteAll();
    }


    @Test
    public void CreateLord_LordDtoValid_CreatedSuccessfully(){
        CreateListsFillingDto createListsFillingDto = new CreateListsFillingDto(anyString());

        int size =  listFillingRepository.findAll().size();

        HttpServletRequest httpServletRequest = new MockHttpServletRequest(anyString(), anyString());

        //mainController.addListFilling(anyLong(), createListsFillingDto, httpServletRequest);

        assertEquals(size+1, listFillingRepository.findAll().size());
    }

    @Test
    public void shouldMapCarToDto() {
        //given
        Products products = new Products();
        products.setPrice(3);
        products.setName("table");

        //when
        ProductsDto productsDto = ProductsMapper.INSTANCE.toDto(products);

        //then
        assertThat(productsDto).isNotNull();
        assertThat(productsDto.getName()).isEqualTo("table");
        assertThat(productsDto.getPrice()).isEqualTo(3);

    }


}
