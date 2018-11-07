package battleship;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.Color;

public class Token {

    protected Color color;
    private int value;
    protected Battleship mainClassInst;
    private boolean unremovable;
   

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
       
       
            g.fillOval(Window.getX(column * xdelta +6), Window.getY(row * ydelta +3),
                    xdelta-12, ydelta -6);
       

    }

    public boolean isUnremovable(){
        return unremovable;
    }
    public void setUnremovable(){
        unremovable = true;
        color = Color.black;
    }
    

}
