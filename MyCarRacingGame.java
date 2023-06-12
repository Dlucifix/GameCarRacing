import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CarRacingGame extends JFrame implements KeyListener {

    private int carX;
    private int carY;
    private int roadY;
    private int score;
    private int fuelX;
    private int fuelY;
    private boolean hasFuel;

    public CarRacingGame() {
        setTitle("Car Racing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        carX = 180;
        carY = 300;
        roadY = 0;
        score = 0;
        fuelX = 0;
        fuelY = 0;
        hasFuel = false;

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.GREEN);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.GRAY);
        g.fillRect(50, roadY, 300, 400);

        g.setColor(Color.RED);
        g.fillRect(carX, carY, 40, 60);

        if (hasFuel) {
            g.setColor(Color.ORANGE);
            g.fillRect(fuelX, fuelY, 20, 20);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + score, 20, 20);
    }

    public void update() {
        roadY += 5;
        if (roadY >= getHeight())
            roadY = 0;

        if (hasFuel) {
            fuelY += 5;
            if (fuelY >= getHeight()) {
                fuelX = (int) (Math.random() * 340) + 50;
                fuelY = -100;
            }
        }

        checkCollision();

        repaint();
    }

    public void checkCollision() {
        Rectangle carRect = new Rectangle(carX, carY, 40, 60);
        Rectangle fuelRect = new Rectangle(fuelX, fuelY, 20, 20);

        if (carRect.intersects(fuelRect)) {
            score += 10;
            fuelX = (int) (Math.random() * 340) + 50;
            fuelY = -100;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            carX -= 10;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            carX += 10;
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    public static void main(String[] args) {
        CarRacingGame game = new CarRacingGame();
        while (true) {
            game.update();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
