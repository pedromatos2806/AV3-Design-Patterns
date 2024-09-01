package br.ifba.inf011.aval2.model.state;

import java.time.LocalDate;
import java.util.List;

import br.ifba.inf011.aval2.model.Arquivo;
import br.ifba.inf011.aval2.model.Credencial;
import br.ifba.inf011.aval2.model.Entrada;
import br.ifba.inf011.aval2.model.EntradaOperavel;

/* */
public abstract class EstadoArquivoAbstract implements EntradaOperavel{
	
	protected Arquivo arquivo;
	
	protected EstadoArquivoAbstract(Arquivo arquivo) {
		this.arquivo = arquivo;
	}
	
	public EntradaOperavel getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}
		
	@Override
	public String getNome() {	
		return this.arquivo.getNome();
	}
	
	@Override
	public LocalDate getDataCriacao() {
		return this.arquivo.getDataCriacao();
	}
	
	@Override
	public List<Entrada> getFilhos() throws UnsupportedOperationException {
		return this.arquivo.getFilhos();
	}

	@Override
	public void addFilho(Entrada entrada) throws UnsupportedOperationException {
		this.arquivo.addFilho(entrada);
	}

	@Override
	public void removeFilho(Entrada entrada) throws UnsupportedOperationException {
		this.arquivo.removeFilho(entrada);
	}
	
	@Override
	public String dump() {
		return this.arquivo.dump();
	}
	
	@Override
	public abstract String ler(Credencial credencial) throws IllegalAccessException;

	@Override
	public abstract void escrever(Credencial credencial, String escrever) throws IllegalAccessException;

	@Override
	public abstract Long getTamanho() throws IllegalAccessException;

}
