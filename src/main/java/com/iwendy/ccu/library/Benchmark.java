package com.iwendy.ccu.library;

import com.iwendy.ccu.library.akka.AkkaScenario;
import com.iwendy.ccu.library.disruptor.DisruptorScenario;
import com.iwendy.ccu.library.forkjoin.FJScenario;
import com.iwendy.ccu.library.thread.ThreadScenario;

public class Benchmark {

  /**
   * @param args[] = {type, test scenario, maybe core num}
   * @param type = {1:disruptor, 2:akka, 3:fork/join, 4:thread}
   * @param scenario = {1:bm_1kiterator_10kJob, 2:bm_100iterator_100kJob}
   */
  public static void main(String[] args) {
    // for benchmark
    //bm(args);
    
    // for test
    test2();
  }
  
  static void test1(){
    IScenario disruptor = new DisruptorScenario();
    IScenario akka = new AkkaScenario();
    IScenario fj = new FJScenario();
    IScenario thread = new ThreadScenario();
    
    System.out.println("Disruptor: "+ disruptor.bm_1kiterator_100kJob());
    System.out.println("Akka: " + akka.bm_1kiterator_100kJob());
    System.out.println("Fork/Join: " + fj.bm_1kiterator_100kJob());
    System.out.println("Executors: " + thread.bm_1kiterator_100kJob());
  }
  
  static void test2(){
    IScenario disruptor = new DisruptorScenario();
    IScenario akka = new AkkaScenario();
    IScenario fj = new FJScenario();
    IScenario thread = new ThreadScenario();
    
    System.out.println("Disruptor: "+ disruptor.bm_100iterator_1MillionJob());
    System.out.println("Akka: " + akka.bm_100iterator_1MillionJob());
    System.out.println("Fork/Join: " + fj.bm_100iterator_1MillionJob());
    System.out.println("Executors: " + thread.bm_100iterator_1MillionJob());
  }
  
  static void bm(String[] args){
    int type = Integer.parseInt(args[0]);
    int scenario = Integer.parseInt(args[1]);
    
    IScenario bm = getType(type);
    if(args.length >= 3){
      bm.setCoreNum(Integer.parseInt(args[2]));
    }
    
    switch (scenario) {
      case 1:
        System.out.println(bm.bm_1kiterator_100kJob());
        break;
      case 2:
        System.out.println(bm.bm_100iterator_1MillionJob());
        break;
      default:
        break;
    }
  }

  static IScenario getType(int type){
    switch (type) {
      case 1:
        return new DisruptorScenario();
      case 2:
        return new AkkaScenario();
      case 3:
        return new FJScenario();
      case 4:
        return new ThreadScenario();

      default:
        return null;
    }
  }
}
