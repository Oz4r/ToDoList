/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ds.ToDoList;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Janeczka
 */
public class DashboardController implements Initializable {
    
    @FXML 
    private PieChart userS;
    @FXML
    private AreaChart projectS;
    
    ToDoList tdl = null;
    
    public void close(ActionEvent exit){
        Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Close Application");
alert.setContentText("By exiting the app all data modified will be lost");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
Platform.exit();
} else {
}
    }
    
    public void About(ActionEvent info){
        Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("About");
alert.setHeaderText("Information about this app");
alert.setContentText("This JavaFX project is made by a 3rd year student");

alert.showAndWait();
    }
    
    public void setToDoList(ToDoList t){
        tdl = t;
        showUserStats();
        showProjectStats();
    }
    public void openPersonManager(ActionEvent um)throws Exception{
        FXMLLoader load = new FXMLLoader(getClass().getResource("Users.fxml"));
        Parent root = load.load();
        Scene scene = new Scene(root);
        
        UsersController col = load.getController();
        col.setToDoList(tdl);
        
        Stage newstg = new Stage();
        newstg.setTitle("Person managment");
        newstg.setScene(scene);
        newstg.show();
    }
          public void openProjectMembers(ActionEvent pmm) throws Exception {
        FXMLLoader load = new FXMLLoader(getClass().getResource("ProjectTasksAndMembers.fxml"));
        Parent root = load.load();


        ProjectTasksAndMembersController col = load.getController();
        col.setToDoList(tdl);

        Scene scene3 = new Scene(root);
        Stage newStage3 = new Stage();
        newStage3.setTitle("Members/Tasks menu");
        newStage3.setScene(scene3);
        newStage3.show();
    }
      public void openCompanyManager(ActionEvent df) throws Exception {
        FXMLLoader load = new FXMLLoader(getClass().getResource("company.fxml"));
        Parent root = load.load();


        CompanyController col = load.getController();
        col.setToDoList(tdl);

        Scene scene2 = new Scene(root);
        Stage newStage2 = new Stage();
        newStage2.setTitle("Company menu");
        newStage2.setScene(scene2);
        newStage2.show();
    }
      public void openProjectManager(ActionEvent pm) throws Exception {
        FXMLLoader load = new FXMLLoader(getClass().getResource("Projects.fxml"));
        Parent root = load.load();


        ProjectsController col = load.getController();
        col.setToDoList(tdl);

        Scene naujas = new Scene(root);
        Stage naujasstage = new Stage();
        naujasstage.setTitle("Project menu");
        naujasstage.setScene(naujas);
        naujasstage.show();
    }
     public void showUserStats() {
        if (tdl != null) {
            
            int[] count = tdl.getUserCount();
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Persons - " + count[0], count[0]),
                            new PieChart.Data("Companies - " + count[1], count[1]));
            userS.setTitle("User stats");
            userS.setData(pieChartData);
        }
     }
    public void showProjectStats() {
        if (tdl != null) {
            XYChart.Series seriesProject = new XYChart.Series();
            seriesProject.setName("Tasks per project ");
            int[][] arr = tdl.getProjectNumbers();
            for (int[] row : arr)
                seriesProject.getData().add(new XYChart.Data(row[0], row[1]));


            projectS.getData().addAll(seriesProject);
        }
    }
    public void openRecord(MouseEvent me){
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
