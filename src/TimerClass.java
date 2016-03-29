import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;


/**
 * TimerClass uses the timer api only to measure
 * the start and end time of the sorting. However, the
 * delay (Thread.sleep()) in each sort is not balanced,
 * so one should not use it for benchmarking, only for
 * educational purposes only.
 * 
 * @author Don Bhrayan M. Singh
 */

public class TimerClass extends JFrame
{
    private Timer timer;
    public int count = 0;
    
    public void TimerStart()
    {
        count = 0;
        timer = new Timer(10, new TimerListener());
        timer.start();
    }
    
    private class TimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            count++;
        }
    }
    
    public void done()
    {
       double time;
       timer.stop();
       time = (double)count / 100;
       if(count!=0){
          JOptionPane.showMessageDialog(null, time+" seconds total!");
       }
       
    }            
    
}

