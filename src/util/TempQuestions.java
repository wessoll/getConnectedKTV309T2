/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Question;
import we.getconnected.Main;

/**
 * IN DEZE KLASSE WORDEN VOOR HET GEMAKT EVEN ALLE VRAGEN DIE WE HEBBEN OPGESLAGEN (VOOR NU)
 * @author wesley
 */
public class TempQuestions {
    
    /*public static Question getQuestion1(){
        ArrayList<Answer> antwoorden = new ArrayList<Answer>();
    
        antwoorden.add(new Answer(350,290,false,"A"));
        antwoorden.add(new Answer(240,240,false,"B"));
        antwoorden.add(new Answer(340,500,true,"C"));
        antwoorden.add(new Answer(400,100,false,"D"));
        
        Question question = null;
        try {
            question = new Question("Waar ligt het hoogste punt van Nederland?",
           ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "hoogtekaart.gif"),
           antwoorden);
        } catch (Exception ex) {
            Logger.getLogger(TempQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }
        public static Question getQuestion2(){
        ArrayList<Answer> antwoorden = new ArrayList<Answer>();
    
        antwoorden.add(new Answer(380,480,false,"A"));
        antwoorden.add(new Answer(220,200,true,"B"));
        antwoorden.add(new Answer(100,450,false,"C"));
        antwoorden.add(new Answer(450,250,false,"D"));
        
        Question question = null;
        try {
            question = new Question("Waar ligt het het laagste punt van Nederland?",
           ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "hoogtekaart.gif"),
           antwoorden);
        } catch (Exception ex) {
            Logger.getLogger(TempQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
        }
       
        public static Question getQuestion3(){
        ArrayList<Answer> antwoorden = new ArrayList<Answer>();
    
        antwoorden.add(new Answer(380,480,false,"A"));
        antwoorden.add(new Answer(220,200,true,"B"));
        antwoorden.add(new Answer(100,450,false,"C"));
        antwoorden.add(new Answer(450,250,false,"D"));
        
        Question question = null;
        try {
            question = new Question("Waar ligt de snelweg A1?",
           ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "snelwegenkaart.jpg"),
           antwoorden);
        } catch (Exception ex) {
            Logger.getLogger(TempQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }
        public static Question getQuestion4(){
        ArrayList<Answer> antwoorden = new ArrayList<Answer>();
    
        antwoorden.add(new Answer(380,480,false,"A"));
        antwoorden.add(new Answer(220,200,true,"B"));
        antwoorden.add(new Answer(100,450,false,"C"));
        antwoorden.add(new Answer(450,250,false,"D"));
        
        Question question = null;
        try {
            question = new Question("Waar ligt de snelweg A2?",
           ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "snelwegenkaart.jpg"),
           antwoorden);
        } catch (Exception ex) {
            Logger.getLogger(TempQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }
        public static Question getQuestion5(){
        ArrayList<Answer> antwoorden = new ArrayList<Answer>();
    
        antwoorden.add(new Answer(380,480,false,"A"));
        antwoorden.add(new Answer(220,200,true,"B"));
        antwoorden.add(new Answer(100,450,false,"C"));
        antwoorden.add(new Answer(450,250,false,"D"));
        
        Question question = null;
        try {
            question = new Question("In welke provincie ligt de hoofdstad van Nederland",
           ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "provincies.jpg"),
           antwoorden);
        } catch (Exception ex) {
            Logger.getLogger(TempQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }
        public static Question getQuestion6(){
        ArrayList<Answer> antwoorden = new ArrayList<Answer>();
    
        antwoorden.add(new Answer(380,480,false,"A"));
        antwoorden.add(new Answer(220,200,true,"B"));
        antwoorden.add(new Answer(100,450,false,"C"));
        antwoorden.add(new Answer(450,250,false,"D"));
        
        Question question = null;
        try {
            question = new Question("Waar ligt het warmste gebied in Nederland?",
           ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "temperatuurkaart.png"),
           antwoorden);
        } catch (Exception ex) {
            Logger.getLogger(TempQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }
        public static Question getQuestion7(){
        ArrayList<Answer> antwoorden = new ArrayList<Answer>();
    
        antwoorden.add(new Answer(380,480,false,"A"));
        antwoorden.add(new Answer(220,200,true,"B"));
        antwoorden.add(new Answer(100,450,false,"C"));
        antwoorden.add(new Answer(450,250,false,"D"));
        
        Question question = null;
        try {
            question = new Question("Waar ligt het koudste gebied in Nederland?",
           ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "temperatuurkaart.png"),
           antwoorden);
        } catch (Exception ex) {
            Logger.getLogger(TempQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }
        public static Question getQuestion8(){
        ArrayList<Answer> antwoorden = new ArrayList<Answer>();
    
        antwoorden.add(new Answer(380,480,false,"A"));
        antwoorden.add(new Answer(220,200,true,"B"));
        antwoorden.add(new Answer(100,450,false,"C"));
        antwoorden.add(new Answer(450,250,false,"D"));
        
        Question question = null;
        try {
            question = new Question("In welk gebied in Nederland waait het het hardst?",
           ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "windkaart.png"),
           antwoorden);
        } catch (Exception ex) {
            Logger.getLogger(TempQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }
        public static Question getQuestion9(){
        ArrayList<Answer> antwoorden = new ArrayList<Answer>();
    
        antwoorden.add(new Answer(380,480,false,"A"));
        antwoorden.add(new Answer(220,200,true,"B"));
        antwoorden.add(new Answer(100,450,false,"C"));
        antwoorden.add(new Answer(450,250,false,"D"));
        
        Question question = null;
        try {
            question = new Question("In welk gebied in Nederland waait het het zachtst?",
           ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "windkaart.png"),
           antwoorden);
        } catch (Exception ex) {
            Logger.getLogger(TempQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }
        public static Question getQuestion10(){
        ArrayList<Answer> antwoorden = new ArrayList<Answer>();
    
        antwoorden.add(new Answer(380,480,false,"A"));
        antwoorden.add(new Answer(220,200,true,"B"));
        antwoorden.add(new Answer(100,450,false,"C"));
        antwoorden.add(new Answer(450,250,false,"D"));
        
        Question question = null;
        try {
            question = new Question("In welk gebied is de meeste neerslag gevallen?",
           ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "neerslag.png"),
           antwoorden);
        } catch (Exception ex) {
            Logger.getLogger(TempQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }
        public static Question getQuestion11() throws Exception{
        ArrayList<Answer> antwoorden = new ArrayList<Answer>();
    
        antwoorden.add(new Answer(380,480,false,"A"));
        antwoorden.add(new Answer(220,200,true,"B"));
        antwoorden.add(new Answer(100,450,false,"C"));
        antwoorden.add(new Answer(450,250,false,"D"));
        
        Question question = new Question("In welk gebied is de minste neerslag gevallen?",
                ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "neerslag.png"),
                antwoorden);
        return question;
    }
        
    */
    
    
}
