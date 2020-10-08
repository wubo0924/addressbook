package com.addressbook;

import com.addressbook.entity.AddressBook;
import com.addressbook.util.UtilTools;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressBookApplicationTests {

    private static final String LIST_OR_CREATE_USERS_URI = "/users";
    private static final String LIST_UNIQUE_USERS_URI = "/users/unique";
    private static final String CLEAR_USERS_URI = "/users/clear";

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

    @Test
    @Order(4)
    public void testClearUsers() throws Exception{
        mvc.perform(delete(CLEAR_USERS_URI))
                .andExpect(status().isNoContent());
    }


    @Test
    @Order(5)
    public void testBadRequestForCreateUsers() throws Exception {
        mvc.perform(post(LIST_OR_CREATE_USERS_URI)
                .accept(MediaType.APPLICATION_JSON)//Missing request body
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @Order(6)
    public void testUnsupportedMediaTypeForCreateUsers() throws Exception{
        mvc.perform(post(LIST_OR_CREATE_USERS_URI)
                .accept(MediaType.APPLICATION_JSON))//Missy content-type header
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    @Order(7)
    public void testBadRequestForListUniqueUsers() throws Exception{
        mvc.perform(post(LIST_UNIQUE_USERS_URI)
                .accept(MediaType.APPLICATION_JSON)//Missing request body
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(8)
    public void testUnsupportedMediaTypeForListUniqueUsers() throws Exception{
        mvc.perform(post(LIST_UNIQUE_USERS_URI)
                .accept(MediaType.APPLICATION_JSON))//Missy content-type header
                .andExpect(status().isUnsupportedMediaType());
    }


    @Test
    @Order(9)
    public void testPassNullToUntilTools() throws Exception {
        try{
            UtilTools.filterOutUniqueUsers(null, null);
        }catch (Exception e){
            Assertions.assertTrue(e instanceof NullPointerException);
        }

    }

    @Test
    @Order(10)
    public void testReturnEmptyWithBothEmptyLists() throws Exception{
        List<AddressBook> list = UtilTools.filterOutUniqueUsers(new ArrayList<>(), new ArrayList<>());
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    @Order(11)
    public void testReturnEmptyWithBothSameLists() throws Exception{
        List<AddressBook> list1 = new ArrayList<>(Arrays.asList(new AddressBook(1, "Bob", "987623"), new AddressBook(2, "Mary", "0238492"), new AddressBook(3, "Jane", "0923042304")));
        List<AddressBook> list2 = new ArrayList<>(Arrays.asList(new AddressBook(1, "Bob", "987623"), new AddressBook(2, "Mary", "0238492"), new AddressBook(3, "Jane", "0923042304")));
        List<AddressBook> list = UtilTools.filterOutUniqueUsers(list1, list2);
        Assertions.assertTrue(list.isEmpty());
    }


}
