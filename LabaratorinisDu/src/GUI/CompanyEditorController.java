
package GUI;


import ds.Company;

import ds.ToDoList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CompanyEditorController implements Initializable {

   
    @FXML
    Label cid, clogin,cpass,ctitle;
    
    
    public void uzpildyk(Company c){
        cid.setText(c.getCompanyId()+"");
        clogin.setText(c.getLogin());
        cpass.setText(c.getPass());
        ctitle.setText(c.getTitle());
        
    }
    ToDoList tdl = null;
    public void deleteCompany() throws Exception{
        int int1 = Integer.parseInt(cid.getText());
        tdl.deleteCompany(int1);   
    }
    public void close(Event e){
        Stage stg = (Stage)cid.getScene().getWindow();
        stg.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
