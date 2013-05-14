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
    
    private int continent_id;
    private String continentName;
    private JLabel lblWorldMap, lblCompleted;
    private ArrayList<Country> landen;
    private static boolean questionsShuffled = false;
    boolean playable;
    
    private static final int LVL_COMPLETE_WIDTH = 818;
    private static final int LVL_COMPLETE_HEIGHT = 582;
    
    /**
     * Constructor voor het opzetten van het continent
     * @param continent_id      id van het continent
     * @param continentName    naam van het continent
     * @param user_id           de id van de user waaraan het continent gekoppeld is
     */
    public Continent(int continent_id, String continentName, int user_id){
        this.continent_id=continent_id;
        this.continentName=continentName;
        
        landen = Main.getQueryManager().getUserCountries(user_id);
        //shuffle de vragen eenmaal per land zodat ze niet bij iedereen in dezelfde volgorde komen
        if (!questionsShuffled){
            for (Country land : landen){
                Collections.shuffle(land.getQuestions());
            }
            questionsShuffled = true;
        }
        
        //initialize form
        setBackground(MainPanel.BACKGROUND_COLOR);
        setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
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
                if(playable){
                    for(Country land:landen){
                    if(land.getLand().getLandBounds().contains(e.getPoint())){
                        Main.getMainPanel().showPanelMapArea(new QuestionSelection(land));
                        break;
                    }
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
    
    public ArrayList<Country> getLanden(){
        return landen;
    }
    /**
     * Update de wereldmap met de landen die uitgespeeld zijn
     */
    public void updateWorldMap(){
        for (Country land : landen){
            if (land.isCompleted()){
                lblCompleted = new JLabel();
                lblCompleted.setBounds(0, 0, LVL_COMPLETE_WIDTH, LVL_COMPLETE_HEIGHT);
                lblCompleted.setIcon(land.getLand().getLandComplete());
                add(lblCompleted);
            }
        }
        add(lblWorldMap);
    }

    public int getContinent_id() {
        return continent_id;
    }

    public void setContinent_id(int continent_id) {
        this.continent_id = continent_id;
    }
    public String getContinentName(){
        return continentName;
    }
    public void setContinentName(String continentName){
        this.continentName = continentName;
    }
    
    public boolean isPlayable(){
        return playable;
    }
    
    public void setPlayable(boolean playable){
        this.playable=playable;
    }
}
