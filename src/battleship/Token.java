package battleship;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.Color;

public class Token {

    private Color color;
    private int value;
    
    private boolean unremovable;
 public static Image Submarine = Toolkit.getDefaultToolkit().getImage("./assets/Submarine.jpg");
 public static Image Cruiser = Toolkit.getDefaultToolkit().getImage("./assets/Cruiser.png");
 public static Image Scout = Toolkit.getDefaultToolkit().getImage("./assets/Scout.png");
 public static Image Destroyer = Toolkit.getDefaultToolkit().getImage("./assets/Destroyer.png");
 public static Image Carrier = Toolkit.getDefaultToolkit().getImage("./assets/Carrier.png");

    Token(Color _color) {
        color = _color;
        value = (int) (Math.random() * 5) + 1;
        
        unremovable = false;

    }

    public Color getColor() {
        return (color);
    }

    public int getValue() {
        return value;
    }

    public void draw(Graphics2D g, int row, int column, int xdelta, int ydelta) {

       g.setColor(color);
       
//        if (shape == 0) {
            g.fillOval(Window.getX(column * xdelta +6), Window.getY(row * ydelta +3),
                    xdelta-12, ydelta -6);
//        } else if (shape == 1) {
//            g.fillRect(Window.getX(column * xdelta), Window.getY(row * ydelta),
//                    xdelta, ydelta);
//
//        } else {
//            int triX[] = new int[3];
//            triX[0] = Window.getX(column * xdelta);
//            triX[1] = Window.getX(column * xdelta + (xdelta / 2));
//            triX[2] = Window.getX(column * xdelta + xdelta);
//            int triY[] = new int[3];
//            triY[0] = Window.getY(row * ydelta + ydelta);
//            triY[1] = Window.getY((row * ydelta));
//            triY[2] = Window.getY(row * ydelta + ydelta);
//            g.fillPolygon(triX, triY, 3);
//
//        }
//        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
//        g.setColor(Color.black);
//        g.drawString("" + value, Window.getX(column * xdelta) + (xdelta / 2 - (xdelta / 13)), Window.getY(row * ydelta) + (ydelta / 2 + (ydelta / 15)));

    }

    public boolean isUnremovable(){
        return unremovable;
    }
    public void setUnremovable(){
        unremovable = true;
        color = Color.black;
    }
    

}
