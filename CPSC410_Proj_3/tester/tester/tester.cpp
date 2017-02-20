// tester.cpp : Defines the entry point for the console application.
//David Kinsley 50% and Josh Cohen 50%

#include "stdafx.h"
#include "output.h"
#include <thread>

int main()
{
	std::string str = "some";
	std::string str1 = "data";
	std::string str2 = "for";
	std::string str3 = "my";
	std::string str4 = "friends";


	std::thread t1(PRINT1, str);
	std::thread t2(PRINT2, str, str1);
	std::thread t3(PRINT3, str, str1, str2);
	std::thread t4(PRINT4, str, str1, str2, str3);
	std::thread t5(PRINT5, str, str1, str2, str3, str4);

	t1.join();
	t2.join();
	t3.join();
	t4.join();
	t5.join();

    return 0;
}

