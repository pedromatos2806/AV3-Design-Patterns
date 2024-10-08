package br.ifba.inf011.aval2.model;

import java.time.LocalDate;
import java.util.List;

import br.ifba.inf011.aval2.model.brigde.ConversorText;
import br.ifba.inf011.aval2.model.brigde.TipoCodificacao;
import br.ifba.inf011.aval2.model.composite.AbstractEntrada;
import br.ifba.inf011.aval2.model.state.EstadoArquivoAbstract;
import br.ifba.inf011.aval2.model.state.Normal;

//Abstraction
public class Arquivo extends AbstractEntrada implements EntradaOperavel{
	
	protected String conteudo;
	
	private EstadoArquivoAbstract state;
	
	private TipoCodificacao tipoCodificacao;

	public Arquivo(String nome, LocalDate dataCriacao, String conteudo) {
		super(nome, dataCriacao);
		this.conteudo =  conteudo;
		this.tipoCodificacao = new ConversorText();
		this.state = new Normal(this);
	}
	
	public Arquivo(String nome, LocalDate dataCriacao, String conteudo,EstadoArquivoAbstract state, TipoCodificacao tipoCodificacao) {
		super(nome, dataCriacao);
		this.conteudo =  conteudo;
		this.state = state;
		this.tipoCodificacao = tipoCodificacao;
	}

	@Override
	public List<Entrada> getFilhos() throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	@Override
	public void addFilho(Entrada entrada) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeFilho(Entrada entrada) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	@Override
	public Long getTamanho() throws IllegalAccessException{
		return state.getTamanho();
	}
	
	@Override
	public String ler(Credencial credencial) throws IllegalAccessException{
		return this.state.ler(credencial);
	}

	@Override
	public void escrever(Credencial credencial, String conteudo) throws IllegalAccessException {
		this.state.escrever(credencial, conteudo);
	}

	@Override
	public String dump(){
		return this.tipoCodificacao.encode(conteudo);
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public void setState(EstadoArquivoAbstract state) {
		this.state = state;
	}
	
	public String getConteudo() {
	    return this.conteudo;
	}

	public Arquivo getArquivo () {
		return this;
	}
}
