package model;

import java.util.ArrayList;

/**
 * Klasse voor het opzetten van een groep met studenten die wordt geleidt door één of meerdere leraren
 * @author wesley
 */
public class Group {
    private String groupName;
    private ArrayList<User> users;
    
    /**
     * Constructor voor opzetten van groep met bestaande studenten/ leraren
     * @param groupName         naam van de groep
     * @param students          lijst met studenten
     * @param teachers          lijst met leraren
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