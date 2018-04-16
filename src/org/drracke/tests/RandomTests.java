/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.drracke.tests;

import org.drracke.citymap.Position;

/**
 * @author Drracke
 */
public class RandomTests {

    public static void main(String[] args) {
        Position hell = new Position(15,15);
        Position seventh = hell.increment(15, 15);
        Position please = hell;
        
        System.out.println(hell.equals(seventh));
        System.out.println(hell);
        System.out.println(seventh);
        
    }
}
