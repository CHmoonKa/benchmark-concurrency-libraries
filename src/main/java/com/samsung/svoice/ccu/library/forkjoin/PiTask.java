package com.samsung.svoice.ccu.library.forkjoin;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

import com.samsung.svoice.ccu.library.PiJob;

public class PiTask extends RecursiveAction{
  private static final long serialVersionUID = 1L;
  private int since;
  private int iterNum;
  private AtomicInteger latch;
  
  public PiTask(int since, int iterNum,AtomicInteger latch){
    this.since = since;
    this.iterNum = iterNum;
    this.latch = latch;
  }
  
  @Override
  protected void compute() {
    PiJob pj = new PiJob(since, iterNum);
    double res = 0.0D;
    do{
      res = pj.calculatePi();
    }while(res <= 0.0D);
    
    latch.decrementAndGet();
  }

}
