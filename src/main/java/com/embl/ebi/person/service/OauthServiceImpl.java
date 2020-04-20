package com.embl.ebi.person.service;


import com.embl.ebi.person.model.Permission;
import com.embl.ebi.person.model.Role;
import com.embl.ebi.person.model.User;
import com.embl.ebi.person.model.oauthmodels.OauthClientDetails;
import com.embl.ebi.person.repository.PermissionRepository;
import com.embl.ebi.person.repository.RoleRepository;
import com.embl.ebi.person.repository.UserDetailRepository;
import com.embl.ebi.person.repository.oauthrepository.OauthClientDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OauthServiceImpl implements OauthService {

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    PermissionRepository permissionRepository;


    @Autowired
    RoleRepository roleRepository;


    @Autowired
    OauthClientDetailsRepo oauthClientDetailsRepo;


    @Override
    public User saveUser(User user) {
        return userDetailRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userDetailRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDetailRepository.findAll();
    }

    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public OauthClientDetails saveClientDetails(OauthClientDetails details) {
        return oauthClientDetailsRepo.save(details);
    }

    @Override
    public void saveAllPermission(List<Permission> list) {
        permissionRepository.saveAll(list);
    }

    @Override
    public void saveAllRoles(List<Role> list) {
        roleRepository.saveAll(list);
    }
}
