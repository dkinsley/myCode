//David Kinsley
#pragma once

#include <string>
#include <Windows.h>

#ifndef ARRAYSTUFF_H
#define ARRAYSTUFF_H

const int SUCCESS = 0;
const int EXIT = SUCCESS - 1;
const char CHAR_TO_SEARCH_FOR = ' ';

struct holderStruct {
	int occurence;
	std:: string word;
};

void alphabetizeAscending(const std::string &myString);

void alphabetizeDescending(const std::string &myString);

void countNumOfOcc(const std::string &myString);

#endif