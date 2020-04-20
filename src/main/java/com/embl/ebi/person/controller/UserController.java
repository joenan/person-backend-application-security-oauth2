package com.embl.ebi.person.controller;

import com.embl.ebi.person.model.User;
import com.embl.ebi.person.model.oauthmodels.OauthClientDetails;
import com.embl.ebi.person.repository.UserDetailRepository;
import com.embl.ebi.person.service.OauthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    OauthService oauthService;
    @Autowired
    private UserDetailRepository userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value = "Get All Active Users")
    @GetMapping("/user")
    public Object getAllUser() {
        List<User> userInfos = userService.findAll();
        if (userInfos == null || userInfos.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return userInfos;
    }

    @ApiOperation(value = "Create new User")
    @PostMapping("/user")
    public ResponseEntity addUser(@RequestBody User userRecord) {

        if (userRecord == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Null Object was submitted"));
        }

        String encodedPassword = passwordEncoder.encode(userRecord.getPassword());
        String encodedClientSecret = passwordEncoder.encode(userRecord.getClientSecret());

        userRecord.setPassword(encodedPassword);

        OauthClientDetails clientDetails = new OauthClientDetails();
        clientDetails.setAccessTokenValidity(3600);
        clientDetails.setAutoapprove("");
        clientDetails.setAdditionalInformation("{}");
        clientDetails.setAuthorizedGrantTypes("authorization_code,password,refresh_token,implicit");
        clientDetails.setClientId(userRecord.getClientId());
        clientDetails.setClientSecret(encodedClientSecret);
        clientDetails.setRefreshTokenValidity(10000);
        clientDetails.setResourceIds("person-rest-api");
        clientDetails.setAuthorities("ROLE_user, Role_admin");
        clientDetails.setScope("READ,WRITE");
        clientDetails.setWebServerRedirectUri("http://localhost:8080/");

        oauthService.saveClientDetails(clientDetails);

        return ResponseEntity.ok().body(userService.save(userRecord));

    }

    @ApiOperation(value = "Get User by ID")
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> User = userService.findUsersById(id);
        if (User == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(User.get(), HttpStatus.OK);
    }
}
