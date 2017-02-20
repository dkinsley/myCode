//David Kinsley
#pragma once

#include <string>
#include <vector>

#ifndef arrayStuff_h
#define arrayStuff_h

const int SUCCESSFUL = 0;
const int EXITING = SUCCESSFUL - 1;
const char CHAR_TO_SEARCH_FOR2 = ' ';

struct vectorStruct {
	std::string VecWord;
	int occ;
};

std::vector<vectorStruct> populateandCountNumOfOccurence(std::vector<vectorStruct> &a, std::string &b);

std::vector<vectorStruct> deleteDuplicates(std::vector<vectorStruct> &a);

std::vector<vectorStruct> sortAscending(std::vector<vectorStruct> &a);

std::vector<vectorStruct> sortDescending(std::vector<vectorStruct> &a);

std::vector<vectorStruct> SortByOccurence(std::vector<vectorStruct> &a);

#endif
