package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User_Role;

@Repository
public interface User_RoleRepository extends JpaRepository<User_Role,Integer> {

}
