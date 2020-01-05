package ds;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
    public int id;
    private String login, pass;
    private static int idCounter = 1;
    public boolean active = true;
    private ArrayList<Project> projects = new ArrayList();

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
//        this.id = idCounter;
//        idCounter++;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addProject(Project p){
        projects.add(p);
        
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", active=" + active + ", projects=" + projects + '}';
    }
    
    

    public ArrayList<Project> getProjects() {
        return projects;
    }
    
    public int getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
    
}
