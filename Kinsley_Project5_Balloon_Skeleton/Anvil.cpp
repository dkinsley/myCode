#include "Anvil.h"
#include "Moveable.h"

Anvil::Anvil(sizeofScreenBuffer myScreenBufferSize, location myLoc, SPEED mySpd, DIRECTION myDir) : Moveable(myScreenBufferSize, myLoc, mySpd, myDir){
	this->iHowLongBeforeFall = iHowLongBeforeFall;
}

Anvil::~Anvil(void) {
}

bool Anvil::draw(std::vector<std::string> &myScreenVector) {
	bool aDeletedMe = false;
	//make switch statements for each anvil condition
	switch (col) {
	case COSMO_POPPED:
	case BALLOON_CLOBBERED_COSMO:
		myScreenVector[myLoc.y].replace(myLoc.x, ANVIL_WIDTH,   "OUCH!");
		myScreenVector[myLoc.y+1].replace(myLoc.x, ANVIL_WIDTH, "OUCH!");
		myScreenVector[myLoc.y+2].replace(myLoc.x, ANVIL_WIDTH, "OUCH!");
		myScreenVector[myLoc.y+3].replace(myLoc.x, ANVIL_WIDTH, "OUCH!");
		aDeletedMe = true;
		break;
	
	case NO:
		default:
			myScreenVector[myLoc.y].replace(myLoc.x, ANVIL_WIDTH,   "    ___   ");
			myScreenVector[myLoc.y+1].replace(myLoc.x, ANVIL_WIDTH, "   /__/\\  ");
			myScreenVector[myLoc.y+2].replace(myLoc.x, ANVIL_WIDTH, "  /   \\ \\ ");
			myScreenVector[myLoc.y+3].replace(myLoc.x, ANVIL_WIDTH, " /     \\ \\");
			myScreenVector[myLoc.y+4].replace(myLoc.x, ANVIL_WIDTH, "/_______\\/");
			if (myLoc.y + ANVIL_HEIGHT >= myScreenBufferSize.y)
				aDeletedMe = true;
			break;
	}
	if(iHowLongBeforeFall-- < ANVIL_WAIT_TIME)
		myLoc.y++;
	return aDeletedMe;
}

//1      ___
//2     /__/ \\
//3    /   \\ \\
//4   /     \\ \\
//5  /_______\\ /
//1234567890

//"  OUCH!   ", " *   * ", "* * * *", "*BOOM *", "* * * *"