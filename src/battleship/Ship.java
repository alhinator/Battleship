package battleship;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.Color;

public class Ship extends Token {
    public static int scoutBoxes = 0;
    public static int cruiserBoxes = 0;
    public static int subBoxes = 0;
    public static int carrierBoxes = 0;
    public static int destroyerBoxes = 0;
    public int boxID;
    public static final int MAX_SHIPS = 5;
    public int SHIP_BOARD;
    public static int p1ShipsPlaced = 0;
    public static int p2ShipsPlaced = 0;
    
    public static Image Cruiser = Toolkit.getDefaultToolkit().getImage("./assets/Cruiser.png");
    public static Image Scout = Toolkit.getDefaultToolkit().getImage("./assets/Scout.png");
    public static Image Submarine = Toolkit.getDefaultToolkit().getImage("./assets/Submarine.png");
    public static Image Destroyer = Toolkit.getDefaultToolkit().getImage("./assets/Destroyer.png");
    public static Image Carrier = Toolkit.getDefaultToolkit().getImage("./assets/Carrier.png");

    public static Image hitGif = Toolkit.getDefaultToolkit().getImage("./assets/hit.gif");
    public static Image missGif = Toolkit.getDefaultToolkit().getImage("./assets/miss.gif");

    public static enum shipClass {
        SCOUT, CRUISER, SUB, CARRIER, DESTROYER, MISS, HIT
    };

    private shipClass shipType;
    private boolean sunk;
    public boolean hit;
    public static final int P1_SHIPS = 1;
    public static final int P2_SHIPS = 2;

    public static int scoutHealth_P1;
    public static int cruiserHealth_P1;
    public static int subHealth_P1;
    public static int carrierHealth_P1;
    public static int destroyerHealth_P1;

    public static int scoutHealth_P2;
    public static int cruiserHealth_P2;
    public static int subHealth_P2;
    public static int carrierHealth_P2;
    public static int destroyerHealth_P2;

    Ship(Color _color, shipClass type, int _SHIP_BOARD) {
        super(_color);
        shipType = type;
        SHIP_BOARD = _SHIP_BOARD;
        if (shipType == shipClass.SCOUT) {
            scoutHealth_P1 = 2;
            scoutHealth_P2 = 2;
            scoutBoxes++;
            boxID = scoutBoxes;
            if(scoutBoxes >= scoutHealth_P1)
                scoutBoxes = 0;
        } else if (shipType == shipClass.CRUISER) {
            cruiserHealth_P1 = 3;
            cruiserHealth_P2 = 3;
            cruiserBoxes++;
            boxID = cruiserBoxes;
            if(cruiserBoxes >= cruiserHealth_P1)
                cruiserBoxes = 0;
        } else if (shipType == shipClass.SUB) {
            subHealth_P1 = 3;
            subHealth_P2 = 3;
            subBoxes++;          
            boxID = subBoxes;
            if(subBoxes >= subHealth_P1)
                subBoxes = 0;
        } else if (shipType == shipClass.CARRIER) {
            carrierHealth_P1 = 4;
            carrierHealth_P2 = 4;
            carrierBoxes++;
            boxID = carrierBoxes;
            if(carrierBoxes >= carrierHealth_P1)
                carrierBoxes = 0;
        } else if (shipType == shipClass.DESTROYER) {
            destroyerHealth_P1 = 5;
            destroyerHealth_P2 = 5;
            destroyerBoxes++;
            boxID = destroyerBoxes;
            if(destroyerBoxes >= destroyerHealth_P1)
                destroyerBoxes = 0;
        }
    }

    public void draw(Graphics2D g, int row, int column, int xdelta, int ydelta) {

        g.setColor(color);
        g.fillRect(Window.getX(column * xdelta + 6), Window.getY(row * ydelta + 3) + Window.getHeight2() / 2,
                xdelta - 12, ydelta - 6);
        
    }

    public void drawShip(Graphics2D g, int row, int column, int xdelta, int ydelta) {
        
            if (shipType == shipClass.SCOUT) {
            if(boxID == 1)
                g.drawImage(Scout, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 2, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.CRUISER) {
            if(boxID == 1)
                g.drawImage(Cruiser, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 3, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.SUB) {
            if(boxID == 1)
                g.drawImage(Submarine, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 3, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.CARRIER) {
            if(boxID == 1)
                g.drawImage(Carrier, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 4, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.DESTROYER) {
            if(boxID == 1)
                g.drawImage(Destroyer, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta * 5, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.HIT) {
            g.drawImage(hitGif, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta, ydelta, super.mainClassInst);
        } else if (shipType == shipClass.MISS) {
            g.drawImage(missGif, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta, ydelta, super.mainClassInst);
        }
        
        if (hit) {
            g.drawImage(hitGif, Window.getX(column * xdelta), Window.getY(row * ydelta + Window.getHeight2() / 2), xdelta, ydelta, super.mainClassInst);

        }
    }

    public shipClass getType() {
        return shipType;
    }

    public boolean isSunk(shipClass type, int SHIP_BOARD) {
        int scoutHealth = 2;
        int cruiserHealth = 3;
        int subHealth = 3;
        int carrierHealth = 4;
        int destroyerHealth = 5;
        
        if(SHIP_BOARD == 1) {
            scoutHealth = scoutHealth_P1;
            cruiserHealth = cruiserHealth_P1;
            subHealth = subHealth_P1;
            carrierHealth = carrierHealth_P1;
            destroyerHealth = destroyerHealth_P1;
        }
        else {
            scoutHealth = scoutHealth_P2;
            cruiserHealth = cruiserHealth_P2;
            subHealth = subHealth_P2;
            carrierHealth = carrierHealth_P2;
            destroyerHealth = destroyerHealth_P2;            
        }
            
        if(type == shipClass.SCOUT && scoutHealth <= 0)
            return true;
        else if(type == shipClass.CRUISER && cruiserHealth <= 0)
            return true;
        else if(type == shipClass.SUB && subHealth <= 0)
            return true;
        else if(type == shipClass.CARRIER && carrierHealth <= 0)
            return true;
        else if(type == shipClass.DESTROYER && destroyerHealth <= 0)
            return true;
        else 
            return false;
    }

    public void shipSank() {
        sunk = true;
        hit = true;
    }
    public int getID() {
        return boxID;
   }
}
