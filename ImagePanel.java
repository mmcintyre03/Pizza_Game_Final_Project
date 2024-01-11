import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//This class allows the images to be placed on the background images 
class ImagePanel extends JPanel{
  private Image img; 

  public ImagePanel(String img){
    this(new ImageIcon(img).getImage()); //getting the image that is being passed through 
  }

  public ImagePanel(Image img){ 
    this.img = img; 
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null)); //sets the image width and height to null 
    setPreferredSize(size); //sets the size to be the inputed size 
    setMinimumSize(size); 
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }

  public void paintComponent(Graphics g){
    g.drawImage(img, 0, 0, null);
  }
}
