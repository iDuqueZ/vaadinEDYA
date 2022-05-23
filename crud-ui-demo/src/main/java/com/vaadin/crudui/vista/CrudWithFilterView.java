package com.vaadin.crudui.vista;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import com.vaadin.crudui.entidades.Group;
import com.vaadin.crudui.entidades.User;
import com.vaadin.crudui.servicio.GroupService;
import com.vaadin.crudui.servicio.UserService;
import com.vaadin.crudui.ui.MainLayout;
import org.vaadin.crudui.form.impl.field.provider.CheckBoxGroupProvider;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;

@Route(value = "filter", layout = MainLayout.class)
public class CrudWithFilterView extends VerticalLayout {

        public CrudWithFilterView(UserService userService, GroupService groupService) {
                // Inicializamos el CRUD
                GridCrud<User> crud = new GridCrud<>(User.class);

                // Adiccionamos los componentes
                TextField filter = new TextField();
                filter.setPlaceholder("Buscar por nombre");
                filter.setClearButtonVisible(true);
                crud.getCrudLayout().addFilterComponent(filter);

                // Configuración de la tabla
                crud.getGrid().setColumns("name", "birthDate", "maritalStatus", "email", "phoneNumber",
                                "active");
                crud.getGrid().setColumnReorderingAllowed(true);

                // Configuración formularios
                crud.getCrudFormFactory().setUseBeanValidation(true);
                crud.getCrudFormFactory().setVisibleProperties(
                                CrudOperation.ADD,
                                "name", "birthDate", "email", "salary", "phoneNumber", "maritalStatus", "groups",
                                "active", "mainGroup",
                                "password");
                crud.getCrudFormFactory().setVisibleProperties(
                                "name", "birthDate", "email", "salary", "phoneNumber", "maritalStatus", "groups",
                                "active", "mainGroup");
                crud.getCrudFormFactory().setFieldProvider("mainGroup",
                                new ComboBoxProvider<>(groupService.findAll()));
                crud.getCrudFormFactory().setFieldProvider("groups",
                                new CheckBoxGroupProvider<>(groupService.findAll()));
                crud.getCrudFormFactory().setFieldProvider("groups",
                                new CheckBoxGroupProvider<>("Groups", groupService.findAll(), Group::getName));
                crud.getCrudFormFactory().setFieldProvider("mainGroup",
                                new ComboBoxProvider<>("Main Group", groupService.findAll(),
                                                new TextRenderer<>(Group::getName), Group::getName));

                // Distribución de pantalla
                // tamaño
                setSizeFull();
                // Imprimimos
                add(crud);

                // Logica
                crud.setOperations(
                                () -> userService.findByNameContainingIgnoreCase(filter.getValue()),
                                user -> userService.save(user),
                                user -> userService.save(user),
                                user -> userService.delete(user));

                filter.addValueChangeListener(e -> crud.refreshGrid());
        }

}
