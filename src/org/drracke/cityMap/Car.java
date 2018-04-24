/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drracke.cityMap;

import java.awt.*;

/**
 * @author Drracke
 */
public class Car {

    public static final int SIZE = 10;

    private Position pos;
    private final int size = SIZE;
    private final Color col = Color.BLUE;
    private boolean moving = false;
    private Path pth;
    protected final int refreshRate = 1000/500; //influences speed obviously

    public Car(Position pos) {
        this.pos = pos;
        this.startMoving();
        pth = new Path();
    }
    
    public Car() {
        pth = new Path();
        this.pos = pth.getInitPos();
        this.startMoving();
    }
    
    public Car(Path pth) {
        this.pth = pth;
        this.pos = pth.getInitPos();
        this.startMoving();
    }

    public void painting(Graphics g) {
        Point pos = this.pos.drawable();
        int x = pos.x - this.size / 2;
        int y = pos.y - this.size / 2;
        g.setColor(col);
        g.fillOval(x, y, size, size);
    }

    public void rndMove() {
        this.pos.x += (City.rnd.nextInt(3) - 1);
        this.pos.y += (City.rnd.nextInt(3) - 1);
    }

    private void startMoving() {
        moving = true;
        Runnable move = new Runnable() {
            @Override
            public void run() {
                while (Car.this.moving) {
                    
                        Car.this.move();
                    
                    try {
                        Thread.sleep(refreshRate);
                    } catch (InterruptedException e) {
                        System.err.println("DONT WAKE ME " + e);
                    }
                }
            }
        };
        Thread thread = new Thread(move);
        thread.setPriority(3);
        thread.start();
    }

    public void stop() {
        this.moving = false;
    }

    void start() {
        if (moving) {
            return;
        }
        this.startMoving();
    }

    private void move() {
        try {
        this.pos = this.pth.nextPos(this.pos);
        } catch (PathException e) {
            this.moving = false;
        }
    }
}
