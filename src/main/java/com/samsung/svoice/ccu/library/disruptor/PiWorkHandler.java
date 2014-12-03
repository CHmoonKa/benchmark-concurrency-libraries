package com.samsung.svoice.ccu.library.disruptor;

import java.util.concurrent.atomic.AtomicInteger;

import com.lmax.disruptor.WorkHandler;
import com.samsung.svoice.ccu.library.PiJob;

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
