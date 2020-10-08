package com.addressbook.service;

import com.addressbook.entity.AddressBook;
import com.addressbook.model.RequestCreateUsers;
import com.addressbook.model.RequestListUniqueUsers;
import com.addressbook.repository.AddressBookRepository;
import com.addressbook.util.UtilTools;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AddressBookService {
    private AddressBookRepository addressBookRepository;

    public List<AddressBook> listAddressBook(){
        return addressBookRepository.findAll();
    }

    public List<AddressBook> createAddressBook(RequestCreateUsers requestCreateUsers){
        List<AddressBook> addressBookList = requestCreateUsers.getAddressBookList();
        return addressBookRepository.saveAll(addressBookList);

    }

    public List<AddressBook> listUniqueAddressBook(RequestListUniqueUsers requestListUniqueUsers) throws Exception{
        List<AddressBook> addressBookListRequested = requestListUniqueUsers.getAddressBookList();
        List<AddressBook> addressBookListSaved = addressBookRepository.findAll();
        List<AddressBook> addressBookResult = UtilTools.filterOutUniqueUsers(addressBookListRequested, addressBookListSaved);
        return addressBookResult;
    }

    public void clearUsers(){
        addressBookRepository.deleteAll();
    }


}
