/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected;

import java.awt.BorderLayout;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import we.getconnected.gui.UserInterfaceDesign;
import we.getconnected.mysql.Dbmanager;
import we.getconnected.mysql.QueryManager;

/**
 *
 * @author Lou
 */
public class Main extends JApplet {

    public static final String NAME = "GetConnected Team KTV309";
    public static final String IMAGES_LOCATION = "/images";
    public static final int FRAME_HEIGHT=1600;
    public static final int FRAME_WIDTH=900;
    
    private Dbmanager dbManager;
    private QueryManager queryManager;
    
    @Override
    public void init() {
        //dbManager = new Dbmanager();
        //dbManager.openConnection();
        //queryManager = new QueryManager(dbManager);
        
        this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        this.add(new UserInterfaceDesign());
        this.setJMenuBar(null);
        this.setVisible(true);
    }
    
    public void showPanel(JPanel panel) {
       this.getContentPane().removeAll();
       this.getContentPane().add(panel, BorderLayout.CENTER);
       this.getContentPane().validate();
       this.getContentPane().repaint();
    }
    
    @Override
    public void stop(){
        //this.st
        //this.
        //dbManager.closeConnection();
    }
}
