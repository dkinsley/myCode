#include "ScoreKeeper.h"

const char COSMO_SCORE_PREFIX[]		= "COSMO =";
const int  LEN_COSMO_SCORE_PREFIX	= 7;
const char BALLOON_SCORE_PREFIX[]	= "BALLOONS ="; 
const int  LEN_BALLOON_SCORE_PREFIX	= 10;
const int  SPACES_FOR_SCORE			= 6;

ScoreKeeper::ScoreKeeper(void)
{
	resetScores();
}

ScoreKeeper::~ScoreKeeper(void)
{
}

bool ScoreKeeper::getDisplayString(std::string &scoreString){
	//TODO calculate the score that goes in the display string here
	if ((scoreString.length() - LEN_BALLOON_SCORE_PREFIX - LEN_COSMO_SCORE_PREFIX - SPACES_FOR_SCORE) < 0)
		return false;
	else {
		char buffer[SPACES_FOR_SCORE];
		int length = sprintf(buffer,"%d", scoreCosmo);
		scoreString.replace(0, LEN_COSMO_SCORE_PREFIX, COSMO_SCORE_PREFIX);
		scoreString.replace(LEN_COSMO_SCORE_PREFIX + 1, length, buffer);

		length = sprintf(buffer, "%d", scoreBalloon);
		int balloonStart = scoreString.length() - LEN_BALLOON_SCORE_PREFIX - SPACES_FOR_SCORE;
		scoreString.replace(balloonStart, LEN_BALLOON_SCORE_PREFIX, BALLOON_SCORE_PREFIX);
		scoreString.replace(balloonStart + LEN_BALLOON_SCORE_PREFIX + 1, length, buffer);
	}
	return true;
}
