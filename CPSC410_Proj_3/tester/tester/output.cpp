//David Kinsley 50% and Josh Cohen 50%

#include "stdafx.h"
#include "output.h"

std::mutex m;

void PRINT1(std::string &txt) {
	m.lock();

	std::cout << txt << std::endl;

	m.unlock();
}

void PRINT2(std::string &txt, std::string &txt1) {
	m.lock();

	std::cout << txt << SPACE << txt1 << std::endl;

	m.unlock();
}

void PRINT3(std::string &txt, std::string &txt1, std::string &txt2) {
	m.lock();

	std::cout << txt << SPACE << txt1 << SPACE << txt2 << std::endl;

	m.unlock();
}

void PRINT4(std::string &txt, std::string &txt1, std::string &txt2, std::string &txt3) {
	m.lock();

	std::cout << txt << SPACE << txt1 << SPACE << txt2 << SPACE << txt3 << std::endl;

	m.unlock();
}

void PRINT5(std::string &txt, std::string &txt1, std::string &txt2, std::string &txt3, std::string &txt4) {
	m.lock();

	std::cout << txt << SPACE << txt1 << SPACE << txt2 << SPACE << txt3 << SPACE << txt4 << std::endl;

	m.unlock();
}