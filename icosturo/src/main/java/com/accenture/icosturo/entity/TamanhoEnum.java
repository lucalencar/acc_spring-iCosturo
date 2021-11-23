package com.accenture.icosturo.entity;

public enum TamanhoEnum {
	
	PP("PP"),
	P("P"),
	M("M"),
	G("G"),
	GG("GG");
	
	private final String tamanho;
	
	private TamanhoEnum(String t) {
		tamanho = t;
	}
	
	public boolean equalsTamanho(String compare) {
		return tamanho.equals(compare);
	}
	
	public String toString() {
		return this.tamanho;
	}

}
