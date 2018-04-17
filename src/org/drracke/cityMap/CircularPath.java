/*
* Hello. I do not like licences {yet).
* If you have this, feel free to use it
* in any way you wish. No guarantees tho.
*/

package org.drracke.cityMap;

/**
 *
 * @author Drracke <at drracke.org>
 */
public class CircularPath extends Path {

    private double index = 0;

    @Override
    public Position nextPos(Position pos) throws PathException {
        pos = pos.increment((int)(Math.sin(index/10) * 15), (int) (Math.cos(index++/10)*15));
        return pos;
    }
    
}
