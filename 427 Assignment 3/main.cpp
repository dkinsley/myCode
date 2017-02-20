//main.cpp
//David Kinsley


#include "stdafx.h"
#include <vector>
#include "arrayStuff.h"
#include "vectorStuff.h"
#include <fstream>
#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>


using namespace std;

int main() {
	vector<vectorStruct> myV(100);
	
	ifstream myFile;
	
	string line =  "";
	string filename;
	string tempToken;
	
	LARGE_INTEGER VectorFrequency;
	LARGE_INTEGER v1, v2;
	double VectorElapsedTime;

	LARGE_INTEGER arrayFrequency;
	LARGE_INTEGER a1, a2;
	double ArrayElapsedTime;

	filename = "TestData.txt";
	myFile.open(filename);

	stringstream ss;

	//reads in the file and turns it into a readable and manipulatable string
	ss << myFile.rdbuf();
	line = ss.str();

	//erases any instances of '\n'
	line.erase(remove(line.begin(), line.end(), '\n'), line.end());

	//gets rid of capitalizations
	transform(line.begin(), line.end(), line.begin(), ::tolower);

	//removes punctuation from line
	line.erase(remove_if(line.begin(), line.end(), ispunct), line.end());

	
	//determine performace for array
	QueryPerformanceFrequency(&arrayFrequency);
	QueryPerformanceCounter(&a1);
	
	//calls the functions from array.cpp
	alphabetizeAscending(line);
	alphabetizeDescending(line);
	countNumOfOcc(line);
		
	QueryPerformanceCounter(&a2);

	ArrayElapsedTime = ((a2.QuadPart - a1.QuadPart) * 100.0 / arrayFrequency.QuadPart);
	
	//determine performance for vector
	QueryPerformanceFrequency(&VectorFrequency);
	QueryPerformanceCounter(&v1);

	//calls the functions from vector.cpp
	populateandCountNumOfOccurence(myV, line);
	sortAscending(myV);
	deleteDuplicates(myV);
	sortDescending(myV);
	SortByOccurence(myV);
	
	QueryPerformanceCounter(&v2);

	VectorElapsedTime = (v2.QuadPart - v1.QuadPart) * 100.0 / VectorFrequency.QuadPart;


	cout << "Array Sorting takes about " << ArrayElapsedTime << " seconds." << endl;

	cout << "Vector Sorting takes about " << VectorElapsedTime << " seconds."<< endl;

	cout << "Vector sort is about " << VectorElapsedTime / ArrayElapsedTime << " faster." << endl;
	myFile.close();

	return SUCCESS;
}