package com.estevao.coletor;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Consumer implements Runnable{
	private Accounts accounts;
	List<Account> listAccounts;
	private String id;
	private boolean finished = false;

	public Consumer(Accounts accounts, String id) {
		this.accounts = accounts;
		this.id = id;
		listAccounts = new ArrayList<>();

	}

	public void getAccounts() {
		while (!accounts.isFinished()) {
			Account account = accounts.getElement();
			if (account!=null){
				if (account.getSocialnet().equals("Youtube"))
					getYoutubeInformation(account);
				else if (account.getSocialnet().equals("Twitter"))
					getTwitterInformation(account);
				else if (account.getSocialnet().equals("Facebook"))
					getFacebookInformation(account);
				listAccounts.add(account);
			}
		}
		try{
            FileOutputStream outputStream = new FileOutputStream("../list"+id+".acc");
            ObjectOutputStream ou = new ObjectOutputStream(outputStream);
            ou.writeObject(listAccounts);
            ou.close();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
	}

	public void getYoutubeInformation(Account account) {
		YoutubeAccount youtubeAccount = (YoutubeAccount) account;
		try {
			Document document = Jsoup.connect(youtubeAccount.getLink()).get();
			Elements elements = document.select("div[class=\"account-detail\"]");
			elements = elements.select("ul[class=\"account-list\"]").select("strong");
			String profile = elements.get(0).select("a").attr("href");
			long subscribers = Long.parseLong(elements.get(1).text().replaceAll(" ", ""));
			long videos = Long.parseLong(elements.get(2).text().replaceAll(" ", ""));
			long totalViews =  Long.parseLong(elements.get(3).text().replaceAll(" ", ""));
			youtubeAccount.setSubscribers(subscribers);
			youtubeAccount.setVideos(videos);
			youtubeAccount.setTotalViews(totalViews);
			youtubeAccount.setProfile(profile);
			
			elements = document.select("div[class=\"account-tag-list\"]").select("li");
			for (Element eq: elements){
				String el = eq.text();
				if (el.equals("BR"))
					youtubeAccount.setCountry("BR");
				else
					youtubeAccount.getCategory().add(el);
			}
			elements = document.select("div[class=\"graph-timeline growth\"]");
			String r = elements.select("strong[class=\"interval-month growth\"]").text();
			if (r.trim().equals(""))
				r = elements.select("strong[class=\"interval-month loss\"]").text();
			r = r.replaceAll(" ", "");
			r = r.replaceAll("[a-zA-Z]", "");
			if (!r.equals(""))
				youtubeAccount.setRising(Float.parseFloat(r)/youtubeAccount.getSubscribers());
			
			//System.out.println(r);
			
//			Vector<Float> va = new Vector<>();
//			for (Element e: elements){
//				System.out.println("Text"+e.text());
//				System.out.println(e.text().replaceAll("[^\\d.]+|\\.(?!\\d)", ""));
//				va.add(Float.valueOf(e.text().replaceAll("[^\\d.]+|\\.(?!\\d)", "")));
//			}
			
			
//			String category = "";
//			if (elements.size()>2)
//			category = elements.get(2).text();
//			String industry = "";
//			if (elements.size()>1)
//			industry = elements.get(1).text();
//			String country = "";
//			if (elements.size()>0)
//			country = elements.get(0).text();
//			youtubeAccount.getCategory().add(category);
//			youtubeAccount.setCountry(country);
//			youtubeAccount.setIndustry(industry);
			elements = document.select("ul[class=\"pages-list\"]").select("a");
			List<String> related = youtubeAccount.getRelated();
			for (Element e: elements) {
				related.add(e.text()+":"+e.attr("href"));
			}
			System.out.println(youtubeAccount);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
	
	public void getFacebookInformation(Account account) {
		FacebookAccount facebookAccount = (FacebookAccount) account;
		try {
			Document document = Jsoup.connect(facebookAccount.getLink()).get();
			Elements elements = document.select("div[class=\"account-detail\"]");
			elements = elements.select("ul[class=\"account-list\"]").select("strong");
			long likes = Long.parseLong(elements.get(0).text().replaceAll(" ", ""));
			elements = document.select("div[class=\"account-detail\"]").select("ul[class=\"account-list\"]").select("a");
			String profile = elements.get(0).attr("href");
			facebookAccount.setLikes(likes);
			facebookAccount.setProfile(profile);
			
			elements = document.select("div[class=\"account-tag-list\"]").select("li");
			for (Element eq: elements){
				String el = eq.text();
				if (el.equals("BR"))
					facebookAccount.setCountry("BR");
				else
					facebookAccount.getCategory().add(el);
			}
			elements = document.select("div[class=\"graph-timeline growth\"]");
			String r = elements.select("strong[class=\"interval-month growth\"]").text();
			if (r.trim().equals(""))
				r = elements.select("strong[class=\"interval-month loss\"]").text();
			r = r.replaceAll(" ", "");
			r = r.replaceAll("[a-zA-Z]", "");
			if (!r.equals(""))
				facebookAccount.setRising(Float.parseFloat(r)/facebookAccount.getLikes());
			
//			System.out.println(r);
//			String category = "";
//			if (elements.size()>2)
//			category = elements.get(2).text();
//			String industry = "";
//			if (elements.size()>1)
//			industry = elements.get(1).text();
//			String country = "";
//			if (elements.size()>0)
//			country = elements.get(0).text();
//			facebookAccount.getCategory().add(category);
//			facebookAccount.setCountry(country);
//			facebookAccount.setIndustry(industry);
			elements = document.select("ul[class=\"pages-list\"]").select("a");
			List<String> related = facebookAccount.getRelated();
			for (Element e: elements) {
				related.add(e.text()+":"+e.attr("href"));
			}
			System.out.println(facebookAccount);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
	
	public void getTwitterInformation(Account account) {
		TwitterAccount twitterAccount = (TwitterAccount) account;
		try {
			Document document = Jsoup.connect(twitterAccount.getLink()).get();
			Elements elements = document.select("div[class=\"account-detail\"]");
			elements = elements.select("ul[class=\"account-list\"]").select("strong");
			String profile = elements.get(0).select("a").attr("href");
			long followers = Long.parseLong(elements.get(1).text().replaceAll(" ", ""));
			long following = Long.parseLong(elements.get(2).text().replaceAll(" ", ""));
			long tweets =  Long.parseLong(elements.get(3).text().replaceAll(" ", ""));
			twitterAccount.setFollowers(followers);
			twitterAccount.setFollowing(following);
			twitterAccount.setTweets(tweets);
			twitterAccount.setProfile(profile);
			
			elements = document.select("div[class=\"account-tag-list\"]").select("li");
			for (Element eq: elements){
				String el = eq.text();
				if (el.equals("BR"))
					twitterAccount.setCountry("BR");
				else
					twitterAccount.getCategory().add(el);
			}
			elements = document.select("div[class=\"graph-timeline growth\"]");
			String r = elements.select("strong[class=\"interval-month growth\"]").text();
			if (r.trim().equals(""))
				r = elements.select("strong[class=\"interval-month loss\"]").text();
			r = r.replaceAll(" ", "");
			r = r.replaceAll("[a-zA-Z]", "");
			if (!r.equals(""))
				twitterAccount.setRising(Float.parseFloat(r)/twitterAccount.getFollowers());
//			String category = "";
//			if (elements.size()>2)
//			category = elements.get(2).text();
//			String industry = "";
//			if (elements.size()>1)
//			industry = elements.get(1).text();
//			String country = "";
//			if (elements.size()>0)
//			country = elements.get(0).text();
//			twitterAccount.getCategory().add(category);
//			twitterAccount.setCountry(country);
//			twitterAccount.setIndustry(industry);
			elements = document.select("ul[class=\"pages-list\"]").select("a");
			List<String> related = twitterAccount.getRelated();
			for (Element e: elements) {
				related.add(e.text()+":"+e.attr("href"));
			}
			System.out.println(twitterAccount);
		} catch (Exception e) {
			e.printStackTrace();
			//System.err.println(e.getMessage());
		}

	}
	
	

	@Override
	public void run() {
		finished = false;
		getAccounts();
		System.out.println("Thread"+ id + " terminou");
		finished = true;
		
		
	}
	
	public boolean isFinished(){
		return finished;
	}

}
