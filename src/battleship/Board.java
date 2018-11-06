package battleship;

import java.awt.*;
import java.io.File;

public class Board {

    public final static int NUM_ROWS = 10;
    public final static int NUM_COLUMNS = 10;
    private static Battleship mainClassInst;
    private static Token p1Ships[][] = new Token[NUM_ROWS][NUM_COLUMNS];
    private static Token p1Shots[][] = new Token[NUM_ROWS][NUM_COLUMNS];
    private static Token p2Ships[][] = new Token[NUM_ROWS][NUM_COLUMNS];
    private static Token p2Shots[][] = new Token[NUM_ROWS][NUM_COLUMNS];

    public static Image oceanBG = Toolkit.getDefaultToolkit().getImage("./assets/Ocean_Background.jpg");
    public static int mostRecentRow;
    public static int mostRecentCol;

    public static void Reset() {

        for (int zi = 0; zi < NUM_ROWS; zi++) {
            for (int zx = 0; zx < NUM_COLUMNS; zx++) {
                p1Ships[zi][zx] = null;
                p2Ships[zi][zx] = null;
                p1Shots[zi][zx] = null;
                p2Shots[zi][zx] = null;

            }
        }
    }

//    public static Token[][] getBoard() {
//        return board;
//    }
    public static int getRow(int y) {
        int currRow = 0;
        int ydelta = Window.getHeight2() / 2 / NUM_ROWS;
        int currYVal = ydelta;
        while (y > currYVal) {
            currRow++;
            currYVal += ydelta;
        }
        return currRow;
    }

    public static int getCol(int x) {
        int currCol = 0;
        int xdelta = Window.getWidth2() / NUM_COLUMNS;
        int currXVal = xdelta;
        while (x > currXVal) {
            currCol++;
            currXVal += xdelta;
        }
        return currCol;
    }

    public static boolean AddTokenPixel(int xpixel, int ypixel, boolean alreadyPlaced) {

        if (xpixel < 0 || xpixel > Window.getWidth2() || ypixel < 0
                || ypixel > Window.getHeight2() / 2) {
            return false;
        }

        int currRow = getRow(ypixel);
        int currCol = getCol(xpixel);

        if (Player.getCurrentPlayer() == Player.getPlayers()[0]) {
            if (p1Shots[currRow][currCol] == null && !alreadyPlaced) {
                p1Shots[currRow][currCol] = new Token(Player.getCurrentPlayer().getColor());

                mostRecentRow = currRow;
                mostRecentCol = currCol;
                return true;
            } else if (p1Shots[currRow][currCol] == null ) {
                if (!p1Shots[mostRecentRow][mostRecentCol].isUnremovable())
                    p1Shots[mostRecentRow][mostRecentCol] = null;
                p1Shots[currRow][currCol] = new Token(Player.getCurrentPlayer().getColor());

                mostRecentRow = currRow;
                mostRecentCol = currCol;
                return true;
            }
        } else if (Player.getCurrentPlayer() == Player.getPlayers()[1]) {
            if (p2Shots[currRow][currCol] == null && !alreadyPlaced) {
                p2Shots[currRow][currCol] = new Token(Player.getCurrentPlayer().getColor());

                mostRecentRow = currRow;
                mostRecentCol = currCol;
                return true;
            } else if (p2Shots[currRow][currCol] == null ) {
                if (!p2Shots[mostRecentRow][mostRecentCol].isUnremovable())
                    p2Shots[mostRecentRow][mostRecentCol] = null;
                p2Shots[currRow][currCol] = new Token(Player.getCurrentPlayer().getColor());

                mostRecentRow = currRow;
                mostRecentCol = currCol;
                return true;
            }
        }
        return false;
    }
    
    public static boolean confirm(){
        if (Player.getCurrentPlayer() == Player.getPlayers()[0]){
            if (mainClassInst.alreadyPlaced){
            p1Shots[mostRecentRow][mostRecentCol].setUnremovable();
            return true;
            }
        }
        else if (mainClassInst.alreadyPlaced){
            p2Shots[mostRecentRow][mostRecentCol].setUnremovable();
            return true;
        }
        
        
        return false;
    }

    public static void Draw(Graphics2D g) {
//Calculate the width and height of each board square.
        int ydelta = Window.getHeight2() / 2 / NUM_ROWS;
        int xdelta = Window.getWidth2() / NUM_COLUMNS;
        g.drawImage(oceanBG, Window.getX(0), Window.getY(0), Window.getWidth2(), Window.getHeight2(), mainClassInst);

        //Draw the grid.
        if (Player.getCurrentPlayer() == Player.getPlayers()[0]) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.red);
        }

        for (int zi = 1; zi < NUM_ROWS; zi++) {
            g.drawLine(Window.getX(0), Window.getY(zi * ydelta),
                    Window.getX(Window.getWidth2()), Window.getY(zi * ydelta));
        }

        for (int zi = 1; zi < NUM_COLUMNS; zi++) {
            g.drawLine(Window.getX(zi * xdelta), Window.getY(0),
                    Window.getX(zi * xdelta), Window.getY(Window.getHeight2() / 2));
        }

        g.drawLine(Window.getX(0), Window.getY(Window.getHeight2() / 2), Window.getX(Window.getWidth2()), Window.getY(Window.getHeight2() / 2));

        g.setColor(Color.black);

        for (int zi = 1; zi < NUM_ROWS; zi++) {
            g.drawLine(Window.getX(0), Window.getY(zi * ydelta + Window.getHeight2() / 2),
                    Window.getX(Window.getWidth2()), Window.getY(zi * ydelta + Window.getHeight2() / 2));
        }

        for (int zi = 1; zi < NUM_COLUMNS; zi++) {
            g.drawLine(Window.getX(zi * xdelta), Window.getY(Window.getHeight2() / 2),
                    Window.getX(zi * xdelta), Window.getY(Window.getHeight2()));
        }

        g.drawLine(Window.getX(0), Window.getY(Window.getHeight2()), Window.getX(Window.getWidth2()), Window.getY(Window.getHeight2()));

//Draw the tokens.    
        if (Player.getCurrentPlayer() == Player.getPlayers()[0]) {
            for (int zi = 0; zi < NUM_ROWS; zi++) {
                for (int zx = 0; zx < NUM_COLUMNS; zx++) {
                    if (p1Shots[zi][zx] != null) {
                        p1Shots[zi][zx].draw(g, zi, zx, xdelta, ydelta);
                    }
                }
            }
        } else {
            for (int zi = 0; zi < NUM_ROWS; zi++) {
                for (int zx = 0; zx < NUM_COLUMNS; zx++) {
                    if (p2Shots[zi][zx] != null) {
                        p2Shots[zi][zx].draw(g, zi, zx, xdelta, ydelta);
                    }
                }
            }
        }

    }

    //CheckWin now checks the whole board.
    public static boolean CheckWin() {
        return false;
    }

}
