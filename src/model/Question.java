package model;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Een vraag met eventueel bijbehorende map(=kaart)
 * @author wesley
 */
public class Question {
    
    private String text;
    private ImageIcon map;
    private boolean correct;//goed beantwoorde vraag
    private int tries;//aantal geprobeerde antwoorden
    private ArrayList<Answer> answers;//lijst met de mogelijke antwoorden
    
    /**
     * Constructor met map
     * @param text          naam van de vraag
     * @param map           map plaatje (bijv. hoogtekaart)
     * @param answers       lijst met mogelijke antwoorden
     */
    public Question(String text, ImageIcon map, ArrayList<Answer> answers,boolean correct, int tries){
        this.text = text;
        this.map = map;
        this.answers = answers;
        this.correct = correct;
        this.tries = tries;
    }
    
    //Getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}