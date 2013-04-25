
package we.getconnected.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Land;
import model.Question;
import we.getconnected.Main;

/**
 * Panel dat de vragen als selectie toont
 * @author wesley
 */
public class QuestionSelection extends JPanel{
    
    private JButton btnQuestionSelect;
    private Question currentQuestion = null;
    
    /**
     * Constructor
     * @param land      het land waarvoor de vragen als selectie moeten worden getoond 
     */
    public QuestionSelection(Land land){
        setBackground(Color.GRAY);
        setBounds(0, 0, MainPanel.MAP_AREA_WIDTH, MainPanel.MAP_AREA_HEIGHT);
        //Gebruik flow layout zodat alle knoppen naast elkaar komen te staan horizontaal gecentreerd
        FlowLayout fLayout = new FlowLayout();
        fLayout.setAlignment(FlowLayout.CENTER);
        setLayout(fLayout);
        
        //set every question on screen for selection
        for (int i=0; i<land.getQuestions().size(); i++){
            currentQuestion = land.getQuestions().get(i);
            btnQuestionSelect = new JButton();
            btnQuestionSelect.setIcon(new ImageIcon(getClass().getResource("/media/vraagbuttons/" + (1 + i) + ".png")));
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