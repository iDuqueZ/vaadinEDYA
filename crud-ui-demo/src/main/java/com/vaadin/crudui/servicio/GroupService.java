package com.vaadin.crudui.servicio;

import org.springframework.stereotype.Service;
import com.vaadin.crudui.entidades.Group;
import com.vaadin.crudui.repositorio.GroupRepository;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public int count() {
        return (int) groupRepository.count();
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

}
