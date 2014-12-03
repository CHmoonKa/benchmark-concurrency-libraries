package com.samsung.svoice.ccu.library.disruptor;

import com.lmax.disruptor.EventTranslatorTwoArg;
import com.samsung.svoice.ccu.library.PiJob;

public class PiEventTranslator implements EventTranslatorTwoArg<PiJob, Integer, Integer>{

  @Override
  public void translateTo(PiJob event, long sequence, Integer arg0, Integer arg1) {
    event.since = arg0;
    event.interatorNum = arg1;
    event.pi = 0.0D;
  }

}
