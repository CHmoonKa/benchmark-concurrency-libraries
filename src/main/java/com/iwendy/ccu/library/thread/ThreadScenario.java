package com.iwendy.ccu.library.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

import com.iwendy.ccu.library.IScenario;
import com.iwendy.ccu.library.PiJob;

public class ThreadScenario implements IScenario{
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
  
  private long run(final int jobNum, final int iterNum) {
    final ExecutorService test = Executors.newFixedThreadPool(coreNum);
    
    final AtomicInteger latch = new AtomicInteger(jobNum);
    final long start = System.currentTimeMillis();
    
    for ( int i= 0; i< jobNum; i++) {
      final int fi = i;
        test.submit(new Runnable() {
            public void run() {
                PiJob pj = new PiJob(fi, iterNum);
                double res = 0.0D;
                do{
                  res = pj.calculatePi();
                }while(res <= 0.0D);
                
                latch.decrementAndGet();
            }
        });
    }
    while (latch.get() > 0 ) {
        LockSupport.parkNanos(100*500);
    }
    long end = System.currentTimeMillis();
    test.shutdownNow();
    try {
      test.awaitTermination(10, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      
    }
    return end - start;
  }
}
