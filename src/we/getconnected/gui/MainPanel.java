/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Continent;

/**
 * Hoofd panel dat alle subpanels bevat
 * @author wesley
 */
public class MainPanel extends JPanel{
    
    //Lengte en breedtes
    public static final int MAIN_WIDTH = 1024;
    public static final int MAIN_HEIGHT = 768;
    public static final int SIDEBAR_WIDTH = 200;
    public static final int BOTTOM_BAR_WIDTH = MAIN_WIDTH - SIDEBAR_WIDTH;
    public static final int BOTTOM_BAR_HEIGHT = 200;
    public static final int MAP_AREA_WIDTH = MAIN_WIDTH - SIDEBAR_WIDTH;
    public static final int MAP_AREA_HEIGHT = MAIN_HEIGHT - BOTTOM_BAR_HEIGHT;
   
    private JPanel sidebar, bottomBar, mapArea;
    private JButton btnMenu, btnMijnKaart, btnLeaderbord, btnOpties;
    
    /**
     * Constructor voor het opzetten van de hoofdonderdelen
     */
    public MainPanel(){
        setBackground(Color.red);
        setLayout(null);
        
        //maak de sidebar aan
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBounds(0, 0, SIDEBAR_WIDTH, MAIN_HEIGHT);
        sidebar.setBackground(Color.blue);
        add(sidebar);
        
        //positioneer de knoppen op de sidebar en geef ze hun functie
        btnMenu = new JButton();
        btnMenu.setIcon(new ImageIcon(getClass().getResource("/media/Menu1024x768.png")));
        btnMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMenu.addMouseListener(new ButtonHandler());
        sidebar.add(btnMenu);
        
        btnMijnKaart = new JButton();
        btnMijnKaart.setIcon(new ImageIcon(getClass().getResource("/media/MijnKaart1024x768.png")));
        btnMijnKaart.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMijnKaart.addMouseListener(new ButtonHandler());
        sidebar.add(btnMijnKaart);
        
        btnLeaderbord = new JButton();
        btnLeaderbord.setIcon(new ImageIcon(getClass().getResource("/media/Highscore1024x768.png")));
        btnLeaderbord.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLeaderbord.addMouseListener(new ButtonHandler());
        sidebar.add(btnLeaderbord);
        
        btnOpties = new JButton();
        btnOpties.setIcon(new ImageIcon(getClass().getResource("/media/Opties1024x768.png")));
        btnOpties.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOpties.addMouseListener(new ButtonHandler());
        sidebar.add(btnOpties);
        
        //maak de andere bars aan
        mapArea = new JPanel();
        mapArea.setLayout(null);
        mapArea.setBounds(SIDEBAR_WIDTH, 0, MAP_AREA_WIDTH, MAP_AREA_HEIGHT);
        mapArea.setBackground(Color.yellow);
        add(mapArea);
        
        bottomBar = new JPanel();
        bottomBar.setLayout(null);
        bottomBar.setBounds(SIDEBAR_WIDTH, MAIN_HEIGHT - BOTTOM_BAR_HEIGHT, BOTTOM_BAR_WIDTH, BOTTOM_BAR_HEIGHT);
        bottomBar.setBackground(Color.green);
        add(bottomBar);
    }
    
    /**
     * Verwijdert bestaande panels op de mapArea en voegt een nieuwe toe
     * @param panel             de panel die zichtbaar moet worden in de mapArea 
     */
    public void showPanelMapArea(JPanel panel){
        mapArea.removeAll();
        mapArea.add(panel);
        mapArea.revalidate();
        mapArea.repaint();
    }
    
    /**
     * Verwijdert bestaande panels op de bottomBar en voegt een nieuwe toe
     * @param panel             de panel die zichtbaar moet worden in de bottomBar
     */
    public void showPanelBottomBar(JPanel panel){
        bottomBar.removeAll();
        bottomBar.add(panel);
        bottomBar.revalidate();
        bottomBar.repaint();
    }
    
    /**
     * Handelt alle button presses af
     */
    private class ButtonHandler implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource().equals(btnMenu)){
                //do stuff for menu
            }
            else if (e.getSource().equals(btnMijnKaart)){
                showPanelMapArea(new Continent());
            }
            else if (e.getSource().equals(btnLeaderbord)){
                //do stuff for leaderbord
            }
            else if (e.getSource().equals(btnOpties)){
                //do stuff for opties
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
    }
}