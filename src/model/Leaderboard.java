package model;

import java.util.ArrayList;

/**
 *
 * @author timvonsee
 */

public class Leaderboard {
    private ArrayList<User> leaderboard; 
    
    public Leaderboard(ArrayList<User> students){
        leaderboard = students;
    }
    
    // Get en Set methode om de ArrayList leaderboard aan te roepen.
    public ArrayList<User> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(ArrayList<User> leaderboard) {
        this.leaderboard = leaderboard;
    }
}
