package we.getconnected.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Answer;
import model.Question;
import we.getconnected.Main;

/**
 * Deze class zet de daadwerkelijke map en het panel dat de vraag houdt op het scherm
 * @author wesley
 */
public class QuestionPanel extends JPanel{
    
    private JLabel lblMap;
    private Question currentQuestion;
    
    /**
     * Constructor
     * @param question          Vraag die op het scherm getoond moet worden 
     */
    public QuestionPanel(Question question){
        this.currentQuestion = question;
         //initialize form
        setLayout(null);
        setBackground(Color.ORANGE);
        setBounds(0, 0, MainPanel.MAP_AREA_WIDTH, MainPanel.MAP_AREA_HEIGHT);
        
        //plaats de map in de mapArea
        lblMap = new JLabel();
        lblMap.setBounds(0, WIDTH, 818, 582);//HARDCODED
        lblMap.setIcon(currentQuestion.getMap());
        
         //Set de punten van de antwoorden op de kaart
        ArrayList<Answer> answers = currentQuestion.getAnswers();
        for (final Answer answer : answers){
            JLabel point = new JLabel(answer.getText());
            point.setFont(new Font("Verdana", Font.BOLD, 20));
            //voeg aan elk antwoord een mouselistener toe die het juiste of niet juiste antwoord afhandeld
            point.addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    //log het aantal pogingen en kijk of het juiste antwoord is geklikt
                    if (!answer.isCorrectAnswer()){
                        currentQuestion.setTries(currentQuestion.getTries() + 1);
                    }
                    else{
                        currentQuestion.setCorrect(true);
                        //het juiste antwoord is gegeven, do.....
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    //not supported
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    //not supported
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    //not supported
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    //not supported
                }
            });
            
            point.setBounds((int)answer.getLocation().getX(), (int)answer.getLocation().getY(), 20, 20);
            add(point);
        }
        add(lblMap);
        
        //maak een panel met de vraag en plaats deze in de bottombar
        //not functional yet
        JPanel panelText = new JPanel();
        panelText.setBackground(Color.pink);
        panelText.setBounds(0, 0, MainPanel.BOTTOM_BAR_WIDTH, MainPanel.BOTTOM_BAR_HEIGHT);
        panelText.add(new JLabel(question.getText()));
        Main.mainPanel.showPanelBottomBar(panelText);
    }
}