package com.vaadin.crudui.vista;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.crudui.ui.MainLayout;

/**
 * @author Alejandro Duarte
 */
@Route(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {

    public HomeView() {
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
