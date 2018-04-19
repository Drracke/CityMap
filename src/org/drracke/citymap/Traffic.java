/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drracke.citymap;


import java.util.Scanner;

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
            city.addRandomCar();
        }
        city.addCar(new Car());
//        city.addCar(new Car(new CircularPath()));
        Scanner s = new Scanner(System.in);
        for(;;) {
            s.nextInt();
            city.addCar(new Car());
        }
    }        
}
