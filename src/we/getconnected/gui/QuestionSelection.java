
package we.getconnected.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Land;
import model.Question;
import we.getconnected.Main;

/**
 * Panel dat de vragen als selectie toont
 * @author wesley
 * @todo dimension en point objects for hardcoded int values
 */
public class QuestionSelection extends JPanel{
    
    private JButton btnQuestionSelect;
    private Question currentQuestion = null;
    
    /**
     * Constructor
     * @param land      het land waarvoor de vragen als selectie moeten worden getoond 
     */
    public QuestionSelection(Land land){
        setBounds(0, 0, MainPanel.MAP_AREA_WIDTH, MainPanel.MAP_AREA_HEIGHT);
        setLayout(null);
        
        //set de selecteer de vraag plaatje op het scherm
        JLabel lblKiesEenVraag = new JLabel();
        lblKiesEenVraag.setIcon(new ImageIcon(getClass().getResource("/media/vraagbuttons/kiesEenVraag.png")));
        lblKiesEenVraag.setBounds((MainPanel.MAP_AREA_WIDTH-400)/2, 10, 400, 39);
        add(lblKiesEenVraag);
        
        //set every question on screen for selection
        for (int i=0; i<land.getQuestions().size(); i++){
            currentQuestion = land.getQuestions().get(i);
            btnQuestionSelect = new JButton();
            btnQuestionSelect.setBorderPainted(false);
            btnQuestionSelect.setIcon(new ImageIcon(getClass().getResource("/media/vraagbuttons/" + (1 + i) + ".png")));
            btnQuestionSelect.setBounds(60+(i*60), 70, 29, 40);
            //add a mouselistener which patches you to the selected question
            btnQuestionSelect.addMouseListener(new MouseListener(){

                @Override
                public void mouseClicked(MouseEvent e) {
                    Main.mainPanel.showPanelMapArea(new QuestionPanel(currentQuestion));
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
            add(btnQuestionSelect);
        }
    }
}