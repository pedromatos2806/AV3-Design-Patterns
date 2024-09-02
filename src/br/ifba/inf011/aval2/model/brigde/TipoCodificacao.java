package br.ifba.inf011.aval2.model.brigde;

public interface TipoCodificacao {
	
	String encode(String conteudo);
	
	String decode(String conteudo);
	
}