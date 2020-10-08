package com.addressbook;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressBookApplicationTests {

    private static final String LIST_OR_CREATE_USERS_URI = "/users";
    private static final String LIST_UNIQUE_USERS_URI = "/users/unique";

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void testListUsers() throws Exception{
        MvcResult mvcResult = mvc.perform(
                get(LIST_OR_CREATE_USERS_URI)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressBookList").isArray())
                .andExpect(jsonPath("$.addressBookList", hasSize(3)))
                .andReturn();
    }

    @Test
    @Order(2)
    public void testCreateUsers() throws Exception{
        MvcResult mvcResult = mvc.perform(
                post(LIST_OR_CREATE_USERS_URI)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"addressBookList\": [\n" +
                        "        {\n" +
                        "\n" +
                        "            \"name\": \"K1\",\n" +
                        "            \"phoneNumber\": \"9999\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "\n" +
                        "            \"name\": \"K2\",\n" +
                        "            \"phoneNumber\": \"8877\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "\n" +
                        "            \"name\": \"K3\",\n" +
                        "            \"phoneNumber\": \"2324\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}")
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.addressBookList").isArray())
                .andExpect(jsonPath("$.addressBookList", hasSize(3)))
                .andReturn();
    }

    @Test
    @Order(3)
    public void testListUniqueUsers() throws Exception{
        mvc.perform(post(LIST_UNIQUE_USERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"addressBookList\": [\n" +
                        "        {\n" +
                        "\n" +
                        "            \"name\": \"Bob\",\n" +
                        "            \"phoneNumber\": \"9999\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "\n" +
                        "            \"name\": \"John\",\n" +
                        "            \"phoneNumber\": \"324324234\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "\n" +
                        "            \"name\": \"Jane\",\n" +
                        "            \"phoneNumber\": \"2324\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressBookList").isArray())
                .andExpect(jsonPath("$.addressBookList", hasSize(5)))
                .andReturn();
    }

}
