/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drracke.cityMap;


/**
 *
 * @author majob_000
 */
public class Traffic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        City city = new City();
        city.begin();
        for (int i = 0; i < 5; i++) {
            new Path();
        }

        for (Path p :
                Path.allPaths) {
            System.out.print(p);
            for (int[] foo : p.getPathLine()) {
                for (int i : foo) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }

        city.repaint();
    }        
}
