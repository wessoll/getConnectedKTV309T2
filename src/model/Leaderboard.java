package model;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import we.getconnected.Main;
import we.getconnected.gui.MainPanel;

/**
 *
 * @author timvonsee
 */

public class Leaderboard extends JPanel{
    private ArrayList<User> leaderboard; 
    private JLabel header, nameHeader;
    private JButton showMap, backToLeader;
    private JTable table;
    private JPanel bottomBar;
    
    public Leaderboard(final ArrayList<User> users){
        leaderboard = users;
        setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
        setBackground(MainPanel.BACKGROUND_COLOR);

        header = new JLabel();
        header.setIcon(new ImageIcon(getClass().getResource("/media/LeaderboardHeader.png")));
        header.setBounds((MainPanel.MAP_AREA.width-header.getWidth())/2, 10, header.getWidth(), header.getHeight());
        
        nameHeader = new JLabel("", SwingConstants.CENTER);
        nameHeader.setFont(new Font("Rockwell",Font.PLAIN,15));
        nameHeader.setBounds(0, 70, MainPanel.BOTTOM_BAR.width, 50);
        
        add(header);
        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Username", "Naam", "Achternaam", "Landen", "Klas"}) {

			@Override
			public boolean isCellEditable(final int row, final int column) {
				return false;
			}
		});
        table.setBounds((MainPanel.MAP_AREA.width-header.getWidth())/2, 10, header.getWidth(), MainPanel.MAP_AREA.height-header.getHeight());
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ((DefaultTableModel)table.getModel()).addRow(new Object[] {"Username","Voornaam","Achternaam","Landen", "Klas"});
        for(User user:users){
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String userName = user.getUserName();
            String groupName = user.getGroupName();
            int completedCountries = 0;
            int availableCountries  = user.getEurope().getLanden().size();
            for(Country land:user.getEurope().getLanden()){
                if(land.isCompleted()){
                   completedCountries++; 
                }
            }
            String progress = completedCountries +" / "+availableCountries;
            ((DefaultTableModel)table.getModel()).addRow(new Object[] {userName,firstName,lastName,progress, groupName});
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table.getSelectedRow() == 0){
                    showMap.setEnabled(false);
                }
                else{
                    showMap.setEnabled(true);
                }
            }
        });
        
        add(table);
        
        bottomBar = new JPanel();
        bottomBar.setLayout(null);
        bottomBar.setBackground(MainPanel.BACKGROUND_COLOR);
        bottomBar.setBounds(0, 0, MainPanel.BOTTOM_BAR.width, MainPanel.BOTTOM_BAR.height);
        
        showMap = new JButton();
        showMap.setEnabled(false);
        showMap.setIcon(new ImageIcon(getClass().getResource("/media/ZieKaart.png")));
        showMap.setBounds((MainPanel.BOTTOM_BAR.width-134) /2, 10,134,53);
        showMap.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow()!=-1 && table.getSelectedRow()!=0){
                    User user = getUser(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
                    Continent continent = user.getEurope();
                    if(user.getUser_id()!=Main.getCurrentUser().getUser_id()){
                       continent.setPlayable(false);
                    } 
                    nameHeader.setText("Je bekijkt " + user.getUserName() + " zijn kaart");
                    nameHeader.setVisible(true);
                    showMap.setVisible(false);
                    backToLeader.setVisible(true);
                    Main.getMainPanel().clearPanelMapArea();
                    Main.getMainPanel().showPanelMapArea(continent);
                    continent.updateWorldMap();
                }
            }
            
        });
        
        backToLeader = new JButton();
        backToLeader.setIcon(new ImageIcon(getClass().getResource("/media/Terug.png")));
        backToLeader.setBounds((MainPanel.BOTTOM_BAR.width-134) /2,10,134,53);
        backToLeader.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               Main.getMainPanel().showPanelMapArea(Main.getLeaderboard());
               nameHeader.setVisible(false);
               backToLeader.setVisible(false);
               showMap.setVisible(true);
            }
            
        });
        backToLeader.setVisible(false);
       
        bottomBar.add(nameHeader);
        bottomBar.add(backToLeader);
        bottomBar.add(showMap);
    }
    
    // Get en Set methode om de ArrayList leaderboard aan te roepen.
    public ArrayList<User> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(ArrayList<User> leaderboard) {
        this.leaderboard = leaderboard;
    }
    
    public JPanel getBottomBar(){
        return bottomBar;
    }
    
    /**
     * Vraag een user op uit de Arraylist leaderboard.
     * @param username
     * @return User with the username <username>
     */
    public User getUser(String username){
        for(User user:leaderboard){
            if(user.getUserName().equals(username)){
                return user;
            }
        }
        return null;
    }
}
