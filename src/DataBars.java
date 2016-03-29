import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 * This class creates and paints the individual bars
 * that would represent the data/elements to be sorted
 *
 * Text labels for values of actual data are given if the
 * elements are at most 35. Anything bigger than that then
 * it would no longer fit the screen so I no longer implemented
 * it.
 * 
 * @author Don Bhrayan M. Singh
 */
public class DataBars extends JComponent {

  private Double pointer1;
  private int swap=0;
  private Double pointer2;
  private Double pointer3;
  private Double[] values;
  
  public synchronized void paintComponent(Graphics g) {
    if (values == null)
      return;
    Graphics2D graph = (Graphics2D) g;
    double width;
      width = (double)(900 / values.length);
    for (int i = 0; i < values.length; i++) {
      Double v = values[i];
      /**
       * Draws the bar for one by one. 
       */
      Rectangle2D bar = new Rectangle2D.Double(width * i, getYCoordinate(v), width, v);
      
      if (v == pointer2){
        graph.setColor(Color.red);
        graph.fill(bar);
        if(width<5){}
        else{
        graph.setColor(Color.black);
        graph.draw(bar);
        }
      }else if (v == pointer1){
        graph.setColor(Color.green);
        graph.fill(bar);
        if(width<5){}
        else{
        graph.setColor(Color.black);
        graph.draw(bar);
        }
      }else if (v == pointer3){
        graph.setColor(Color.blue);
        graph.fill(bar);
        if(width<5){}
        else{
        graph.setColor(Color.black);
        graph.draw(bar);
        }
      }else{
        graph.setColor(Color.white);
        graph.fill(bar);
        graph.setColor(Color.black);
        graph.draw(bar);
      }
      /**
       * Checks if the number of elements is less than 36. If so, allows
       * the program to add labels on the bottom for the actual value
       * of the array. If the elements are more than 36, the text will be too
       * distorted/unreadable so it will no longer be written.
       */
      if(values.length<36){
        int part = (int)v.doubleValue();
        int stringLen = (int)  
        graph.getFontMetrics().getStringBounds(String.valueOf(part), graph).getWidth();  
        int start = (int)width/2 - stringLen/2;  
        graph.drawString(String.valueOf(part), (int)(start+(width*i)), 565);
      }
    }
  }
  
  /**
   * setValues allows the Algorithm class to repaint the bars the array of
   * elements has been changed/swapped around. 
   * @param values array of elements
   * @param pointer1 currently affected element, paints it red (compare)
   * @param pointer2 2nd affected element, paints it green (swap)
   */
  public synchronized void setValues(Double[] values, Double pointer1, Double pointer2) {
    this.values = (Double[]) values.clone();
    this.pointer1 = pointer1;
    this.pointer2 = pointer2;
    repaint();
  }
  
  /**
   * An override method that allows us to add a third affected element (pointer 3)
   * used by sorting algos that need a third comparator for middle index or boundary
   * such as merge and shell sort
   * @param values array of elements
   * @param pointer1 currently affected element, paints it red (compare)
   * @param pointer2 2nd affected element, paints it green (swap)
   * @param pointer3 3rd affected element, paints it blue (boundary)
   */
  public synchronized void setValues(Double[] values, Double pointer1, Double pointer2, Double pointer3) {
    this.values = (Double[]) values.clone();
    this.pointer1 = pointer1;
    this.pointer2 = pointer2;
    this.pointer3 = pointer3;
    repaint();
  }
  
  /**
   * Calculates the bar Y coordinate since Rectangle2d
   * Paints from top-down and we need to generate it from bottom-up
   * @param v the height of the bar based on its value
   * @return returns the panel height(550) minus the height of the bar giving
   * us the ideal Y coordinate for the bar.
   */
  public double getYCoordinate(double v){
      return 550-v;
  }

    

}