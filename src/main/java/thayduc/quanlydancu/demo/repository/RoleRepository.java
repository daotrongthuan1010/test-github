package thayduc.quanlydancu.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import thayduc.quanlydancu.demo.entity.security.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
