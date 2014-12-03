package com.samsung.svoice.ccu.library.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.samsung.svoice.ccu.library.PiJob;

public class PiEventProducer {
  private final RingBuffer<PiJob> ringBuffer;
  private static final PiEventTranslator TRANSLATOR = new PiEventTranslator();

  public PiEventProducer(RingBuffer<PiJob> ringBuffer)
  {
      this.ringBuffer = ringBuffer;
  }
  
  public void onPublish(int since, int interatorNum){
    ringBuffer.publishEvent(TRANSLATOR, since,interatorNum);
  }
}
