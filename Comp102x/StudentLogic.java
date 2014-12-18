import java.util.Random;

import comp102x.ColorImage;
import comp102x.assignment.GameLogic;
import comp102x.assignment.GameRecord;
import comp102x.assignment.Goal;

public class StudentLogic implements GameLogic {

	/**
	 * Creates intermediate image of football when it is fired from ARROW.
	 * Returns ColorImage object of football.
	 * 
	 * @param depthImages
	 *            Array of ColorImage object containing football image.
	 * @param initialX
	 * @param finalX
	 * @param initialY
	 */
	public ColorImage generateIntermediateFootballImage(
			ColorImage[] depthImages, int initialStep, int currentStep,
			int finalStep, double initialScale, double finalScale,
			int initialX, int finalX, int initialY, int finalY) {
		// write your code after this line

		int imageIndex, xPosition, yPosition;
		double scale;

		// calculating index of required football image.
		imageIndex = (depthImages.length - 1) * (currentStep - initialStep)
				/ (finalStep - initialStep);

		// calculating x position of football image
		xPosition = initialX + (finalX - initialX)
				* (currentStep - initialStep) / (finalStep - initialStep);

		// calculating y position of football image
		yPosition = initialY + (finalY - initialY)
				* (currentStep - initialStep) / (finalStep - initialStep);

		// calculating scale for football image
		scale = initialScale + (finalScale - initialScale)
				* (currentStep - initialStep) / (finalStep - initialStep);

		// extract football image from array
		ColorImage football = depthImages[imageIndex];
		football.setX(xPosition);
		football.setY(yPosition);
		football.setScale(scale);

		return football;
	}

	/**
	 * This metod is used to swap a hitted goal with a randomly selected
	 * intact(isHit == false) one, provided it is of MOVABLE type and its hit
	 * status is false.
	 * 
	 * @param goals
	 *            refers to a 2D array which contains object of Goal class.
	 */
	public void updateGoalPositions(Goal[][] goals) {
		// write your code after this line

		int row, col;

		// get the total no. of row and col in Goal 2D array
		row = goals.length;
		col = goals[0].length;

		// To hold a random row no. and col no. .
		int rowI, colJ;

		//
		Goal g1, g2;

		Random rand = new Random();

		// loop over goal array
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				g1 = goals[i][j];

				// if g1 is hit then swap it with other intact and Movable goal
				// :).
				if (g1.isHit() == true) {

					// get a random goal object from array
					rowI = rand.nextInt(row);
					colJ = rand.nextInt(col);
					g2 = goals[rowI][colJ];

					// if the random goal selected is not hit and MOVABLE
					// then perform swap action .
					if (!g2.isHit() && g2.getType() == Goal.MOVABLE) {
						goals[i][j] = g2;
						goals[rowI][colJ] = g1;
						break;
					}
				}
			}
		}

	}

	/**
	 * Updates the high score list(present in highScore.txt) contained in
	 * highScoreRecords[] . We have to consider two case for highScoreRecords
	 * array : Case 1: -Array length is zero Case 2: -Array length is more than
	 * zero.
	 * 
	 * @param highScoreRecords
	 *            Array holds the list of high scoring player .
	 * @param name
	 *            Name of current player
	 * @param level
	 *            Difficulty level of game
	 * @param score
	 *            Score of player at the end of game
	 */
	public GameRecord[] updateHighScoreRecords(GameRecord[] highScoreRecords,
			String name, int level, int score) {
		// write your code after this line
		final int SIZE = 10;
		GameRecord currentRecord;
		GameRecord[] newHighScoreRec = new GameRecord[SIZE];

		/**
		 * these are local variable to store details(field of GameRecord) of a
		 * record extracted from highScoreRecords array .
		 */
		String currentName;
		int currentLevel, currentScore;

		// length of highScoreRecords array
		int len = highScoreRecords.length;
		int i = 0, j = 0, k = 0;
		boolean recordInserted = false;

		/**
		 * create GameRecord object of current player by passing player
		 * name,game level and his/her score .
		 */
		GameRecord playerRecord = new GameRecord(name, level, score);
		if (len == 0) {
			highScoreRecords = new GameRecord[1];
			highScoreRecords[0] = playerRecord;
		}

		if (len > 0) {
			for (i = 0; i < len; i++) {

				// get the game record details from array to local variable
				currentRecord = highScoreRecords[i];
				currentName = currentRecord.getName();
				currentLevel = currentRecord.getLevel();
				currentScore = currentRecord.getScore();

				// player name already present in record file
				if (name.equals(currentName)) {

					// His/her current score is greater than previous one
					if (score > currentScore) {
						highScoreRecords[i].setScore(score);
						highScoreRecords[i].setLevel(level);
						break;
					}
					/*
					 * if current score is same as previous and current level is
					 * greater then only update the level
					 */
					else if (score == currentScore && level > currentLevel)
						highScoreRecords[i].setLevel(level);
					break;
				}
			}

			// got new player :)
			if (i == len) {
				/**
				 * 1st array = highScoreRecords
				 * 2nd array = newHighScoreRec
				 * compare each record's score present in highScoreRecords[]
				 * 
				 * with the score of new player and put the record with greater
				 * score into new array newHighScoreRec[] .
				 */
				while (j < len && k < SIZE) {

					// current score is greater than new players score
					if (highScoreRecords[j].getScore() >= score) {
						newHighScoreRec[k] = highScoreRecords[j];

						// increment index of both arrays
						j++;
						k++;
						// players score is greater than current score
					} else if (recordInserted == false) {
						newHighScoreRec[k] = playerRecord;

						// increment index of 2nd array only
						k++;

						// update to true
						recordInserted = true;
					} else {
						// add remaining records to 2nd array whose score is
						// less than
						// player's score
						newHighScoreRec[k] = highScoreRecords[j];
						k++;
						j++;
					}
				}

				// insert new player's record if its not inserted yet and size
				// of
				// 2nd array is less than SIZE
				if (recordInserted == false && k < SIZE) {
					newHighScoreRec[k] = playerRecord;

					// increment index of 2nd array only
					k++;

					// record inserted
					recordInserted = true;
				}
			}

			// if new player made an entry to high scores list then only copy
			// back 2nd array
			if (recordInserted == true) {

				// realloc array of exact size = k
				highScoreRecords = new GameRecord[k];

				// copy back 2nd array to 1st
				for (i = 0; i < k; i++) {
					highScoreRecords[i] = newHighScoreRec[i];
				}
			}
		}

		return highScoreRecords;
	}
}
