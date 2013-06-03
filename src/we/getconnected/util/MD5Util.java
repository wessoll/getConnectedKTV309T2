/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected.util;

import java.security.MessageDigest;

/**
 *
 * @author Lou
 */
public class MD5Util {
    
    /**
     * Maakt een MD5-hash van een char[]
     * @param password      De password char[] die moet worden geconvert naar een MD5 hash
     * @return              MD5-hash in String formaat
     */
    public static String md5(char[] password){
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
            System.out.println("Couldn't convert hash Error");
            return null;
        }
    }
    
}
