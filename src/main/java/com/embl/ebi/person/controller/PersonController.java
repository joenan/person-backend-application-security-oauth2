package com.embl.ebi.person.controller;

import com.embl.ebi.person.exception.RecordNotFoundException;
import com.embl.ebi.person.model.Person;
import com.embl.ebi.person.service.PersonService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    PersonService service;

    @ApiOperation(value = "Create Person")
    @PostMapping("/person")
    public ResponseEntity<?> savePerson(@RequestBody @Valid Person request) {
        log.info("Sending Person {} from Client with Values " + "FirstName: " + request.getFirst_name() + " LastName: " + request.getLast_name() + " Age: " + request.getAge() + "Hobby" + request.getHobby());

        if (request == null) {
            log.info("Person {} is empty or Null");
            return ResponseEntity.badRequest().body("Request is empty");

        }
        Person savedPersonObject = service.save(request);
        return ResponseEntity.ok().body(savedPersonObject);
    }

    @ApiOperation(value = "Get List of Persons")
    @GetMapping("/person")
    public ResponseEntity<?> findAllPersons() {
        log.info("Getting all Persons");
        List<Person> list = service.findAllPersons();
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "Get Person by Id")
    @GetMapping("/person/{id}")
    public ResponseEntity<Person> findPersonsById(@PathVariable("id") Long id) {
        log.info("Getting Person {} with Id " + id);
        return service.findPersonById(id).map(record -> ResponseEntity.ok().body(record))
                .orElseThrow(() -> new RecordNotFoundException("User Searched Not Found: " + id));
    }


    @ApiOperation(value = "Delete Person by Id")
    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> deletePersonCode(@PathVariable("id") Long id) {
        log.info("Deleting Person {} with Id " + id);
        return service.findPersonById(id)
                .map(record -> {
                    service.deletePersonById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new RecordNotFoundException("User To Delete Not Found: " + id));
    }

    @ApiOperation(value = "Edit Person by ID")
    @PutMapping("/person/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable("id") Long id, @RequestBody Person data) {
        log.info("Updating Person {} with Id " + id);
        return service.findPersonById(id)
                .map(record -> {
                    record.setAge(data.getAge());
                    record.setFirst_name(data.getFirst_name());
                    record.setHobby(data.getHobby());
                    record.setLast_name(data.getLast_name());
                    Person updated = service.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}
