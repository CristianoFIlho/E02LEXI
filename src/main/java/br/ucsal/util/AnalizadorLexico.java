package br.ucsal.util;

import br.ucsal.modelo.PalavrasReservadas;
import br.ucsal.modelo.SimbolosReservados;
import br.ucsal.repositorio.Repositorio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.*;

public class AnalizadorLexico {

    private Repositorio repositorio;

    public AnalizadorLexico(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void analisar() {
        try {
            String texto = primeiroFiltro();
            StringReader arquivo = new StringReader(texto);
            BufferedReader leitor = new BufferedReader(arquivo);
            String linha;
            while ((linha = leitor.readLine()) != null) {
                classificarLexeme(linha);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void classificarLexeme(String linha) {
        List<String> lexemes = separarLexemes(linha);
        if (lexemes == null) return;

        if (!lexemes.isEmpty() && lexemes.get(lexemes.size() - 1).equals("{")) {
            int index = lexemes.indexOf("(");
            if (index >= 0) repositorio.getFuncoes().add(lexemes.get(index - 1));
        }

        if (!lexemes.isEmpty() && lexemes.contains(";")) {
            int index = lexemes.indexOf(":=");
            if (index >= 0) repositorio.getVariaveis().add(lexemes.get(index - 1));
        }

        if (!lexemes.isEmpty() && lexemes.get(0).equals("programa")) {
            Repositorio.nomePrograma = lexemes.get(1);
        }

        lexemes.forEach(s -> {
            if (PalavrasReservadas.getValoresPalavrasReservadas().stream().anyMatch(v -> v.equalsIgnoreCase(s))) {
                PalavrasReservadas.validar(s);
            } else if (SimbolosReservados.getValoresSimbolosReservados().stream().anyMatch(v -> v.equalsIgnoreCase(s))) {
                SimbolosReservados.validar(s);
            } else {
                Identificadores.validar(s);
            }
        });
    }

    private List<String> separarLexemes(String linha) {
        linha = controleSimbolo(linha);

        final List<String> lexemes = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        boolean isString = false;
        char ultimoCharacterValido = ' ';

        for (int i = 0; i < linha.length(); i++) {
            char character = linha.charAt(i);
            boolean isNotSimbol = false;
            if (i > 0 && String.valueOf(linha.charAt(i - 1)).matches("[a-zA-Z]*")
                    && repositorio.getSimbolosReservados().contains(String.valueOf(linha.charAt(i))))
                isNotSimbol = true;
            if (character == '"') isString = !isString;
            String str = String.valueOf(ultimoCharacterValido) + character;

            if ((repositorio.getSimbolosReservados().contains(String.valueOf(character)) || repositorio.getSimbolosReservados().contains(str)) && !isString && !isNotSimbol) {

                if (repositorio.getSimbolosReservados().contains(str)) {
                    lexemes.set(lexemes.size() - 1, str);
                } else {
                    lexemes.add(String.valueOf(character));
                }
                buffer = new StringBuilder();
            }

            if (caracterValido(character) || isString || isNotSimbol) {
                buffer.append(character);
            } else if ((character == ' ' || character == '\n') && buffer.length() != 0) {
                lexemes.add(buffer.toString());
                buffer = new StringBuilder();
            }

            ultimoCharacterValido = character == ' ' ? ultimoCharacterValido : character;
        }

        return lexemes;
    }

    private String controleSimbolo(String linha) {
        final StringBuilder builder = new StringBuilder();
        boolean isString = false;

        for (int i = 0; i < linha.length(); i++) {
            boolean isNotSimbol = false;
            if (linha.charAt(i) == '"')
                isString = !isString;
            if (i > 0 && String.valueOf(linha.charAt(i - 1)).matches("[a-zA-Z]*")
                    && repositorio.getSimbolosReservados().contains(String.valueOf(linha.charAt(i))))
                isNotSimbol = true;
            if (repositorio.getSimbolosReservados().contains(String.valueOf(linha.charAt(i))) && !isString
                    && !isNotSimbol) {
                builder.append(" ");
            }

            builder.append(linha.charAt(i));
        }

        builder.append('\n');

        return builder.toString();
    }

    private boolean caracterValido(char texto) {
        return Character.isLetterOrDigit(texto) || "\"'._".contains(String.valueOf(texto));
    }

    private String primeiroFiltro() {
        String texto = null;
        try {
            texto = readString(repositorio.getArquivo().toPath(), StandardCharsets.US_ASCII);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return texto.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", "").replaceAll("(?:!|@|&|~|^|`|´)", "");
    }

}
