/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drracke.cityMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Drracke
 */
class City extends JFrame {

    private Canvas c;
    private List<Car> cars; //shh, this is actually ArrayList, except that it has to be synced and stuff....
    public static final int mapDim = 500;
    public static final Random rnd = new Random();
    private BufferStrategy str;
    private long sleepTime = 5;
    private final int BUFFERS = 3;
    private Image img;
    private boolean repainting = false;
    private boolean showPaths = false;
    private ArrayList<int[][]> pathData;
    private boolean clearing = true;


    public City() {
        super("This is the city");
        cars = Collections.synchronizedList(new ArrayList<>());
        try {
            img = ImageIO.read(new File("Btfld.png"));
        } catch (IOException ex) {
            System.out.println("Background not found " + ex);
        }

        //this.setSize(this.winDim, this.winDim);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cont = this.getContentPane();
        cont.setLayout(new FlowLayout());
        c = new Canvas() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(City.mapDim, City.mapDim);
            }

            @Override
            public void paint(Graphics g) {
            }
        };
        cont.add(c);
        JButton stop = new JButton("Stop");
        JButton paths = new JButton("Paths");
        JButton start = new JButton("Start");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("stop pressed");
                City.this.stopAll();
                repainting = false;
            }
        });
        paths.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("paths pressed");
                showPaths = !showPaths;      
            }
        });
        start.addActionListener(new ActionListener() {
            
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("start pressed");
                City.this.startAll();
                
                if (repainting) 
                    return;
                startRepaingin();
            }
        });
        cont.add(stop);
        cont.add(paths);
        cont.add(start);

        this.pack();
        c.createBufferStrategy(BUFFERS);
        str = c.getBufferStrategy();
        this.setLocationRelativeTo(null);
        this.startRepaingin();
    }

    void begin() {
        this.setVisible(true);
    }

    private void painting() {
        Graphics g = null;
        do {
            g = str.getDrawGraphics();
            if (clearing)
                g.clearRect(0,0,500,500);
            for (Car car : cars) {
                car.painting(g);
            }
            g.dispose();
            str.show();
        } while (str.contentsLost());
        if(showPaths) 
            this.paintingPaths();
        
    }
    
    
    public void paintingPaths(ArrayList<int[][]> paths){
        Graphics g = null;
        do {
            g = str.getDrawGraphics();
            
            
            for (int[][] coords : paths) {
                //System.out.println("If you see 0 or 1 something is wrong " + np[0].length);
                g.setColor(Color.black);
                g.drawPolyline(coords[0], coords[1], coords[0].length);
//                System.out.println("Pathline drawn for " + np);
//                System.out.println("Color was " + g.getColor());
            }
            g.dispose();
            str.show();
            str.show();
        } while (str.contentsLost());
    }

    public void addCar(Car c) {
        this.cars.add(c);
    }

    public void addRandomCar() {
        this.addCar(new Car(new Position(rnd.nextInt(mapDim), rnd.nextInt(mapDim))));
    }

    public void stopAll() {
        for (Car car : cars) {
            car.stop();
        }
    }

    private void startAll() {
        for (Car car : cars) {
            car.start();
        }
    }

    private void startRepaingin() {
        Thread thread = new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repainting = true;
                while(repainting) {
                    City.this.painting();
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException ex) {
                        System.err.println("WAKE UP MAN " + ex);
                    }
                }
            }
        });
        thread.start();
    }

    private void paintingPaths() {
        if (pathData == null)
            this.forcePaths();
        paintingPaths(pathData);
    }

    private void forcePaths() {
        this.pathData = new ArrayList<>();
        for (Path pth : Path.allPaths) {
            this.pathData.add(pth.getPathLine());
        }
    }

    public void alphaPaintingPaths() {
        this.paintingPaths();
    }

}
