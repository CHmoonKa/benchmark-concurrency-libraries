package com.iwendy.ccu.library.disruptor;

import com.iwendy.ccu.library.PiJob;
import com.lmax.disruptor.RingBuffer;

public class PiEventNoTranslatorProducer {
  private final RingBuffer<PiJob> ringBuffer;

  public PiEventNoTranslatorProducer(RingBuffer<PiJob> ringBuffer)
  {
      this.ringBuffer = ringBuffer;
  }
  
  public void onPublish(int since, int interatorNum) {
    // Grab the next sequence
    long sequence = ringBuffer.next();
    try {
      // Get the entry in the Disruptor for the sequence
      PiJob event = ringBuffer.get(sequence);
      event.interatorNum = interatorNum;
      event.since = since;
    } finally {
      ringBuffer.publish(sequence);
    }//finally
  }
}
