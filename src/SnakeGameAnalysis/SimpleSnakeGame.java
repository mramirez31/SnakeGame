package SnakeGameAnalysis;

/**
 * class: SimpleSnakeGame
 * this is a course material developed for ITEC 2140 section 5
 * Spring 2023
 * version: 1.0
 * description: This is a simple snake game.
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class SimpleSnakeGame extends JPanel implements ActionListener {
    private static final int SIZE = 300; // 300 x 300 pixels for the game
    // board size
    private static final int UNIT_SIZE = 10; // 10 x 10 pixels for each unit
    private static final int GAME_UNITS = (SIZE * SIZE) / UNIT_SIZE; // 9000
    private static final int DELAY = 100; // 100 milliseconds for the delay
    private final int[] x = new int[GAME_UNITS]; // x coordinates of the snake
    private final int[] y = new int[GAME_UNITS]; // y coordinates of the snake
    private int bodyParts = 3; // initial body parts of the snake
    private int applesEaten; // number of apples eaten by the snake
    private int appleX; // x coordinate of the apple
    private int appleY; // y coordinate of the apple
    private char direction = 'R'; // initial direction of the snake (right)
    private boolean running = false; // whether the game is running or not
    private Timer timer; // timer for the game
    //private Random random; // random number generator

    /**
     * Constructor
     *
     */
    public SimpleSnakeGame() {
//       random = new Random();
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    /**
     * startGame method
     *
     */
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    /**
     * paintComponent method
     *
     * @param g the  Graphics class object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }


    /**
     * draw method
     *
     * @param g
     */
    public void draw(Graphics g) {
        if (running) {
            for (int i = 0; i < SIZE / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SIZE);
                g.drawLine(0, i * UNIT_SIZE, SIZE, i * UNIT_SIZE);
            }

            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    /**
     * newApple method
     *
     */
    public void newApple() {
        appleX = (int) (Math.random() * (SIZE / UNIT_SIZE)) * UNIT_SIZE;
        appleY = (int) (Math.random() * (SIZE / UNIT_SIZE)) * UNIT_SIZE;
    }



    /**
     * move method
     * description: The move() method is responsible for moving the snake's
     * body in the direction specified by the direction variable.
     * This method updates the position of each unit in the snake's body
     * by moving each unit to the position of the unit in front of it.
     */
    public void move() {
        /**
         * The for loop in the move() method starts at the end of the snake's
         * body and moves each unit one position forward until it reaches the
         * head of the snake. This ensures that each unit in the snake's body moves
         * to the position of the unit in front of it.
         */
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        /**
         * the switch statement updates the position of the head of the snake
         * based on the current direction of the snake. For example,
         * if the snake is moving up (direction is 'U'), the y coordinate of
         * the head is decreased by the size of each unit (UNIT_SIZE),
         * which makes the snake move up by one unit.
         */
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }


    /**
     * checkApple method
     *
     */
    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) { //check if the snake eats
            // an apple.
            bodyParts++; //then, snake's body will be longer. bodyParts are
            // increased by one if the snake eats an apple.
            applesEaten++; //it keeps track of the number of apples that the snake has eaten.
            newApple(); //after the snake eats an apple, then give another
            // apple.
        }
    }

    /**
     * checkCollisions method
     */
    public void checkCollisions() {
        // Check for collisions with the snake's body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        // Check for collisions with the left border
        if (x[0] < 0) {
            running = false;
        }

        // Check for collisions with the right border
        if (x[0] > SIZE) {
            running = false;
        }

        // Check for collisions with the top border
        if (y[0] < 0) {
            running = false;
        }

        // Check for collisions with the bottom border
        if (y[0] >= SIZE) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    /**
     * gameOver method
     * @param g
     */
    public void gameOver(Graphics g) {
        // Game Over text
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SIZE - metrics.stringWidth("Game Over")) / 2, SIZE / 2);
    }

    /**
     * actionPerformed method
     * @param e the event to be processed
     * description: The actionPerformed() method is part of the ActionListener
     *          interface and is called whenever an action event occurs,
     *          such as when a button is clicked or a timer goes off.
     * By using the ActionListener interface and the actionPerformed() method,
     *          the game loop is synchronized with the system clock and runs at
     *          a constant frame rate. This ensures that the game runs smoothly
     *          and consistently on different systems and platforms.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    /**
     * MyKeyAdapter class
     *
     */
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }

    /**
     * main method
     *
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame(); //creates a main window of the GUI

        SimpleSnakeGame gamePanel = new SimpleSnakeGame();

        frame.add(gamePanel); //the gamePanel is added to the JFrame object
        // so it appears inside the main window of the GUI.

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if the game
        // ends/exit, the window will be closed.

        frame.setTitle("Simple Snake Game");
        frame.setResizable(false); //the window is not resizable in this case.

        frame.pack(); //resizes the window to fit its contents,
        // which in this case is the gamePanel object.

        frame.setLocationRelativeTo(null);//centers the window on the screen

        frame.setVisible(true);//makes the window visible on the screen.
    }
}