package model;

import java.util.ArrayList;

/**
 * Klasse voor het opzetten van een groep met studenten die wordt geleidt door één of meerdere leraren
 * @author wesley
 */
public class Group {
    private String groupName;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    
    /**
     * Constructor voor opzetten van groep met bestaande studenten/ leraren
     * @param groupName         naam van de groep
     * @param students          lijst met studenten
     * @param teachers          lijst met leraren
     */
    public Group(String groupName, ArrayList<Student> students, ArrayList<Teacher> teachers){
        this.groupName = groupName;
        this.students = students;
        this.teachers = teachers;
    }
    
    //Getters and setters
    
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }
}