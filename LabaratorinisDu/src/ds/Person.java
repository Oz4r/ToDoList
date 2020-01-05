package ds;


public class Person {
    private int personId;
    private String name, surname,pass,login;

    public Person(String login, String pass, String name, String surname) {
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.surname = surname;
        
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", surname=" + surname + ", pass=" + pass + ", login=" + login + '}';
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    
}
