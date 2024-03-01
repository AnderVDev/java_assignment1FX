package org.assignment.assignment1_fx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.assignment.assignment1_fx.database.DatabaseConnector;
import org.assignment.assignment1_fx.model.DataModel;
import org.assignment.assignment1_fx.model.SceneModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


/**
 * Controller class for Scene1.fxml.
 * Handles actions and initializes UI elements.
 */

public class Scene1Controller implements Initializable {

    @FXML
    private RadioButton roleBtn;
    @FXML
    private RadioButton attacksBtn;
    @FXML
    private RadioButton difficultyBtn;
    @FXML
    private ToggleGroup dataView;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label welcomeText;
    @FXML
    private AnchorPane scene1AnchorPane;
    @FXML


    // Observable list for pie chart data
    ObservableList<DataModel> pieChartData = FXCollections.observableArrayList();


    // Database connector instance
    DatabaseConnector dbConnector = new DatabaseConnector();
    Connection connection = dbConnector.connect();


    /**
     * Method to switch to Scene 2.
     */
    @FXML
    void switchToScene2(ActionEvent event) throws IOException {

        new SceneModel(scene1AnchorPane, "scene2-view.fxml");
    }

    /**
     * Method to handle button click event for Role data view.
     * Retrieves and displays Role data on pie chart.
     */

    @FXML
    private void HandleButtonActionRole(ActionEvent event){

        try {
            Statement statement = connection.createStatement();
            ResultSet response = statement.executeQuery("SELECT role, COUNT(*) AS quantity FROM Champion GROUP BY role");
            pieChart.getData().clear();
            while (response.next()) {
                pieChart.getData().add(new PieChart.Data(response.getString("role"), response.getInt("quantity")));
            }
            // Apply inline CSS

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to handle button click event for Attack Type data view.
     * Retrieves and displays Attack Type data on pie chart.
     */

    @FXML
    private void HandleButtonActionAttackType(ActionEvent event){
        try {
            Statement statement = connection.createStatement();
            ResultSet response = statement.executeQuery("SELECT attack_type, COUNT(*) AS quantity FROM Champion GROUP BY attack_type");
            pieChart.getData().clear();
            while (response.next()) {
                pieChart.getData().add(new PieChart.Data(response.getString("attack_type"), response.getInt("quantity")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to handle button click event for Difficulty data view.
     * Retrieves and displays Difficulty data on pie chart.
     */

    @FXML
    private void HandleButtonActionDifficulty(ActionEvent event){
        try {
            Statement statement = connection.createStatement();
            ResultSet response = statement.executeQuery("SELECT difficulty, COUNT(*) AS quantity FROM Champion GROUP BY difficulty");
            pieChart.getData().clear();
            while (response.next()) {
                pieChart.getData().add(new PieChart.Data(response.getString("difficulty"), response.getInt("quantity")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     * Sets up the initial UI state and loads Role data onto the pie chart.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    // Select Role button by default
        roleBtn.setSelected(true);
        try {
            Statement statement = connection.createStatement();
            ResultSet response = statement.executeQuery("SELECT role, COUNT(*) AS quantity FROM Champion GROUP BY role");
                while (response.next()) {
                    pieChart.getData().add(new PieChart.Data(response.getString("role"), response.getInt("quantity")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}