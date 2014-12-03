benchmark-concurrency-libraries
===============================
## Overview
In order to make use of modern multi core/socket hardware, Java offers threads and locks for concurrent programming.

But, it is well known that this model suffers of a number of problems:
*Deadlocks*
*Bad scaling due to "over-synchronization" or contention on common resources*
*Errors caused by invalid synchronization are timing dependent and hard to reproduce. It is common they appear under special conditions (load, hardware) in production.*
*As a software project grows, it gets hard to safely modify the application.*

So there are some alternative models to express concurrency: Actors and Disruptor.
###Actors
An Actor is like an object instance executed by a single thread. Threads cooperating by sending messages to each other who has it’s own message queue.

###Disruptor
The disruptor is a bounded queue where producers add to the head of the queue (if slots are available, else the producer is blocked). Consumers access the queue at different points, so each consumer has its own read position (cursor) inside the queue. 
