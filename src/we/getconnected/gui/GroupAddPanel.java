/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected.gui;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import we.getconnected.Main;

/**
 * Toevoegen van een nieuwe groep in de database
 *
 * @author lou
 */
public class GroupAddPanel extends JPanel {
    private JLabel nameErrorLabel,succesLabel;
    private JButton backButton, addButton;
    private JTextField nameField;
    
    //Constanten voor dimensies van labels/fields/panels
    private Rectangle LABEL_NAME_BOUNDS = new Rectangle(70, 10, 120, 20);
    private Rectangle LABEL_NAMEERROR_BOUNDS = new Rectangle(320, 10, 240, 20);
    private Rectangle LABEL_SUCCES_BOUNDS = new Rectangle(190, (MainPanel.MAP_AREA.width-240)/2, 240, 40);
    
    private Rectangle FIELD_NAME_BOUNDS = new Rectangle(190, 10, 120, 20);
    
    private Rectangle PANEL_BOTTOMBAR_BOUNDS = new Rectangle(0,0,MainPanel.BOTTOM_BAR.width,MainPanel.BOTTOM_BAR.height);
    
    
    
    /**
     * Constructor voor toevoegen van een nieuwe vraag aan de app
     */
    public GroupAddPanel(){
        setLayout(null);
        setBackground(MainPanel.BACKGROUND_COLOR);
        setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
        
        //Voeg labels en velden toe aan het GroupPanel
        JLabel usernameLabel = new JLabel("Groep naam:");
        usernameLabel.setBounds(LABEL_NAME_BOUNDS);
        add(usernameLabel);
        
        nameField = new JTextField();
        nameField.setBounds(190, 10, 120, 20);
        add(nameField);
        
        nameErrorLabel = new JLabel("De groep naam is al in gebruik!");
        nameErrorLabel.setBounds(LABEL_NAMEERROR_BOUNDS);
        nameErrorLabel.setForeground(Color.RED);
        nameErrorLabel.setVisible(false);
        add(nameErrorLabel);
        
        succesLabel = new JLabel("");
        succesLabel.setBounds(LABEL_SUCCES_BOUNDS);
        succesLabel.setForeground(Color.GREEN);
        succesLabel.setVisible(false);
        add(succesLabel);
        
        JPanel bottomBar = new JPanel();
        bottomBar.setLayout(null);
        bottomBar.setBackground(MainPanel.BACKGROUND_COLOR);
        bottomBar.setBounds(PANEL_BOTTOMBAR_BOUNDS);
        
        backButton = new JButton();
        ImageIcon backIcon = new ImageIcon(Main.IMAGES_LOCATION+"/Terug.png");
        backButton.setIcon(backIcon);
        //X co-ordinaat voor de backButton
        int backButtonX = (MainPanel.BOTTOM_BAR.width-backIcon.getIconWidth())/2;
        backButton.setBounds(backButtonX, 10, backIcon.getIconWidth(), backIcon.getIconHeight());
        backButton.addActionListener(new ButtonListener());
        bottomBar.add(backButton);
        
        addButton = new JButton();
        ImageIcon addIcon = new ImageIcon(Main.IMAGES_LOCATION+"/ToevoegenBT.png");
        addButton.setIcon(addIcon);
        //X co-ordinaat voor de addButton
        int addButtonX = (MainPanel.BOTTOM_BAR.width-addIcon.getIconWidth())/2;
        addButton.setBounds(addButtonX, 70, addIcon.getIconWidth(), addIcon.getIconHeight());
        addButton.addActionListener(new ButtonListener());
        bottomBar.add(addButton);
        
        Main.getMainPanel().showPanelBottomBar(bottomBar);
    }
    
    
    /**
     * Action Listener voor het klikken op de knoppen van het Menu panel.
     */
    public class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             //Brengt de gebruiker terug naar het admin menu.
            if(e.getSource()==backButton){
                Main.getMainPanel().clearPanelBottomBar();
                Main.getMainPanel().showPanelMapArea(new MenuPanel());
                return;
            }
            //Stuurt gegevens naar de Database.
            if(e.getSource()==addButton){
                if(Main.getQueryManager().getGroup(nameField.getText())==null){
                  nameErrorLabel.setVisible(false);
                  //feedback voor de user
                  succesLabel.setText("De groep '"+nameField.getText()+"'is toegevoegd!");
                  succesLabel.setVisible(true);
                  Main.getQueryManager().addGroup(nameField.getText());  
                }else{
                    //Verwijder (invisible) de oude feedback, laat de nieuwe feedback zien (negatief)
                    nameErrorLabel.setVisible(true);
                    succesLabel.setVisible(false);
                }
                
            }
        }
    }
}
