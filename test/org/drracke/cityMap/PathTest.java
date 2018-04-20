package org.drracke.cityMap;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class PathTest {

    private Path unbehaved;
    private Position one, two, three;
    private Path behaved;

    @Before
    public void setUp() {
        one = new Position(50, 50);
        two = new Position(150, 50);
        three = new Position(312, 269);

    }

    @Test
    public void perpCheck() {
        boolean later = Path.checkPerpendicularity;
        try {
            if (Path.checkPerpendicularity) {
                System.out.println("perpendicularity is wronlgy set for this test, correcting");
                Path.checkPerpendicularity = false;
            }
            unbehaved = new Path(one, three);
            behaved = new Path(one, two);

            Path.checkPerpendicularity = true;
            try {
                unbehaved = new Path(one, three);
                fail("bad checkPerpendicularity usage in Path class");
            } catch (PathException ex) {
                System.out.println("all is fine with perpendicularity.");
            }
            try {
                behaved = new Path(one, two);

            } catch (PathException ex) {
                fail("bad checkPerpendicularity usage in Path class");
            }
        } finally {
            Path.checkPerpendicularity = later;
        }
    }

    @Test
    public void beSureAboutIntegers() {
        Set<Integer> theSet = new HashSet<>();
        theSet.add(15);
        assertTrue(theSet.add(35));
        assertFalse(theSet.add(15));
    }
}