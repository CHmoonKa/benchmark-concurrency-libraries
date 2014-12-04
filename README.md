benchmark-concurrency-libraries
===============================
## Overview
In order to make use of modern multi core/socket hardware, Java offers threads and locks for concurrent programming.

But, it is well known that this model suffers of a number of problems:
* Deadlocks
* Bad scaling due to "over-synchronization" or contention on common resources
* Errors caused by invalid synchronization are timing dependent and hard to reproduce. It is common they appear under special conditions (load, hardware) in production.
* As a software project grows, it gets hard to safely modify the application.

So there are some alternative models to express concurrency: Actors and Disruptor.
#### Actors
An Actor is like an object instance executed by a single thread. Threads cooperating by sending messages to each other who has it’s own message queue.

#### Disruptor
The disruptor is a bounded queue where producers add to the head of the queue (if slots are available, else the producer is blocked). Consumers access the queue at different points, so each consumer has its own read position (cursor) inside the queue. 

## Concurrency Libraries
| No | Item     |    Type    |    Remark                                        | Ok  |
| ---|:--------:| ----------:| ------------------------------------------------:|----:|
| 1  | Executors|Thread+Lock |  Since JDK 1.5                                   |  √  |
| 2  |   guava  |Thread+Lock |  Google common library                           |  ⤬  |
| 3  |   Akka   |   Actor    |  A popular actor framework                       |  √  |
| 4  | Disruptor|  Disruptor | High Performance Inter-Thread Messaging Library  |  √  |
| 5  |Fork/Join |Thread+Lock |  Since JDK 1.7                                   |  √  |
| 6  | jetlang  |  Actor     |  https://code.google.com/p/jetlang/              |  ⤬  |
| 7  | jactor   |  Actor     |  http://jactorconsulting.com/product/jactor/     |  ⤬  |
| 8  |  kilim   |  Actor     |  http://www.malhar.net/sriram/kilim/             |  ⤬  |
| 9  |  korus   |  Actor     |  https://code.google.com/p/korus/                |  ⤬  |
| 10 |   quasar |  Actor     |  https://github.com/puniverse/quasar             |  ⤬  |
| 11 |   actor  |  Actor     |  https://github.com/edescourtis/actor            |  ⤬  |
| 12 |  Jumi    |  Actor     |  http://jumi.fi/actors.html                      |  ⤬  |
| 13 |kontraktor|  Actor     |  https://github.com/RuedigerMoeller/kontraktor   |  ⤬  |

## Result
![images](https://github.com/CHmoonKa/benchmark-concurrency-libraries/blob/master/images/100k_jobs_1000_iter.png)

![images](https://github.com/CHmoonKa/benchmark-concurrency-libraries/blob/master/images/1Million_jobs_100_iter.png)
