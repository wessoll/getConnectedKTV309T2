/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package we.getconnected.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Question;
import util.ImageUtil;
import util.TempQuestions;
import we.getconnected.Main;

/**
 *
 * @author wesley
 */
public class PrototypeInterface extends JPanel{
    
    private Question qObject;
    
    public PrototypeInterface() throws Exception{
        this.setLayout(null);
        JPanel content = new JPanel();
        content.setBounds(0, 0, 808, 573);
        content.setLayout(null);
        
        //map
        JLabel map = new JLabel();
        ImageIcon tempMap = ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "eu_kaart.png");
        map.setIcon(tempMap);
        map.setBounds(0, 0, tempMap.getIconWidth(), tempMap.getIconHeight());
        map.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt){
                System.out.println("click!");
                try {
                    showQuestion(TempQuestions.getQuestion1());
                } catch (Exception ex) {
                    Logger.getLogger(PrototypeInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        content.add(map);
        add(content);
        repaint();
    }
    
    public void showQuestion(Question qObjectt) throws Exception{
        this.qObject = qObjectt;
        this.removeAll();
        
        JPanel map = new QuestionInterface(qObject);
        map.setBounds(0, 0, 600, 600);
        
        JPanel q = new JPanel();
        q.setLayout(null);
        q.setBounds(0,600,1024,100);
        q.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel question = new JLabel(qObject.getText());
        question.setFont(new Font("Arial", Font.BOLD, 14));
        question.setBounds(20, 0, 600, 50);
        JButton next = new JButton("Volgende vraag");
        next.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!qObject.isCorrect()){
                        JOptionPane.showMessageDialog(null, "Vraag nog niet beantwoord", "CONCEPT FOUTMELDING", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        if (qObject.getText().contains("temperaturen")){
                            System.out.println("end process");
                            JPanel content = new JPanel();
                            content.setBounds(0, 0, 808, 573);
                            content.setLayout(null);
        
                            //map
                            JLabel map = new JLabel();
                            ImageIcon tempMap = ImageUtil.getAndLoadImageIcon(Main.IMAGES_LOCATION + "eu_kaart_nl_completed.png");
                            map.setIcon(tempMap);
                            map.setBounds(0, 0, tempMap.getIconWidth(), tempMap.getIconHeight());
                            removeAll();
                            content.add(map);
                            add(content);
                            repaint();
                        }
                        else{
                            showQuestion(TempQuestions.getQuestion2());
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PrototypeInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        next.setBounds(850, 40, 150, 30);
        q.add(question);
        q.add(next);
        
        add(q);
        add(map);
        repaint();
        
        
        
    }
    
    
    
    
}
