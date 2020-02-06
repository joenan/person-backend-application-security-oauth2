package com.embl.ebi.person;


import com.embl.ebi.person.controller.PersonController;
import com.embl.ebi.person.model.Person;
import com.embl.ebi.person.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;


@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonService personService;

    @Autowired
    PersonController personController;

    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testAddPerson() throws Exception {

        Person p = new Person();
        p.setFirst_name("Stanley");
        p.setLast_name("Philips");
        p.setAge("45");

        p.setHobby(Arrays.asList("Dancing", "Music"));


        Mockito.when(personService.save(any(Person.class))).thenReturn(p);

        mockMvc.perform(post("/api/v1/person")
                .content(mapper.writeValueAsString(p))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name").value(p.getFirst_name()))
                .andExpect(jsonPath("$.last_name").value(p.getLast_name()))
                .andExpect(jsonPath("$.age").value(p.getAge()));

    }

    @Test
    public void findAllPersons() throws Exception {
        // given
        Person p = new Person();
        p.setFirst_name("Stanley");
        p.setLast_name("Philips");
        p.setAge("45");
        p.setHobby(Arrays.asList("Dancing", "Music"));


        Person p2 = new Person();
        p2.setFirst_name("John");
        p2.setLast_name("Wesley");
        p2.setAge("60");
        p2.setHobby(Arrays.asList("Dancing", "Music"));

        List<Person> list = Arrays.asList(p, p2);

        Mockito.when(personService.findAllPersons()).thenReturn(list);

        // when + then
        this.mockMvc.perform(get("/api/v1/person"))
                .andExpect(jsonPath("$.[1].first_name", equalTo("John")))
                .andExpect(jsonPath("$.[1].last_name", equalTo("Wesley")))
                .andExpect(jsonPath("$.[1].age", equalTo("60")))

                .andExpect(jsonPath("$.[0].first_name", equalTo("Stanley")))
                .andExpect(jsonPath("$.[0].last_name", equalTo("Philips")))
                .andExpect(jsonPath("$.[0].age", equalTo("45")))


                .andExpect(status().isOk());

        verify(personService).findAllPersons();
        verifyNoMoreInteractions(personService);

    }

    @Test
    public void findAllUsersById()throws Exception {

        Person p = new Person();
        p.setFirst_name("Stanley");
        p.setLast_name("Philips");
        p.setAge("45");
        p.setHobby(Arrays.asList("Dancing", "Music"));



        Mockito.when(personService.findPersonById(anyLong())).thenReturn(Optional.ofNullable(p));


        mockMvc.perform(get("/api/v1/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name").value(p.getFirst_name()))
                .andExpect(jsonPath("$.last_name").value(p.getLast_name()))
                .andExpect(jsonPath("$.age").value(p.getAge()));
    }

    @Test
    public void deleteUserById() throws Exception {
        Person p = new Person();
        p.setFirst_name("Stanley");
        p.setLast_name("Philips");
        p.setAge("45");
        p.setHobby(Arrays.asList("Dancing", "Music"));



        Mockito.when(personService.findPersonById(anyLong())).thenReturn(Optional.ofNullable(p));

        mockMvc.perform(delete("/api/v1/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserById() throws Exception {
        Person p = new Person();
        p.setFirst_name("Stanley");
        p.setLast_name("Philips");
        p.setAge("45");
        p.setHobby(Arrays.asList("Dancing", "Music"));



        Mockito.when(personService.findPersonById(anyLong())).thenReturn(Optional.ofNullable(p));

        mockMvc.perform(delete("/api/v1/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
