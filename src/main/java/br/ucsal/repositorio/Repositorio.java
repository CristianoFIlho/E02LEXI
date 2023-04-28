package br.ucsal.repositorio;

import br.ucsal.modelo.PalavrasReservadas;
import br.ucsal.modelo.SimbolosReservados;
import br.ucsal.relatorio.RelatorioLexicos;
import br.ucsal.relatorio.RelatorioSimbolos;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Repositorio {
	public static File arquivo;
	private List<String> palavrasReservadas = PalavrasReservadas.getValoresPalavrasReservadas();
	private List<String> simbolosReservados = SimbolosReservados.getValoresSimbolosReservados();
	public final static List<RelatorioLexicos> lexicos = new ArrayList<>();
	public final static List<RelatorioSimbolos> simbolos = new ArrayList<>();
	public static String nomePrograma = "";
	public final static Set<String> funcoes = new HashSet<>();
	public final static Set<String> variaveis = new HashSet<>();

	public Repositorio(String caminhoArquivo) {

		File arquivo;
		if(caminhoArquivo.indexOf(".") != -1) {
			
			if (checkExtensao(caminhoArquivo, 4).equals(".222")) {
				arquivo = new File(caminhoArquivo);
			} else {
				throw new RuntimeException("Extensão inválido.");
			}
				
		} else {
			arquivo = new File(caminhoArquivo.concat(".222"));
		}
		
		if (arquivo.isFile())
			this.arquivo = arquivo;
		else
			throw new RuntimeException("Arquivo inválido.");
	}

	public static String checkExtensao(String texto, int length) {
		if (texto.length() <= length)
			return null;
		return texto.substring(texto.length() - length);
	}

	public File getArquivo() {
		return arquivo;
	}

	public List<String> getPalavrasReservadas() {
		return palavrasReservadas;
	}

	public List<String> getSimbolosReservados() {
		return simbolosReservados;
	}

	public Set<String> getFuncoes() {
		return funcoes;
	}

	public Set<String> getVariaveis() {
		return variaveis;
	}

	public void identificarSimbolos(String simbolo) {

	}

	public void verificarPalavraReservada(String lexeme) {
		PalavrasReservadas.validar(lexeme);
	}

	public void verificarSimbolosReservada(String lexeme) {
		SimbolosReservados.validar(lexeme);
	}

}
