## ISF Picking Scheduler
Program which purpose is to optimze the procces of assigning orders to each individual picker.
Orders are sorted in a way that:<br>
the order with the least amount of time left to be completed (completeBy)
and also with the least duration to complete the order (pickingTime)
is at the beginning of the queue.
The list of the pickers is sorted everytime a order is assigned to a picker, so that there is always an available person at the start of the list who is able to pick up another order from the queue of orders.


