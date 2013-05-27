package we.getconnected.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import we.getconnected.Main;

/**
 * Dialog voor het tonen van een laadscherm met custom statusmessage
 * @author wesley
 */
public class Loader extends JDialog{
    
    private static final Dimension LOADER_DIALOG = new Dimension(500,250);
    private static final Dimension LOADER_IMAGE = new Dimension(655,456);
    private static final Dimension LOADER_TEXT = new Dimension(LOADER_DIALOG.width, 50);
    private static final Point LOADER_IMAGE_P = new Point(-70,-90);
    
    /**
     * Constructor voor het aanmaken van de jdialog met custom boodschap
     * @param statusMessage         de message die de dialog moet weergeven
     */
    public Loader(String statusMessage){
        //set dialog eigenschappen
        setTitle("Een ogenblik geduld");
        setBackground(MainPanel.BACKGROUND_COLOR);
        setLayout(null);
        setAlwaysOnTop(true);
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(LOADER_DIALOG);
        //get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //resize window to match center position
        setLocation((dim.width-LOADER_DIALOG.width)/2, (dim.height-LOADER_DIALOG.height)/2);
        
        //maak de melding op met een plaatje en de statusmessage
        JLabel lblImage = new JLabel();
        lblImage.setIcon(new ImageIcon(Main.IMAGES_LOCATION + "loading.gif"));
        lblImage.setBounds(LOADER_IMAGE_P.x, LOADER_IMAGE_P.y, LOADER_IMAGE.width, LOADER_IMAGE.height);
        
        JLabel lblMessage = new JLabel("", SwingConstants.CENTER);
        lblMessage.setText("<html><center>" + statusMessage + "</center></html>");
        lblMessage.setBounds(0, (LOADER_DIALOG.height - LOADER_TEXT.height), 
                LOADER_DIALOG.width, LOADER_TEXT.height);
        lblMessage.setFont(new Font("Rockwell", Font.PLAIN, 20));
                
        add(lblMessage);
        add(lblImage);
    }
}