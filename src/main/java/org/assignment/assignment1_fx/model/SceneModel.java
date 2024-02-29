package org.assignment.assignment1_fx.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.assignment.assignment1_fx.AssignmentApplication;

import java.io.IOException;
import java.util.Objects;

/**
 * SceneModel class facilitates the switching of scenes in the application.
 */
public class SceneModel {

    /**
     * Initializes a new SceneModel and switches to the specified FXML scene.
    */
    public SceneModel(AnchorPane currentAnchorPane, String fxml) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(AssignmentApplication.class.getResource(fxml)));
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }


}
