package com.samsung.svoice.ccu.library.disruptor;

import com.lmax.disruptor.EventFactory;
import com.samsung.svoice.ccu.library.PiJob;

public class PiEventFactory implements EventFactory<PiJob>
{

  @Override
  public PiJob newInstance() 
  {
    return new PiJob();
  }

}
