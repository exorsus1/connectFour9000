package evaluation;

import connectFour9000.Player;
import connectFour9000.Position;

public class Evaluation {

	static int vertical = Position.vertical;
	static int horizontal = Position.horizontal;
	
	public static float evaluate(Position pos, Player maxPlayer, Player minPlayer) {
		
		float evaluation = 0;
		

		evaluation = checkforThreeAndForLines(pos, maxPlayer, true) + 
				checkforThreeAndForLines(pos, minPlayer, false);
		return evaluation; 
	}
	
	
	
	private static float checkforThreeAndForLines(Position pos, Player player, 
			boolean maxPlayer) {
		
		float evaluation = 0;
		
		int counter = 0;
		
		for(int i=0; i<horizontal;i++ ) {
			
			for(int j=0; j<vertical;j++ ) {
				
				if(pos.board[i][j] == player.ordinal()) {
					counter++;
					if(counter == 3) {
						
						if(maxPlayer) {
							evaluation++;
						}else {
							evaluation--;
						}
	
					}else if(counter == 4) {
						if(maxPlayer) {
							evaluation = 1000; //Float.MAX_VALUE;
						}else {
							evaluation = -1000; //Float.MIN_VALUE;
						}
						
					}
				}else {
					counter=0;
				}
				
				
			}
			counter=0;
		}
		
		//check vertical lines
		for(int i=0; i<vertical;i++ ) {
			
			for(int j=0; j<horizontal;j++ ) {
				
				
				if(pos.board[j][i] == player.ordinal()) {
					counter++;
					if(counter == 3) {
						
						if(maxPlayer) {
							evaluation++;
						}else {
							evaluation--;
						}
	
					}else if(counter == 4) {
						if(maxPlayer) {
							evaluation = 1000; //Float.MAX_VALUE;
						}else {
							evaluation = -1000;  //Float.MIN_VALUE;
						}
						
					}
				}else {
					counter=0;
				}
				
				
			}
			counter=0;
		}
	
		return evaluation;
		
	}
	
	
	
}
