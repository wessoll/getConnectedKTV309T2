/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.Answer;
import model.Country;
import model.Country.Countries;
import model.Question;
import model.User;
import we.getconnected.Main;
import static we.getconnected.gui.MainPanel.BOTTOM_BAR;

/**
 *
 * @author timvonsee -----TODO "ITOPIA CODING STANDARDS"-----
 * @todo1: variabelen moeten met een lowercase letter beginnen
 * @todo2: regels niet langer dan 100 (even enter bij jlabel class variables)
 * @todo3: i.p.v. losse getallen moet je (bijv.) constanten gebruiken. Een Point object kan je
 * gebruiken om de x en y positie aan te geven en een Dimension object kan je gebruiken om de width
 * en height aan te geven -----TODO nu we toch bezig zijn nog wat tips :P-----
 * @todo4: imageABT niet vergeten als private te declareren
 * @todo5: die axPos variabelen etc. mag je net als de jlabels achter elkaar zetten
 * @todo6: html tags sluiten af met "</html>" DONE
 * @todo7: je kan ook een constant voor het Rockwell font aanmaken (die kan je dan opgeven bij de
 * labels zonder telkens de hele nieuwe font op te geven
 * @todo8: de mouse listener voor de url test button kun je vervangen voor een kortere
 * (ActionListener) zie Login als voorbeeld -----TODO waarom het niet werkte-----
 * @todo9: vanuit MainPanel wanneer er op optiesButton gepressed wordt doe je
 * "showPanelBottomBar(new AddQuestion());" dat zou betekenen dat je onderaan niet nog een keer
 * "Main.getMainPanel().showPanelBottomBar(bottomBar);" moet doen en dat zou ook betekenen dat je
 * bottomBar uit deze class mag verwijderen (deze hele class wordt dus al aan de bottomBar
 * toegevoegd) alles waar je nu voor zegt "bottomBar.add()" mag je het bottomBar gedeelte weglaten
 * en alleen de add(...) laten staan
 * @todo10: zie code
 *
 */
public class AddQuestion extends JPanel {

    private ImageIcon imageABT = new ImageIcon("/media/ABT.png");
    //private ImageIcon toevoegen = new ImageIcon("/media/ToevoegenBT.png");
    private JTextField questionField, URLField;
    private JLabel txtQuestionRequest,
            txtURLRequest,
            imgPreview,
            txtCorrectRequest,
            txtCountryRequest,
            txtSelectQuestion,
            aButton,
            bButton,
            cButton,
            dButton,
            aFeedback,
            bFeedback,
            cFeedback,
            dFeedback,
            aKnop,
            toevoegButton;
    private JPanel bottomBar, canvas;
    private JButton testURLBT, upload;
    private JComboBox answerBox, countryBox;
    private String[] correctAnswer = {"A", "B", "C", "D"}; // Keuzes voor answerBox 
    private int selectedLetter = 0; // 0 = niks geslecteerd, 1 = A, 2 = B, 3 = C, 4 = D 
    private double xDouble;
    private double yDouble;
    private int xInt;
    private int yInt;
    private double axPos, ayPos, bxPos, byPos, cxPos, cyPos, dxPos, dyPos;
    private Loader loader;
    
    private Font fontType_20 = new Font("Rockwell", Font.PLAIN, 20);    
    public static final Font FONT_TYPE30 = new Font("Rockwell", Font.BOLD, 30);
    
    
    public static final int MARGIN = 10;
    // Input field H&W
    public static final int DEFAULT_HEIGHT = 23;
    public static final int DEFAULT_WIDTH = 400;
    
    // Input fields
    public static final Dimension INPUT_DIME = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);      
    public static final Point QI_PT = new Point(MARGIN, 25); //questionField
    
    //txtQuestoinRequest 
    public static final Dimension QR_DIME = new Dimension(200, DEFAULT_HEIGHT);
    public static final Point QR_PT = new Point(MARGIN, 1); 
    
    // txtURLRequest
    public static final Point UR_PT = new Point(MARGIN, 50); 
    public static final Dimension UR_DIME = new Dimension(360, DEFAULT_HEIGHT); 
    // URLField
    public static final Point UI_PT = new Point(MARGIN, 74);
    //testURLBT
    public static final Dimension UB_DIME = new Dimension(80, DEFAULT_HEIGHT); 
    public static final Point UB_PT = new Point(405, 74);
    //txtCorrectRequest
    public static final Point CR_PT = new Point(MARGIN, 103);
    public static final Dimension CR_DIME = new Dimension(400, DEFAULT_HEIGHT); 
    //answerBox
    public static final Point AB_PT = new Point(220, 97);
    public static final Dimension AB_DIME = new Dimension(60, 35);
    //countryBox
    public static final Point CB_PT = new Point(180, 132);
    public static final Dimension CB_DIME = new Dimension(120, DEFAULT_HEIGHT);
    //countryBox
    public static final Point COUNTRYR_PT = new Point(MARGIN, 138);
    public static final Dimension COUNTRYR_DIME = new Dimension(400, DEFAULT_HEIGHT);
    
    // Default dimension voor vraag opties
    public static final Dimension DEFAULT_BUTTON = new Dimension(30, 30);
    
    
    //aButton, bButton, cButton, DButton
    public static final Point ABUTTON_PT = new Point(500, 50);
    public static final Point BBUTTON_PT = new Point(540, 50);
    public static final Point CBUTTON_PT = new Point(580, 50);
    public static final Point DBUTTON_PT = new Point(620, 50);
    
    //selectQuestion
    public static final Point SR_PT = new Point(500, 10);
    public static final Dimension SR_DIME = new Dimension(600, 60);
    
    // aFeedback
    public static final Point FEEDBACK = new Point(0, 0);
    
    
    
    
    public AddQuestion() {


        setLayout(null);
        setBackground(MainPanel.BACKGROUND_COLOR);
        //@todo10: je geeft aan dat de bottombar net zo groot als de mapArea moet zijn
        //dit moet dus bottomBar.width en height zijn
        setBounds(0, 0, BOTTOM_BAR.width, BOTTOM_BAR.height);

        //canvas
        canvas = new JPanel();
        canvas.setLayout(null);
        canvas.setBackground(MainPanel.BACKGROUND_COLOR);
        canvas.setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);

        // De JLabel vraag om input in te voeren voor de vraag
        txtQuestionRequest = new JLabel();
        txtQuestionRequest.setBounds(QR_PT.x, QR_PT.y, QR_DIME.width, QR_DIME.height);
        txtQuestionRequest.setText("Vul hier uw vraag in: ");
        txtQuestionRequest.setFont(fontType_20);
        add(txtQuestionRequest);

        // Input gedeelte voor de vraag
        questionField = new JTextField();
        questionField.setBounds(QI_PT.x, QI_PT.y, INPUT_DIME.width, INPUT_DIME.height);
        add(questionField);

        // De vraag om input in  te voeren voor de URL
        txtURLRequest = new JLabel();
        txtURLRequest.setBounds(UR_PT.x, UR_PT.y, UR_DIME.width, UR_DIME.height);
        txtURLRequest.setText("Vul hier de link naar de afbeelding in:");
        txtURLRequest.setFont(fontType_20);
        add(txtURLRequest);

        // Input gedeelte voor URL
        URLField = new JTextField();
        URLField.setBounds(UI_PT.x, UI_PT.y, INPUT_DIME.width, INPUT_DIME.height);
        add(URLField);

        // Display URL knop
        testURLBT = new JButton();
        testURLBT.setBounds(UB_PT.x, UB_PT.y, UB_DIME.width, UB_DIME.height);
        testURLBT.setText("test URL");
        add(testURLBT);
        
        // Upload knop
        upload = new JButton();
        upload.setBounds( 700, 50, 80, 40);
        upload.setText("Toevoegen");
        add(upload);

        // Hier komt wordt het plaatje getoond
        imgPreview = new JLabel("Er is nog geen afbeelding...", SwingConstants.CENTER);
        imgPreview.setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
        imgPreview.setFont(fontType_20);
  

        // De vraag om een juist antwoord te kiezen
        txtCorrectRequest = new JLabel();
        txtCorrectRequest.setBounds(CR_PT.x, CR_PT.y, CR_DIME.width, CR_DIME.height);
        txtCorrectRequest.setText("Het goede antwoord is:");
        txtCorrectRequest.setFont(fontType_20);
        add(txtCorrectRequest);

        //Combobox voor het goede antwoord        
        answerBox = new JComboBox(correctAnswer);
        answerBox.setBounds(AB_PT.x, AB_PT.y, AB_DIME.width, AB_DIME.height);
        answerBox.setSelectedIndex(0);
        add(answerBox);

        //Combobox voor het land
        countryBox = new JComboBox(Country.Countries.values());
        countryBox.setBounds(CB_PT.x, CB_PT.y, CB_DIME.width, CB_DIME.height);
        countryBox.setSelectedIndex(0);
        add(countryBox);

        // vraag om type land
        txtCountryRequest = new JLabel();
        txtCountryRequest.setBounds(COUNTRYR_PT.x, COUNTRYR_PT.y, COUNTRYR_DIME.width, COUNTRYR_DIME.height);
        txtCountryRequest.setText("Geef het land aan:");
        txtCountryRequest.setFont(fontType_20);
        add(txtCountryRequest);

        // Vraag opties
        aButton = new JLabel();
        aButton.setBounds(ABUTTON_PT.x, ABUTTON_PT.y, DEFAULT_BUTTON.width, DEFAULT_BUTTON.height);
        aButton.setText("A");
        aButton.setFont(FONT_TYPE30);
        add(aButton);

        bButton = new JLabel();
        bButton.setBounds(BBUTTON_PT.x, BBUTTON_PT.y, DEFAULT_BUTTON.width, DEFAULT_BUTTON.height);
        bButton.setText("B");
        bButton.setFont(FONT_TYPE30);
        add(bButton);

        cButton = new JLabel();
        cButton.setBounds(CBUTTON_PT.x, CBUTTON_PT.y, DEFAULT_BUTTON.width, DEFAULT_BUTTON.height);
        cButton.setText("C");
        cButton.setFont(FONT_TYPE30);
        add(cButton);

        dButton = new JLabel();
        dButton.setBounds(DBUTTON_PT.x, DBUTTON_PT.y, DEFAULT_BUTTON.width, DEFAULT_BUTTON.height);
        dButton.setText("D");
        dButton.setFont(FONT_TYPE30);
        add(dButton);

        txtSelectQuestion = new JLabel();
        txtSelectQuestion.setBounds(SR_PT.x, SR_PT.y, SR_DIME.width, SR_DIME.height);
        txtSelectQuestion.setText("Klik op een letter:");
        txtSelectQuestion.setFont(fontType_20);
        add(txtSelectQuestion);
        
        toevoegButton = new JLabel();
        toevoegButton.setBounds(700, 50, 158, 61);
        toevoegButton.setIcon(new ImageIcon("/media/ToevoegenBT.png"));
        toevoegButton.setVisible(true);
        add(toevoegButton);
        

        // maakt als vast de knoppen voor op het scherm klaar
        aFeedback = new JLabel();
        aFeedback.setSize(DEFAULT_BUTTON);
        aFeedback.setText("A");
        aFeedback.setFont(FONT_TYPE30);
        aFeedback.setVisible(false);
        canvas.add(aFeedback);

        bFeedback = new JLabel();
        bFeedback.setSize(DEFAULT_BUTTON);
        bFeedback.setText("B");
        bFeedback.setFont(FONT_TYPE30);
        bFeedback.setVisible(false);
        canvas.add(bFeedback);

        cFeedback = new JLabel();
        cFeedback.setSize(DEFAULT_BUTTON);
        cFeedback.setText("C");
        cFeedback.setFont(FONT_TYPE30);
        cFeedback.setVisible(false);
        canvas.add(cFeedback);

        dFeedback = new JLabel();
        dFeedback.setSize(DEFAULT_BUTTON);
        dFeedback.setText("D");
        dFeedback.setFont(FONT_TYPE30);
        dFeedback.setVisible(false);
        canvas.add(dFeedback);
        
        canvas.add(imgPreview);
        
        testURLBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //handel het ophalen van de url af
            loader = new Loader("Afbeelding inladen...");
            loader.setVisible(true);
            //maak een thread aan die de afbeelding ophaalt en resized
            Thread loadUrl = new Thread(){
                @Override
                public void run() {
                imgPreview.setText(""); // Haalt de text uit de label

                ImageIcon rawImg = new ImageIcon(Main.getImage(URLField.getText())); // Hier word de Img opgehaald en in een icon gezet

                if (rawImg.getIconHeight() > rawImg.getIconWidth()) {  // Kijkt of de image Portait is                 
                    if (rawImg.getIconHeight() >= MainPanel.MAP_AREA.height) {   // kijkt naar de hoogte van de image                       
                        ImageIcon importImg = new ImageIcon(
                                Main.getImage(URLField.getText()).getScaledInstance(-1,
                                MainPanel.MAP_AREA.height,
                                Image.SCALE_FAST)); // Haalt de Image binnen van de URL en resized het. De -1 is om de aspect ratio te behouden

                        imgPreview.setIcon(importImg);
                        imgPreview.setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
                    }
                } else if (rawImg.getIconWidth() > rawImg.getIconHeight()) { // Kijkt of de image Landscap is                   
                    if (rawImg.getIconWidth() >= MainPanel.MAP_AREA.width) {     //kijkt naar de breedte van de image
                        ImageIcon importImg = new ImageIcon(
                                Main.getImage(URLField.getText()).getScaledInstance(MainPanel.MAP_AREA.width,
                                -1, Image.SCALE_FAST));

                        imgPreview.setIcon(importImg);
                        imgPreview.setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
                    }
                }

                if (rawImg.getIconHeight() <= MainPanel.MAP_AREA.height && rawImg.getIconWidth() <= MainPanel.MAP_AREA.width) {
                    System.out.println("prefect");
                    ImageIcon importImg = new ImageIcon(Main.getImage(URLField.getText()));
                    imgPreview.setIcon(importImg);
                    imgPreview.setBounds((MainPanel.MAP_AREA.width - importImg.getIconWidth()) / 2,
                            (MainPanel.MAP_AREA.height - importImg.getIconHeight()) / 2,
                            importImg.getIconWidth(), importImg.getIconHeight());
                }
                        loader.setVisible(false);
                }
            };
            loadUrl.start();
                    }
        });
    
                

            
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //bepaal welk antwoord als correct is gezet in de combobox
                byte a = 0, b = 0, c = 0, d = 0;
                if(answerBox.getSelectedItem().toString().equals("A")){
                    a = 1;
                }
                if(answerBox.getSelectedItem().toString().equals("B")){
                    b = 1;
                }
                if(answerBox.getSelectedItem().toString().equals("C")){
                    c = 1;
                }
                if(answerBox.getSelectedItem().toString().equals("D")){
                    d = 1;
                }
                   
                //bepaal alvast wat de nieuwe id van de vraag wordt
                int question_id = Main.getQueryManager().getLastQuestionId()+1;
                //maak de answers aan
                ArrayList<Answer> answers = new ArrayList<Answer>();
                answers.add(new Answer((int)axPos, (int)ayPos, a ,"A", question_id));
                answers.add(new Answer((int)bxPos, (int)byPos, b ,"B", question_id));
                answers.add(new Answer((int)cxPos, (int)cyPos, c ,"C", question_id));
                answers.add(new Answer((int)dxPos, (int)dyPos, d ,"D", question_id));
                
                //maak de daadwerkelijke vraag met de answers aan
                //bepaal eerst a.d.h.v. de combobox aan welk land de vraag gekoppeld moet worden
                int country_id=-1;
                for (int i=0; i<Country.Countries.values().length;i++){
                    if (Country.Countries.values()[i] == countryBox.getSelectedItem()){
                        country_id = Main.getQueryManager().getCountry_id(
                                Country.Countries.values()[i].getLandNaam());
                    }
                }
                //maak nu de vraag aan
                Question question = new Question(question_id
                        , questionField.getText(), URLField.getText(), answers, country_id);
                
                //update de database
                //voeg de gegevens voor een nieuwe vraag toe
                Main.getQueryManager().insertQuestion(question);
                //voeg de antwoorden toe aan de database
                for (Answer answer : answers){
                    Main.getQueryManager().insertAnswer(answer);
                }
                //update de user_question tabel zodat elke user over de nieuwe vraag bezit
                ArrayList<User> users = Main.getQueryManager().getUsers();
                for (User user : users){
                    Main.getQueryManager().insertUserQuestion(question, user.getUser_id());
                }
            }
        });
        
        aButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedLetter = 1;

                aButton.setForeground(Color.RED);
                bButton.setForeground(Color.BLACK);
                cButton.setForeground(Color.BLACK);
                dButton.setForeground(Color.BLACK);

                txtSelectQuestion.setText("<html> Klik nu op de kaart<BR>"
                        + "om daar de letter te plaatsen </html>");
                txtSelectQuestion.setBounds(500, 0, 600, 60);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //not supported
            }
        });

        bButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedLetter = 2;

                aButton.setForeground(Color.BLACK);
                bButton.setForeground(Color.RED);
                cButton.setForeground(Color.BLACK);
                dButton.setForeground(Color.BLACK);

                txtSelectQuestion.setText("<html> Klik nu op de kaart<BR>"
                        + "om daar de letter te plaatsen </html>");
                txtSelectQuestion.setBounds(500, 0, 600, 60);

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //not supported
            }
        });

        cButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedLetter = 3;

                aButton.setForeground(Color.BLACK);
                bButton.setForeground(Color.BLACK);
                cButton.setForeground(Color.RED);
                dButton.setForeground(Color.BLACK);

                txtSelectQuestion.setText("<html> Klik nu op de kaart<BR>"
                        + "om daar de letter te plaatsen </html>");
                txtSelectQuestion.setBounds(500, 0, 600, 60);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //not supported
            }
        });

        dButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedLetter = 4;

                aButton.setForeground(Color.BLACK);
                bButton.setForeground(Color.BLACK);
                cButton.setForeground(Color.BLACK);
                dButton.setForeground(Color.RED);

                txtSelectQuestion.setText("<html> Klik nu op de kaart<BR>"
                        + "om daar de letter te plaatsen </html>");
                txtSelectQuestion.setBounds(500, 0, 600, 60);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //not supported
            }
        });

        canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println("Height,Width:   " + imgPreview.getIcon().getIconHeight() + "," + imgPreview.getIcon().getIconWidth());

                xDouble = canvas.getMousePosition().getX();
                yDouble = canvas.getMousePosition().getY();

                // Dit zet de xDouble en yDouble om naar int zodat ze mee gegeven kunnen worden aan .setBorder()l
                xInt = (int) xDouble;
                yInt = (int) yDouble;
                
                //trek bij de x en y position de helft van de DEFAULT_BUTTON er af op
                //zodat bij een klik op de canvas het punt midden van de muisklik komt te staan
                xInt -= (DEFAULT_BUTTON.width/2);
                yInt -= (DEFAULT_BUTTON.height/2);
                switch (selectedLetter) {
                    case 0:
                        System.out.println("Selecteer eerst een letter");
                        break;
                    case 1:// 1 == A, dus A is geslecteerd
                        axPos = xInt;
                        ayPos = yInt;

                        aFeedback.setBounds(xInt, yInt, aButton.getWidth(), aButton.getHeight());
                        aFeedback.setVisible(true);
                        break;
                    case 2:
                        bxPos = xInt; // 2 == B, dus B is geslecteerd
                        byPos = yInt;
                        bFeedback.setBounds(xInt, yInt, aButton.getWidth(), aButton.getHeight());
                        bFeedback.setVisible(true);

                        break;
                    case 3:
                        cxPos = xInt; // 3 == C, dus C is geslecteerd
                        cyPos = yInt;

                        cFeedback.setBounds(xInt, yInt, aButton.getWidth(), aButton.getHeight());
                        cFeedback.setVisible(true);
                        break;
                    case 4:
                        dxPos = xInt; // 4 == D, dus D is geslecteerd
                        dyPos = yInt;

                        dFeedback.setBounds(xInt, yInt, aButton.getWidth(), aButton.getHeight());
                        dFeedback.setVisible(true);
                        break;
                }

                System.out.println("LETTER X & Y:");
                System.out.println("A " + axPos + "," + ayPos);
                System.out.println("B " + bxPos + "," + byPos);
                System.out.println("C " + cxPos + "," + cyPos);
                System.out.println("D " + dxPos + "," + dyPos);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //not supported
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //not supported
            }
        });

        //Main.getMainPanel().showPanelBottomBar(bottomBar);
        Main.getMainPanel().showPanelMapArea(canvas);


    }
}
