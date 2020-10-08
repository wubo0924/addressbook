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
        List<AddressBook> addressBookList = addressBookService.listAddressBook();
        ResponseListUsers responseListUsers = new ResponseListUsers();
        responseListUsers.setAddressBookList(addressBookList);
        return ResponseEntity.ok().body(responseListUsers);
    }


    @Override
    public ResponseEntity<ResponseListUsers> createUsers(RequestCreateUsers requestCreateUsers) {
        List<AddressBook> addressBookList = addressBookService.createAddressBook(requestCreateUsers);
        ResponseListUsers responseListUsers = new ResponseListUsers();
        responseListUsers.setAddressBookList(addressBookList);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseListUsers);
    }

    @Override
    public ResponseEntity<ResponseListUsers> listUniqueUsers(RequestListUniqueUsers requestListUniqueUsers) {
        List<AddressBook> addressBookList = addressBookService.listUniqueAddressBook(requestListUniqueUsers);
        ResponseListUsers responseListUsers = new ResponseListUsers();
        responseListUsers.setAddressBookList(addressBookList);
        return ResponseEntity.ok().body(responseListUsers);
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String test(){
//        return "Test";
//    }
}
