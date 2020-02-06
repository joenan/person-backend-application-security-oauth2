package com.embl.ebi.person.repository;

import com.embl.ebi.person.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
