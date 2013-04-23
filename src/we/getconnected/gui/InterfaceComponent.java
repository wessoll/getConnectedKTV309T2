/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected.gui;

import util.ImageUtil;
import javax.swing.ImageIcon;

/**
 *
 * @author Lou
 */
    public enum InterfaceComponent{
        BACKGROUND,
        MENU,
        HIGHSCORE,
        PROFIEL,
        WORLDMAP
        ;
        
        public ImageIcon getIcon(){
            return ImageUtil.getImage(this);
        }
    }