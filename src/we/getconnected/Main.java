package we.getconnected;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JApplet;
import javax.swing.JPanel;
import model.Continent;
import model.User;
import we.getconnected.gui.Login;
import we.getconnected.gui.MainPanel;
import we.getconnected.mysql.Dbmanager;
import we.getconnected.mysql.QueryManager;

/**
 *
 * @author Lou
 */
public class Main extends JApplet {

    public static final String NAME = "GetConnected Team KTV309";
    public static final Dimension INTERFACE_SIZE = new Dimension(1024,768);
    public static String IMAGES_LOCATION;
    private static MainPanel mainPanel;
    private static User currentUser;
    private Dbmanager dbManager;
    private static QueryManager queryManager;
    private static JPanel userInterface;
    
    @Override
    public void init() {
        IMAGES_LOCATION = getCodeBase().getPath().replace("/build/classes", "/src/media");
        dbManager = new Dbmanager();
        dbManager.openConnection();
        Main.queryManager = new QueryManager(dbManager);
        this.setSize(INTERFACE_SIZE.getSize());
        
        userInterface = new JPanel();
        userInterface.setSize(INTERFACE_SIZE);
        userInterface.setLayout(new BorderLayout());
        this.add(userInterface);
        
        userInterface.add(new Login());
        
        this.setJMenuBar(null);
        this.setVisible(true);
    }
    public static void showUserInterfacePanel(JPanel panel){
        userInterface.removeAll();
        userInterface.add(panel);
        userInterface.repaint();
        userInterface.revalidate();
    }
    
    
    @Override
    public void stop(){
        dbManager.closeConnection();
    }
    
    public static QueryManager getQueryManager(){
        return queryManager;
    }
    public static void setCurrentUser(User user){
        currentUser = user;
    }
    public static User getCurrentUser(){
        return currentUser;
    }
    public static MainPanel getMainPanel(){
        return mainPanel;
    }
    public static void setMainPanel(MainPanel mp){
        mainPanel = mp;
    }
}
