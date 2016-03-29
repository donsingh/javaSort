import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * This initializes the animation JFrame where the 
 * panel for the bars are added. The settings for the
 * window are set up.
 * @author Don Bhrayan M. Singh
 */

public class AnimationInitialize {
  /**
   * Begin initializes the JFrame and calls the necessary
   * elements needed for the sorting.
   * 
   * @param elem sets the number of elements(bars) to be made
   * @param delay passes the delay required by the user
   * @param type index of the Algorithm selection dropdown
   * @param name name taken from the dropdown to be displayed as the JFrame title
   */
  public static void begin(int elem, int delay, int type, String name) {
    int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    JFrame frame = new JFrame();
    DataBars panel = new DataBars();
    frame.add(panel, BorderLayout.CENTER);

    frame.setSize(910, 600);
    frame.setResizable(false);
    frame.setTitle(name);
    frame.setVisible(true);
    frame.setLocation(windowWidth/5, 100);
    /**
     * Initializes the array for the elements. Set as double
     * since the dynamic number requires us to adjust width according
     * to screen width vs number of elements. Rectangle2D allows us to do so
     * but we must use Double as width/height;
     */
    Double[] values = new Double[elem];
    /**
     * Loop to randomly generate element values
     * Set to 500 for the total size of the JPanel
     * so that the elements wont go out of bounds.
     */
    for (int i = 0; i < values.length; i++)
      values[i] = Math.random() * 500;

    /**
     * Creates an object of Algorithms passing the newly generated
     * array of elements, panel to be rendered onto, delay value and type
     * of sorting to be used.
     */
    final Algorithms sort = new Algorithms(values, panel, delay, type);
    /**
     * Thread object creates a new thread for the sort which
     * allows use to pause (Thread.sleep) the sorting without
     * causing the entire system to be affected. (Needed for smooth
     * animation and accurate timer)
     */
    Thread threadForSort = new Thread(sort);
    /**
     * Calls start method which is the polymorph method
     * of the Algorithm class that has implemented  Runnable.
     * This will look for the run() when thread is ready.
     */
    threadForSort.start();
    
  }
}

