package br.ifba.inf011.aval2.model;

import java.time.LocalDate;

//Originator
public class ArquivoHistorico extends Arquivo implements EntradaOperavel{


	public ArquivoHistorico(String nome, LocalDate dataCriacao, String conteudo) {
		super(nome, dataCriacao, conteudo);
	}
	
	public ArquivoHistoricoMemento save() {
		return new ArquivoHistoricoMemento(conteudo);
	}
	
	public void restore (ArquivoHistoricoMemento memento) {
		setConteudo(memento.getState());
	}
	
	//Memento
	public static class ArquivoHistoricoMemento {
		private String conteudoSalvo;

		public ArquivoHistoricoMemento(String conteudo) {
			this.conteudoSalvo = conteudo;
		}
		
		private String getState() {
			return conteudoSalvo;
		}
	}


}
