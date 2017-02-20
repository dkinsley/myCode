#include "TerribleBalloon.h"


TerribleBalloon::TerribleBalloon(sizeofScreenBuffer myScreenBufferSize, location myLoc, int iHowLongBeforeFall, SPEED spd, DIRECTION dir):Balloon(myScreenBufferSize,myLoc,iHowLongBeforeFall,spd,dir) {
	this->iHowLongBeforeFall = iHowLongBeforeFall;
	this->balloonState = balloonState;
}

TerribleBalloon::~TerribleBalloon(void) {
	
}

void TerribleBalloon::setBalloonState(BALLOON_STATE state) {
	this->balloonState = state;
}

bool TerribleBalloon::draw(std::vector<std::string> &myScreenVector) {
	bool tDeleteMe = false;
	
	if (this->balloonState == IS_BEE) {
		dir = static_cast<DIRECTION>(rand() % 5);
		flappingWings = !flappingWings;
		switch (dir) {
		case LEFT:
			if (myLoc.x - BEE_WIDTH < 3) {
				tDeleteMe = true;
				break;
			}
			else {
				myLoc.x = myLoc.x - spd;
				if (!flappingWings) {
					myScreenVector[myLoc.y].replace(myLoc.x, BEE_WIDTH,     "   ()");
					myScreenVector[myLoc.y + 1].replace(myLoc.x, BEE_WIDTH, "%000-");
					myScreenVector[myLoc.y + 2].replace(myLoc.x, BEE_WIDTH, "     ");
					break;
				}
				else {
					myScreenVector[myLoc.y].replace(myLoc.x, BEE_WIDTH,     "  ())");
					myScreenVector[myLoc.y + 1].replace(myLoc.x, BEE_WIDTH, "%000-");
					myScreenVector[myLoc.y + 2].replace(myLoc.x, BEE_WIDTH, "     ");
					break;
				}
			}
		case RIGHT:
			if (myLoc.x + BEE_WIDTH > DEFAULT_WIDTH) {
				tDeleteMe = true;
				break;
			}
			else {
				myLoc.x = myLoc.x + spd;
				if (!flappingWings) {
					myScreenVector[myLoc.y].replace(myLoc.x, BEE_WIDTH,     "()   ");
					myScreenVector[myLoc.y + 1].replace(myLoc.x, BEE_WIDTH, "-000%");
					myScreenVector[myLoc.y + 2].replace(myLoc.x, BEE_WIDTH, "     ");
					
					break;
				}
				else {
					myScreenVector[myLoc.y].replace(myLoc.x, BEE_WIDTH,     "())  ");
					myScreenVector[myLoc.y + 1].replace(myLoc.x, BEE_WIDTH, "-000%");
					myScreenVector[myLoc.y + 2].replace(myLoc.x, BEE_WIDTH, "     ");
					
					break;
				}
			}
		case UP:
			if (myLoc.y + BEE_HEIGHT <= 3) {
				tDeleteMe = true;
				break;
			}
			else {
				myLoc.y = myLoc.y - spd;
				myScreenVector[myLoc.y].replace(myLoc.x, BEE_WIDTH,     " %   ");
				myScreenVector[myLoc.y + 1].replace(myLoc.x, BEE_WIDTH, "=0=  ");
				myScreenVector[myLoc.y + 2].replace(myLoc.x, BEE_WIDTH, " |   ");
				
				break;
			}
		case DOWN:
			if (myLoc.y + BEE_HEIGHT < DEFAULT_HEIGHT-3) {
				myLoc.y = myLoc.y + spd;
				myScreenVector[myLoc.y].replace(myLoc.x, BEE_WIDTH,     " |   ");
				myScreenVector[myLoc.y + 1].replace(myLoc.x, BEE_WIDTH, "=0=  ");
				myScreenVector[myLoc.y + 2].replace(myLoc.x, BEE_WIDTH, " %   ");
				
				break;
			}
			else {
				tDeleteMe = true;
				break;
			}
		case NO_DIR:
			myScreenVector[myLoc.y].replace(myLoc.x, BEE_WIDTH,     " %   ");
			myScreenVector[myLoc.y + 1].replace(myLoc.x, BEE_WIDTH, "=0=  ");
			myScreenVector[myLoc.y + 2].replace(myLoc.x, BEE_WIDTH, " |   ");
			
			break;
		}
	}
	else {
		switch (col) {
		case NO:
			default:
				myScreenVector[myLoc.y].replace(myLoc.x, BALLOON_WIDTH,     "  ___  ");
				myScreenVector[myLoc.y + 1].replace(myLoc.x, BALLOON_WIDTH, " //\\ \\ ");
				myScreenVector[myLoc.y + 2].replace(myLoc.x, BALLOON_WIDTH, "| \\/  |");
				myScreenVector[myLoc.y + 3].replace(myLoc.x, BALLOON_WIDTH, " \\   / ");
				myScreenVector[myLoc.y + 4].replace(myLoc.x, BALLOON_WIDTH, "  \\ /  ");
				myScreenVector[myLoc.y + 5].replace(myLoc.x, BALLOON_WIDTH, "   |   ");
				myScreenVector[myLoc.y + 6].replace(myLoc.x, BALLOON_WIDTH, "   |   ");

				if (myLoc.y + BALLOON_HEIGHT >= myScreenBufferSize.y) {
					tDeleteMe = true;
				}

				break;


			case BALLOON_CLOBBERED_COSMO:
				myScreenVector[myLoc.y].replace(myLoc.x, BALLOON_WIDTH,     "   *   ");
				myScreenVector[myLoc.y + 1].replace(myLoc.x, BALLOON_WIDTH, " *   * ");
				myScreenVector[myLoc.y + 2].replace(myLoc.x, BALLOON_WIDTH, "* * * *");
				myScreenVector[myLoc.y + 3].replace(myLoc.x, BALLOON_WIDTH, "*BOOM *");
				myScreenVector[myLoc.y + 4].replace(myLoc.x, BALLOON_WIDTH, "* * * *");
				myScreenVector[myLoc.y + 5].replace(myLoc.x, BALLOON_WIDTH, " *   * ");
				myScreenVector[myLoc.y + 6].replace(myLoc.x, BALLOON_WIDTH, "   *   ");
				this->balloonState = IS_BEE;
				tDeleteMe = false;
				break;

			case COSMO_POPPED:
				myScreenVector[myLoc.y + 1].replace(myLoc.x, BALLOON_WIDTH, "       ");
				myScreenVector[myLoc.y + 1].replace(myLoc.x, BALLOON_WIDTH, "    |  ");
				myScreenVector[myLoc.y + 2].replace(myLoc.x, BALLOON_WIDTH, "  \\   /");
				myScreenVector[myLoc.y + 3].replace(myLoc.x, BALLOON_WIDTH, " - pop-");
				myScreenVector[myLoc.y + 4].replace(myLoc.x, BALLOON_WIDTH, "  /   \\");
				myScreenVector[myLoc.y + 5].replace(myLoc.x, BALLOON_WIDTH, "    |  ");
				myScreenVector[myLoc.y + 6].replace(myLoc.x, BALLOON_WIDTH, "       ");
				tDeleteMe = false;
				this->balloonState = IS_BEE;
				break;
			}


			if (iHowLongBeforeFall-- < 0) {
				if (iTimeBetweenMovements-- == 0) {
					myLoc.y += spd;
					iTimeBetweenMovements = FAST - spd + 1;
				}
			}
		}
	return tDeleteMe;
}