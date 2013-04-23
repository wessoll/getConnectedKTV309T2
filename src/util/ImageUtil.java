/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Polygon;
import javax.swing.ImageIcon;
import model.Land.Landen;
import we.getconnected.Main;
import we.getconnected.gui.InterfaceComponent;
import we.getconnected.gui.InterfaceSize;

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
            case WORLDMAP:
                imageIcon =  new ImageIcon(Main.IMAGES_LOCATION + "Europe1024x768.jpg");
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
            case WORLDMAP:
                imageIcon =  new ImageIcon(Main.IMAGES_LOCATION + "Europe1600x900.png");
                break;
            
        }
        return imageIcon;
    }
    
    public static Polygon getCountryBounds(Landen land){
        switch(land){
            case BELGIE:
                break;
            case DENEMARKEN:
                break;
            case DUITSLAND:
                break;
            case FRANKRIJK:
                break;
            case ITALIE:
                break;
            case LUXEMBURG:
                break;
            case NEDERLAND:
                return (Main.FRAME_SIZE==InterfaceSize.NORMAL)?new Polygon(new int[]{492,491,503,474,461,477},new int[]{317,307,288,288,312,322},6):null;
            case NOORWEGEN:
                break;
            case OOSTENRIJK:
                break;
            case PORTUGAL_DKR:
                break;
            case PORTUGAL_DR:
                break;
            case SPANJE:
                break;
            case TSJECHIE:
                break;
            case ZWEDEN:
                break;
            default:
                break;
        }
        return null;
    }
}
