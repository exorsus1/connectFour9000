package connectFour9000;

public class Game {

	int[][] board = new int[6][7];
	
	
	
	private float alphaBeta(Position position, int depth, float alpha, float beta, boolean maximizingPlayer) {
		if(depth == 0 || position.gameOver()) {
			return eval(position);
		}
		
		if(maximizingPlayer) {
			float maxEval = Float.MIN_VALUE;
			
			//iterate over the children of current position
			for( Position child : position) {
				float eval = alphaBeta(child, depth -1, alpha,beta, false);
				maxEval = Float.max(maxEval,eval);
				alpha = Float.max(alpha, eval);
				if(beta <= alpha) {
					break;
				}
				
			}
			return maxEval;
	
		}else {
			float minEval = Float.MAX_VALUE;
			
			//iterate over the children of current position
			for( Position child : position) {
				float eval = alphaBeta(child, depth -1, alpha,beta, true);
				minEval = Float.min(minEval,eval);
				beta = Float.min(beta, eval);
				if(beta <= alpha) {
					break;
				}
				
			}
			return minEval;			
		}
	}
	

	
	
	private float eval(Position position) {
		return 0;
	}
	
	
	private boolean depthBoundaryReached() {
		return false;
	}
	
	
	
}


