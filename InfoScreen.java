import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoScreen extends JFrame {
    JButton startButton;
    private GameScreen gameScreen;

    public InfoScreen() throws IOException {
        setLayout(null);

        // Create custom JPanel for the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // load background
                    Image backgroundImage = Toolkit.getDefaultToolkit().getImage("instructions.jpeg"); // uses Abstract
                                                                                                       // Window
                                                                                                       // Toolkit(AWT)
                                                                                                       // package
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Set layout to null for manual positioning
        backgroundPanel.setLayout(null);

        startButton = new JButton("CLICK HERE TO START!");
        Font diffFont = new Font("Georgia", Font.BOLD, 24);
        startButton.setFont(diffFont);
        Color startColor = new Color(60, 125, 64);
        startButton.setForeground(startColor);

        int x = 425;
        int y = 490;
        Dimension buttonSize = new Dimension(350, 175);
        startButton.setLocation(x, y);
        startButton.setSize(buttonSize);
        startButton.setVisible(true);

        // Add button to background
        backgroundPanel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gameScreen = new GameScreen();
                    startButton.setVisible(false);
                    dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // GUI setup
        setContentPane(backgroundPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(1300, 800);
        setLocation(100, 100);
    }

    public static void main(String[] args) throws IOException {
        new InfoScreen();
    }
}
