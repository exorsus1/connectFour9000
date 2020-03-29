package connectFour9000;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Position implements Iterable<Position>{

	private final int vertical = 6;
	private final int horizontal = 7;
	
	private int[][] board = new int[vertical][horizontal];
	
	private int moveCounter;
	
	//player that made the last move on this position
	private Player player;
	
	private boolean gameOver;
	
	private final List<Position> children = new ArrayList<Position>();
	
	
	Position(Action action, int moveCounter, Player player) throws InvalidActionException{
		if(checkForValidityOfAction())
		
		
		if(moveCounter == 0) {
			initalizeBoard();
		}
		checkForGameOver();
		this.player = player;
		
		
		
		
		
		
	}
	

	private boolean actionIsValid(Action a) {
		//check if there is at least one free open slot
		if(board[vertical][a.getColumn() ] != Player.NOT_PLAYED.ordinal()	) {
			return false;
		}else {
			return true;
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	public boolean gameOver() {
		return gameOver;
	}

	private void checkForGameOver() {
		if(moveCounter == Game.NUMBER_OF_MOVES) {
			gameOver = true;
		}else {
			gameOver = false;
		}
	}
	
	
	private boolean checkForFours() {
		
		int counter=0;
		
		//check horizontal lines
		for(int i=0; i<vertical;i++ ) {
			
			for(int j=0; j<horizontal;j++ ) {
				
				if(board[i][j] == player.ordinal()) {
					counter++;
					if(counter == 4) {
						return true;
					}
				}else {
					counter=0;
				}
				
				
			}
			counter=0;
		}
		
		
		//check vertical lines
		for(int i=0; i<horizontal;i++ ) {
			
			for(int j=0; j<vertical;j++ ) {
				
				if(board[j][i] == player.ordinal()) {
					counter++;
					if(counter == 4) {
						return true;
					}
				}else {
					counter=0;
				}
				
				
			}
			counter=0;
		}
		
		//check diagonal TO-DO
		
		
		
		
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	//creates all possible child-positions out of current position
	private void createChildren() {
		
	}
	
	public Position getChildPositionOnAction(Action a)  {
		
		if(player == Player.PLAYER_BLUE) {
			try {
				return new Position(a, moveCounter++, Player.PLAYER_RED );
			} catch (InvalidActionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				return new Position(a, moveCounter++, Player.PLAYER_BLUE);
			} catch (InvalidActionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private void initalizeBoard() {
		for(int i=0; i<vertical;i++ ) {
			
			for(int j=0; j<horizontal;j++ ) {
				board[i][j] = Player.NOT_PLAYED.ordinal();	
			}

		}
	}
	
    @Override
    public Iterator<Position> iterator() {
    	
    	//create the child-position on calling the iterator
    	createChildren();
    	
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
