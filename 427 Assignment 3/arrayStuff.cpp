// 427 Assignment 3.cpp : Defines the entry point for the console application.
//David Kinsley

#include "stdafx.h"
#include "arrayStuff.h"
#include <fstream>
#include <string>
#include <sstream>

using namespace std;

ofstream OutputArray;

/******************************************************************************

THIS METHOD IS FOR THE ALPHABETIZING DESCENDING ARRAY 

*******************************************************************************/
void alphabetizeAscending(const string &myString) {
	OutputArray.open("OutputArray.txt");
	stringstream ss(myString);
	string tempToken;
	string ascendingArr[20];

	int j = 0;
	int i = 0;
	
	while (getline(ss, tempToken, CHAR_TO_SEARCH_FOR)) {

		j = i;
		ascendingArr[j] = tempToken;

		//alphabetizes the array in ascending order 
		while (j > 0 && tempToken < ascendingArr[j - 1])
		{
			swap(ascendingArr[j - 1], ascendingArr[j]);
			j--;
		}
		i++;
	}
	int q = 0;
	while(q < 20) {
		while (ascendingArr[q] == ascendingArr[q + 1] && ascendingArr[q] != "") {
			ascendingArr[q + 1].erase();
			//clears out empty elements in arrays
			//moves elements forward in array if array[q] = ""
			if (ascendingArr[q + 1] == "") {
				int n = q + 1;
				while (n < 19) {
					ascendingArr[n] = ascendingArr[n + 1];
					n++;
				}
			}
		}
		//checks if it has reached the end of the array
		//if array[q] == "" && array[q+1] == "", it breaks
		if (ascendingArr[q] == "" && ascendingArr[q + 1] == "")
			break;
		OutputArray << "Ascending Array: " << ascendingArr[q] << endl;
		q++;
	}
	OutputArray << endl;
}

/******************************************************************************

THIS METHOD IS FOR THE ALPHABETIING DESCENDING ARRAY 

*******************************************************************************/

void alphabetizeDescending(const string &myString){
	
	string descendingArr[20];
	string tempToken;
	stringstream strstr(myString);

	int a = 0;
	int b = 0;

	while (getline(strstr, tempToken, CHAR_TO_SEARCH_FOR)) {
		a = b;
		descendingArr[a] = tempToken;
		// alphabetizes the array in descending order 
		while (a > 0 && tempToken > descendingArr[a - 1])
		{
			swap(descendingArr[a - 1], descendingArr[a]);
			a--;
		}
		b++;
	}
	int q = 0;
	while (q < 20) {
		while (descendingArr[q] == descendingArr[q + 1] && descendingArr[q] != "") {
			descendingArr[q + 1].erase();
			//clears out empty elements in arrays
			//moves elements forward in array if array[q] = ""
			if (descendingArr[q + 1] == "") {
				int n = q + 1;
				while (n < 19) {
					descendingArr[n] = descendingArr[n + 1];
					n++;
				}
			}
		}
		//checks if it has reached the end of the array
		//if array[q] == "" && array[q+1] == "", it breaks
		if (descendingArr[q] == "" && descendingArr[q + 1] == "")
			break;
		OutputArray << "Descending Array: " << descendingArr[q] << endl;
		q++;
	}
	OutputArray << endl;
}

/******************************************************************************

THIS METHOD IS FOR THE COUNTING OF OCCURENCES

*******************************************************************************/

void countNumOfOcc(const string &myString) {
	string tempArr[sizeof(myString)];
	string tempToken;
	int mySize = sizeof(myString);
	int numOfOccurences = 1;
	int o = 0;
	int h = 0;

	stringstream ss(myString);

	holderStruct str[20];
	while (getline(ss, tempToken, CHAR_TO_SEARCH_FOR)) {
		o = h;
		tempArr[o] = tempToken;
		
		while (o > 0 && tempToken < tempArr[o - 1])
		{
			swap(tempArr[o - 1], tempArr[o]);
			o--;
		}
		h++;
	}
	//counts the number of occurences in ascending array
	for (int k = 0; k < mySize; k++) {

		//checks for duplicates
		while (tempArr[k] == tempArr[k + 1] && tempArr[k] != "") {
			numOfOccurences++;
			tempArr[k + 1].erase();

			//clears out empty elements in arrays
			//moves elements forward in array if array[k] = ""
			if (tempArr[k + 1] == "") {
				int n = k + 1;
				while (n < 20) {
					tempArr[n] = tempArr[n + 1];
					n++;
				}
			}
		}
		str[k].word = tempArr[k];
		str[k].occurence = numOfOccurences;
		numOfOccurences = 1;
		//checks if it has reached the end of the array
		//if array[k] == "" && array[k+1] == "", it breaks
		if (tempArr[k] == "" && tempArr[k + 1] == "") {
			numOfOccurences = 0;
			break;
		}
	}
	int q = 0;
	int w = 0;
	while (str[q].word != "") {		
			q = w;
			while (q > 0 && str[q].occurence > str[q - 1].occurence) {
				swap(str[q].word, str[q - 1].word);
				swap(str[q].occurence, str[q - 1].occurence);
				q--;
		}
		w++;
	}
	int y = 0;
	while (str[y].word != "") {
		OutputArray << "Occurence: " << str[y].occurence  << " - Word: " << str[y].word << endl;
		y++;
	}
	OutputArray.close();
}

