package com.addressbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Table(name="addressbook")
@JsonIgnoreProperties(value = { "id" })
public class AddressBook {

    @Id
    @GeneratedValue(strategy= IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @Column(nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(nullable = false)
    @JsonProperty("phoneNumber")
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AddressBook))
            return false;
        AddressBook addressBook = (AddressBook) o;
        return addressBook.getName().equals(name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        return result;
    }
}
