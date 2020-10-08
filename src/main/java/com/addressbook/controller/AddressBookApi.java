package com.addressbook.controller;

import com.addressbook.entity.AddressBook;

import com.addressbook.model.RequestCreateUsers;
import com.addressbook.model.RequestListUniqueUsers;

import com.addressbook.model.ResponseListUsers;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


public interface AddressBookApi {
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseListUsers> listUsers();

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseListUsers> createUsers(@RequestBody RequestCreateUsers requestCreateUsers);


    @PostMapping(value = "/users/unique", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseListUsers> listUniqueUsers(@RequestBody RequestListUniqueUsers requestListUniqueUsers);
}
