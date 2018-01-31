package com.bisratm.addressbook.addressbook;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepoistory extends CrudRepository <AddressC,Long> {
    Iterable<AddressC>findAllByName(String search);
}
