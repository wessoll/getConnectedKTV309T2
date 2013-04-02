/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import model.Answer;
import model.Question;
import we.getconnected.Main;

/**
 * IN DEZE KLASSE WORDEN VOOR HET GEMAKT EVEN ALLE VRAGEN DIE WE HEBBEN OPGESLAGEN (VOOR NU)
 * @author wesley
 */
public class TempQuestions {
    
    public static Question getQuestion1() throws Exception{
        ArrayList<Answer> antwoorden = new ArrayList<Answer>();
    
        antwoorden.add(new Answer(350,290,false,"A"));
        antwoorden.add(new Answer(240,240,false,"B"));
        antwoorden.add(new Answer(340,500,true,"C"));
        antwoorden.add(new Answer(400,100,false,"D"));
        
        Question question = new Question("Waar ligt het hoogste punt van Nederland?",
                ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "vraag_nederland_1.jpg"),
                antwoorden);
        return question;
    }
        public static Question getQuestion2() throws Exception{
        ArrayList<Answer> antwoorden = new ArrayList<Answer>();
    
        antwoorden.add(new Answer(380,480,false,"A"));
        antwoorden.add(new Answer(220,200,true,"B"));
        antwoorden.add(new Answer(100,450,false,"C"));
        antwoorden.add(new Answer(450,250,false,"D"));
        
        Question question = new Question("In welke provincie lopen de temperaturen het meest uiteen?",
                ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "vraag_nederland_2.png"),
                antwoorden);
        return question;
    }
    
    
    
}
