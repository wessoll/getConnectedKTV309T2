/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Land.Landen;
import util.TempQuestions;
import we.getconnected.Main;
import we.getconnected.gui.MainPanel;
import we.getconnected.gui.QuestionSelection;

/**
 * De hoofdmap waarop alle landen te vinden zijn
 * @author Lou
 */
public class Continent extends JPanel{
    
    private JLabel lblWorldMap;
    private ArrayList<Land> landen;
    
    /**
     * Constructor voor het opzetten van het continent
     */
    public Continent(){
        //initalize content
        landen = new ArrayList<Land>();
        
        List<Question> questionList = new ArrayList<Question>();
        questionList.add(TempQuestions.getQuestion1());
        questionList.add(TempQuestions.getQuestion2());
        questionList.add(TempQuestions.getQuestion3());
        
        //shuffle de vragen zodat ze niet telkens in dezelfde volgorde komen
        Collections.shuffle(questionList);
        
        landen.add(new Land(Landen.NEDERLAND,questionList));
        
        //initialize form
        setBackground(Color.pink);
        setBounds(0, 0, MainPanel.MAP_AREA_WIDTH, MainPanel.MAP_AREA_HEIGHT);
        
        //plaats de wereldmap op het scherm
        lblWorldMap = new JLabel();
        lblWorldMap.setIcon(new ImageIcon(getClass().getResource("/media/Europe1024x768.jpg")));
        //schakel door naar de vragen selectie voor het land dat geklikt is
        lblWorldMap.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Main.mainPanel.showPanelMapArea(new QuestionSelection(landen.get(0)));
                for(Land land : landen){
                    if(land.getBounds().contains(e.getPoint())){
                        //hier moet dus hetgeen van 3 regels hierboven komen te staan
                        break;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //no supported
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //no supported
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //no supported
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //no supported
            }
              
        });
        add(lblWorldMap);
    }
}
