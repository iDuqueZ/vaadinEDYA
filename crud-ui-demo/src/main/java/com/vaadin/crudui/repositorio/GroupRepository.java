package com.vaadin.crudui.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vaadin.crudui.entidades.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
