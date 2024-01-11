import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//This method creates the start screen for the game 

public class ParentGUI extends JFrame {
  //creates the buttons on the start screen 
  private JButton startButton;
  private JButton infoButton;
  private GameScreen gameScreen;
  private InfoScreen infoScreen;

  public ParentGUI() throws IOException {

    setLayout(null);

    // Create custom JPanel for the background image
    JPanel backgroundPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
          // load background
          Image backgroundImage = Toolkit.getDefaultToolkit().getImage("Start.jpeg"); // uses Abstract Window
                                                                                      // Toolkit(AWT) package
          g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };

    // Set layout to null for manual positioning
    backgroundPanel.setLayout(null);

    /* SETTING UP BUTTONS */
    startButton = new JButton();
    infoButton = new JButton();
    Dimension buttonSize = new Dimension(350, 175);
    Dimension infoSize = new Dimension(150, 75);
    startButton.setSize(buttonSize);
    infoButton.setSize(infoSize);

    // Set the location of the button
    int x = 475;
    int y = 460;
    int x2 = 1020;
    int y2 = 590;

    //set the location for both of the buttons and makes them visible on the start screen 
    startButton.setLocation(x, y);
    infoButton.setLocation(x2, y2);
    startButton.setVisible(true);
    infoButton.setVisible(true);

    // Makes the buttons transparent on the screen 
    startButton.setContentAreaFilled(false);
    startButton.setBorderPainted(false);
    infoButton.setContentAreaFilled(false);
    infoButton.setBorderPainted(false);

    // Add the buttons to the GUI
    backgroundPanel.add(startButton);
    backgroundPanel.add(infoButton);

    /* BUTTON FUNCTIONALITY */

    // When start button is pressed, changes to game screen
    startButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          gameScreen = new GameScreen(); //opens the game screen 
          startButton.setVisible(false); 
          infoButton.setVisible(false);
          // creates new window and closes start screen 
          dispose();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });
    // When info button is pressed, changes to info screen
    infoButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          infoScreen = new InfoScreen(); //opends the info screen 
          startButton.setVisible(true);
          infoButton.setVisible(false);
          // creates new window and closes start screen 
          dispose();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });

    // GUI set up
    setContentPane(backgroundPanel);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
    setSize(1300, 800);
    setLocation(100, 100);
  }

  public static void main(String[] args) throws IOException {
    new ParentGUI();
  }

}

/*
 * CITATIONS
 * 
 * background:
 * https://stackoverflow.com/questions/19125707/simplest-way-to-set-image-as-
 * jpanel-background
 * buttons:
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/plaf/basic/
 * BasicButtonUI.html
 * https://stackoverflow.com/questions/6734171/how-do-i-set-the-r-g-b-and-alpha-
 * components-of-a-color
 */
