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
	
	/*
	 * private constructor for creating children position out of current position
	 */
	private Position(Action action, int moveCounter, Player player, Position prevPosition) {
		this.player = player;
		copyBoard(prevPosition);
		
		setStone(action);
		checkForGameOver();

	}
	
	//empty position
	Position(Player player){
		this.player = player;
		initalizeBoard();
	}
	
	
	private void copyBoard(Position prevPosition) {
		for(int i=0; i<vertical;i++ ) {
			for(int j=0; j<horizontal;j++ ) {
				board[i][j] = prevPosition.board[i][j];
			}
		}
	}
	
	
	private void setStone(Action a) {
		for(int i=vertical-1; i>=0 ;i-- ) {
			if(board[a.getColumn()][i] != Player.NOT_PLAYED.ordinal()) {
				board[a.getColumn()][i+1] = player.ordinal(); 
			}
		}	
	}
	
	private boolean actionIsValid(Action a) {
		//check if there is at least one free open slot
		if(board[vertical-1][a.getColumn() ] != Player.NOT_PLAYED.ordinal()	) {
			return false;
		}else {
			return true;
		}
	
	}
	
	public boolean gameOver() {
		return gameOver;
	}

	private void checkForGameOver() {
		if(moveCounter == Game.NUMBER_OF_MOVES || fourstonesReached()) {
			gameOver = true;
		}else {
			gameOver = false;
		}
	}
	
	
	public void printPosition() {
		
		
		for(int i=0; i<vertical;i++ ) {
			
			for(int j=0; j<horizontal;j++ ) {
				if( board[i][j] == Player.NOT_PLAYED.ordinal()) {
					System.out.print( "_" );
				}else if(board[i][j] == Player.PLAYER_RED.ordinal()) {
					System.out.print( "X" );
				}else {
					System.out.print( "O" );
				}
				
				
				
				
				
				
			}
			System.out.println( "" );
		}
	}
	
	
	
	
	
	/*
	 * check if a four stone line is reached
	 */
	private boolean fourstonesReached() {
		
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
		
		for(int i=0;i<vertical;i++) {
			
			Action a = new Action(i);
			if(!gameOver && actionIsValid(a)) {
				
				Player otherPlayer;
				
				if(player == Player.PLAYER_BLUE) {
					otherPlayer = Player.PLAYER_RED;
				}else {
					otherPlayer = Player.PLAYER_BLUE;
				}
				
				Position newPosition;
				newPosition = new Position(a, moveCounter++, otherPlayer, this);
				children.add(newPosition);	
			}
		}
	}
	
	
	public List<Action> getListOfPossibleActions(){
		List<Action> actions = new ArrayList<Action>();
		
		for(int i=0;i<vertical;i++) {
			
			Action a = new Action(i);
			if(actionIsValid(a)) {
				actions.add(a);
			}
			
		}
		return actions;
	}
	
	/*
	 * method for creating new position for the human player of the game
	 */
	public Position getChildPositionOnAction(Action a) throws InvalidActionException  {
		if(!actionIsValid(a)) {
			throw new InvalidActionException("action not allowed because the column is already full");
		}else if(gameOver) {
			throw new InvalidActionException("action not allowed because the game is over");
		}
		
		if(player == Player.PLAYER_BLUE) {
			return new Position(a, moveCounter++, Player.PLAYER_RED, this );
		}else {
			return new Position(a, moveCounter++, Player.PLAYER_BLUE, this);
		}
	}
	
	private void initalizeBoard() {
		for(int i=0; i<vertical;i++ ) {
			
			for(int j=0; j<horizontal;j++ ) {
				board[i][j] = Player.NOT_PLAYED.ordinal();	
			}

		}
	}
	
	/*
	 * iterator for iterating over all possible children-position for the new setting player
	 * @see java.lang.Iterable#iterator()
	 */
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
