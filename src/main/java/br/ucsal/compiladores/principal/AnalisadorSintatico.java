package br.ucsal.compiladores.principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

import br.ucsal.compiladores.controller.AnalisadorLexico;
import br.ucsal.compiladores.controller.OutputLexico;
import br.ucsal.compiladores.tabelas.TabeladeSimbolos;

public class AnalisadorSintatico {
	private static String nomeDoArquivo;
	private static Scanner scanner = new Scanner(System.in);
	private static File arquivo;
	
	public static void main(String[] args) {
		try {
			System.out.println("Insira o nome do arquivo");
			nomeDoArquivo = scanner.next();
			
			arquivo = new File(nomeDoArquivo + ".222");
			
			AnalisadorLexico lexico = new AnalisadorLexico(new BufferedReader(new FileReader(arquivo)));
			OutputLexico outputLexico = lexico.gerarRelatorio();
			gerarRelatorioLexico(outputLexico);
			gerarTabelaDeSimbolos(outputLexico.getTabeladeSimbolos());
			
			
		} catch (Exception e) {
			System.out.println("Erro ao abrir o arquivo");
			e.printStackTrace();
		}
	}
	
	
	
	public static void gerarRelatorioLexico(OutputLexico outputLexico) throws IOException {
	
		OutputStream os = new FileOutputStream(nomeDoArquivo + ".LEX"); 
        Writer wr = new OutputStreamWriter(os); 
        BufferedWriter br = new BufferedWriter(wr);
		
		System.out.println("Output Lexico");
		System.out.println("atomos " + outputLexico.getItens().size());
		
		br.write("Equipe E02  ");
		br.newLine();
		br.write("Equipe: ");
		br.newLine();
		br.write("Cristiano Filho, cristiano.filho@ucsal.edu.br, (71) 983973644");
		br.newLine();
		br.write("Enzo Santana., enzo.almeida@ucsal.edu.br , (71) 991150087");
		br.newLine();
		br.write("Vinicius dos Santos., vinicius.candeias@ucsal.edu.br, (71) 984353370");
		br.newLine();
		br.write("Rodrigo Fiuza., rodrigo.oliveira@ucsal.edu.br, (71) 981086001");
		
		
		for (int i = 0; i < outputLexico.getItens().size(); i++) {
			br.write(outputLexico.getItens().get(i).toStringLex());
			br.newLine();
		}
		
		br.close();
		
	}
	
	public static void gerarTabelaDeSimbolos(TabeladeSimbolos simbolos) throws IOException {
		File tabSimbolos = new File(nomeDoArquivo + ".TAB");
		FileWriter fileWriter = new FileWriter(tabSimbolos);
		
		fileWriter.append(simbolos.toString());
		
		fileWriter.close();
	}
	
	

}
