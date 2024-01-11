import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//This method contains the functionality for the Game screen. 

public class GameScreen extends JFrame {
  //Creating all of the buttons and text for the screen 
  JTextField recipe;
  JButton olive = new JButton();
  JButton anchovie = new JButton();
  JButton bacon = new JButton();
  JButton pepperoni = new JButton();
  JButton mushroom = new JButton();
  JTextField congrats;
  JTextArea nameInput = new JTextArea();
  JLabel name = new JLabel("Enter Name Here --->"); 
  JButton recipeButton;
  //Creating the variables for the timer
  int time = 10;
  Timer timer = null;
  int delay = 1000;
  JLabel timerLabel = new JLabel("00:10");
  JButton stopButton = new JButton("DONE");
  int remainingTime = 10;
  //Creating the images to add to the top of the pizza
  private JLabel olivesLabel = new JLabel();;
  private JLabel anchovieLabel = new JLabel();;
  private JLabel baconLabel = new JLabel();;
  private JLabel pepperoniLabel = new JLabel();;
  private JLabel mushroomLabel = new JLabel();;
  private List<String> expectedToppings = new ArrayList<>();
  private List<String> selectedToppings = new ArrayList<>();
  boolean recipeDisplayed = false;
  boolean anchovieSelected = false;
  boolean baconSelected = false;
  boolean pepperoniSelected = false;
  boolean mushroomSelected = false;
  boolean oliveSelected = false;

  //This method loads the background image and sets the buttons and text fields to be in the correct spot
  public GameScreen() throws IOException {
    setLayout(null);

    // Create custom JPanel for the background image
    JPanel backgroundPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
          // load background
          Image backgroundImage = Toolkit.getDefaultToolkit().getImage("Game.jpeg"); // uses Abstract Window
                                                                                     // Toolkit(AWT) package
          g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };

    // Set layout to null for manual positioning
    backgroundPanel.setLayout(null);

    /* Setting the buttons and the correct locations */
    recipeButton = new JButton("Get Recipe!");
    Dimension buttonSize = new Dimension(150, 95);
    recipeButton.setSize(buttonSize);
    int x = 940;
    int y = 150;
    recipeButton.setLocation(x, y);
    recipeButton.setVisible(true);
    Border boldBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
    recipeButton.setBorder(boldBorder);
    Font buttonFont = new Font("Georgia", Font.BOLD, 24);
    recipeButton.setFont(buttonFont);

    Dimension button4Size = new Dimension(150, 75);
    stopButton.setSize(button4Size);
    int x4 = 610;
    int y4 = 15;
    stopButton.setLocation(x4, y4);
    Font diffFont = new Font("Arial", Font.BOLD, 24);
    stopButton.setFont(diffFont);
    Color REDISH = new Color(139, 0, 0);
    stopButton.setForeground(REDISH);

    Dimension nameSize = new Dimension(150, 50);
    nameInput.setSize(nameSize);
    int x5 = 310; 
    int y5 = 20; 
    nameInput.setLocation(x5, y5);

    name.setSize(nameSize);
    name.setLocation(150,20);


    Dimension button3Size = new Dimension(150, 75);
    timerLabel.setSize(button3Size);
    int x3 = 490;
    int y3 = 15;
    timerLabel.setForeground(Color.RED);
    Font timerFont = new Font("Arial", Font.BOLD, 24);
    timerLabel.setFont(timerFont);
    timerLabel.setLocation(x3, y3);

    backgroundPanel.add(stopButton);
    backgroundPanel.add(recipeButton);
    backgroundPanel.add(timerLabel);
    backgroundPanel.add(name);
    backgroundPanel.add(nameInput);
    
    /* TOPPINGS BUTTONS AND IMAGES */
    JPanel toppingsF = new JPanel();
    toppingsF.setLayout(new GridLayout(2, 2));
    toppingsF.add(anchovie);
    toppingsF.add(bacon);
    toppingsF.add(pepperoni);
    toppingsF.add(mushroom);
    Dimension topPanelSize = new Dimension(400, 350);
    toppingsF.setSize(topPanelSize);
    toppingsF.setLocation(710, 330);
    toppingsF.setVisible(true);

    anchovie.setOpaque(false);
    anchovie.setContentAreaFilled(false);
    anchovie.setBorderPainted(false);

    bacon.setOpaque(false);
    bacon.setContentAreaFilled(false);
    bacon.setBorderPainted(false);

    pepperoni.setOpaque(false);
    pepperoni.setContentAreaFilled(false);
    pepperoni.setBorderPainted(false);

    mushroom.setOpaque(false);
    mushroom.setContentAreaFilled(false);
    mushroom.setBorderPainted(false);

    toppingsF.setOpaque(false);

    // have to create separate olive button
    Dimension oliveSize = new Dimension(150, 150);
    olive.setSize(oliveSize);
    olive.setLocation(730, 170);
    olive.setVisible(true);
    olive.setOpaque(false);
    olive.setContentAreaFilled(false);
    olive.setBorderPainted(false);

    stopButton.setVisible(true);
    timerLabel.setVisible(true);

    // Adding everything on top of background
    backgroundPanel.add(toppingsF);
    backgroundPanel.add(olive);

    // Adding the JLables (topping images)
    backgroundPanel.add(anchovieLabel);
    backgroundPanel.add(olivesLabel);
    backgroundPanel.add(pepperoniLabel);
    backgroundPanel.add(mushroomLabel);
    backgroundPanel.add(baconLabel);

    /* TIMER FUNCTIONALITY */
    timer = new Timer(1000, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Handling case of time running out
        if (remainingTime <= 0) {
          ((Timer) e.getSource()).stop();
          JOptionPane.showMessageDialog(null, "Oh no! You ran out of time. Try again!");
          resetGame();
          // Countdown
        } else {
          timerLabel.setText(getRemainingTime());
          remainingTime--;
        }
      }
    });

    /* BUTTON FUNCTIONALITY FOR THE TOPPINGS, REPICIE, AND TIMER */
    recipeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        expectedToppings = generateRandomPizza();
        JOptionPane.showMessageDialog(null, "Expected Toppings: " + expectedToppings);
        recipeDisplayed = true; // Ensure recipe is displayed
        if (!timer.isRunning()) {
          timer.start(); // Start the timer
        }
      }
    });

    stopButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.stop();
        if (recipeDisplayed) {
          // if pizza is correct
          if (checkPizza(selectedToppings)) {
            JOptionPane.showMessageDialog(null, "Congrats " + nameInput.getText() +" you completed the pizza in under 10 seconds!");
            // if incorrect pizza
          } else {
            JOptionPane.showMessageDialog(null, "Not quite "+ nameInput.getText() +" try again with a new recipe!");
          }
          resetGame();
          // if out of time
        } else {
          JOptionPane.showMessageDialog(null, "Oh no! You ran out of time. Try again " + nameInput.getText() +"!");
          resetGame();
        }
      }
    });

    anchovie.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // If button is pressed twice, remove topping
        if (anchovieSelected) {
          // Remove the image
          anchovieLabel.setVisible(false);
          // Remove the topping from the selected list
          selectedToppings.remove("Anchovies");
          // Set the selection status to false
          anchovieSelected = false;
          // If button is only selected once
        } else {
          // Load the image
          ImageIcon anchovieImage = new ImageIcon("anchovies.png");
          anchovieSelected = true;
          selectedToppings.add("Anchovies");
          // Create a JLabel to display the image
          anchovieLabel = new JLabel(anchovieImage);
          int width = anchovieImage.getIconWidth();
          int height = anchovieImage.getIconHeight();

          // Set the location
          anchovieLabel.setBounds(50, 150, width, height);

          // Add the JLabel to the content pane
          getContentPane().add(anchovieLabel);

          // Repaint the frame to update the display
          revalidate();
          repaint();
        }
      }
    });

    bacon.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // If button is pressed twice, remove topping
        if (baconSelected) {
          // Remove the image
          baconLabel.setVisible(false);
          // Remove the topping from the selected list
          selectedToppings.remove("Bacon");
          // Set the selection status to false
          baconSelected = false;
        } else {
          // Load the image
          ImageIcon baconImage = new ImageIcon("bacon.png");
          baconSelected = true;
          selectedToppings.add("Bacon");
          // Create a JLabel to display the image
          baconLabel = new JLabel(baconImage);
          int width = baconImage.getIconWidth();
          int height = baconImage.getIconHeight();

          // Set the location
          baconLabel.setBounds(40, 150, width, height);

          // Add the JLabel to the content pane
          getContentPane().add(baconLabel);

          // Repaint the frame to update the display
          revalidate();
          repaint();
        }
      }
    });

    mushroom.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // If button is pressed twice, remove topping
        if (mushroomSelected) {
          // Remove the image
          mushroomLabel.setVisible(false);
          // Remove the topping from the selected list
          selectedToppings.remove("Mushrooms");
          // Set the selection status to false
          mushroomSelected = false;
        } else {
          // Load the image
          ImageIcon mushroomImage = new ImageIcon("mushroom.png");
          mushroomSelected = true;
          selectedToppings.add("Mushrooms");
          // Create a JLabel to display the image
          mushroomLabel = new JLabel(mushroomImage);
          int width = mushroomImage.getIconWidth();
          int height = mushroomImage.getIconHeight();

          // Set the location
          mushroomLabel.setBounds(45, 150, width, height);

          // Add the JLabel to the content pane
          getContentPane().add(mushroomLabel);

          // Repaint the frame to update the display
          revalidate();
          repaint();
        }
      }
    });

    pepperoni.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // If button is pressed twice, remove topping
        if (pepperoniSelected) {
          // Remove the image
          pepperoniLabel.setVisible(false);
          // Remove the topping from the selected list
          selectedToppings.remove("Pepperoni");
          // Set the selection status to false
          pepperoniSelected = false;
        } else {
          // Load the image
          ImageIcon pepperoniImage = new ImageIcon("pepperoni.png");
          pepperoniSelected = true;
          selectedToppings.add("Pepperoni");
          // Create a JLabel to display the image
          pepperoniLabel = new JLabel(pepperoniImage);
          int width = pepperoniImage.getIconWidth();
          int height = pepperoniImage.getIconHeight();

          // Set the location
          pepperoniLabel.setBounds(50, 150, width, height);

          // Add the JLabel to the content pane
          getContentPane().add(pepperoniLabel);

          // Repaint the frame to update the display
          revalidate();
          repaint();
        }
      }
    });

    olive.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // If button is pressed twice, remove topping
        if (oliveSelected) {
          // Remove the image
          olivesLabel.setVisible(false);
          // Remove the topping from the selected list
          selectedToppings.remove("Olives");
          // Set the selection status to false
          oliveSelected = false;
        } else {
          // Load the image
          ImageIcon olivesImage = new ImageIcon("olives.png");
          oliveSelected = true;
          selectedToppings.add("Olives");
          // Create a JLabel to display the image
          olivesLabel = new JLabel(olivesImage);
          int width = olivesImage.getIconWidth();
          int height = olivesImage.getIconHeight();

          // Set the location
          olivesLabel.setBounds(40, 150, width, height);

          // Add the JLabel to the content pane
          getContentPane().add(olivesLabel);

          // Repaint the frame to update the display
          revalidate();
          repaint();
        }
      }
    });

    // Add to GUI
    setContentPane(backgroundPanel);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
    setSize(1300, 800);
    setLocation(100, 100);
  }

  /* FUNCTIONALITY OF GAME (RECIPES AND PIZZA) */

  // This method generates a random selection of toppings
  private List<String> generateRandomPizza() {
    String[] allToppings = { "Anchovies", "Bacon", "Pepperoni", "Mushrooms", "Olives" };
    // Clear the existing expected toppings list and create new list
    expectedToppings.clear();
    List<String> expectedToppingsList = new ArrayList<>();
    // Generate a random number of toppings to include in the pizza
    int numToppings = (int) (Math.random() * allToppings.length) + 1;

    // Loop to randomly select toppings without repetition
    for (int i = 0; i < numToppings; i++) {
      // Generate a random index to select a topping and get that topping at index
      int randomIndex = (int) (Math.random() * allToppings.length);
      String topping = allToppings[randomIndex];
      // If topping is already in list, ignore
      if (!expectedToppingsList.contains(topping)) {
        expectedToppingsList.add(topping);
      }
    }

    return expectedToppingsList;
  }

  private boolean checkPizza(List<String> selectedToppings) {
    // Check if selected toppings are the same as expected
    return selectedToppings.equals(expectedToppings);
  }

  // This is the implementation that allows for the timer to countdown
  public String getRemainingTime() {
    int minutes = remainingTime / 60;
    int seconds = remainingTime % 60;
    // Formats the time with leading zeros and returns the result
    return String.format("%02d:%02d", minutes, seconds);
  }

  //This method resets the game and clears everything 
  private void resetGame() {
    resetTimer();
    expectedToppings.clear();
    selectedToppings.clear();
    resetToppingImages();
    recipeDisplayed = false;
    oliveSelected = false;
    pepperoniSelected = false;
    mushroomSelected = false;
    baconSelected = false;
    anchovieSelected = false;
  }
  
  //This method resets the timer to 10 seconds
  private void resetTimer() {
    remainingTime = 10;
    timerLabel.setText("00:10");
  }

  //This method clears all of the toppings off the pizza 
  private void resetToppingImages() {
    anchovieLabel.setVisible(false);
    baconLabel.setVisible(false);
    pepperoniLabel.setVisible(false);
    mushroomLabel.setVisible(false);
    olivesLabel.setVisible(false);
  }

  
  public static void main(String[] args) throws IOException {
    GameScreen gameScreen = new GameScreen();
    gameScreen.setVisible(true);
  }
}

/*
 * CITATIONS
 * timer:
 * https://stackoverflow.com/questions/22366890/java-timer-action-listener
 * https://stackoverflow.com/questions/27154621/stopping-java-timer
 * 
 * background:
 * https://stackoverflow.com/questions/19125707/simplest-way-to-set-image-as-
 * jpanel-background
 */
