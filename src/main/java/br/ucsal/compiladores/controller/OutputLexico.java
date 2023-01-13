package br.ucsal.compiladores.controller;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.compiladores.tabelas.ItemTabelaDeSimbolos;
import br.ucsal.compiladores.tabelas.TabeladeSimbolos;

public class OutputLexico {
	List<ItemTabelaDeSimbolos> itens;
	TabeladeSimbolos tabeladeSimbolos;

	public OutputLexico() {
		this.itens = new ArrayList<ItemTabelaDeSimbolos>();
		this.tabeladeSimbolos = new TabeladeSimbolos();
	}

	public void addItem(ItemTabelaDeSimbolos item) {
		this.itens.add(item);
	}
	
	public TabeladeSimbolos getTabeladeSimbolos() {
		return this.tabeladeSimbolos;
	}
	
	public List<ItemTabelaDeSimbolos> getItens() {
		return this.itens;
	}

}
