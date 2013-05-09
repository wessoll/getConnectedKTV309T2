package model;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import util.ImageUtil;
import we.getconnected.Main;
import we.getconnected.gui.InterfaceSize;

/**
 * Land met bijbehorende vragen
 * @author wesley
 */
public class Land {
    
    private Landen land;
    private boolean completed;//uitgespeeld (voltooid) land
    private List<Question> questions;//de vragen die het land heeft
    
    /**
     * De landen met naam, moeilijkheidsgraad en plaatje
     */
    public enum Landen{
        BELGIE("België", 1, new ImageIcon(Main.IMAGES_LOCATION + "belgie.jpg"), null),
        DENEMARKEN("Denemarken", 2, new ImageIcon(Main.IMAGES_LOCATION + "denemarken.jpg"), null),
        DUITSLAND("Duitsland", 3, new ImageIcon(Main.IMAGES_LOCATION + "duitsland.jpg"), null),
        FRANKRIJK("Frankrijk", 4, new ImageIcon(Main.IMAGES_LOCATION + "frankrijk.jpg"), null),
        ITALIE("Italië", 5, new ImageIcon(Main.IMAGES_LOCATION + "IT-Complete.png"), new ImageIcon(Main.IMAGES_LOCATION + "IT-lvl-Complete.png")),
        LUXEMBURG("Luxemburg", 6, new ImageIcon(Main.IMAGES_LOCATION + "luxemburg.jpg"), null),
        NEDERLAND("Nederland", 7, new ImageIcon(Main.IMAGES_LOCATION + "NL-Complete.png"), new ImageIcon(Main.IMAGES_LOCATION + "NL-lvl-Complete.png")),
        NOORWEGEN("Noorwegen", 8, new ImageIcon(Main.IMAGES_LOCATION + "NR-Complete.png"), null),
        OOSTENRIJK("Oostenrijk", 9, new ImageIcon(Main.IMAGES_LOCATION + "oostenrijk.jpg"), null),
        PORTUGAL_DKR("Portugal dkr", 10, new ImageIcon(Main.IMAGES_LOCATION + "portugal_dkr.jpg"), null),
        PORTUGAL_DR("Portugal dr", 11, new ImageIcon(Main.IMAGES_LOCATION + "portugal_dr.jpg"), null),
        SPANJE("Spanje", 12, new ImageIcon(Main.IMAGES_LOCATION + "SP-Complete.png"), new ImageIcon(Main.IMAGES_LOCATION + "SP-lvl-Complete.png")),
        TSJECHIE("Tsjechiē", 13, new ImageIcon(Main.IMAGES_LOCATION + "tsjechie.jpg"), null),
        ZWEDEN("Zweden", 14, new ImageIcon(Main.IMAGES_LOCATION + "zweden.jpg"), null),
        VERENIGD_KONINKRIJK("Verenigd Koninkrijk", 15, new ImageIcon(Main.IMAGES_LOCATION + "GB-Complete"), new ImageIcon(Main.IMAGES_LOCATION + "GB-lvl-Complete.png"));
        
        private String landNaam;
        private int moeilijkheidsgraad;
        private ImageIcon landComplete;
        private ImageIcon landEnded;
        
        /**
         * Constructor
         * @param landNaam              naam van het land
         * @param moeilijkheidsgraad    moeilijkheidsgraad (1=makkelijkst)
         * @param landComplete          plaatje van het land op de map wanneer het land uitgespeeld is
         * @param landEnded             plaatje dat wordt getoond wanneer het land uitgespeeld is
         */
        Landen(String landNaam, int moeilijkheidsgraad, ImageIcon landComplete, ImageIcon landEnded){
            this.landNaam = landNaam;
            this.moeilijkheidsgraad = moeilijkheidsgraad;
            this.landComplete = landComplete;
            this.landEnded = landEnded;
        }
        //Getters
        public String getLandNaam(){
            return landNaam;
        }
        public int getMoeilijkheidsgraad(){
            return moeilijkheidsgraad;
        }
        public ImageIcon getLandComplete(){
            return landComplete;
        }
        public ImageIcon getLandEnded(){
            return landEnded;
        }
    }

    /**
     * Constructor
     * @param land          het land dat dit object representeert
     */
    public Land(Landen land,List<Question> questions){
        this.land = land;
        this.completed = false;
        this.questions=questions;
    }
    
    public Polygon getBounds(){
        return ImageUtil.getCountryBounds(land);
    }
    
    //Getters and setters
    public Landen getLand() {
        return land;
    }

    public void setLand(Landen land) {
        this.land = land;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}