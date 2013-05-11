
package we.getconnected.gui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Country;
import model.Question;
import we.getconnected.Main;

/**
 * Panel dat de vragen als selectie toont
 * @author wesley
 * @todo dimension en point objects for hardcoded int values
 * @todo van Main.mainPanel.showPanelMapArea(new QuestionPanel(currentQuestion)); eerder een methode maken
 * waarin de showPanelMapArea wordt aangeroepen, omdat in deze panel (moet dus methode zijn) ook de questionTextPanel
 * wordt geplaatst
 */
public class QuestionSelection extends JPanel{
    
    //lengtes en breedtes
    private static final Dimension KIES_VRAAG = new Dimension(400,39);
    private static final Dimension SELECT_BUTTON = new Dimension(28,40);
    
    private JButton btnQuestionSelect;
    private Country currentLand;
    
    /**
     * Constructor
     * @param land      het land waarvoor de vragen als selectie moeten worden getoond 
     */
    public QuestionSelection(Country land){
        currentLand = land;
        setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
        setLayout(null);
        
        //set de selecteer de vraag plaatje op het scherm
        JLabel lblKiesEenVraag = new JLabel();
        lblKiesEenVraag.setIcon(new ImageIcon(getClass().getResource("/media/vraagbuttons/kiesEenVraag.png")));
        lblKiesEenVraag.setBounds((MainPanel.MAP_AREA.width-KIES_VRAAG.width)/2, 10, KIES_VRAAG.width, KIES_VRAAG.height);
        add(lblKiesEenVraag);
        
        //set every question on screen for selection
        for (int i=0; i<currentLand.getQuestions().size(); i++){
            btnQuestionSelect = new JButton();
            btnQuestionSelect.setBorderPainted(false);
            btnQuestionSelect.setIcon(new ImageIcon(getClass().getResource("/media/vraagbuttons/" + (1 + i)+((currentLand.getQuestions().get(i).isCorrect())?"G":"")+".png")));
            btnQuestionSelect.setName(String.valueOf(i));
            btnQuestionSelect.setBounds(60+(i*60), 70, SELECT_BUTTON.width, SELECT_BUTTON.height);
            //add a mouselistener which patches you to the selected question
            btnQuestionSelect.addMouseListener(new MouseListener(){

                @Override
                public void mouseClicked(MouseEvent e) {
                    //strip de naam van de button om het id te krijgen
                    int selectedQuestion = Integer.parseInt(e.getSource().toString().substring(20, 21));
                    Main.getMainPanel().showPanelMapArea(new QuestionPanel(currentLand.getQuestions().get(selectedQuestion), currentLand));
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