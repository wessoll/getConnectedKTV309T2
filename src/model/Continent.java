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
    
    private int id;
    private String name;
    private JLabel lblWorldMap;
    private ArrayList<Land> landen;
    
    /**
     * Constructor voor het opzetten van het continent
     */
    public Continent(int id, String name, int userID){
        this.id=id;
        this.name=name;
        //this.landen = landenList;
         //initalize content
        //List<Question> questionList = new ArrayList<Question>();
        //questionList.add(TempQuestions.getQuestion1());
        //questionList.add(TempQuestions.getQuestion2());
        //questionList.add(TempQuestions.getQuestion3());
        
        //shuffle de vragen zodat ze niet telkens in dezelfde volgorde komen
        //Collections.shuffle(questionList);
        landen = Main.queryManager.getLandenByContinentID(id, userID);
        //landen.add(new Land(Landen.NEDERLAND,questionList));
        //landen.add(new Land(Landen.ITALIE,null));
        //landen.add(new Land(Landen.VERENIGD_KONINKRIJK,null));
        //landen.add(new Land(Landen.SPANJE,null));
        //landen.add(new Land(Landen.NOORWEGEN,null));
        
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
                //kijk welk land geklikt is a.d.v. landen bounds
                for(Land land:landen){
                    if(land.getLand().getLandBounds().contains(e.getPoint())){
                        Main.mainPanel.showPanelMapArea(new QuestionSelection(land));
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
