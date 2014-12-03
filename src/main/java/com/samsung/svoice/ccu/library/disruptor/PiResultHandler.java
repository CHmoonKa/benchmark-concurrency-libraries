package com.samsung.svoice.ccu.library.disruptor;

import java.util.concurrent.CountDownLatch;

import com.lmax.disruptor.EventHandler;
import com.samsung.svoice.ccu.library.PiJob;

public class PiResultHandler implements EventHandler<PiJob>{
  public long seq = 0;
  private final int jobNum;
  public final CountDownLatch latch;
  
  public PiResultHandler(int jobNum){
    this.jobNum = jobNum;
    latch = new CountDownLatch(1);
  }

  @Override
  public void onEvent(PiJob event, long sequence, boolean endOfBatch) throws Exception {
    ++seq;
    if (seq >= jobNum) {
      latch.countDown();
    }
  }

}
