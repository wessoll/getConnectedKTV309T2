/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.ImageIcon;
import we.getconnected.Main;
import we.getconnected.gui.InterfaceComponent;

/**
 *
 * @author Lou
 */
public class ImageUtil {
    
    /**
     * Laad een image in het programma via een path
     * @param path              locatie van de image
     * @return                  image van de locatie
     * @throws Exception 
     */
    public static ImageIcon getAndLoadImageIcon(String path) throws Exception{
        ImageIcon imageIcon = new ImageIcon(path);
        if (imageIcon.getImageLoadStatus() != 8){
            throw new Exception("Cannot find image for path: \"" + path + "\"");
        }
        return imageIcon;
    }
    
    public static ImageIcon getImage(InterfaceComponent component){
        switch(Main.FRAME_SIZE){
            case NORMAL:
                return getNormalImage(component);
            case BIG:
                return getLargeImage(component);
                
        }
        return null;
    }
    
    private static ImageIcon getNormalImage(InterfaceComponent component){
        ImageIcon imageIcon = null;
        switch(component){
            case BACKGROUND:
                imageIcon =  new ImageIcon(Main.IMAGES_LOCATION + "Background1024x768.png");
                break;
            case MENU:
                imageIcon =  new ImageIcon(Main.IMAGES_LOCATION + "Menu1024x768.png");
                break;
            case HIGHSCORE:
                imageIcon =  new ImageIcon(Main.IMAGES_LOCATION + "Highscore1024x768.png");
                break;
            case PROFIEL:
                imageIcon =  new ImageIcon(Main.IMAGES_LOCATION + "Profiel1024x768.png");
                break;
            
        }
        return imageIcon;
    }
    
    private static ImageIcon getLargeImage(InterfaceComponent component){
        ImageIcon imageIcon = null;
        switch(component){
            case BACKGROUND:
                imageIcon =  new ImageIcon(Main.IMAGES_LOCATION + "Background1600x900.png");
                break;
            case MENU:
                imageIcon =  new ImageIcon(Main.IMAGES_LOCATION + "Menu1600x900.png");
                break;
            case HIGHSCORE:
                imageIcon =  new ImageIcon(Main.IMAGES_LOCATION + "Highscore1600x900.png");
                break;
            case PROFIEL:
                imageIcon =  new ImageIcon(Main.IMAGES_LOCATION + "Profiel1600x900.png");
                break;
            
        }
        return imageIcon;
    }
}
