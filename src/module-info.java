module controllers{
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.security.core;
    requires spring.security.crypto;

    opens frontend.controllers to javafx.fxml;
    opens frontend.controllers.homepage to javafx.fxml;

    exports frontend.controllers;
    exports backend;
    exports backend.tests;
    exports backend.users;
    exports backend.functionality;
    exports backend.constants;
    exports frontend.controllers.homepage;
}