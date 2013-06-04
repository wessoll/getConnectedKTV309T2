/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import we.getconnected.Main;

/**
 *
 * @author Lou
 */
public class GroupAddPanel extends JPanel {
    private JLabel nameErrorLabel,succesLabel;
    private JButton backButton, addButton;
    private JTextField nameField;
    
    public GroupAddPanel(){
        setLayout(null);
        setBackground(MainPanel.BACKGROUND_COLOR);
        setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
        
        JLabel usernameLabel = new JLabel("Groep naam:");
        usernameLabel.setBounds(70, 10, 120, 20);
        add(usernameLabel);
        
        nameField = new JTextField();
        nameField.setBounds(190, 10, 120, 20);
        add(nameField);
        
        nameErrorLabel = new JLabel("De groep naam is al in gebruik!");
        nameErrorLabel.setBounds(320, 10, 240, 20);
        nameErrorLabel.setForeground(Color.RED);
        nameErrorLabel.setVisible(false);
        add(nameErrorLabel);
        
        succesLabel = new JLabel("");
        succesLabel.setBounds(190, (MainPanel.MAP_AREA.width-240)/2, 240, 40);
        succesLabel.setForeground(Color.GREEN);
        succesLabel.setVisible(false);
        add(succesLabel);
        
        JPanel bottomBar = new JPanel();
        bottomBar.setLayout(null);
        bottomBar.setBackground(MainPanel.BACKGROUND_COLOR);
        bottomBar.setBounds(0,0,MainPanel.BOTTOM_BAR.width,MainPanel.BOTTOM_BAR.height);
        
        backButton = new JButton();
        ImageIcon backIcon = new ImageIcon(Main.IMAGES_LOCATION+"/Terug.png");
        backButton.setIcon(backIcon);
        backButton.setBounds((MainPanel.BOTTOM_BAR.width-backIcon.getIconWidth())/2, 10, backIcon.getIconWidth(), backIcon.getIconHeight());
        backButton.addActionListener(new AL());
        bottomBar.add(backButton);
        
        addButton = new JButton();
        ImageIcon addIcon = new ImageIcon(Main.IMAGES_LOCATION+"/ToevoegenBT.png");
        addButton.setIcon(addIcon);
        addButton.setBounds((MainPanel.BOTTOM_BAR.width-addIcon.getIconWidth())/2, 70, addIcon.getIconWidth(), addIcon.getIconHeight());
        addButton.addActionListener(new AL());
        bottomBar.add(addButton);
        
        Main.getMainPanel().showPanelBottomBar(bottomBar);
    }
    
    
    /**
     * Action Listener voor het klikken op de knoppen van het Menu panel.
     */
    public class AL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==backButton){
                Main.getMainPanel().clearPanelBottomBar();
                Main.getMainPanel().showPanelMapArea(new MenuPanel());
                return;
            }
            if(e.getSource()==addButton){
                if(Main.getQueryManager().getGroup(nameField.getText())==null){
                  nameErrorLabel.setVisible(false);
                  succesLabel.setText("De groep '"+nameField.getText()+"'is toegevoegd!");
                  succesLabel.setVisible(true);
                  Main.getQueryManager().addGroup(nameField.getText());  
                }else{
                    nameErrorLabel.setVisible(true);
                    succesLabel.setVisible(false);
                }
                
            }
        }
    }
}
