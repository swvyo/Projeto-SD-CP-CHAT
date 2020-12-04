package edu.br.sd.cp.projeto;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

//criador das threads para os clientes
public class ClientThread extends Thread {
	// declaração de variavéis
	private Socket s;
	private DataInputStream in;
	private boolean end;
	private String message, nome;

	public ClientThread(Socket s, String name) {
		this.s = s;
		this.nome = name;
	}

	public ClientThread() {

	}

	// inicializador e verificador de entradas
	public void run() {
		try {
			end = false;
			in = new DataInputStream(s.getInputStream());
			while (!end) {
				message = in.readUTF();
				System.out.println(message);
				if (message.equalsIgnoreCase("- " + nome + " Saiu da conversa.")) {
					end = true;
				}
			}
			s.close();
		} catch (IOException io) {
			System.out.println("Erro na thread do cliente encontrado!");
			io.printStackTrace();
			end = true;
		}
	}
}