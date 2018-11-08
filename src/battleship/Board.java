package battleship;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.Color;

public class Board {

    public final static int NUM_ROWS = 10;
    public final static int NUM_COLUMNS = 10;
    private static Battleship mainClassInst;
    private static Ship p1Ships[][] = new Ship[NUM_ROWS][NUM_COLUMNS];
    private static Token p1Shots[][] = new Token[NUM_ROWS][NUM_COLUMNS];
    private static Ship p2Ships[][] = new Ship[NUM_ROWS][NUM_COLUMNS];
    private static Token p2Shots[][] = new Token[NUM_ROWS][NUM_COLUMNS];

    public static Image oceanBG = Toolkit.getDefaultToolkit().getImage("./assets/Ocean_Background.jpg");
    public static int mostRecentRow;
    public static int mostRecentCol;
    public static boolean placingShips;

    public static void Reset() {

        for (int zi = 0; zi < NUM_ROWS; zi++) {
            for (int zx = 0; zx < NUM_COLUMNS; zx++) {
                p1Ships[zi][zx] = null;
                p2Ships[zi][zx] = null;
                p1Shots[zi][zx] = null;
                p2Shots[zi][zx] = null;

            }
        }
        placingShips = true;
        mostRecentRow = 0;
        mostRecentCol = 0;
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

    public static int getRowShips(int y) {
        int currRow = 0;
        int ydelta = Window.getHeight2() / 2 / NUM_ROWS;
        int currYVal = Window.getHeight2() / 2 + ydelta;
        while (y > currYVal) {

            currRow++;
            currYVal += ydelta;
        }
        return currRow;
    }

    public static boolean AddTokenPixel(int xpixel, int ypixel, boolean alreadyPlaced) {
        int currCol = getCol(xpixel);

        if (placingShips) {
            int currRow = getRowShips(ypixel);
            if (xpixel < 0 || xpixel > Window.getWidth2() || ypixel < Window.getHeight2() / 2
                    || ypixel > Window.getHeight2()) {
                return false;
            }
            //adding Ships        
            if (Player.getCurrentPlayer() == Player.getPlayers()[0]) {
                if (p1Ships[currRow][currCol] == null && !alreadyPlaced) {

                    if (Ship.p1ShipsPlaced == 0) {
                        if (currCol > NUM_COLUMNS - 2) {
                            currCol = NUM_COLUMNS - 2;
                            if(p1Ships[currRow][currCol] != null)
                                return false;
                        }
                        p1Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.SCOUT);
                    } else if (Ship.p1ShipsPlaced == 1) {
                        if (currCol > NUM_COLUMNS - 3) {
                            currCol = NUM_COLUMNS - 3;
                            if(p1Ships[currRow][currCol] != null)
                                return false;
                        }
                        p1Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.CRUISER);
                    } else if (Ship.p1ShipsPlaced == 2) {
                        if (currCol > NUM_COLUMNS - 3) {
                            currCol = NUM_COLUMNS - 3;
                            if(p1Ships[currRow][currCol] != null)
                                return false;
                        }
                        p1Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.SUB);
                    } else if (Ship.p1ShipsPlaced == 3) {
                        if (currCol > NUM_COLUMNS - 4) {
                            currCol = NUM_COLUMNS - 4;
                            if(p1Ships[currRow][currCol] != null)
                                return false;
                        }
                        p1Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.CARRIER);
                    } else if (Ship.p1ShipsPlaced == 4) {
                        if (currCol > NUM_COLUMNS - 5) {
                            currCol = NUM_COLUMNS - 5;
                            if(p1Ships[currRow][currCol] != null)
                                return false;
                        }
                        p1Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.DESTROYER);
                    }

                    mostRecentRow = currRow;
                    mostRecentCol = currCol;
                    return true;
                } else if (p1Ships[currRow][currCol] == null) {
                    if (p1Ships[mostRecentRow][mostRecentCol] != null && !p1Ships[mostRecentRow][mostRecentCol].isUnremovable()) {
                        p1Ships[mostRecentRow][mostRecentCol] = null;
                    }

                    if (Ship.p1ShipsPlaced == 0) {
                        if (currCol > NUM_COLUMNS - 2) {
                            currCol = NUM_COLUMNS - 2;
                            if(p1Ships[currRow][currCol] != null)
                                return false;
                        }
                        p1Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.SCOUT);
                    } else if (Ship.p1ShipsPlaced == 1) {
                        if (currCol > NUM_COLUMNS - 3) {
                            currCol = NUM_COLUMNS - 3;
                            if(p1Ships[currRow][currCol] != null)
                                return false;
                        }
                        p1Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.CRUISER);
                    } else if (Ship.p1ShipsPlaced == 2) {
                        if (currCol > NUM_COLUMNS - 3) {
                            currCol = NUM_COLUMNS - 3;
                            if(p1Ships[currRow][currCol] != null)
                                return false;
                        }
                        p1Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.SUB);
                    } else if (Ship.p1ShipsPlaced == 3) {
                        if (currCol > NUM_COLUMNS - 4) {
                            currCol = NUM_COLUMNS - 4;
                            if(p1Ships[currRow][currCol] != null)
                                return false;
                        }
                        p1Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.CARRIER);
                    } else if (Ship.p1ShipsPlaced == 4) {
                        if (currCol > NUM_COLUMNS - 5) {
                            currCol = NUM_COLUMNS - 5;
                            if(p1Ships[currRow][currCol] != null)
                                return false;
                        }
                        p1Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.DESTROYER);
                    }

                    mostRecentRow = currRow;
                    mostRecentCol = currCol;
                    return true;
                }
            } else if (Player.getCurrentPlayer() == Player.getPlayers()[1]) {
                if (p2Ships[currRow][currCol] == null && !alreadyPlaced) {

                    if (Ship.p2ShipsPlaced == 0) {
                        if (currCol > NUM_COLUMNS - 2) {
                            currCol = NUM_COLUMNS - 2;
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                        }
                        p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.SCOUT);
                    } else if (Ship.p2ShipsPlaced == 1) {
                        if (currCol > NUM_COLUMNS - 3) {
                            currCol = NUM_COLUMNS - 3;
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                        }
                        p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.CRUISER);
                    } else if (Ship.p2ShipsPlaced == 2) {
                        if (currCol > NUM_COLUMNS - 3) {
                            currCol = NUM_COLUMNS - 3;
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                        }
                        p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.SUB);
                    } else if (Ship.p2ShipsPlaced == 3) {
                        if (currCol > NUM_COLUMNS - 4) {
                            currCol = NUM_COLUMNS - 4;
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                        }
                        p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.CARRIER);
                    } else if (Ship.p2ShipsPlaced == 4) {
                        if (currCol > NUM_COLUMNS - 5) {
                            currCol = NUM_COLUMNS - 5;
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                        }
                        p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.DESTROYER);
                    }

                    mostRecentRow = currRow;
                    mostRecentCol = currCol;
                    return true;
                } else if (p2Ships[currRow][currCol] == null) {
                    if (p2Ships[mostRecentRow][mostRecentCol] != null && !p2Ships[mostRecentRow][mostRecentCol].isUnremovable()) {
                        p2Ships[mostRecentRow][mostRecentCol] = null;
                    }

                    if (Ship.p2ShipsPlaced == 0) {
                        if (currCol > NUM_COLUMNS - 2 && p2Ships[currRow][NUM_COLUMNS - 2] == null) {
                            currCol = NUM_COLUMNS - 2;
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                            p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.SCOUT);
                        } else {
                            p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.SCOUT);
                        }
                    } else if (Ship.p2ShipsPlaced == 1) {
                        if (currCol > NUM_COLUMNS - 3 && p2Ships[currRow][NUM_COLUMNS - 3] == null) {
                            currCol = NUM_COLUMNS - 3;
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                            p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.CRUISER);

                        } else {
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                            p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.CRUISER);
                        }
                    } else if (Ship.p2ShipsPlaced == 2) {
                        if (currCol > NUM_COLUMNS - 3 && p2Ships[currRow][NUM_COLUMNS - 3] == null) {
                            currCol = NUM_COLUMNS - 3;
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                            p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.SUB);

                        } else {
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                            p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.SUB);
                        }
                    } else if (Ship.p2ShipsPlaced == 3) {
                        if (currCol > NUM_COLUMNS - 4 && p2Ships[currRow][NUM_COLUMNS - 4] == null) {
                            currCol = NUM_COLUMNS - 4;
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                            p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.CARRIER);

                        } else {
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                            p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.CARRIER);
                        }
                    } else if (Ship.p2ShipsPlaced == 4) {
                        if (currCol > NUM_COLUMNS - 5 && p2Ships[currRow][NUM_COLUMNS - 5] == null) {
                            currCol = NUM_COLUMNS - 5;
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                            p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.DESTROYER);

                        } else {
                            if(p2Ships[currRow][currCol] != null)
                                return false;
                            p2Ships[currRow][currCol] = new Ship(Player.getCurrentPlayer().getColor(), Ship.shipClass.DESTROYER);
                        }
                    }

                    mostRecentRow = currRow;
                    mostRecentCol = currCol;
                    return true;
                }
            }
        } else {
//adding Shots
            int currRow = getRow(ypixel);
            if (xpixel < 0 || xpixel > Window.getWidth2() || ypixel < 0
                    || ypixel > Window.getHeight2() / 2) {
                return false;
            }
            if (Player.getCurrentPlayer() == Player.getPlayers()[0]) {
                if (p1Shots[currRow][currCol] == null && !alreadyPlaced) {
                    p1Shots[currRow][currCol] = new Token(Player.getCurrentPlayer().getColor());

                    mostRecentRow = currRow;
                    mostRecentCol = currCol;
                    return true;
                } else if (p1Shots[currRow][currCol] == null) {
                    if (!p1Shots[mostRecentRow][mostRecentCol].isUnremovable()) {
                        p1Shots[mostRecentRow][mostRecentCol] = null;
                    }
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
                } else if (p2Shots[currRow][currCol] == null) {
                    if (!p2Shots[mostRecentRow][mostRecentCol].isUnremovable()) {
                        p2Shots[mostRecentRow][mostRecentCol] = null;
                    }
                    p2Shots[currRow][currCol] = new Token(Player.getCurrentPlayer().getColor());

                    mostRecentRow = currRow;
                    mostRecentCol = currCol;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean confirm() {

        //placing and confirming shots
        if (!placingShips) {
            if (Player.getCurrentPlayer() == Player.getPlayers()[0]) {
                if (mainClassInst.alreadyPlaced) {
                    p1Shots[mostRecentRow][mostRecentCol].setUnremovable();
                    checkHit(mostRecentRow, mostRecentCol);
                    return true;
                }
            } else if (mainClassInst.alreadyPlaced) {
                p2Shots[mostRecentRow][mostRecentCol].setUnremovable();
                checkHit(mostRecentRow, mostRecentCol);
                return true;
            }
        } else {

            //placing and confirming ships
            if (Player.getCurrentPlayer() == Player.getPlayers()[0]) {
                if (mainClassInst.alreadyPlaced && p1Ships[mostRecentRow][mostRecentCol] != null) {
                    p1Ships[mostRecentRow][mostRecentCol].setUnremovable();
                    Ship.p1ShipsPlaced++;
                    if (Ship.p1ShipsPlaced == Ship.MAX_SHIPS && Ship.p2ShipsPlaced == Ship.MAX_SHIPS) {
                        placingShips = false;
                    }
                    return true;
                }
            } else if (mainClassInst.alreadyPlaced && p2Ships[mostRecentRow][mostRecentCol] != null) {
                p2Ships[mostRecentRow][mostRecentCol].setUnremovable();
                Ship.p2ShipsPlaced++;
                if (Ship.p1ShipsPlaced == Ship.MAX_SHIPS && Ship.p2ShipsPlaced == Ship.MAX_SHIPS) {
                    placingShips = false;
                }
                return true;
            }
        }
        return false;
    }

    public static void Draw(Graphics2D g) {
//Calculate the width and height of each board square.
        int ydelta = Window.getHeight2() / 2 / NUM_ROWS;
        int xdelta = Window.getWidth2() / NUM_COLUMNS;
        g.drawImage(oceanBG, Window.getX(0), Window.getY(0), Window.getWidth2(), Window.getHeight2(), mainClassInst);

        //Draw the grid.
        g.setColor(Player.getCurrentPlayer().getColor());

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
        //shots
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

        //ships
        if (Player.getCurrentPlayer() == Player.getPlayers()[0]) {
            for (int zi = 0; zi < NUM_ROWS; zi++) {
                for (int zx = 0; zx < NUM_COLUMNS; zx++) {
                    if (p1Ships[zi][zx] != null) {
                        if(p1Ships[zi][zx].getType() == Ship.shipClass.SCOUT)
                            p1Ships[zi][zx+1] = new Ship(p1Ships[zi][zx].getColor(),Ship.shipClass.SCOUT);
                        if(p1Ships[zi][zx].getType() == Ship.shipClass.CRUISER)
                            p1Ships[zi][zx+1] = new Ship(p1Ships[zi][zx].getColor(),Ship.shipClass.CRUISER);
                            p1Ships[zi][zx+2] = new Ship(p1Ships[zi][zx].getColor(),Ship.shipClass.CRUISER);
                        if(p1Ships[zi][zx].getType() == Ship.shipClass.SUB)
                            p1Ships[zi][zx+1] = new Ship(p1Ships[zi][zx].getColor(),Ship.shipClass.SUB);
                            p1Ships[zi][zx+2] = new Ship(p1Ships[zi][zx].getColor(),Ship.shipClass.CRUISER);
                        if(p1Ships[zi][zx].getType() == Ship.shipClass.CARRIER)
                            p1Ships[zi][zx+1] = new Ship(p1Ships[zi][zx].getColor(),Ship.shipClass.CARRIER);
                            p1Ships[zi][zx+2] = new Ship(p1Ships[zi][zx].getColor(),Ship.shipClass.CRUISER);
                            p1Ships[zi][zx+3] = new Ship(p1Ships[zi][zx].getColor(),Ship.shipClass.CRUISER);
                        if(p1Ships[zi][zx].getType() == Ship.shipClass.DESTROYER)
                            p1Ships[zi][zx+1] = new Ship(p1Ships[zi][zx].getColor(),Ship.shipClass.DESTROYER);
                            p1Ships[zi][zx+2] = new Ship(p1Ships[zi][zx].getColor(),Ship.shipClass.CRUISER);
                            p1Ships[zi][zx+3] = new Ship(p1Ships[zi][zx].getColor(),Ship.shipClass.CRUISER);
                            p1Ships[zi][zx+4] = new Ship(p1Ships[zi][zx].getColor(),Ship.shipClass.CRUISER);
                        p1Ships[zi][zx].drawShip(g, zi, zx, xdelta, ydelta);
                    }
                }
            }
        } else {
            for (int zi = 0; zi < NUM_ROWS; zi++) {
                for (int zx = 0; zx < NUM_COLUMNS; zx++) {
                    if (p2Ships[zi][zx] != null) {
                        p2Ships[zi][zx].drawShip(g, zi, zx, xdelta, ydelta);
                    }
                }
            }
        }

    }

    public static boolean checkHit(int row, int col) {
        if (row > NUM_ROWS || col > NUM_COLUMNS || row < 0 || col < 0) {
            return false;
        }

        if (Player.getCurrentPlayer() == Player.getPlayers()[0]) {
            //check opposite board implementation
            if (p2Ships[row][col] != null) {
                p2Ships[row][col].shipSank();
                p1Shots[row][col].isahit();
                Player.getPlayers()[0].addPoints(1);
                return true;
            } else {
                p2Ships[row][col] = new Ship(Color.black, Ship.shipClass.MISS);
            }

        } else if (Player.getCurrentPlayer() == Player.getPlayers()[1]) {
            //same code, but for diff player
            if (p1Ships[row][col] != null) {
                p1Ships[row][col].shipSank();
                p2Shots[row][col].isahit();
                Player.getPlayers()[1].addPoints(1);
                return true;
            } else {
                p1Ships[row][col] = new Ship(Color.black, Ship.shipClass.MISS);
            }
        }

        return false;
    }

    //CheckWin now checks the whole board.
    public static int checkWin() {
        boolean DestroyerSunk = false;
        boolean CarrierSunk = false;
        boolean SubSunk = false;
        boolean CruiserSunk = false;
        boolean ScoutSunk = false;

        if (Player.getCurrentPlayer() == Player.getPlayers()[0]) {
            for (int zi = 0; zi < NUM_ROWS; zi++) {
                for (int zx = 0; zx < NUM_COLUMNS; zx++) {
                    if (p1Ships[zi][zx] != null) {
                        if (p1Ships[zi][zx].getType() == Ship.shipClass.DESTROYER && p1Ships[zi][zx].isSunk()) {
                            DestroyerSunk = true;
                        } else if (p1Ships[zi][zx].getType() == Ship.shipClass.CARRIER && p1Ships[zi][zx].isSunk()) {
                            CarrierSunk = true;
                        } else if (p1Ships[zi][zx].getType() == Ship.shipClass.SUB && p1Ships[zi][zx].isSunk()) {
                            SubSunk = true;
                        } else if (p1Ships[zi][zx].getType() == Ship.shipClass.CRUISER && p1Ships[zi][zx].isSunk()) {
                            CruiserSunk = true;
                        } else if (p1Ships[zi][zx].getType() == Ship.shipClass.SCOUT && p1Ships[zi][zx].isSunk()) {
                            ScoutSunk = true;
                        }
                    }
                }
            }
            if (DestroyerSunk && CarrierSunk && SubSunk && CruiserSunk && ScoutSunk) {
                return 1;
            }
        } else {
            for (int zi = 0; zi < NUM_ROWS; zi++) {
                for (int zx = 0; zx < NUM_COLUMNS; zx++) {
                    if (p2Ships[zi][zx] != null) {
                        if (p2Ships[zi][zx].getType() == Ship.shipClass.DESTROYER && p2Ships[zi][zx].isSunk()) {
                            DestroyerSunk = true;
                        } else if (p2Ships[zi][zx].getType() == Ship.shipClass.CARRIER && p2Ships[zi][zx].isSunk()) {
                            CarrierSunk = true;
                        } else if (p2Ships[zi][zx].getType() == Ship.shipClass.SUB && p2Ships[zi][zx].isSunk()) {
                            SubSunk = true;
                        } else if (p2Ships[zi][zx].getType() == Ship.shipClass.CRUISER && p2Ships[zi][zx].isSunk()) {
                            CruiserSunk = true;
                        } else if (p2Ships[zi][zx].getType() == Ship.shipClass.SCOUT && p2Ships[zi][zx].isSunk()) {
                            ScoutSunk = true;
                        }
                    }
                }
            }
            if (DestroyerSunk && CarrierSunk && SubSunk && CruiserSunk && ScoutSunk) {
                return 2;
            }
        }
        return 0;

    }

}
