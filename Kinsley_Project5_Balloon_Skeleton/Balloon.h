#pragma once
#include "moveable.h"

class Balloon :
	public Moveable
{
public:
	Balloon(sizeofScreenBuffer myScreenBufferSize,location myLoc, int iHowLongBeforeFall, SPEED spd=NO_SPD, DIRECTION dir=DOWN);
	virtual ~Balloon(void);

	//returns true if its time to delete
	virtual bool draw(std::vector<std::string> &myScreenVector);	

private:
	//waits around a while before it falls 
	int iHowLongBeforeFall;
};

