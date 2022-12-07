package br.ucsal.compiladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AnalisadorSintatico {
	private static String fileName;
	private static Scanner scanner = new Scanner(System.in);
	private static File file;
	
	public static void main(String[] args) {
		try {
			System.out.println("Insira o nome do arquivo");
			fileName = scanner.next();
			
			if (fileName.contains(".201")) {
				System.out.println("Por favor insira o nome do arquivo sem a extensão");
				return;
			}
			
			file = new File(fileName + ".201");
			
			AnalisadorLexico lexico = new AnalisadorLexico(new BufferedReader(new FileReader(file)));
			RelatorioLexico relatorioLexico = lexico.gerarRelatorio();
			gerarRelatorioLexico(relatorioLexico);
			gerarTabelaDeSimbolos(relatorioLexico.getTabeladeSimbolos());
			
			/// ENTRADA
			//TODO: Caso seja inserido somente o nome do arquivo buscar no diretorio do static checker
			//TODO: caso seja inserido o diretorio mais o nome do arquivo, buscar no diretorio especificado
			
			
			/// SAIDA
			//TODO: para cada texto fonte recebido como parametro: (Ex.: MeuTeste.201)
			//TODO: gerar arquivo com relatorio da analise lexica, com extensao .LEX (Ex.: MeuTeste.LEX)
			//TODO: gerar arquivo com relatorio da tabela de simbolos, com extensao .TAB (Ex.: MeuTeste.TAB)
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao abrir o arquivo!");
			e.printStackTrace();
		}
	}
	
	public static void gerarRelatorioLexico(RelatorioLexico relatorioLexico) throws IOException {
		File relatorioLex = new File(fileName + ".LEX");
		FileWriter fileWriter = new FileWriter(relatorioLex);
		System.out.println("gerando Relatorio Lexico");
		System.out.println("atomos " + relatorioLexico.getItens().size());
		fileWriter.append("Equipe 03 \n");
		fileWriter.append("Componentes: \n");
		fileWriter.append("Eli Marins, eli.filho@ucsal.edu.br, (71) 99191-9488 \n");
		fileWriter.append("Gabriel Paim, gabriel.paim@ucsal.edu.br, (71) 99164-2456\n");
		fileWriter.append("Ibira Junior., ibira.junior@ucsal.edu.br, (71) 99387-7743 \n \n");
		
		for (int i = 0; i < relatorioLexico.getItens().size(); i++) {
			fileWriter.append(relatorioLexico.getItens().get(i).toStringLex()).append("\n");
		}
		
		fileWriter.close();
		
	}
	
	public static void gerarTabelaDeSimbolos(TabeladeSimbolos simbolos) throws IOException {
		File tabSimbolos = new File(fileName + ".TAB");
		FileWriter fileWriter = new FileWriter(tabSimbolos);
		
		fileWriter.append(simbolos.toString());
		
		fileWriter.close();
	}
	
	/**
	 * Arquivo .LEX
	 * deve mostrar a relação dos símbolos da linguagem que foram encontrados no texto fonte analisado, 
	 * na ordem em que estes aparecerem e tantas vezes quantas tenham aparecido.
	 * 
	 * deve indicar no cabeçalho: o código identificador da equipe, os nomes, e-mails e telefones 
	 * de todos os componentes da equipe que participaram da elaboração desta etapa do projeto.
	 * 
	 * Para cada linha detalhe do relatório de análise léxica, devem ser exibidas no mínimo as informações: 
	 * o elemento léxico formado (chamado de lexeme), o código do átomo correspondente a este elemento léxico 
	 * e o índice deste símbolo na tabela de símbolos (quando for um símbolo que seja armazenado nesta estrutura de dados).
	 * 
	 * 
	 * 
	 * 
	 * Arquivo .TAB
	 * deve mostrar todos os símbolos do tipo identificadores que foram armazenados na tabela de símbolos durante o processo de avaliação do texto fonte.
	 * 
	 * Para cada símbolo armazenado na tabela de símbolos devem ser relacionados 
	 * todos os atributos dele com todos os valores que foram preenchidos durante o funcionamento do Static Checker
	 */

}
