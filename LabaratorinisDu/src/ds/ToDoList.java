package ds;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class ToDoList {
    private static final String DB_NAME = "todolistdb";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost/" + DB_NAME;
    Connection conn = null;
    private ArrayList<User> users = new ArrayList();
    private ArrayList<Company> companies = new ArrayList();
    private ArrayList<Project> projects = new ArrayList();
    private User loggedIn = null;
    
    public void connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String USER = "root";
        String PASS = "";
        conn = DriverManager.getConnection(CONNECTION_STRING, USER, PASS);
    }
    
        public void disconnect() throws Exception {
        conn.close();
    }
    
    
    public Company registerCompany(String login, String pass, String title)throws Exception{
       
           Company newCompany = new Company(login,pass,title);
           this.connect();
           PreparedStatement ps2 = conn.prepareStatement("INSERT INTO company"
                   + "(companyId, login, pass, title) VALUES"
                   + "(NULL, ?, ?, ?)");
           ps2.setString(3, title);
           ps2.setString(1, login);
           ps2.setString(2, pass);
           ps2.executeUpdate();
           
           this.disconnect();

           return newCompany;
           }
    public void deleteCompany(int companyId)throws Exception{
        this.connect();
        PreparedStatement pst = conn.prepareStatement("DELETE FROM company WHERE companyId=?");
        pst.setInt(1, companyId);
        pst.executeUpdate();
        this.disconnect();
    }
    
    public ArrayList<Company> getAllCompanies() throws Exception{
          ArrayList<Company> grazinti = new ArrayList();
          this.connect();
          Statement stmt = conn.createStatement();
          ResultSet rez = stmt.executeQuery("SELECT * FROM company");
          while(rez.next()){
              int companyId = rez.getInt(1);
              String title = rez.getString(2);
              String login = rez.getString(3);
              String pass = rez.getString(4);
              Company naujas = new Company(login,pass,title);
              naujas.setCompanyId(companyId);
              naujas.setTitle(title);
              naujas.setLogin(login);
              naujas.setPass(pass);
              
              grazinti.add(naujas);
              
          }
          this.disconnect();
          return grazinti;
    }
    
    public void editCompanyInfo(int companyId, String login, String pass, String title)throws Exception{
        this.connect();
        PreparedStatement pst = conn.prepareStatement("UPDATE company SET login = ?, pass= ?, title=? where companyId=?");
        pst.setString(1, login);
        pst.setString(2,pass);
        pst.setString(3,title);
        pst.setInt(4,companyId);
        pst.executeUpdate();
        
        this.disconnect();
    }
  

    public Person registerPerson(String login, String pass, String name, String surname)throws Exception{
       
            Person newPerson = new Person(login, pass, name, surname);
           this.connect();
           
           PreparedStatement ps = conn.prepareStatement("INSERT INTO person"
                   + "(personId, login, pass, name, surname) VALUES"
                   + "(NULL, ?, ?, ?, ?)");
          
           ps.setString(1, name);
           ps.setString(2, surname);
           ps.setString(3, login);
           ps.setString(4, pass);
           ps.executeUpdate();
           
           this.disconnect();

            return newPerson; 
    }
    public ArrayList<Person> getAllPersons() throws Exception{
        ArrayList<Person> grazinti = new ArrayList();
        this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rez = stmt.executeQuery("Select * from person");
        while(rez.next()){
            int personId = rez.getInt(1);
            String login = rez.getString(2);
            String pass = rez.getString(3);
            String name = rez.getString(4);
            String surname = rez.getString(5);
            Person naujas = new Person(login,pass,name,surname);
            naujas.setPersonId(personId);
            naujas.setLogin(login);
            naujas.setPass(pass);
            grazinti.add(naujas);
        }
        this.disconnect();
        return grazinti;
    }
    public Person getPersonById( int personId) throws Exception{
        this.connect();
        PreparedStatement stmt = conn.prepareStatement("Select * from person WHERE personId = ?");
        stmt.setInt(1,personId);
        ResultSet rez = stmt.executeQuery();
        Person naujas = null;
        while(rez.next()){
    
            String login = rez.getString(2);
            String pass = rez.getString(3);
            String name = rez.getString(4);
            String surname = rez.getString(5);
            naujas = new Person(login,pass,name,surname);
            naujas.setPersonId(personId);
            naujas.setLogin(login);
            naujas.setPass(pass);
        }
        this.disconnect();
        return naujas;
    }
    public void deletePerson(int personId)throws Exception{
        this.connect();
        PreparedStatement pst = conn.prepareStatement("DELETE FROM person WHERE personId=?");
        PreparedStatement pst2 = conn.prepareStatement("DELETE FROM projectmembers WHERE personId=?");
        pst.setInt(1, personId);
        pst2.setInt(1, personId);
        pst.executeUpdate();
        pst2.executeUpdate();
        
        this.disconnect();
    }
    
        public void editPersonInfo(int personId, String login, String pass, String name, String surname)throws Exception{
        this.connect();
        PreparedStatement pst = conn.prepareStatement("UPDATE person SET login = ?, pass= ?, name=?, surname=? where personId=?");
        pst.setString(1, login);
        pst.setString(2,pass);
        pst.setString(3, name);
        pst.setString(4,surname);
        pst.setInt(5,personId);
        pst.executeUpdate();
        
        this.disconnect();
    }
        
    
  

    public User login (String login, String pass){
        for(User u :users){
            if(u.getLogin().equals(login) && u.getPass().equals(pass) && u.isActive()){
                loggedIn = u;
                 return u;
                }
       }      
        return null;
    }
    
    public void logout(int id){
        loggedIn = null;
    }
    
    public Project addProject(String title, String madeby) throws Exception{
        Project newProject = new Project(title, madeby);
        this.connect();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO project"
                + "(projectId, title, madeby) VALUES"
                + "(NULL, ?, ?)");
        ps.setString(1, title);
        ps.setString(2, madeby);
        ps.executeUpdate();
        this.disconnect();
        return newProject;
      
    }
    public void editProjectInfo(int projectId, String title)throws Exception{
        this.connect();
        PreparedStatement pst = conn.prepareStatement("UPDATE project SET title=? where projectId=?");
        pst.setInt(2,projectId);
        pst.setString(1,title);
        pst.executeUpdate();
        
        this.disconnect();
    }
    public ArrayList<Project> getAllProjects() throws Exception{
        ArrayList<Project> grazinti = new ArrayList();
        this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rez = stmt.executeQuery("Select * from project");
        while(rez.next()){
            int projectId= rez.getInt(1);
            String title = rez.getString(2);
            String madeby = rez.getString(3);
            Project naujas = new Project(title, madeby);
            naujas.setId(projectId);
            naujas.setTitle(title);
            naujas.setMadeby(madeby);
            grazinti.add(naujas);
        }
        this.disconnect();
        return grazinti;
    }
//    public ArrayList<Task> getAllTasks() throws Exception{
//        ArrayList<Task> grazinti = new ArrayList();
//        this.connect();
//        Statement stmt = conn.createStatement();
//        ResultSet rez = stmt.executeQuery("Select * from tasks");
//        while(rez.next()){
//            int TASKID = rez.getInt(1);
//            String title = rez.getString(2);
//            String description = rez.getString(3);
//            int projectId = rez.getInt(4);
//            Task naujas = new Task(projectId, String title, String description);
//            naujas.setId(TASKID);
//            naujas.setTitle(title);
//            
//            grazinti.add(naujas);
//        }
//        this.disconnect();
//        return grazinti;
//    }
    public void deleteProject(int id) throws Exception{
        this.connect();
        PreparedStatement pst = conn.prepareStatement("DELETE FROM project WHERE projectId=?");
        PreparedStatement pst2 = conn.prepareStatement("DELETE FROM TASKS WHERE projectId=?");
        PreparedStatement pst3 = conn.prepareStatement("DELETE from projectmembers WHERE projectId=?");
        pst.setInt(1,id);
        pst2.setInt(1, id);
        pst3.setInt(1,id);
        pst.executeUpdate();
        this.disconnect();
    }

    
    public void addProjectMember(int projectId, int personId)throws Exception{
                this.connect();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO projectmembers"
                + "(projectId, personId) VALUES"
                + "( ?, ?)");
                ps.setInt(1, projectId);
                ps.setInt(2, personId);
                ps.executeUpdate();
                this.disconnect();
//            }
    }
     public void removeProjectMember(int projectId, int personId)throws Exception{
            this.connect();
            PreparedStatement pst = conn.prepareStatement("DELETE FROM projectmembers WHERE projectId=? AND "
                    + "personId=?");
            pst.setInt(1, projectId);
            pst.setInt(2, personId);
            pst.executeUpdate();
            this.disconnect();
        
    }
     
    public ArrayList<Project> getAllProjectMembers()throws Exception{
        ArrayList<Project> grazinti = new ArrayList();
        this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rez = stmt.executeQuery("Select title, person.name"
                + " FROM PROJECT "
                + "JOIN PROJECTMEMBERS ON PROJECT.`PROJECTID`=PROJECTMEMBERS.`projectId`"
                + "JOIN PERSON ON PROJECTMEMBERS.`personId`=PERSON.`PERSONID`");
        while(rez.next()){
            String title = rez.getString(1);
            String name = rez.getString(2);
           
            Project naujas = new Project(title,name);
            naujas.setTitle(title);
            naujas.setMadeby(name);
            grazinti.add(naujas);
        }
        this.disconnect();
        return grazinti;
    }
    
    public Task addTaskToProject(int projectId, String title, String description)throws Exception{
        this.connect();
        PreparedStatement pst = conn.prepareStatement("INSERT INTO tasks "
                + "(projectId, taskId, title, description) VALUES"
                + "(?,NULL,?,?)");
        pst.setInt(1, projectId);
        pst.setString(2, title);
        pst.setString(3, description);
        pst.executeUpdate();
        this.disconnect();
        return null;
    }
    public void editTask(int taskId, String title, String description)throws Exception{
        this.connect();
        PreparedStatement pst = conn.prepareStatement("UPDATE tasks SET title = ?, description= ? where taskId=?");
        pst.setString(1, title);
        pst.setString(2,description);
        pst.setInt(3,taskId);
        pst.executeUpdate();
        
        this.disconnect();
    }
    public void removeTaskFromProject(int taskId)throws Exception{
        this.connect();
        PreparedStatement pst = conn.prepareStatement("DELETE FROM TASKS WHERE TASKID=?");
        pst.setInt(1, taskId);
        pst.executeUpdate();
        this.disconnect();
    }
    
    
    public Project getUserProjectById(int id){
        if(loggedIn != null){
            for(Project p:loggedIn.getProjects()){
                if(p.getId()==id)
                    return p;
            }
        }
      return null;  
    }
    

    public User getUserById (int id){
        if(loggedIn != null && loggedIn.isActive()){
        for(User u:users){
            if(u.getId() == id){
                return u;
            }
        }
      }
        return null;
    }
    public Project getProjectById (int id){
        for(Project p:projects){
            if(p.getId() == id ){
                return p;
            }
        }
        return null;
    }
    
    public User getUserByLogin (String login){
        
        for(User u:users){
            if(u.getLogin().equals(login)){
                return u;
            }
        }
      
        return null;
    }
   
    
    public int[] getUserCount() {
        int[] num = new int[2];
        for (User u : users) {
            if (u.getClass().equals(Person.class)) {
                num[0] ++;
            } else {
                num[1] ++;
            }
        }
        return num;
    }
    public int[][] getProjectNumbers() {
        int[][] arr = new int[projects.size()][2];
        int id = 0;
        for (Project p : projects) {
            arr[id][0] = p.getId();
            arr[id][1] = p.getAllTasks().size();
            id += 1;
        }
        return arr;
    }
}
