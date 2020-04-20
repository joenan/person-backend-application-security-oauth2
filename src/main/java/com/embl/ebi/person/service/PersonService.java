package com.embl.ebi.person.service;

import com.embl.ebi.person.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    public Person save(Person person);

    List<Person> findAllPersons();

    Optional<Person> findPersonById(Long id);

    void deletePersonById(Long id);
}
