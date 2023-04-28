package br.ucsal.console;

import br.ucsal.util.AnalizadorLexico;
import br.ucsal.relatorio.GerarRelatorio;
import br.ucsal.repositorio.Repositorio;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		Boolean caminhoInexistente = false;
		String caminhoArquivo;
		Scanner scanner = new Scanner(System.in);

		do {
			caminhoInexistente = true;
			caminhoArquivo = JOptionPane.showInputDialog("Informe o nome do arquivo: ");
			if (caminhoArquivo == null) {
				JOptionPane.showMessageDialog(null, "Execução abortada!");
				System.exit(0);
			}
			try {
				System.out.println(caminhoArquivo);
				Repositorio repositorio = new Repositorio(caminhoArquivo);
				AnalizadorLexico analizadorLexico = new AnalizadorLexico(repositorio);
				analizadorLexico.analisar();
				JOptionPane.showMessageDialog(null, "Execução finalizada com sucesso!");
			}
			catch (RuntimeException e) {
				System.out.println("Nome invalido -> " + e.getMessage());
			    JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.ERROR_MESSAGE);
			    caminhoInexistente = false;
			}			
		}while(!caminhoInexistente);

		GerarRelatorio.gerarRelatorioLexico(Repositorio.lexicos, caminhoArquivo);
		GerarRelatorio.gerarRelatorioSimbolos(Repositorio.simbolos, caminhoArquivo);
		scanner.close();
	}
}
