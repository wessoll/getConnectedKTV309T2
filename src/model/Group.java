package model;

import java.util.ArrayList;

/**
 * Klasse voor het opzetten van een groep die bestaat uit users (studenten, leerlingen)
 * @author wesley
 */
public class Group {
    private String groupName;
    private ArrayList<User> users;
    
    /**
     * Constructor voor opzetten van groep studenten/ leraren
     * @param groupName         naam van de groep
     * @param users             de lijst met users
     */
    public Group(String groupName, ArrayList<User> users){
        this.groupName = groupName;
        this.users = users;
    }
    
    //Getters and setters
    
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}