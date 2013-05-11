package model;

import java.awt.Point;

/**
 *
 * @author wesley
 */
public class Answer extends java.awt.Point{
    
    private boolean correct;
    private String answer;
    
    /**
     * Constructor
     * @param x                 x coordinaat op de map
     * @param y                 y coordinaat op de map
     * @param correct           boolean of dit het goede antwoord is
     * @param answer            antwoord tekst (bijv. A, B, C of D)
     */
    public Answer(int x, int y, byte correct, String answer){
        super(x,y);
        if (correct == 0){
            this.correct = false;
        }
        else{
            this.correct = true;
        }
        this.answer = answer;
    }
    
    //Getters and setters
    public boolean isCorrect(){
        return correct;
    }
    public void setCorrect(boolean correct){
        this.correct = correct;
    }
    public String getAnswer(){
        return answer;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
    @Override
    public Point getLocation(){
        return super.getLocation();
    }
    @Override
    public void setLocation(int x, int y){
        super.setLocation(x, y);
    }
}