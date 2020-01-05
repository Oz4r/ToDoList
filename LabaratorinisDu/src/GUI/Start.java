
package GUI;

import ds.ToDoList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Janeczka
 */
public class Start extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        ToDoList todo = new ToDoList();

            todo.registerPerson("admin", "admin", "admin", "admin");
            todo.registerPerson("heiko12", "123dasd", "Tadas", "Ponas");
            todo.registerCompany("magazynwilenski", "125324", "MagazynWilenski");
            todo.registerCompany("Kilikija", "19e83", "Kilikija");
            todo.registerPerson("Ozar", "12345", "Andrzej", "Tatol");
            todo.login("admin", "admin");
            todo.addProject("Tetris","admin");
            todo.addProject("Minesweeper","admin");
            todo.addProject("Snake","admin");
            todo.addTaskToProject(1, "Game Field","admin");
            todo.addTaskToProject(1, "Game collision","admin");
            todo.addTaskToProject(2, "Game Field","admin");
            todo.addTaskToProject(3,"Test","admin");
        
        FXMLLoader load = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent root = load.load();
          
        
        DashboardController col = load.getController();
        col.setToDoList(todo);
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("ToDoList Admin Interface");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
