package we.getconnected;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JPanel;
import model.Leaderboard;
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
    private static Leaderboard leaderboard;
    private Dbmanager dbManager;
    private static QueryManager queryManager;
    private static JPanel userInterface;
    private static ArrayList<User> leaderbordUsers;
    public static final long EXTRA_TIMEZONE_HOURS = 25200000;//het verschil in uren tussen Houston-Amsterdam
    
    @Override
    public void init() {
        IMAGES_LOCATION = getCodeBase().getPath().replace("/build/classes", "/src/media");
        dbManager = new Dbmanager();
        dbManager.openConnection();
        Main.queryManager = new QueryManager(dbManager);
        this.setSize(INTERFACE_SIZE.getSize());
        leaderbordUsers = queryManager.getUsers();
        leaderboard = new Leaderboard(leaderbordUsers);
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
    public static void setLeaderboard(Leaderboard lb){
        leaderboard = lb;
    }
    public static Leaderboard getLeaderboard(){
        return leaderboard;
    }
    public static ArrayList<User> getLeaderbordUsers(){
        return leaderbordUsers;
    }
    
    public static Image getImage(String imgUrl) {
        Image img = null;
        try {
            URL url = new URL(imgUrl);
            img = ImageIO.read(url);
        } catch (IOException e) {
        }
        return img;
    }
    
}
