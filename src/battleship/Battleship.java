package battleship;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.Color;

public class Battleship extends JFrame implements Runnable {

    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
//hi
    boolean freeze = false;

    public static boolean startScreen = true;
    
    boolean switchScreen = false;
    public static boolean alreadyPlaced = false;
    int win = 0;

    public static Image Screen1 = Toolkit.getDefaultToolkit().getImage("./assets/Screen1.jpg");
    public static Image Screen2 = Toolkit.getDefaultToolkit().getImage("./assets/Screen2.jpg");

    static Battleship frame;

    public static void main(String[] args) {
        frame = new Battleship();
        if (startScreen){
            frame.setSize(Window.WINDOW_WIDTH_SMALL, Window.WINDOW_HEIGHT_SMALL);
        }
        else
            frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public Battleship() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (!freeze && !switchScreen) {
                    if (e.BUTTON1 == e.getButton()) {

                        e.getX();
                        e.getY();
                        if (Board.AddTokenPixel(e.getX() - Window.getX(0),
                                e.getY() - Window.getY(0), alreadyPlaced)) {
                            alreadyPlaced = true;
                        }

                    }
                }

                if (e.BUTTON3 == e.getButton()) {
                    if (freeze) {
                        return;
                    }

                    int x = e.getX();
                    int y = e.getY();
                    if (x > Window.getWidth2() + Window.getXBorder() - 100 && x < Window.getWidth2() + Window.getXBorder() && y > 0 && y < 70) {
                        if (switchScreen) {
                            switchScreen = false;
                        } else {
                            
                            if (Board.confirm()){switchScreen = true; Player.switchTurn(); alreadyPlaced = false;}
                            
                        }

                    }

                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {

                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {

                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {

                if (freeze || switchScreen) {
                    return;
                }

                if (e.VK_UP == e.getKeyCode()) {
                    startScreen = !startScreen;
                } else if (e.VK_DOWN == e.getKeyCode()) {

                } else if (e.VK_LEFT == e.getKeyCode()) {

                } else if (e.VK_RIGHT == e.getKeyCode()) {

                } else if (e.VK_ESCAPE == e.getKeyCode()) {
                    reset();
                }
                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////

    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////

    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////

    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
//fill background

        g.setColor(Color.black);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        g.setColor(Color.black);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.blue);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        Board.Draw(g);
        g.setColor(Player.getPlayers()[0].getColor());
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        g.drawString("P1 score: " + Player.getPlayers()[0].getPoints(), 50, 60);
        g.setColor(Player.getPlayers()[1].getColor());
        g.drawString("P2 score: " + Player.getPlayers()[1].getPoints(), 175, 60);

        g.setColor(Player.getCurrentPlayer().getColor());
        g.drawString("Current player = " + Player.getCurrentPlayer().toString(), 320, 60);

        g.setColor(Color.gray);
        g.fillRect(Window.getWidth2() + Window.getXBorder() - 100, 0, 100, 70);
        g.setColor(Color.black);
        g.drawString("CONFIRM", Window.getWidth2() + Window.getXBorder() - 99, 60);
        
         g.setColor(Color.gray);
        g.fillRect(Window.getWidth2()/2 - 200, Window.getHeight2()/2, 200, 75);
        
         g.setColor(Color.gray);
        g.fillRect(Window.getWidth2()/2 + 100, Window.getHeight2()/2, 200, 75);
//        g.setColor(Color.black);
//        g.drawString("CONFIRM", Window.getWidth2() + Window.getXBorder() - 99, 60);

//        if (win == 1) {
//            g.setColor(Color.gray);
//            g.fillRect(Window.getWidth2() / 2 - 100, Window.getHeight2() / 2 - 75, 200, 100);
//            g.setColor(Player.getPlayers()[0].getColor());
//            g.setFont(new Font("Arial", Font.PLAIN, 45));
//            g.drawString(" P1 WIN", Window.getWidth2() / 2 - 100, Window.getHeight2() / 2);
//        } else if (win == 2) {
//            g.setColor(Color.gray);
//            g.fillRect(Window.getWidth2() / 2 - 100, Window.getHeight2() / 2 - 75, 200, 100);
//            g.setColor(Player.getPlayers()[1].getColor());
//            g.setFont(new Font("Arial", Font.PLAIN, 45));
//            g.drawString(" P2 WIN", Window.getWidth2() / 2 - 100, Window.getHeight2() / 2);
//        }
        if (switchScreen) {
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            g.setColor(Color.black);
            //g.fillRect(Window.getX(0), Window.getY(0), Window.getWidth2()+1, Window.getHeight2()+1);
            if (Player.getCurrentPlayer() == Player.getPlayers()[0]) {
                g.drawImage(Screen1, Window.getX(0), Window.getY(0), Window.getWidth2(), Window.getHeight2(), this);
                g.setColor(Color.black);
                g.drawString("look away, switch players", Window.getWidth2() / 2 - 100, Window.getHeight2() / 2);
            } else {
                g.drawImage(Screen2, Window.getX(0), Window.getY(0), Window.getWidth2(), Window.getHeight2(), this);

                g.setColor(Color.black);
                g.drawString("look away, switch players", Window.getWidth2() / 2 - 100, Window.getHeight2() / 2 - 200);
            }
        }
        gOld.drawImage(image, 0, 0, null);
    }

////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = .1;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }

/////////////////////////////////////////////////////////////////////////
    public void reset() {

        win = 0;
        Board.Reset();
        Player.Reset();

    }
/////////////////////////////////////////////////////////////////////////

    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            reset();
        }

        
        //System.out.println("here i am in gitHub");
        // hello world;
        
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////

    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }

}
