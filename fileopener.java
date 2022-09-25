
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author TANIM
 */
public class fileopener {
    //JFileChooser file_chooser = new JFileChooser();
    //StringBuilder sb = new StringBuilder();
    String s;
    public File file;
    public void pick_me() throws FileNotFoundException{
         // create an object of JFileChooser class
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
 
            // invoke the showsOpenDialog function to show the save dialog
            file = j.getSelectedFile();
            int r=j.showOpenDialog(null);
 
            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION)
 
            {
                // set the label to the path of the selected file
                s= j.getSelectedFile().getAbsolutePath();
            }
            // if the user cancelled the operation
            else
                System.out.println("File is cancelled");
        }
    }

