package model;

import java.awt.Point;

/**
 *
 * @author wesley
 */
public class Answer extends java.awt.Point{
    
    private boolean correct;
    private String answer;
    private int question_id;
    
    /**
     * Constructor
     * @param x                 x coordinaat op de map
     * @param y                 y coordinaat op de map
     * @param correct           boolean of dit het goede antwoord is
     * @param answer            antwoord tekst (bijv. A, B, C of D)
     * @param question_id       id van de vraag waaran dit antwoord gekoppeld is
     */
    public Answer(int x, int y, byte correct, String answer, int question_id){
        super(x,y);
        if (correct == 0){
            this.correct = false;
        }
        else{
            this.correct = true;
        }
        this.answer = answer;
        this.question_id = question_id;
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

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
}