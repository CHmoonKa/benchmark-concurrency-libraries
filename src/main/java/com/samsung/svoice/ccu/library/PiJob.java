package com.samsung.svoice.ccu.library;

public class PiJob {
  public double pi = 0.0D;
  public int since;
  public int interatorNum;
  
  public PiJob(){
    this.since = 0;
    this.interatorNum = 0;
  }
  
  public PiJob(int since, int interatorNum){
    this.since = since;
    this.interatorNum = interatorNum;
  }

  /**
   * 1. pi is the result
   * 2. since control the start position of i
   * 3. interatorNum is the loop times of for statement
   */
  public double calculatePi() {
      for (int i = since * interatorNum; i <= ((since + 1) * interatorNum - 1); i++) {
        pi += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1);
      }
      return pi;
  }
}
