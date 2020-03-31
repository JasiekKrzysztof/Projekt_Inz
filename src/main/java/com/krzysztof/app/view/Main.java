package com.krzysztof.app.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

@Route("main")
@StyleSheet("/css/style.css")
public class Main extends AppLayout {
    public Main() {
        setPrimarySection(AppLayout.Section.DRAWER);
        Image img = new Image("https://radioznin.fm/images/artykuly/2015/12/ankiet.jpg", "https://radioznin.fm/images/artykuly/2015/12/ankiet.jpg");
        img.setHeight("44px");
        addToNavbar(new DrawerToggle(), img);
        Tabs tabs = new Tabs(new Tab("Home"), new Tab("Login"), new Tab("Registration"));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        addToDrawer(tabs);


    }
}