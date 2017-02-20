#pragma once
#include <vector>
#include <string>
#include "constants.h"

class Moveable
{
public:
	//size of screen, mylocation (x,y)  etc.
	Moveable(sizeofScreenBuffer myScreenBufferSize, location myLoc, SPEED spd, DIRECTION dir, int pointsIfMoveableWins=1, int pointsIfCosmoWins=1);
	void setDirection(DIRECTION dir);
	void setSpeed(SPEED spd); 

	inline int getX(){return myLoc.x;};
	inline int getY(){return myLoc.y;};
	inline DIRECTION getDir(){return dir;};
	void setCollidedState(COLLISION col);
	inline void setLocation(location myLoc){this->myLoc = myLoc;};
	
	
	bool Collidable_set_false(bool hasCollided);

	//returns true if its time to delete
	virtual	bool draw(std::vector<std::string> &myScreenVector)=0;			//pure virtual, abstract base class, MUST BE DEFINED BY DERIVED CLASSES
	virtual ~Moveable(void);
protected:
	location myLoc; 
	sizeofScreenBuffer myScreenBufferSize;
	DIRECTION dir;
	SPEED spd;
	int iTimeBetweenMovements;	
	COLLISION col;
	int pointsIfMoveableWins;
	int pointsIfCosmoWins;

	//use this to limit the number of times a collision can be counted
	//for instance when a terrible balloon turns into a Bee dont count collisions afterward
	bool bCollidable;
	inline void setCollidable(bool bCollidable){this->bCollidable = bCollidable;};
	
};

