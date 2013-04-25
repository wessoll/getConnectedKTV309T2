/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;

/**
 *
 * @author wesley
 */
public class Answer extends java.awt.Point{
    
    private boolean correctAnswer;
    private String text;
    
    /**
     * Constructor
     * @param x                 x coordinaat
     * @param y                 y coordinaat
     * @param correctAnswer     boolean of dit het goede antwoord is
     * @param text              antwoord tekst (bijv. A, B, C of D)
     */
    public Answer(int x, int y, boolean correctAnswer, String text){
        super(x,y);
        this.correctAnswer = correctAnswer;
        this.text = text;
    }
    
    //Getters and setters
    public boolean isCorrectAnswer(){
        return correctAnswer;
    }
    public void setCorrectAnswer(boolean correctAnswer){
        this.correctAnswer = correctAnswer;
    }
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
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
