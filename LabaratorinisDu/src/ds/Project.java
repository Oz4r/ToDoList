package ds;

import java.util.ArrayList;


public class Project {
    private int id;
    private static int idCounter = 1;
    private String title;
    private String madeby;
    private int taskId;
    private ArrayList<User> members = new ArrayList();
    public ArrayList<Task> tasks = new ArrayList();
    
    public Project(String title, String madeby){
        this.title = title;
        this.madeby = madeby;
//        this.id = idCounter++;
    }

    public void setId(int id) {
        this.id = id;
        
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    

    public String getMadeby() {
        return madeby;
    }

    public void setMadeby(String madeby) {
        this.madeby = madeby;
    }
    
    public void addMember(User u){
        members.add(u);
    }
    public void removeMember(User u){
        members.remove(u);
    }
    public void addTask(Task u){
        tasks.add(u);
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTitle(String title) {
        this.title = title;
    }

 
    
@Override
    public String toString() {
        return "Project{" + "id=" + id + ", title=" + title + '}';
    }
    
    public ArrayList<Task> getAllTasks(){
        ArrayList<Task> all = new ArrayList();
        all.addAll(this.tasks);
        for(Task t:tasks){
            all.addAll(t.getAllTasks());
        }
        return all;
    }
    
}
