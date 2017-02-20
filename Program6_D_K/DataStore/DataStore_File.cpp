#include <fstream>
#include <iostream>
#include "../includes/DataStore_File.h"

DataStore_File::DataStore_File(std::string fname, Crypto* myCrypto) : DataStore(myCrypto) {
	myFileName = fname;
}

DataStore_File::~DataStore_File(void) {}

bool DataStore_File::openFile(std::fstream& myfile, const std::string& myFileName, std::ios_base::openmode mode) {
	myfile = std::fstream(myFileName, mode);

	if (!myfile) {
		return false;
	}
	return true;
}

void DataStore_File::closeFile(std::fstream& myfile) {
	myfile.close();
}

bool DataStore_File::load(std::vector<String_Data> &myVector) {
	std::fstream file;
	this->openFile(file, myFileName);

	std::string line;
	while (std::getline(file, line)) {
		std::string parse("");
		int count = 0;

		DataStore::decrypt(line);
		String_Data::parseData(line, parse, count);
		String_Data stringData(parse, count);

		myVector.push_back(stringData);
	}
	this->closeFile(file);
	return true;
}

bool DataStore_File::save(std::vector<String_Data> &myVector) {
	std::fstream outputFile;
	this->openFile(outputFile, this->myFileName, std::ios_base::out);

	for (String_Data data : myVector) {
		std::string serialized = data.serialize();
		encrypt(serialized);

		outputFile << serialized << std::endl;
	}
	this->closeFile(outputFile);
	return true;
}