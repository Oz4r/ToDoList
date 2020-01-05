/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ds.Project;
import ds.ToDoList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Janeczka
 */
public class ProjectTasksAndMembersController implements Initializable {

    @FXML
    TableView ProjectMembers , Tasks;
    @FXML
    TextField projectId, personId, description, title, taskProjectId;
    
    
//    public void fillTableTasks() throws Exception{
//        if (tdl != null) {
//            Tasks.getItems().clear();
//            Tasks.getItems().addAll(tdl.getAllTasks());
//        }
//    } 
    public void fillTableProjectMembers() throws Exception{
        if (tdl != null) {
            ProjectMembers.getItems().clear();
            ProjectMembers.getItems().addAll(tdl.getAllProjectMembers());
        }
    } 
     
   
    ToDoList tdl = null;
    public void setToDoList(ToDoList t)throws Exception {
        tdl = t;
//        fillTableTasks();
        fillTableProjectMembers();
    }
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<String, Project> column1 = new TableColumn<>("ProjectID");
        column1.setCellValueFactory(new PropertyValueFactory<>("projectId"));

        TableColumn<String, Project> column2 = new TableColumn<>("PersonID");
        column2.setCellValueFactory(new PropertyValueFactory<>("personId"));

      

        ProjectMembers.getColumns().clear();
        ProjectMembers.getColumns().add(column1);
        ProjectMembers.getColumns().add(column2);
        
    }    
    
}
