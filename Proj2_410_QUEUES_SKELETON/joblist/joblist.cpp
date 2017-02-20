//David Kinsley and Christian Volz 10/21/2016
#include <iostream>
#include "..\includes\joblist.h"
#include "..\includes\file_io.h"
#include "..\includes\constants.h"
#include "..\includes\Dispatcher.h"
#include <fstream>
#include <vector>
#include <string>
#include <algorithm>
#include <sstream>

std::vector<PCB> myVec;

bool sort_start_time2(const PCB &measurement1, const PCB &measurement2) {
	return measurement1.start_time < measurement2.start_time;
}


//assumme the worst
bool joblistHasJobs = false;

int joblist::init(const char* filename) {
	std::ifstream myFile;
	PCB *tempPCB = new PCB();

	std::string line;
	//open the file and retrieves the data
	int i = 0;
	int count = 0;
	myFile.open(filename);
	if (myFile.is_open()) {
		while (std::getline(myFile, line)) {
			std::stringstream ss(line);
			while (getline(ss, line, CHAR_TO_SEARCH_FOR)) {
				if (count % 4 == 0) {
					myVec.push_back(PCB());
					myVec[i].process_number = stoi(line);
				}
				else if (count % 4 == 1) {
					myVec[i].start_time = stoi(line);
				}
				else if (count % 4 == 2) {
					myVec[i].cpu_time = stoi(line);
				}
				else if (count % 4 == 3) {
					myVec[i].io_time = stoi(line);
					i++;
				}
				count++;
			}
			if (count == 4)
				count = 0;
		}
		std::sort(myVec.begin(), myVec.end(), sort_start_time2);
	}
	else {
		return FAIL;
	}
	return SUCCESS;
}

PCB joblist::getNextJob() {
	PCB tmpPCB = myVec[0];
	myVec.erase(myVec.begin());
	return tmpPCB;
}

int joblist::doTick(int currentTick) {
	bool hasJob = false;
	//if there are no jobs, return NO_JOBS
	if (myVec.size() == 0) {
		return NO_JOBS;
	}
	//check to see if any jobs have io interrupts
	for (int i = 0; i < myVec.size() - 1; i++) {
		if (myVec[i].io_time == 1) {
			hasJob = true;
		}
		if (i == myVec.size() - 1 && hasJob == false) {
			return NO_JOBS;
		}
	}
	//if there is a job ready to begin, add it to the dispatcher
	if (myVec[0].start_time <= currentTick) {
		return ADD_JOB_TO_DISPATCHER;
	}
	//else wait
	else {
		return WAITING_TO_ADD_JOB_TO_DISPATCHER;
	}
}