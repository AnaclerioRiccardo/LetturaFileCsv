package it.csv.model;

public enum Genere {
	
	FANTASY("F"),
	HORROR("H"),
	AZIONE("A");
	
	private String sigla;
	
	private Genere(String sigla) {
		this.sigla=sigla;
	}

	public String getSigla() {
		return sigla;
	}

}
