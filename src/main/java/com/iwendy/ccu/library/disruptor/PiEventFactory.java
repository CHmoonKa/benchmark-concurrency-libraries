package com.iwendy.ccu.library.disruptor;

import com.iwendy.ccu.library.PiJob;
import com.lmax.disruptor.EventFactory;

public class PiEventFactory implements EventFactory<PiJob>
{

  @Override
  public PiJob newInstance() 
  {
    return new PiJob();
  }

}
