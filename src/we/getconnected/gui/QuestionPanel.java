package we.getconnected.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import model.Answer;
import model.Country;
import model.Question;
import we.getconnected.Main;

/**
 * De QuestionPanel regelt de panels mapArea(bevat de map met antwoorden) en
 * questionTextPanel(bevat vraag).
 *
 * @author wesley
 * @todo dimension en point objects for hardcoded int values
 * @todo deze class moet eigenlijk worden verdeeld in een methode voor het showen van de mapArea en questionTextPanel
 * @todo mouselisteners vervangen voor een kortere methode?
 */
public class QuestionPanel extends JPanel {

    //lengtes en breedtes
    private static final Dimension FEEDBACK_LARGE = new Dimension(605,484);
    private static final Dimension FEEDBACK_SMALL = new Dimension(113, 91);
    private static final Dimension LEVEL_COMPLETE = new Dimension(757,568);
    private static final Dimension VRAGEN = new Dimension(128,37);
    private static final Dimension VOLGENDE = new Dimension(137,39);
    private static final Dimension VORIGE = new Dimension(137,40);
    private static final Point VRAGEN_C = new Point((MainPanel.BOTTOM_BAR.width-VOLGENDE.width)/2, 100);
    private static final Point VOLGENDE_C = new Point((VRAGEN_C.x+VRAGEN.width), 100);
    private static final Point VORIGE_C = new Point(VRAGEN_C.x-VORIGE.width, 100);
    
    private JLabel lblMap, lblCorrectLarge, lblIncorrectLarge, lblCorrectSmall, lblIncorrectSmall, lblLandComplete;
    private JButton btnNext, btnPrevious;
    private Question currentQuestion;
    private Timer feedbackTimer;
    private JPanel questionTextPanel;
    private Country currentLand;
    private JLabel questionText;

    /**
     * Constructor
     *
     * @param question Vraag die op het scherm getoond moet worden
     * @param land     het land van de vraag
     */
    public QuestionPanel(Question question, Country land) {
        this.currentQuestion = question;
        this.currentLand = land;
        
        //initialize form
        setLayout(null);
        setBackground(MainPanel.BACKGROUND_COLOR);
        setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);

        //plaats de map in de mapArea
        ImageIcon tempMap = new ImageIcon(Main.getImage(currentQuestion.getMapPath()));
        lblMap = new JLabel();
        lblMap.setBounds((MainPanel.MAP_AREA.width-tempMap.getIconWidth())/2, 
                (MainPanel.MAP_AREA.height-tempMap.getIconHeight())/2, 
                tempMap.getIconWidth(), tempMap.getIconHeight());
        lblMap.setIcon(tempMap);
        
        //maak alvast de plaatjes voor de feedback in de mapArea aan
        lblCorrectLarge = new JLabel();
        lblCorrectLarge.setIcon(new ImageIcon(getClass().getResource("/media/correctLarge.png")));
        lblCorrectLarge.setBounds((MainPanel.MAP_AREA.width - FEEDBACK_LARGE.width) / 2, 
                (MainPanel.MAP_AREA.height - FEEDBACK_LARGE.height) / 2, FEEDBACK_LARGE.width, FEEDBACK_LARGE.height);
        lblCorrectLarge.setVisible(false);
        add(lblCorrectLarge);

        lblIncorrectLarge = new JLabel();
        lblIncorrectLarge.setIcon(new ImageIcon(getClass().getResource("/media/incorrectLarge.png")));
        lblIncorrectLarge.setBounds((MainPanel.MAP_AREA.width - FEEDBACK_LARGE.width) / 2, 
                (MainPanel.MAP_AREA.height - FEEDBACK_LARGE.height) / 2, FEEDBACK_LARGE.width, FEEDBACK_LARGE.height);
        lblIncorrectLarge.setVisible(false);
        add(lblIncorrectLarge);
        
        //maak de label voor het uitspelen van land aan
        lblLandComplete = new JLabel();
        lblLandComplete.setIcon(currentLand.getLand().getLandEnded());
        lblLandComplete.setBounds((MainPanel.MAP_AREA.width-LEVEL_COMPLETE.width)/2, 
                (MainPanel.MAP_AREA.height-LEVEL_COMPLETE.height)/2, LEVEL_COMPLETE.width, LEVEL_COMPLETE.height);
        lblLandComplete.setVisible(false);
        add(lblLandComplete);

        //maak een timer die na een halve seconde de feedback weer van de mapArea haalt
        //en die de feedback ook in het questionTextPanel zet
        feedbackTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //haal de feedback van het scherm
                if (lblIncorrectLarge.isVisible()) {
                    lblIncorrectLarge.setVisible(false);
                }
                if (lblCorrectLarge.isVisible()) {
                    lblCorrectLarge.setVisible(false);
                }
                //update de feedback in de questionTextPanel
                if (currentQuestion.isCorrect()) {
                    lblIncorrectSmall.setVisible(false);
                    lblCorrectSmall.setVisible(true);
                } else {
                    lblIncorrectSmall.setVisible(true);
                }
                //timer eenmalig uitvoeren, dus stop weer
                feedbackTimer.stop();
            }
        });

        //Set de punten van de antwoorden op de kaart
        ArrayList<Answer> answers = currentQuestion.getAnswers();
        for (final Answer answer : answers) {
            JLabel point = new JLabel(answer.getAnswer());
            point.setSize(AddQuestion.DEFAULT_BUTTON);
            point.setFont(AddQuestion.FONT_TYPE30);
            //voeg aan elk antwoord een mouselistener toe die het juiste of niet juiste antwoord afhandeld
            point.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //kijkt of de vraag al eerder goed beantwoord is of dat de vraag een lock heeft
                    //om vervolgens de input te blokkeren
                    if (currentQuestion.isCorrect() == false && currentQuestion.getAvailable().before(Main.getQueryManager().getDate())) {
                        
                        //log het aantal pogingen en kijk of het juiste antwoord is geklikt
                        if (!answer.isCorrect()) {
                            currentQuestion.setTries(currentQuestion.getTries() + 1);
                            //lock de vraag als er meer dan 2 foute pogingen gedaan zijn
                            if (currentQuestion.getTries() >= 2){
                                //lock de vraag voor 1 uur
                                currentQuestion.setAvailable(new Timestamp(Main.getQueryManager().getDate().getTime() + 3600000));
                                //toon dat de vraag gelocked is
                                setQuestionTextLocked();
                            }
                            //toon feedback op scherm
                            lblIncorrectLarge.setVisible(true);
                            feedbackTimer.start();
                        } else {
                            currentQuestion.setCorrect(true);
                            //for loop controleren of alle vragen juist zijn
                            int questionCount = 0;
                            int questionComplete = 0;

                            //Deze for-loop kijkt hoeveel vragen er zijn
                            for (Question x : currentLand.getQuestions()) {
                                questionCount++;
                            }

                            // Voor elke vraag die goed is beantwoord word er 1 opgeteld bij questionComplete
                            // Is er een foute vraag dan word de teller weer op 0 gezet.
                            for (Question allQuestions : currentLand.getQuestions()) {
                                if (allQuestions.isCorrect() == true) {
                                    questionComplete++;
                                } else {
                                    questionComplete = 0;
                                }
                            }
                            //Hier word gekeken of de goed beantwoorde vragen gelijk zijn aan het aantal vragen. Want dat betekend dat alle vragen goed zijn beantwoord
                            if (questionCount == questionComplete) {
                                currentLand.setCompleted(true);
                                //update database
                                System.out.println("updateing database");
                                Main.getQueryManager().updateUserCountry(currentLand, Main.getCurrentUser().getUser_id());
                                   
                                //toon feedback op scherm
                                lblLandComplete.setVisible(true);
                                lblCorrectSmall.setVisible(true);

                            } else {
                                //toon feedback op scherm
                                lblCorrectLarge.setVisible(true);
                                feedbackTimer.start();
                            }
                        }
                            //update in database
                            Main.getQueryManager().updateUserQuestion(currentQuestion, Main.getCurrentUser().getUser_id());
                    }
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

            point.setLocation((int) answer.getLocation().getX(), (int) answer.getLocation().getY());
            add(point);
        }
        add(lblMap);

        //maak de questionTextPanel met de vraag en plaats deze in de bottombar
        questionTextPanel = new JPanel();
        questionTextPanel.setLayout(null);
        questionTextPanel.setBackground(MainPanel.BACKGROUND_COLOR);
        questionTextPanel.setBounds(0, 0, MainPanel.BOTTOM_BAR.width, MainPanel.BOTTOM_BAR.height);
        //zet de vraag op het scherm of een melding dat de vraag locked is
        questionText = new JLabel("", SwingConstants.CENTER);
        if (!currentQuestion.getAvailable().before(Main.getQueryManager().getDate())){
            setQuestionTextLocked();
        }
        else{
            questionText.setText(question.getQuestion());
        }
        questionText.setFont(new Font("Rockwell",Font.PLAIN,20));
        questionText.setBounds(0, 0, MainPanel.BOTTOM_BAR.width, 50);
        questionTextPanel.add(questionText);
        
        //maak 3 buttons aan voor het navigeren naar questionselect, vorige en volgende vraag
        JButton btnQuestionSelect = new JButton();
        btnQuestionSelect.setIcon(new ImageIcon(Main.IMAGES_LOCATION + "Vragen.png"));
        btnQuestionSelect.setBorderPainted(false);
        btnQuestionSelect.setBounds(VRAGEN_C.x, VRAGEN_C.y, VRAGEN.width, VRAGEN.height);
        btnQuestionSelect.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.getMainPanel().clearPanelBottomBar();
                Main.getMainPanel().showPanelMapArea(new QuestionSelection(currentLand));
            }
        });
        btnNext = new JButton();
        btnNext.setIcon(new ImageIcon(Main.IMAGES_LOCATION + "Volgende.png"));
        btnNext.setBorderPainted(false);
        btnNext.setBounds(VOLGENDE_C.x, VOLGENDE_C.y, VOLGENDE.width, VOLGENDE.height);
        btnNext.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateButtonsEnabled();
                for (int i=0;i<currentLand.getQuestions().size();i++){
                    if (currentLand.getQuestions().get(i) == currentQuestion && i+1<currentLand.getQuestions().size()){
                        Main.getMainPanel().showPanelMapArea(new QuestionPanel(currentLand.getQuestions().get(i+1), currentLand));
                    }
                }
            }
        });
        btnPrevious = new JButton();
        btnPrevious.setIcon(new ImageIcon(Main.IMAGES_LOCATION + "Vorige.png"));
        btnPrevious.setBorderPainted(false);
        btnPrevious.setBounds(VORIGE_C.x, VORIGE_C.y, VORIGE.width, VORIGE.height);
        btnPrevious.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateButtonsEnabled();
                for (int i=0;i<currentLand.getQuestions().size();i++){
                    if (currentLand.getQuestions().get(i) == currentQuestion && i>0){
                        Main.getMainPanel().showPanelMapArea(new QuestionPanel(currentLand.getQuestions().get(i-1), currentLand));
                    }
                }
            }
        });
        
        questionTextPanel.add(btnQuestionSelect);
        questionTextPanel.add(btnNext);
        questionTextPanel.add(btnPrevious);
        
        //maak alvast de plaatjes voor de feedback in de questionTextPanel aan
        lblCorrectSmall = new JLabel();
        lblCorrectSmall.setIcon(new ImageIcon(getClass().getResource("/media/correctSmall.png")));
        lblCorrectSmall.setBounds(MainPanel.BOTTOM_BAR.width - FEEDBACK_SMALL.width, 0, FEEDBACK_SMALL.width, FEEDBACK_SMALL.height);
        lblCorrectSmall.setVisible(false);
        questionTextPanel.add(lblCorrectSmall);

        lblIncorrectSmall = new JLabel();
        lblIncorrectSmall.setIcon(new ImageIcon(getClass().getResource("/media/incorrectSmall.png")));
        lblIncorrectSmall.setBounds(MainPanel.BOTTOM_BAR.width - FEEDBACK_SMALL.width, 0, FEEDBACK_SMALL.width, FEEDBACK_SMALL.height);
        lblIncorrectSmall.setVisible(false);
        questionTextPanel.add(lblIncorrectSmall);

        Main.getMainPanel().showPanelBottomBar(questionTextPanel);

        //laat bij openen van vraag het feebback plaatje in de 
        //questionTextPanel zien als de vraag al geprobeerd of correct is
        if (currentQuestion.isCorrect()) {
            lblCorrectSmall.setVisible(true);
        } else if (currentQuestion.getTries() > 0) {
            lblIncorrectSmall.setVisible(true);
        }
        updateButtonsEnabled();
    }
    
    private void setQuestionTextLocked(){
        //maak een formaat aan hoe de tijd gepresenteerd moet worden
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        //converteer de tijd uit de database naar de nederlandse tijd (alleen voor visuele weergave)
        String date = formatter.format(new Date(currentQuestion.getAvailable().getTime() + Main.EXTRA_TIMEZONE_HOURS));

        //zet de melding op het scherm in de vraag text
        questionText.setText("<html><center>Je hebt deze vraag 2 of meer keer fout beantwoord. <br>Je kunt het opnieuw proberen om: <b>" + date + "</b>.</center></html>");
    }
    
    public void updateButtonsEnabled(){
        //blokkeer de previous knop als we op de eerste vraag zijn
        if (currentQuestion == currentLand.getQuestions().get(0)){
            btnPrevious.setEnabled(false);
        }
        else{
            btnPrevious.setEnabled(true);
        }
        //blokkeer de next knop als we op de laatste vraag zijn
        if (currentQuestion == currentLand.getQuestions().get(currentLand.getQuestions().size()-1)){
            btnNext.setEnabled(false);
        }
        else{
            btnNext.setEnabled(true);
        }
    }
}