package com.iwendy.ccu.library.disruptor;

import com.iwendy.ccu.library.PiJob;
import com.lmax.disruptor.EventHandler;

public class PiEventHandler implements EventHandler<PiJob> {

  @Override
  public void onEvent(PiJob event, long sequence, boolean endOfBatch) throws Exception {
    event.calculatePi();
  }

}
