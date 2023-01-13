package br.ucsal.compiladores.tabelas;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.compiladores.ENUNS.*;

public class TabeladeSimbolos {
	private List<ItemTabelaDeSimbolos> itens;
	private List<Atomo> atomos;
	
	public List<ItemTabelaDeSimbolos> getItens() {
		return this.itens;
	}
	
	public List<Atomo> getAtomos() {
		return this.atomos;
	}
	
	public void addItem(ItemTabelaDeSimbolos novoItem) {
		if (itens.contains(novoItem)) {
			for (ItemTabelaDeSimbolos item : itens) {
				if (novoItem.getSimboloReservadoTipo() == item.getSimboloReservadoTipo()) {
					item.addNovaLinha(novoItem.getLinha());
				}
			}	
		} else {
			itens.add(novoItem);
		}
	}
	public TabeladeSimbolos() {
		this.itens = new ArrayList<ItemTabelaDeSimbolos>();
		this.atomos = new ArrayList<Atomo>();
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < this.itens.size(); i++) {
			ItemTabelaDeSimbolos currentItem = this.itens.get(i);
			str += "Numero de entrada na tabela de simbolos: " + i + " , codigo: " + currentItem.getIndiceAtomo() + 
					" , lexeme: " + currentItem.getLexame()+ " , caracteres antes da truncagem: " + currentItem.getAntesTruncagem() + 
					" , caracteres depois da truncagem: " + currentItem.getDepoisTruncagem() + " , cinco primeiras linhas: " + currentItem.getCincoPrimeirasLinhas().toString()
					+ "\n";
		}
		return str;
	}
	public void addAtomo(Atomo atomo) {
		this.atomos.add(atomo);
	}
}
