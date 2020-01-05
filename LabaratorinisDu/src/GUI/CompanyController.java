/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ds.Company;
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



public class CompanyController implements Initializable {
    
    private ArrayList<Company> company = new ArrayList();
    @FXML
    private TableView  companyTable;

    @FXML
    private TextField companyLogin, companyPass, companyTitle;

    ToDoList tdl = null;
    public void setToDoList(ToDoList t)throws Exception {
        tdl = t;
        fillTableCompany();
    }
     public void fillTableCompany() throws Exception{
        if (tdl != null) {
            companyTable.getItems().clear();
            companyTable.getItems().addAll(tdl.getAllCompanies());
        }
    }
        public void addCompany() throws Exception{
        String complogin = companyLogin.getText();
        String comppass = companyPass.getText();
        String comptitle = companyTitle.getText();
        
        
        tdl.registerCompany(complogin, comppass, comptitle);
        fillTableCompany();
        companyLogin.setText("");
        companyPass.setText("");
        companyTitle.setText("");
    }
        public void openRecord(MouseEvent me) throws Exception{
        if(me.getClickCount()==2){
        Company selektintas = (Company)companyTable.getSelectionModel().getSelectedItem();
        FXMLLoader load = new FXMLLoader(getClass().getResource("CompanyEditor.fxml"));
        Parent root = load.load();
        CompanyEditorController cc = load.getController();
        cc.uzpildyk(selektintas);
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<String, Company> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("companyId"));

        TableColumn<String, Company> column2 = new TableColumn<>("Login");
        column2.setCellValueFactory(new PropertyValueFactory<>("login"));
        
        TableColumn<String, Company> column3 = new TableColumn<>("Title");
        column3.setCellValueFactory(new PropertyValueFactory<>("title"));

      

        companyTable.getColumns().clear();
        companyTable.getColumns().add(column1);
        companyTable.getColumns().add(column2);
        companyTable.getColumns().add(column3);
    }    
    
}
