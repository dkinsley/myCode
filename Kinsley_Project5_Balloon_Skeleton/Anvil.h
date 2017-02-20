#pragma once
//include moveable b/c its a moveable object
#include "Moveable.h"

//make a class anvil that inherits from moveable
class Anvil : public Moveable {
	//make public 
	public:
		Anvil(sizeofScreenBuffer myScreenBufferSize, location myLoc, SPEED mySpd = NO_SPD, DIRECTION myDir = DOWN);
		virtual ~Anvil(void);

		virtual bool draw(std::vector<std::string> &myScreenVector);


	//make private
	private:
		int iHowLongBeforeFall;
		
};