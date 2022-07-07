package com.example.demo;

import com.example.demo.model.dto.ProductsDto;
import com.example.demo.model.entity.Products;
import com.example.demo.model.mapping.ProductsMapper;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class DemoApplicationTests {


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
