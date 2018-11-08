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

    // freeze is for when we dont want anythign to happen.
    boolean freeze = false;

    //startScreen, switchScreen, and rulesScreen are for if we wnat to display those screens or not.
    public static boolean startScreen = true;
    public static boolean rulesScreen = false;
    boolean switchScreen = false;

    //alreadyPlaced determines whether a player has placed a token or not on their turn.
    //this is in main class so that mouseEvent handlers can access it w/o using class handles
    public static boolean alreadyPlaced = false;

    //if win is 0, nobody has won.
    //if win is 1, player 1 won.
    //if win is 2, player 2 won.
    int win = 0;

    //these screens are the two switchScreens.
    public static Image Screen3 = Toolkit.getDefaultToolkit().getImage("./assets/Screen3.jpg");
    public static Image Screen4 = Toolkit.getDefaultToolkit().getImage("./assets/Screen4.jpeg");

    //contrary to its name, this is the startScreen.
    public static Image Loadingscreen = Toolkit.getDefaultToolkit().getImage("./assets/LoadingScreen1.jpg");

    //going to put rules screen here
    
    //this is the frame. it is outside of main so that it can be accessed in other methods.
    static Battleship frame;

    public static void main(String[] args) {
        //frame settings. DO NOT CHANGE
        frame = new Battleship();
        frame.setSize(Window.WINDOW_WIDTH_SMALL, Window.WINDOW_HEIGHT_SMALL);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Battleship() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (!freeze && !switchScreen) { //prevents things from happening
                    if (e.BUTTON1 == e.getButton()) { //left click

                        int xpos = e.getX(); //finding x and y pos of mouse pointer
                        int ypos = e.getY();

                        if (startScreen) { //clicking "start" box will make the screen big.
                            if (xpos > 13 && xpos < 13 + 240 && ypos > Window.WINDOW_HEIGHT_SMALL - 60 && ypos < Window.WINDOW_HEIGHT_SMALL - 60 + 45) {
                                switchSize();
                            }
                            else if (xpos > 13 + 240 && xpos < 13 + 480 && ypos > Window.WINDOW_HEIGHT_SMALL - 60 && ypos < Window.WINDOW_HEIGHT_SMALL - 60 + 45) {
                                switchScreen = true;
                            }
                            else if (xpos > 13 + 240 && xpos < 13 + 4800 && ypos > Window.WINDOW_HEIGHT_SMALL - 60 && ypos < Window.WINDOW_HEIGHT_SMALL - 60 + 45) {
                                rulesScreen = true;
                            }
                        } else if (!switchScreen && !startScreen) { //add token when placing board is active                                                     
                            if (Board.AddTokenPixel(e.getX() - Window.getX(0),
                                    e.getY() - Window.getY(0), alreadyPlaced)) {
                                alreadyPlaced = true;
                            }
                        }

                    }
                }
                if (e.BUTTON3 == e.getButton()) { // right click

                    int x = e.getX(); // finding x and y pos of mouse pointer
                    int y = e.getY();
                    if (x > Window.getWidth2() + Window.getXBorder() - 100 && x < Window.getWidth2() + Window.getXBorder() && y > 0 && y < 70) { //"confirm" bounding box
                        if (switchScreen) {
                            switchScreen = false; //leaving switchScreen
                        } else {
                            if (Board.confirm()) { //if the player HAS placed a token, turn switchScreen ON. otherwise, do nothing
                                switchScreen = true;
                                Player.switchTurn();
                                alreadyPlaced = false;
                            }

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
                    switchSize(); // FOR TESTING
                } else if (e.VK_DOWN == e.getKeyCode()) {
                    switchScreen = !switchScreen; //FOR TESTING
                } else if (e.VK_LEFT == e.getKeyCode()) {

                } else if (e.VK_RIGHT == e.getKeyCode()) {

                } else if (e.VK_ESCAPE == e.getKeyCode()) {
                    //reset(); //WARNING! CAUSES NULLPOINTER
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

        Board.Draw(g); //drawing grid and tokens.

        //drawing scores
        g.setColor(Player.getPlayers()[0].getColor());
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        g.drawString("P1 score: " + Player.getPlayers()[0].getPoints(), 50, 60);
        g.setColor(Player.getPlayers()[1].getColor());
        g.drawString("P2 score: " + Player.getPlayers()[1].getPoints(), 175, 60);

        //drawing who's turn it is
        g.setColor(Player.getCurrentPlayer().getColor());
        g.drawString("Current player = " + Player.getCurrentPlayer().toString(), 320, 60);

        //drawing "confirm" bounding box
        g.setColor(Color.gray);
        g.fillRect(Window.getWidth2() + Window.getXBorder() - 100, 0, 100, 70);
        g.setColor(Color.black);
        g.drawString("CONFIRM", Window.getWidth2() + Window.getXBorder() - 99, 60);

        //drawing switchScreens
        if (switchScreen) {
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            g.setColor(Color.black);
            g.fillRect(Window.getX(0), Window.getY(0), Window.getWidth2() + 1, Window.getHeight2() + 1);
            if (Player.getCurrentPlayer() == Player.getPlayers()[0]) {
                g.drawImage(Screen4, Window.getX(0), Window.getY(0), Window.getWidth2() + 1, Window.getHeight2() + 1, this);
                g.setColor(Color.black);
                g.drawString("look away, switch players", Window.getWidth2() / 2 - 200, Window.getHeight2() / 2 - 100);
            } else {
                g.drawImage(Screen3, Window.getX(0), Window.getY(0), Window.getWidth2() + 1, Window.getHeight2() + 1, this);
                g.setColor(Color.black);
                g.drawString("look away,", 50, Window.getHeight2() / 2 - 100);
                g.drawString("switch players", 425, Window.getHeight2() / 2 - 100);

            }
        }
        if(win == 1) {
            g.setColor(Color.black);
            g.setFont(new Font("Comic Sans MS",Font.PLAIN,45));
            g.drawString("Player 1 Wins",Window.getWidth2()/2,Window.getHeight2()/2);
        }
        else if(win == 2) {
            g.setColor(Color.black);
            g.setFont(new Font("Comic Sans MS",Font.PLAIN,45));
            g.drawString("Player 2 Wins",Window.getWidth2()/2,Window.getHeight2()/2);            
        }
        //drawing startScreen
        if (startScreen) {
            g.drawImage(Loadingscreen, 0, Window.getYBorder(), Window.WINDOW_WIDTH_SMALL, Window.WINDOW_HEIGHT_SMALL - Window.getYBorder(), this);
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

        //Checking for wins
        win = Board.checkWin();

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

    public void switchSize() { //Changes the size from big to small and inverts startScreen
        startScreen = !startScreen;

        if (!startScreen) {
            frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation(dim.width / 2 - this.getSize().width / 2,0);
            
        } else {
            frame.setSize(Window.WINDOW_WIDTH_SMALL, Window.WINDOW_HEIGHT_SMALL);
            frame.setLocationRelativeTo(null);

        }
    }

}
