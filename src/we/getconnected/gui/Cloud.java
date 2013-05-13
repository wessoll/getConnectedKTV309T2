package we.getconnected.gui;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import we.getconnected.Main;

/**
 * Een aparte JLabel voor de cloud pictures
 * @author wesley
 */
public class Cloud extends JLabel {
    
    private String fileName;
    private Point point;
    private Dimension dimension;
    private final String PATH_CLOUD = Main.IMAGES_LOCATION + "clouds/";
    
    /**
     * Constructor
     * @param fileName      naam van het bestand
     * @param point         x en y coordinaten waarop de jlabel moet komen
     * @param dimension     width en height voor de jlabel
     */
    public Cloud(String fileName, Point point, Dimension dimension){
       
        this.fileName = PATH_CLOUD + fileName;
        this.point = point;
        this.dimension = dimension;
        setIcon(new ImageIcon(this.fileName));
        setLocation(point);
        setSize(dimension);
    }

    //Getters en setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}