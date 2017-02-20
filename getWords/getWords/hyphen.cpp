//David Kinsley 100%

//passes all tests but doesn't compile in ssh
//doesn't like "stdafx.h" as well as a couple other header files
//couldn't find a solution other than deleting stdafx.h which made program unusable 
#include "stdafx.h"
#include <vector>
#include <sstream>

#define _ALT_SECURE_NO_WARNINGS


using namespace std;

//checks to see if character is a vowel
//if a,e,i,o,u, return true
//else return false
bool isVowel(char a) {
	if ((tolower(a) == 'a') || tolower(a) == 'e' || tolower(a) == 'i' ||
		tolower(a) == 'o' || tolower(a) == 'u' || tolower(a) == 'y') {
		return true;
	}
	return false;
}
//looks for exception characters 
//if characters are exception, return true
//else return false
bool hasException(char a, char b) {
	if (tolower(a) == 'q' && tolower(b) == 'u')
		return true;
	else if (tolower(a) == 't' && tolower(b) == 'r')
		return true;
	else if (tolower(a) == 'b' && tolower(b) == 'r')
		return true;
	else if (tolower(a) == 's' && tolower(b) == 't')
		return true;
	else if (tolower(a) == 's' && tolower(b) == 'l')
		return true;
	else if (tolower(a) == 'b' && tolower(b) == 'l')
		return true;
	else if (tolower(a) == 'c' && tolower(b) == 'r')
		return true;
	else if (tolower(a) == 'p' && tolower(b) == 'h')
		return true;
	else if (tolower(a) == 'c' && tolower(b) == 'h')
		return true;
	return false;
}
//checks for str, if found, return true
//else return false
bool hasException2(char a, char b, char c) {
	if (tolower(a) == 's' && tolower(b) == 't' && tolower(c) == 'r')
		return true;
	return false;
}

//checks for e followed by a blank space to signal the end of a word
//if it finds an 'e' and a space, return true
//else return false
bool checkForE2(char a, char b, char c, char d, char e) {
	if (a == 'e' && b == ' ')
		return true;
	else if (b == 'e' && c == ' ')
		return true;
	else if (c == 'e' && d == ' ')
		return true;
	else if (d == 'e' && e == ' ')
		return true;
	else
		return false;
	
}
//finds the size of the entire line so iterating through is easier
//returns the size
int getInputSize(string a) {
	int size = 0;

	while (a[size] != '\0') {
		size++;
	}
	return size;
}
//checks for the first pattern v/c/v
//if characters are v/c/v, return true
//else return false
bool checkPattern(char a, char b, char c) {
	if (isVowel(a) && (!isVowel(b) && b != ' ') && isVowel(c))
		return true;
	else
		return false;
}
//checking for vowel/consonant and not a space/consonant and not a space/vowel
bool checkPattern2(char a, char b, char c, char d) {
	if (isVowel(a) && (!isVowel(b) && b != ' ') && (!isVowel(c) && c != ' ') && isVowel(d))
		return true;
	else
		return false;
}
//checks for the end of the line
//necessary to ensure the program doesn't crash when the end 
//of the line is reached
bool checkForEnd(char a) {
	if (a == '\n')
		return true;
	else
		return false;
}

//checks for v/c/c/c so when called, can look for exceptions 
//necessary for 'La Mancha' for example
//'ancha' = v/c/c/c/v but the ch is an exception and counts as just one consonant 
//therefore a hyphen is needed before the ch
bool checkPattern3(char a, char b, char c, char d) {
	if (isVowel(a) && (!isVowel(b) && b != ' ')  && (!isVowel(c) && c != ' ') && (!isVowel(d) && d != ' ')) //check here
		return true;
	else 
		return false;
}

//goHyphen method does all the dirty work, it iterates through the line and looks for v/c/v and v/c/c/v
//and adds hyphens accordingly
char* goHyphen(const char* input) {
	string inputs = input;
	int sizeinputs = getInputSize(inputs);

	int hyphenCounter = 0;

	vector<int> locations;
	string word;
	for (int i = 0; i < sizeinputs-3; i++) {
	
			char tempA = inputs[i + 1];
			char tempB = inputs[i + 2];
			char tempC = inputs[i + 3];
			char tempD = inputs[i + 4];				

			//checks inputs for v/c/v and looks for exception characters 
			if (!checkForEnd(tempB) && checkPattern(inputs[i], tempA, tempB)
					|| isVowel(inputs[i]) && hasException(tempA, tempB) && isVowel(tempC)){
					//after entering loop, checks characters for e then space 
					//if it finds an e, it breaks
					if (checkForE2(i, tempA, tempB, tempC, tempD))
						continue;
					//if input[i] = 'u' and the input before is a 'q' and the next inputs are a c/v
					//continue
					else if (i > 0 && inputs[i] == 'u' && inputs[i-1] == 'q' 
						&& !isVowel(tempA) && isVowel(tempB)) {
						continue;
					}
					//otherwise add hyphen
					else {
						locations.push_back(i + 1);
						hyphenCounter++;
					}
				}
				//checks for v/c/c/v
				else if (!checkForEnd(tempC) && checkPattern2(inputs[i], tempA, tempB, tempC)) {
					//if tempA and B (and C) are an exception
					//add hyphen at i + 1
					if (hasException(tempA, tempB) || hasException2(i, tempA, tempB)) {
						locations.push_back(i + 1);
						hyphenCounter++;
					}
					 //otherwise add hyphen at i + 2
					else {
						locations.push_back(i + 2);
						hyphenCounter++;
					}
				}
				//checks for v/c/c/c, if tempB and tempC are an exception and tempD is a vowel, add hyphen 
				else if (checkPattern3(inputs[i], tempA, tempB, tempC)) {
					 if (hasException(tempB, tempC) && (isVowel(tempD))) {
						 locations.push_back(i+2);
						 hyphenCounter++;
					 }
				 }
			}
			//actually adds the hyphen into the new vector newChar
			char* newChar = new char[sizeinputs + hyphenCounter + 1];
			int z = 0;
			for (int m = 0; m < sizeinputs; m++) {
				for (size_t j = 0; j < locations.size(); j++) {
					if (locations[j] == m) {
						*(newChar + z) = '-';
						z++;
					}
				}
				*(newChar + z) = inputs[m];
				z++;
			}
			newChar[sizeinputs + hyphenCounter] = '\0';
			return newChar;

}






