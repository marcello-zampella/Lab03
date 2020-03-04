package it.polito.tdp.spellchecker.model;

import java.io.*;

import java.util.*;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dictionary {
	private List<String> dizionario;
	private StringProperty sbagliFattiProperty;
	private SimpleDoubleProperty tempoImpiegato;
	
	
	
	

	public SimpleDoubleProperty getTempoImpiegato() {
		return tempoImpiegato;
	}

	public StringProperty getSbagliFattiProperty() {
		return sbagliFattiProperty;
	}
	
	public Dictionary() {
		sbagliFattiProperty= new SimpleStringProperty();
		this.tempoImpiegato=new SimpleDoubleProperty();
	}

	public LinkedList<RichWord> SpellCheckText(LinkedList<String> parole) {
		LinkedList<RichWord> temp=new LinkedList<RichWord>();
		int numeroErrori=0;
		for(String s: parole) {
			int flag=0;
			RichWord r=new RichWord();
			r.setParola(s);
			temp.add(r);
			r.setCorretto(false);
			int valore =dizionario.size()/2;
			if(dizionario.get(valore).compareTo(r.getParola())==0) {
				r.setCorretto(true);
			}
			if(dizionario.get(valore).compareTo(r.getParola())<0) {
				for(int q=valore; q<dizionario.size();q++) {
					if(dizionario.get(q).equals(r.getParola())) {
						r.setCorretto(true);
						q=dizionario.size();
						flag=1;
					}
			}
			}
			if(dizionario.get(valore).compareTo(r.getParola())>0) {
				for(int q=valore; q>=0;q--) {
					if(dizionario.get(q).equals(r.getParola())) {
						r.setCorretto(true);
						q=-1;
						flag=1;
					}
			}
			}
			
			if(flag==0)
				numeroErrori++;
		}
		
		this.sbagliFattiProperty.set("Il numero di errori e' "+numeroErrori);
		this.tempoImpiegato.set(System.currentTimeMillis());
		return temp;
	}
	
	public void loadDictionary (String language) {
		this.dizionario=new ArrayList<String>();
		try {
			String s="rsc/"+language+".txt";
			FileReader fr= new FileReader(s);
			BufferedReader br= new BufferedReader(fr);
			String word;
			while(((word=br.readLine())!=null)) {
				dizionario.add(word);
			}
			br.close();
		} catch(IOException e) {
			System.out.println("Errore nella lettur del file");
		}
		
	}

}
