/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.drracke.cityMap;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * @author Drracke
 * so far only point.. thats it.
 */
public class Position extends Point2D.Float {

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void increment(float dx, float dy) {
        x += dx;
        y += dy;
    }
    
    @Override 
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Position))
            return false;
        Position reTyped = (Position) o;
        return reTyped.x == this.x && reTyped.y == this.y;
        
    }

    public Point drawable() {
        return new Point(Math.round(x), Math.round(y));
    }
    
    
    @Override
    public Object clone() {
        return new Position(this.x, this.y);
    }
}
