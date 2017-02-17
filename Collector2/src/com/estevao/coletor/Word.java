package com.estevao.coletor;

public class Word {
	private String URL;
	private String word;
	
	public Word(String URL, String word){
		this.URL = URL;
		this.word = word;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	

}
