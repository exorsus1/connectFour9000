package connectFour9000;

import java.util.List;
import java.util.Random;

import evaluation.Evaluation;

public class Main {

	public static void main(String[] args) {
		
		Random rand = new Random();
		Position position = new Position(Player.PLAYER_BLUE);
		
		
		
		while(!position.gameOver()) {
			
			List<Action> actions = position.getListOfPossibleActions();
			Action result=null;
			
			if(position.getPlayer() ==  Player.PLAYER_BLUE) {
				
				float value=0;
				float resultValue = Float.MIN_VALUE;
				
				
				for(Action action : actions) {
					
					value = MinMax.alphaBeta( position, 5,  Float.MIN_VALUE, Float.MAX_VALUE, 
							true, Player.PLAYER_BLUE, Player.PLAYER_RED);
					
					
					if (value > resultValue) {
		                result = action;
		                resultValue = value;
		            }
					
					
				}
				

				
			}else {
				result = actions.get(rand.nextInt(actions.size()));
			}
			
			
			
			
			
			
			
			try {
				position = position.getChildPositionOnAction(result);
			} catch (InvalidActionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			position.printPosition();
			
		}
	}

}
