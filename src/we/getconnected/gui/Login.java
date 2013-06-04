package we.getconnected.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.User;
import we.getconnected.Main;
import we.getconnected.util.MD5Util;

/**
 *
 * @author tim,wesley
 */
public class Login extends JPanel {
    
    private JLabel background, backgroundOverlay;
    private JTextField txtUsername;
    private JPasswordField pwfPassword;
    private JButton btnSubmit;
    private Cloud lblCloud1, lblCloud2, lblCloud3, lblCloud4, lblCloud5, lblCloud6;
    private boolean cloud1Entered= false, cloud2Entered= false, cloud3Entered= false,
            cloud4Entered = false, cloud5Entered = false, cloud6Entered = false;
    private Timer cloudTimerFast, cloudTimerSlow;
    private User user;
    private Loader loader;
    
    private static final Dimension BUTTON = new Dimension(119,38);

    public Login(){
        //panel settings
        setLayout(null);
        setSize(Main.INTERFACE_SIZE);
        
        //maak de background aan
        background = new JLabel();
        background.setBounds(0,0,Main.INTERFACE_SIZE.width,Main.INTERFACE_SIZE.height);
        background.setIcon(new ImageIcon(Main.IMAGES_LOCATION + "LoginScreen.png"));
        
        backgroundOverlay = new JLabel();
        backgroundOverlay.setBounds(0,0,Main.INTERFACE_SIZE.width,Main.INTERFACE_SIZE.height);
        backgroundOverlay.setIcon(new ImageIcon(Main.IMAGES_LOCATION + "LoginScreenOverlay.png"));
        
        //username, password en submit fields/button
        txtUsername = new JTextField();
        txtUsername.setBounds(470, 353, 190, 20);
        txtUsername.setOpaque(true);
        txtUsername.getDocument().addDocumentListener(new FieldListener());
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnSubmitActionPerformed();
                }
            }
        });
        add(txtUsername);
        
        pwfPassword = new JPasswordField();
        pwfPassword.setBounds(470,390,190,20);
        pwfPassword.setOpaque(true);
        pwfPassword.getDocument().addDocumentListener(new FieldListener());
        pwfPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnSubmitActionPerformed();
                }
            }
        });
        add(pwfPassword);
        
        btnSubmit = new JButton();
        btnSubmit.setBounds((Main.INTERFACE_SIZE.width-BUTTON.width)/2, 460, BUTTON.width, BUTTON.height);
        btnSubmit.setBorderPainted(false);
        btnSubmit.setIcon(new ImageIcon(Main.IMAGES_LOCATION + "LoginBT.png"));
        btnSubmit.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnSubmitActionPerformed();
                }
            }
        });
        btnSubmit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSubmitActionPerformed();
        }});
        
        add(btnSubmit);
        
        //maak de bewegende wolken aan
        lblCloud1 = new Cloud("cloud1.png", new Point(600,0), new Dimension(175,112));
        lblCloud1.addMouseListener(new CloudListener());
        lblCloud2 = new Cloud("cloud2.png", new Point(0,100), new Dimension(195,104));
        lblCloud2.addMouseListener(new CloudListener());
        lblCloud3 = new Cloud("cloud3.png", new Point(300,200), new Dimension(164,100));
        lblCloud3.addMouseListener(new CloudListener());
        lblCloud4 = new Cloud("cloud4.png", new Point(500,300), new Dimension(217,121));
        lblCloud4.addMouseListener(new CloudListener());
        lblCloud5 = new Cloud("cloud5.png", new Point(200,500), new Dimension(190,138));
        lblCloud5.addMouseListener(new CloudListener());
        lblCloud6 = new Cloud("cloud6.png", new Point(100,600), new Dimension(187,132));
        lblCloud6.addMouseListener(new CloudListener());
        
        add(backgroundOverlay);
        
        add(lblCloud1);
        add(lblCloud2);
        add(lblCloud3);
        add(lblCloud4);
        add(lblCloud5);
        add(lblCloud6);
        
        add(background);
        
        //maak de timers aan die de wolken over het scherm laat bewegen
        cloudTimerSlow = new Timer(50 ,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //cloud2
                if (!cloud2Entered){
                    if(lblCloud2.getX() == Main.INTERFACE_SIZE.width){
                        lblCloud2.setLocation(-lblCloud2.getWidth(), lblCloud2.getY());
                    }
                    lblCloud2.setLocation(lblCloud2.getX()+1, lblCloud2.getY());
                }
                
                //cloud 4
                if(!cloud4Entered){
                    if(lblCloud4.getX() == Main.INTERFACE_SIZE.width){
                        lblCloud4.setLocation(-lblCloud4.getWidth(), lblCloud4.getY());
                    }
                    lblCloud4.setLocation(lblCloud4.getX()+1, lblCloud4.getY());
                }
                
                //cloud 5
                if (!cloud5Entered){
                    if(lblCloud5.getX() == -lblCloud5.getWidth()){
                        lblCloud5.setLocation(Main.INTERFACE_SIZE.width, lblCloud5.getY());
                    }
                    lblCloud5.setLocation(lblCloud5.getX()-1, lblCloud5.getY());
                }
            } 
        });
        
        cloudTimerFast = new Timer(30 ,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //cloud1
                if (!cloud1Entered){
                    if(lblCloud1.getX() == Main.INTERFACE_SIZE.width){
                        lblCloud1.setLocation(-lblCloud1.getWidth(), lblCloud1.getY());
                    }
                    lblCloud1.setLocation(lblCloud1.getX()+1, lblCloud1.getY());
                }
                
                //cloud3
                if (!cloud3Entered){
                    if(lblCloud3.getX() == -lblCloud3.getWidth()){
                        lblCloud3.setLocation(Main.INTERFACE_SIZE.width, lblCloud3.getY());
                    }
                    lblCloud3.setLocation(lblCloud3.getX()-1, lblCloud3.getY());
                }
                
                //cloud 6
                if (!cloud6Entered){
                    if(lblCloud6.getX() == Main.INTERFACE_SIZE.width){
                        lblCloud6.setLocation(-lblCloud6.getWidth(), lblCloud6.getY());
                    }
                    lblCloud6.setLocation(lblCloud6.getX()+1, lblCloud6.getY());
                }
            } 
        });
        cloudTimerSlow.start();
        cloudTimerFast.start();
        
        //run eenmaal fieldValidations om de submitknop op het begin te blokkeren
        fieldValidations();
    }
    
    private void btnSubmitActionPerformed() {
        if (btnSubmit.isEnabled()){
            //voer de inlogprocedure uit
            loader = new Loader("Gegevens ophalen...");
            loader.setVisible(true);
            //maak een thread aan die de gegevens uit de database ophaalt
            Thread getDbData = new Thread(){
                @Override
                public void run() {
                    //converteer password naar md5
                    String password = null;
                    try {
                        password = MD5Util.md5(pwfPassword.getPassword());
                    } catch (Exception ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //haal user uit database volgens txtUsername
                    user = Main.getQueryManager().getUser(txtUsername.getText());
                    
                    //als username niet bestaat of wachtwoord niet overeenkomt: toon error
                    if (user == null || !user.getPassword().equals(password)) {
                        loader.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Uw gebruikersnaam of wachtwoord is onjuist.", "Foutieve inloggegevens", JOptionPane.ERROR_MESSAGE);
                    } 
                    //set anders het currentUser object in main
                    else {
                        //set timers stop
                        cloudTimerFast.stop();
                        cloudTimerSlow.stop();
                        //set currentuser als ingelogde gebruiker
                        Main.setCurrentUser(user);
                        //maak een nieuwe mainPanel aan voor de gebruiker en voeg deze toe aan de interface
                        Main.setMainPanel(new MainPanel());
                        Main.showUserInterfacePanel(Main.getMainPanel());
                        loader.setVisible(false);
                    }
                }
            };
            getDbData.start();
        }
    }

    /**
     * Validaties voor het blokkeren van de submit button wanneer een textfield leeg is
     */
        public void fieldValidations(){
            if (txtUsername.getText().isEmpty() || pwfPassword.getPassword().length == 0){
                btnSubmit.setEnabled(false);
            }
            else{
                btnSubmit.setEnabled(true);
            }
        }
    
    /**
     * Documentlistener die luistert naar een wijziging in de input van een textfield
     */
    private class FieldListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            fieldValidations();
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            fieldValidations();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            fieldValidations();
        }
    }
    
    /**
     * MouseListener die kijkt of de muis in een wolk staat en zoja de wolk "pauzeert"
     */
    private class CloudListener implements MouseListener{

        public void mouseClicked(MouseEvent e) {
            //not supported
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //not supported
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //not supported
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == lblCloud1){
                cloud1Entered = true;
            }
            if (e.getSource() == lblCloud2){
                cloud2Entered = true;
            }
            if (e.getSource() == lblCloud3){
                cloud3Entered = true;
            }            
            if (e.getSource() == lblCloud4){
                cloud4Entered = true;
            }            
            if (e.getSource() == lblCloud5){
                cloud5Entered = true;
            }            
            if (e.getSource() == lblCloud6){
                cloud6Entered = true;
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == lblCloud1){
                cloud1Entered = false;
            }
            if (e.getSource() == lblCloud2){
                cloud2Entered = false;
            }
            if (e.getSource() == lblCloud3){
                cloud3Entered = false;
            }            
            if (e.getSource() == lblCloud4){
                cloud4Entered = false;
            }            
            if (e.getSource() == lblCloud5){
                cloud5Entered = false;
            }            
            if (e.getSource() == lblCloud6){
                cloud6Entered = false;
            }
        }
    }
}
