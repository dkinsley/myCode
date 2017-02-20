//Josh Cohen 50% David Kinsley 50%

#include <mutex>
#include <math.h>
#include "..\includes\datastructs.h"
#include "..\includes\externs.h"
#include "..\includes\Baker.h"

using namespace std;

Baker::Baker(int id):id(id)
{
}

Baker::~Baker()
{
}

//boxes and creates the boxes
void CreateBox(int numdonuts, ORDER &order) {
	
	if (numdonuts > 12)
	{
		numdonuts = numdonuts - 12;
		Box box;
		for (int i = 0; i < 12; i++)
		{
			DONUT donut;
			box.addDonut(donut);
		}
		order.boxes.push_back(box);
		CreateBox(numdonuts, order);
	}
	else if (numdonuts > 0)
	{
		Box box;
		for (int i = 0; i < numdonuts; i++)
		{
			DONUT donut;
			box.addDonut(donut);
		}
		order.boxes.push_back(box);
	}
}

//recusively calls create box to make and box the donuts
void Baker::bake_and_box(ORDER &anOrder) {
	CreateBox(anOrder.number_donuts, anOrder);
}

//runs through order in q and calls bakeandbox() 
void Baker::beBaker() {
	

		unique_lock<mutex> unique(mutex_order_inQ);
		cv_order_inQ.wait(unique, []() {return !order_in_Q.empty() || b_WaiterIsFinished == true; });
		
		while (!order_in_Q.empty()) {
			ORDER temp = order_in_Q.front();

			bake_and_box(temp);
			order_in_Q.pop();

			lock_guard<mutex> m(mutex_order_outQ);
			order_out_Vector.push_back(temp);

			
			//anOrder.number_donuts = 0;
			
		}
}
