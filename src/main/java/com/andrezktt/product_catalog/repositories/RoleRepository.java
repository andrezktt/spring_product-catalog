package com.andrezktt.product_catalog.repositories;

import com.andrezktt.product_catalog.entities.Role;
import com.andrezktt.product_catalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
