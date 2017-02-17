package com.estevao.coletor;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Words {
	private ConcurrentLinkedQueue<Word> resource;
	private boolean finished;

	public Words(){
		resource = new ConcurrentLinkedQueue<>();
		finished = false;
	}
	
	public synchronized  Word getElement(){
		if (resource.isEmpty() && !finished)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return resource.poll();
	}
	
	public synchronized void putElement(Word word){
		resource.add(word);
		notify();
	}
	public boolean isFinished(){
		return finished&&resource.isEmpty();
	}
	
	public synchronized void setFinished(boolean f){
		finished = f;
		notifyAll();
	}
	
	

}
