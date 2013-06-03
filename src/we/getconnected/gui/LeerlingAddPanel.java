/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import we.getconnected.Main;

/**
 *
 * @author Lou
 */
public class LeerlingAddPanel extends JPanel{
    
     private JLabel backButton, addButton,usernameErrorLabel,succesLabel;
     private JTextField usernameField,naamField,achternaamField;
     private JPasswordField passwordField;
     private JComboBox teacherBox,klasBox;
    
    public LeerlingAddPanel(){
        setLayout(null);
        setBackground(MainPanel.BACKGROUND_COLOR);
        setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
        
        JLabel usernameLabel = new JLabel("Gebruikers Naam:");
        usernameLabel.setBounds(70, 10, 120, 20);
        add(usernameLabel);
        
        usernameField = new JTextField();
        usernameField.setBounds(190, 10, 120, 20);
        add(usernameField);
        
        usernameErrorLabel = new JLabel("De username is al in gebruik!");
        usernameErrorLabel.setBounds(320, 10, 240, 20);
        usernameErrorLabel.setForeground(Color.RED);
        usernameErrorLabel.setVisible(false);
        add(usernameErrorLabel);
        
        JLabel passwordLabel = new JLabel("Wachtwoord:");
        passwordLabel.setBounds(70, 50, 120, 20);
        add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(190, 50, 120, 20);
        add(passwordField);
        
        JLabel naamLabel = new JLabel("Voornaam:");
        naamLabel.setBounds(70, 90, 120, 20);
        add(naamLabel);
        
        naamField = new JTextField();
        naamField.setBounds(190, 90, 120, 20);
        add(naamField);
        
        JLabel achternaamLabel = new JLabel("Achternaam:");
        achternaamLabel.setBounds(70, 130, 120, 20);
        add(achternaamLabel);
        
        achternaamField = new JTextField();
        achternaamField.setBounds(190, 130, 120, 20);
        add(achternaamField);
        
        JLabel teacherLabel = new JLabel("Is leraar:");
        teacherLabel.setBounds(70, 170, 120, 20);
        add(teacherLabel);
        
        teacherBox = new JComboBox();
        teacherBox.addItem("Nee");
        teacherBox.addItem("Ja");
        teacherBox.setBounds(190, 170, 120, 20);
        add(teacherBox);
        
        JLabel klasLabel = new JLabel("Klas:");
        klasLabel.setBounds(70, 210, 120, 20);
        add(klasLabel);
        
        klasBox = new JComboBox();
        ArrayList<String> klasNames = Main.getQueryManager().getGroupNames();
        for(String name:klasNames){
            klasBox.addItem(name);
        }
        klasBox.setBounds(190, 210, 120, 20);
        add(klasBox);
        
        succesLabel = new JLabel("");
        succesLabel.setBounds(190, (MainPanel.MAP_AREA.width-240)/2, 240, 40);
        succesLabel.setForeground(Color.GREEN);
        succesLabel.setVisible(false);
        add(succesLabel);
        
        JPanel bottomBar = new JPanel();
        bottomBar.setLayout(null);
        bottomBar.setBackground(MainPanel.BACKGROUND_COLOR);
        bottomBar.setBounds(0,0,MainPanel.BOTTOM_BAR.width,MainPanel.BOTTOM_BAR.height);
        
        backButton = new JLabel();
        ImageIcon backIcon = new ImageIcon(Main.IMAGES_LOCATION+"/Terug.png");
        backButton.setIcon(backIcon);
        backButton.setBounds((MainPanel.BOTTOM_BAR.width-backIcon.getIconWidth())/2, 10, backIcon.getIconWidth(), backIcon.getIconHeight());
        backButton.addMouseListener(new ML());
        bottomBar.add(backButton);
        
        addButton = new JLabel();
        ImageIcon addIcon = new ImageIcon(Main.IMAGES_LOCATION+"/ToevoegenBT.png");
        addButton.setIcon(addIcon);
        addButton.setBounds((MainPanel.BOTTOM_BAR.width-addIcon.getIconWidth())/2, 70, addIcon.getIconWidth(), addIcon.getIconHeight());
        addButton.addMouseListener(new ML());
        bottomBar.add(addButton);
        
        Main.getMainPanel().showPanelBottomBar(bottomBar);
    }
    
    public class ML implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==backButton){
                Main.getMainPanel().clearPanelBottomBar();
                Main.getMainPanel().showPanelMapArea(new MenuPanel());
                return;
            }
            if(e.getSource()==addButton){
                if(Main.getQueryManager().getUsername(usernameField.getText())==null){
                    usernameErrorLabel.setVisible(false);
                    succesLabel.setText("De gebruiker '"+usernameField.getText()+"'is toegevoegd!");
                    succesLabel.setVisible(true);
                    //Main.getQueryManager().addUser(usernameField.getText(),passwordField.getPassword(), naamField.getText(), achternaamField.getText(), ((teacherBox.getSelectedIndex()==0)?false:true), klasBox.getSelectedIndex()+1);
                }else{
                    usernameErrorLabel.setVisible(true);
                    succesLabel.setVisible(false);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
           
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
           
        }
    }
}
