package ryan.carter;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Desktop;

public class FileManager {
    
    // Ref to desired file to open
    File desiredFile;

    // Checks quat adjustments for formula
    public void SearchFormula(String formula, JFrame frame, JPanel southPanel) {

        try {
            // Searches lab directory for folders under formula name
            File directory = new File("H:\\Adjustments\\Quat\\" + formula);
            
            // Checks file is actually a folder
            if(directory.isDirectory()) {   
                
                // Checks what filel should be opened
                OpenFile(directory.listFiles(), frame, southPanel);
            }

       } catch (Exception e) {
            System.err.println("No folder exists.");
        }
    }

    // Checks file for most recent file and opens it
    void OpenFile(File[] files, JFrame frame, JPanel southPanel) {

        /*
         * Checks if first file is a directory or excel file since products may share a formula code and user input may be required
         * Ideally ALL files should be a folder or file, so checking just the first one should be fine.. right?
         */ 
        if(IsDirectory(files, frame, southPanel)) {
            return;
        }
        
        // Checks the batch number at the end of the file name, the highest numer is the file to open
        int highestBatchNumber = 0; 
        
        try {
            
            // Checks every file in chosen directory
            for(File file : files) {

                // Gets batch number for each file
                int batchNumber = Integer.parseInt(file.getName().substring(file.getName().length() - 12, file.getName().length() - 5));

                // Compares batch numbers of each file
                if(batchNumber > highestBatchNumber) {
                    highestBatchNumber = batchNumber;
                    desiredFile = file;
                }
            }

            // Opens file
            if (Desktop.isDesktopSupported()) {

                Desktop.getDesktop().open(desiredFile);
            }

        } catch (Exception e) {
            System.err.println("Error with finding most recent file.");
        }
    }

    // Checks if program should continue based on if multiple products exist for a single formula
    private boolean IsDirectory(File[] files, JFrame frame, JPanel southPanel) {

        // File is directory
        if(files[0].isDirectory()) {

            // Checks list of products in formula folder
            for(File file : files) {

                // Makes new button for each product
                JButton fileButton = new JButton(file.getName());

                // Adds functionality to button
                fileButton.addActionListener(e -> OpenFile(file.listFiles(), frame, southPanel));
                
                // Adds button to panel
                southPanel.add(fileButton);
            }

            // Just makes everything work..
            frame.validate();
            return true;
        }
        // File is not a directory
        else {
            return false;
        }
    }
}
