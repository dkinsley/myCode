

#include <sstream>
#include <time.h>
#include <mutex>
#include <Windows.h>
#include <conio.h>
#include "ThreadUserInput.h"

void ThreadUserInput::run()  
{ 
	while (!m_stop) 
	{ 
		//see if user wants to go anywhere
		char val = _getch_nolock();
		{   
			//scoped to gurantee mutex unlock
			std::lock_guard<std::mutex> guard(instance_lock);
			mChar = val;
		}
	}
}

char ThreadUserInput::getInput(){   
		//scoped to gurantee mutex unlock
		std::lock_guard<std::mutex> guard(instance_lock);
		char userChar = mChar;
		mChar = '\0';
		return userChar;
}

////global mutex
//std::mutex instance_lock ;
//
//void getInput(char &mChar){
//	//runs until user enters an x or X
//	while(true){
//		//see if user wants to go anywhere
//		char val = _getch_nolock();
//		{   
//			//scoped to gurantee mutex unlock
//			std::lock_guard<std::mutex> guard(instance_lock);
//			mChar = val;
//		}
//		if (mChar == 'x' || mChar == 'X')
//			break;
//	}
//}
//ThreadUserInput::ThreadUserInput(void):bQuit(false)
//{	
//	std::thread
//}
//
//char ThreadUserInput::getInput(){   
//		//scoped to gurantee mutex unlock
//		std::lock_guard<std::mutex> guard(instance_lock);
//		char userChar = mChar;
//		mChar = '';
//		return userChar;
//}
//void ThreadUserInput::Start(){
//	checkForUserInput = std::thread{ [this](){ this->threadGetInput(); } };
//}
//void ThreadUserInput::Stop(){
//	{   
//		//scoped to gurantee mutex unlock
//		std::lock_guard<std::mutex> guard(instance_lock);
//		bQuit==true;
//	}
//	checkForUserInput.join();
//}
//
//ThreadUserInput::~ThreadUserInput(void)
//{
//	Stop();
//}
