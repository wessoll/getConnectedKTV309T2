package model;

import java.security.MessageDigest;
import we.getconnected.Main;

/**
 * Parentklasse voor het opzetten van een gebruiker
 * @author wesley
 */
public class User {
    
    private int user_id;
    private String firstName, lastName, userName, password;
    private Continent europe;
    private boolean teacher;
    
    
    /**
     * Constructor
     * @param user_id           id van de gebruiker
     * @param firstName         voornaam gebruiker
     * @param lastName          achternaam gebruiker
     * @param userName          username gebruiker
     * @param password          wachtwoord gebruiker
     * @param teacher           boolean of het een leraar is of niet
     */
    public User(int user_id, String firstName, String lastName, String userName, String password, boolean teacher){
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.teacher=teacher;
        europe = Main.getQueryManager().getContinent("Europa", user_id);
    }
 
    //Getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTeacher() {
        return teacher;
    }

    public void setTeacher(boolean teacher) {
        this.teacher = teacher;
    }
    
    public Continent getEurope(){
        return europe;
    }
    
    public void setEurope(Continent europe){
        this.europe = europe;
    }
    
    public int getUser_id(){
        return user_id;
    }
    public void setUser_id(int user_id){
        this.user_id = user_id;
    }
}