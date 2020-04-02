package connectFour9000;

import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		Random rand = new Random();
		Position position = new Position(Player.PLAYER_BLUE);
		
		
		
		while(!position.gameOver()) {
			
			
			List<Action> actions = position.getListOfPossibleActions();
			
			int n = rand.nextInt(actions.size());
			
			
			try {
				position = position.getChildPositionOnAction(actions.get(n));
			} catch (InvalidActionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			position.printPosition();
			
		}
	}

}
