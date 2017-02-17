package com.estevao.coletor;

import java.util.List;

public class YoutubeAccount extends Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = -351565330815323072L;
	private long subscribers;
	private long videos;
	private long totalViews;
		
	public YoutubeAccount(String socialnet, String profile, String name, String country, List<String> category,
			String industry, List<String> related, long subscribers, long videos, long totalViews) {
		super(socialnet, profile, name, country, category, industry, related);
		this.setSubscribers(subscribers);
		this.setVideos(videos);
		this.setTotalViews(totalViews);
	}
	public YoutubeAccount() {
		super();
		super.setSocialnet("Youtube");
		this.setSubscribers(0);
		this.setVideos(0);
		this.setTotalViews(0);
	}
	public long getSubscribers() {
		return subscribers;
	}
	public void setSubscribers(long subscribers) {
		this.subscribers = subscribers;
	}
	public long getVideos() {
		return videos;
	}
	public void setVideos(long videos) {
		this.videos = videos;
	}
	public long getTotalViews() {
		return totalViews;
	}
	public void setTotalViews(long totalViews) {
		this.totalViews = totalViews;
	}
	@Override
	public String toString() {
		return super.toString()+";YoutubeAccount [subscribers=" + subscribers + ", videos=" + videos + ", totalViews=" + totalViews + "]";
	}
	
}
