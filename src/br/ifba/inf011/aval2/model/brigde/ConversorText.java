package br.ifba.inf011.aval2.model.brigde;

//Concrete Implementation
public class ConversorText implements TipoCodificacao{

	@Override
	public String encode(String conteudo) {
		return conteudo;
	}

	@Override
	public String decode(String conteudo) {	
		return conteudo;
	}

}