//Josh Cohen 50% David Kinsley 50%

#include <string>
#include "stdlib.h"
#include "..\includes\externs.h"
#include "..\includes\datastructs.h"
#include "..\includes\Waiter.h"

using namespace std;

Waiter::Waiter(int id,std::string filename):id(id),myIO(filename){
}

Waiter::~Waiter()
{
}

//gets next Order(s) from file_IO
int Waiter::getNext(ORDER &anOrder){
	
	return myIO.getNext(anOrder);
}

void Waiter::beWaiter() {
	
	ORDER anOrder;

	lock_guard<mutex> lock(mutex_order_inQ);

	//ifstream file(DEFAULT_ORDER_IN_FILE);
	while (getNext(anOrder) == SUCCESS) {
		order_in_Q.push(anOrder);
	}
	
	b_WaiterIsFinished = true;
	cv_order_inQ.notify_all();

}

