package com.iwendy.ccu.library.disruptor;

import com.iwendy.ccu.library.PiJob;
import com.lmax.disruptor.EventTranslatorTwoArg;

public class PiEventTranslator implements EventTranslatorTwoArg<PiJob, Integer, Integer>{

  @Override
  public void translateTo(PiJob event, long sequence, Integer arg0, Integer arg1) {
    event.since = arg0;
    event.interatorNum = arg1;
    event.pi = 0.0D;
  }

}
