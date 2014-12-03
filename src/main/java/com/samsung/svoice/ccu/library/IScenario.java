package com.samsung.svoice.ccu.library;

public interface IScenario {

  /**
   * 1. The task is calculate Pi as {@link PiJob}.
   * 2. each job will iterator 100 times.
   * 3. there are 1000000 job
   * 
   * @return the whole time in milliseconds(MS)
   */
  long bm_100iterator_1MillionJob();

  /**
   * 1. The task is calculate Pi as {@link PiJob}.
   * 2. each job will iterator 1000 times.
   * 3. there are 100000 job
   * 
   * @return the whole time in milliseconds(MS)
   */
  long bm_1kiterator_100kJob();
  
  /**
   * Sets number of thread
   * 
   * @param core the number of CPU
   */
  void setCoreNum(int core);
  
}
