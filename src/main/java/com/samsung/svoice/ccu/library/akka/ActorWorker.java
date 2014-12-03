package com.samsung.svoice.ccu.library.akka;

import com.samsung.svoice.ccu.library.PiJob;

import akka.actor.UntypedActor;

public class ActorWorker extends UntypedActor {
	
  @Override
  public void onReceive(Object arg0) throws Exception {
	  if(arg0 instanceof MessageWorker){
			  PiJob pj = new PiJob(((MessageWorker) arg0).getSince(), ((MessageWorker) arg0).getIterNum());
              double res = 0.0D;
              do{
                res = pj.calculatePi();
              }while(res <= 0.0D);
		  ((MessageWorker) arg0).getAtomicInteger().decrementAndGet();
	  }
  }
}
