/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected.gui;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Answer;
import model.Question;
import util.ImageUtil;
import we.getconnected.Main;

/**
 *
 * @author wesley
 */
public class QuestionInterface extends JPanel{
    
    private Question question;
    private JLabel checkmark;
    private static final String CHECKMARK_PATH = Main.IMAGES_LOCATION + "check_mark.png";
    
    /**
     * Constructor
     * @param question          de vraag die getoond moet worden
     * @throws Exception 
     */
    public QuestionInterface(Question question) throws Exception{
        setLayout(null);
        
        this.question = question;
        
        //set de map met de antwoorden op het scherm
        drawQuestion();
    }
    
    /**
     * Set de map op het scherm met de clickable antwoorden
     */
    public void drawQuestion(){
        //set de map op het scherm
        JLabel map = new JLabel();
        map.setIcon(question.getMap());
        map.setBounds(0, 0, question.getMap().getIconWidth(), question.getMap().getIconHeight());
        
        //maak een checkmark die wordt getoond wanneer de vraag juist is beantwoord
        checkmark = new JLabel();
        ImageIcon checkmarkImage = null;
        try{
            checkmarkImage = ImageUtil.getAndLoadImageIcon(CHECKMARK_PATH);
            checkmark.setIcon(checkmarkImage);
            checkmark.setBounds(200, 200, checkmarkImage.getIconWidth(), checkmarkImage.getIconHeight());
        }
        catch(Exception e){
            System.err.println("cannot load image");
        }
        
        //toon de antwoorden op de map
        for (Answer a : question.getAnswers()){
            JLabel label = new JLabel(a.getText());
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setBounds((int)a.getX(), (int)a.getY(), 20, 20);
            
            //er hoeft alleen geluisterd te worden naar een correct antwoord (VOORLOPIG VOOR PROTOTYPE!)
            if (a.isCorrectAnswer()){
                label.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent evt){
                        //wanneer het juiste antwoord geklikt is moet de vraag als correct worden gemarkeerd
                        //en moet er een checkmark worden getoond
                        question.setCorrect(true);
                        checkmark.setVisible(true);
                        repaint();
                    }
                });
            }
            add(label);
        }
        //set alles daadwerkelijk op het scherm, maar laat checkmark pas tonen wanneer de vraag juist beantwoord is
        if (question.isCorrect()){
            checkmark.setVisible(true);
        }
        else{
            checkmark.setVisible(false);
        }
        add(checkmark);
        add(map);
        repaint();
    }
}