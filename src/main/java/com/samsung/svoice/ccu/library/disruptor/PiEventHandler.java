package com.samsung.svoice.ccu.library.disruptor;

import com.lmax.disruptor.EventHandler;
import com.samsung.svoice.ccu.library.PiJob;

public class PiEventHandler implements EventHandler<PiJob> {

  @Override
  public void onEvent(PiJob event, long sequence, boolean endOfBatch) throws Exception {
    event.calculatePi();
  }

}
