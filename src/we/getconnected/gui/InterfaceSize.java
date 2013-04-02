/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected.gui;

import java.awt.Dimension;

/**
 *
 * @author Lou
 */
    public enum InterfaceSize{
        NORMAL(new Dimension(1024,768)),
        BIG(new Dimension(1600,900));
        
        private Dimension dimension;
        
        InterfaceSize(Dimension dimension){
            this.dimension=dimension;
        }
        
        public Dimension getSize(){
            return dimension;
        }
    }
