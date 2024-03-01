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
 *  @author  Anderson Victoral
 *  @version 1.0
 *  @since   2024-03-01
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

    /**     * Method to switch to Scene 2.
     */
    @FXML
    void switchToScene2(ActionEvent event) throws IOException {

        new SceneModel(scene1AnchorPane, "scene2-view.fxml");
    }

    /**
     * Initializes the controller class.
     * Sets up the initial UI state and loads Role data onto the pie chart.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Select Role button by default
        roleBtn.setSelected(true);
        fetchDataAndDisplay("role");
    }

    /**
     * Method to handle button click event for Role data view.
     * Retrieves and displays Role data on pie chart.
     */
    @FXML
    private void HandleButtonActionRole(ActionEvent event){
        fetchDataAndDisplay("role");
    }

    /**
     * Method to handle button click event for Attack Type data view.
     * Retrieves and displays Attack Type data on pie chart.
     */

    @FXML
    private void HandleButtonActionAttackType(ActionEvent event){
        fetchDataAndDisplay("attack_type");

    }

    /**
     * Method to handle button click event for Difficulty data view.
     * Retrieves and displays Difficulty data on pie chart.
     */

    @FXML
    private void HandleButtonActionDifficulty(ActionEvent event){
        fetchDataAndDisplay("difficulty");
    }

    /**
     * Fetches data from the database based on the specified column and displays it on a pie chart.
     */

    private void fetchDataAndDisplay(String column) {
        // Create a new instance of DatabaseConnector to establish a connection to the database
        DatabaseConnector dbConnector = new DatabaseConnector();
        // Attempt to establish a connection to the database
        Connection connection = dbConnector.connect();
        try (Statement statement = connection.createStatement()) {
            // Execute SQL query to retrieve data from the specified column along with its count
            ResultSet response = statement.executeQuery("SELECT " + column + ", COUNT(*) AS quantity FROM Champion GROUP BY " + column);
            // Clear existing data in the pie chart
            pieChart.getData().clear();
            // Iterate through the result set and add data to the pie chart
            while (response.next()) {
                // Add a new slice to the pie chart with the retrieved data
                pieChart.getData().add(new PieChart.Data(response.getString(column), response.getInt("quantity")));
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions by printing the stack trace
            e.printStackTrace();
        } finally {
            // Ensure that the database connection is closed, regardless of whether an exception occurred or not
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                // Handle any SQL exceptions that may occur while attempting to close the connection
                e.printStackTrace();
            }
        }
    }



}