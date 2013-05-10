package model;

import java.awt.Polygon;
import java.util.List;
import javax.swing.ImageIcon;
import we.getconnected.Main;

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
        BELGIE("België", 1, new ImageIcon(Main.IMAGES_LOCATION + "belgie.jpg"), null,null),
        DENEMARKEN("Denemarken", 2, new ImageIcon(Main.IMAGES_LOCATION + "denemarken.jpg"), null,null),
        DUITSLAND("Duitsland", 3, new ImageIcon(Main.IMAGES_LOCATION + "duitsland.jpg"), null,null),
        FRANKRIJK("Frankrijk", 4, new ImageIcon(Main.IMAGES_LOCATION + "frankrijk.jpg"), null,null),
        ITALIE("Italië", 5, new ImageIcon(Main.IMAGES_LOCATION + "IT-Complete.png"), new ImageIcon(Main.IMAGES_LOCATION + "IT-lvl-Complete.png"),new Polygon(new int[]{268,283,293,301,269,265,279,307,334,370,348,314,330,326,303,269,268}, new int[]{440,435,450,468,483,512,508,536,552,506,479,433,420,408,400,406,439},17)),
        LUXEMBURG("Luxemburg", 6, new ImageIcon(Main.IMAGES_LOCATION + "luxemburg.jpg"), null,null),
        NEDERLAND("Nederland", 7, new ImageIcon(Main.IMAGES_LOCATION + "NL-Complete.png"), new ImageIcon(Main.IMAGES_LOCATION + "NL-lvl-Complete.png"),new Polygon(new int[]{271,274,280,286,290,277,264,265,259,257,264},new int[]{336,324,317,305,296,296,302,308,315,319,326},11)),
        NOORWEGEN("Noorwegen", 8, new ImageIcon(Main.IMAGES_LOCATION + "NR-Complete.png"), null, new Polygon(new int[]{302,330,340,347,381,398,403,417,422,431,441,419,353,357,328,296,291}, new int[]{234,224,205,152,91,80,84,82,68,66,61,47,98,113,154,183,22},17)),
        OOSTENRIJK("Oostenrijk", 9, new ImageIcon(Main.IMAGES_LOCATION + "oostenrijk.jpg"), null,null),
        PORTUGAL_DKR("Portugal dkr", 10, new ImageIcon(Main.IMAGES_LOCATION + "portugal_dkr.jpg"), null,null),
        PORTUGAL_DR("Portugal dr", 11, new ImageIcon(Main.IMAGES_LOCATION + "portugal_dr.jpg"), null,null),
        SPANJE("Spanje", 12, new ImageIcon(Main.IMAGES_LOCATION + "SP-Complete.png"), new ImageIcon(Main.IMAGES_LOCATION + "SP-lvl-Complete.png"),new Polygon(new int[]{182,187,222,229,195,146,113,102,131,112,110,121}, new int[]{424,434,452,491,499,520,512,490,428,419,398,397},12)),
        TSJECHIE("Tsjechiē", 13, new ImageIcon(Main.IMAGES_LOCATION + "tsjechie.jpg"), null,null),
        ZWEDEN("Zweden", 14, new ImageIcon(Main.IMAGES_LOCATION + "zweden.jpg"), null,null),
        VERENIGD_KONINKRIJK("Verenigd Koninkrijk", 15, new ImageIcon(Main.IMAGES_LOCATION + "GB-Complete"), new ImageIcon(Main.IMAGES_LOCATION + "GB-lvl-Complete.png"),new Polygon(new int[]{233,176,190,177,188,207,258,235,220,242}, new int[]{323,315,261,251,241,201,182,230,243,308},10));
        
        private String landNaam;
        private int moeilijkheidsgraad;
        private ImageIcon landComplete;
        private ImageIcon landEnded;
        private Polygon landBounds;

        /**
         * Constructor
         * @param landNaam              naam van het land
         * @param moeilijkheidsgraad    moeilijkheidsgraad (1=makkelijkst)
         * @param landComplete          plaatje van het land op de map wanneer het land uitgespeeld is
         * @param landEnded             plaatje dat wordt getoond wanneer het land uitgespeeld is
         * @param landBounds            de borders van het land in een veelhoek
         */
        Landen(String landNaam, int moeilijkheidsgraad, ImageIcon landComplete, ImageIcon landEnded, Polygon landBounds){
            this.landNaam = landNaam;
            this.moeilijkheidsgraad = moeilijkheidsgraad;
            this.landComplete = landComplete;
            this.landEnded = landEnded;
            this.landBounds = landBounds;
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
        public Polygon getLandBounds(){
            return landBounds;
        }
        
        public static Landen fromString(String name){
            for(Landen currentLand:values()){
                if(currentLand.getLandNaam().equalsIgnoreCase(name)){
                    return currentLand;
                }
            }
            return null;
        }
    }

    /**
     * Constructor
     * @param land          het land dat dit object representeert
     */
    public Land(Landen land,List<Question> questions, boolean completed){
        this.land = land;
        this.completed = completed;
        this.questions=questions;
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