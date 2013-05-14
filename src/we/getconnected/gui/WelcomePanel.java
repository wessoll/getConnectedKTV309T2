package we.getconnected.gui;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author tim, wesley
 */
public class WelcomePanel extends JPanel {
    
    private JLabel txtMessage;
    
    public WelcomePanel(){
       setLayout(null);
       setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
       setBackground(MainPanel.BACKGROUND_COLOR);

       //Maakt een label aan en print de text uit
       txtMessage = new JLabel("", SwingConstants.CENTER);
       txtMessage.setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
       txtMessage.setText("<html> Hallo<BR>"
               + "Welkom op “Smart World”<BR>"
               + "Klik op Mijn Kaart en begin meteen met spelen<BR>"
               + "Lukt het jou om in de toplijst te komen?<BR>"
               + "Succes en veel plezier!<BR>"
               + "<BR>"
               + "Klasse TV </html>");
       txtMessage.setFont(new Font("Rockwell", Font.PLAIN, 20));
       add(txtMessage); 
    }
}
