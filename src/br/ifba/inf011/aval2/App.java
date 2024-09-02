package br.ifba.inf011.aval2;

import java.time.LocalDate;

import br.ifba.inf011.aval2.model.Arquivo;
import br.ifba.inf011.aval2.model.ArquivoHistorico;
import br.ifba.inf011.aval2.model.ArquivoHistorico.ArquivoHistoricoMemento;
import br.ifba.inf011.aval2.model.Conversor2Bin;
import br.ifba.inf011.aval2.model.Credencial;
import br.ifba.inf011.aval2.model.Entrada;
import br.ifba.inf011.aval2.model.EntradaOperavel;
import br.ifba.inf011.aval2.model.Pasta;
import br.ifba.inf011.aval2.model.brigde.ConversorText;
import br.ifba.inf011.aval2.model.brigde.TipoCodificacao;
import br.ifba.inf011.aval2.model.memento.Caretaker;
import br.ifba.inf011.aval2.model.state.Bloqueado;
import br.ifba.inf011.aval2.model.state.Excluido;
import br.ifba.inf011.aval2.model.state.Normal;
import br.ifba.inf011.aval2.model.state.SomenteLeitura;

public class App {

	public void runQ1() throws IllegalAccessException {

		Credencial user01 = new Credencial("user01");

		EntradaOperavel a1 = new Arquivo("A1", LocalDate.now(), "00011000100011100000011111110101");
		EntradaOperavel b1 = new Arquivo("B1", LocalDate.now(), "UM ARQUIVO TAMANHO GRANDE");
		EntradaOperavel c1 = new Arquivo("C1", LocalDate.now(), "UM ARQUIVO TAMANHO MUITO MUITO GRANDE");

		Entrada a = new Pasta("A", LocalDate.now());
		Entrada b = new Pasta("B", LocalDate.now());
		Entrada c = new Pasta("C", LocalDate.now());
		Entrada raiz = new Pasta("/", LocalDate.now());

		raiz.addFilho(a);
		raiz.addFilho(b);

		a.addFilho(a1);

		b.addFilho(c);
		b.addFilho(b1);

		c.addFilho(c1);

		try {
			b1.escrever(user01, "CINCO");
		} catch (IllegalAccessException ex) {
			System.out.println("NÃO FOI POSSIVEL ESCREVER EM A1");
		}

		System.out.println(raiz.getNome() + ": " + raiz.getTamanho() + "K");

		try {
			b1.escrever(user01, "CINCO+2");
		} catch (IllegalAccessException ex) {
			System.out.println("NÃO FOI POSSIVEL ESCREVER EM A1");
		}

		try {
			System.out.println("B1: " + b1.ler(user01));
		} catch (IllegalAccessException e) {
			System.out.println("NÃO FOI POSSIVEL LER DE A1");
		}

		System.out.println(raiz.getNome() + ": " + raiz.getTamanho() + "K");
	}

	public void runStateTest() throws IllegalAccessException {

		Credencial user01 = new Credencial("user01");

		Arquivo arquivo = new Arquivo("A1", LocalDate.now(), "Conteúdo Inicial");

		System.out.println("Estado: Normal");
		System.out.println("Conteúdo: " + arquivo.ler(user01));
		arquivo.escrever(user01, "Novo Conteúdo");
		System.out.println("Conteúdo após escrita: " + arquivo.ler(user01));
		System.out.println("Tamanho: " + arquivo.getTamanho());

		arquivo.setState(new SomenteLeitura(arquivo));

		System.out.println("\nEstado: SomenteLeitura");
		try {
			System.out.println("Conteúdo: " + arquivo.ler(user01));
		} catch (IllegalAccessException e) {
			System.out.println("Erro ao ler: " + e.getMessage());
		}

		try {
			arquivo.escrever(user01, " Tentativa de escrita");
		} catch (Exception e) {
			System.out.println("Não foi possível escrever no arquivo. Exceção esperada: " + e.getMessage());
		}

		System.out.println("Tamanho: " + arquivo.getTamanho());

		arquivo.setState(new Bloqueado(arquivo));

		System.out.println("\nEstado: Bloqueado");
		try {
			System.out.println("Tentando ler: " + arquivo.ler(user01));
		} catch (IllegalAccessException e) {
			System.out.println("Não foi possível ler o arquivo. Exceção esperada: " + e.getMessage());
		}

		try {
			arquivo.escrever(user01, " Tentativa de escrita");
		} catch (IllegalAccessException e) {
			System.out.println("Não foi possível escrever no arquivo. Exceção esperada: " + e.getMessage());
		}

		System.out.println("Tamanho: " + arquivo.getTamanho());

		arquivo.setState(new Excluido(arquivo));

		System.out.println("\nEstado: Excluído");
		try {
			System.out.println("Tentando ler: " + arquivo.ler(user01));
		} catch (IllegalAccessException e) {
			System.out.println("Não foi possível ler o arquivo. Exceção esperada: " + e.getMessage());
		}

		try {
			arquivo.escrever(user01, "Tentativa de nova escrita");
		} catch (IllegalAccessException e) {
			System.out.println("Não foi possível escrever no arquivo. Exceção esperada: " + e.getMessage());
		}

		System.out.println("Tamanho após exclusão: " + arquivo.getTamanho());

	}

	public void runBridgeTest() throws IllegalAccessException {
		Credencial user01 = new Credencial("user01");

		TipoCodificacao conversorTexto = new ConversorText();
		Arquivo arquivoTexto = new Arquivo("ArquivoTexto", LocalDate.now(), "Conteúdo para teste",
				new Normal(new Arquivo("temp", LocalDate.now(), "")), conversorTexto);

		System.out.println("Teste com ConversorText:");
		System.out.println("Conteúdo Original: " + arquivoTexto.ler(user01));
		String encodedTexto = arquivoTexto.dump();
		System.out.println("Conteúdo Codificado: " + encodedTexto);

		String decodedTexto = conversorTexto.decode(encodedTexto);
		System.out.println("Conteúdo Decodificado: " + decodedTexto);

		TipoCodificacao conversorBinario = new Conversor2Bin();
		Arquivo arquivoBinario = new Arquivo("ArquivoBinario", LocalDate.now(), "Conteúdo para teste",
				new Normal(new Arquivo("temp", LocalDate.now(), "")), conversorBinario);

		System.out.println("\nTeste com Conversor2Bin:");
		System.out.println("Conteúdo Original: " + arquivoBinario.ler(user01));
		String encodedBin = arquivoBinario.dump();
		System.out.println("Conteúdo Codificado: " + encodedBin);

		String decodedBin = conversorBinario.decode(encodedBin);
		System.out.println("Conteúdo Decodificado: " + decodedBin);

	}

	public void runMementoTest() throws IllegalAccessException {

		String leitura = "";
		ArquivoHistorico historico = new ArquivoHistorico("teste", LocalDate.now(), "Texto 1");
		Caretaker caretaker = new Caretaker();

		caretaker.add(historico.save());
		leitura = historico.ler(new Credencial("1"));
		System.out.println(leitura);

		historico.escrever(new Credencial("1"), "Texto 2");
		caretaker.add(historico.save());
		leitura = historico.ler(new Credencial("1"));
		System.out.println(leitura);

		historico.restore((ArquivoHistoricoMemento) caretaker.get(0));
		leitura = historico.ler(new Credencial("1"));
		System.out.println(leitura);

		historico.restore((ArquivoHistoricoMemento) caretaker.get(1));
		leitura = historico.ler(new Credencial("1"));
		System.out.println(leitura);

		historico.escrever(new Credencial("1"), "Texto 3");
		caretaker.add(historico.save());
		leitura = historico.ler(new Credencial("1"));
		System.out.println(leitura);

		historico.restore((ArquivoHistoricoMemento) caretaker.get(0));
		leitura = historico.ler(new Credencial("1"));
		System.out.println(leitura);

		historico.restore((ArquivoHistoricoMemento) caretaker.get(1));
		leitura = historico.ler(new Credencial("1"));
		System.out.println(leitura);

		historico.restore((ArquivoHistoricoMemento) caretaker.get(2));
		leitura = historico.ler(new Credencial("1"));
		System.out.println(leitura);

	}

	public static void main(String[] args) throws IllegalAccessException {
		App app = new App();
		app.runQ1();
		System.out.println("\n\n");
		app.runStateTest();
		System.out.println("\n\n");
		app.runBridgeTest();
		System.out.println("\n\n");
		app.runMementoTest();
	}

}
