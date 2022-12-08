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
			
	
		} catch (Exception e) {
			System.out.println("Erro ao abrir o arquivo");
			e.printStackTrace();
		}
	}
	
	public static void gerarRelatorioLexico(RelatorioLexico relatorioLexico) throws IOException {
		File relatorioLex = new File(fileName + ".LEX");
		FileWriter fileWriter = new FileWriter(relatorioLex);
		System.out.println("Relatorio Lexico");
		System.out.println("atomos " + relatorioLexico.getItens().size());
		fileWriter.append("Equipe E02 \n");
		fileWriter.append("Componentes: \n");
		fileWriter.append("Cristiano Filho, cristiano.filho@ucsal.edu.br, (71) 983973644 \n");
		fileWriter.append("Enzo Santana., enzo.almeida@ucsal.edu.br , (71) 991150087\n");
		fileWriter.append("Vinicius dos Santos., vinicius.candeias@ucsal.edu.br, (71) 984353370 \n \n");
		fileWriter.append("Rodrigo Fiuza., rodrigo.oliveira@ucsal.edu.br, (71) 981086001\n \n");
		
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
	


}
