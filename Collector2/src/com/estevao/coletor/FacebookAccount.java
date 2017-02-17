package com.estevao.coletor;

import java.util.List;

public class FacebookAccount extends Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2102894702998547926L;
	private long likes;
	
	public FacebookAccount(){
		super();
		setLikes(0);
		super.setSocialnet("Facebook");
	}

	public FacebookAccount(String socialnet, String profile, String name, String country, List<String> category,
			String industry, List<String> related, long likes) {
		super(socialnet, profile, name, country, category, industry, related);
		this.likes = likes;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return super.toString()+";FacebookAccount [likes=" + likes + "]";
	}
	
	
	
}
