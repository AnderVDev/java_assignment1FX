package org.assignment.assignment1_fx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
 * Controller class for the Scene2 FXML view.
 */
public class Scene2Controller implements Initializable {

    @FXML
    public TableView<DataModel> dataTableView;
    @FXML
    public TableColumn<DataModel, Integer> dataIDTableColumn;
    @FXML
    public TableColumn<DataModel, String> dataNameTableColumn;
    @FXML
    public TableColumn<DataModel, String> dataRoleTableColumn;
    @FXML
    public TableColumn<DataModel, String> dataAttackTableColumn;
    @FXML
    public TableColumn<DataModel, Integer> dataDifficultyTableColumn;
    @FXML
    private TextField searchedKey;
    @FXML
    private AnchorPane scene2AnchorPane;

    ObservableList<DataModel> dataList = FXCollections.observableArrayList();
    /**
     * Switches to Scene1 view.
     */
    @FXML
    void switchToScene1(ActionEvent event) throws IOException {
        new SceneModel(scene2AnchorPane, "scene1-view.fxml");
    }

    /**
     * Initializes the Scene2 view.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnector dbConnector = new DatabaseConnector();
        Connection connection = dbConnector.connect();

        try {
            Statement statement = connection.createStatement();
            ResultSet response = statement.executeQuery("SELECT champion_id, name, role, attack_type, difficulty FROM Champion");

            while (response.next()) {
                Integer champion_Id = response.getInt("champion_id");
                String name = response.getString("name");
                String role = response.getString("role");
                String attack_type = response.getString("attack_type");
                String difficulty = response.getString("difficulty");

                //Populate Observable List
                dataList.add(new DataModel(champion_Id,name,role,attack_type,difficulty));

            }
            dataIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("champion_id"));
            dataNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            dataRoleTableColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
            dataAttackTableColumn.setCellValueFactory(new PropertyValueFactory<>("attack_type"));
            dataDifficultyTableColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));

            dataTableView.setItems(dataList);
            FilteredList<DataModel> filteredData = new FilteredList<>(dataList, b-> true);

            // Listen for changes in the search text field
            searchedKey.textProperty().addListener((observable,oldValue, newValue)-> {
                filteredData.setPredicate(dataModel -> {
                    // if no search value then display all records or whatever records it current
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                        return true;
                    }
                    String searchKeyboard = newValue.toLowerCase();

                    //Find for match
                    if (dataModel.getName().toLowerCase().contains(searchKeyboard)){
                        return  true;

                    } else if (dataModel.getRole().toLowerCase().indexOf(searchKeyboard)> -1) {
                        return true;
                        
                    } else if (dataModel.getAttack_type().toLowerCase().indexOf(searchKeyboard)> -1) {
                        return true;

                    }else if (dataModel.getDifficulty().toLowerCase().indexOf(searchKeyboard)> -1) {
                        return true;

                    }else {
                        return false;
                    }
                });
            });

            SortedList<DataModel> sortedData = new SortedList<>(filteredData);

            //Bind sorted result with table view
            sortedData.comparatorProperty().bind(dataTableView.comparatorProperty());
            //Apply filtered and sorted data to the Table View
            dataTableView.setItems(sortedData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
