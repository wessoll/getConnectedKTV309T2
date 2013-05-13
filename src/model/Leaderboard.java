package model;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import we.getconnected.Main;
import we.getconnected.gui.MainPanel;

/**
 *
 * @author timvonsee
 */

public class Leaderboard extends JPanel{
    private ArrayList<User> leaderboard; 
    private JLabel header;
    private JTable table;
    private JPanel bottomBar;
    private JButton backToLeader;
    
    public Leaderboard(final ArrayList<User> users){
        leaderboard = users;
        setBounds(0, 0, MainPanel.MAP_AREA.width, MainPanel.MAP_AREA.height);
        setBackground(MainPanel.BACKGROUND_COLOR);

        
        header = new JLabel();
        header.setIcon(new ImageIcon(getClass().getResource("/media/LeaderboardHeader.png")));
        header.setBounds((MainPanel.MAP_AREA.width-header.getWidth())/2, 10, header.getWidth(), header.getHeight());
        add(header);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Speler ID", "Naam", "Achternaam", "Landen", "Klas"}) {
			private static final long serialVersionUID = -3523375506919277039L;

			@Override
			public boolean isCellEditable(final int row, final int column) {
				return false;
			}
		});
        table.setBounds((MainPanel.MAP_AREA.width-header.getWidth())/2, 10, header.getWidth(), MainPanel.MAP_AREA.height-header.getHeight());
        ((DefaultTableModel)table.getModel()).addRow(new Object[] {"ID", "Voornaam","Achternaam","Landen", "Klas"});
        for(User user:users){
            int id = user.getUser_id();
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            int completedCountries = 0;
            int availableCountries  = user.getEurope().getLanden().size();
            for(Country land:user.getEurope().getLanden()){
                if(land.isCompleted()){
                   completedCountries++; 
                }
            }
            String progress = completedCountries +" / "+availableCountries;
            ((DefaultTableModel)table.getModel()).addRow(new Object[] {id, firstName,lastName,progress, "Geen"});
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(27);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        add(table);
       
        
        bottomBar = new JPanel();
        bottomBar.setLayout(null);
        bottomBar.setBackground(MainPanel.BACKGROUND_COLOR);
        bottomBar.setBounds(0, 0, MainPanel.BOTTOM_BAR.width, MainPanel.BOTTOM_BAR.height);
        JButton showMapButton = new JButton("Bekijk kaart");
        showMapButton.setBounds(0, 0, MainPanel.BOTTOM_BAR.width, 50);
        showMapButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedColumn()!=-1){
                    User user = users.get(table.getSelectedColumn()-1);
                    Continent continent = user.getEurope();
                    if(user.getUser_id()!=Main.getCurrentUser().getUser_id()){
                       continent.setPlayable(false);
                    }
                    backToLeader.setEnabled(true);
                    Main.getMainPanel().showPanelMapArea(continent);
                }
            }
        });
        backToLeader = new JButton("Leaderboard");
        backToLeader.setBounds(0, 60, 100, 50);
        backToLeader.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.getMainPanel().showPanelMapArea(Main.getLeaderboard());
            }
            
        });
        bottomBar.add(backToLeader);
        bottomBar.add(showMapButton);
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
}
