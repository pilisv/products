package com.tektonlabs.products;

import com.tektonlabs.products.controller.ProductController;
import com.tektonlabs.products.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void whenCreateProduct_Success() throws Exception {
        String product = "{\n" +
                "    \"name\":\"emperador\",\n" +
                "    \"price\": 21.4,\n" +
                "    \"quantity\": 30,\n" +
                "    \"idBrand\" : 2,\n" +
                "    \"detail\" : {\n" +
                "        \"description\": \"chocolate flavor\",\n" +
                "        \"color\": \"black\",\n" +
                "        \"width\": 5,\n" +
                "        \"high\": 10,\n" +
                "        \"large\": 15,\n" +
                "        \"weight\": 20\n" +
                "    }\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                        .content(product)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());
    }

}
