#include <string>
#include "..\Include\stringparserClass.h"
#include "..\Include\FileReader.h"
#include "..\Include\constants.h"
#include "..\Include\Debug_Help.h"

using namespace std;
using namespace KP_StringParserClass;

StringParserClass::StringParserClass() : pStartTag(NULL), pEndTag(NULL), areTagsSet(NULL), lastError(ERROR_NO_ERROR) {}
StringParserClass::~StringParserClass() {}
//TODO Fill this in

int StringParserClass::getLastError() {
	int temp = this->lastError;
	this->lastError = ERROR_NO_ERROR;
	return temp;
}

bool StringParserClass::setTags(const char *pStartTag, const char *pEndTag) {
	if (pStartTag != NULL && pEndTag != NULL) {
		this->areTagsSet = true;
		this->pStartTag = (char*)pStartTag;
		this->pEndTag = (char*)pEndTag;
	}
	else {
		this->areTagsSet = false;
		return ERROR_TAGS_NULL;
	}

	return this->areTagsSet;
}

bool StringParserClass::getDataBetweenTags(char *pDataToSearchThru, vector<string> &myVector) {

	int SizeOfpDataToSearchThru = strlen(pDataToSearchThru);

	std::string data;

	bool isEmpty = myVector.empty();
	bool found = false;
	
	for (int i = 0; i < SizeOfpDataToSearchThru; i++) {
		if (pStartTag[0] == *(pDataToSearchThru) && (memcmp(pStartTag, (pDataToSearchThru + i), strlen(pStartTag)) == 0)) {
			i += strlen(pStartTag) - 1;
			found = true;
		}
		else if (found) {
			if (pEndTag[0] == *(pDataToSearchThru + i) && (memcmp(pEndTag, (pDataToSearchThru + i), strlen(pEndTag)) == 0)) {
				
				i += strlen(pEndTag) - 1;
				found = false;
			}
			else
				data += *(pDataToSearchThru + i);
		}
	}
	myVector.push_back(data);
	return isEmpty;
}

void StringParserClass::cleanup() {
	this->pStartTag = NULL;
	this->pEndTag = NULL;
	this->lastError = ERROR_NO_ERROR;
	this->areTagsSet = false;
}
