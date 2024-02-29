module org.assignment.assignment1_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.assignment.assignment1_fx to javafx.fxml;
    exports org.assignment.assignment1_fx;
    exports org.assignment.assignment1_fx.controller;
    opens org.assignment.assignment1_fx.controller to javafx.fxml;
    exports org.assignment.assignment1_fx.model;
    opens org.assignment.assignment1_fx.model to javafx.fxml;
}