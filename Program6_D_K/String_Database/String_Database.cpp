#include "..\includes\String_Database.h"
#include <algorithm>
#include <mutex>


String_Database::String_Database(void) {}

String_Database::~String_Database(void) {}

void String_Database::add(std::string &myString) {
	std::lock_guard<std::mutex> lock(mutex);

	bool exists = false;
	String_Data Data(myString, 1);

	for (this->myStringsIter = this->myStrings.begin(); this->myStringsIter != this->myStrings.end(); this->myStringsIter++) {
		if ((*this->myStringsIter) == Data) {
			exists = true;
			myStringsIter->increment();
			break;
		}
	}
	if (!exists) {
		myStrings.push_back(Data);
	}
}

int String_Database::getCount(std::string &myString) {
	
	std::lock_guard<std::mutex> lock(mutex);
	int counter = 0;
	
	String_Data DataCounter(myString, 1);
	for (this->myStringsIter = this->myStrings.begin(); this->myStringsIter != this->myStrings.end(); this->myStringsIter++) {
		if ((*this->myStringsIter) == DataCounter) {
			counter = myStringsIter->getCount();
			break;
		}
	}
	return counter;
}

void String_Database::clear() {
	std::lock_guard<std::mutex> lock(mutex);
	myStrings.clear();
}

bool String_Database::load(DataStore  *myDataStore) {
	std::lock_guard<std::mutex> lock(mutex);
	return myDataStore->load(myStrings);
}

bool String_Database::save(DataStore* myDataStore) {
	std::lock_guard<std::mutex> lock(mutex);
	return myDataStore->save(myStrings);
}