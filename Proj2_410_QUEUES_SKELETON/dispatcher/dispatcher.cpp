//Christian Volz and David Kinsley 10/21/2016
#include "..\includes\Dispatcher.h"
#include "..\includes\constants.h"
#include <queue>

PCB runningPCB;
std::queue<PCB> ready_Q;
std::queue<PCB> blocked_Q;

void dispatcher::init() {
	runningPCB.process_number = UNINITIALIZED;
	runningPCB.start_time = UNINITIALIZED;
	runningPCB.cpu_time = UNINITIALIZED;
	runningPCB.io_time = UNINITIALIZED;

	while (!ready_Q.empty()) ready_Q.pop();
	while (!blocked_Q.empty()) blocked_Q.pop();
}

PCB dispatcher::getCurrentJob() {
	return runningPCB;
}

void dispatcher::addJob(PCB &myPCB) {
	ready_Q.push(myPCB);
}

int dispatcher::processInterrupt(int interrupt) {
	//Checks to see if it is a switch process
	if (interrupt == SWITCH_PROCESS) {
		//Checks if readyq has values in it.
		if (!ready_Q.empty()) {
			PCB tmpPCB;
			tmpPCB = ready_Q.front();
			ready_Q.pop();
			//Checks to see if runningPCB is valid
			if (runningPCB.process_number != -5) {
				ready_Q.push(runningPCB);
			}
			runningPCB = tmpPCB;
			return PCB_SWITCHED_PROCESSES;
		}
		else {
			//checks if blockedq has values in it
			if (!blocked_Q.empty()) {
				return BLOCKED_JOBS;
			}
			else
				return NO_JOBS;
		}
	}
	//if interrupt is an io complete interrupt
	else {
		if (interrupt == IO_COMPLETE) {
			if (!blocked_Q.empty()) {
				while (!blocked_Q.empty()) {
					ready_Q.push(blocked_Q.front());
					blocked_Q.pop();
				}
				return PCB_MOVED_FROM_BLOCKED_TO_READY;
			}
			return PCB_BLOCKED_QUEUE_EMPTY;
		}
		return PCB_UNIMPLEMENTED;
	}
	return NO_JOBS;
}

int dispatcher::doTick() {
	//is there a runningPCB
	int returnval = 0;
	if (runningPCB.process_number != -5) {
		runningPCB.cpu_time--;
		//checks to see if job is finished
		if (runningPCB.cpu_time <= 0) {
			if (runningPCB.io_time == 1) {
				runningPCB.io_time--;
				blocked_Q.push(runningPCB);
				returnval = PCB_ADDED_TO_BLOCKED_QUEUE;
			}
			else {
				returnval = PCB_FINISHED;
			}
			runningPCB.process_number = UNINITIALIZED;
			runningPCB.start_time = UNINITIALIZED;
			runningPCB.cpu_time = UNINITIALIZED;
			runningPCB.io_time = UNINITIALIZED;
			return returnval;
		}
		//if no then return PCB_CPUTIME_DECRENTED
		else {
			return PCB_CPUTIME_DECREMENTED;
		}
	}
	else {
		//is readyQ empty
		if (ready_Q.empty()) {
			//yes, return no jobs if both queues are empty
			if (blocked_Q.empty()) {
				return NO_JOBS;
			}
			//or return blocked jobs if just ready q is empty
			else {
				return BLOCKED_JOBS;
			}
		}
		//if readyq isnt empty then load job into runningPCB
		else {
			runningPCB = ready_Q.front();
			ready_Q.pop();
			return PCB_MOVED_FROM_READY_TO_RUNNING;
		}
	}
	return NO_JOBS;
}