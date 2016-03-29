import java.awt.*;
import javax.swing.*;

/**
 * Visualization of some of the most popular sorting Algorithms
 * This software was made for the Final Project for
 * MS-IT 201 A.Y. 2014-2015.
 * @author Don Bhrayan M. Singh
 *
 * Starter class contains main which basically pulls
 * up the JFrame for the menu panel (MainFrame).
 */
public class Starter extends JFrame{

    public static void main(String[] args){
        int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        JFrame mainFrame = new MainFrame();
        mainFrame.setLocation(windowWidth/4, 10);
        mainFrame.setVisible(true);
    }
}
