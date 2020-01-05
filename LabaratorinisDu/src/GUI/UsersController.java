/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ds.Person;
import ds.ToDoList;
import ds.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UsersController implements Initializable {

    private ArrayList<User> users = new ArrayList();
    @FXML
    private TableView userTable;

    @FXML
    private TextField lLogin, lPass, lName, lSurname;
  

   
    public void fillTableUser() throws Exception{
        if (tdl != null) {
            userTable.getItems().clear();
            userTable.getItems().addAll(tdl.getAllPersons());
        }
    }

    public void addPerson()throws Exception {
        String login = lLogin.getText();
        String pass = lPass.getText();
        String name = lName.getText();
        String surname = lSurname.getText();
      
        tdl.registerPerson(login, pass, name, surname);
        fillTableUser();
        lLogin.setText("");
        lPass.setText("");
        lName.setText("");
        lSurname.setText("");
        }
    ToDoList tdl = null;

    public void setToDoList(ToDoList t)throws Exception {
        tdl = t;
        fillTableUser();
    }

    public void openRecord(MouseEvent me) throws Exception{
        if(me.getClickCount()==2){
        Person selektintas = (Person)userTable.getSelectionModel().getSelectedItem();
        FXMLLoader load = new FXMLLoader(getClass().getResource("PersonEditor.fxml"));
        Parent root = load.load();
        PersonEditorController ec = load.getController();
        ec.uzpildyk(selektintas);
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TableColumn<String, User> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("personId"));

        TableColumn<String, User> column2 = new TableColumn<>("Login");
        column2.setCellValueFactory(new PropertyValueFactory<>("login"));

      

        userTable.getColumns().clear();
        userTable.getColumns().add(column1);
        userTable.getColumns().add(column2);

    }

}
