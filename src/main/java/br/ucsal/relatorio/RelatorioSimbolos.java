package br.ucsal.relatorio;

import java.util.ArrayList;
import java.util.List;

public class RelatorioSimbolos {

    protected String numeroEntradaTabela;
    private final Integer tamanhoOriginal;
    private final Integer tamanhoFinal;
    private List<Integer> cincoPrimeirasLinhas = new ArrayList<>();
    protected String codigo;
    protected String lexeme;
    protected String tipoAtomo;

    public RelatorioSimbolos(String indice, String codigo, String lexeme,
                              String tipo, Integer tamanhoOriginal, Integer tamanhoFinal) {
        this.numeroEntradaTabela = indice;
        this.codigo = codigo;
        this.lexeme = lexeme;
        this.tipoAtomo = tipo;
        this.tamanhoOriginal = tamanhoOriginal;
        this.tamanhoFinal = tamanhoFinal;
    }

    public String getNumeroEntradaTabela() {
        return numeroEntradaTabela;
    }

    public String getLexeme() {
        return lexeme;
    }
    public String getTipoAtomo() {
        return tipoAtomo;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "TabelaSimbolos{" +
                "tipo='" + tipoAtomo + '\'' +
                ", tamanhoOriginal=" + tamanhoOriginal +
                ", tamanhoFinal=" + tamanhoFinal +
                ", cincoPrimeirasLinhas=" + cincoPrimeirasLinhas +
                ", numeroEntradaTabela='" + numeroEntradaTabela + '\'' +
                ", codigo='" + codigo + '\'' +
                ", lexeme='" + lexeme + '\'' +
                '}';
    }

}
