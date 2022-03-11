package it.csv.model;

public enum Tipo {
	
	CARTACEO("C"),
	EBOOK("E"),
	WEB("W");

	private String sigla;

	private Tipo(String sigla) {
		this.sigla=sigla;
	}

	public String getSigla() {
		return sigla;
	}
	
	
}
