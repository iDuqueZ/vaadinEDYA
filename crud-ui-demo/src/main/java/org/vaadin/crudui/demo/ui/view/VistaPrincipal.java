package org.vaadin.crudui.demo.ui.view;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.demo.ui.MainLayout;

/**
 * @author Alejandro Duarte
 */
@Route(value = "", layout = MainLayout.class)
public class VistaPrincipal extends VerticalLayout {

    public VistaPrincipal() {
        add(
                new H1("Bienvenidos este es un CRUD con Vaadin"),
                new Html("<div>" +
                        "<h4>Integrantes: </h4>" +
                        "<ul>" +
                        "<li>Ivan D Duque</li>" +
                        "<li>Jaun David Garzón</li>" +
                        "<li>Juan Puche</li>" +
                        "<li>Juliana Lugo</li>" +
                        "</ul>" +
                        "</div>"));
    }

}
