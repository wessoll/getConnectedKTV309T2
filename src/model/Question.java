package model;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import we.getconnected.Main;

/**
 * Een vraag met eventueel bijbehorende map(=kaart)
 * @author wesley
 */
public class Question {
    
    private String question;
    private int question_id;
    private ImageIcon map;
    private boolean correct;//goed beantwoorde vraag
    private int tries;//aantal geprobeerde antwoorden
    private ArrayList<Answer> answers;//lijst met de mogelijke antwoorden
    
    /**
     * Constructor met map
     * @param question_id   id van de vraag
     * @param question      naam van de vraag
     * @param map           map plaatje (bijv. hoogtekaart)
     * @param answers       lijst met mogelijke antwoorden
     * @param correct       boolean of dit land is uitgespeeld of niet
     * @param tries         het aantal pogingen dat gedaan is op deze vraag
     */
    public Question(int question_id, String question, String map, ArrayList<Answer> answers, byte correct, int tries){
        this.question_id = question_id;
        this.question = question;
        this.map = new ImageIcon(Main.IMAGES_LOCATION + map);
        this.answers = answers;
        if (correct == 0){
            this.correct = false;
        }
        else{
            this.correct = true;
        }
        this.tries = tries;
    }
    
    //Getters and setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String text) {
        this.question = text;
    }

    public ImageIcon getMap() {
        return map;
    }

    public void setMap(ImageIcon map) {
        this.map = map;
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
}