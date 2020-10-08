package com.addressbook.controller;

import com.addressbook.entity.AddressBook;
import com.addressbook.model.RequestCreateUsers;
import com.addressbook.model.RequestListUniqueUsers;

import com.addressbook.model.ResponseListUsers;

import com.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressBookController implements AddressBookApi{


    @Autowired
    private AddressBookService addressBookService;

    @Override
    public ResponseEntity<ResponseListUsers> listUsers() {
        try{
            List<AddressBook> addressBookList = addressBookService.listAddressBook();
            ResponseListUsers responseListUsers = new ResponseListUsers();
            responseListUsers.setAddressBookList(addressBookList);
            return ResponseEntity.ok().body(responseListUsers);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @Override
    public ResponseEntity<ResponseListUsers> createUsers(RequestCreateUsers requestCreateUsers) {
        try{
            List<AddressBook> addressBookList = addressBookService.createAddressBook(requestCreateUsers);
            ResponseListUsers responseListUsers = new ResponseListUsers();
            responseListUsers.setAddressBookList(addressBookList);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseListUsers);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Override
    public ResponseEntity<ResponseListUsers> listUniqueUsers(RequestListUniqueUsers requestListUniqueUsers) {
        try {
            List<AddressBook> addressBookList = addressBookService.listUniqueAddressBook(requestListUniqueUsers);
            ResponseListUsers responseListUsers = new ResponseListUsers();
            responseListUsers.setAddressBookList(addressBookList);
            return ResponseEntity.ok().body(responseListUsers);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Override
    public ResponseEntity clearUsers() {
        try{
            addressBookService.clearUsers();
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
