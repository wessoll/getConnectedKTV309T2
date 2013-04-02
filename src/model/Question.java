package model;

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
    
    /**
     * Constructor met map
     * @param text          naam van de vraag
     * @param map           map plaatje (bijv. hoogtekaart)
     */
    public Question(String text, ImageIcon map){
        this.text = text;
        this.map = map;
        correct = false;
        tries = 0;
    }
    /**
     * Constructor zonder map
     * @param text          naam van de vraag
     */
    public Question(String text){
        this.text = text;
        correct = false;
        tries = 0;
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
}