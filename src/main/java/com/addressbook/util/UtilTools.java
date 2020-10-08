package com.addressbook.util;

import com.addressbook.entity.AddressBook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UtilTools {

    public static List<AddressBook> filterOutUniqueUsers(List<AddressBook> list1, List<AddressBook> list2) throws Exception{
        Set<AddressBook> set1 = new HashSet<>(list1);
        Set<AddressBook> set2 = new HashSet<>(list2);
        list1.removeAll(set2);
        list2.removeAll(set1);
        list1.addAll(list2);
        return list1;
    }
}
