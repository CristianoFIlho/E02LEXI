package br.ucsal.util;
import br.ucsal.modelo.Atomo;
import br.ucsal.relatorio.RelatorioLexicos;
import br.ucsal.relatorio.RelatorioSimbolos;
import br.ucsal.repositorio.Repositorio;

public class Identificadores {
	public static void validar(String lexeme) {
		Atomo atomo = classificarIdentificadores(lexeme);
		construirSimbolo(atomo);
		inserirSimbolo(atomo);
	}
	public static Atomo classificarIdentificadores(String lexeme) {

		if (Repositorio.nomePrograma.equals(lexeme)){
			return new Atomo("nom-programa", "201", lexeme);
		} else if (Repositorio.variaveis.contains(lexeme)) {
			return new Atomo("variavel", "202", lexeme);
		} else if (Repositorio.funcoes.contains(lexeme)) {
			return new Atomo("nom-funcao", "203", lexeme);
		}if (lexeme.matches("^\\d+$")) {
			return new Atomo("cons-inteiro", "204", lexeme);
		} else if (lexeme.matches("[0-9]+.[0-9]+(\\e(\\-|\\+)?[0-9]+\\.[0-9]+)?")) {
			return new Atomo("cons-real", "205", lexeme);
		} else if (lexeme.matches("\"([a-zA-Z]|\\s|\\d|\\$|\\_|\\.)+\"")) {
			return new Atomo("cons-cadeia", "206", lexeme);
		} else if (lexeme.matches("\'[a-zA-Z]\'")) {
			return new Atomo("cons-caracter", "207", lexeme);
		}

		return new Atomo("", "", "");
	}

	private static void inserirSimbolo(Atomo atomo) {
		RelatorioLexicos lexico;

		if (Repositorio.lexicos.isEmpty()) {
			lexico = new RelatorioLexicos(1, atomo.getCodigo(), atomo.getAtomo(), atomo.getTipo());
		} else {
			final Integer ordem = Repositorio.lexicos.get(Repositorio.lexicos.size() - 1).getOrdem() + 1;
			lexico = new RelatorioLexicos(ordem, atomo.getCodigo(), atomo.getAtomo(), atomo.getTipo());
		}

		Repositorio.lexicos.add(lexico);
	}

	public static Integer construirSimbolo(Atomo atomo) {
		final String lexemeOriginal = atomo.getAtomo();
		final Integer tamanhoOriginal = atomo.getAtomo().length();

		String lexeme = atomo.getAtomo().length() > 30 ? atomo.getAtomo().substring(0, 30) : atomo.getAtomo();

		final Integer tamanhoFinal = lexeme.length();

		Integer indice = 0;
		boolean naoContem = false;

		if (!Repositorio.simbolos.isEmpty()) {
			naoContem = Repositorio.simbolos.stream().anyMatch(s -> !s.getLexeme().equals(lexemeOriginal));
			indice = Integer.parseInt(Repositorio.simbolos.get(Repositorio.simbolos.size() - 1).getNumeroEntradaTabela()) + 1;
		}

		if (Repositorio.simbolos.isEmpty() || naoContem) {
			indice = indice == 0 ? 1 : indice;
			RelatorioSimbolos relatorioSimbolos = new RelatorioSimbolos(String.valueOf(indice), atomo.getCodigo(), lexeme, atomo.getAtomo(), tamanhoOriginal, tamanhoFinal);
			Repositorio.simbolos.add(relatorioSimbolos);
		}

		return indice;
	}

}
