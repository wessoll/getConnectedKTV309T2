package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import we.getconnected.Main;

/**
 * Een vraag met eventueel bijbehorende map(=kaart)
 * @author wesley
 */
public class Question {
    
    private String question, mapPath;
    private int question_id;
    private boolean correct;//goed beantwoorde vraag
    private int tries;//aantal geprobeerde antwoorden
    private ArrayList<Answer> answers;//lijst met de mogelijke antwoorden
    private Timestamp available;
    private int country_id;
    
    /**
     * Constructor voor een bestaande vraag uit de database gekoppeld aan een user
     * user
     * @param question_id   id van de vraag
     * @param question      naam van de vraag
     * @param mapPath       map plaatje (bijv. hoogtekaart)
     * @param answers       lijst met mogelijke antwoorden
     * @param correct       boolean of deze vraag beantwoord is of niet
     * @param tries         het aantal pogingen dat gedaan is op deze vraag
     * @param available     datumtijd vanaf wanneer de vraag beantwoord mag worden
     */
    public Question(int question_id, String question, String mapPath, ArrayList<Answer> answers, byte correct, int tries, Timestamp available){
        this.question_id = question_id;
        this.question = question;
        this.mapPath = mapPath;
        this.answers = answers;
        if (correct == 0){
            this.correct = false;
        }
        else{
            this.correct = true;
        }
        this.tries = tries;
        this.available = available;
    }
    
        /**
     * Constructor voor het aanmaken van een nieuwe vraag in de database
     * @param question_id   id van de vraag
     * @param question      naam van de vraag
     * @param mapPath       map plaatje (bijv. hoogtekaart)
     * @param answers       lijst met mogelijke antwoorden
     * @param country_id    id van het land waaraan de vraag gekoppeld is
     */
    public Question(int question_id, String question, String mapPath, ArrayList<Answer> answers, int country_id){
        this.question_id = question_id;
        this.question = question;
        this.mapPath = mapPath;
        this.answers = answers;
        this.country_id = country_id;
    }
    
    //Getters and setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String text) {
        this.question = text;
    }

    public String getMapPath() {
        return mapPath;
    }

    public void setMapPath(String mapPath) {
        this.mapPath = mapPath;
    }
    
    public boolean isCorrect(){
        return correct;
    }
    
    public void setCorrect(boolean correct){
        this.correct = correct;
    }
    
    public int getTries(){
        return tries;
    }
    
    public void setTries(int tries){
        this.tries = tries;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
    
    public int getQuestion_Id(){
        return question_id;
    }
    
    public void setQuestion_Id(int question_id){
        this.question_id = question_id;
    }

    public Timestamp getAvailable() {
        return available;
    }

    public void setAvailable(Timestamp date) {
        this.available = date;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}