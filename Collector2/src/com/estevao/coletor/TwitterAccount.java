package com.estevao.coletor;

import java.util.List;

public class TwitterAccount extends Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6024842065970371120L;
	private long followers;
	private long following;
	private long tweets;

	public TwitterAccount() {
		super();
		setFollowers(0);
		setFollowing(0);
		setTweets(0);
		setSocialnet("Twitter");
	}

	public TwitterAccount(String socialnet, String profile, String name, String country, List<String> category,
			String industry, List<String> related, long followers, long following, long tweets) {
		super(socialnet, profile, name, country, category, industry, related);
		this.followers = followers;
		this.following = following;
		this.tweets = tweets;
	}

	public long getFollowers() {
		return followers;
	}

	public void setFollowers(long followers) {
		this.followers = followers;
	}

	public long getFollowing() {
		return following;
	}

	public void setFollowing(long following) {
		this.following = following;
	}

	public long getTweets() {
		return tweets;
	}

	public void setTweets(long tweets) {
		this.tweets = tweets;
	}

}
