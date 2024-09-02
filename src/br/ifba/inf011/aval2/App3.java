package br.ifba.inf011.aval2;

import java.time.LocalDate;

import br.ifba.inf011.aval2.model.ArquivoHistorico;
import br.ifba.inf011.aval2.model.ArquivoHistorico.ArquivoHistoricoMemento;
import br.ifba.inf011.aval2.model.Credencial;
import br.ifba.inf011.aval2.model.memento.Caretaker;

public class App3 {
	
	
	public void runQ1() throws IllegalAccessException  {
		
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
		App3 app = new App3();
		app.runQ1();
	}

}
