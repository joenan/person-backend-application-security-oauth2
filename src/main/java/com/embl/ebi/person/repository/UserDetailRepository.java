package com.embl.ebi.person.repository;

import com.embl.ebi.person.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String name);

    public Optional<User> findUsersById(Long id);

    public void deleteUserById(Long id);
}