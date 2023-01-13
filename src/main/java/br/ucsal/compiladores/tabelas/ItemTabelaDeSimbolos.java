package br.ucsal.compiladores.tabelas;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.compiladores.ENUNS.Atomo;

public class ItemTabelaDeSimbolos {
	
	private Integer numEntradaSimbolos;

	private String indiceAtomo;

	private String lexame;

	private Integer antesTruncagem;

	private Integer depoisTruncagem;

	private Atomo simboloReservadoTipo;

	private Integer linha;

	private List<Integer> cincoPrimeirasLinhas;


	public ItemTabelaDeSimbolos(Integer numEntradaTabSimbolos, String codigoAtomo, String lexame,
			Integer antesTruncagem, Integer depoisTruncagem, Atomo tipoSimbolo, Integer linha) {
		super();
		this.numEntradaSimbolos = numEntradaTabSimbolos;
		this.indiceAtomo = codigoAtomo;
		this.lexame = lexame;
		this.antesTruncagem = antesTruncagem;
		this.depoisTruncagem = depoisTruncagem;
		this.simboloReservadoTipo = tipoSimbolo;
		this.linha = linha;
		this.cincoPrimeirasLinhas = new ArrayList<Integer>();
		this.cincoPrimeirasLinhas.add(linha);
	}


	public void addNovaLinha(Integer novaLinha) {
		if (this.cincoPrimeirasLinhas.size() < 5) {
			this.cincoPrimeirasLinhas.add(novaLinha);
		}
	}
	
	public String toStringLex() {
		return "Lexeme: " + lexame + " , Codigo: " + indiceAtomo + " , Indice: " + numEntradaSimbolos;
	}
	
	public String toStringTab() {
		return "Numero_entrada_tab_simbolos: " + numEntradaSimbolos + " , codigo: " + indiceAtomo + 
				" , lexeme: " + lexame + " ,caracteresantes da truncagem: " + antesTruncagem + 
				" , caracteres depois da truncagem: " + depoisTruncagem + " , cinco primeiras linhas que aparecem: " + cincoPrimeirasLinhas;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemTabelaDeSimbolos other = (ItemTabelaDeSimbolos) obj;
		if (!this.lexame.equalsIgnoreCase(other.lexame))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cincoPrimeirasLinhas == null) ? 0 : cincoPrimeirasLinhas.hashCode());
		result = prime * result + ((indiceAtomo == null) ? 0 : indiceAtomo.hashCode());
		result = prime * result + ((lexame == null) ? 0 : lexame.hashCode());
		result = prime * result + ((linha == null) ? 0 : linha.hashCode());
		result = prime * result + ((numEntradaSimbolos == null) ? 0 : numEntradaSimbolos.hashCode());
		result = prime * result + ((antesTruncagem == null) ? 0 : antesTruncagem.hashCode());
		result = prime * result
				+ ((depoisTruncagem == null) ? 0 : depoisTruncagem.hashCode());
		result = prime * result + ((simboloReservadoTipo == null) ? 0 : simboloReservadoTipo.hashCode());
		return result;
	}
	public Integer getNumEntradaSimbolos() {
		return numEntradaSimbolos;
	}


	public void setNumEntradaSimbolos(Integer numEntradaSimbolos) {
		this.numEntradaSimbolos = numEntradaSimbolos;
	}


	public String getIndiceAtomo() {
		return indiceAtomo;
	}


	public void setIndiceAtomo(String indiceAtomo) {
		this.indiceAtomo = indiceAtomo;
	}


	public String getLexame() {
		return lexame;
	}


	public void setLexame(String lexame) {
		this.lexame = lexame;
	}


	public Integer getAntesTruncagem() {
		return antesTruncagem;
	}


	public void setAntesTruncagem(Integer antesTruncagem) {
		this.antesTruncagem = antesTruncagem;
	}


	public Integer getDepoisTruncagem() {
		return depoisTruncagem;
	}


	public void setDepoisTruncagem(Integer depoisTruncagem) {
		this.depoisTruncagem = depoisTruncagem;
	}


	public Atomo getSimboloReservadoTipo() {
		return simboloReservadoTipo;
	}


	public void setSimboloReservadoTipo(Atomo simboloReservadoTipo) {
		this.simboloReservadoTipo = simboloReservadoTipo;
	}


	public Integer getLinha() {
		return linha;
	}


	public void setLinha(Integer linha) {
		this.linha = linha;
	}


	public List<Integer> getCincoPrimeirasLinhas() {
		return cincoPrimeirasLinhas;
	}


	public void setCincoPrimeirasLinhas(List<Integer> cincoPrimeirasLinhas) {
		this.cincoPrimeirasLinhas = cincoPrimeirasLinhas;
	}
	@Override
	public String toString() {
		return "ItemTabelaDeSimbolos [numEntradaTabSimbolos=" + numEntradaSimbolos + ", codigoAtomo=" + indiceAtomo
				+ ", lexame=" + lexame + ", qntCaracteresAntesTruncagem=" + antesTruncagem
				+ ", qntCaracteresDepoisTruncagem=" + depoisTruncagem + ", tipoSimbolo=" + simboloReservadoTipo
				+ ", linha=" + linha + ", cincoPrimeirasLinhas=" + cincoPrimeirasLinhas + "]";
	}

	
}
