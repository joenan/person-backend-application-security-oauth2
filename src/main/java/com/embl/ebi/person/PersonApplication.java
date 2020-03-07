package com.embl.ebi.person;

import com.embl.ebi.person.model.Permission;
import com.embl.ebi.person.model.Role;
import com.embl.ebi.person.service.OauthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableResourceServer
public class PersonApplication  {
    public static void main(String[] args) {
        SpringApplication.run(PersonApplication.class, args);
    }

}
