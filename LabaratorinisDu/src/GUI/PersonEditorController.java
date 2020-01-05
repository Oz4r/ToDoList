/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ds.Person;
import ds.ToDoList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Janeczka
 */
public class PersonEditorController implements Initializable {

    @FXML
    Label pid, plogin, ppass, pname, psurname;
    
    
    public void uzpildyk(Person p){
        pid.setText(p.getPersonId()+"");
        plogin.setText(p.getLogin());
        ppass.setText(p.getPass());
        pname.setText(p.getName());
        psurname.setText(p.getSurname());
    }
    ToDoList tdl = null;
    public void deletePerson() throws Exception{
        int int1 = Integer.parseInt(pid.getText());
        tdl.deletePerson(int1);
    }
    public void close(Event e){
        Stage stg = (Stage)pid.getScene().getWindow();
        stg.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
