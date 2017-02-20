// David Kinsley
//427 Assignment 1.cpp : Defines the entry point for the console application.


#include "stdafx.h"
#include <fstream>
#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>

using namespace std;

const int SUCCESS = 0;
const int EXIT = SUCCESS - 1;

void extractTokensFromLine(string &myString) {
	ofstream output;
	
	stringstream ss(myString);
	string tempToken;
	string tempArr[sizeof(myString)];

	char CHAR_TO_SEARCH_FOR = ' ';

	int j = 0;
	int size = sizeof(myString);
	int i = 0;
	int numOfOccurences = 1;

	//alphabetizes the array
	while (getline(ss, tempToken, CHAR_TO_SEARCH_FOR)) {
		j = i;
		tempArr[j] = tempToken;
		
		while (j > 0 && tempToken < tempArr[j-1])
		{
			swap(tempArr[j-1], tempArr[j]);
			j--;
		}
		i++;	
	}
		
		output.open("outFile.txt");
		//counts the number of occurences 
		for (int k = 0; k < size; k++) {
			//checks for duplicates
			while (tempArr[k] == tempArr[k + 1] && tempArr[k] != "") {
				numOfOccurences++;
				tempArr[k + 1].erase();
				
				//clears out empty elements in arrays
				//moves elements forward in array if array[k] = ""
				if (tempArr[k+1] == "") {
					int n = k+1;
					while (n < 20) {
						tempArr[n] = tempArr[n + 1];
						n++;
					}
				}
			}
			
			//checks if it has reached the end of the array
			//if array[k] == "" && array[k+1] == "", it breaks
			if (tempArr[k] == "" && tempArr[k + 1] == "") {
				numOfOccurences = 0;
				break;
			}

			output << tempArr[k] << ": " << numOfOccurences << endl;
			numOfOccurences = 1;
		}
		output.close();

}
//in order for my program to work, there must be spaces after each line, otherwise
//the words dont have spaces and it reads as one whole word
int main()
{
	ifstream myFile;				
	string line;
	string filename = "TestData.txt";
	stringstream ss;

	myFile.open(filename);

	
	while (!myFile.is_open()) {
		cout << "The file was not found, enter the file name or press 'x' to exit" << endl;
		cin >> filename;
		if (filename == "x" || filename == "X")
			return EXIT;
		else
			myFile.open(filename);
		//reads in the file and turns it into a readable and manipulatable string
		ss << myFile.rdbuf();
		line = ss.str();
		//erases any instances of '\n'
		line.erase(remove(line.begin(), line.end(), '\n'), line.end());
		//lowers the case of all characters so it can be alphabetized easier
		transform(line.begin(), line.end(), line.begin(), ::tolower);
		//removes punctuation from line
		line.erase(remove_if(line.begin(), line.end(), ispunct), line.end());
		}
	
		//reads in the file and turns it into a readable and manipulatable string
		ss << myFile.rdbuf();	
		line = ss.str();
		
		//erases any instances of '\n'
		line.erase(remove(line.begin(), line.end(), '\n'), line.end());
		//erases any instances of '\r'
		line.erase(remove(line.begin(), line.end(), '\r'), line.end());
		//lowers the case of all characters so it can be alphabetized correctly
		transform(line.begin(), line.end(), line.begin(), ::tolower);
		//removes punctuation from line
		line.erase(remove_if(line.begin(), line.end(), ispunct), line.end());
		
	
	
	extractTokensFromLine(line);
	myFile.close();
	
    return SUCCESS;
}
