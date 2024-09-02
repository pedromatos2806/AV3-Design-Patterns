package br.ifba.inf011.aval2;

import java.time.LocalDate;

import br.ifba.inf011.aval2.model.Arquivo;
import br.ifba.inf011.aval2.model.Conversor2Bin;
import br.ifba.inf011.aval2.model.Credencial;
import br.ifba.inf011.aval2.model.brigde.ConversorText;
import br.ifba.inf011.aval2.model.brigde.TipoCodificacao;
import br.ifba.inf011.aval2.model.state.Bloqueado;
import br.ifba.inf011.aval2.model.state.Excluido;
import br.ifba.inf011.aval2.model.state.Normal;
import br.ifba.inf011.aval2.model.state.SomenteLeitura;

public class App2 {

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
        Arquivo arquivoTexto = new Arquivo("ArquivoTexto", LocalDate.now(), "Conteúdo para teste", new Normal(new Arquivo("temp", LocalDate.now(), "")), conversorTexto);

        System.out.println("Teste com ConversorText:");
        System.out.println("Conteúdo Original: " + arquivoTexto.ler(user01));
        String encodedTexto = arquivoTexto.dump();
        System.out.println("Conteúdo Codificado: " + encodedTexto);
        
        String decodedTexto = conversorTexto.decode(encodedTexto);
        System.out.println("Conteúdo Decodificado: " + decodedTexto);

        
        TipoCodificacao conversorBinario = new Conversor2Bin();
        Arquivo arquivoBinario = new Arquivo("ArquivoBinario", LocalDate.now(), "Conteúdo para teste", new Normal(new Arquivo("temp", LocalDate.now(), "")), conversorBinario);

        System.out.println("\nTeste com Conversor2Bin:");
        System.out.println("Conteúdo Original: " + arquivoBinario.ler(user01));
        String encodedBin = arquivoBinario.dump();
        System.out.println("Conteúdo Codificado: " + encodedBin);
        
        String decodedBin = conversorBinario.decode(encodedBin);
        System.out.println("Conteúdo Decodificado: " + decodedBin);
    }

	
	
	public static void main(String[] args) throws IllegalAccessException {
		App2 app = new App2();
		app.runStateTest();
		app.runBridgeTest();
	}
}
