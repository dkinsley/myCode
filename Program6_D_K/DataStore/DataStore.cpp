#include <string>
#include <iostream>
#include "DataStore.h"
#include "../includes/Crypto_AES.h"


DataStore::DataStore(Crypto* myCrypto):myCrypto(myCrypto){}

DataStore::~DataStore(void) {}

bool DataStore::encrypt(std::string &myString) {
	bool isComplete;
	if (myCrypto != NULL) {
		(*myCrypto).encrypt(myString);
		isComplete = true;
	}
	else
		isComplete = false;
	return isComplete;

}

bool DataStore::decrypt(std::string &myString) {
	bool isComplete = true;
	if (this->myCrypto != NULL) {
		(*myCrypto).decrypt(myString);
		isComplete = true;
	}
	else
		isComplete = false;
	return isComplete;
}

bool DataStore::load(std::vector<String_Data> &myVector) {
	for (std::vector<String_Data>::iterator myVec = myVector.begin(); myVec != myVector.end(); myVec++ ) {
		decrypt(myVec->serialize());
	}
	return true;
}

bool DataStore::save(std::vector<String_Data> &myVector) {
	for (std::vector<String_Data>::iterator myVec = myVector.begin(); myVec != myVector.end(); myVec++) {
		encrypt(myVec->serialize());
	}
	return true;
}
