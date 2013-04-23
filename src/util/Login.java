/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.security.MessageDigest;

/**
 *
 * @author wesley
 */
public class Login {
    
    
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
            throw new Exception("Error bij omzetten md5hash, error: " + e.getMessage());
        }
    }
    
}
