package com.embl.ebi.person.service;

import com.embl.ebi.person.model.Permission;
import com.embl.ebi.person.model.Role;
import com.embl.ebi.person.model.User;
import com.embl.ebi.person.model.oauthmodels.OauthClientDetails;

import java.util.List;
import java.util.Optional;

public interface OauthService {
    public User saveUser(User user);
    public Optional<User> getUserById(Long id);
    public List<User> findAll();
    public Permission savePermission(Permission permission);
    public Role saveRole(Role role);
    public OauthClientDetails saveClientDetails(OauthClientDetails details);
    public void saveAllPermission(List<Permission> list);
    public void saveAllRoles(List<Role> list);

}
