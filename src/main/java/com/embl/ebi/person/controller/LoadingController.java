package com.embl.ebi.person.controller;


import com.embl.ebi.person.PersonApplication;
import com.embl.ebi.person.model.Person;
import com.embl.ebi.person.service.OauthService;
import com.embl.ebi.person.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadingController implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(PersonApplication.class);
    @Autowired
    OauthService service;
    @Autowired
    PersonService personService;

    public void executeScripts() {

        LOG.info("Loading Test data into Person");

        Person p = new Person();
        p.setLast_name("Johnson");
        p.setFirst_name("Saleh");
        p.setAge("34");
        personService.save(p);
        LOG.info("Created First Person");

        Person p2 = new Person();
        p2.setLast_name("Salim");
        p2.setFirst_name("Mohammed");
        p2.setAge("50");
        personService.save(p2);
        LOG.info("Created Second Person");

//
//
//
//        LOG.info("Creating Permissions");
//
//        Permission permission1 = new Permission();
//        permission1.setName("create_person");
//
//        Permission permission2 = new Permission();
//        permission2.setName("read_person");
//
//        Permission permission3 = new Permission();
//        permission3.setName("edit_person");
//
//        Permission permission4 = new Permission();
//        permission4.setName("delete_person");
//
//        List<Permission> permissionList = Arrays.asList(permission1, permission2, permission3, permission4);
//        service.saveAllPermission(permissionList);
//
//        LOG.info("Permissions Created Successfully");
//
//
//        LOG.info("Creating User Roles");
//        Role role = new Role();
//        role.setName("ADMIN");
//        role.setPermissions(permissionList);
//
//        Role role2 = new Role();
//        role2.setName("USER");
//        role2.setPermissions(permissionList);
//
//        List<Role> roleList = Arrays.asList(role, role2);
//        service.saveAllRoles(roleList);
//
//        LOG.info("User Roles Created Successfully");


    }

    @Override
    public void run(String... args) throws Exception {
        executeScripts();
    }
}
