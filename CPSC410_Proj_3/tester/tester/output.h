#pragma once

#ifndef OUTPUT_H_
#define OUTPUT_H_

#include <string>
#include <mutex>
#include <iostream>

const std::string SPACE = " ";

void PRINT1(std::string &txt);

void PRINT2(std::string &txt, std::string &txt1);

void PRINT3(std::string &txt, std::string &txt1, std::string &txt2);

void PRINT4(std::string &txt, std::string &txt1, std::string &txt2, std::string &txt3);

void PRINT5(std::string &txt, std::string &txt1, std::string &txt2, std::string &txt3, std::string &txt4);

#endif 
