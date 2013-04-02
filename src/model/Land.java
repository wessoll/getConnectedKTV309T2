package model;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import we.getconnected.Main;

/**
 * Land met bijbehorende vragen
 * @author wesley
 */
public class Land {
    
    private Landen land;
    private boolean completed;//uitgespeeld (voltooid) land
    private ArrayList<Question> questions;//de vragen die het land heeft
    
    /**
     * De landen met naam, moeilijkheidsgraad en plaatje
     */
    public enum Landen{
        BELGIE("België", 1, new ImageIcon(Main.IMAGES_LOCATION + "belgie.jpg")),
        DENEMARKEN("Denemarken", 2, new ImageIcon(Main.IMAGES_LOCATION + "denemarken.jpg")),
        DUITSLAND("Duitsland", 3, new ImageIcon(Main.IMAGES_LOCATION + "duitsland.jpg")),
        FRANKRIJK("Frankrijk", 4, new ImageIcon(Main.IMAGES_LOCATION + "frankrijk.jpg")),
        ITALIE("Italië", 5, new ImageIcon(Main.IMAGES_LOCATION + "italie.jpg")),
        LUXEMBURG("Luxemburg", 6, new ImageIcon(Main.IMAGES_LOCATION + "luxemburg.jpg")),
        NEDERLAND("Nederland", 7, new ImageIcon(Main.IMAGES_LOCATION + "nederland.jpg")),
        NOORWEGEN("Noorwegen", 8, new ImageIcon(Main.IMAGES_LOCATION + "noorwegen.jpg")),
        OOSTENRIJK("Oostenrijk", 9, new ImageIcon(Main.IMAGES_LOCATION + "oostenrijk.jpg")),
        PORTUGAL_DKR("Portugal dkr", 10, new ImageIcon(Main.IMAGES_LOCATION + "portugal_dkr.jpg")),
        PORTUGAL_DR("Portugal dr", 11, new ImageIcon(Main.IMAGES_LOCATION + "portugal_dr.jpg")),
        SPANJE("Spanje", 12, new ImageIcon(Main.IMAGES_LOCATION + "spanje.jpg")),
        TSJECHIE("Tsjechiē", 13, new ImageIcon(Main.IMAGES_LOCATION + "tsjechie.jpg")),
        ZWEDEN("Zweden", 14, new ImageIcon(Main.IMAGES_LOCATION + "zweden.jpg"));
        
        private String landNaam;
        private int moeilijkheidsgraad;
        private ImageIcon image;
        
        /**
         * Constructor
         * @param landNaam              naam van het land
         * @param moeilijkheidsgraad    moeilijkheidsgraad (1=makkelijkst)
         * @param image                 plaatje van het land
         */
        Landen(String landNaam, int moeilijkheidsgraad, ImageIcon image){
            this.landNaam = landNaam;
            this.moeilijkheidsgraad = moeilijkheidsgraad;
            this.image = image;
        }
        //Getters
        public String getLandNaam(){
            return landNaam;
        }
        public int getMoeilijkheidsgraad(){
            return moeilijkheidsgraad;
        }
        public ImageIcon getImage(){
            return image;
        }
    }

    /**
     * Constructor
     * @param land          het land dat dit object representeert
     */
    public Land(Landen land){
        this.land = land;
        completed = false;
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

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}