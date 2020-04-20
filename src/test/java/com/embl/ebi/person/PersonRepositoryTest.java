package com.embl.ebi.person;

import com.embl.ebi.person.model.Person;
import com.embl.ebi.person.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;


    @Test
    public void testSavePerson() {

        Person person = new Person();
        person.setFirst_name("Stanley");
        person.setLast_name("Philips");
        person.setAge("45");
        person.setHobby(Arrays.asList("Dancing", "Music"));

        Person savedPerson = personRepository.save(person);

        Assert.assertNotNull(savedPerson);
        Assertions.assertEquals(savedPerson.getFirst_name(), person.getFirst_name());
        Assertions.assertEquals(savedPerson.getLast_name(), person.getLast_name());

    }

    @Test
    public void testGetPersonById() {

        Person person = new Person();
        person.setFirst_name("Stanley");
        person.setLast_name("Philips");
        person.setAge("45");
        person.setHobby(Arrays.asList("Dancing", "Music"));

        Person savedPerson = personRepository.save(person);

        Optional<Person> personWithId = personRepository.findById(savedPerson.getId());
        Assertions.assertNotNull(personWithId);
        Assertions.assertEquals(personWithId.get().getFirst_name(), person.getFirst_name());
    }

    @Test
    public void findAllPersons() {

        Person person = new Person();
        person.setFirst_name("Stanley");
        person.setLast_name("Philips");
        person.setAge("45");
        person.setHobby(Arrays.asList("Dancing", "Music"));

        Person savedPerson = personRepository.save(person);

        List<Person> personList = personRepository.findAll();
        Assertions.assertNotNull(personList);
    }

    @Test
    public void deletePersonById() {
        Person person = new Person();
        person.setFirst_name("Stanley");
        person.setLast_name("Philips");
        person.setAge("45");
        person.setHobby(Arrays.asList("Dancing", "Music"));

        Person savedPerson = personRepository.save(person);
        personRepository.deleteById(person.getId());
    }


}
