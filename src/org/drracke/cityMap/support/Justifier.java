package org.drracke.cityMap.support;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.PushbackReader;

public class Justifier {

    Canvas c;

    public Justifier() {
        this.c = new Canvas() {
            @Override
            public void paint(Graphics graphics) {
                Graphics2D g = (Graphics2D) graphics;
                g.fillPolygon(getpoo());
                g.fillPolygon(gettransla());
                g.fill(shoo());


            }
        };


    }

    public static void muchmore(String[] args) {
        Justifier j = new Justifier();


        JFrame lolo = new JFrame();
        lolo.setSize(500, 500);
        lolo.setLocationRelativeTo(null);
        lolo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lolo.add(j.c);
        lolo.setVisible(true);

    }

    private Shape shoo() {
        Rectangle ret = new Rectangle();
        ret.add(5, 15);
        ret.add(200, 100);
        ret.translate(200, 200);

        return ret;
    }

    private Polygon getpoo() {
        Polygon ret = new Polygon();
        ret.addPoint(5, 10);
        ret.addPoint(20, 10);

        return ret;
    }

    private Polygon gettransla() {
        Polygon ret = new Polygon();
        ret.addPoint(5, 100);
        ret.addPoint(5, 15);
        ret.addPoint(200, 15);
        ret.addPoint(200, 100);

        ret.translate(50, 50);

        return ret;
    }
}
