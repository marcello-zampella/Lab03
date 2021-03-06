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
			RichWord r=new RichWord();
			r.setParola(s);
			temp.add(r);
			
			if(dizionario.contains(s.toLowerCase())) {
				r.setCorretto(true);
				
			}
			else {
				r.setCorretto(false);
				numeroErrori++;
			}
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
