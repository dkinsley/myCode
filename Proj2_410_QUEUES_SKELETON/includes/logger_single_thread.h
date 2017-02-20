#pragma once
#include "constants.h"

namespace ST_LOG {
	void log(int tickcount, int process_number);
	void save(const char* filename = LOG_FILE);
}

