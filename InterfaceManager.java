package ryan.carter;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;


public class InterfaceManager extends FileManager{

    // Initializes main ui used
    public void CreateInterface() {

        // Creates a new frame to hold the UI
        JFrame frame = new JFrame("Ryan's Super Cool Quat/Epton Adjustment Finder 3000");
        
        // Adds basic UI elements
        JPanel southPanel = CreateFileSelectionPanel(frame);        // Creates ref to panel so buttons can be added to it
        CreateSearchBar(frame, southPanel);

        // Just makes everything work..
        frame.pack();

        // Enables user to see the frame
        frame.setVisible(true);

        // Makes program close when frame is closed
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    // Creates a search bar with search button
    private void CreateSearchBar(JFrame frame, JPanel southPanel)
    {   
        // Creates panel to hold search bar elements
        JPanel northPanel = new JPanel();

        // Initializes place to recieve text input from user
        JTextField searchBar = new JTextField("Enter first 6 characters of formula code...", 22);
        searchBar.setHorizontalAlignment(0);
        searchBar.setFont(searchBar.getFont().deriveFont(Font.PLAIN, 30f));
        searchBar.addActionListener(e -> SearchFormula(searchBar.getText(), frame, southPanel));

        // Initializes search button
        JButton searchButton = new JButton("Search");    

        // Adds functionality to button
        searchButton.addActionListener(e -> SearchFormula(searchBar.getText(), frame, southPanel));
        
        // Makes button activate through enter key
        KeyStroke us2 = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
        northPanel.getInputMap().put(us2, "ENTER");
        northPanel.getActionMap().put("ENTER", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent evt){
               searchButton.doClick();
               searchBar.setFocusable(true);
               System.out.println("IT WORKED !!! :DD");
            }
        });
        searchButton.setFocusable(true);        

        // Adds components to panel and frame
        northPanel.add(searchBar);
        northPanel.add(searchButton);
        frame.add(northPanel, BorderLayout.NORTH);
    }

    // Creates a seperate area for when multiple products share a formula
    private JPanel CreateFileSelectionPanel(JFrame frame) {

        // Creates panel to hold multiple choice selection of products when multiple exist for one formula
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        southPanel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

        // Creates new scroll pane to hold buttons
        JScrollPane scrollPane = new JScrollPane(southPanel);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(24);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(), 50));

        // Adds buttons to panel and panel to frame
        frame.add(scrollPane, BorderLayout.SOUTH);

        return southPanel;
    }

}
