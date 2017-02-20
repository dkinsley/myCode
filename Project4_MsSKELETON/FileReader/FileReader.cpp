#include <iostream>
#include <fstream>
#include "..\Include\FileReader.h"
#include "..\Include\constants.h"
#include "..\Include\Debug_Help.h"

using namespace KP_FileReaderClass;

FileReader::FileReader() : filecontents("") {}
FileReader::~FileReader() {}

	//TODO Fill this in
	int FileReader::getFileContents(const std::string filename, std::string &contents) {
		
		ReadTheWholeFile(filename);
		contents = this->filecontents;

		return SUCCEEDED;
	}

	int FileReader::ReadTheWholeFile(const std::string &filename) {
		std::ifstream myFile;
		std::string line = "";
		myFile.open(filename);
		
		if (!myFile.is_open())
			return COULD_NOT_OPEN_FILE;
		else {
			while (myFile.is_open()) {
				while (getline(myFile, line)) {
					filecontents += line;
				}
				myFile.close();
			}
		}
		
		return SUCCEEDED;
	}