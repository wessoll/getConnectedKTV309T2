package we.getconnected.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import we.getconnected.Main;

/**
 * Hoofd panel dat alle subpanels bevat
 * @author wesley
 */
public class MainPanel extends JPanel{
    
    //Lengte en breedtes
    public static final Dimension SIDEBAR = new Dimension(200,Main.INTERFACE_SIZE.height); 
    public static final Dimension BOTTOM_BAR = new Dimension(Main.INTERFACE_SIZE.width - SIDEBAR.width, 200);
    public static final Dimension MAP_AREA = new Dimension(Main.INTERFACE_SIZE.width - SIDEBAR.width, Main.INTERFACE_SIZE.height - BOTTOM_BAR.height);
    public static final Color BACKGROUND_COLOR = new Color(178, 181, 138);
   
    private JPanel sidebar, bottomBar, mapArea;
    private JButton btnMenu, btnMijnKaart, btnLeaderbord, btnUitloggen;
    
    /**
     * Constructor voor het opzetten van de hoofdonderdelen
     */
    public MainPanel(){
        setBackground(BACKGROUND_COLOR);
        setLayout(null);
        
        //maak de sidebar aan
        sidebar = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.drawImage(new ImageIcon(Main.IMAGES_LOCATION+"/sidebar.png").getImage(), 0, 0, null);
            }
        };
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBounds(0, 0, SIDEBAR.width, SIDEBAR.height);
        add(sidebar);
        
        //positioneer de knoppen op de sidebar en geef ze hun functie
        btnMenu = new JButton();
        btnMenu.setIcon(new ImageIcon(getClass().getResource("/media/Menu1024x768.png")));
        btnMenu.setBorderPainted(false);
        btnMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMenu.addMouseListener(new ButtonHandler());
        sidebar.add(btnMenu);
        
        btnMijnKaart = new JButton();
        btnMijnKaart.setIcon(new ImageIcon(getClass().getResource("/media/MijnKaart1024x768.png")));
        btnMijnKaart.setBorderPainted(false);
        btnMijnKaart.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMijnKaart.addMouseListener(new ButtonHandler());
        sidebar.add(btnMijnKaart);
        
        btnLeaderbord = new JButton();
        btnLeaderbord.setIcon(new ImageIcon(getClass().getResource("/media/Leaderboard.png")));
        btnLeaderbord.setBorderPainted(false);
        btnLeaderbord.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLeaderbord.addMouseListener(new ButtonHandler());
        sidebar.add(btnLeaderbord);
        
        btnUitloggen = new JButton();
        btnUitloggen.setIcon(new ImageIcon(getClass().getResource("/media/Logout.png")));
        btnUitloggen.setBorderPainted(false);
        btnUitloggen.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnUitloggen.addMouseListener(new ButtonHandler());
        sidebar.add(btnUitloggen);
        
        //maak de andere bars aan
        mapArea = new JPanel();
        mapArea.setLayout(null);
        mapArea.setBounds(SIDEBAR.width, 0, MAP_AREA.width, MAP_AREA.height);
        mapArea.setBackground(BACKGROUND_COLOR);
        add(mapArea);
        
        bottomBar = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.drawImage(new ImageIcon(Main.IMAGES_LOCATION+"/bottombar.png").getImage(), 0, 0, null);
            }
        };
        bottomBar.setLayout(null);
        bottomBar.setBounds(SIDEBAR.width, SIDEBAR.height - BOTTOM_BAR.height, BOTTOM_BAR.width, BOTTOM_BAR.height);
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
     * Verwijdert alle componenten in de bottom bar.
     */
    public void clearPanelBottomBar (){
        bottomBar.removeAll();
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
                showPanelMapArea(Main.getCurrentUser().getEurope());
                
                //update de map zodat alle uitgespeelde landen worden getoond
                Main.getCurrentUser().getEurope().updateWorldMap();
            }
            else if (e.getSource().equals(btnLeaderbord)){
                //do stuff for leaderbord
            }
            else if (e.getSource().equals(btnUitloggen)){
                //set mainPanel weer op null en open het loginscherm voor een nieuwe gebruiker
                Main.setMainPanel(null);
                Main.showUserInterfacePanel(new Login());
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