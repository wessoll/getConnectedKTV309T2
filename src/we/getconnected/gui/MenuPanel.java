/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import we.getconnected.Main;

/**
 *
 * @author Lou
 */
public class MenuPanel extends JPanel{
    
    private JButton vraagButton,klasButton,leerlingButton;
    
    /**
     * Menu Panel is het paneel voor de "Admin" (leraar) om het spel te bewerken.
     * In het MenuPanel selecteerd de leraar wat hij wil aanpassen/toevoegen.
     */
    public MenuPanel(){
        setLayout(null);
        setBackground(MainPanel.BACKGROUND_COLOR);
        setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
        
        vraagButton  = new JButton();
        ImageIcon vraagIcon = new ImageIcon(Main.IMAGES_LOCATION+"/VraagToevoegenBT.png");
        vraagButton.setIcon(vraagIcon);
        vraagButton.setBounds((MainPanel.MAP_AREA.width-vraagIcon.getIconWidth())/2, 20, vraagIcon.getIconWidth(), vraagIcon.getIconHeight());
        vraagButton.addActionListener(new AL());
        add(vraagButton);
        
        klasButton  = new JButton();
        ImageIcon klasIcon = new ImageIcon(Main.IMAGES_LOCATION+"/KlasToevoegenBT.png");
        klasButton.setIcon(klasIcon);
        klasButton.setBounds((MainPanel.MAP_AREA.width-klasIcon.getIconWidth())/2, 120, klasIcon.getIconWidth(), klasIcon.getIconHeight());
        klasButton.addActionListener(new AL());
        add(klasButton);
        
        leerlingButton  = new JButton();
        ImageIcon leerlingIcon = new ImageIcon(Main.IMAGES_LOCATION+"/LeerlingToevoegenBT.png");
        leerlingButton.setIcon(leerlingIcon);
        leerlingButton.setBounds((MainPanel.MAP_AREA.width-leerlingIcon.getIconWidth())/2, 220, leerlingIcon.getIconWidth(), leerlingIcon.getIconHeight());
        leerlingButton.addActionListener(new AL());
        add(leerlingButton);
    }
    
    /**
     * Action Listener voor het klikken op de knoppen van het Menu panel.
     */
    public class AL  implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
             if(e.getSource()==vraagButton){
               Main.getMainPanel().showPanelBottomBar(new AddQuestion());
               Main.getMainPanel().clearPanelMapArea(); 
               return;
           }
           
           if(e.getSource()==klasButton){
               Main.getMainPanel().clearPanelBottomBar();
               Main.getMainPanel().showPanelMapArea(new GroupAddPanel());
               return;
           }
           
           if(e.getSource()==leerlingButton){
               Main.getMainPanel().clearPanelBottomBar();
               Main.getMainPanel().showPanelMapArea(new LeerlingAddPanel());
           }
        }
    }
}
