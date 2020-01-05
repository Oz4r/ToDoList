
package GUI;

import ds.Project;
import ds.ToDoList;
import java.net.URL;
import java.util.ArrayList;
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
public class ProjectsController implements Initializable {

   @FXML
    private TableView  projectTable;
    private TextField  projectTitle;
    private TextField projectMadeBy;
    private TextField projectId;
    private ArrayList<Project> projects = new ArrayList();
    
    
    public void deleteProject() throws Exception{
        String pId = projectId.getText();
        int int1 = Integer.parseInt(pId);
        tdl.deleteProject(int1);
        fillTableProject();
    }
      public void fillTableProject() throws Exception{
        if (tdl != null) {
            projectTable.getItems().clear();
            projectTable.getItems().addAll(tdl.getAllProjects());
        }
    } 
    
   public void addProject() throws Exception{
        String pTitle = projectTitle.getText();
        String pMadeBy = projectMadeBy.getText();
        
        tdl.addProject(pTitle, pMadeBy);
        fillTableProject();
        projectTitle.setText("");
        projectMadeBy.setText("");
    }
   
    ToDoList tdl = null;
    public void setToDoList(ToDoList t)throws Exception {
        tdl = t;
        fillTableProject();
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<String, Project> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<String, Project> column2 = new TableColumn<>("Title");
        column2.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        TableColumn<String, Project> column3 = new TableColumn<>("Made By");
        column3.setCellValueFactory(new PropertyValueFactory<>("madeby"));

      

        projectTable.getColumns().clear();
        projectTable.getColumns().add(column1);
        projectTable.getColumns().add(column2);
        projectTable.getColumns().add(column3);
    }    
    
}

