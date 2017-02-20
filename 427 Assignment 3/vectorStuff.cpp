//David Kinsley

#include "stdafx.h"
#include "vectorStuff.h"
#include <vector>
#include <fstream>
#include <string>
#include <sstream>

using namespace std;

vector<string> vec;
ofstream OutputVector;

/*************************************************************************************************

FILLS UP THE ARRAY AND GIVES EVERY WORD AN OCCURENCE COUNT

**************************************************************************************************/
vector<vectorStruct> populateandCountNumOfOccurence(vector<vectorStruct> &a, string &b) {
	stringstream ss(b);
	string tempToken;
	int tempOcc = 1;
	int c = 0;
	int i = 0;
	int n = 0;
	while (getline(ss, tempToken, CHAR_TO_SEARCH_FOR2)) {	
		//const char* d = tempToken.c_str();
		a[c].VecWord += tempToken;
		a[c].occ = tempOcc;
		c++;
	}
	a.resize(c);
	return a;
}

vector<vectorStruct> sortAscending(vector<vectorStruct> &a) {
	OutputVector.open("OutputVector.txt");
	int i = 0;
	int	q = 0;
	for (unsigned i = 0; i < size(a); ++i) {
		q = i;
		while (q > 0 && a[q].VecWord < a[q - 1].VecWord) {
			swap(a[q-1].VecWord, a[q].VecWord);
			--q;
		}
	}

	return a;
}

/************************************************************************************************

ITERATES THROUGH VECTOR AND DELETES DUPLICATES, CONSOLIDATES OCCURENCE COUNT ACCORDINGLY

*************************************************************************************************/
vector<vectorStruct> deleteDuplicates(vector<vectorStruct> &a) {
	int v = 0;
	//int i = -1;
	int size = a.size();
	for (int i = 0; i < size; i++) {
	//if(i < sizeof(a)){
		while (i+1 < size && a[i].VecWord == a[i + 1].VecWord) {
			
			a.erase(a.begin() + (i + 1));
			a[i].occ++;
		}
		size = a.size();
		OutputVector << "Ascending Array: " << a[i].VecWord << endl;
	}
	OutputVector << endl;
	return a;
}

/************************************************************************************************

SORTS VECTOR BY DESCENDING ALPHABETIZATION 

************************************************************************************************/
vector<vectorStruct> sortDescending(vector<vectorStruct> &a) {
	int	q = 0;
	for (int i = a.size(); i > 0; i--) {
		OutputVector << "Descending Array: " << a[i-1].VecWord << endl;
	}
	OutputVector << endl;
	return a;
}

/**********************************************************************************************

SORTS VECTOR BY OCCURENCE 

***********************************************************************************************/
vector<vectorStruct> SortByOccurence(vector<vectorStruct> &a) {
	int g = 0;
	for (size_t i = 0; i < a.size()-1; i++) {
		g = i;
		while (g > 0 && a[g].occ < a[g + 1].occ) {
			swap(a[g].VecWord, a[g+1].VecWord);
			swap(a[g].occ, a[g+1].occ);
			g--;
		}
	}

	for (size_t z = 0; z < a.size(); z++) {
		OutputVector << "Number of Occurence: " << a[z].occ << " - Descending Array: " << a[z].VecWord << endl;
	}
	OutputVector << endl;
	OutputVector.close();
	return a;
}
