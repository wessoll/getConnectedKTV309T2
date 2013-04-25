/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected;

import java.awt.BorderLayout;
import javax.swing.JApplet;
import javax.swing.JPanel;
import we.getconnected.gui.InterfaceSize;
import we.getconnected.gui.MainPanel;
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
    public static MainPanel mainPanel;
    private Dbmanager dbManager;
    private QueryManager queryManager;
    
    @Override
    public void init() {
        //dbManager = new Dbmanager();
        //dbManager.openConnection();
        //queryManager = new QueryManager(dbManager);
        IMAGES_LOCATION = getCodeBase().getPath().replace("/build/classes", "/src/media");
        this.setSize(FRAME_SIZE.getSize());
        
        //maak het MainPanel object aan dat de gehele interface bevat
        mainPanel = new MainPanel();
        this.add(mainPanel);
        //this.add(new UserInterfaceDesign());
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
