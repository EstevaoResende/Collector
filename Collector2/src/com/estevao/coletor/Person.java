package com.estevao.coletor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7674036901523770384L;
	private String name;
	private List<Account> accounts;
	
	public Person() {
		super();
		name = "";
		accounts = new ArrayList<>();
	}
	public Person(String name, List<Account> accounts) {
		super();
		this.name = name;
		this.accounts = accounts;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	@Override
	public String toString() {
		String names="";
		for (Account account: accounts)
			names+=account.getName()+"="+account.getSocialnet()+";";
		return "Person [name=" + name + ", accounts=(" + names + ")]";
	}
	
	
	

}
