package com.addressbook.service;

import com.addressbook.entity.AddressBook;
import com.addressbook.model.RequestCreateUsers;
import com.addressbook.model.RequestListUniqueUsers;
import com.addressbook.repository.AddressBookRepository;
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

    public List<AddressBook> listUniqueAddressBook(RequestListUniqueUsers requestListUniqueUsers){
        List<AddressBook> addressBookListRequested = requestListUniqueUsers.getAddressBookList();
        List<AddressBook> addressBookListSaved = addressBookRepository.findAll();
        List<AddressBook> addressBookResult = filterOutUniqueUsers(addressBookListRequested, addressBookListSaved);
        return addressBookResult;
    }

    private List<AddressBook> filterOutUniqueUsers(List<AddressBook> list1, List<AddressBook> list2){
        Set<AddressBook> set1 = new HashSet<>(list1);
        Set<AddressBook> set2 = new HashSet<>(list2);
        list1.removeAll(set2);
        list2.removeAll(set1);
        list1.addAll(list2);
        return list1;
    }

//    public static void main(String[] args){
//        List<AddressBook> listOne = new ArrayList<>(Arrays.asList(new AddressBook(1, "Mary", "001"), new AddressBook(2, "John", "002"), new AddressBook(3, "K", "003")));
//        List<AddressBook> listTwo = new ArrayList<>(Arrays.asList(new AddressBook(1, "Mary", "001"), new AddressBook(2, "John", "002"), new AddressBook(3, "Jane", "003")));
//
//        Set<AddressBook> setOne = new HashSet<>(listOne);
//        Set<AddressBook> setTwo = new HashSet<>(listTwo);
//
//        listOne.removeAll(setTwo);
//        listTwo.removeAll(setOne);
//
//        listOne.addAll(listTwo);
//        listOne.forEach(e -> System.out.println(e.getName()));
//
//
//
//    }
}
