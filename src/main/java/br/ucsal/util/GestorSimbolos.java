package br.ucsal.util;
import br.ucsal.modelo.Atomo;
import br.ucsal.relatorio.RelatorioSimbolos;
import br.ucsal.repositorio.Repositorio;

public class GestorSimbolos {

    public static Integer construirSimbolo(String lexeme, Atomo atomo) {
        final String lexemeOriginal = lexeme;
        final Integer tamanhoOriginal = lexeme.length();

        lexeme = lexeme.length() > 30 ? lexeme.substring(0, 30) : lexeme;

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
