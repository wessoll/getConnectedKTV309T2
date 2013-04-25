package we.getconnected.gui;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Question;
import we.getconnected.Main;

/**
 * Deze class zet de daadwerkelijke map en het panel dat de vraag houdt op het scherm
 * @author wesley
 */
public class QuestionPanel extends JPanel{
    
    private JLabel lblMap;
    
    /**
     * Constructor
     * @param question          Vraag die op het scherm getoond moet worden 
     */
    public QuestionPanel(Question question){
         //initialize form
        setBackground(Color.ORANGE);
        setBounds(0, 0, MainPanel.MAP_AREA_WIDTH, MainPanel.MAP_AREA_HEIGHT);
        
        //plaats de map in de mapArea
        lblMap = new JLabel();
        lblMap.setIcon(question.getMap());
        add(lblMap);
        
        //maak een panel met de vraag en plaats deze in de bottombar
        //not functional yet
        JPanel panelText = new JPanel();
        panelText.add(new JLabel(question.getText()));
        Main.mainPanel.showPanelBottomBar(panelText);
    }
}