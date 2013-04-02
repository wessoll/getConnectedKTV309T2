/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JApplet;
import javax.swing.JPanel;
import we.getconnected.gui.InterfaceSize;
import we.getconnected.gui.UserInterfaceDesign;
import we.getconnected.mysql.Dbmanager;
import we.getconnected.mysql.QueryManager;

/**
 *
 * @author Lou
 */
public class Main extends JApplet {

    public static final String NAME = "GetConnected Team KTV309";
    public static String IMAGES_LOCATION;
    public static final InterfaceSize FRAME_SIZE = InterfaceSize.NORMAL;
    
    private Dbmanager dbManager;
    private QueryManager queryManager;
    
    @Override
    public void init() {
        //dbManager = new Dbmanager();
        //dbManager.openConnection();
        //queryManager = new QueryManager(dbManager);
        IMAGES_LOCATION = getCodeBase().getPath().replace("/build/classes", "/src/media");
        this.setSize(FRAME_SIZE.getSize());
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
