package we.getconnected.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import model.Answer;
import model.Land;
import model.Question;
import we.getconnected.Main;

/**
 * De QuestionPanel regelt de panels mapArea(bevat de map met antwoorden) en
 * questionTextPanel(bevat vraag).
 *
 * @author wesley
 * @todo dimension en point objects for hardcoded int values
 */
public class QuestionPanel extends JPanel {

    private JLabel lblMap, lblCorrectLarge, lblIncorrectLarge, lblCorrectSmall, lblIncorrectSmall, lblLandComplete;
    private Question currentQuestion;
    private Timer feedbackTimer;
    private JPanel questionTextPanel;
    private Land currentLand;

    /**
     * Constructor
     *
     * @param question Vraag die op het scherm getoond moet worden
     * @param land     het land van de vraag
     */
    public QuestionPanel(Question question, Land land) {
        this.currentQuestion = question;
        this.currentLand = land;
        //initialize form
        setLayout(null);
        setBackground(Color.ORANGE);
        setBounds(0, 0, MainPanel.MAP_AREA_WIDTH, MainPanel.MAP_AREA_HEIGHT);

        //plaats de map in de mapArea
        lblMap = new JLabel();
        lblMap.setBounds(0, WIDTH, 818, 582);//HARDCODED
        lblMap.setIcon(currentQuestion.getMap());

        //maak alvast de plaatjes voor de feedback in de mapArea aan
        lblCorrectLarge = new JLabel();
        lblCorrectLarge.setIcon(new ImageIcon(getClass().getResource("/media/correctLarge.png")));
        lblCorrectLarge.setBounds((MainPanel.MAP_AREA_WIDTH - 605) / 2, (MainPanel.MAP_AREA_HEIGHT - 484) / 2, 605, 484);
        lblCorrectLarge.setVisible(false);
        add(lblCorrectLarge);

        lblIncorrectLarge = new JLabel();
        lblIncorrectLarge.setIcon(new ImageIcon(getClass().getResource("/media/incorrectLarge.png")));
        lblIncorrectLarge.setBounds((MainPanel.MAP_AREA_WIDTH - 605) / 2, (MainPanel.MAP_AREA_HEIGHT - 484) / 2, 605, 484);
        lblIncorrectLarge.setVisible(false);
        add(lblIncorrectLarge);
        
        //maak de label voor het uitspelen van land aan
        lblLandComplete = new JLabel();
        lblLandComplete.setIcon(currentLand.getLand().getLandEnded());
        lblLandComplete.setBounds((MainPanel.MAP_AREA_WIDTH-757)/2, (MainPanel.MAP_AREA_HEIGHT-568)/2, 757, 568);
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
                //update ook de feedback in de questionTextPanel
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
            JLabel point = new JLabel(answer.getText());
            point.setFont(new Font("Verdana", Font.BOLD, 20));
            //voeg aan elk antwoord een mouselistener toe die het juiste of niet juiste antwoord afhandeld
            point.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    
                    // Kijkt of de vraag al eerder goed beatnwoord is. Want dan blokeert hij je input.
                    if (currentQuestion.isCorrect() == false) {

                        //log het aantal pogingen en kijk of het juiste antwoord is geklikt
                        if (!answer.isCorrectAnswer()) {
                            currentQuestion.setTries(currentQuestion.getTries() + 1);
                            //toon feedback op scherm
                            lblIncorrectLarge.setVisible(true);
                            feedbackTimer.start();
                        } else {
                            currentQuestion.setCorrect(true);
                            //toon feedback op scherm
                            lblCorrectLarge.setVisible(true);
                            feedbackTimer.start();

                            //for loop controleren of alle vragen juist zijn
                            int questionCount = 0;
                            int questionComplete = 0;

                            //Deze for-loop kijkt hoeveel vragen er zijn
                            for (Question x : currentLand.getQuestions()) {
                                questionCount++;
                            }
                            System.out.println("Aantal vragen: " + questionCount);

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
                                lblLandComplete.setVisible(true);

                            } else {
                                System.out.println("Het land is nog NIET vrijgespeeld");
                                System.out.println("Goede vragen: " + questionComplete);
                            }
                        }

                    } else if (currentQuestion.isCorrect() == true) {
                        // Dit geeft een POP-UP dat je de vraag al (goed) hebt beantwoord
                        JOptionPane.showMessageDialog(null, "Deze vraag is al goed beantwoord.", "Let op", JOptionPane.INFORMATION_MESSAGE);
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

            point.setBounds((int) answer.getLocation().getX(), (int) answer.getLocation().getY(), 20, 20);
            add(point);
        }
        add(lblMap);

        //maak de questionTextPanel met de vraag en plaats deze in de bottombar
        questionTextPanel = new JPanel();
        questionTextPanel.setLayout(null);
        questionTextPanel.setBackground(Color.pink);
        questionTextPanel.setBounds(0, 0, MainPanel.BOTTOM_BAR_WIDTH, MainPanel.BOTTOM_BAR_HEIGHT);
        JLabel questionText = new JLabel(question.getText(), SwingConstants.CENTER);
        questionText.setBounds(0, 0, MainPanel.BOTTOM_BAR_WIDTH, 50);
        questionTextPanel.add(questionText);

        //maak alvast de plaatjes voor de feedback in de questionTextPanel aan
        lblCorrectSmall = new JLabel();
        lblCorrectSmall.setIcon(new ImageIcon(getClass().getResource("/media/correctSmall.png")));
        lblCorrectSmall.setBounds(MainPanel.BOTTOM_BAR_WIDTH - 113, 0, 113, 91);
        lblCorrectSmall.setVisible(false);
        questionTextPanel.add(lblCorrectSmall);

        lblIncorrectSmall = new JLabel();
        lblIncorrectSmall.setIcon(new ImageIcon(getClass().getResource("/media/incorrectSmall.png")));
        lblIncorrectSmall.setBounds(MainPanel.BOTTOM_BAR_WIDTH - 113, 0, 113, 91);
        lblIncorrectSmall.setVisible(false);
        questionTextPanel.add(lblIncorrectSmall);

        Main.mainPanel.showPanelBottomBar(questionTextPanel);

        //laat bij openen van vraag het feebback plaatje in de 
        //questionTextPanel zien als de vraag al geprobeerd of correct is
        if (currentQuestion.isCorrect()) {
            lblCorrectSmall.setVisible(true);
        } else if (currentQuestion.getTries() > 0) {
            lblIncorrectSmall.setVisible(true);
        }
    }
}