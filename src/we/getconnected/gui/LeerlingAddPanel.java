/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected.gui;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import we.getconnected.Main;

/**
 * Toevoegen van een nieuwe leerling in de database
 *
 * @author Lou
 */
public class LeerlingAddPanel extends JPanel{
    
     private JLabel usernameErrorLabel,succesLabel;
     private JTextField usernameField,naamField,achternaamField;
     private JButton backButton, addButton;
     private JPasswordField passwordField;
     private JComboBox teacherBox,klasBox;
     
     //Constanten voor dimensies van labels/fields/panels
     private Rectangle LABEL_USERNAME_BOUNDS = new Rectangle(70, 10, 120, 20);
     private Rectangle LABEL_ERROR_USERNAME_BOUNDS = new Rectangle(320, 10, 240, 20);
     private Rectangle LABEL_PASSWORD_BOUNDS = new Rectangle(70, 50, 120, 20);
     private Rectangle LABEL_NAAM_BOUNDS = new Rectangle(70, 90, 120, 20);
     private Rectangle LABEL_ACHTERNAAM_BOUNDS = new Rectangle(70, 130, 120, 20);
     private Rectangle LABEL_TEACHER_BOUNDS = new Rectangle(70, 170, 120, 20);
     private Rectangle LABEL_KLAS_BOUNDS = new Rectangle(70, 210, 120, 20);
     private Rectangle LABEL_SUCCES_BOUNDS = new Rectangle(190, (MainPanel.MAP_AREA.width-240)/2, 240, 40);
     
     private Rectangle FIELD_USERNAME_BOUNDS = new Rectangle(190, 10, 120, 20);
     private Rectangle FIELD_PASSWORD_BOUNDS = new Rectangle(190, 50, 120, 20);
     private Rectangle FIELD_NAAM_BOUNDS = new Rectangle(190, 90, 120, 20);
     private Rectangle FIELD_ACHTERNAAM_BOUNDS = new Rectangle(190, 130, 120, 20);
     
     private Rectangle COMBOBOX_TEACHER_BOUNDS = new Rectangle(190, 170, 120, 20);
     private Rectangle COMBOBOX_KLAS_BOUNDS = new Rectangle(190, 210, 120, 20);
     
     private Rectangle PANEL_BOTTOMBAR_BOUNDS = new Rectangle(0,0,MainPanel.BOTTOM_BAR.width,MainPanel.BOTTOM_BAR.height);
     
     
    /**
     * Constructor voor toevoegen van een nieuwe vraag aan de app
     */
    public LeerlingAddPanel(){
        setLayout(null);
        setBackground(MainPanel.BACKGROUND_COLOR);
        setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
        
        //Voeg labels, velden, boxes toe aan het LeerlingPanel
        JLabel usernameLabel = new JLabel("Gebruikers Naam:");
        usernameLabel.setBounds(LABEL_USERNAME_BOUNDS);
        add(usernameLabel);
        
        usernameField = new JTextField();
        usernameField.setBounds(FIELD_USERNAME_BOUNDS);
        add(usernameField);
        
        usernameErrorLabel = new JLabel("De username is al in gebruik!");
        usernameErrorLabel.setBounds(LABEL_ERROR_USERNAME_BOUNDS);
        usernameErrorLabel.setForeground(Color.RED);
        usernameErrorLabel.setVisible(false);
        add(usernameErrorLabel);
        
        JLabel passwordLabel = new JLabel("Wachtwoord:");
        passwordLabel.setBounds(LABEL_PASSWORD_BOUNDS);
        add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(FIELD_PASSWORD_BOUNDS);
        add(passwordField);
        
        JLabel naamLabel = new JLabel("Voornaam:");
        naamLabel.setBounds(LABEL_NAAM_BOUNDS);
        add(naamLabel);
        
        naamField = new JTextField();
        naamField.setBounds(FIELD_NAAM_BOUNDS);
        add(naamField);
        
        JLabel achternaamLabel = new JLabel("Achternaam:");
        achternaamLabel.setBounds(LABEL_ACHTERNAAM_BOUNDS);
        add(achternaamLabel);
        
        achternaamField = new JTextField();
        achternaamField.setBounds(FIELD_ACHTERNAAM_BOUNDS);
        add(achternaamField);
        
        JLabel teacherLabel = new JLabel("Is leraar:");
        teacherLabel.setBounds(LABEL_TEACHER_BOUNDS);
        add(teacherLabel);
        
        teacherBox = new JComboBox();
        teacherBox.addItem("Nee");
        teacherBox.addItem("Ja");
        teacherBox.setBounds(COMBOBOX_TEACHER_BOUNDS);
        add(teacherBox);
        
        JLabel klasLabel = new JLabel("Klas:");
        klasLabel.setBounds(LABEL_KLAS_BOUNDS);
        add(klasLabel);
        
        klasBox = new JComboBox();
        ArrayList<String> klasNames = Main.getQueryManager().getGroupNames();
        //Voeg alle klasnamen aan de Box toe
        for(String name:klasNames){
            klasBox.addItem(name);
        }
        klasBox.setBounds(COMBOBOX_KLAS_BOUNDS);
        add(klasBox);
        
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
                if(Main.getQueryManager().getUsername(usernameField.getText())==null){
                    usernameErrorLabel.setVisible(false);
                    //feedback voor de user
                    succesLabel.setText("De gebruiker '"+usernameField.getText()+"'is toegevoegd!");
                    succesLabel.setVisible(true);
                    Main.getQueryManager().addUser(usernameField.getText(),passwordField.getPassword(), naamField.getText(), achternaamField.getText(), ((teacherBox.getSelectedIndex()==0)?false:true), klasBox.getSelectedIndex()+1);
                }else{
                    //Verwijder (invisible) de oude feedback, laat de nieuwe feedback zien (negatief)
                    usernameErrorLabel.setVisible(true);
                    succesLabel.setVisible(false);
                }
            }
        }
    }
}
