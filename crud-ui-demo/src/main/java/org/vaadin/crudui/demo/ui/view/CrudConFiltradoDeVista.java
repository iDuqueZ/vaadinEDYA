package org.vaadin.crudui.demo.ui.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.demo.entity.Group;
import org.vaadin.crudui.demo.entity.User;
import org.vaadin.crudui.demo.service.GroupService;
import org.vaadin.crudui.demo.service.UserService;
import org.vaadin.crudui.demo.ui.MainLayout;
import org.vaadin.crudui.form.impl.field.provider.CheckBoxGroupProvider;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;

@Route(value = "filter", layout = MainLayout.class)
public class CrudConFiltradoDeVista extends VerticalLayout {

        public CrudConFiltradoDeVista(UserService userService, GroupService groupService) {
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
                                "nombre", "birthDate", "email", "salary", "phoneNumber", "maritalStatus", "groups",
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
                                usuario -> userService.save(usuario),
                                usuario -> userService.save(usuario),
                                usuario -> userService.delete(usuario));

                filter.addValueChangeListener(e -> crud.refreshGrid());
        }

}
