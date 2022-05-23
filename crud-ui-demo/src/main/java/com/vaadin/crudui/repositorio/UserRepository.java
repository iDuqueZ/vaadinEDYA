package com.vaadin.crudui.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vaadin.crudui.entidades.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContainingIgnoreCase(String name);

}
