package com.estevao.coletor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5831643115098377985L;
	private String socialnet;
	private String profile;
	private String name;
	private String country;
	private List<String> category;
	private String industry;
	private String link;
	private List<String> related;
	private int pos;
	private float rising;
	
	public Account(){
		setSocialnet("");
		setProfile("");
		setName("");
		setCountry("");
		setCategory(new ArrayList<>());
		setIndustry("");
		setRelated(new ArrayList<>());
		setPos(0);
		setRising(0);
	}

	public Account(String socialnet, String profile, String name, String country, List<String> category, String industry, List<String> related) {
		this.socialnet = socialnet;
		this.profile = profile;
		this.name = name;
		this.country = country;
		this.category = category;
		this.industry = industry;
		this.related = related;
		setRising(0);
	}

	public String getSocialnet() {
		return socialnet;
	}
	public void setSocialnet(String socialnet) {
		this.socialnet = socialnet;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<String> getRelated() {
		return related;
	}

	public void setRelated(List<String> related) {
		this.related = related;
	}

	

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public float getRising() {
		return rising;
	}

	public void setRising(float rising) {
		this.rising = rising;
	}
	
	@Override
	public String toString() {
		return "Account ["+ "pos=" + pos +", socialnet=" + socialnet + ", profile=" + profile + ", name=" + name + ", country=" + country
				+ ", category=" + category + ", industry=" + industry + ", link=" + link + ", related=" + related+
				 "]";
	}


}
