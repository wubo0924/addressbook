package com.addressbook.repository;

import com.addressbook.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AddressBookRepository extends JpaRepository<AddressBook, Integer> {

    List<AddressBook> findByName(String name);
}
