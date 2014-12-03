package com.samsung.svoice.ccu.library.akka;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinRouter;

import com.samsung.svoice.ccu.library.IScenario;

public class AkkaScenario implements IScenario{
  int coreNum = Runtime.getRuntime().availableProcessors();
  
	@Override
	public long bm_100iterator_1MillionJob() {
		int jobNum = 1000000;
	    int iterNum = 100;
	    return startTest(jobNum, iterNum);
	}

	@Override
	public long bm_1kiterator_100kJob() {
		int jobNum = 100000;
	    int iterNum = 1000;
	    return startTest(jobNum, iterNum);
	}
	
	@Override
	  public void setCoreNum(int core) {
	    this.coreNum = core;
	  }
	
	@SuppressWarnings("deprecation")
	private long startTest(final int jobNum, final int iterNum){
		final AtomicInteger latch = new AtomicInteger(jobNum);
		ActorSystem system = ActorSystem.create("CalcSystem");
		ActorRef router = system.actorOf(Props.create(ActorWorker.class).withRouter(new RoundRobinRouter(coreNum)));
		
		MessageWorker[] mws = new MessageWorker[jobNum];
		for(int i=0;i<jobNum;i++){
			mws[i] = new MessageWorker(i, iterNum,latch);
		}
		
		// start actors
		long start = System.currentTimeMillis();
		for(int i=0;i<jobNum;i++){
			router.tell(mws[i]);
		}

		while (latch.get() > 0 ) {
	        LockSupport.parkNanos(100*500);
	    }
	    long end = System.currentTimeMillis();
		system.shutdown();
		return end-start;
	}

}
