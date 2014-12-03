package com.iwendy.ccu.library.akka;

import java.util.concurrent.atomic.AtomicInteger;

public class MessageWorker {
	
	int since;
	int iterNum;
	AtomicInteger latch;
	
	public MessageWorker(int since, int iterNum,AtomicInteger latch){
		this.since  = since;
		this.iterNum = iterNum;
		this.latch = latch;
	}
	
	public int getSince(){
		return this.since;
	}
	
	public int getIterNum(){
		return this.iterNum;
	}
	
	public AtomicInteger getAtomicInteger(){
		return this.latch;
	}
	
}
