package connectFour9000;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Position implements Iterable<Position>{

	
	private final boolean gameOver;
	private final List<Position> children = new ArrayList<Position>();
	
	
	Position(){
		gameOver = false;
	}
	

	public boolean gameOver() {
		return gameOver;
	}

	
    @Override
    public Iterator<Position> iterator() {
        return new Iterator<Position> () {
            private final Iterator<Position> iter = children.iterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public Position next() {
                return iter.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("no changes allowed");
            }
        };
    }
	
	
	
}
