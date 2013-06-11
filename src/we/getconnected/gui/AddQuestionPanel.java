package we.getconnected.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
import model.Question;
import model.User;
import we.getconnected.Main;
import static we.getconnected.gui.MainPanel.BOTTOM_BAR;

//@todo
// url veld moet geldig zijn
/**
 * Toevoegen van een nieuwe vraag aan een land
 *
 * @author tim/wesley
 */
public class AddQuestionPanel extends JPanel {

    private JTextField txtUrl, txtQuestion;
    private JLabel lblMapPreview, lblAnswerSelection;
    private JLabel lblAnswerA, lblAnswerB, lblAnswerC, lblAnswerD;
    private JLabel lblAnswerMapA, lblAnswerMapB, lblAnswerMapC, lblAnswerMapD;
    private JComboBox cmbCountry, cmbAnswer;
    private JPanel mapArea;
    private String[] correctAnswer = {"A", "B", "C", "D"}; // Keuzes voor answerBox 
    private char selectedLetter;
    private Loader loader;
    private int aXPos = 0, bXPos = 0, cXPos = 0, dXPos = 0, aYPos = 0, bYPos = 0, cYPos = 0, dYPos = 0;
    //constanten voor dimensies
    private static final Dimension LABEL_DIM = new Dimension(600, 30);
    private static final Dimension LABEL_ANSWER_SELECTION_DIM = new Dimension(600, 60);
    public static final Dimension LABEL_ANSWER_DIM = new Dimension(30, 35);
    private static final Dimension TEXTFIELD_DIM = new Dimension(400, 25);
    private static final Dimension COMBOBOX_DIM = new Dimension(100, 35);
    private static final Dimension BUTTON_DIM = new Dimension(160, 25);
    //constanten voor coordinaten
    private static final Point LABEL_ANSWER_A_P = new Point(500, 50);
    private static final Point LABEL_ANSWER_B_P = new Point(540, 50);
    private static final Point LABEL_ANSWER_C_P = new Point(580, 50);
    private static final Point LABEL_ANSWER_D_P = new Point(620, 50);
    private static final Point LABEL_ANSWER_SELECTION_P = new Point(500, 0);
    private static final Point LABEL_QUESTION_P = new Point(10, 0);
    private static final Point LABEL_COUNTRY_P = new Point(10, 120);
    private static final Point LABEL_ANSWER_P = new Point(200, 120);
    private static final Point TXT_QUESTION_P = new Point(10, 30);
    private static final Point LABEL_URL_P = new Point(10, 60);
    private static final Point TXT_URL_P = new Point(10, 90);
    private static final Point CMB_COUNTRY_P = new Point(80, 120);
    private static final Point CMB_ANSWER_P = new Point(310, 120);
    private static final Point BTN_URL_P = new Point(420, 90);
    private static final Point BTN_UPLOADER_P = new Point(420, 120);

    /**
     * Constructor voor toevoegen van een nieuwe vraag aan de app
     */
    public AddQuestionPanel() {
        setLayout(null);
        setBackground(MainPanel.BACKGROUND_COLOR);
        setBounds(0, 0, BOTTOM_BAR.width, BOTTOM_BAR.height);

        //maak de panel op voor de inputvelden
        JLabel lblQuestion = new JLabel("Vraag:");
        lblQuestion.setSize(LABEL_DIM);
        lblQuestion.setLocation(LABEL_QUESTION_P);
        lblQuestion.setFont(MainPanel.ROCKWELL_20);
        add(lblQuestion);

        txtQuestion = new JTextField();
        txtQuestion.setSize(TEXTFIELD_DIM);
        txtQuestion.setLocation(TXT_QUESTION_P);
        add(txtQuestion);

        JLabel lblUrl = new JLabel("Link naar afbeelding:");
        lblUrl.setSize(LABEL_DIM);
        lblUrl.setLocation(LABEL_URL_P);
        lblUrl.setFont(MainPanel.ROCKWELL_20);
        add(lblUrl);

        txtUrl = new JTextField();
        txtUrl.setSize(TEXTFIELD_DIM);
        txtUrl.setLocation(TXT_URL_P);
        add(txtUrl);

        JButton btnTestUrl = new JButton();
        btnTestUrl.setSize(BUTTON_DIM);
        btnTestUrl.setLocation(BTN_URL_P);
        btnTestUrl.setText("Test URL");
        btnTestUrl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //laadt de preview in van de url
                loader = new Loader("Afbeelding inladen...");
                loader.setVisible(true);
                Thread loadUrl = new Thread() {
                    @Override
                    public void run() {

                        //begin plaatje inladen

                        lblMapPreview.setText(""); // Haalt de text uit de label

                        // Hier gaat de output van de methode in.
                        lblMapPreview.setIcon(getResizedImage(txtUrl.getText()));
                        lblMapPreview.setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);

                        //einde plaatje inladen

                        loader.setVisible(false);
                    }
                };
                loadUrl.start();
            }
        });
        add(btnTestUrl);

        JLabel lblAnswer = new JLabel("Antwoord:");
        lblAnswer.setSize(LABEL_DIM);
        lblAnswer.setLocation(LABEL_ANSWER_P);
        lblAnswer.setFont(MainPanel.ROCKWELL_20);
        add(lblAnswer);

        cmbAnswer = new JComboBox(correctAnswer);
        cmbAnswer.setSize(COMBOBOX_DIM);
        cmbAnswer.setLocation(CMB_ANSWER_P);
        cmbAnswer.setSelectedIndex(0);
        add(cmbAnswer);

        JLabel lblCountry = new JLabel("Land:");
        lblCountry.setSize(LABEL_DIM);
        lblCountry.setLocation(LABEL_COUNTRY_P);
        lblCountry.setFont(MainPanel.ROCKWELL_20);
        add(lblCountry);

        cmbCountry = new JComboBox(Country.Countries.values());
        cmbCountry.setSize(COMBOBOX_DIM);
        cmbCountry.setLocation(CMB_COUNTRY_P);
        cmbCountry.setSelectedIndex(0);
        add(cmbCountry);

        lblAnswerSelection = new JLabel("Klik op een letter");
        lblAnswerSelection.setSize(LABEL_ANSWER_SELECTION_DIM);
        lblAnswerSelection.setLocation(LABEL_ANSWER_SELECTION_P);
        lblAnswerSelection.setFont(MainPanel.ROCKWELL_20);
        add(lblAnswerSelection);

        //labels voor de answers
        lblAnswerA = new JLabel("A");
        lblAnswerA.setSize(LABEL_ANSWER_DIM);
        lblAnswerA.setLocation(LABEL_ANSWER_A_P);
        lblAnswerA.setFont(MainPanel.ROCKWELL_30_BOLD);
        lblAnswerA.addMouseListener(new AnswerListener());
        add(lblAnswerA);

        lblAnswerB = new JLabel("B");
        lblAnswerB.setSize(LABEL_ANSWER_DIM);
        lblAnswerB.setLocation(LABEL_ANSWER_B_P);
        lblAnswerB.setFont(MainPanel.ROCKWELL_30_BOLD);
        lblAnswerB.addMouseListener(new AnswerListener());
        add(lblAnswerB);

        lblAnswerC = new JLabel("C");
        lblAnswerC.setSize(LABEL_ANSWER_DIM);
        lblAnswerC.setLocation(LABEL_ANSWER_C_P);
        lblAnswerC.setFont(MainPanel.ROCKWELL_30_BOLD);
        lblAnswerC.addMouseListener(new AnswerListener());
        add(lblAnswerC);

        lblAnswerD = new JLabel("D");
        lblAnswerD.setSize(LABEL_ANSWER_DIM);
        lblAnswerD.setLocation(LABEL_ANSWER_D_P);
        lblAnswerD.setFont(MainPanel.ROCKWELL_30_BOLD);
        lblAnswerD.addMouseListener(new AnswerListener());
        add(lblAnswerD);

        JButton upload = new JButton("Vraag toevoegen");
        upload.setSize(BUTTON_DIM);
        upload.setLocation(BTN_UPLOADER_P);
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //blokkeer het uploaden als de vraag ontbreekt
                if (txtQuestion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Voer het veld \"Vraag\" in met een vraag.",
                            "Vraag ontbreekt", JOptionPane.ERROR_MESSAGE);
                } //blokkeer het uploaden als het plaatje ontbreekt
                else if (lblMapPreview.getIcon() == null) {
                    JOptionPane.showMessageDialog(null, "Voer een geldige URL in en klik op \"Test URL\".",
                            "Afbeelding ontbreekt", JOptionPane.ERROR_MESSAGE);
                } //blokkeer het uploaden als de antwoorden niet geplaatst zijn
                else if (aXPos == 0 || bXPos == 0 || cXPos == 0 || dXPos == 0) {
                    JOptionPane.showMessageDialog(null, "Zorg ervoor dat alle antwoorden op de kaart zijn geplaatst.",
                            "Één of meerdere ontbrekende antwoorden", JOptionPane.ERROR_MESSAGE);
                } //vraag om bevestiging alvorens verder te gaan
                else {
                    String tempQuestion = txtQuestion.getText();
                    String tempUrl = txtUrl.getText();
                    if (tempQuestion.length() >= 50) {
                        tempQuestion = tempQuestion.substring(0, 20) + "..."
                                + tempQuestion.substring(tempQuestion.length() - 20, tempQuestion.length());
                    }
                    if (tempUrl.length() >= 50) {
                        tempUrl = tempUrl.substring(0, 20) + "..."
                                + tempUrl.substring(tempUrl.length() - 20, tempUrl.length());
                    }
                    String prefix = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    String dialogContent = "<html>U wilt een nieuwe vraag met de volgende gegevens aanmaken: <br>"
                            + prefix + "<b>Vraag: </b>" + tempQuestion + "<br>"
                            + prefix + "<b>URL: </b>" + tempUrl + "<br>"
                            + prefix + "<b>Land: </b>" + cmbCountry.getSelectedItem().toString() + "<br>"
                            + prefix + "<b>Juiste antwoord: </b>" + cmbAnswer.getSelectedItem() + "<br>"
                            + "Is dit juist?</html>";

                    if (JOptionPane.showConfirmDialog(null, dialogContent,
                            "Bevestiging nieuwe vraag", JOptionPane.YES_NO_OPTION) == 0) {
                        //maak de nieuwe vraag aan
                        //bepaal welk antwoord geselecteerd is
                        byte a = 0, b = 0, c = 0, d = 0;
                        if (cmbAnswer.getSelectedItem().toString().equals("A")) {
                            a = 1;
                        }
                        if (cmbAnswer.getSelectedItem().toString().equals("B")) {
                            b = 1;
                        }
                        if (cmbAnswer.getSelectedItem().toString().equals("C")) {
                            c = 1;
                        }
                        if (cmbAnswer.getSelectedItem().toString().equals("D")) {
                            d = 1;
                        }

                        int question_id = Main.getQueryManager().getLastQuestionId() + 1;
                        //maak de answers aan
                        ArrayList<Answer> answers = new ArrayList<Answer>();
                        answers.add(new Answer((int) aXPos, (int) aYPos, a, "A", question_id));
                        answers.add(new Answer((int) bXPos, (int) bYPos, b, "B", question_id));
                        answers.add(new Answer((int) cXPos, (int) cYPos, c, "C", question_id));
                        answers.add(new Answer((int) dXPos, (int) dYPos, d, "D", question_id));

                        //maak de daadwerkelijke vraag met de answers aan
                        //bepaal eerst a.d.h.v. de combobox aan welk land de vraag gekoppeld moet worden
                        int country_id = -1;
                        for (int i = 0; i < Country.Countries.values().length; i++) {
                            if (Country.Countries.values()[i] == cmbCountry.getSelectedItem()) {
                                country_id = Main.getQueryManager().getCountry_id(
                                        Country.Countries.values()[i].getLandNaam());
                            }
                        }
                        //maak nu de vraag aan
                        Question question = new Question(question_id, txtQuestion.getText(), txtUrl.getText(), answers, country_id);

                        //update de database
                        Main.getQueryManager().insertQuestion(question);
                        //voeg de antwoorden toe aan de database
                        for (Answer answer : answers) {
                            Main.getQueryManager().insertAnswer(answer);
                        }
                        //update de user_question tabel zodat elke user over de nieuwe vraag bezit
                        ArrayList<User> users = Main.getQueryManager().getUsers();
                        for (User user : users) {
                            Main.getQueryManager().insertUserQuestion(question, user.getUser_id());
                        }
                        //update de landen voor de ingeladen user
                        Main.setCurrentUser(Main.getQueryManager().getUser(Main.getCurrentUser().getUserName()));
                    }
                }
            }
        });
        add(upload);

        //stel de maparea in zodat daar een afbeelding op kan worden getoond
        //en zodat de antwoorden erop kunnen worden geplaatst
        mapArea = new JPanel();
        mapArea.setLayout(null);
        mapArea.setBackground(MainPanel.BACKGROUND_COLOR);
        mapArea.setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
        mapArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                /**
                 * log de mouse coordinates en trekt de helft van de DEFAULT_BUTTON eraf zodat bij
                 * een klik op de maparea de punt in het midden van de muisklik komt te staan
                 */
                int xPos = (int) mapArea.getMousePosition().getX() - (LABEL_ANSWER_DIM.width / 2);
                int yPos = (int) mapArea.getMousePosition().getY() - (LABEL_ANSWER_DIM.width / 2);

                switch (selectedLetter) {
                    case 'A':
                        aXPos = xPos;
                        aYPos = yPos;
                        lblAnswerMapA.setLocation(xPos, yPos);
                        lblAnswerMapA.setVisible(true);
                        break;
                    case 'B':
                        bXPos = xPos;
                        bYPos = yPos;
                        lblAnswerMapB.setLocation(xPos, yPos);
                        lblAnswerMapB.setVisible(true);
                        break;
                    case 'C':
                        cXPos = xPos;
                        cYPos = yPos;
                        lblAnswerMapC.setLocation(xPos, yPos);
                        lblAnswerMapC.setVisible(true);
                        break;
                    case 'D':
                        dXPos = xPos;
                        dYPos = yPos;
                        lblAnswerMapD.setLocation(xPos, yPos);
                        lblAnswerMapD.setVisible(true);
                        break;
                    default:
                        lblAnswerSelection.setText("Selecteer eerst een letter");
                        break;
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

        //labels voor de answers
        lblAnswerMapA = new JLabel("A");
        lblAnswerMapA.setFont(MainPanel.ROCKWELL_30_BOLD);
        lblAnswerMapA.setSize(LABEL_ANSWER_DIM);
        lblAnswerMapA.setVisible(false);
        mapArea.add(lblAnswerMapA);

        lblAnswerMapB = new JLabel("B");
        lblAnswerMapB.setFont(MainPanel.ROCKWELL_30_BOLD);
        lblAnswerMapB.setSize(LABEL_ANSWER_DIM);
        lblAnswerMapB.setVisible(false);
        mapArea.add(lblAnswerMapB);

        lblAnswerMapC = new JLabel("C");
        lblAnswerMapC.setFont(MainPanel.ROCKWELL_30_BOLD);
        lblAnswerMapC.setSize(LABEL_ANSWER_DIM);
        lblAnswerMapC.setVisible(false);
        mapArea.add(lblAnswerMapC);

        lblAnswerMapD = new JLabel("D");
        lblAnswerMapD.setFont(MainPanel.ROCKWELL_30_BOLD);
        lblAnswerMapD.setSize(LABEL_ANSWER_DIM);
        lblAnswerMapD.setVisible(false);
        mapArea.add(lblAnswerMapD);

        //label voor de map preview
        lblMapPreview = new JLabel("Er is nog geen afbeelding...", SwingConstants.CENTER);
        lblMapPreview.setFont(MainPanel.ROCKWELL_20);
        lblMapPreview.setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
        mapArea.add(lblMapPreview);

        Main.getMainPanel().showPanelMapArea(mapArea);
    }

    private class AnswerListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == lblAnswerA) {
                aXPos =
                        selectedLetter = 'A';

                lblAnswerA.setForeground(Color.RED);
                lblAnswerB.setForeground(Color.BLACK);
                lblAnswerC.setForeground(Color.BLACK);
                lblAnswerD.setForeground(Color.BLACK);
            }
            if (e.getSource() == lblAnswerB) {
                selectedLetter = 'B';

                lblAnswerA.setForeground(Color.BLACK);
                lblAnswerB.setForeground(Color.RED);
                lblAnswerC.setForeground(Color.BLACK);
                lblAnswerD.setForeground(Color.BLACK);
            }
            if (e.getSource() == lblAnswerC) {
                selectedLetter = 'C';

                lblAnswerA.setForeground(Color.BLACK);
                lblAnswerB.setForeground(Color.BLACK);
                lblAnswerC.setForeground(Color.RED);
                lblAnswerD.setForeground(Color.BLACK);
            }
            if (e.getSource() == lblAnswerD) {
                selectedLetter = 'D';

                lblAnswerA.setForeground(Color.BLACK);
                lblAnswerB.setForeground(Color.BLACK);
                lblAnswerC.setForeground(Color.BLACK);
                lblAnswerD.setForeground(Color.RED);
            }
            lblAnswerSelection.setText("<html> Klik nu op de kaart<BR>"
                    + "om daar de letter te plaatsen </html>");
            //txtSelectQuestion.setBounds(500, 0, 600, 60);
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
    }
    // In deze methode word de image ingeladen uit de mee gegeven URL en dan word er gekeken naar de
    // groote. Als deze te groot is word hij aangepast tot het juiste formaat.

    public static ImageIcon getResizedImage(String URL) {
        // rawImg, hier word de image ingelade URL in geplaatst
        ImageIcon rawImg = new ImageIcon(Main.getImage(URL));
        // Deze ImgaIcon dient alleen voor de return
        ImageIcon importImg = null;

        //Kijkt of de image portret of landscape is en kijkt dan naar de groote van de image en resized hem dan.
        // Landscape resize:
        if (rawImg.getIconHeight() >= MainPanel.MAP_AREA.height) {
                importImg = new ImageIcon(
                        Main.getImage(URL).getScaledInstance(-1,
                        MainPanel.MAP_AREA.height,
                        Image.SCALE_FAST));
            
        } // Portret resize:
        else if (rawImg.getIconWidth() >= MainPanel.MAP_AREA.width) {
                importImg = new ImageIcon(
                        Main.getImage(URL).getScaledInstance(MainPanel.MAP_AREA.width,
                        -1, Image.SCALE_FAST));
            }
        // geen resize nodig
        else{
            importImg = new ImageIcon(Main.getImage(URL));
        }
        return importImg;
    }
}
