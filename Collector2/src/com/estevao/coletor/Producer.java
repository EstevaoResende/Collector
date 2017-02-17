package com.estevao.coletor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Producer implements Runnable {
	private Accounts accounts;
	private Map<Integer, String> mapHref;
	// private Set<String> wordSet;
	private Map<Integer, String> mapURL;
	private int pos = 1;
	// int c=0;

	public Producer(Accounts accounts) {
		this.accounts = accounts;
		// wordSet = new HashSet<>();
		mapURL = new HashMap<>(); 
		mapURL.put(0,"https://www.socialbakers.com/statistics/youtube/channels/brazil/page-");
		mapURL.put(1,"https://www.socialbakers.com/statistics/facebook/pages/brazil/page-");
		mapURL.put(2,"https://www.socialbakers.com/statistics/twitter/profiles/brazil/page-");
		
		mapHref = new HashMap<>();
		mapHref.put(0, "a[href^=\"/statistics/youtube/channels/\"]");
		mapHref.put(1, "a[href^=\"/statistics/facebook/pages/\"]");
		mapHref.put(2, "a[href^=\"/statistics/twitter/profiles/\"]");

	}

	public void getPageLinks(String URL, String href, int type) {
		while(true){
		try {
			Document document = Jsoup.connect(URL).get();
			Elements otherLinks = document.select("div[class=\"brand-table-placeholder\"]").select(href);
			

			for (Element page : otherLinks) {

				String text = page.attr("href");
				String name = page.select("span").text();
				
				//System.out.println("https://www.socialbakers.com" + text);
				Account a = null;
				if (type==0)
					a = new YoutubeAccount();
				else if (type==1)
					a = new FacebookAccount();
				else
					a = new TwitterAccount();
				
				a.setLink("https://www.socialbakers.com" + text);
				a.setName(name);
				a.setPos(pos++);
				accounts.putElement(a);
				// if (!text.contains(" ") && !wordSet.contains(text)) {
				// if (text.toLowerCase().equals("amor")){
				// //System.out.println(text);
				//// if (c<50)
				//// c++;
				//// else{
				//// c=0;
				//// break;
				//// }
				// wordSet.add(text);
				// String link = "http://dicionariocriativo.com.br/" + text;
				// Word w = new Word(link, text.toLowerCase());
				// words.putElement(w);
				// break;
				// }
				// }
			}
			break;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		}
	}
	private void  getPage(){
		try{
		FileInputStream inputStream = new FileInputStream("../listF.acc");
        ObjectInputStream in = new ObjectInputStream(inputStream);
        List<Account> l = (List<Account>) in.readObject();
        for (Account account: l){
        	//if (account.getCategory().isEmpty())
        	account.getCategory().clear();
        		accounts.putElement(account);
        }
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
//		for (int i = 0; i < 3; i++) {
//			for (int j = 1; j <= 100; j++) {
//				//getPageLinks(mapURL.get(i)+j+"/", mapHref.get(i),i);
//			}
//			pos=1;
//		}
		getPage();
		accounts.setFinished(true);
	}
}
