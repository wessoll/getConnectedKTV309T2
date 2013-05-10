/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private JLabel lblWorldMap, lblCompleted;
    private ArrayList<Land> landen;
    private static boolean questionsShuffled = false;
    
    private static final int LVL_COMPLETE_WIDTH = 818;
    private static final int LVL_COMPLETE_HEIGHT = 582;
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
        
        landen = Main.queryManager.getLandenByContinentID(id, userID);
        //shuffle de vragen eenmalig zodat ze niet telkens in dezelfde volgorde komen
        if (!questionsShuffled){
            Collections.shuffle(landen);
            questionsShuffled = true;
        }
        //landen.add(new Land(Landen.NEDERLAND,questionList));
        //landen.add(new Land(Landen.ITALIE,null));
        //landen.add(new Land(Landen.VERENIGD_KONINKRIJK,null));
        //landen.add(new Land(Landen.SPANJE,null));
        //landen.add(new Land(Landen.NOORWEGEN,null));
        
        //initialize form
        setBackground(MainPanel.BACKGROUND_COLOR);
        setBounds(0, 0, MainPanel.MAP_AREA_WIDTH, MainPanel.MAP_AREA_HEIGHT);
        setLayout(null);
        
        //plaats de wereldmap op het scherm
        lblWorldMap = new JLabel();
        lblWorldMap.setBounds(0, 0, LVL_COMPLETE_WIDTH, LVL_COMPLETE_HEIGHT);
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
    
    public ArrayList<Land> getLanden(){
        return landen;
    }
    /**
     * Update de wereldmap met de landen die uitgespeeld zijn
     */
    public void updateWorldMap(){
        for (Land land : landen){
            if (land.isCompleted()){
                lblCompleted = new JLabel();
                lblCompleted.setBounds(0, 0, LVL_COMPLETE_WIDTH, LVL_COMPLETE_HEIGHT);
                lblCompleted.setIcon(land.getLand().getLandComplete());
                add(lblCompleted);
                add(lblWorldMap);
            }
        }
    }
}
