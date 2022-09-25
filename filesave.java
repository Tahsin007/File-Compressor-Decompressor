
import java.io.File;
import javax.swing.JFileChooser;


/**
 *
 * @author TANIM
 */
public class filesave {
    JFileChooser file_choser = new JFileChooser();
    String s1;
    public void save(){
        file_choser.setCurrentDirectory(new File("C:\\Users\\TANIM\\Desktop"));
        int response = file_choser.showSaveDialog(null);
        if(response==JFileChooser.APPROVE_OPTION){
            File file = new File(file_choser.getSelectedFile().getAbsolutePath());
            s1=file_choser.getSelectedFile().getAbsolutePath();
        }
    }
    
}
