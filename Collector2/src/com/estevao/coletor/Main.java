package com.estevao.coletor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.text.Normalizer;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.Painter;

public class Main {
	public static void main(String args[]) {
		// Demorou:11315

//		 int quant = 30;
//		 long ti = System.currentTimeMillis();
//		 Accounts accounts = new Accounts();
//		 Producer producer = new Producer(accounts);
//		 // Consumer consumer = new Consumer(accounts, "0");
//		 // new Thread(consumer).start();
//		 Thread[] t = new Thread[quant];
//		 Consumer[] co = new Consumer[quant];
//		 for (int i = 0; i < quant; i++) {
//		 co[i] = new Consumer(accounts, i + "");
//		 t[i] = new Thread(co[i]);
//		 t[i].start();
//		 System.out.println("Started" + i);
//		 }
//		 new Thread(producer).start();
//		 for (int i = 0; i < quant; i++) {
//		 try {
//		 if (!co[i].isFinished())
//		 t[i].join();
//		 } catch (Exception e) {
//		 e.printStackTrace();
//		 }
//		 // TODO: handle exception
//		 }
//		 System.out.println("Demorou:" + ((System.currentTimeMillis() - ti) /
//		 1000));
		// pr();
		//pr1();
		pr2();

	}

	private static void pr2() {
		Map<String , String> translator = new HashMap<>();
		translator.put("Artist", "Artista");
		translator.put("Singer", "Cantor/Cantora");
		translator.put("Entertainment", "Entretenimento");
		translator.put("Sport Event", "Evento Esportivo");
		translator.put("Film", "Filme");
		translator.put("Apps", "Aplicativos");
		translator.put("Celebrities", "Celebridades");
		translator.put("Sport", "Esporte");
		translator.put("Online Media", "Mídia Online");
		translator.put("Education", "Educação");
		translator.put("Musician", "Músico");
		translator.put("Actor", "Ator/Atriz");
		translator.put("Film Industry", "Indústria Cinematográfica");
		translator.put("Fictional Character","Personagem Fictício");
		translator.put("Sport Club","Clube Esportivo");
		translator.put("Sport Organization","Organização Esportiva");
		translator.put("Personal", "Pessoal");
		translator.put("Online Show","Canal da Internet");
		translator.put("Media","Meios de Comunicação");
		translator.put("Printed Media","Mídia Impressa");
		translator.put("City","Cidade");
		translator.put("Music Industry","Indústria musical");
		translator.put("Auto Interest","Automóvel");
		translator.put("Fashion Star","Estrela da Moda");
		translator.put("Computer Game","Jogo de Computador");
		translator.put("University","Universidade");
		translator.put("Radio Media","Rádio");
		translator.put("Country","País");
		translator.put("Society","Sociedade");
		translator.put("Political","Político");
		translator.put("Web Portal","Portal da Internet");
		translator.put("Life Style","Estilo de Vida");
		translator.put("Governmental","Instituição Governamental");
		translator.put("Social Media","Mídia Social");
		translator.put("Disc Jockey","DJ");
		translator.put("Wikipedia","Enciclopédia Online");
		translator.put("Broadcast Show","Programa de TV");
		translator.put("TV Channels","Canal de TV");
		translator.put("News","Novidades");
		translator.put("GLOBAL","Global");
		translator.put("Books","Livros");
		translator.put("Blog","Blog");
		translator.put("Fun","Diversão");
		translator.put("Sport Interest","Esportes");
		translator.put("Broadcast Star","Artista de TV");
		translator.put("Religion","Religião");
		translator.put("Hobbies","Hobbies");
		translator.put("Music","Música");
		translator.put("Media House","Canal de TV");
		translator.put("Event","Evento");
		translator.put("Politics","Política");
		translator.put("Sport Star","Esportista");
		translator.put("Community","Comunidade");
		translator.put("Writer","Escritor");
		translator.put("Place","Lugares");
		
		try {
			Map<Integer, Account> m = null;
			Set<String> s = new HashSet<>();
			FileInputStream inputStream = new FileInputStream("../listF1.acc");
			ObjectInputStream in = new ObjectInputStream(inputStream);
			m = (Map<Integer, Account>) in.readObject();
			in.close();
			inputStream.close();
			for (int i = 1; i < m.size(); i++) {
				Account a = m.get(i);
				a.setName(formatString(a.getName()));
				List<String> aux = new ArrayList<>();
				for (int j = 0; j < a.getRelated().size(); j++) {
					aux.add(formatString(a.getRelated().get(j).split(":")[0]));
				}
				a.setRelated(aux);
				if (a.getCategory().contains("PT"))
					a.getCategory().remove("PT");
				if (a.getCategory().contains("US"))
					a.getCategory().remove("US");
				if (a.getCategory().contains("MX"))
					a.getCategory().remove("MX");
				aux = new ArrayList<>();
				for (int j = 0; j < a.getCategory().size(); j++) {
					aux.add(translator.get(a.getCategory().get(j)));
				}
				a.setCategory(aux);
				
				// System.out.println(a);

				//s.addAll(a.getCategory());

			}
			System.out.println("Size: " + m.size());
			//System.out.println(s);

			Writer w = new FileWriter("../t.txt");
			for (int i = 0; i < m.size(); i++) {
				w.write(m.get(i) + "\n");
				// System.out.println(m1.get(i));
			}
			w.close();

			try {
				FileOutputStream outputStream = new FileOutputStream("../listF12.acc");
				ObjectOutputStream ou = new ObjectOutputStream(outputStream);
				ou.writeObject(m);
				ou.close();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	private static void pr1() {
		try {
			Map<Integer, Account> m = new HashMap<>();
			Map<Integer, Account> m1 = new HashMap<>();
			for (int i = 0; i < 30; i++) {
				FileInputStream inputStream = new FileInputStream("../list" + i + ".acc");
				ObjectInputStream in = new ObjectInputStream(inputStream);
				List<Account> l = (List<Account>) in.readObject();
				for (int j = 0; j < l.size(); j++) {
					Account account = l.get(j);
					int index = 0;
					if (account.getSocialnet().equals("Youtube"))
						index += 1000;
					else if (account.getSocialnet().equals("Twitter"))
						index += 2000;
					m.put(index + account.getPos(), account);
					// lf.add(account);
				}
				in.close();
				inputStream.close();

			}
			System.out.println("Size: " + m.size());
			int pos = 0;
			for (int i = 1; i < m.size(); i++) {
				//System.out.println(i);
				if (m.containsKey(i)) {
					//System.out.println(m.get(i).getSocialnet());
					if (i != 1 && !m.get(i - 1).getSocialnet().equals(m.get(i).getSocialnet()))
						pos = 1;
					else
						pos++;
					if (!m.get(i).getCategory().contains("Brands")
							&& !m.get(i).getName().equals("Johnson & Johnson Brasil")
							&& !m.get(i).getCategory().contains("JP") &&
							!m.get(i).getCategory().contains("NGO")) {
						Account a = m.get(i);
						a.setPos(pos);
						m1.put(m1.size(), a);
					} else {
						System.out.println("Removendo: " + m.get(i).getName());
						pos--;
					}
				}else
					System.out.println(i);
			}
			System.out.println("Size: " + m1.size());
			Writer w = new FileWriter("../t.txt");
			for (int i = 0; i < m1.size(); i++) {
				w.write(m1.get(i) + "\n");
				// System.out.println(m1.get(i));
			}
			w.close();

			try {
				FileOutputStream outputStream = new FileOutputStream("../listF1.acc");
				ObjectOutputStream ou = new ObjectOutputStream(outputStream);
				ou.writeObject(m1);
				ou.close();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Erro aqu"+e.getMessage());
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	private static void pr() {
		try {
			FileInputStream inputStream = new FileInputStream("../list0.acc");
			ObjectInputStream in = new ObjectInputStream(inputStream);
			List<Account> l = (List<Account>) in.readObject();
			for (Account account : l) {
				if (!account.getCountry().equals("BR")) {
					account.getCategory().add(account.getCountry());
					account.setCountry("BR");
					boolean v = false;
					for (int i = 0; i < account.getCategory().size(); i++) {
						if (account.getCategory().get(i).equals("BR")) {
							account.getCategory().remove(i);
							account.getCategory().add(account.getIndustry());
							v = true;
						}
					}
					if (!v)
						account.setIndustry("");
				} else {
					account.getCategory().add(account.getIndustry());
					account.setIndustry("");
				}
				for (int i = 0; i < account.getCategory().size(); i++) {
					if (account.getCategory().get(i).trim().equals("")) {
						account.getCategory().remove(i);
					}
				}

			}

			for (int i = 995; i < 1050; i++)
				System.out.println(l.get(i).getCategory());
			for (int i = 1; i < 90; i++)
				System.out.println(l.get(i).getCategory());
			for (int i = 2000; i < 2050; i++)
				System.out.println(l.get(i).getCategory());

			in.close();
			inputStream.close();

			try {
				FileOutputStream outputStream = new FileOutputStream("../listF.acc");
				ObjectOutputStream ou = new ObjectOutputStream(outputStream);
				ou.writeObject(l);
				ou.close();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// ou.writeObject(listAccounts);
			// ou.close();
			// outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

		
	public static String formatString(String s) {
		// String temp = Normalizer.normalize(s, java.text.Normalizer.Form.NFD);
		return s.replaceAll("[^\\p{ASCII}áàéíóúÁÀÉÍÓÚçÇãÃâêîôûÂÊÎÔÛ]", "");
	}

}
