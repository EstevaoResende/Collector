package com.estevao.coletor;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Accounts {
	private ConcurrentLinkedQueue<Account> resource;
	private boolean finished;

	public Accounts(){
		resource = new ConcurrentLinkedQueue<>();
		finished = false;
	}
	
	public synchronized Account getElement(){
		if (resource.isEmpty() && !finished)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return resource.poll();
	}
	
	public synchronized void putElement(Account account){
		resource.add(account);
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
