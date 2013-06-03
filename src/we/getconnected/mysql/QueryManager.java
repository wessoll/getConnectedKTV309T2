package we.getconnected.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Answer;
import model.Continent;
import model.Country;
import model.Question;
import model.User;
import we.getconnected.util.MD5Util;

public class QueryManager {

    private final Dbmanager dbmanager;

    public QueryManager(Dbmanager dbmanager) {
        this.dbmanager = dbmanager;
    }

     public ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<User>();
        try {
            String sql = "SELECT * FROM user";
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()) {
                users.add(new User(
                        result.getInt("user_id"),
                        result.getString("firstname"),
                        result.getString("lastname"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getBoolean("teacher"),
                        getUsergroup(result.getInt("user_id"))
                        ));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return users;
    }
    
    public User getUser(String username){
        User user=  null; 
        try {
            String sql = "SELECT * FROM user WHERE username='" + username + "'";
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()) {
                user = new User(
                        result.getInt("user_id"),
                        result.getString("firstname"),
                        result.getString("lastname"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getBoolean("teacher"),
                        getUsergroup(result.getInt("user_id"))
                        );
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return user;
    }
    
    public String getUsername(String username){
        String name=  null; 
        try {
            String sql = "SELECT username FROM user WHERE username='" + username + "'";
            ResultSet result = dbmanager.doQuery(sql);
            if(result.next()) {
                name = result.getString("username");
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return name;
    }
    
       public String getGroup(String groupname){
        String name=  null; 
        try {
            String sql = "SELECT groupName FROM groep WHERE groupName='" + groupname + "'";
            ResultSet result = dbmanager.doQuery(sql);
            if(result.next()) {
                name = result.getString("groupName");
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return name;
    }
    
    public Continent getContinent(String name, int user_id){
        Continent continent =  null; 
        try {
            String sql = "SELECT * FROM continent WHERE name='" + name + "'";
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()) {
                continent = new Continent(result.getInt("continent_id"),
                        name, user_id);
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return continent;
    }
    
    public void addUser(String username, char[] password, String firstName, String lastName, boolean isTeacher, int groupID){
        int userID = getMaxUserID()+1;
        int maxQuestionID = getMaxQuestionID();
        int maxCountryID = getMaxCountryID();
        String query = null;
        query="INSERT INTO user(user_id,username, password,firstName,Lastname,teacher) VALUES("+userID+",'"+username+"','"+MD5Util.md5(password)+"', '"+firstName+"', '"+lastName+"',"+((isTeacher)?1:0)+");";
        dbmanager.insertQuery(query);
        fillUserQuestions(maxQuestionID,userID);
        fillUserCountry(maxCountryID,userID);
        setUserGroup(userID,groupID);
    }
    
    public void setUserGroup(int userID, int groupID){
       String query ="INSERT INTO `user_group` (`user_id`, `group_id`) VALUES ('"+userID+"', '"+groupID+"');";
       dbmanager.insertQuery(query);
    }
        
    public void fillUserCountry(int maxCountryID,int userID){
        String query =null;
        for(int i =1;i<=maxCountryID;i++){
            query="INSERT INTO user_country (user_id,country_id,completed) VALUES ("+userID+","+i+",0);";
            dbmanager.insertQuery(query);
        }
    }
    
    public void fillUserQuestions(int maxQuestionID, int userID){
        String query = "";
        for(int i=1;i<=maxQuestionID;i++){
           query="INSERT INTO `user_question` (`user_id`, `question_id`, `tries`, `complete`)";
           query+= " VALUES ('"+userID+"', '"+i+"', '0', 0);";
           dbmanager.insertQuery(query);
        }
    }
    
    public void addGroup(String groupName){
        String query = "INSERT INTO groep(group_id, groupName) VALUES ("+getMaxGroupID()+","+groupName+"');";
        dbmanager.insertQuery(query);
    }
    
    public ArrayList<String> getGroupNames(){
        ArrayList<String> groupNames = new ArrayList<String>();
        try {
            String sql = "SELECT groupName FROM k00tj3_klassetv.groep;";
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()) {
                groupNames.add(result.getString("groupName"));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return groupNames;
    }
    
        public int getMaxGroupID(){
        int id =0;
        try {
            String sql = "SELECT MAX(group_id) FROM k00tj3_klassetv.groep;";
            ResultSet result = dbmanager.doQuery(sql);
            if(result.next()) {
                id = result.getInt("MAX(group_id)");
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return id;
    }
    
    public int getMaxUserID(){
        int id = 0;
        try {
            String sql = "SELECT MAX(user_id) FROM user;";
            ResultSet result = dbmanager.doQuery(sql);
            if(result.next()) {
              id = result.getInt("MAX(user_id)");
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return id;
    }
    
    public int getMaxQuestionID(){
        int id = 0;
        try {
            String sql = "SELECT MAX(question_id) from question;";
            ResultSet result = dbmanager.doQuery(sql);
            if(result.next()) {
              id = result.getInt("MAX(question_id)");
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return id;
    }
    
    public int getMaxCountryID(){
        int id = 0;
        try {
            String sql = "SELECT MAX(country_id) from country;";
            ResultSet result = dbmanager.doQuery(sql);
            if(result.next()) {
              id = result.getInt("MAX(country_id)");
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return id;
    }
    
    public String getUsergroup(int userID){
        String groupName = "";
         try {
            String sql = "SELECT groupName FROM groep INNER JOIN user_group ON groep.group_id=user_group.group_id WHERE user_group.user_id = "+userID;
            ResultSet result = dbmanager.doQuery(sql);
            if(result.next()) {
                groupName = result.getString("groupName");
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return groupName;
    }
    
    public ArrayList<Country> getUserCountries(int user_id){
        ArrayList<Country> landen = new ArrayList<Country>();
        
        try {
            String sql = "SELECT country.country_id, country.name, user_country.completed " + 
                    "FROM country " +
                    "INNER JOIN user_country " + 
                    "ON country.country_id=user_country.country_id " +
                    "WHERE user_country.user_id = " + user_id;
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()) {
                landen.add(new Country(result.getInt("country_id"),
                        Country.Countries.fromString(result.getString("name")),
                        getUserQuestions(user_id, result.getInt("country_id")),
                        result.getBoolean("completed")));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return landen;
    }
    
    public ArrayList<Question> getUserQuestions(int user_id, int country_id){
        ArrayList<Question> questions = new ArrayList<Question>();
        try {
            ;
            String sql = "SELECT question.question_id, question.question, question.map,user_question.complete,user_question.tries,user_question.available " +
                    "FROM question " +
                    "INNER JOIN user_question ON question.question_id=user_question.question_id " +
                    "WHERE user_question.user_id = " + user_id + " " +
                    "AND question.country_id = " + country_id;
            ResultSet result = dbmanager.doQuery(sql);
            
            while(result.next()) {
                questions.add(new Question(result.getInt("question_id"),
                        result.getString("question"),
                        result.getString("map"),
                        getAnswers(result.getInt("question_id")),
                        result.getByte("complete"),
                        result.getInt("tries"),
                        result.getTimestamp("available")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return questions;
    }
    
    public ArrayList<Answer> getAnswers(int question_id){
        ArrayList<Answer> answers = new ArrayList<Answer>();
        try {
            String sql = "SELECT * FROM answer WHERE question_id = " + question_id;
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()) {
                answers.add(new Answer(result.getInt("x"),
                        result.getInt("y"),
                        result.getByte("correct"),
                        result.getString("answer")));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return answers;
    }
    
    public Timestamp getDate(){
        Timestamp date = null;
        try{
            String query = "SELECT CURRENT_TIMESTAMP();";
            ResultSet result = dbmanager.doQuery(query);
            if (result.next()){
                date = result.getTimestamp("CURRENT_TIMESTAMP()");
            }
        }
        catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return date;
    }
    
    public int getLastQuestionId(){
        int question_id = 0;
        try{
            String query = "SELECT question_id FROM question ORDER BY question_id DESC LIMIT 1";
            ResultSet result = dbmanager.doQuery(query);
            if (result.next()){
                question_id = result.getInt("question_id");
            }
        }
        catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return question_id;
    }
    
    public void insertQuestion(Question question){
        String query = "INSERT INTO question (question_id, question, map, country_id) VALUES ("
                + "" + question.getQuestion_Id() + ", "
                + "'" + question.getQuestion() + "', "
                + "'" + question.getMap().toString() + "', "
                + "" + question.getCountry_id() + ")";
        }
        public void insertAnswers(ArrayList<Answer> answers){
        
        }
    
    public void updateUserQuestion(Question question, int user_id){
        String query = "UPDATE user_question SET tries= " + question.getTries() + ", complete=" + (question.isCorrect()?1:0) + 
                ", available = '" + question.getAvailable() + "'" +
                " WHERE user_id = " + user_id + 
                " AND question_id = " + question.getQuestion_Id();
        dbmanager.insertQuery(query);
    }
    
    public void updateUserCountry(Country country, int user_id){
        String query = "UPDATE user_country SET completed = " + (country.isCompleted()?1:0) + 
                " WHERE user_id = " + user_id +
                " AND country_id = " + country.getCountry_id();
        dbmanager.insertQuery(query);
    }
}