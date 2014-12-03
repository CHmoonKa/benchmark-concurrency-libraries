package com.iwendy.ccu.library.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

import com.iwendy.ccu.library.IScenario;
import com.iwendy.ccu.library.PiJob;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class DisruptorScenario implements IScenario{
  int coreNum = Runtime.getRuntime().availableProcessors();

  @Override
  public long bm_100iterator_1MillionJob() {
    int jobNum = 1000000;
    int iterNum = 100;
    
    try {
      return run(jobNum, iterNum);
    } catch (InterruptedException e) {
      e.printStackTrace();
      return 0;
    }
  }

  @Override
  public long bm_1kiterator_100kJob() {
    int jobNum = 100000;
    int iterNum = 1000;
    
    try {
      return run(jobNum, iterNum);
    } catch (InterruptedException e) {
      e.printStackTrace();
      return 0;
    }
  }
  
  @Override
  public void setCoreNum(int core) {
    this.coreNum = core;
  }

  public long run(int jobNum, int iterNum) throws InterruptedException {
    PiEventFactory factory = new PiEventFactory();
    ExecutorService executor = Executors.newCachedThreadPool();
    
    // Specify the size of the ring buffer, must be power of 2.
    int bufferSize = 16384;
    
    Disruptor<PiJob> disruptor = new Disruptor<>(factory, bufferSize, executor,ProducerType.SINGLE,new SleepingWaitStrategy());
//    PiEventHandler handlers[] = new PiEventHandler[coreNum];
//    PiResultHandler resHandler = new PiResultHandler(jobNum);
    
    PiWorkHandler[] handlers = new PiWorkHandler[coreNum];
    final AtomicInteger latch = new AtomicInteger(jobNum);

    for (int i = 0; i < coreNum; i++) {
      handlers[i] = new PiWorkHandler(latch);
    }

    disruptor.handleEventsWithWorkerPool(handlers);
    disruptor.start();

    // publish task;
    final RingBuffer<PiJob> ringBuffer = disruptor.getRingBuffer();
    PiEventProducer producer = new PiEventProducer(ringBuffer);
   // PiEventNoTranslatorProducer producer = new PiEventNoTranslatorProducer(ringBuffer);
    
    long start = System.currentTimeMillis();
    for (int i= 0; i < jobNum; i++ ) {
       producer.onPublish(i, iterNum);
    }
    
    while(latch.get() > 0){
      LockSupport.parkNanos(100*500);
    }
    long end = System.currentTimeMillis();

    disruptor.shutdown();
    executor.shutdownNow();
    executor.awaitTermination(10, TimeUnit.SECONDS);
    return end - start;
  }
}
