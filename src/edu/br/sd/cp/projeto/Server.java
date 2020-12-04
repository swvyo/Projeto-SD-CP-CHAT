package edu.br.sd.cp.projeto;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//definição do socket servidor
public class Server {
	private static ServerSocket ss;
	private static boolean sair;
	private static int cont;
	private static Users lista;

	// metodo principal para o servidor
	public static void main(String[] args) {
		try {
			cont = 0;
			lista = new Users();
			sair = false;
			ss = new ServerSocket(12345);// porta utilizada
			System.out.println("Conectado ao servidor!");
			while (!sair) {
				Socket s = ss.accept();
				lista.addUser(cont, s);
				ServerThread st = new ServerThread(s, lista, cont);
				st.start();
				cont++;
			}
		} catch (IOException io) {
			System.out.println("Erro no servidor encontrado!");
		}
	}

}