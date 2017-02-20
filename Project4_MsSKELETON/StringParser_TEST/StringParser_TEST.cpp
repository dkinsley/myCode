//============================================================================
// Name        : Proj4_Test.cpp
// Author      : David Kinsley
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <sstream>
#include "../Include/FileReader.h"	//relative path, from PWD, go up one, then down into Include to find FileReader.h
#include "../Include/constants.h"
#include "../Include/Debug_Help.h"
#include "../Include/stringparserclass.h"




void outputvectorrow(std::string i){
	std::cout<<i<<std::endl;
}
void foreach(std::vector<std::string> myVector){
	std::for_each(myVector.begin(), myVector.end(), outputvectorrow);
}

int main(){
	//TODO open file, if not there ask for a different file or exit
	std::ifstream myFile;
	std::string filename = TEST_FILE_NAME;
	std::string line;
	std::stringstream ss;
	KP_FileReaderClass::FileReader read = KP_FileReaderClass::FileReader::FileReader();

	myFile.open(filename);

	if (!myFile.is_open()) {
		std::cout << ENTER_FN_OR_X << std::endl;
		std::cin >> filename;
		if (filename == EXITCHAR) {
			return FILE_NOT_OPEN;
		}
		else
			myFile.open(filename);
	}
	else {
		while (getline(myFile, line, CHAR_TO_SEARCH_FOR)) {
			read.getFileContents(filename, line);
			break;
			}
	}
	

	//got file data, this is a bogus time and memory wasting step
	//whose sole purpose is to provide a way to pass
	//in a non const pointer to getDataBetweenTags(..) without casting
	/*vector<char> myLine;
	std::copy(filecontents.begin(), filecontents.end(), back_inserter(myLine));*/

	KP_StringParserClass::StringParserClass parser = KP_StringParserClass::StringParserClass::StringParserClass();
	std::string tag1;
	std::string tag2;

	std::cout << "What tags would you like to search through? (and please include the <>)" << '\n';
	std::cin >> tag1;
	std::cin >> tag2;

	const char* UserTagStart = tag1.c_str();
	const char* UserTagEnd = tag2.c_str();

	bool AreTagsSet = parser.setTags(UserTagStart, UserTagEnd);

	std::vector<std::string> myVec;

	char* new_line = (char*)line.c_str();
	parser.getDataBetweenTags(new_line, myVec);

	bool done = !myVec.empty();
	std::ofstream myoutputfile(OUTPUTFILENAME);
	if (done) {
		if (myoutputfile.is_open()) {
			for (unsigned i = 0; i < myVec.size(); i++)
				myoutputfile << myVec[i] << std::endl;
		}
	}
	else
		std::cout << "Data could not be found" << '\n';
	myoutputfile.close();
}
