package com.iwendy.ccu.library.disruptor;

import java.util.concurrent.atomic.AtomicInteger;

import com.iwendy.ccu.library.PiJob;
import com.lmax.disruptor.WorkHandler;

public class PiWorkHandler implements WorkHandler<PiJob> {
  public final AtomicInteger latch;
  
  public PiWorkHandler(AtomicInteger latch){
    this.latch = latch;
  }
  @Override
  public void onEvent(PiJob event) throws Exception {
    double res = 0.0D;
    do{
      res = event.calculatePi();
    }while(res <= 0.0D);
    
    latch.decrementAndGet();
  }

}
