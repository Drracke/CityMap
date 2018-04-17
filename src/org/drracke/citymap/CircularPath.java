/*
* Hello. I do not like licences {yet).
* If you have this, feel free to use it
* in any way you wish. No guarantees tho.
*/

package org.drracke.citymap;

/**
 *
 * @author Drracke <at drracke.org>
 */
public class CircularPath extends Path {

    private double index = 0;
    
    public CircularPath() { super(new Position(200,400));}
    
    public CircularPath(Position crPos) {
        super(crPos);
    }
    
    @Override
    public Position nextPos() throws PathException {
        super.crPos = crPos.increment((int)(Math.sin(index/10) * 15), (int) (Math.cos(index++/10)*15));
        return crPos;
    }
    
}
