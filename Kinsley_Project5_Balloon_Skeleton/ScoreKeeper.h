#pragma once
#include <string>

class ScoreKeeper
{
public:
	ScoreKeeper(void);
	~ScoreKeeper(void);

	//pass in a string and put cosmos score at begginning and Balloons at end
	//WILL NOT ALTER LENGTH OF STRING (fails if string not long enough)
	bool getDisplayString(std::string &scoreString);

	//inline int getScoreBalloon(){return scoreBalloon;};
	//inline int getScoreCosmo(){return scoreBalloon;};

	//typically its 1 at a time
	inline  void incScoreBalloon(int i=1){scoreBalloon+=i;};
	inline  void incScoreCosmo(int i=1){scoreCosmo+=i;};
	inline void anvil_clobbers_cosmo(int i = 5){ scoreBalloon += i; };
	inline void cosmo_clobbers_anvil(int i = 5){ scoreCosmo += i; }
	inline void cosmo_and_bee(int i = 0) { scoreBalloon += i; };
	inline  void resetScores(){scoreBalloon=0;scoreCosmo=0;};

private:
	//scores
	int scoreBalloon;
	int scoreCosmo;
};

