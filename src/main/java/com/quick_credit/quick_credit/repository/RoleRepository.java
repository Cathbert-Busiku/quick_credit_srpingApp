package com.quick_credit.quick_credit.repository;

import com.quick_credit.quick_credit.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
