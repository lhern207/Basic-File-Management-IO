/*
 * Test class for File Management/IO
 * Some exception handling not correctly implemented.
 * Windows change of focus must be improved.
 */

/**
 * @author Lester
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.io.IOException;
import javax.swing.JFrame;

class TestFile {

    public static void main(String[] arg) throws FileNotFoundException {
        boolean done = false;
        BasicFile f;
        
        display("Please select a File to see menu of options", "Welcome");
        f = new BasicFile();
        displayScrollPane(f.getContents(), "File Contents");

        String menu = "Enter option:\n1. Open a different File on Scrollable Window\n2. Copy File\n" +
                "3. Append to file\n4. Over-Write file\n5. Display File Information in Scrollable Window" +
                "\n6. Find a word in the file\n7.Quit";
        while (!done) {
            String s = JOptionPane.showInputDialog(menu);
            try {
                int i = Integer.parseInt(s);
                switch (i) {
                    case 1:
                        f = new BasicFile();
                        displayScrollPane(f.getContents(), "File Contents");
                        break;
                    
                    case 2:
                        String copyMenu = "Please enter the name and extension you would like to assign to the copy:\n"
                                + "An example of a valid name would be 'copy.txt'\n"
                                + "(Copy will appear inside Project Main Folder assigned by user)";
                        String path = JOptionPane.showInputDialog(copyMenu);
                        f.copyFile(path);
                        display("File has been copied successfully", "Copy Successful");
                        break;
                    
                    case 3:
                        String appendMenu = "Please enter what you would like to append to the file:";
                        String appendText = JOptionPane.showInputDialog(appendMenu);
                        f.appendToFile(appendText);
                        display("File has been appended successfully", "Append Successful");
                        break;
                        
                    case 4:
                        String overWriteMenu = "Please enter overwrite text:";
                        String overWriteText = JOptionPane.showInputDialog(overWriteMenu);
                        f.overWriteFile(overWriteText);
                        display("File has been overwritten successfully", "OverWrite Successful");
                        break;
                        
                    case 5:
                        displayScrollPane(f);
                        break;
                        
                    case 6:
                        String findMenu = "Please enter the word that you would like to find:";
                        String searchQuery = JOptionPane.showInputDialog(findMenu);
                        display(f.findMatchingSearch(searchQuery), "Matching Results");
                        break;
                   
                    case 7:
                        done = true;
                        break;
                    default:
                        displayError("This option is undefined", "Error");
                        break;
                }
            } catch (NumberFormatException | NullPointerException | IOException e) {
                displayError(e.toString(), "Error");
            }
        }
    }

    static void displayError(String s, String err) {
        JOptionPane.showMessageDialog(null, s, err, JOptionPane.ERROR_MESSAGE);
    }
    
    static void display(String s, String title){
        JOptionPane.showMessageDialog(null, s, title ,JOptionPane.INFORMATION_MESSAGE);
    }

    static void displayScrollPane(String text, String title) {
        
        JTextArea textArea = new JTextArea();
        textArea.setText(text);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JFrame frame = new JFrame(title);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1200, 720));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
    static void displayScrollPane(BasicFile f) throws FileNotFoundException {
        String miscellaneous = "Size: " + f.getFileSize() + " kilobytes" + "\n" + "Absolute Path: " + f.getPath() + "\n";
        String name = f.getName();
        String NumberOfLines = f.getNumberOfLines() + "";
        String FilesAndDirs = f.getFilesAndDirsInPath();
        
        JTextArea textArea = new JTextArea();
        textArea.setText("File Name: " + name + "\n" + miscellaneous + 
                "Number of Lines: " + NumberOfLines + "\n\n" + "Files and Directories in the File's path:\n" + 
                FilesAndDirs);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JFrame frame = new JFrame("File Information");
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1200, 720));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}

