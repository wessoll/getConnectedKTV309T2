package we.getconnected.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.User;
import we.getconnected.Main;

/**
 *
 * @author tim,wesley
 */
public class Login extends JPanel {
    
    private JLabel background;
    private JTextField txtUsername;
    private JPasswordField pwfPassword;
    private JButton btnSubmit;
    
    private static final Dimension BUTTON = new Dimension(119,38);
    
    public Login(){
        //panel settings
        setLayout(null);
        setSize(Main.INTERFACE_SIZE);
        
        //maak de background aan
        background = new JLabel();
        background.setBounds(0,0,Main.INTERFACE_SIZE.width,Main.INTERFACE_SIZE.height);
        background.setIcon(new ImageIcon(Main.IMAGES_LOCATION + "LoginScreen.png"));
        
        //username, password en submit fields/button
        txtUsername = new JTextField();
        txtUsername.setBounds(470, 353, 190, 20);
        txtUsername.getDocument().addDocumentListener(new fieldListener());
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnSubmitActionPerformed();
                }
            }
        });
        add(txtUsername);
        
        pwfPassword = new JPasswordField();
        pwfPassword.setBounds(470,390,190,20);
        pwfPassword.getDocument().addDocumentListener(new fieldListener());
        pwfPassword.addKeyListener(new java.awt.event.KeyAdapter() {
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
        add(background);
        
        //run eenmaal fieldValidations om de submitknop op het begin te blokkeren
        fieldValidations();
    }
    
    private void btnSubmitActionPerformed() {
        if (btnSubmit.isEnabled()){
        try {
            String password = md5(pwfPassword.getPassword());
            //haal user uit database volgens txtUsername
            User user = Main.getQueryManager().getUser(txtUsername.getText());
            //als username niet bestaat of wachtwoord niet overeenkomt: toon error
            if (user == null || !user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(null, "Uw gebruikersnaam of wachtwoord is onjuist.", "Foutieve inloggegevens", JOptionPane.ERROR_MESSAGE);
            } //set anders het currentUser object in main
            else {
                //set currentuser als ingelogde gebruiker
                Main.setCurrentUser(user);
                //maak een nieuwe mainPanel aan voor de gebruiker en voeg deze toe aan de interface
                Main.setMainPanel(new MainPanel());
                Main.showUserInterfacePanel(Main.getMainPanel());
            }
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * Maakt een MD5-hash van een char[]
     * @param password      De password char[] die moet worden geconvert naar een MD5 hash
     * @return              MD5-hash in String formaat
     */
    public static String md5(char[] password) throws Exception{
        try {
            String stringPassword = "";
            for (char p : password){
                stringPassword += String.valueOf(p);
            }
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(stringPassword.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } 
        catch (java.security.NoSuchAlgorithmException e) {
            throw new Exception("Converting password to md5hash: " + e.getMessage());
        }
    }
    
    /**
     * Documentlistener die luistert naar een wijziging in de input van een textfield
     */
    private class fieldListener implements DocumentListener {
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
}
