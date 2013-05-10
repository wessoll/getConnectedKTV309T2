package we.getconnected.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import model.Answer;
import model.Continent;
import model.Group;
import model.Land;
import model.Land.Landen;
import model.Question;
import model.User;
import we.getconnected.Main;

public class QueryManager {

    private final Dbmanager dbmanager;

    public QueryManager(Dbmanager dbmanager) {
        this.dbmanager = dbmanager;
    }
    
    
    /**
     * Haal een antwoord uit de database
     * @param answerID
     * @return Answer answer, het antwoord voor een vraag.
     */
    public Answer getAnswer(int answerID){
        Answer answer = null;
         try {
            String sql = "SELECT * FROM answers WHERE answer_id='" + answerID + "'";
            ResultSet result = dbmanager.doQuery(sql);
            if(result.next()) {
                answer = new Answer(
                        result.getInt("x"),
                        result.getInt("y"),
                        result.getBoolean("correct"),
                        result.getString("answer")
                        );
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return answer;
    }
    
    public User getUser(int userID){
        User user=  null; 
        try {
            String sql = "SELECT * FROM user WHERE user_id='" + userID + "'";
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()) {
                user = new User(
                        result.getInt("user_id"),
                        result.getString("firstname"),
                        result.getString("lastname"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getBoolean("teacher"),
                        getContinent(1,result.getInt("user_id"))
                        );
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return user;
    }
    
    /**
     * Haal een groep studenten uit de database, die tot een bepaalde group (klas) behoren.
     * 
     * @return List<Students> (lijst met leerlingen die tot een group behoren)
     */
    public ArrayList<User> getUsersByGroup(int groupID){
        ArrayList<User> users= new ArrayList<User>();
        try {
            String sql = "SELECT * FROM students WHERE group_id='" + groupID + "'";
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()) {
                users.add(new User(
                        result.getInt("user_id"),
                        result.getString("firstname"),
                        result.getString("lastname"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getBoolean("teacher"),
                        getContinent(1,result.getInt("user_id"))
                        ));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return users;
    }
    
    
    /**
     * Haal alle studenten uit de database
     * 
     * @return List<Students> (lijst met alle leerlingen in de database)
     */
    public ArrayList<User> getUsers(){
        ArrayList<User> students= new ArrayList<User>();
        try {
            String sql = "SELECT * FROM students";
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()) {
                students.add(new User(
                        result.getInt("user_id"),
                        result.getString("firstname"),
                        result.getString("lastname"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getBoolean("teacher"),
                        getContinent(1,result.getInt("user_id"))
                        ));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return students;
    }
    
    public Continent getContinent(int continentID, int userID){
        Continent continent = null;
        try {
            String sql = "SELECT * FROM continent WHERE continent_id='" + continentID + "'";
            ResultSet result = dbmanager.doQuery(sql);
            if(result.next()) {
                continent = new Continent(
                        continentID,
                        result.getString("name"),
                        userID
                        );
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return continent;
    }
    
    public ArrayList<Land> getLandenByContinentID(int continentID, int userID){
        ArrayList<Land> landen = new ArrayList<Land>();
        try {
            String sql = "SELECT * FROM country WHERE continent_id='" + continentID + "'";
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()) {
                landen.add(new Land(
                        Landen.fromString(result.getString("name")),
                        getQuestionsByLandID(result.getInt("country_id"),userID),
                        false
                        ));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return landen;
    }
    
    public ArrayList<Question> getQuestionsByLandID(int landID, int userID){
        ArrayList<Question> questions = new ArrayList<Question>();
        try {
            String sql = "SELECT * FROM question WHERE country_id='" + landID + "'";
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()) {
                questions.add(new   Question(
                        result.getString("question"),
                        new ImageIcon(Main.IMAGES_LOCATION+"/"+result.getString("map")),
                        getAnswersByQuestionID(result.getInt("question_id")),
                        getQuestionCompletionByUserID(result.getInt("question_id"),userID),
                        getQuestionTriesByUserID(result.getInt("question_id"),userID)
                        ));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return questions;
    }
    
    public boolean getQuestionCompletionByUserID(int questionID, int userID){
          try {
            String sql = "SELECT * FROM user_question WHERE question_id='" + userID + "' AND user_id='"+userID+"'";
            ResultSet result = dbmanager.doQuery(sql);
            if(result.next()) {
                 return result.getBoolean("complete");
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
       return false;
    }
    
    public int getQuestionTriesByUserID(int questionID, int userID){
          try {
            String sql = "SELECT * FROM user_question WHERE question_id='" + userID + "' AND user_id='"+userID+"'";
            ResultSet result = dbmanager.doQuery(sql);
            if(result.next()) {
                 return result.getInt("tries");
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return 0;
    }
    
    public ArrayList<Answer> getAnswersByQuestionID(int questionID){
        ArrayList<Answer> answers = new ArrayList<Answer>();
        try {
            String sql = "SELECT * FROM answer WHERE question_id='" + questionID + "'";
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()) {
                 answers.add(new Answer(
                         result.getInt("x"),
                         result.getInt("y"),
                         result.getBoolean("correct"),
                         result.getString("answer")
                         ));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return answers;
    }
    
    
}
