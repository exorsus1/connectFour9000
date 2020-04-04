package connectFour9000;

import evaluation.Evaluation;

public class MinMax {

	public static float alphaBeta(Position position, int depth, float alpha, float beta, 
			boolean maximizingPlayer, Player maxPlayer, Player minPlayer) {
		
		if(depth == 0 || position.gameOver()) {
			return Evaluation.evaluate(position, maxPlayer, minPlayer);
		}
		
		if(maximizingPlayer) {
			float maxEval = Float.MIN_VALUE;
			
			//iterate over the children of current position
			for( Position child : position) {
				float eval = alphaBeta(child, depth -1, alpha,beta, false, minPlayer, maxPlayer);
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
				float eval = alphaBeta(child, depth -1, alpha,beta, true, maxPlayer, minPlayer);
				minEval = Float.min(minEval,eval);
				beta = Float.min(beta, eval);
				if(beta <= alpha) {
					break;
				}
				
			}
			return minEval;			
		}
	}	
	
}


