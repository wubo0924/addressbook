package com.addressbook.model;

import com.addressbook.entity.AddressBook;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public abstract class HttpDataBase {
    private List<AddressBook> addressBookList;
}
