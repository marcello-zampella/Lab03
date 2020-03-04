package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	private boolean corretto;
	private String parola;
	
	
	public boolean isCorretto() {
		return corretto;
	}
	public void setCorretto(boolean corretto) {
		this.corretto = corretto;
	}
	public String getParola() {
		return parola;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	@Override
	public String toString() {
		return "RichWord [corretto=" + corretto + ", parola=" + parola + "]";
	}
	
	
	

}
