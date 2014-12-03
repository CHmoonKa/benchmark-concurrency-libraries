package com.iwendy.ccu.library.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

import com.iwendy.ccu.library.IScenario;

public class FJScenario implements IScenario {
  int coreNum = Runtime.getRuntime().availableProcessors();
  
  @Override
  public long bm_100iterator_1MillionJob() {
    int jobNum = 1000000;
    int iterNum = 100;
    
    return run(jobNum, iterNum);
  }

  @Override
  public long bm_1kiterator_100kJob() {
    int jobNum = 100000;
    int iterNum = 1000;
    
    return run(jobNum, iterNum);
  }
  
  @Override
  public void setCoreNum(int core) {
    this.coreNum = core;
  }
  
  public long run(final int jobNum, final int iterNum) {
    ForkJoinPool fjp = new ForkJoinPool(
      coreNum,       //the parallelism level(such as thread number)
      ForkJoinPool.defaultForkJoinWorkerThreadFactory, //the factory for creating new threads. 
      null,          //the handler for internal worker threads
      true           // asyncMode for fork/join
    );
    
    final AtomicInteger latch = new AtomicInteger(jobNum);
    final long start = System.currentTimeMillis();
    
    for ( int i= 0; i< jobNum; i++) {
      PiTask pt = new PiTask(i, iterNum, latch);
      fjp.submit(pt);
    }
    while (latch.get() > 0 ) {
        LockSupport.parkNanos(100*500);
    }
    long end = System.currentTimeMillis();
    fjp.shutdownNow();
    try {
      fjp.awaitTermination(10, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
    }
    return end - start;
  }
}
