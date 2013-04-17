package model;

import java.security.MessageDigest;

/**
 * Parentklasse voor het opzetten van een gebruiker
 * @author wesley
 */
public class User {
    private String firstName, lastName, userName, password;
    
    /**
     * Constructor
     * @param firstName         voornaam gebruiker
     * @param lastName          achternaam gebruiker
     * @param userName          username gebruiker
     * @param password          wachtwoord gebruiker
     */
    public User(String firstName, String lastName, String userName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
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

    //Getters and setters
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}