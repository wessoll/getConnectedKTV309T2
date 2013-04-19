package model;

import java.util.ArrayList;

/**
 *
 * @author timvonsee
 */

public class Leaderboard {
    private ArrayList<Student> leaderboard; 
    
    public Leaderboard(ArrayList<Student> students){
        leaderboard = students;
    }
    
    // Get en Set methode om de ArrayList leaderboard aan te roepen.
    public ArrayList<Student> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(ArrayList<Student> leaderboard) {
        this.leaderboard = leaderboard;
    }
}
