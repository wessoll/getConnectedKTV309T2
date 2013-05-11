package we.getconnected.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
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
    
    private JTextField txtUsername;
    private JPasswordField pwfPassword;
    private JButton btnSubmit;
    
    public Login(){
        //panel settings
        setLayout(null);
        setBackground(MainPanel.BACKGROUND_COLOR);
        setSize(Main.INTERFACE_SIZE);
        
        //username, password en submit fields/button
        txtUsername = new JTextField();
        txtUsername.setBounds(0, 300, 200, 20);
        txtUsername.getDocument().addDocumentListener(new fieldListener());
        add(txtUsername);
        
        pwfPassword = new JPasswordField();
        pwfPassword.setBounds(300,300,200,20);
        pwfPassword.getDocument().addDocumentListener(new fieldListener());
        add(pwfPassword);
        
        btnSubmit = new JButton("Login");
        btnSubmit.setBounds(600, 300, 100, 20);
        btnSubmit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String password = md5(pwfPassword.getPassword());
                    //haal user uit database volgens txtUsername
                    User user = Main.getQueryManager().getUser(txtUsername.getText());
                    //als username niet bestaat of wachtwoord niet overeenkomt: toon error
                    if (user == null || !user.getPassword().equals(password)){
                        JOptionPane.showMessageDialog(null, "Uw gebruikersnaam of wachtwoord is onjuist.", "Foutieve inloggegevens", JOptionPane.ERROR_MESSAGE);
                    }
                    //set anders het currentUser object in main
                    else {
                        Main.setCurrentUser(user);
                        Main.setMainPanel(new MainPanel());
                    }
                }
                catch(Exception ex){
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        add(btnSubmit);
        
        //run eenmaal fieldValidations om de submitknop op het begin te blokkeren
        fieldValidations();
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
